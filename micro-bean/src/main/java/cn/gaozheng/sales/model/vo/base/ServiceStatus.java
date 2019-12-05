package cn.gaozheng.sales.model.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel
public class ServiceStatus<T> implements Serializable {

    @ApiModelProperty(value = "状态")
    private Status status;
    @ApiModelProperty(value = "信息")
    private String msg;
    @ApiModelProperty(value = "数据")
    private T data;
    @ApiModelProperty(value = "时间")
    private Date serverTime = new Date();

    private Long id = null;

    public ServiceStatus(){};

    public ServiceStatus(Status status) {
        this.status = status;
    }

    public ServiceStatus(Status status, T data) {
        this.status = status;
        if (status== Status.Fail){
            if (data instanceof String){
                this.msg = (String) data;
            }
        }
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ServiceStatus(Status status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ServiceStatus(Status status, String msg, int errorCode) {
        this.status = status;
        this.msg = msg;
    }

    public ServiceStatus(Status status, String msg, Long id) {
        this.status = status;
        this.msg = msg;
        this.id = id;
    }

    public Date getServerTime() {
        return serverTime;
    }

    public void setServerTime(Date serverTime) {
        this.serverTime = serverTime;
    }


    public enum Status {

        Success(0), Fail(1);

        private int status;

        Status(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

        public String toString() {
            return new Integer(this.status).toString();
        }
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
