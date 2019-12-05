package cn.gaozheng.sales.model.po;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "tbl_charge_config")
public class TblChargeConfig {
    @Column(name = "`appid`")
    private String appid;

    @Column(name = "`mch_id`")
    private String mchId;

    @Column(name = "`secret`")
    private String secret;

    @Column(name = "`apiKey`")
    private String apikey;

    @Column(name = "`notify_url`")
    private String notifyUrl;

    /**
     * @return appid
     */
    public String getAppid() {
        return appid;
    }

    /**
     * @param appid
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * @return mch_id
     */
    public String getMchId() {
        return mchId;
    }

    /**
     * @param mchId
     */
    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    /**
     * @return secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     * @param secret
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * @return apiKey
     */
    public String getApikey() {
        return apikey;
    }

    /**
     * @param apikey
     */
    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    /**
     * @return notify_url
     */
    public String getNotifyUrl() {
        return notifyUrl;
    }

    /**
     * @param notifyUrl
     */
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}