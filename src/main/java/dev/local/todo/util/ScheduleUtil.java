package dev.local.todo.util;

import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import dev.local.todo.util.JavaMailUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import java.text.SimpleDateFormat;
import net.sf.json.JSONObject;
import dev.local.todo.service.RecordService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ScheduleUtil {

    @Autowired
    private JavaMailUtil javaMailUtil;

    @Autowired
    RecordService recordService;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0 00 11,21 * * ?")
    public void sendReminderEmail() {
        List<JSONObject> problems = recordService.trackRecord(new Date(), new int[]{-3,-7});
        StringBuilder text = new StringBuilder();
        text.append("Please practise following problems: ");

        for(int i = 0; i < problems.size();i++) {
            String num = problems.get(i).get("number").toString();
            String name = problems.get(i).get("name").toString();
            text.append(num+". ");
            text.append(name+" ");
        }

        try {
//            javaMailUtil.sendSimpleEmail("happydailycode@gmail.com", new String[] { "suncihai@gmail.com" },
//                    new String[] {}, "Reminder from Daily Code", text.toString());
            String thymeleafTemplatePath = "EmailTemplate";
            Map<String, Object> thymeleafTemplateVariable = new HashMap<String, Object>();
            thymeleafTemplateVariable.put("problems", problems);
            javaMailUtil.sendTemplateEmail("happydailycode@gmail.com",
                    new String[] { "suncihai@gmail.com" },
                    new String[] {},
                    "Reminder from Daily Code",
                    thymeleafTemplatePath,
                    thymeleafTemplateVariable);
        } catch (Exception e) {
            logger.error("邮件发送失败, 失败原因 :{} 。", e.getMessage(), e);
        }
    }
}