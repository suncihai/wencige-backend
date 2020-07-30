package dev.local.todo.service;

import dev.local.todo.api.ApiCode;
import dev.local.todo.api.ApiResponse;
import dev.local.todo.dao.CommentsRepository;
import dev.local.todo.dao.ProblemRepository;
import dev.local.todo.model.Comments;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CommentsService {
    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private ProblemRepository problemRepository;

    public ApiResponse getComments() {
        JSONObject response = new JSONObject();

        List<Comments> comments = commentsRepository.findAll();

        if(comments == null) {
            return ApiResponse.createFailure(ApiCode.User.REGISTERFAILURE);
        }

        response.put("comments", comments);

        return ApiResponse.createSuccess(ApiCode.User.ADDSUCCESS, response);
    }

    public ApiResponse submitComment(String username, String text, Long timestamp) {

        JSONObject response = new JSONObject();

        Timestamp ts= new Timestamp(timestamp);
        Comments comment = new Comments(username, text, ts);
        commentsRepository.save(comment);

        return ApiResponse.createSuccess(ApiCode.User.ADDSUCCESS, response);
    }
}
