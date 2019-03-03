package cn.bin2.sport.core.Exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 10:49 2019/1/25
 * @Modified By:
 */
public class BadRequestException extends RuntimeException {
    private Integer status = BAD_REQUEST.value();

    public BadRequestException(String msg){
        super(msg);
    }

    public BadRequestException(HttpStatus status, String msg){
        super(msg);
        this.status = status.value();
    }
}
