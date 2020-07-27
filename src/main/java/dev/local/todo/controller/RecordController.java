package dev.local.todo.controller;

import dev.local.todo.api.ApiBase;
import dev.local.todo.api.ApiResponse;
import dev.local.todo.dao.RecordRepository;
import dev.local.todo.service.RecordService;
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
public class RecordController {

   @Autowired
   RecordRepository recordRepository;

   @Autowired
   RecordService recordService;

   @ApiOperation(value = "get records", response = Iterable.class)
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
   @RequestMapping("/get_record")
   public @ResponseBody ApiResponse getRecords(final String username, int page) {
      return new ApiBase() {
         @Override
         protected void validate() throws Exception {
            Validate.notNull(username,"username is empty");
            Validate.notNull(page,"page is empty");
         }

         @Override
         protected ApiResponse process() throws Exception {
            return recordService.getRecord(username, page);
         }
      }.run();
   }

   @ApiOperation(value = "submit records", response = Iterable.class)
   @ApiImplicitParams({
           @ApiImplicitParam(name = "username", value = "username, e.g. peter@gmail.comr", required = true, dataType = "String", paramType = "query"),
           @ApiImplicitParam(name = "problems", value = "array of problems, e.g. 1,4,10", required = true, dataType = "String", paramType = "query"),
           @ApiImplicitParam(name = "timestamp", value = "submit time, e.g. 1559777307000", required = true, dataType = "Long", paramType = "query"),
           @ApiImplicitParam(name = "success or not of problem", value = ", e.g. true", required = true, dataType = "Boolean", paramType = "query"),
           @ApiImplicitParam(name = "language", value = "javascript, e.g.java", required = true, dataType = "String", paramType = "query"),
   })
   @ApiResponses(value = {
           @io.swagger.annotations.ApiResponse(code = 200, message = "" +
                   "说明       | code码 <br/>" +
                   "成功       | 0 <br/>" +
                   "失败       | -1 <br/>"
           )
   }
   )
   @RequestMapping("/submit_records")
   public @ResponseBody ApiResponse submitRecords(final String username, final String problems, final Long timestamp, final Boolean success, String language) {
      return new ApiBase() {
         @Override
         protected void validate() throws Exception {
            Validate.notNull(username,"username is empty");
            Validate.notNull(problems,"username is empty");
            Validate.notNull(timestamp,"username is empty");
            Validate.notNull(success,"username is empty");
            Validate.notNull(language,"language is empty");
         }

         @Override
         protected ApiResponse process() throws Exception {
            return recordService.submitRecords(username, problems, timestamp, success, language);
         }
      }.run();
   }

}
