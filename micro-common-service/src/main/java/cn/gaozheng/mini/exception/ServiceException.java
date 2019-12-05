package cn.gaozheng.mini.exception;

/**
 * 业务处理异常
 *
 * @aut r Cheng Bo
 * @version 1.0
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }
}
