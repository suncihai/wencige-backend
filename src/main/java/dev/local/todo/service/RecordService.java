package dev.local.todo.service;

import dev.local.todo.api.ApiCode;
import dev.local.todo.api.ApiResponse;
import dev.local.todo.dao.RecordRepository;
import dev.local.todo.dao.ProblemRepository;
import dev.local.todo.model.Problem;
import dev.local.todo.model.Record;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.local.todo.util.LocalDateTimeUtil;
import org.apache.commons.lang3.StringUtils;
import java.sql.Timestamp;

import java.util.*;

@Service
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private ProblemRepository problemRepository;

    public ApiResponse getRecord(String username, int page) {
        JSONObject response = new JSONObject();

        List<Record> record = recordRepository.findAll();

        if(record == null) {
            return ApiResponse.createFailure(ApiCode.User.REGISTERFAILURE);
        }

        List<List<Record>> previousMonthRecord = filterRecord(record, -2-3*(page-1));
        List<List<Record>> lastMonthRecord = filterRecord(record, -1-3*(page-1));
        List<List<Record>> currentMonthRecord = filterRecord(record, 0-3*(page-1));

        int prevMonthCount = countRecord(record, -2-3*(page-1));
        int lastMonthCount = countRecord(record, -1-3*(page-1));
        int currMonthCount = countRecord(record, 0-3*(page-1));

        response.put("prevMonthRecord", previousMonthRecord);
        response.put("lastMonthRecord", lastMonthRecord);
        response.put("currMonthRecord", currentMonthRecord);
        response.put("prevMonthCount", prevMonthCount);
        response.put("lastMonthCount", lastMonthCount);
        response.put("currMonthCount", currMonthCount);

        return ApiResponse.createSuccess(ApiCode.User.ADDSUCCESS, response);
    }

    public List<List<Record>> filterRecord(List<Record> record, int monthCount) {
        List<List<Record>> result = new LinkedList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, monthCount); //get the month
        calendar.set(Calendar.DAY_OF_MONTH, 1);// From 1st of the Month
        int dayNumOfMonth = LocalDateTimeUtil.getDaysByYearMonth(calendar.getTime());

        for (int i = 0; i < dayNumOfMonth; i++, calendar.add(Calendar.DATE, 1)) {
            Date d = calendar.getTime();
            Long dayStart = LocalDateTimeUtil.getStartOfDay(d);
            Long dayEnd = LocalDateTimeUtil.getEndOfDay(d);
            List<Record> list = new LinkedList<>();
            for(int j = 0; j < record.size(); j ++) {
                Long timestamp = record.get(j).getCreateTime().getTime();
                if( timestamp > dayStart && timestamp < dayEnd) {
                    list.add(record.get(j));
                }else if( timestamp > dayEnd) {
                    break;
                }
            }
            result.add(list);
        }
        return result;
    }

    public int countRecord(List<Record> record, int monthCount) {
        int count = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, monthCount); //get the month
        calendar.set(Calendar.DAY_OF_MONTH, 1);// From 1st of the Month
        int dayNumOfMonth = LocalDateTimeUtil.getDaysByYearMonth(calendar.getTime());

        for (int i = 0; i < dayNumOfMonth; i++, calendar.add(Calendar.DATE, 1)) {
            Date d = calendar.getTime();
            Long dayStart = LocalDateTimeUtil.getStartOfDay(d);
            Long dayEnd = LocalDateTimeUtil.getEndOfDay(d);
            for(int j = 0; j < record.size(); j ++) {
                Long timestamp = record.get(j).getCreateTime().getTime();
                if( timestamp > dayStart && timestamp < dayEnd) {
                    count++;
                }else if( timestamp > dayEnd) {
                    break;
                }
            }
        }
        return count;
    }

    public ApiResponse submitRecords(String username, String problems, Long timestamp, Boolean success, String language) {

        JSONObject response = new JSONObject();

        String[] problemList = new String[]{};
        if(StringUtils.isNoneBlank(problems)) {
            problemList = problems.split("-");
        }

        for(int i = 0; i < problemList.length; i++) {
            Timestamp ts= new Timestamp(timestamp);
            Record record = new Record(username, Integer.valueOf(problemList[i]), ts, success, language);
            recordRepository.save(record);
        }

        return ApiResponse.createSuccess(ApiCode.User.ADDSUCCESS, response);
    }

    public List<JSONObject> trackRecord(Date date, int[] days) {
        if(days == null || days.length == 0) {
            days = new int[]{-3};
        }

        List<Record> record = recordRepository.findAll();
        List<Problem> problems = problemRepository.findAll();
        List<Integer> nums = new LinkedList<>();
        List<JSONObject> result = new JSONArray();

        for(int i = 0; i < days.length; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, days[i]);
            Date d = calendar.getTime();
            Long dayStart = LocalDateTimeUtil.getStartOfDay(d);
            Long dayEnd = LocalDateTimeUtil.getEndOfDay(d);
            for(int j = 0; j < record.size(); j ++) {
                Long timestamp = record.get(j).getCreateTime().getTime();
                if( timestamp > dayStart && timestamp < dayEnd) {
                    nums.add(record.get(j).getProblem());
                }else if( timestamp > dayEnd) {
                    break;
                }
            }
        }

        for(int k = 0; k < problems.size(); k++) {
            for(int l = 0; l < nums.size(); l++) {
                JSONObject problem = new JSONObject();
                if(problems.get(k).getNumber() == nums.get(l)) {
                    problem.put("number", problems.get(k).getNumber());
                    problem.put("name", problems.get(k).getName());
                    result.add(problem);
                }
            }
        }

        return result;
    }
}
