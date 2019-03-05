package cn.bin2.sport.common.util;

import java.lang.annotation.*;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 8:44 2019/3/5
 * @Modified By:
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface JobHandle {
    String value() default "";
}
