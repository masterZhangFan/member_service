package cn.gaozheng.sales.model.po;

import javax.persistence.*;

@Table(name = "user_withdraw_set")
public class UserWithdrawSet {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`min_price`")
    private Integer minPrice;

    @Column(name = "`max_price`")
    private Integer maxPrice;

    @Column(name = "`start_time`")
    private Integer startTime;

    @Column(name = "`end_time`")
    private Integer endTime;

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
     * @return min_price
     */
    public Integer getMinPrice() {
        return minPrice;
    }

    /**
     * @param minPrice
     */
    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * @return max_price
     */
    public Integer getMaxPrice() {
        return maxPrice;
    }

    /**
     * @param maxPrice
     */
    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * @return start_time
     */
    public Integer getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    /**
     * @return end_time
     */
    public Integer getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }
}