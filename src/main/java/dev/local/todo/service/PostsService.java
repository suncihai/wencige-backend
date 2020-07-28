package dev.local.todo.service;

import dev.local.todo.api.ApiCode;
import dev.local.todo.api.ApiResponse;
import dev.local.todo.dao.PostsRepository;
import dev.local.todo.dao.ProblemRepository;
import dev.local.todo.model.Problem;
import dev.local.todo.model.Posts;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.local.todo.util.LocalDateTimeUtil;
import org.apache.commons.lang3.StringUtils;
import java.sql.Timestamp;

import java.util.*;

@Service
public class PostsService {
    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private ProblemRepository problemRepository;

    public ApiResponse getPosts() {
        JSONObject response = new JSONObject();

        List<Posts> posts = postsRepository.findAll();

        if(posts == null) {
            return ApiResponse.createFailure(ApiCode.User.REGISTERFAILURE);
        }

        response.put("prevMonthRecord", posts);

        return ApiResponse.createSuccess(ApiCode.User.ADDSUCCESS, response);
    }

    public ApiResponse submitPost(String username, String title, String text, String imageUrl, Long timestamp) {

        JSONObject response = new JSONObject();

        Timestamp ts= new Timestamp(timestamp);
        Posts post = new Posts(username, title, text, imageUrl, ts);
        postsRepository.save(post);

        return ApiResponse.createSuccess(ApiCode.User.ADDSUCCESS, response);
    }
}
