package com.xiaoqiang.netty.study.http;

public class Response {
    private Status status;
    private Header header;

    private Object body;

//    private

    public static class Status {
        private String protocol;
        private int code;
        private String message;

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return protocol + " " + code + " " + message + "\r\n";
        }
    }

    public static class Header {
        private String contentType;

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        @Override
        public String toString() {
            return "Content-Type:" + contentType + "\r\n";
        }
    }
}
