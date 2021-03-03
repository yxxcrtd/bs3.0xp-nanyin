/*
 * 文件名：BussFinBondRepInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.assetinfodetail.asset;

import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 *  债券中债报送
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月23日
 * @see BussAssetInfoService
 * @since 1.0
 */
public interface BussAssetInfoService{


    /**
     * 工厂模式根据二级资产编号获取类名
     * @param type2
     * @return
     */
    boolean getBussinessCode(String type2);

    /**
     * 默认entity名称获取画面初始化需要的数据字典
     */
    void setModelAttribute(Model model, HttpServletRequest req);

    /**
     * 当前页面所需数据字典
     * @return
     */
    IDictCode[] getInitDictCode();

    /**
     * 赋值方法
     * @param model 界面model实体
     * @param assetUuid  资产编号
     * @param assetType2  资产二级类型
     * @param dataStatus   数据状态(用于区分ERROR数据还是INFO数据)
     */
    void setEntity(Model model,String assetUuid,String assetType2,String dataStatus);


    /**
     * 保存时数据转换方法
     * @param info  界面传入实体类
     * @return
     */
    AssetInfoDetailDto setEntity(AssetInfoDetailDto info);
}
