package cn.gaozheng.sales.service;

public interface SmsSendService {
    void insertSmsInfo(String phoneNumber,String code);

    boolean checkSmsCode(String phoneNumbe,String code);
}
