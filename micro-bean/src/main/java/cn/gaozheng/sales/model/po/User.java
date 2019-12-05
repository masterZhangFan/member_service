package cn.gaozheng.sales.model.po;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class User {
    @Id
    @Column(name = "`user_id`")
    private Long userId;

    @Column(name = "`user_name`")
    private String userName;

    @Column(name = "`reg_pwd`")
    private String regPwd;

    @Column(name = "`long_name`")
    private String longName;

    @Column(name = "`rep_name`")
    private String repName;

    @Column(name = "`switch_phone`")
    private String switchPhone;

    @Column(name = "`user_type`")
    private String userType;

    @Column(name = "`province`")
    private String province;

    @Column(name = "`city`")
    private String city;

    @Column(name = "`mobile`")
    private String mobile;

    @Column(name = "`email`")
    private String email;

    @Column(name = "`phone`")
    private String phone;

    @Column(name = "`address`")
    private String address;

    @Column(name = "`post_code`")
    private String postCode;

    @Column(name = "`field_id`")
    private Integer fieldId;

    @Column(name = "`enable_flag`")
    private String enableFlag;

    @Column(name = "`pwd_question`")
    private String pwdQuestion;

    @Column(name = "`pwd_answer`")
    private String pwdAnswer;

    @Column(name = "`balance_warn_flag`")
    private String balanceWarnFlag;

    @Column(name = "`balance_warn`")
    private Long balanceWarn;

    @Column(name = "`user_desc`")
    private String userDesc;

    @Column(name = "`create_time`")
    private Date createTime;

    @Column(name = "`active_time`")
    private Date activeTime;

    @Column(name = "`valid_date`")
    private Date validDate;

    @Column(name = "`create_time_96`")
    private Date createTime96;

    @Column(name = "`status_96`")
    private Integer status96;

    @Column(name = "`nickname`")
    private String nickname;

    @Column(name = "`icon`")
    private String icon;

    @Column(name = "`level_id`")
    private Integer levelId;

    @Column(name = "`hehuoren_type`")
    private Boolean hehuorenType;

    @Column(name = "`hehuoren_pc`")
    private Long hehuorenPc;

    @Column(name = "`wx_open_id`")
    private String wxOpenId;

    @Column(name = "`is_sub`")
    private Byte isSub;

    @Column(name = "`qrcode_url`")
    private String qrcodeUrl;

    @Column(name = "`regshop`")
    private Byte regshop;

    @Column(name = "`jifen`")
    private Integer jifen;

    @Column(name = "`callable`")
    private Boolean callable;

    @Column(name = "`is_wlk`")
    private Integer isWlk;

    @Column(name = "`wlk_info`")
    private String wlkInfo;

    @Column(name = "`wlk_iccid`")
    private String wlkIccid;

    @Column(name = "`wlk_puk`")
    private String wlkPuk;

    @Column(name = "`wlk_token`")
    private String wlkToken;

    @Column(name = "`wlk_bind`")
    private Integer wlkBind;

    @Column(name = "`wlk_check_idcard`")
    private Integer wlkCheckIdcard;

    @Column(name = "`wlk_weid`")
    private Integer wlkWeid;

    @Column(name = "`wlk_userid`")
    private Integer wlkUserid;

    @Column(name = "`wlk_pass`")
    private String wlkPass;

    @Column(name = "`hehuoren_time`")
    private Date hehuorenTime;

    @Column(name = "`platform`")
    private Integer platform;

    @Column(name = "`token`")
    private String token;

    /**
     * 微信登录openid
     */
    @Column(name = "`openid`")
    private String openid;

    /**
     * 微信登录unionid
     */
    @Column(name = "`unionid`")
    private String unionid;

    /**
     * 用户pid
     */
    @Column(name = "`pid`")
    private String pid;

    @Column(name = "`gg_id`")
    private String ggId;

    /**
     * 支付密码
     */
    @Column(name = "`zf_pwd`")
    private String zfPwd;

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return reg_pwd
     */
    public String getRegPwd() {
        return regPwd;
    }

    /**
     * @param regPwd
     */
    public void setRegPwd(String regPwd) {
        this.regPwd = regPwd;
    }

    /**
     * @return long_name
     */
    public String getLongName() {
        return longName;
    }

    /**
     * @param longName
     */
    public void setLongName(String longName) {
        this.longName = longName;
    }

    /**
     * @return rep_name
     */
    public String getRepName() {
        return repName;
    }

    /**
     * @param repName
     */
    public void setRepName(String repName) {
        this.repName = repName;
    }

    /**
     * @return switch_phone
     */
    public String getSwitchPhone() {
        return switchPhone;
    }

    /**
     * @param switchPhone
     */
    public void setSwitchPhone(String switchPhone) {
        this.switchPhone = switchPhone;
    }

    /**
     * @return user_type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @return province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return post_code
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * @param postCode
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * @return field_id
     */
    public Integer getFieldId() {
        return fieldId;
    }

    /**
     * @param fieldId
     */
    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * @return enable_flag
     */
    public String getEnableFlag() {
        return enableFlag;
    }

    /**
     * @param enableFlag
     */
    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    /**
     * @return pwd_question
     */
    public String getPwdQuestion() {
        return pwdQuestion;
    }

    /**
     * @param pwdQuestion
     */
    public void setPwdQuestion(String pwdQuestion) {
        this.pwdQuestion = pwdQuestion;
    }

    /**
     * @return pwd_answer
     */
    public String getPwdAnswer() {
        return pwdAnswer;
    }

    /**
     * @param pwdAnswer
     */
    public void setPwdAnswer(String pwdAnswer) {
        this.pwdAnswer = pwdAnswer;
    }

    /**
     * @return balance_warn_flag
     */
    public String getBalanceWarnFlag() {
        return balanceWarnFlag;
    }

    /**
     * @param balanceWarnFlag
     */
    public void setBalanceWarnFlag(String balanceWarnFlag) {
        this.balanceWarnFlag = balanceWarnFlag;
    }

    /**
     * @return balance_warn
     */
    public Long getBalanceWarn() {
        return balanceWarn;
    }

    /**
     * @param balanceWarn
     */
    public void setBalanceWarn(Long balanceWarn) {
        this.balanceWarn = balanceWarn;
    }

    /**
     * @return user_desc
     */
    public String getUserDesc() {
        return userDesc;
    }

    /**
     * @param userDesc
     */
    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return active_time
     */
    public Date getActiveTime() {
        return activeTime;
    }

    /**
     * @param activeTime
     */
    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    /**
     * @return valid_date
     */
    public Date getValidDate() {
        return validDate;
    }

    /**
     * @param validDate
     */
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    /**
     * @return create_time_96
     */
    public Date getCreateTime96() {
        return createTime96;
    }

    /**
     * @param createTime96
     */
    public void setCreateTime96(Date createTime96) {
        this.createTime96 = createTime96;
    }

    /**
     * @return status_96
     */
    public Integer getStatus96() {
        return status96;
    }

    /**
     * @param status96
     */
    public void setStatus96(Integer status96) {
        this.status96 = status96;
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return level_id
     */
    public Integer getLevelId() {
        return levelId;
    }

    /**
     * @param levelId
     */
    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    /**
     * @return hehuoren_type
     */
    public Boolean getHehuorenType() {
        return hehuorenType;
    }

    /**
     * @param hehuorenType
     */
    public void setHehuorenType(Boolean hehuorenType) {
        this.hehuorenType = hehuorenType;
    }

    /**
     * @return hehuoren_pc
     */
    public Long getHehuorenPc() {
        return hehuorenPc;
    }

    /**
     * @param hehuorenPc
     */
    public void setHehuorenPc(Long hehuorenPc) {
        this.hehuorenPc = hehuorenPc;
    }

    /**
     * @return wx_open_id
     */
    public String getWxOpenId() {
        return wxOpenId;
    }

    /**
     * @param wxOpenId
     */
    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    /**
     * @return is_sub
     */
    public Byte getIsSub() {
        return isSub;
    }

    /**
     * @param isSub
     */
    public void setIsSub(Byte isSub) {
        this.isSub = isSub;
    }

    /**
     * @return qrcode_url
     */
    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    /**
     * @param qrcodeUrl
     */
    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    /**
     * @return regshop
     */
    public Byte getRegshop() {
        return regshop;
    }

    /**
     * @param regshop
     */
    public void setRegshop(Byte regshop) {
        this.regshop = regshop;
    }

    /**
     * @return jifen
     */
    public Integer getJifen() {
        return jifen;
    }

    /**
     * @param jifen
     */
    public void setJifen(Integer jifen) {
        this.jifen = jifen;
    }

    /**
     * @return callable
     */
    public Boolean getCallable() {
        return callable;
    }

    /**
     * @param callable
     */
    public void setCallable(Boolean callable) {
        this.callable = callable;
    }

    /**
     * @return is_wlk
     */
    public Integer getIsWlk() {
        return isWlk;
    }

    /**
     * @param isWlk
     */
    public void setIsWlk(Integer isWlk) {
        this.isWlk = isWlk;
    }

    /**
     * @return wlk_info
     */
    public String getWlkInfo() {
        return wlkInfo;
    }

    /**
     * @param wlkInfo
     */
    public void setWlkInfo(String wlkInfo) {
        this.wlkInfo = wlkInfo;
    }

    /**
     * @return wlk_iccid
     */
    public String getWlkIccid() {
        return wlkIccid;
    }

    /**
     * @param wlkIccid
     */
    public void setWlkIccid(String wlkIccid) {
        this.wlkIccid = wlkIccid;
    }

    /**
     * @return wlk_puk
     */
    public String getWlkPuk() {
        return wlkPuk;
    }

    /**
     * @param wlkPuk
     */
    public void setWlkPuk(String wlkPuk) {
        this.wlkPuk = wlkPuk;
    }

    /**
     * @return wlk_token
     */
    public String getWlkToken() {
        return wlkToken;
    }

    /**
     * @param wlkToken
     */
    public void setWlkToken(String wlkToken) {
        this.wlkToken = wlkToken;
    }

    /**
     * @return wlk_bind
     */
    public Integer getWlkBind() {
        return wlkBind;
    }

    /**
     * @param wlkBind
     */
    public void setWlkBind(Integer wlkBind) {
        this.wlkBind = wlkBind;
    }

    /**
     * @return wlk_check_idcard
     */
    public Integer getWlkCheckIdcard() {
        return wlkCheckIdcard;
    }

    /**
     * @param wlkCheckIdcard
     */
    public void setWlkCheckIdcard(Integer wlkCheckIdcard) {
        this.wlkCheckIdcard = wlkCheckIdcard;
    }

    /**
     * @return wlk_weid
     */
    public Integer getWlkWeid() {
        return wlkWeid;
    }

    /**
     * @param wlkWeid
     */
    public void setWlkWeid(Integer wlkWeid) {
        this.wlkWeid = wlkWeid;
    }

    /**
     * @return wlk_userid
     */
    public Integer getWlkUserid() {
        return wlkUserid;
    }

    /**
     * @param wlkUserid
     */
    public void setWlkUserid(Integer wlkUserid) {
        this.wlkUserid = wlkUserid;
    }

    /**
     * @return wlk_pass
     */
    public String getWlkPass() {
        return wlkPass;
    }

    /**
     * @param wlkPass
     */
    public void setWlkPass(String wlkPass) {
        this.wlkPass = wlkPass;
    }

    /**
     * @return hehuoren_time
     */
    public Date getHehuorenTime() {
        return hehuorenTime;
    }

    /**
     * @param hehuorenTime
     */
    public void setHehuorenTime(Date hehuorenTime) {
        this.hehuorenTime = hehuorenTime;
    }

    /**
     * @return platform
     */
    public Integer getPlatform() {
        return platform;
    }

    /**
     * @param platform
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    /**
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取微信登录openid
     *
     * @return openid - 微信登录openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置微信登录openid
     *
     * @param openid 微信登录openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取微信登录unionid
     *
     * @return unionid - 微信登录unionid
     */
    public String getUnionid() {
        return unionid;
    }

    /**
     * 设置微信登录unionid
     *
     * @param unionid 微信登录unionid
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    /**
     * 获取用户pid
     *
     * @return pid - 用户pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置用户pid
     *
     * @param pid 用户pid
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * @return gg_id
     */
    public String getGgId() {
        return ggId;
    }

    /**
     * @param ggId
     */
    public void setGgId(String ggId) {
        this.ggId = ggId;
    }

    /**
     * 获取支付密码
     *
     * @return zf_pwd - 支付密码
     */
    public String getZfPwd() {
        return zfPwd;
    }

    /**
     * 设置支付密码
     *
     * @param zfPwd 支付密码
     */
    public void setZfPwd(String zfPwd) {
        this.zfPwd = zfPwd;
    }
}