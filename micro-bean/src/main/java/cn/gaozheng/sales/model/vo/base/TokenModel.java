package cn.gaozheng.sales.model.vo.base;

public class TokenModel {

    /**
     * ID值
     */
    private Long idValue;
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    private String dateFormat;


    public Long getIdValue() {
        return idValue;
    }

    public void setIdValue(Long idValue) {
        this.idValue = idValue;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}
