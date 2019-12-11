package cn.gaozheng.sales.model.vo.member;

import cn.gaozheng.sales.model.vo.base.PageRequestModel;
import lombok.Data;

@Data
public class MemberListParm extends PageRequestModel {
    private Integer memberLevel;
    private Long userId;
    private String searchText;
}
