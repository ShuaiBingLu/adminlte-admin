package cn.bin2.sport.core.Exception;

import cn.bin2.sport.common.util.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 10:46 2019/1/25
 * @Modified By:
 */
@Slf4j
@ControllerAdvice
public class GlobelExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception e){
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        ApiError apiError = new ApiError(BAD_REQUEST.value(),e.getMessage());
        return buildResponseEntity(apiError);
    }
    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }
}
