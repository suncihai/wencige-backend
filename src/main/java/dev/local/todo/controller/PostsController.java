package dev.local.todo.controller;

import dev.local.todo.api.ApiBase;
import dev.local.todo.api.ApiResponse;
import dev.local.todo.dao.PostsRepository;
import dev.local.todo.service.PostsService;
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
public class PostsController {

   @Autowired
   PostsRepository postsRepository;

   @Autowired
   PostsService postsService;

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
   @RequestMapping("/get_posts")
   public @ResponseBody ApiResponse getRecords() {
      return new ApiBase() {
         @Override
         protected void validate() throws Exception {
         }

         @Override
         protected ApiResponse process() throws Exception {
            return postsService.getPosts();
         }
      }.run();
   }

   @ApiOperation(value = "submit post", response = Iterable.class)
   @ApiImplicitParams({
           @ApiImplicitParam(name = "username", value = "username, e.g. peter@gmail.comr", required = true, dataType = "String", paramType = "query"),
           @ApiImplicitParam(name = "title", value = "post title, e.g. A happy day", required = true, dataType = "String", paramType = "query"),
           @ApiImplicitParam(name = "text", value = "post text, e.g. Today I was playing tennis...", required = true, dataType = "String", paramType = "query"),
           @ApiImplicitParam(name = "imageUrl", value = "post image's url, e.g. assets/image/xxxx.jpg", required = true, dataType = "String", paramType = "query"),
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
   @RequestMapping("/submit-post")
   public @ResponseBody ApiResponse submitRecords(final String username, final String title, final String text, final String imageUrl, final Long timestamp) {
      return new ApiBase() {
         @Override
         protected void validate() throws Exception {
            Validate.notNull(username,"username is empty");
            Validate.notNull(title,"title is empty");
            Validate.notNull(text,"text is empty");
            Validate.notNull(imageUrl,"imageUrl is empty");
            Validate.notNull(timestamp,"timestamp is empty");
         }

         @Override
         protected ApiResponse process() throws Exception {
            return postsService.submitPost(username, title, text, imageUrl, timestamp);
         }
      }.run();
   }

}
