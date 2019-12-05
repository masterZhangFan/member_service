package cn.gaozheng.common.annotation;

import java.lang.annotation.*;

/**
 * Js Includes
 *
 * @author Cheng Bo
 * @version 1.0
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface JsonIncludes {

    JsonInclude[] value();
}
