package cn.gaozheng.sales.exception;


public class SaleException extends RuntimeException {
    public SaleException(String message) {
        super(message);
    }

    public SaleException(String message, Throwable cause) {
        super(message, cause);
    }
}
