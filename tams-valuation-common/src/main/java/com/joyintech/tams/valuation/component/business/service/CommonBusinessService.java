package com.joyintech.tams.valuation.component.business.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.component.business.dao.CommonBusinessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName CommonBusinessService
 * @Description: 组件共通服务
 * @Author yanya
 * @Date 2021/3/4 0004 
 * @Version V1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class CommonBusinessService {

    @Autowired(required = false)
    private CommonBusinessMapper commonBusinessMapper;

    /**
     * FinProductMapper
     */
    @Autowired
    private FinProductMapper finProductMapper;
    /**
     * 获取最大交易日期
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public Date getMaxTradeDate(String finprodId) {
        return commonBusinessMapper.getMaxTradeDate(finprodId);
    }

    /**
     * 根据产品代码查询产品信息
     * @param finprodId
     * @return
     */
    public FinProduct getFinProductById(String finprodId) {
        return finProductMapper.selectByPrimaryKey(finprodId);
    }

    /**
     * 资产是否存在交易
     *
     * @param finprodId 金融产品代码
     * @return 交易笔数
     * @see
     * @since 1.0
     */
    public int isTransInAsset(String finprodId) {
      return commonBusinessMapper.isTransInAsset(finprodId);
    }

    /**
     * 现金流代码生成
     *
     * @param busiNo 业务代码
     * @param busiType 业务类型
     * @param branch 分支序号
     * @param busiType2 现金流二级类型
     * @return 现金流代码
     */
    public String generateCashFlowCode(String busiNo, String busiType, long branch, String busiType2) {
        return InterfHelper.getMd5CashId_(busiNo, busiType, busiType2);
        //return CommonUtil.generateCashFlowCode(busiNo, busiType, branch, busiType2);
    }

    /**
     * 获取金融产品代码
     *
     * @param finprodMarketId 市场代码
     * @param finprodType2 金融产品类型（投管分类）
     * @param chlFinprodId 通道代码
     * @return 金融产品代码
     * @see
     * @since 1.0
     */
    public String getFinProdId(String finprodMarketId, String finprodType, String chlFinprodId) {
        /** modify by yanya on 20210316 金融产品代码生成前面不拼接资产类型**/
//        if (StringUtil.isEmpty(chlFinprodId)) {
//            return StringUtil.defaultString(finprodType).concat(GlobalConstant.UNDER_LINE).concat(
//                    StringUtil.defaultString(finprodMarketId));
//        }
//        return StringUtil.defaultString(finprodType).concat(GlobalConstant.UNDER_LINE).concat(
//                StringUtil.defaultString(finprodMarketId)).concat(GlobalConstant.UNDER_LINE).concat(
//                StringUtil.defaultString(chlFinprodId));
        if (StringUtil.isEmpty(chlFinprodId)) {
            return StringUtil.defaultString(finprodMarketId);
        }
        return StringUtil.defaultString(finprodMarketId).concat(GlobalConstant.UNDER_LINE).concat(StringUtil.defaultString(chlFinprodId));
    }
}
