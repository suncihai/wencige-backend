package dev.local.todo.service;

import dev.local.todo.api.ApiCode;
import dev.local.todo.api.ApiResponse;
import dev.local.todo.dao.ProblemRepository;
import dev.local.todo.model.Problem;
import dev.local.todo.util.LocalDateTimeUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    public ApiResponse getProblems() {
        List<Problem> problems = problemRepository.findAll();

        if(problems == null) {
            return ApiResponse.createFailure(ApiCode.User.REGISTERFAILURE);
        }

        return ApiResponse.createSuccess(ApiCode.User.ADDSUCCESS, JSONArray.fromObject(problems));
    }

}
