package cn.gaozheng.mini.config;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信状态报告 短信平台收到短信网关的状态报告，或超过72小时自主构建状态报告消息通过回调地址推送给客户
 */
public class SmsStatusReport {

    public static void main(String[] args) throws Exception {

        // 短信平台上报状态报告数据样例(urlencode)
        // String success_body = "sequence=1&total=1&updateTime=2018-10-31T08%3A43%3A41Z&source=2&smsMsgId=2ea20735-f856-4376-afbf-570bd70a46ee_11840135&status=DELIVRD";
        String failed_body = "sequence=1&total=1&updateTime=2018-10-31T08%3A43%3A41Z&source=2&smsMsgId=2ea20735-f856-4376-afbf-570bd70a46ee_11840135&status=E200027";
        // onSmsStatusReport(success_body);
        onSmsStatusReport(failed_body);
    }

    /**
     * 解析状态报告数据
     *
     * @param data 短信平台上报的状态报告数据
     */
    static void onSmsStatusReport(String data) {

        if (null == data || data.isEmpty()) {
            System.out.println("onSmsStatusReport(): data is null.");
            return;
        }
        Map<String, String> keyValues = new HashMap<String, String>();
        try {
            // 解析状态报告数据
            String[] params = URLDecoder.decode(data, "UTF-8").split("&");
            String[] temp;
            for (int i = 0; i < params.length; i++) {
                temp = params[i].split("=");
                if (temp.length == 2 && null != temp[1] && temp[1] != "") {
                    keyValues.put(temp[0], temp[1]);
                }
            }

            /**
             * Example: 此处已解析status为例,请按需解析所需参数并自行实现相关处理
             *
             * 'smsMsgId': 短信唯一标识
             * 'total': 长短信拆分条数
             * 'sequence': 拆分后短信序号
             * 'source': 状态报告来源
             * 'updateTime': 资源更新时间
             * 'status': 状态码
             */
            String status = keyValues.get("status"); // 状态报告枚举值
            // 通过status判断短信是否发送成功
            if ("DELIVRD".equalsIgnoreCase(status)) {
                System.out.println("Send sms success. smsMsgId: " + keyValues.get("smsMsgId"));
            } else {
                // 发送失败,打印status和orgCode
                System.out.println("Send sms failed. smsMsgId: " + keyValues.get("smsMsgId"));
                System.out.println("Failed status: " + status);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
