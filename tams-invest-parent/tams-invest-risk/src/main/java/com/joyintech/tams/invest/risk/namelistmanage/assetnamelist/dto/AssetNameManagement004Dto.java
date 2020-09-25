/*
 * 文件名：AssetNameManagement004Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2019年1月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto;

import java.util.List;

import com.joyintech.framework.base.BaseEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhuangzhuang
 * @version 1.0 2019年1月15日
 * @see AssetNameManagement004Dto
 * @since 1.0
 */
public class AssetNameManagement004Dto extends BaseEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3807988315287355853L;
    /**
     * 导入数据
     */
    private List<RskAccessListDetailDto>  list;
    /**
     * 新增条数
     */
    private int addNum;
    /**
     * 修改条数
     */
    private int editNum;
    /**
     * 删除条数
     */
    private int deleteNum;
    
    /**
     * 获取 list
     * @return list.
     */
    public List<RskAccessListDetailDto> getList() {
        return list;
    }
    
    /**
     * 设置 list
     * @param list list
     */
    public void setList(List<RskAccessListDetailDto> list) {
        this.list = list;
    }
    
    /**
     * 获取 addNum
     * @return addNum.
     */
    public int getAddNum() {
        return addNum;
    }
    
    /**
     * 设置 addNum
     * @param addNum addNum
     */
    public void setAddNum(int addNum) {
        this.addNum = addNum;
    }
    
    /**
     * 获取 editNum
     * @return editNum.
     */
    public int getEditNum() {
        return editNum;
    }
    
    /**
     * 设置 editNum
     * @param editNum editNum
     */
    public void setEditNum(int editNum) {
        this.editNum = editNum;
    }
    
    /**
     * 获取 deleteNum
     * @return deleteNum.
     */
    public int getDeleteNum() {
        return deleteNum;
    }
    
    /**
     * 设置 deleteNum
     * @param deleteNum deleteNum
     */
    public void setDeleteNum(int deleteNum) {
        this.deleteNum = deleteNum;
    }
    
}
