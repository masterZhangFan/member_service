package cn.gaozheng.sales.model.po;

import javax.persistence.*;

@Table(name = "user_commission_set")
public class UserCommissionSet {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户uid
     */
    @Column(name = "`uid`")
    private String uid;

    /**
     * 账号类型（alipay/wepay/unionpay）
     */
    @Column(name = "`type`")
    private String type;

    /**
     * 支付宝账号
     */
    @Column(name = "`alipay_account`")
    private String alipayAccount;

    /**
     * 微信账号
     */
    @Column(name = "`wepay_account`")
    private String wepayAccount;

    /**
     * 开户行
     */
    @Column(name = "`unionpay_bank`")
    private String unionpayBank;

    /**
     * 银联卡号
     */
    @Column(name = "`unionpay_account`")
    private String unionpayAccount;

    /**
     * 支付宝实名
     */
    @Column(name = "`alipay_realname`")
    private String alipayRealname;

    /**
     * 微信实名
     */
    @Column(name = "`wepay_realname`")
    private String wepayRealname;

    /**
     * 银联卡户名
     */
    @Column(name = "`unionpay_realname`")
    private String unionpayRealname;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户uid
     *
     * @return uid - 用户uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * 设置用户uid
     *
     * @param uid 用户uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 获取账号类型（alipay/wepay/unionpay）
     *
     * @return type - 账号类型（alipay/wepay/unionpay）
     */
    public String getType() {
        return type;
    }

    /**
     * 设置账号类型（alipay/wepay/unionpay）
     *
     * @param type 账号类型（alipay/wepay/unionpay）
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取支付宝账号
     *
     * @return alipay_account - 支付宝账号
     */
    public String getAlipayAccount() {
        return alipayAccount;
    }

    /**
     * 设置支付宝账号
     *
     * @param alipayAccount 支付宝账号
     */
    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    /**
     * 获取微信账号
     *
     * @return wepay_account - 微信账号
     */
    public String getWepayAccount() {
        return wepayAccount;
    }

    /**
     * 设置微信账号
     *
     * @param wepayAccount 微信账号
     */
    public void setWepayAccount(String wepayAccount) {
        this.wepayAccount = wepayAccount;
    }

    /**
     * 获取开户行
     *
     * @return unionpay_bank - 开户行
     */
    public String getUnionpayBank() {
        return unionpayBank;
    }

    /**
     * 设置开户行
     *
     * @param unionpayBank 开户行
     */
    public void setUnionpayBank(String unionpayBank) {
        this.unionpayBank = unionpayBank;
    }

    /**
     * 获取银联卡号
     *
     * @return unionpay_account - 银联卡号
     */
    public String getUnionpayAccount() {
        return unionpayAccount;
    }

    /**
     * 设置银联卡号
     *
     * @param unionpayAccount 银联卡号
     */
    public void setUnionpayAccount(String unionpayAccount) {
        this.unionpayAccount = unionpayAccount;
    }

    /**
     * 获取支付宝实名
     *
     * @return alipay_realname - 支付宝实名
     */
    public String getAlipayRealname() {
        return alipayRealname;
    }

    /**
     * 设置支付宝实名
     *
     * @param alipayRealname 支付宝实名
     */
    public void setAlipayRealname(String alipayRealname) {
        this.alipayRealname = alipayRealname;
    }

    /**
     * 获取微信实名
     *
     * @return wepay_realname - 微信实名
     */
    public String getWepayRealname() {
        return wepayRealname;
    }

    /**
     * 设置微信实名
     *
     * @param wepayRealname 微信实名
     */
    public void setWepayRealname(String wepayRealname) {
        this.wepayRealname = wepayRealname;
    }

    /**
     * 获取银联卡户名
     *
     * @return unionpay_realname - 银联卡户名
     */
    public String getUnionpayRealname() {
        return unionpayRealname;
    }

    /**
     * 设置银联卡户名
     *
     * @param unionpayRealname 银联卡户名
     */
    public void setUnionpayRealname(String unionpayRealname) {
        this.unionpayRealname = unionpayRealname;
    }
}