package cn.gaozheng.common.annotation;

import java.lang.annotation.*;

/**
 * @a hor Cheng Bo
 * @version 1.0
 */
@Documented
@Inherited
@Repeatable(JsonIncludes.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface JsonInclude {

    Class filter();

    String[] includes() default {};

    String[] excludes() default {};

}
