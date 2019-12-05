package cn.gaozheng.sales.model.vo.base;

/**
 * Response Code
 *
 * @author Cheng Bo
 * version 1.0
 */
public interface RespCode {

    /**
     * 操作成功
     */
    int SUCCESS = 0;
    /**
     * 业务逻辑异常
     */
    int BUS_ERROR = 1;
    /**
     * 未登陆导致操作失败
     */
    int NOT_SIGNED_IN = 102;
    /**
     * 操作失败
     */
    int FAILED = 103;

    /**
     * 找不到处理器
     */
    int NO_HANDLER = 104;


}
