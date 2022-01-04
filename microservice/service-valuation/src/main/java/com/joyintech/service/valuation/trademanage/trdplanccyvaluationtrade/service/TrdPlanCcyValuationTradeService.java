/*
 * 文件名：TaxBalanceQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：leipeng
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.trademanage.trdplanccyvaluationtrade.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.trademanage.trdplanccyvaluationtrade.dao.TrdPlanCcyValuationTradeMapper;
import com.joyintech.service.valuation.trademanage.trdplanccyvaluationtrade.dto.TrdPlanCcyValuationTradeDto;
import com.joyintech.service.valuation.trademanage.trdplannetvaluationtrade.dto.TrdPlanNetValuationTradeDto;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;


/**
 * 简易计税余额查询
 * @author leipeng
 * @version 1.0 2018年10月9日
 * @see TrdPlanCcyValuationTradeService
 * @since 1.0
 */
@Service(value="serviceTrdPlanCcyValuationTradeService")
public class TrdPlanCcyValuationTradeService extends CommonExportService<TrdPlanCcyValuationTradeDto>{

    @Autowired
    private TrdPlanCcyValuationTradeMapper trdPlanCcyValuationTradeMapper;

    /**
     * 平台日获取接口
     */
    @Autowired(required = false)
    private IPlatformDate iPlatformDate;


    /**
     * 产品代码下拉框
     * @auther: Administrator
     * @date: 2021/12/29 16:47
     * @param:
     * @return:
     */
    public Page<SelectOptionDto> findPortfolioId(int page, int length,Map<String, Object> params) {
        return this.queryForPageForSelectReturn(page, length, ()->trdPlanCcyValuationTradeMapper.findPortfolioId(params));
    }

    /**
     * 资产代码下拉框
     * @auther: Administrator
     * @date: 2021/12/29 16:47
     * @param:
     * @return:
     */
    public Page<SelectOptionDto> findFinprodId(int page, int length,Map<String, Object> params) {
        return this.queryForPageForSelectReturn(page, length, ()->trdPlanCcyValuationTradeMapper.findFinprodId(params));
    }


    /**
     *
     * 工作流完结之后的操作
     * <功能详细描述>
     * @param entity 实体
     * @see
     * @since 1.0
     */
    public void doComplete(TrdPlanCcyValuationTradeDto entity) {
//        if(StringUtil.equals(entity.getOperateType(), "DELETE")) {
//            trdPlanCcyValuationTradeMapper.deleteByPrimaryKey(entity);
//        }else if(StringUtil.equals(entity.getOperateType(), "NEW")){
//            trdPlanCcyValuationTradeMapper.insert(entity);
//        }else{
//            trdPlanCcyValuationTradeMapper.updateByPrimaryKeySelective(entity);
//        }
    }

    /**
     *
     * 获取分页查询结果（sharesId  dealDateString）
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<MstSharePriceDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<TrdPlanCcyValuationTradeDto> selectForPage(int page, int length, Map<String, Object> pageParam) throws Exception {
//        return super.queryForPage(page,length,()-> trdPlanCcyValuationTradeMapper.select(pageParam));
        Page<TrdPlanCcyValuationTradeDto> pagelist = new Page<>();
        List<TrdPlanCcyValuationTradeDto> planList = new ArrayList<>();
        pagelist.setRoot(planList);
        return pagelist;
    }


    /**
     * 策略代码生成
     *
     * @return
     * @see
     * @since 1.0
     */
    public String createPlanTradeId() {
        String tradeId = null;
        while (true) {
            // 获取自增数
            String nextId = this.getNextId("PLANTRADEID_SEQUENCE");
            //平台日
            String dateStr= DateUtil.format(iPlatformDate.getSysDate(),DateUtil.DEFAULT_OTHER_FORMAT);//yyyyMMdd
            // 拼接
            tradeId = "JY"+"PLAN"+dateStr+ nextId;
            // 查询是否重复
            int num = trdPlanCcyValuationTradeMapper.selectCountByTradeId(tradeId);
            // 如果自增的策略代码不存在，则跳出循环
            if (num==0) {
                break;
            }
        }
        return tradeId;
    }

    /**
     * 获取自增序列值
     *
     * @param sequenceName 序列名
     * @return
     */
    public String getNextId(String sequenceName) {
        return trdPlanCcyValuationTradeMapper.getNextId(sequenceName);
    }




}
