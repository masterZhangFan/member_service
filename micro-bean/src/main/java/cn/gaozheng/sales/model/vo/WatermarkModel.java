package cn.gaozheng.sales.model.vo;

import lombok.Data;

@Data
public class WatermarkModel {
    private String imageUrl;
    private String text;
    private double x;
    private double y;
    private double w;
    private double h;
}
