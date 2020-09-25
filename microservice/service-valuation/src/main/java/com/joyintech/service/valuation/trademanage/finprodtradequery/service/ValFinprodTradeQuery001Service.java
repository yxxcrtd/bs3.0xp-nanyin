/*
 * 文件名：ValFinprodTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.trademanage.finprodtradequery.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//import com.jixu.mvc.control.service.impl.TradeServiceImpl;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.trademanage.finprodtradequery.dao.ValFinprodTradeQuery001Mapper;
import com.joyintech.service.valuation.trademanage.finprodtradequery.dto.ValFinprodTradeQuery001Dto;
import com.joyintech.tams.common.base.dao.BokDealAccountingDetailMapper;
import com.joyintech.tams.common.base.dao.BokDealAccountingMapper;
import com.joyintech.tams.common.base.dao.StlOrdersExtMapper;
import com.joyintech.tams.common.base.dao.StlOrdersMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealAddMapper;
//import com.joyintech.tams.common.base.dao.TrdProductDealExtMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetailKey;
import com.joyintech.tams.common.base.entity.BokDealAccountingKey;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 金融产品交易查询Service
 * 
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Service
 * @since 1.0
 */
@Service(value="serviceValFinprodTradeQuery001Service")
public class ValFinprodTradeQuery001Service extends CommonExportService<ValFinprodTradeQuery001Dto> implements BaseProcessService<ValFinprodTradeQuery001Dto> {

    /**
     * ValFinprodTradeQuery001Mapper
     */
    @Autowired
    private ValFinprodTradeQuery001Mapper mapper;

    // 结算指令扩展表Mapper
    @Autowired
    private StlOrdersExtMapper stlOrdersExtMapper;

    // 结算指令表Mapper
    @Autowired
    private StlOrdersMapper stlOrdersMapper;

    // 入账账务明细表Mapper
    @Autowired
    private BokDealAccountingDetailMapper bokDealAccountingDetailMapper;

    // 入账信息表Mapper
    @Autowired
    private BokDealAccountingMapper bokDealAccountingMapper;

    // 金融产品交易扩展表Mapper
    //一体化问题12:表名变更(金融产品交易扩展表(TRD_PRODUCT_DEAL_EXT) -> 金融产品交易附属表(TRD_PRODUCT_DEAL_ADD))
    @Autowired
    private TrdProductDealAddMapper trdProductDealExtMapper;

    // 金融产品交易表Mapper
    @Autowired
    private TrdProductDealMapper trdProductDealMapper;
//    @Autowired
//    private TradeServiceImpl tradeServiceImpl;

    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception
     */
    public Page<ValFinprodTradeQuery001Dto> findPage(int page, int length,Map<String, Object> params)
        throws Exception {
        return super.queryForPage(page, length, () -> this.queryPageList(params));
    }

    public List<ValFinprodTradeQuery001Dto> queryPageList(Map<String, Object> params) {
        // 关联入账信息表的业务表--对应金融产品表;
        params.put("busiTable", DictCodeList.业务表_金融产品表.getValue());
        // 获取业务类型
        String busiType = (String)params.get("busiType");
        // 业务类型多选，转为数组
        String[] busiTypeArr = null;
        if (StringUtil.isNotEmpty(busiType)) {
            busiTypeArr = busiType.split(",");
            // 判断是否包含指令交易
            if (busiType.contains(DictCodeList.业务类型_指令交易.getValue())) {
                params.put("orderBusi", DictCodeList.业务类型_指令交易.getValue());
                params.put("orderTable", DictCodeList.业务表_结算指令表.getValue());
            }
        }
        params.put("busiTypeArr", busiTypeArr);

        // 获取交易方向
        String ps = (String)params.get("ps");
        // 交易方向多选，转为数组
        String[] psArr = null;
        if (StringUtil.isNotEmpty(ps)) {
            psArr = ps.split(",");
        }
        params.put("psArr", psArr);

        String prodId = (String)params.get("prodId");
        String[] prodIdArr = null;
        if (StringUtil.isNotEmpty(prodId)) {
            prodIdArr = prodId.split(",");
        }
        params.put("prodIdArr", prodIdArr);

        return mapper.queryPageList(params);
    }

    /**
     * 删除
     * 
     * @param finprodId
     * @param fundType
     * @return
     * @see
     * @since 1.0
     */
    public int delete(ValFinprodTradeQuery001Dto dto) {
        // 业务类型
        String busiType = dto.getBusiTypeCode();
        // 交易编号
        String tradeId = dto.getTradeId();
        // 账套代码
        String booksetId = dto.getBooksetId();
        // 业务表
        String busiTable = dto.getBusiTable();

        // 当 参数.业务类型 = 19：指令交易时、进行以下操作：
        if (busiType.equals(DictCodeList.业务类型_指令交易.getValue())) {
            /**
             * 删除结算指令扩展表
             */
            // 指令编号-交易编号
            String orderId = dto.getProdId();
            stlOrdersExtMapper.deleteByPrimaryKey(orderId);

            /**
             * 删除入账账务明细表
             */
            BokDealAccountingDetailKey bokDealAccountingDetailKey = new BokDealAccountingDetailKey();
            // 账套代码-账套代码
            bokDealAccountingDetailKey.setBooksetId(booksetId);
            // 业务编号-指令编号
            bokDealAccountingDetailKey.setBusiNo(orderId);
            // 业务表-业务表
            bokDealAccountingDetailKey.setBusiTable(busiTable);
            bokDealAccountingDetailMapper.deleteByPrimaryKey(bokDealAccountingDetailKey);

            /**
             * 删除入账信息表
             */
            BokDealAccountingKey bokDealAccountingKey = new BokDealAccountingKey();
            // 账套代码-账套代码
            bokDealAccountingKey.setBooksetId(booksetId);
            // 业务编号-指令编号
            bokDealAccountingKey.setBusiNo(orderId);
            // 业务表-业务表
            bokDealAccountingKey.setBusiTable(busiTable);
            bokDealAccountingMapper.deleteByPrimaryKey(bokDealAccountingKey);

            /**
             * 删除结算指令表
             */
            // 指令编号-指令编号
            return stlOrdersMapper.deleteByPrimaryKey(orderId);
        }
        else {
            // 定义变量 指令编号列表
            List<String> orderIdList = new ArrayList<>();
            // 定义变量 业务编号列表
            List<String> busiNoList = new ArrayList<>();
            // 定义变量 账务明细代码列表
            List<String> bokDetailIdList = new ArrayList<>();

            Map<String, Object> params = new HashMap<>();
            params.put("tradeId", tradeId);
            // 取得随交易生成的 指令编号
            orderIdList = mapper.getOrderIdList(params);

            Map<String, Object> params2 = new HashMap<>();
            params2.put("booksetId", booksetId);
            params2.put("orderIdArr", orderIdList.toArray(new String[orderIdList.size()]));
            params2.put("busiTable", DictCodeList.业务表_结算指令表.getValue());
            // 取得随交易生成的指令的账务明细代码 列表
            bokDetailIdList = mapper.getBokDetailIdList(params2);

            // 生成删除用账务明细代码列表
            bokDetailIdList.add(dto.getBokDetailId());

            // 生成删除用业务编码列表
            busiNoList.add(tradeId);
            for (String orderId : orderIdList) {
                busiNoList.add(orderId);
            }

            // 进行删除操作
            // 指令编号
            String[] orderIdArr = orderIdList.toArray(new String[orderIdList.size()]);
            // 业务编号
            String[] busiNoArr = busiNoList.toArray(new String[busiNoList.size()]);
            // 账务明细代码
            String[] bokDetailIdArr = bokDetailIdList.toArray(new String[bokDetailIdList.size()]);
            /**
             * 删除交易费用明细表
             */
            Map<String, Object> tgParams = new HashMap<>();
            tgParams.put("busiNoArr", busiNoArr);
            mapper.deleteTgTradeFeeDetail(tgParams);

            /**
             * 删除结算指令扩展表
             */
            Map<String, Object> stleParams = new HashMap<>();
            stleParams.put("orderIdArr", orderIdArr);
            mapper.deleteStlOrdersExt(stleParams);

            /**
             * 删除结算指令表
             */
            mapper.deleteStlOrders(stleParams);

            /**
             * 删除账务明细表
             */
            Map<String, Object> bodParams = new HashMap<>();
            bodParams.put("booksetId", booksetId);
            bodParams.put("busiNoArr", busiNoArr);
            bodParams.put("bokDetailIdArr", bokDetailIdArr);
            mapper.deleteBokDealAccountingDetail(bodParams);

            /**
             * 删除入账信息表
             */
            Map<String, Object> bokParams = new HashMap<>();
            bokParams.put("booksetId", booksetId);
            bokParams.put("busiNoArr", busiNoArr);
            mapper.deleteBokDealAccounting(bokParams);

            /**
             * 删除金融产品交易扩展表
             */

            trdProductDealExtMapper.deleteByPrimaryKey(tradeId);

            /**
             * 删除金融产品交易表
             */
            return trdProductDealMapper.deleteByPrimaryKey(tradeId);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ValFinprodTradeQuery001Dto entity, OperationType type) {
        // 删除操作
        if (OperationType.DELETE.equals(type)) {
            // 业务类型
            String busiType = entity.getBusiTypeCode();
            // 交易编号
            String tradeId = entity.getTradeId();
            // 账套代码
            String booksetId = entity.getBooksetId();
            // 业务表
            String busiTable = entity.getBusiTable();

            // 当 参数.业务类型 = 19：指令交易时、进行以下操作：
            if (busiType.equals(DictCodeList.业务类型_指令交易.getValue())) {
                /**
                 * 删除结算指令扩展表
                 */
                // 指令编号-交易编号
                String orderId = entity.getProdId();
                stlOrdersExtMapper.deleteByPrimaryKey(orderId);

                /**
                 * 删除入账账务明细表
                 */
                BokDealAccountingDetailKey bokDealAccountingDetailKey = new BokDealAccountingDetailKey();
                // 账套代码-账套代码
                bokDealAccountingDetailKey.setBooksetId(booksetId);
                // 业务编号-指令编号
                bokDealAccountingDetailKey.setBusiNo(orderId);
                // 业务表-业务表
                bokDealAccountingDetailKey.setBusiTable(busiTable);
                bokDealAccountingDetailMapper.deleteByPrimaryKey(bokDealAccountingDetailKey);

                /**
                 * 删除入账信息表
                 */
                BokDealAccountingKey bokDealAccountingKey = new BokDealAccountingKey();
                // 账套代码-账套代码
                bokDealAccountingKey.setBooksetId(booksetId);
                // 业务编号-指令编号
                bokDealAccountingKey.setBusiNo(orderId);
                // 业务表-业务表
                bokDealAccountingKey.setBusiTable(busiTable);
                bokDealAccountingMapper.deleteByPrimaryKey(bokDealAccountingKey);

                /**
                 * 删除结算指令表
                 */
                // 指令编号-指令编号
                stlOrdersMapper.deleteByPrimaryKey(orderId);
            }else if (busiType.equals(DictCodeList.业务类型_股票交易.getValue())) { 
//                tradeServiceImpl.deleteTran(tradeId,true);
//            	String e =resultContainer.getErrorMsg();
//            	if(e!=null||e!=""){           		
//            		new AjaxResult(e);
//            		return;
//            	}
            	            	
           }
            else {
                // 定义变量 指令编号列表
                List<String> orderIdList = new ArrayList<>();
                // 定义变量 业务编号列表
                List<String> busiNoList = new ArrayList<>();
                // 定义变量 账务明细代码列表
                List<String> bokDetailIdList = new ArrayList<>();

                Map<String, Object> params = new HashMap<>();
                params.put("tradeId", tradeId);
                // 取得随交易生成的 指令编号
                orderIdList = mapper.getOrderIdList(params);

                Map<String, Object> params2 = new HashMap<>();
                params2.put("booksetId", booksetId);
                params2.put("orderIdArr", orderIdList.toArray(new String[orderIdList.size()]));
                params2.put("busiTable", DictCodeList.业务表_结算指令表.getValue());
                // 取得随交易生成的指令的账务明细代码 列表
                bokDetailIdList = mapper.getBokDetailIdList(params2);

                // 生成删除用账务明细代码列表
                bokDetailIdList.add(entity.getBokDetailId());

                // 生成删除用业务编码列表
                busiNoList.add(tradeId);
                for (String orderId : orderIdList) {
                    busiNoList.add(orderId);
                }

                // 进行删除操作
                // 指令编号
                String[] orderIdArr = orderIdList.toArray(new String[orderIdList.size()]);
                // 业务编号
                String[] busiNoArr = busiNoList.toArray(new String[busiNoList.size()]);
                // 账务明细代码
                String[] bokDetailIdArr = bokDetailIdList.toArray(
                    new String[bokDetailIdList.size()]);
                /**
                 * 删除交易费用明细表
                 */
                Map<String, Object> tgParams = new HashMap<>();
                tgParams.put("busiNoArr", busiNoArr);
                mapper.deleteTgTradeFeeDetail(tgParams);

                /**
                 * 删除结算指令扩展表
                 */
                Map<String, Object> stleParams = new HashMap<>();
                stleParams.put("orderIdArr", orderIdArr);
                mapper.deleteStlOrdersExt(stleParams);

                /**
                 * 删除结算指令表
                 */
                mapper.deleteStlOrders(stleParams);

                /**
                 * 删除账务明细表
                 */
                Map<String, Object> bodParams = new HashMap<>();
                bodParams.put("booksetId", booksetId);
                bodParams.put("busiNoArr", busiNoArr);
                bodParams.put("bokDetailIdArr", bokDetailIdArr);
                mapper.deleteBokDealAccountingDetail(bodParams);

                /**
                 * 删除入账信息表
                 */
                Map<String, Object> bokParams = new HashMap<>();
                bokParams.put("booksetId", booksetId);
                bokParams.put("busiNoArr", busiNoArr);
                mapper.deleteBokDealAccounting(bokParams);

                /**
                 * 删除金融产品交易扩展表
                 */

                trdProductDealExtMapper.deleteByPrimaryKey(tradeId);
                
                /**
                 * 删除金融产品实物交易
                 */
                Map<String, Object> trdParams = new HashMap<>();
                trdParams.put("tradeId", tradeId);
                mapper.deleteTrdFinProductBarter(trdParams);
                

                /**
                 * 删除金融产品交易表
                 */
                trdProductDealMapper.deleteByPrimaryKey(tradeId);
            }
        }

    }
    
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public List<String> getBusinessDataInFlow(ProcessMark processMark) {
        return mapper.getBusinessDataInFlow(processMark);
    }
}
