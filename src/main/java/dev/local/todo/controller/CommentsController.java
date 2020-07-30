package dev.local.todo.controller;

import dev.local.todo.api.ApiBase;
import dev.local.todo.api.ApiResponse;
import dev.local.todo.dao.CommentsRepository;
import dev.local.todo.service.CommentsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentsController {

   @Autowired
   CommentsRepository commentsRepository;

   @Autowired
   CommentsService commentsService;

   @ApiOperation(value = "get posts", response = Iterable.class)
   @ApiImplicitParams({
           @ApiImplicitParam(name = "username", value = "username, e.g. peter@gmail.comr", required = true, dataType = "String", paramType = "query"),
   })
   @ApiResponses(value = {
           @io.swagger.annotations.ApiResponse(code = 200, message = "" +
                   "说明       | code码 <br/>" +
                   "成功       | 0 <br/>" +
                   "失败       | -1 <br/>"
           )
   }
   )
   @RequestMapping("/get-comments")
   public @ResponseBody ApiResponse getComments() {
      return new ApiBase() {
         @Override
         protected void validate() throws Exception {
         }

         @Override
         protected ApiResponse process() throws Exception {
            return commentsService.getComments();
         }
      }.run();
   }

   @ApiOperation(value = "submit comment", response = Iterable.class)
   @ApiImplicitParams({
           @ApiImplicitParam(name = "username", value = "username, e.g. peter@gmail.comr", required = true, dataType = "String", paramType = "query"),
           @ApiImplicitParam(name = "text", value = "post text, e.g. Today I was playing tennis...", required = true, dataType = "String", paramType = "query"),
           @ApiImplicitParam(name = "timestamp", value = "submit time, e.g. 1559777307000", required = true, dataType = "Long", paramType = "query"),
   })
   @ApiResponses(value = {
           @io.swagger.annotations.ApiResponse(code = 200, message = "" +
                   "说明       | code码 <br/>" +
                   "成功       | 0 <br/>" +
                   "失败       | -1 <br/>"
           )
   }
   )
   @RequestMapping("/submit-comment")
   public @ResponseBody ApiResponse submitComment(final String username, final String text, final Long timestamp) {
      return new ApiBase() {
         @Override
         protected void validate() throws Exception {
            Validate.notNull(username,"username is empty");
            Validate.notNull(text,"text is empty");
            Validate.notNull(timestamp,"timestamp is empty");
         }

         @Override
         protected ApiResponse process() throws Exception {
            return commentsService.submitComment(username, text, timestamp);
         }
      }.run();
   }

}
