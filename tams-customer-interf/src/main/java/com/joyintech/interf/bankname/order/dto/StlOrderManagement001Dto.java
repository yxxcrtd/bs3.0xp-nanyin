package com.joyintech.interf.bankname.order.dto;

import java.util.List;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2019-01-03 10:56
 **/
public class StlOrderManagement001Dto extends StlOrderManagementDto {
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 选中列表
     */
    private List<StlOrderManagement001Dto> selectedItem;

    // 数据字典.平安业务类型
    public String getPaBizTypeString() {
        return SysDataUtil.getNameFromDataDict(DictCode.平安业务类型, getPaBizType());
    }

    // 数据字典.划款类型
    public String getPayTypeString() {
        // 一体化对应etliu start
//        return SysDataUtil.getNameFromDataDict(DictCode.划款类型, getPayType());
        return SysDataUtil.getNameFromDataDict(DictCode.平安划款类型, getPayType());
        // 一体化对应etliu end
    }

    // 数据字典.成功失败
    public String getSendStatusString() {
        return SysDataUtil.getNameFromDataDict(DictCode.成功失败标志, getSendStatus());
    }

    // 数据字典.成功失败
    public String getReceiveStatusString() {
        return SysDataUtil.getNameFromDataDict(DictCode.成功失败标志, getOrderRecStatus());
    }

    // 数据字典.指令处理状态
    public String getDealStatusString() {
        return SysDataUtil.getNameFromDataDict(DictCode.指令处理状态, getOrderRetStatus());
    }

    // 数据字典.指令处理状态
    public String getCcyString() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, getCcy());
    }


    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<StlOrderManagement001Dto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<StlOrderManagement001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }
}
