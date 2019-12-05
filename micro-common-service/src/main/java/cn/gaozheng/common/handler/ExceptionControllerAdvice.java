package cn.gaozheng.common.handler;

import cn.gaozheng.mini.exception.ServiceException;
import cn.gaozheng.sales.exception.SaleException;
import cn.gaozheng.sales.exception.SalesException;
import cn.gaozheng.sales.model.vo.base.RespCode;
import cn.gaozheng.sales.model.vo.base.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public RestResponse<String> handleConstraintViolationException(ConstraintViolationException ex) {

        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

        int i = 0;
        String[] messages = new String[constraintViolations.size()];
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            Path path = constraintViolation.getPropertyPath();
            if (path instanceof PathImpl) {
                PathImpl pathImpl = (PathImpl) path;
                messages[i] = pathImpl.getLeafNode().asString() + " : " + constraintViolation.getMessage();
            } else {
                messages[i] = path.toString() + " : " + constraintViolation.getMessage();
            }
            i++;
        }

        return handleException(ex, RespCode.FAILED, StringUtils.join(messages, ","));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResponse<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        int i = 0;
        String[] messages = new String[fieldErrorList.size()];
        for (FieldError error : fieldErrorList) {
            messages[i] = error.getDefaultMessage();
            i++;
        }
        return handleException(ex, RespCode.FAILED, StringUtils.join(messages, ","));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BindException.class)
    public RestResponse<String> handleMethodArgumentNotValidException(BindException ex) {

        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        int i = 0;
        String[] messages = new String[fieldErrorList.size()];
        for (FieldError error : fieldErrorList) {
            messages[i] = error.getDefaultMessage();
            i++;
        }
        return handleException(ex, RespCode.FAILED, StringUtils.join(messages, ","));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoHandlerFoundException.class)
    public RestResponse<String> noHandlerFoundException(NoHandlerFoundException ex) {
        return handleException(ex, RespCode.NO_HANDLER, ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(FileUploadException.class)
    public RestResponse<String> serviceExceptionHandler(FileUploadException ex) {
        return handleException(ex, RespCode.BUS_ERROR, "文件上传失败，" + ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public RestResponse<String> runtimeExceptionHandler(RuntimeException ex) {
        return catchSalesException(ex);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public RestResponse<String> exceptionHandler(Exception ex) {
        return catchSalesException(ex);
    }

    private RestResponse<String> handleException(Exception ex, int code, String msg) {
        log.error(ex.getMessage(), ex);

        return RestResponse.getInstance(code,msg, StringUtils.EMPTY);
    }

    private RestResponse<String> catchSalesException(Exception ex) {
        if (ex instanceof SaleException) {
            return handleException(ex, RespCode.BUS_ERROR, ex.getMessage());
        } else if (ex instanceof SalesException){
            return handleException(ex, RespCode.BUS_ERROR, ex.getMessage());
        } else if (ex instanceof ServiceException){
            return handleException(ex, RespCode.BUS_ERROR, ex.getMessage());
        }
        return handleException(ex, RespCode.FAILED, "服务器内部发生错误!");
    }

}
