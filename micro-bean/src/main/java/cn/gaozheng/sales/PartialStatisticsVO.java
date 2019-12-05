package cn.gaozheng.sales;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author Cheng Bo
 * @version 1.0
 */
@Getter
@Setter
public class PartialStatisticsVO {

    @ApiModelProperty("客户ID")
    private Integer custCorpId;
    @ApiModelProperty("开票吨数")
    private Double pickupWeight;
    @ApiModelProperty("实际拉货重量")
    private Double netWeight;
    private Integer handleStatus;
    private Integer billLadingStatus;
    private Integer sign = NumberUtils.INTEGER_ZERO;
    private Integer truckTotal = NumberUtils.INTEGER_ZERO;
    private Double pickupWeightTotal = NumberUtils.DOUBLE_ZERO;
    private Double netWeightTotal = NumberUtils.DOUBLE_ZERO;
    @ApiModelProperty("结算单打印")
    private Integer settlementPrintCount;
    @ApiModelProperty("出库单打印")
    private Integer onceWeightPrintCount;
    @ApiModelProperty("出厂单打印")
    private Integer secondWeightPrintCount;
    @ApiModelProperty("下料单补打")
    private Integer replenishPrintCount;
}
