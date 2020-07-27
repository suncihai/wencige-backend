package dev.local.todo.service;

import dev.local.todo.api.*;
import dev.local.todo.dao.UserRepository;
import dev.local.todo.model.User;
import io.swagger.annotations.Api;
import dev.local.todo.util.JavaMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;
import java.sql.Timestamp;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailUtil javaMailUtil;

    public ApiResponse register(String username, String password) {
        JSONObject response = new JSONObject();
        User find = userRepository.findUser(username);
        if(find != null) {
            return ApiResponse.createFailure(ApiCode.User.REGISTERFAILURE);
        }

        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        response.put("name",user.getUserName());
        userRepository.save(user);

        return ApiResponse.createSuccess(ApiCode.User.ADDSUCCESS, response);
    }

    public ApiResponse login(String username, String password) {
        JSONObject response = new JSONObject();
        User find = userRepository.findUser(username);
        if(find == null) {
            return ApiResponse.createFailure(ApiCode.User.LOGIN_USERNAME_FAILURE);
        }

        if(find.getPassword().equals(password)) {
            return ApiResponse.createSuccess(ApiCode.User.ADDSUCCESS, response);
        }else{
            return ApiResponse.createFailure(ApiCode.User.LOGIN_PASSWORD_FAILURE);
        }
    }

}
