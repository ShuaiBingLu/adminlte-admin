package cn.bin2.sport.core.Exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 10:59 2019/1/25
 * @Modified By:
 */
@Data
class ApiError {

    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(Integer status,String message) {
        this();
        this.status = status;
        this.message = message;
    }
}
