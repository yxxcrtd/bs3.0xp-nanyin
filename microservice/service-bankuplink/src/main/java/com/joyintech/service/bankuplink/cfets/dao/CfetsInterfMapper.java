/*
 * 文件名：CfetsInterfMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2020年12月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.bankuplink.cfets.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.background.component.uplink.interbank.business.dto.IbuQuoteBondDetDto;
import com.joyintech.background.component.uplink.interbank.business.dto.IbuQuoteBondDto;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteCrep;
import com.joyintech.system.user.dto.User;
import com.joyintech.tams.common.base.entity.CfetsDatadictConvert;
import com.joyintech.tams.common.base.entity.CfetsStatusDatadictConvert;
import com.joyintech.tams.common.base.entity.FinProduct;

/**
 * Cfets接口机连接Mapper
 * @author etliu
 * @version 1.0 2020年12月23日
 * @see CfetsInterfMapper
 * @since 1.0
 */
@Repository
public interface CfetsInterfMapper {

    /**
     * 查询Cfets数据字典转换表数据
     * @return Cfets数据字典转换表数据
     */
    List<CfetsDatadictConvert> selectCfetsDataDictConvertData();
    
    /**
     * 查询Cfetsstatus数据字典转换表数据
     * @return Cfetsstatus数据字典转换表数据
     */
    List<CfetsStatusDatadictConvert> selectCfetsStatusDataDictConvertData();
    
//    /**
//     * 根据ibu查询user
//     * @return user
//     */
//    List<User> selectUserByIBU(String ibuTrader);
    
    /**
     * 根据机构21位码查询银行间账户组合关系表数据条数
     * @param partyID 机构21位码
     * @return 数据条数
     */
    int selectIbuPortfolioDataCount(String partyID);
    
    /**
     * 根据市场代码列表查询金融产品信息列表数据
     * @param paramMap 参数map
     * @return 金融产品信息列表数据
     */
    List<FinProduct> selectFinProductList(Map<String, Object> paramMap);
    
    /**
     * 根据CFETS报价编号查询回购报价信息
     * @param cfetsQuoteId CFETS报价编号
     * @return 回购报价信息
     */
    IbuQuoteCrep selectIbuQuoteCrepDataByCfetsQuoteId(String cfetsQuoteId);
    
    /**
     * 根据CFETS报价编号查询现券报价信息
     * @param cfetsQuoteId CFETS报价编号
     * @return 现券报价信息
     */
    IbuQuoteBondDto selectIbuQuoteBondDtoDataByCfetsQuoteId(String cfetsQuoteId);
    
    /**
     * 根据CFETS报价编号查询现券报价明细信息
     * @param cfetsQuoteId CFETS报价编号
     * @return 现券报价明细信息
     */
    List<IbuQuoteBondDetDto> selectIbuQuoteBondDetDtoDataByCfetsQuoteId(String cfetsQuoteId);
    
    /**
     * 更新现券请求报价明细表状态
     * @param ibuQuoteBondDto 更新数据
     * @return 更新条数
     */
    int updateBondReqDetDataStatusForJ(IbuQuoteBondDto ibuQuoteBondDto);
}
