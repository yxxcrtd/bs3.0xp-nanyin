package com.joyintech.service.bankuplink.common.dto;

import java.util.List;

/**
 * 跨托管机构实体
 * @auther zhangnian
 * @date 2021/3/24
 */
public class NoRelatedPartydDetail {

    List<NoRelatedPartydDetailSubID> noRelatedPartydDetailSubIDs;

    public List<NoRelatedPartydDetailSubID> getNoRelatedPartydDetailSubIDs() {
        return noRelatedPartydDetailSubIDs;
    }

    public void setNoRelatedPartydDetailSubIDs(List<NoRelatedPartydDetailSubID> noRelatedPartydDetailSubIDs) {
        this.noRelatedPartydDetailSubIDs = noRelatedPartydDetailSubIDs;
    }
}

