package cn.gaozheng.sales.model.vo.base;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * Rest Response
 *
 * @author Cheng Bo
 * @version 1.0
 */
@Data
public class RestResponse<T> {

    private int status;

    private String msg;

    private T data;

    private RestResponse(int status, String msg, T data){this.status = status;this.msg = msg;this.data =data;}

    public static <T> RestResponse<T> getInstance(int status){
        return getInstance(status, StringUtils.EMPTY, null);
    }

    public static <T> RestResponse<T> getInstance(int status, String msg){
        return getInstance(status, msg, null);
    }
    public static <T> RestResponse<T> getInstance(int status, String msg, T data){
        return new RestResponse<>(status, msg, data);
    }
}
