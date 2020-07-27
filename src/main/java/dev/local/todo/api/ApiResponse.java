package dev.local.todo.api;

import java.io.Serializable;

public class ApiResponse implements Serializable{
   private static final long serialVersionUID = 1L;

   private String msg;
   private int code;
   private long elapseMills;
   private Object data;

   public String getMsg() {
      return msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public int getCode() {
      return code;
   }

   public void setCode(int code) {
      this.code = code;
   }

   public Object getData() {
      return data;
   }

   public void setData(Object data) {
      this.data = data;
   }

   public long getElapseMills() {return elapseMills;}

   public void setElapseMills(long elapseMills) {this.elapseMills = elapseMills;}

   public static ApiResponse createSuccess(ApiCode.CodeMessage msg, Object data) {
      ApiResponse bean = new ApiResponse();
      bean.setMsg(msg.getMsg());
      bean.setCode(msg.getCode());
      bean.setData(data);
      return bean;
   }

   public static ApiResponse createSuccess(ApiCode.CodeMessage msg) {
      ApiResponse bean = new ApiResponse();
      bean.setMsg(msg.getMsg());
      bean.setCode(msg.getCode());
      return bean;
   }

   public static ApiResponse createFailure(ApiCode.CodeMessage msg) {
      ApiResponse bean = new ApiResponse();
      bean.setMsg(msg.getMsg());
      bean.setCode(msg.getCode());
      return bean;
   }

   @Override
   public String toString() {
      return "RestResponse{" +
              "code=" + code +
              ", msg='" + msg + '\'' +
              ", elapseMills='" + elapseMills + '\'' +
              ", data=" + data +
              '}';
   }

}
