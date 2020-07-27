package dev.local.todo.controller;

import dev.local.todo.api.ApiBase;
import dev.local.todo.api.ApiResponse;
import dev.local.todo.dao.ProblemRepository;
import dev.local.todo.service.ProblemService;
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
public class ProblemController {

   @Autowired
   ProblemRepository problemRepository;

   @Autowired
   ProblemService problemService;

   @ApiOperation(value = "get problems", response = Iterable.class)
   @ApiResponses(value = {
           @io.swagger.annotations.ApiResponse(code = 200, message = "" +
                   "说明       | code码 <br/>" +
                   "成功       | 0 <br/>" +
                   "失败       | -1 <br/>"
           )
   }
   )
   @RequestMapping("/get_problems")
   public @ResponseBody ApiResponse getRecords(final String username) {
      return new ApiBase() {

         @Override
         protected void validate() throws Exception {
         }

         @Override
         protected ApiResponse process() throws Exception {
            return problemService.getProblems();
         }
      }.run();
   }
}
