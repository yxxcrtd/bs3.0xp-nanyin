/*
 * 文件名：MstProjectCatInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：mrbbp@qq.com
 * 修改时间：2020-06-30T15:13:50.730
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.projectcatinfo.service;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.dao.FinProductValuationMapper;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.InvestInterfCommDto;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import org.springframework.beans.factory.annotation.Autowired;
import com.joyintech.framework.base.BaseService;
import org.springframework.stereotype.Service;
import com.joyintech.service.basicdata.projectcatinfo.dto.MstProjectCatInfoDto;
import com.joyintech.service.basicdata.projectcatinfo.dao.MstProjectCatInfoMapper;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.constant.GlobalConstant;

import java.util.Date;
import java.util.Map;
import java.util.List;
import com.joyintech.framework.grid.Page;

/**
 * 项目类资产估值行情信息
 * 
 * @author mrbbp@qq.com
 * @version 1.0 2020-06-30T15:13:50.730
 * @see MstProjectCatInfoService
 * @since 1.0
 */
@Service
public class MstProjectCatInfoService extends BaseService<MstProjectCatInfoDto> {

    @Autowired
    private MstProjectCatInfoMapper mstProjectCatInfoMapper;

    /**
     * 平台日组件
     */
    @Autowired
    private IPlatformDate iPlatformDate;

    /**
     * 投管接口共通组件
     */
    @Autowired
    private InvestInterfCommService investInterfCommService;

    /**
     * 场外金融产品估值Mapper
     */
    @Autowired
    private FinProductValuationMapper finProductValuationMapper;

    /**
     * 查询详细信息
     * 
     * @param param 查询条件
     * @return 详细信息
     * @throws Exception 异常信息
     */
    public MstProjectCatInfoDto selectInfo(Map<String, Object> param) {
        return mstProjectCatInfoMapper.selectInfo(param);
    }

    /**
     * 插入数据
     * 
     * @param dto 数据
     * @return 结果
     */
    public int insert(MstProjectCatInfoDto entity) {

        Date sysDate= iPlatformDate!=null ? iPlatformDate.getSysDate(): DateUtil.now();
        entity.setPublishDate(sysDate);
        entity.setInputType(BussinessDictCodeList.录入方式_手工录入.getValue());
        entity.setFinprodType(BussinessDictCodeList.金融产品类型_项目类资产.getValue());
        entity.setFinprodType2(DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());

        //投管接口调用
        InvestInterfCommDto investInterfCommDto = new InvestInterfCommDto();
        investInterfCommDto.setPageId("MstNetAssetQuotationInfo002");
        investInterfCommDto.setPortfolioId(entity.getFinProdMarketId());
        investInterfCommDto.setFinProductValuation(entity);
        investInterfCommService.doInvestInterf(investInterfCommDto);

        return mstProjectCatInfoMapper.insertSelective(entity);
    }

    /**
     * 更新数据
     * 
     * @param dto 数据
     * @return 结果
     */
    public int update(MstProjectCatInfoDto dto) {
        Date sysDate= iPlatformDate!=null ? iPlatformDate.getSysDate(): DateUtil.now();
        dto.setPublishDate(sysDate);
        dto.setInputType(BussinessDictCodeList.录入方式_手工录入.getValue());
        dto.setFinprodType(BussinessDictCodeList.金融产品类型_项目类资产.getValue());
        dto.setFinprodType2(DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        return mstProjectCatInfoMapper.updateByPrimaryKeySelective(dto);
    }

    /**
     * 删除数据
     * 
     * @param dto 数据
     * @return 结果
     */
    public int delete(MstProjectCatInfoDto dto) {
        Date sysDate= iPlatformDate!=null ? iPlatformDate.getSysDate(): DateUtil.now();
        dto.setPublishDate(sysDate);
        dto.setInputType(BussinessDictCodeList.录入方式_手工录入.getValue());
        dto.setFinprodType(BussinessDictCodeList.金融产品类型_项目类资产.getValue());
        dto.setFinprodType2(DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        return mstProjectCatInfoMapper.deleteByPrimaryKey(dto);
    }

    /**
     * 分页查询
     * 
     * @param page 页数
     * @param length 页的大小
     * @param pageParam 查询参数
     * @return
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<MstProjectCatInfoDto> selectForPage(int page, int length,
                                                    Map<String, Object> pageParam)
        throws Exception {

//        pageParam.put("PROFITTYPE_VALUE_01", BussinessDictCodeList.收益类型_净值.getValue());
//        pageParam.put("PROFITTYPE_VALUE_03", BussinessDictCodeList.收益类型_货币.getValue());
        pageParam.put("FINPROD_TYPE_TG_F37", DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());

        Page<MstProjectCatInfoDto> pageDto = super.queryForPage(page, length,
            () -> mstProjectCatInfoMapper.selectForPage(pageParam));
        return pageDto;
    }

    /**
     * 查询界面资产代码联想文本框
     * 可以通过输入的资产代码模糊查询
     * @param map 查询参数
     * @return  资产名称和资产代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getAssetCode(Map<String, Object> map) {
        return mstProjectCatInfoMapper.getAssetCode(map);
    }
    
    
    /**
     * 新建界面资产代码联想文本框
     * 可以通过输入的资产代码模糊查询
     * @param map 查询参数
     * @return  资产名称和资产代码列表
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAssetCodeList(Map<String, Object> map) {
        return mstProjectCatInfoMapper.getAssetCodeList(map);
    }
}
