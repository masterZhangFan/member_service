package cn.gaozheng.sales.model.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Date;

@Table(name = "tbl_sms_code")
public class TblSmsCode {
    @Id
    @Column(name = "`sms_id`")
    private Long smsId;

    @Column(name = "`sms_send_time`")
    private Date smsSendTime;

    @Column(name = "`sms_code`")
    private String smsCode;

    @Column(name = "`phone_number`")
    private String phoneNumber;

    /**
     * @return sms_id
     */
    public Long getSmsId() {
        return smsId;
    }

    /**
     * @param smsId
     */
    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }

    /**
     * @return sms_send_time
     */
    public Date getSmsSendTime() {
        return smsSendTime;
    }

    /**
     * @param smsSendTime
     */
    public void setSmsSendTime(Date smsSendTime) {
        this.smsSendTime = smsSendTime;
    }

    /**
     * @return sms_code
     */
    public String getSmsCode() {
        return smsCode;
    }

    /**
     * @param smsCode
     */
    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    /**
     * @return phone_number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}