package dev.local.todo.api;

import org.aspectj.apache.bcel.classfile.Code;

public abstract class ApiCode {

    public static class CodeMessage {
        String msg;
        int code;

        public CodeMessage() {}

        public CodeMessage(String msg, int code) {
            this.code = code;
            this.msg = msg;
        }

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
    }

    public static class Common {
        public static CodeMessage SUCCESS = new CodeMessage("success", 0);
        public static CodeMessage FAILURE = new CodeMessage("failure", -1);
        public static CodeMessage PARAM_ERROR = new CodeMessage("failure", -6);
    }

    public static class User {
        public static CodeMessage ADDSUCCESS = new CodeMessage("success", 0);
        public static CodeMessage ADDFAILURE = new CodeMessage("failure", -2);
        public static CodeMessage DELETESUCCESS = new CodeMessage("success", 0);
        public static CodeMessage DELETEFAILURE = new CodeMessage("failure", -3);
        public static CodeMessage FINDFAILURE = new CodeMessage("failure", -4);
        public static CodeMessage REGISTERFAILURE = new CodeMessage("failure", -5);
        public static CodeMessage LOGIN_USERNAME_FAILURE = new CodeMessage("failure", -7);
        public static CodeMessage LOGIN_PASSWORD_FAILURE = new CodeMessage("failure", -8);
    }

}
