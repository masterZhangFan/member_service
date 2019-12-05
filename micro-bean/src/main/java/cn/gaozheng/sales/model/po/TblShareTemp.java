package cn.gaozheng.sales.model.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tbl_share_temp")
public class TblShareTemp {
    @Id
    @Column(name = "`share_temp_id`")
    private Long shareTempId;

    @Column(name = "`share_temp_size_width`")
    private Double shareTempSizeWidth;

    @Column(name = "`share_temp_size_height`")
    private Double shareTempSizeHeight;

    @Column(name = "`share_temp_small_pic`")
    private String shareTempSmallPic;

    @Column(name = "`share_temp_big_pic`")
    private String shareTempBigPic;

    @Column(name = "`share_text_point_x`")
    private Integer shareTextPointX;

    @Column(name = "`share_text_point_y`")
    private Integer shareTextPointY;

    @Column(name = "`share_text_size_width`")
    private Double shareTextSizeWidth;

    @Column(name = "`share_text_size_height`")
    private Double shareTextSizeHeight;

    @Column(name = "`share_qrcode_point_x`")
    private Integer shareQrcodePointX;

    @Column(name = "`share_qrcode_point_y`")
    private Integer shareQrcodePointY;

    @Column(name = "`share_qrcode_point_size`")
    private Double shareQrcodePointSize;

}