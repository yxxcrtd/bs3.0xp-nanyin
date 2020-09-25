/*
 * 文件名：FinProductDurDeal001Service.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：etliu 修改时间：2018年12月18日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.durdeal.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.framework.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.PaBranchSaleDetailMapper;
import com.joyintech.tams.common.base.dao.PaTrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.PaTrdProductDealMapper;
import com.joyintech.tams.common.base.dao.PtlTradeMapper;
import com.joyintech.tams.common.base.dao.RepDurationMapper;
import com.joyintech.tams.common.base.dao.StlPayOrdersMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealAddMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.dao.TrdTradeFeeDetailMapper;
import com.joyintech.tams.common.base.entity.PaBranchSaleDetail;
import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.RepDuration;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.settle.dto.BussSettleOrderParameterDto;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.common.trade.service.TradeProductDealCommService;
import com.joyintech.tams.invest.interf.dto.FinProdDto;
import com.joyintech.tams.invest.product.durdeal.dto.FinProductDurDeal001Dto;
import com.joyintech.tams.invest.product.durdeal.dto.FinProductDurDealDbDataDto;
import com.joyintech.tams.invest.product.durdeal.dto.InterfProdInfo001Dto;
import com.joyintech.tams.valuation.common.service.BooksetCommonService;

/**
 * 产品存续期交易查询Service
 *
 * @author etliu
 * @version 1.0 2018年12月18日
 * @see FinProductDurDeal001Service
 * @since 1.0
 */
@Service
public class FinProductDurDeal001Service extends CommonExportService<FinProductDurDeal001Dto> {

    /**
     * 接口组件Service
     */
    @Autowired
    private TradeProductDealCommService tradeProductDealCommService;

    /**
     * BaseGetBusinessId
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    /**
     * BussTrdTransferOrderService
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    
    @Autowired
    private BooksetCommonService booksetCommonService;
    /**
     * 产品代码下拉框
     *
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception Exception
     */
    public List<SelectOptionDto> queryProdIdInfo(Map<String, Object> param)
        throws Exception {
        // 收益型产品 下拉选项过滤净值型产品 lijun add SHBS-518
        // param.put("PROFIT_TYPE", DictCodeList.收益类型_收益型.getValue());
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductDurDeal/queryProdIdInfo", param,
            SelectOptionDto.class);
    }

    /**
     * 检索产品存续期交易信息
     *
     * @param pageParam 查询参数
     * @return 产品存续期交易信息
     * @see
     * @since 1.0
     */
    public Page<FinProductDurDeal001Dto> findPage(Map<String, Object> pageParam) {
        // 多选下拉框值处理
        // 组合代码
        Object portfolioIdObj = pageParam.get("portfolioId");
        if (portfolioIdObj != null) {
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA, (String)portfolioIdObj));
        }
        else {
            pageParam.put("portfolioIdList", new ArrayList<>());
        }
        // 产品代码
        Object prodId = pageParam.get("prodId");
        if (prodId != null) {
            pageParam.put("prodIdList", StringUtil.splitToList(GlobalConstant.COMMA, (String)prodId));
        }
        else {
            pageParam.put("prodIdList", new ArrayList<>());
        } 
       // add by wangyang1 【吉林银行】 增加 产品经理  2019年9月29日 start
        if(StringUtil.isNotEmpty(pageParam.get("prodManager"))){
            pageParam.put("prodManager", StringUtil.splitToList(null, pageParam.get("prodManager").toString()));
        } else {
            pageParam.put("prodManager", null);
        }
        // add by wangyang1 【吉林银行】 增加 产品经理  end 

        pageParam.put("loginUserName", LoginUserUtil.getUserName());

        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductDurDeal/findPage", pageParam,
            FinProductDurDeal001Dto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        Map<String, Object> param = exportParam.getSelectParam();
        String tableId = (String)param.get("tableId");
        if (StringUtil.equals(tableId, "total")) {
            exportParam.config("/com/joyintech/tams/invest/product/durdeal/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
        }
        else {
            exportParam.config("/com/joyintech/tams/invest/product/durdeal/conf/export_conf_detail.xml").dataSource(
                DataSourceType.MASERT);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinProductDurDeal001Dto> selectPageData(Map<String, Object> selectParam) {
        String tableId = (String)selectParam.get("tableId");
        if (StringUtil.equals(tableId, "total")) {
            Page<FinProductDurDeal001Dto> result = findPage(selectParam);
            return result;
        }
        else {
            return findDetailPage(selectParam);
        }

    }

    /**
     * 同步
     *
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    public AjaxResult synchroData() {
        AjaxResult ajaxResult = new AjaxResult();

        // 查询出开放类型为‘开放式’且处理模式为'日添利','智能日添利','货币型','净值型'且到期日大于平台日的产品代码、金融产品代码、销售部门、处理模式
        // 构造产品代码DTO List<FinProdDto>
        List<FinProdDto> finProdList = restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductDurDeal/selectTargetProdData", null, FinProdDto.class);

        if (StringUtil.isEmptyList(finProdList)) {
            ajaxResult.setMessage(getMessage("BUSS_COM_E_0020"));
            return ajaxResult;
        }

        // 2.调用接口
        // TODO 和接口联调时才能打开，现在暂时封上
        // List<FinProdDto> prodList = interfComponentService.sendRequest(
        // "FinProductDurDeal001", "FinProductDurDeal001_btn_synchro", finProdList);
        //
        // if (StringUtil.isEmptyList(prodList)) {
        // ajaxResult.setMessage(getMessage("BUSS_COM_E_0020"));
        // return ajaxResult;
        // }
        // // 输出参数.FinProdDto.isHandleOK(处理结果)为false的给出提示
        // List<FinProdDto> notOkProdList = prodList.stream().filter(
        // x -> !x.isHandleOK()).collect(Collectors.toList());
        // if (!StringUtil.isEmptyList(notOkProdList)) {
        // ajaxResult.setMessage(getMessage("BUSS_COM_I_0052"));
        // return ajaxResult;
        // }

        // 查询产品申赎信息接口表数据
        List<InterfProdInfo001Dto> interfProdInfoDataList = restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductDurDeal/selectInterfProdInfoData", null,
            InterfProdInfo001Dto.class);

        // 查询之前已经同步的交易数据
        List<TrdFinProductDealTotal> synchroedTradeIdList = restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductDurDeal/selectSynchroedTradeData", null,
            TrdFinProductDealTotal.class);

        // 平台日
        String platDate = DateUtil.format(DateUtil.platDateFromCache(), DateUtil.DEFAULT_OTHER_FORMAT);
        // TODO 和接口联调时才能打开，现在暂时封上,联调的时候237行打开，238行删掉
        // for (FinProdDto prodDto : prodList) {
        for (FinProdDto prodDto : finProdList) {
            this.handleOneProdSynchro(prodDto, interfProdInfoDataList, platDate, synchroedTradeIdList);
        }

        // 一体化对应etliu start
        ajaxResult.setMessage(getMessage("BUSS_COM_I_0033", getMessage("FinProductDurDeal001.synchro")));
        // 一体化对应etliu end

        return ajaxResult;
    }

    /**
     * 单只产品的同步处理
     *
     * @param prodDto 产品信息
     * @param interfProdInfoDataList 产品申赎信息接口数据
     * @param platDate 平台日期
     * @param synchroedTradeIdList 之前已经同步的交易数据
     * @see
     * @since 1.0
     */
    private void handleOneProdSynchro(FinProdDto prodDto, List<InterfProdInfo001Dto> interfProdInfoDataList, String platDate,
                                      List<TrdFinProductDealTotal> synchroedTradeIdList) {
        if (StringUtil.isEmptyList(interfProdInfoDataList)) {
            return;
        }

        List<InterfProdInfo001Dto> tempDataList = interfProdInfoDataList.stream().filter(
            x -> StringUtil.equals(prodDto.getProdId(), x.getProdid())).collect(Collectors.toList());
        if (StringUtil.isEmptyList(tempDataList)) {
            return;
        }

        // 已同步数据撤销
        if (!StringUtil.isEmptyList(synchroedTradeIdList)) {
            List<TrdFinProductDealTotal> tempSynchroedTradeIdList = synchroedTradeIdList.stream().filter(
                x -> StringUtil.equals(prodDto.getFinProdId(), x.getFinprodId())).collect(Collectors.toList());
            if (!this.handleSynchroedTrade(tempSynchroedTradeIdList)) {
                return;
            }
        }

        for (InterfProdInfo001Dto interfProdInfoDto : tempDataList) {
            this.handleOneInterfDataSynchro(prodDto, interfProdInfoDto, platDate);
        }
    }

    /**
     * 处理已经同步的数据 check是否做过现金流对账，如做过现金流对账交易，不允许删除业务表，不需要再次同步 如没有，撤销之前交易
     *
     * @param tempSynchroedTradeIdList 已同步交易id
     * @return 是否需要重新同步
     * @see
     * @since 1.0
     */
    private boolean handleSynchroedTrade(List<TrdFinProductDealTotal> tempSynchroedTradeIdList) {
        if (StringUtil.isEmptyList(tempSynchroedTradeIdList)) {
            return false;
        }

        for (TrdFinProductDealTotal trdFinProductDealTotal : tempSynchroedTradeIdList) {
            String synchroedTradeId = trdFinProductDealTotal.getTotleTradeId();
            // 调用基础组合.【金融产品交易组件.查询金融产品交易信息】获取原交易信息
            List<BaseTradeProductDealDto> baseTradeProductDealDtoList = restTemplate.getForList(
                ServiceApplication.TRADE_SERVICE_NAME + "/common/trdcommonbase/selectTradeInfo?tradeId=" + synchroedTradeId,
                BaseTradeProductDealDto.class);

            // 2、调用<金融产品交易共通组件>，更新系统持仓和现金流水
            FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
            if (!StringUtil.isEmptyList(baseTradeProductDealDtoList)) {
                for (BaseTradeProductDealDto baseDto : baseTradeProductDealDtoList) {
                    baseDto.getTrdProductDeal().setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
                    baseDto.getTrdProductDeal().setCancelRemark("重新同步");
                }
                finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_撤销.getValue());
                finProductTradeDto.setNeedSum(true);
                finProductTradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtoList);

                // 调用业务组件.【金融产品交易共通.交易产品接口】, insert
                // 调用业务组件.【金融产品交易共通.交易产品接口】,写入[实际]的组合持仓流水及现金流水
                tradeProductDealCommService.execute(finProductTradeDto);

                // 一体化对应etliu start
                // 所有交易生效后调用的共通方法
                for (BaseTradeProductDealDto baseDto : baseTradeProductDealDtoList) {
                    baseDto.getTrdProductDeal().setpTradeId(baseDto.getTrdProductDeal().getTradeId());
                    baseDto.getTrdProductDeal().setTradeId(baseDto.getTrdProductDeal().getTradeId() + "R");

                    AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                    inputDto.setProdid(baseDto.getTrdProductDeal().getFinprodId());
                    inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
                    inputDto.setTrdProductDeal(baseDto.getTrdProductDeal());
                    AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
                }
                // 一体化对应etliu end
            }

            tradeProductDealCommService.deleteDatas(synchroedTradeId);
        }

        return true;
    }

    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;

    @Autowired
    private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;

    @Autowired
    private TrdProductDealMapper trdProductDealMapper;

    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;

    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;

    @Autowired
    private PtlTradeMapper ptlTradeMapper;

    @Autowired
    private PaBranchSaleDetailMapper paBranchSaleDetailMapper;

    @Autowired
    private TrdTradeFeeDetailMapper trdTradeFeeDetailMapper;

    @Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;

    @Autowired
    private RepDurationMapper repDurationMapper;

    /**
     * 处理产品申赎信息接口表中一条数据的同步
     *
     * @param prodDto 产品信息
     * @param interfProdInfoDto 产品申赎信息接口数据
     * @param platDate 平台日期
     * @see
     * @since 1.0
     */
    private void handleOneInterfDataSynchro(FinProdDto prodDto, InterfProdInfo001Dto interfProdInfoDto, String platDate) {
        List<FinProductDurDealDbDataDto> dbDataDtoList = new ArrayList<FinProductDurDealDbDataDto>();

        // 2.1如果产品申赎信息接口表.总募集〉0
        if (interfProdInfoDto.getTotalbuyamount() != null
            && BigDecimal.ZERO.compareTo(interfProdInfoDto.getTotalbuyamount()) < 0) {
            dbDataDtoList.add(this.gen募集(interfProdInfoDto, platDate));
        }
        // 2.2如果产品申赎信息接口表.总赎回〉0
        if (interfProdInfoDto.getTotaldrawamount() != null
            && BigDecimal.ZERO.compareTo(interfProdInfoDto.getTotaldrawamount()) < 0) {
            dbDataDtoList.add(this.gen赎回(interfProdInfoDto, platDate));
        }
        // 2.4如果产品申赎信息接口表.总已结转收益〉0
        if (interfProdInfoDto.getTotalcarriedinterest() != null
            && BigDecimal.ZERO.compareTo(interfProdInfoDto.getTotalcarriedinterest()) < 0) {
            dbDataDtoList.add(this.gen已结转收益(interfProdInfoDto, platDate));
        }
        // 2.5如果产品申赎信息接口表.红利再投总份额〉0
        if (interfProdInfoDto.getTotalcarriedvol() != null
            && BigDecimal.ZERO.compareTo(interfProdInfoDto.getTotalcarriedvol()) < 0) {
            dbDataDtoList.add(this.gen红利再投(interfProdInfoDto, platDate));
        }
        // 2.6存续期报送信息Entity
        RepDuration repDuration = this.gen存续期报送信息(interfProdInfoDto, platDate);

        // 4.交易数据登表、划款指令登表
        for (FinProductDurDealDbDataDto dbDataDto : dbDataDtoList) {
            if (dbDataDto.getTrdFinProductDealTotal() != null) {
                trdFinProductDealTotalMapper.insertSelective(dbDataDto.getTrdFinProductDealTotal());
            }
            if (dbDataDto.getPaTrdFinProductDealTotal() != null) {
                paTrdFinProductDealTotalMapper.insertSelective(dbDataDto.getPaTrdFinProductDealTotal());
            }
            if (dbDataDto.getTrdProductDeal() != null) {
                trdProductDealMapper.insertSelective(dbDataDto.getTrdProductDeal());
            }
            if (dbDataDto.getTrdProductDealAdd() != null) {
                trdProductDealAddMapper.insertSelective(dbDataDto.getTrdProductDealAdd());
            }
            if (dbDataDto.getPaTrdProductDeal() != null) {
                paTrdProductDealMapper.insertSelective(dbDataDto.getPaTrdProductDeal());
            }
            if (dbDataDto.getPtlTrade() != null) {
                ptlTradeMapper.insertSelective(dbDataDto.getPtlTrade());
            }
            if (dbDataDto.getPaBranchSaleDetail() != null) {
                paBranchSaleDetailMapper.insertSelective(dbDataDto.getPaBranchSaleDetail());
            }
            if (dbDataDto.getTrdTradeFeeDetail() != null) {
                trdTradeFeeDetailMapper.insertSelective(dbDataDto.getTrdTradeFeeDetail());
            }
            StlPayOrdersDto stlPayOrders = null;
            if (dbDataDto.getBussTrdTransferOrderDto() != null) {
                stlPayOrders = new StlPayOrdersDto();
                BeanUtil.copyPropertiesIgnoreNull(stlPayOrders, dbDataDto.getBussTrdTransferOrderDto());
                stlPayOrdersMapper.insertSelective(stlPayOrders);
            }

            // 5.调用<金融产品交易共通组件>，更新系统持仓和现金流水
            FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
            finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
            finProductTradeDto.setNeedSum(true);
            BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
            baseTradeProductDealDto.setTrdProductDeal(dbDataDto.getTrdProductDeal()); // 金融产品交易
            baseTradeProductDealDto.setTrdProductDealAdd(dbDataDto.getTrdProductDealAdd()); // 金融产品附属
            // 划款指令
            if (stlPayOrders != null) {
                baseTradeProductDealDto.setStlPayOrdersList(Arrays.asList(stlPayOrders));
            }
            finProductTradeDto.setBaseTradeProductDealDtos(Arrays.asList(baseTradeProductDealDto));

            // 调用业务组件.【金融产品交易共通.交易产品接口】,写入[实际]的组合持仓流水及现金流水
            tradeProductDealCommService.execute(finProductTradeDto);

            // 一体化对应etliu start
            // 所有交易生效后调用的共通方法
            AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
            inputDto.setProdid(interfProdInfoDto.getFinprodId());
            inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
            inputDto.setTrdProductDeal(dbDataDto.getTrdProductDeal());
            AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
            // 一体化对应etliu end
        }

        if (repDuration != null) {
            repDurationMapper.insertSelective(repDuration);
        }
    }

    /**
     * 2.6存续期报送信息Entity
     *
     * @param interfProdInfoDto 产品申赎信息接口数据
     * @param platDate 平台日期
     * @return 存续期报送信息Entity
     * @see
     * @since 1.0
     */
    private RepDuration gen存续期报送信息(InterfProdInfo001Dto interfProdInfoDto, String platDate) {
        RepDuration repDuration = new RepDuration();
        repDuration.setRepFinprodId(interfProdInfoDto.getFinprodId());

      //  repDuration.setRepIssuePartyName(CacheUtil.getSysParam(BussinessConstant.ISSUE_PARTY_NAME));
        //多级法人改造start add by hxy 20200620
        if(MultilevelDataAuthorityUtil.isEnabled()) {
	        	 //联社模式
	        	 //根据选择的账套组或者账套，查询出它们的所属机构
	        	String booksetOrg=interfProdInfoDto.getFinprodId();
	        	Map<String, Object> orgmap=new HashMap<String, Object>();
	        	orgmap.put("booksetOrg", booksetOrg);
	        	orgmap.put("dataType", "FINPROD04");
	        	String orgName=booksetCommonService.selectAssociationModeOrgName(orgmap);
	        	repDuration.setRepIssuePartyName(orgName);
	        	
        	}else{
        	//银行模式
        	String orgName=booksetCommonService.selectBankModeOrgName();
        	repDuration.setRepIssuePartyName(orgName);
        	
           }
        //多级法人改造end add by hxy 20200620
        
        
        repDuration.setRepIssuePartyId(CacheUtil.getSysParam(BussinessConstant.ISSUE_PARTY_ID));
        repDuration.setRepFinprodRegId(interfProdInfoDto.getProdRegistCode());
        repDuration.setRepBankProdCode(interfProdInfoDto.getProdid());
        repDuration.setRepBusVdate(DateUtil.parse(platDate));
        repDuration.setRepBusMdate(DateUtil.parse(platDate));
        repDuration.setRepCcy(interfProdInfoDto.getCcy());
        // 一体化对应etliu start
        repDuration.setRepDurationRegDate(DateUtil.parse(platDate));
        // 一体化对应etliu end

        // 一体化对应etliu start 存续期需要参照强成西共通，暂时先不登
        // return repDuration;
        return null;
        // 一体化对应etliu end
    }

    /**
     * 2.1如果产品申赎信息接口表.总募集〉0
     *
     * @param interfProdInfoDto interfProdInfoDto
     * @param platDate platDate
     * @return FinProductDurDealDbDataDto
     * @see
     * @since 1.0
     */
    private FinProductDurDealDbDataDto gen募集(InterfProdInfo001Dto interfProdInfoDto, String platDate) {
        FinProductDurDealDbDataDto dbDataDto = new FinProductDurDealDbDataDto();

        // 调用[详细设计-业务编号共通.xlsx]取得业务编码
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_产品申购);
        String totleTradeId = baseGetBusinessId.buildBussinessCode(codeParameter);

        // 金融产品交易汇总表Entity
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(totleTradeId); // 汇总交易编号
        // 一体化对应etliu start
        trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_产品申购.getValue());
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        // 一体化对应etliu end
        trdFinProductDealTotal.setFinprodId(interfProdInfoDto.getFinprodId());
        trdFinProductDealTotal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        trdFinProductDealTotal.setFinprodType(null); // TODO
        trdFinProductDealTotal.setCcy(interfProdInfoDto.getCcy());
        trdFinProductDealTotal.setPs(DictCodeList.交易方向_产品申购.getValue());
        trdFinProductDealTotal.setTradeDate(DateUtil.parse(interfProdInfoDto.getCdate()));
        trdFinProductDealTotal.setSettleDate(DateUtil.parse(interfProdInfoDto.getCdate()));
        trdFinProductDealTotal.setShareAmt(interfProdInfoDto.getTotalbuyamount());
        trdFinProductDealTotal.setPrinAmt(interfProdInfoDto.getTotalbuyamount());
        trdFinProductDealTotal.setTradeAmt(interfProdInfoDto.getTotalbuyamount());
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdFinProductDealTotal.setfTradeId(interfProdInfoDto.getProdid() + platDate);
        trdFinProductDealTotal.setPortfolioId(interfProdInfoDto.getPortfolioId());
        dbDataDto.setTrdFinProductDealTotal(trdFinProductDealTotal);

        // 金融产品交易汇总扩展表Entity
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
        paTrdFinProductDealTotal.setTotleTradeId(totleTradeId);
        paTrdFinProductDealTotal.setPaBussType(DictCodeListExt1.平安业务类型_产品申购.getValue());
        dbDataDto.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);

        // 金融产品交易表Entity
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        trdProductDeal.setTradeId(totleTradeId);
        trdProductDeal.setFinprodId(interfProdInfoDto.getFinprodId());
        trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        trdProductDeal.setFinprodType(null); // TODO
        trdProductDeal.setCcy(interfProdInfoDto.getCcy());
        trdProductDeal.setPs(DictCodeList.交易方向_产品申购.getValue());
        trdProductDeal.setTradeDate(DateUtil.parse(interfProdInfoDto.getCdate()));
        trdProductDeal.setSettleDate(DateUtil.parse(interfProdInfoDto.getCdate()));
        trdProductDeal.setShareAmt(interfProdInfoDto.getTotalbuyamount());
        trdProductDeal.setPrinAmt(interfProdInfoDto.getTotalbuyamount());
        trdProductDeal.setTradeAmt(interfProdInfoDto.getTotalbuyamount());
        trdProductDeal.setTotleTradeId(totleTradeId);
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        // 一体化对应etliu start
        trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        // 一体化对应etliu end
        dbDataDto.setTrdProductDeal(trdProductDeal);

        // 金融产品交易附属表Entity
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        trdProductDealAdd.setTradeId(trdProductDeal.getTradeId()); // 交易编号
        trdProductDealAdd.setPortfolioId(interfProdInfoDto.getPortfolioId()); // 投资组合代码
        // trdProductDealAdd.setProdId(interfProdInfoDto.getProdid()); // 理财产品代码
        trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_接口接入.getValue()); // 生成方式
        dbDataDto.setTrdProductDealAdd(trdProductDealAdd);

        // 金融产品交易扩展表Entity
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        paTrdProductDeal.setTradeId(trdProductDeal.getTradeId());
        paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_产品申购.getValue());
        dbDataDto.setPaTrdProductDeal(paTrdProductDeal);

        // 组合交易Entity
        PtlTrade ptlTrade = new PtlTrade();
        ptlTrade.setPortfolioId(interfProdInfoDto.getPortfolioId()); // 组合代码
        ptlTrade.setBusiNo(trdProductDeal.getTradeId()); // 业务编号
        ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue()); // 业务表
        ptlTrade.setBusiId(trdProductDeal.getFinprodId()); // 业务明细代码
        ptlTrade.setSettleDate(trdProductDeal.getTradeDate());
        dbDataDto.setPtlTrade(ptlTrade);

        // 理财分行销售明细Entity
        if (StringUtil.isNotBlank(interfProdInfoDto.getBranchbuyamount())) {
            PaBranchSaleDetail paBranchSaleDetail = new PaBranchSaleDetail();
            paBranchSaleDetail.setTradeId(trdProductDeal.getTradeId());
            paBranchSaleDetail.setCdate(trdProductDeal.getTradeDate());
            paBranchSaleDetail.setBranch(interfProdInfoDto.getBranchId());
            paBranchSaleDetail.setPrinAmt(new BigDecimal(interfProdInfoDto.getBranchbuyamount()));
            paBranchSaleDetail.setCustomNum(new Long(interfProdInfoDto.getBranchbuynum()));
            dbDataDto.setPaBranchSaleDetail(paBranchSaleDetail);
        }

        // 调用<清算指令共通组件>，返回清算指令结果DTO，构造划款指令Entity
        dbDataDto.setBussTrdTransferOrderDto(this.gen划款指令(interfProdInfoDto, trdProductDeal));

        return dbDataDto;
    }

    /**
     * 调用<清算指令共通组件>，返回清算指令结果DTO，构造划款指令Entity
     *
     * @param interfProdInfoDto interfProdInfoDto
     * @param trdProductDeal trdProductDeal
     * @return 清算指令结果DTO
     * @see
     * @since 1.0
     */
    private BussTrdTransferOrderDto gen划款指令(InterfProdInfo001Dto interfProdInfoDto, TrdProductDeal trdProductDeal) {
        // 调用<清算指令共通组件>，返回清算指令结果DTO，构造划款指令Entity
        BussSettleOrderParameterDto orderParameterDto = new BussSettleOrderParameterDto();
        orderParameterDto.setIsTotalTrade(DictCodeList.是否_是.getValue());
        orderParameterDto.setBankLendingType(null);
        orderParameterDto.setPortfolioId(interfProdInfoDto.getPortfolioId());
        orderParameterDto.setDeal(trdProductDeal);

        BussTrdTransferOrderDto resultOrderDto = bussTrdTransferOrderService.generateOrder(orderParameterDto);
        resultOrderDto.setTradeId(trdProductDeal.getTradeId());
        resultOrderDto.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());
        List<BussTrdTransferOrderDto> tempList = bussTrdTransferOrderService.save(Arrays.asList(resultOrderDto));
        resultOrderDto = tempList.get(0);

        return resultOrderDto;
    }

    /**
     * 2.2如果产品申赎信息接口表.总赎回〉0
     *
     * @param interfProdInfoDto interfProdInfoDto
     * @param platDate platDate
     * @return FinProductDurDealDbDataDto
     * @see
     * @since 1.0
     */
    private FinProductDurDealDbDataDto gen赎回(InterfProdInfo001Dto interfProdInfoDto, String platDate) {
        FinProductDurDealDbDataDto dbDataDto = new FinProductDurDealDbDataDto();

        // 调用[详细设计-业务编号共通.xlsx]取得业务编码
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_产品赎回);
        String totleTradeId = baseGetBusinessId.buildBussinessCode(codeParameter);

        // 金融产品交易汇总表Entity
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(totleTradeId); // 汇总交易编号
        // 一体化对应etliu start
        trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_产品赎回.getValue());
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        // 一体化对应etliu end
        trdFinProductDealTotal.setFinprodId(interfProdInfoDto.getFinprodId());
        trdFinProductDealTotal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        trdFinProductDealTotal.setFinprodType(null); // TODO
        trdFinProductDealTotal.setCcy(interfProdInfoDto.getCcy());

        String ps = DictCodeList.交易方向_产品赎回.getValue();
        if (DateUtil.compareDate(interfProdInfoDto.getCdate(), interfProdInfoDto.getProdMdate()) >= 0) {
            ps = DictCodeList.交易方向_产品到期.getValue();
        }
        trdFinProductDealTotal.setPs(ps);

        trdFinProductDealTotal.setTradeDate(DateUtil.parse(interfProdInfoDto.getCdate()));
        trdFinProductDealTotal.setSettleDate(DateUtil.parse(interfProdInfoDto.getCdate()));
        trdFinProductDealTotal.setShareAmt(interfProdInfoDto.getTotaldrawamount());
        trdFinProductDealTotal.setPrinAmt(interfProdInfoDto.getTotaldrawamount());
        // 一体化对应etliu start
        // trdFinProductDealTotal.setTradeAmt(interfProdInfoDto.getTotaldrawamount());
        trdFinProductDealTotal.setTradeAmt(
            ArithmeticUtil.subtract(interfProdInfoDto.getTotaldrawamount(), interfProdInfoDto.getTotalfeesamount()));
        // 一体化对应etliu end
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdFinProductDealTotal.setfTradeId(interfProdInfoDto.getProdid() + platDate);
        trdFinProductDealTotal.setPortfolioId(interfProdInfoDto.getPortfolioId());
        // 一体化对应etliu start
        trdFinProductDealTotal.setFeeAmtPay(interfProdInfoDto.getTotalfeesamount());
        trdFinProductDealTotal.setFeeAmt(interfProdInfoDto.getTotalfeesamount());
        // 一体化对应etliu end
        dbDataDto.setTrdFinProductDealTotal(trdFinProductDealTotal);

        // 金融产品交易汇总扩展表Entity
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
        paTrdFinProductDealTotal.setTotleTradeId(totleTradeId);
        paTrdFinProductDealTotal.setPaBussType(DictCodeListExt1.平安业务类型_产品赎回.getValue());
        dbDataDto.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);

        // 金融产品交易表Entity
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        trdProductDeal.setTradeId(totleTradeId);
        trdProductDeal.setFinprodId(interfProdInfoDto.getFinprodId());
        trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        trdProductDeal.setFinprodType(null); // TODO
        trdProductDeal.setCcy(interfProdInfoDto.getCcy());
        trdProductDeal.setPs(DictCodeList.交易方向_产品赎回.getValue());
        trdProductDeal.setTradeDate(DateUtil.parse(interfProdInfoDto.getCdate()));
        trdProductDeal.setSettleDate(DateUtil.parse(interfProdInfoDto.getCdate()));
        trdProductDeal.setShareAmt(interfProdInfoDto.getTotaldrawamount());
        trdProductDeal.setPrinAmt(interfProdInfoDto.getTotaldrawamount());
        // 一体化对应etliu start
        // trdProductDeal.setTradeAmt(interfProdInfoDto.getTotaldrawamount());
        trdProductDeal.setTradeAmt(
            ArithmeticUtil.subtract(interfProdInfoDto.getTotaldrawamount(), interfProdInfoDto.getTotalfeesamount()));
        // 一体化对应etliu end
        trdProductDeal.setTotleTradeId(totleTradeId);
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        // 一体化对应etliu start
        trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        trdProductDeal.setFeeAmtPay(interfProdInfoDto.getTotalfeesamount());
        trdProductDeal.setFeeAmt(interfProdInfoDto.getTotalfeesamount());
        // 一体化对应etliu end
        dbDataDto.setTrdProductDeal(trdProductDeal);

        // 金融产品交易附属表Entity
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        trdProductDealAdd.setTradeId(trdProductDeal.getTradeId()); // 交易编号
        trdProductDealAdd.setPortfolioId(interfProdInfoDto.getPortfolioId()); // 投资组合代码
        // trdProductDealAdd.setProdId(interfProdInfoDto.getProdid()); // 理财产品代码
        trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_接口接入.getValue()); // 生成方式
        dbDataDto.setTrdProductDealAdd(trdProductDealAdd);

        // 金融产品交易扩展表Entity
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        paTrdProductDeal.setTradeId(trdProductDeal.getTradeId());
        paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_产品赎回.getValue());
        dbDataDto.setPaTrdProductDeal(paTrdProductDeal);

        // 组合交易Entity
        PtlTrade ptlTrade = new PtlTrade();
        ptlTrade.setPortfolioId(interfProdInfoDto.getPortfolioId()); // 组合代码
        ptlTrade.setBusiNo(trdProductDeal.getTradeId()); // 业务编号
        ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue()); // 业务表
        ptlTrade.setBusiId(trdProductDeal.getFinprodId()); // 业务明细代码
        ptlTrade.setSettleDate(trdProductDeal.getTradeDate());
        dbDataDto.setPtlTrade(ptlTrade);

        // 理财分行销售明细Entity
        if (StringUtil.isNotBlank(interfProdInfoDto.getBranchbuyamount())) {
            PaBranchSaleDetail paBranchSaleDetail = new PaBranchSaleDetail();
            paBranchSaleDetail.setTradeId(trdProductDeal.getTradeId());
            paBranchSaleDetail.setCdate(trdProductDeal.getTradeDate());
            paBranchSaleDetail.setBranch(interfProdInfoDto.getBranchId());
            paBranchSaleDetail.setPrinAmt(new BigDecimal(interfProdInfoDto.getBranchbuyamount()));
            paBranchSaleDetail.setCustomNum(new Long(interfProdInfoDto.getBranchbuynum()));
            dbDataDto.setPaBranchSaleDetail(paBranchSaleDetail);
        }

        // 2.3如果产品申赎信息接口表.赎回手续费〉0
        if (BigDecimal.ZERO.compareTo(interfProdInfoDto.getTotalfeesamount()) < 0) {
            // 调用[详细设计-业务编号共通.xlsx]取得业务编码
            codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_产品赎回);
            String feeTradeId = baseGetBusinessId.buildBussinessCode(codeParameter);

            // 交易费用明细表Entity
            TrdTradeFeeDetail tradeFeeDetail = new TrdTradeFeeDetail();
            tradeFeeDetail.setTradeId(feeTradeId);
            tradeFeeDetail.setFeeType(DictCodeListExt1.一次性费用类型_交易手续费.getValue());
            tradeFeeDetail.setFeeAmt(interfProdInfoDto.getTotalfeesamount());
            dbDataDto.setTrdTradeFeeDetail(tradeFeeDetail);
        }

        // 调用<清算指令共通组件>，返回清算指令结果DTO，构造划款指令Entity
        dbDataDto.setBussTrdTransferOrderDto(this.gen划款指令(interfProdInfoDto, trdProductDeal));

        return dbDataDto;
    }

    /**
     * 2.4如果产品申赎信息接口表.总已结转收益〉0
     *
     * @param interfProdInfoDto interfProdInfoDto
     * @param platDate platDate
     * @return FinProductDurDealDbDataDto
     * @see
     * @since 1.0
     */
    private FinProductDurDealDbDataDto gen已结转收益(InterfProdInfo001Dto interfProdInfoDto, String platDate) {
        FinProductDurDealDbDataDto dbDataDto = new FinProductDurDealDbDataDto();

        // 调用[详细设计-业务编号共通.xlsx]取得业务编码
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_产品赎回);
        String totleTradeId = baseGetBusinessId.buildBussinessCode(codeParameter);

        // 金融产品交易汇总表Entity
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(totleTradeId); // 汇总交易编号
        // 一体化对应etliu start
        trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_产品赎回.getValue());
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        // 一体化对应etliu end
        trdFinProductDealTotal.setFinprodId(interfProdInfoDto.getFinprodId());
        trdFinProductDealTotal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        trdFinProductDealTotal.setFinprodType(null); // TODO
        trdFinProductDealTotal.setCcy(interfProdInfoDto.getCcy());

        // trdFinProductDealTotal.setPs(DictCodeList.交易方向_赎回收益确认.getValue());
        String ps = null;
        // 如果日期>=产品到期日
        if (DateUtil.compareDate(interfProdInfoDto.getCdate(), interfProdInfoDto.getProdMdate()) >= 0) {
            if (StringUtil.equals(DictCodeList.收益类型_收益型.getValue(), interfProdInfoDto.getProfitType())) {
                ps = DictCodeList.交易方向_中间付息.getValue();
            }
            else if (StringUtil.equals(DictCodeList.收益类型_货币型.getValue(), interfProdInfoDto.getProfitType())) {
                ps = DictCodeList.交易方向_产品到期分红.getValue();
            }
            else if (StringUtil.equals(DictCodeList.收益类型_净值型.getValue(), interfProdInfoDto.getProfitType())) {
                ps = DictCodeList.交易方向_产品现金分红.getValue();
            }
        }
        else {
            // 总赎回>0时
            if (interfProdInfoDto.getTotaldrawamount() != null
                && BigDecimal.ZERO.compareTo(interfProdInfoDto.getTotaldrawamount()) < 0) {
                if (StringUtil.equals(DictCodeList.收益类型_收益型.getValue(), interfProdInfoDto.getProfitType())) {
                    ps = DictCodeList.交易方向_赎回收益确认.getValue();
                }
                else if (StringUtil.equals(DictCodeList.收益类型_货币型.getValue(), interfProdInfoDto.getProfitType())) {
                    ps = DictCodeList.交易方向_赎回收益确认.getValue();
                }
                else if (StringUtil.equals(DictCodeList.收益类型_净值型.getValue(), interfProdInfoDto.getProfitType())) {
                    ps = DictCodeList.交易方向_产品现金分红.getValue();
                }
            }
            else {
                if (StringUtil.equals(DictCodeList.收益类型_收益型.getValue(), interfProdInfoDto.getProfitType())) {
                    ps = DictCodeList.交易方向_中间付息.getValue();
                }
                else if (StringUtil.equals(DictCodeList.收益类型_货币型.getValue(), interfProdInfoDto.getProfitType())) {
                    ps = DictCodeList.交易方向_产品现金分红.getValue();
                }
                else if (StringUtil.equals(DictCodeList.收益类型_净值型.getValue(), interfProdInfoDto.getProfitType())) {
                    ps = DictCodeList.交易方向_产品现金分红.getValue();
                }
            }
        }
        trdFinProductDealTotal.setPs(ps);

        trdFinProductDealTotal.setTradeDate(DateUtil.parse(interfProdInfoDto.getCdate()));
        trdFinProductDealTotal.setSettleDate(DateUtil.parse(interfProdInfoDto.getCdate()));
        trdFinProductDealTotal.setShareAmt(interfProdInfoDto.getTotalcarriedinterest());
        trdFinProductDealTotal.setPrinAmt(interfProdInfoDto.getTotalcarriedinterest());
        trdFinProductDealTotal.setTradeAmt(interfProdInfoDto.getTotalcarriedinterest());
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdFinProductDealTotal.setfTradeId(interfProdInfoDto.getProdid() + platDate);
        trdFinProductDealTotal.setPortfolioId(interfProdInfoDto.getPortfolioId());
        dbDataDto.setTrdFinProductDealTotal(trdFinProductDealTotal);

        // 金融产品交易汇总扩展表Entity
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
        paTrdFinProductDealTotal.setTotleTradeId(totleTradeId);
        paTrdFinProductDealTotal.setPaBussType(DictCodeListExt1.平安业务类型_产品中间付息.getValue());
        dbDataDto.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);

        // 金融产品交易表Entity
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        trdProductDeal.setTradeId(totleTradeId);
        trdProductDeal.setFinprodId(interfProdInfoDto.getFinprodId());
        trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        trdProductDeal.setFinprodType(null); // TODO
        trdProductDeal.setCcy(interfProdInfoDto.getCcy());
        // trdProductDeal.setPs(DictCodeList.交易方向_赎回收益确认.getValue());
        trdProductDeal.setPs(ps);

        trdProductDeal.setTradeDate(DateUtil.parse(interfProdInfoDto.getCdate()));
        trdProductDeal.setSettleDate(DateUtil.parse(interfProdInfoDto.getCdate()));
        trdProductDeal.setShareAmt(interfProdInfoDto.getTotalcarriedinterest());
        trdProductDeal.setPrinAmt(interfProdInfoDto.getTotalcarriedinterest());
        trdProductDeal.setTradeAmt(interfProdInfoDto.getTotalcarriedinterest());
        trdProductDeal.setTotleTradeId(totleTradeId);
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        // 一体化对应etliu start
        trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        // 一体化对应etliu end
        dbDataDto.setTrdProductDeal(trdProductDeal);

        // 金融产品交易附属表Entity
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        trdProductDealAdd.setTradeId(trdProductDeal.getTradeId()); // 交易编号
        trdProductDealAdd.setPortfolioId(interfProdInfoDto.getPortfolioId()); // 投资组合代码
        // trdProductDealAdd.setProdId(interfProdInfoDto.getProdid()); // 理财产品代码
        trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_接口接入.getValue()); // 生成方式
        dbDataDto.setTrdProductDealAdd(trdProductDealAdd);

        // 金融产品交易扩展表Entity
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        paTrdProductDeal.setTradeId(trdProductDeal.getTradeId());
        paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_产品中间付息.getValue());
        dbDataDto.setPaTrdProductDeal(paTrdProductDeal);

        // 组合交易Entity
        PtlTrade ptlTrade = new PtlTrade();
        ptlTrade.setPortfolioId(interfProdInfoDto.getPortfolioId()); // 组合代码
        ptlTrade.setBusiNo(trdProductDeal.getTradeId()); // 业务编号
        ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue()); // 业务表
        ptlTrade.setBusiId(trdProductDeal.getFinprodId()); // 业务明细代码
        ptlTrade.setSettleDate(trdProductDeal.getTradeDate());
        dbDataDto.setPtlTrade(ptlTrade);

        // 调用<清算指令共通组件>，返回清算指令结果DTO，构造划款指令Entity
        dbDataDto.setBussTrdTransferOrderDto(this.gen划款指令(interfProdInfoDto, trdProductDeal));

        return dbDataDto;
    }

    /**
     * 2.5如果产品申赎信息接口表.红利再投总份额〉0
     *
     * @param interfProdInfoDto interfProdInfoDto
     * @param platDate platDate
     * @return FinProductDurDealDbDataDto
     * @see
     * @since 1.0
     */
    private FinProductDurDealDbDataDto gen红利再投(InterfProdInfo001Dto interfProdInfoDto, String platDate) {
        FinProductDurDealDbDataDto dbDataDto = new FinProductDurDealDbDataDto();

        // 调用[详细设计-业务编号共通.xlsx]取得业务编码
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_产品申购);
        String totleTradeId = baseGetBusinessId.buildBussinessCode(codeParameter);

        // 金融产品交易汇总表Entity
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(totleTradeId); // 汇总交易编号
        // 一体化对应etliu start
        trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_产品申购.getValue());
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        // 一体化对应etliu end
        trdFinProductDealTotal.setFinprodId(interfProdInfoDto.getFinprodId());
        trdFinProductDealTotal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        trdFinProductDealTotal.setFinprodType(null); // TODO
        trdFinProductDealTotal.setCcy(interfProdInfoDto.getCcy());
        trdFinProductDealTotal.setPs(DictCodeList.交易方向_产品红利转投.getValue());
        trdFinProductDealTotal.setTradeDate(DateUtil.parse(interfProdInfoDto.getCdate()));
        trdFinProductDealTotal.setSettleDate(DateUtil.parse(interfProdInfoDto.getCdate()));
        trdFinProductDealTotal.setShareAmt(interfProdInfoDto.getTotalcarriedvol());
        trdFinProductDealTotal.setPrinAmt(interfProdInfoDto.getTotalcarriedvol());
        trdFinProductDealTotal.setTradeAmt(interfProdInfoDto.getTotalcarriedvol());
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdFinProductDealTotal.setfTradeId(interfProdInfoDto.getProdid() + platDate);
        trdFinProductDealTotal.setPortfolioId(interfProdInfoDto.getPortfolioId());
        dbDataDto.setTrdFinProductDealTotal(trdFinProductDealTotal);

        // 金融产品交易汇总扩展表Entity
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
        paTrdFinProductDealTotal.setTotleTradeId(totleTradeId);
        paTrdFinProductDealTotal.setPaBussType(DictCodeListExt1.平安业务类型_产品申购.getValue());
        dbDataDto.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);

        // 金融产品交易表Entity
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        trdProductDeal.setTradeId(totleTradeId);
        trdProductDeal.setFinprodId(interfProdInfoDto.getFinprodId());
        trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        trdProductDeal.setFinprodType(null); // TODO
        trdProductDeal.setCcy(interfProdInfoDto.getCcy());
        trdProductDeal.setPs(DictCodeList.交易方向_产品红利转投.getValue());
        trdProductDeal.setTradeDate(DateUtil.parse(interfProdInfoDto.getCdate()));
        trdProductDeal.setSettleDate(DateUtil.parse(interfProdInfoDto.getCdate()));
        trdProductDeal.setShareAmt(interfProdInfoDto.getTotalcarriedvol());
        trdProductDeal.setPrinAmt(interfProdInfoDto.getTotalcarriedvol());
        trdProductDeal.setTradeAmt(interfProdInfoDto.getTotalcarriedvol());
        trdProductDeal.setTotleTradeId(totleTradeId);
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        // 一体化对应etliu start
        trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        // 一体化对应etliu end
        dbDataDto.setTrdProductDeal(trdProductDeal);

        // 金融产品交易附属表Entity
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        trdProductDealAdd.setTradeId(trdProductDeal.getTradeId()); // 交易编号
        trdProductDealAdd.setPortfolioId(interfProdInfoDto.getPortfolioId()); // 投资组合代码
        // trdProductDealAdd.setProdId(interfProdInfoDto.getProdid()); // 理财产品代码
        trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_接口接入.getValue()); // 生成方式
        dbDataDto.setTrdProductDealAdd(trdProductDealAdd);

        // 金融产品交易扩展表Entity
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        paTrdProductDeal.setTradeId(trdProductDeal.getTradeId());
        paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_产品申购.getValue());
        dbDataDto.setPaTrdProductDeal(paTrdProductDeal);

        // 组合交易Entity
        PtlTrade ptlTrade = new PtlTrade();
        ptlTrade.setPortfolioId(interfProdInfoDto.getPortfolioId()); // 组合代码
        ptlTrade.setBusiNo(trdProductDeal.getTradeId()); // 业务编号
        ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue()); // 业务表
        ptlTrade.setBusiId(trdProductDeal.getFinprodId()); // 业务明细代码
        ptlTrade.setSettleDate(trdProductDeal.getTradeDate());
        dbDataDto.setPtlTrade(ptlTrade);

        // // 调用<清算指令共通组件>，返回清算指令结果DTO，构造划款指令Entity
        // dbDataDto.setBussTrdTransferOrderDto(this.gen划款指令(interfProdInfoDto, trdProductDeal));

        return dbDataDto;
    }

    public Page<FinProductDurDeal001Dto> findDetailPage(Map<String, Object> pageParam) {
        // 多选下拉框值处理
        // 组合代码
        Object portfolioIdObj = pageParam.get("portfolioId");
        if (portfolioIdObj != null) {
            pageParam.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA, (String)portfolioIdObj));
        }
        else {
            pageParam.put("portfolioIdList", new ArrayList<>());
        }
        // 产品代码
        Object prodId = pageParam.get("prodId");
        if (prodId != null) {
            pageParam.put("prodIdList", StringUtil.splitToList(GlobalConstant.COMMA, (String)prodId));
        }
        else {
            pageParam.put("prodIdList", new ArrayList<>());
        }
        // add by wangyang1 【吉林银行】 增加 产品经理  2019年9月29日 start
        if(StringUtil.isNotEmpty(pageParam.get("prodManager"))){
            pageParam.put("prodManager", StringUtil.splitToList(null, pageParam.get("prodManager").toString()));
        } else {
            pageParam.put("prodManager", null);
        }
        // add by wangyang1 【吉林银行】 增加 产品经理  end 
        pageParam.put("loginUserName", LoginUserUtil.getUserName());

        Page<FinProductDurDeal001Dto> res =   restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductDurDeal/findDetailPage", pageParam,
            FinProductDurDeal001Dto.class);

        res.getRoot().forEach(fundTrade -> {
        	//update by yantianyuan 20200731 start
            if(!SecurityUtil.isPermitted("TrdFundInvestTrade001_TBLROW_manual")){
            //update by yantianyuan 20200731 end
                fundTrade.setPermitFlag("02");
            }
            else{
                fundTrade.setPermitFlag("01");
            }
        });
        return res;
    }
}
