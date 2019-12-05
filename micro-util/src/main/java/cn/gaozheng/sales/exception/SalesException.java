package cn.gaozheng.sales.exception;

public class SalesException extends RuntimeException {
    public SalesException(String message) {
        super(message);
    }

    public SalesException(String message, Throwable cause) {
        super(message, cause);
    }
}
