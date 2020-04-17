package com.springboot.result;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JSONResult<T> extends ResponseEntity<Message> {

    public JSONResult(HttpStatus status) {
        super(status);
    }

    public JSONResult(String code, String msg, T data) {
        super(Message.custom(code, msg, data), Message.num2HttpStatus(code));
    }

    public JSONResult(String code, String msg) {
        super(Message.custom(code, msg), Message.num2HttpStatus(code));
    }

    public static <T> JSONResult<T> successData(T data) {
        return new JSONResult("200", "成功", data);
    }

    public static <T> JSONResult<T> success() {
        return new JSONResult("200", "成功");
    }

    public static <T> JSONResult<T> failed(T data) {
        return new JSONResult("422", "失败", data);
    }

    public static <T> JSONResult<T> failMsg(String msg) {
        return new JSONResult("422", msg);
    }

    public static <T> JSONResult<T> custom(String code, String msg, T data) {
        return new JSONResult(code, msg, data);
    }
}

class Message<T> {

    String status;
    //向前端返回的内容
    String message;

    T data;

    public Message() {
    }

    public Message(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public Message(String status, String message, T data) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public static <T> Message<T> custom(String status, String message, T data) {
        return new Message(status, message, data);
    }

    public static <T> Message<T> custom(String status, String message) {
        return new Message(status, message);
    }

    public static HttpStatus num2HttpStatus(String code) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        for (HttpStatus httpStatus : HttpStatus.values()) {
            boolean b = Integer.parseInt(code) == httpStatus.value();
            if (b) {
                return httpStatus;
            }
        }
        return status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
