/*
 * 文件名：ImplOccupyRel.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年10月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.redisinit.dto;

import java.util.Map;
import java.util.Set;

import com.joyintech.tams.common.base.entity.RskOccupyInfo;
import com.joyintech.tams.invest.common.riskinfrds.occupy.entity.IOccupyRelParam;
import com.joyintech.tams.invest.common.riskinfrds.occupy.entity.IOcpRelParamItem;

/**
 * 资产信息实现类
 * @author 裴家俊
 * @version 1.0 2019年10月2日
 * @see ImplOccupyRel
 * @since 1.0
 */
public class ImplOccupyRel extends RskOccupyInfo implements IOccupyRelParam {

    /**
     * 预占/实占类型
     */
    private int occupyType;

    @Override
    public String getOccupyCode() {
        return this.getEventId();
    }

    @Override
    public int getOccupyType() {
        return occupyType;
    }

    @Override
    public Map<String, IOcpRelParamItem> getItems() {
        return null;
    }

    public void setOccupyType(int occupyType) {
        this.occupyType = occupyType;
    }

    private Set<String> seqNoList;

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getSeqNoList() {
        return seqNoList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSeqNoList(Set<String> seqNoList) {
        this.seqNoList = seqNoList;
    }

    private Set<String> ocpCodeList;

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getOcpCodeList() {
        return ocpCodeList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOcpCodeList(Set<String> ocpCodeList) {
        this.ocpCodeList = ocpCodeList;
    }
}
