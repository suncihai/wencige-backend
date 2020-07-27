package dev.local.todo.controller;

import dev.local.todo.api.ApiResponse;
import io.swagger.annotations.*;
import dev.local.todo.dao.*;
import dev.local.todo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import dev.local.todo.service.*;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.StringUtils;
import dev.local.todo.api.ApiBase;

import java.util.List;

import static sun.misc.PostVMInitHook.run;

@RestController
public class UserController {

   @Autowired
   UserRepository userRepository;

   @Autowired
   UserService userService;

   @ApiOperation(value = "Register", response = Iterable.class)
   @ApiImplicitParams({
           @ApiImplicitParam(name = "username", value = "username, e.g. peter@gmail.comr", required = true, dataType = "String", paramType = "query"),
           @ApiImplicitParam(name = "password", value = "password, e.g. 12345678", required = true, dataType = "String", paramType = "query"),
   })
   @ApiResponses(value = {
           @io.swagger.annotations.ApiResponse(code = 200, message = "" +
                   "说明       | code码 <br/>" +
                   "成功       | 0 <br/>" +
                   "失败       | -1 <br/>"
           )
   }
   )
   @RequestMapping("/register")
   public @ResponseBody ApiResponse Register(final String username, final String password) {
      return new ApiBase() {
         @Override
         protected void validate() throws Exception {
            Validate.notNull(username,"username is empty");
            Validate.notNull(password,"password is empty");
         }

         @Override
         protected ApiResponse process() throws Exception {
            return userService.register(username, password);
         }
      }.run();
   }

   @ApiOperation(value = "Login", response = Iterable.class)
   @ApiImplicitParams({
           @ApiImplicitParam(name = "username", value = "username, e.g. peter@gmail.comr", required = true, dataType = "String", paramType = "query"),
           @ApiImplicitParam(name = "password", value = "password, e.g. 12345678", required = true, dataType = "String", paramType = "query"),
   })
   @ApiResponses(value = {
           @io.swagger.annotations.ApiResponse(code = 200, message = "" +
                   "说明       | code码 <br/>" +
                   "成功       | 0 <br/>" +
                   "失败       | -1 <br/>"
           )
   }
   )
   @RequestMapping("/login")
   public @ResponseBody ApiResponse Login(final String username, final String password) {
      return new ApiBase() {
         @Override
         protected void validate() throws Exception {
            Validate.notNull(username,"username is empty");
            Validate.notNull(password,"password is empty");
         }

         @Override
         protected ApiResponse process() throws Exception {
            return userService.login(username, password);
         }
      }.run();
   }

}
