package cn.bin2.sport.core.entity;

import java.io.Serializable;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 9:12 2019/1/26
 * @Modified By:
 */
public class ReponseEntity implements Serializable {

    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_FAIL = 500;
    public static final int STATUS_NOT_PERMISSION = 403;
    private int status;
    private String message;
    private Object data;


    public static ReponseEntity notPermission(String message){
        return ReponseEntity.createResult(STATUS_NOT_PERMISSION,message,null);
    }

    public static ReponseEntity success() {
        return ReponseEntity.createResult(STATUS_SUCCESS, "成功", null);
    }

    public static ReponseEntity success(String message) {
        return ReponseEntity.createResult(STATUS_SUCCESS, message, null);
    }

    public static ReponseEntity success(String message, Object data) {
        return ReponseEntity.createResult(STATUS_SUCCESS, message, data);
    }

    public static ReponseEntity fail() {
        return ReponseEntity.createResult(STATUS_FAIL, "失败", null);
    }

    public static ReponseEntity fail(String message) {
        return ReponseEntity.createResult(STATUS_FAIL, message, null);
    }

    public static ReponseEntity fail(int status, String message) {
        return ReponseEntity.createResult(status, message, null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private static ReponseEntity createResult(int status, String message, Object data) {
        ReponseEntity baseResult = new ReponseEntity();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        baseResult.setData(data);
        return baseResult;
    }
}
