/*
 * 文件名：FinFundTargetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.fund.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.asset.interfaceDto.S001AssetTypeEntity;
import com.joyintech.tams.invest.asset.interfaceDto.S007Dto;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.tams.invest.common.util.MapParseUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.common.base.entity.RepAssetDept;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.component.business.asset.dto.BussFinIntPayDto;
import com.joyintech.tams.component.business.common.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.component.business.common.dto.InfChinamutualfunddescriptionDto;
import com.joyintech.tams.component.business.common.service.CommonAutoCouponService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.asset.common.service.AssetCommonService;
import com.joyintech.tams.invest.asset.fund.dto.FinFundTargetDto;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 基金类标的信息service
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月10日
 * @see FinFundTargetService
 * @since 1.0
 */
@Service
public class FinFundTargetService extends CommonExportService<FinFundTargetDto> implements BaseProcessService<FinFundTargetDto> {
    /**
     * 投管接口共通组件
     */
    @Autowired
    private InvestInterfCommService investInterfCommService;
    @Autowired
    private CommonAutoCouponService commonAutoService;
    /**
     * 共通--资产
     */
    @Autowired
    private AssetCommonService assetCommonService;
    /**
     * 使用给定的sqlID检索分页数据。
     *  
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<FinFundTargetDto> selectForPage(Map<String, Object> pageParam)
        throws Exception {
        // type9-11 created by wangguiqi on 2019/7/19 -start
        if (StringUtil.isNotEmpty(pageParam.get("type9"))) {
            pageParam.put("type9", StringUtil.splitToList(null, pageParam.get("type9").toString()));
        }else{
            pageParam.remove("type9");
        }
        if (StringUtil.isNotEmpty(pageParam.get("type10"))) {
            pageParam.put("type10", StringUtil.splitToList(null, pageParam.get("type10").toString()));
        }else{
            pageParam.remove("type10");
        }
        if (StringUtil.isNotEmpty(pageParam.get("type11"))) {
            pageParam.put("type11", StringUtil.splitToList(null, pageParam.get("type11").toString()));
        }else{
            pageParam.remove("type11");
        }
        if (StringUtil.isNotEmpty(pageParam.get("trmFundType"))) {
            pageParam.put("trmFundType", StringUtil.splitToList(null, pageParam.get("trmFundType").toString()));
        }else{
            pageParam.remove("trmFundType");
        }
        // type9-11 created by wangguiqi on 2019/7/19 -end
        Page<FinFundTargetDto> page =  restTemplate.postForPage(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinFundTarget/findPage",
            pageParam, FinFundTargetDto.class);
        return page;
    }
    /**
     * 查询资产代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFinprodMarketIdList(String param) {
        return restTemplate.getForList(
            ServiceApplication.ASSET_SERVICE_NAME +
                "/FinFundTarget/getFinprodMarketIdList?finprodMarketId="+ param,
                    SuggestDto.class);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void doInsert(FinFundTargetDto entity, OperationType type){
        //entity.setOperateType(type.toString());
        restTemplate.postForObject(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinFundTarget/doInsert",
            entity, Integer.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doUpdate(FinFundTargetDto entity){
        restTemplate.postForObject(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinFundTarget/doInsert",
            entity, Integer.class);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinFundTargetDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        // 中债报送机构代码
        RepAssetDept repAssetDept = new RepAssetDept();
        entity.getBussRepFundInfoDto().setRepAssetDept(repAssetDept);
        entity.getBussRepFundInfoDto().getRepAssetDept().setRepIssuePartyId(CacheUtil.getSysParam(BussinessConstant.ISSUE_PARTY_ID));
        
        //add by wangpeng start at 2019-07-15 :生成委外组合
        PtlPortfolio ptlPortfolio = new PtlPortfolio();
        ptlPortfolio.setPortfolioId(entity.getFinprodMarketId());
        ptlPortfolio.setPortfolioType(DictCodeListExt1.组合类型_委外组合.getValue());
        ptlPortfolio.setPortfolioName(entity.getFinprodName());
        ptlPortfolio.setVdate(entity.getVdate());
        if(entity.getMdate()==null){
        	try {
				ptlPortfolio.setMdate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2099-12-31").getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }else{
        	ptlPortfolio.setMdate(entity.getMdate());
        }
        ptlPortfolio.setProfitType(entity.getProfitType());
        entity.setPtlPortfolio(ptlPortfolio);
        //add end
        
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
        	//新建基金资产时，自动创建交易对手 add by hxy 20200421 start
    		assetCommonService.autoCreateTradeCounter(entity.getManager());
    		//新建基金资产时，自动创建交易对手 add by hxy 20200421 end
            action = "insert";
        }
        else {
            action = "update";
        }

        // 二级法人权限处理
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            //创建机构代码
            String createInst =   MultilevelDataAuthorityUtil.getService().queryOrganizationCode(entity.getCreateUser());
            entity.setCreateInst(createInst);
            // 有查询权限的机构代码
            List<String> searchOrgCodeList = null;
            // 有编辑权限的机构代码
            List<String> editOrgCodeList = null;
            // 新建或修改时重新设置二级法人权限，删除时则清空
            if (!OperationType.DELETE.equals(type)) {
                //获取所有父级机构。  拥有查看权限
                searchOrgCodeList = CommonUtil.getParentInstitution(createInst);
                // 仅资产的创建机构（当前登录用户所在机构）有编辑权限
                editOrgCodeList = Arrays.asList(entity.getCreateInst());
            }
            //modify by wj 20200624 增加组合权限
            insertDataAuthTypeInst(entity.getFinprodMarketId(), searchOrgCodeList, editOrgCodeList,DictCodeList.共通业务类型_组合管理.getValue());

        }

        restTemplate.postForObject(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinFundTarget/" + action, entity,
            Integer.class);

        // 推送数据到第三方接口

        //1.基金要素信息
        Map<String, Object> s007Map = JsonUtils.object2Map(entity);
        s007Map.put("pageId","FinFundTargetAsset002S007");
        investInterfCommService.doInvestInterf(s007Map);

    }
    /**
     * 查询基金管理人
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     *//*
    public List<SelectOptionDto> getIssuer() {
        return restTemplate.getForList(
                ServiceApplication.ASSET_SERVICE_NAME + "/FinFundTarget/getIssuer",
                    SelectOptionDto.class);
    }*/
    /**
     * 修改/双击查看根据金融产品代码查询dto
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinFundTargetDto selectByFinprodId(String finprodId) {
        return restTemplate.getForObject(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinFundTarget/selectByFinprodId?finprodId="
                +finprodId,FinFundTargetDto.class);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodMarketId
     * @return
     * @see
     * @since 1.0
     */
    public FinFundTargetDto findEntity(Map<String,Object> map) {
        return 
            restTemplate.postForObject(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinFundTarget/findEntity"
                ,map,FinFundTargetDto.class);
    }

    /**
     * 查询接口表资产信息
     * @param finprodMarketId 市场代码
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public FinFundTargetDto getInterfFundDto (String finprodMarketId) throws ParseException{
        InfChinamutualfunddescriptionDto dto = new InfChinamutualfunddescriptionDto();
//        //根据前台代码获取wind代码
//        List<FinProduct> fWindCode =  restTemplate.getForList(
//            ServiceApplication.COMMON_SERVICE_NAME + "/core/getFwindCode?finprodMarketId="
//            +finprodMarketId,FinProduct.class);
//        if (fWindCode.isEmpty() ) {
//           return null;
//        }
        List<String> list = new ArrayList<>();
        list.add(finprodMarketId);
        dto.setfInfoWindcodeList(list);
        dto = commonAutoService.autoFundCoupon(dto, finprodMarketId);
        
        BaseFinProductBaseInfoDto baseInfoDto  = new BaseFinProductBaseInfoDto();
        if (StringUtil.isNotEmpty(dto)) {
            baseInfoDto =  dto.getBaseFinProductBaseInfoList().get(0);
        }
        //基金基本信息
        FinFundTargetDto fundTargetDto = new FinFundTargetDto();
        if (!StringUtil.isNotEmpty(baseInfoDto.getFinProduct())) {
            return null;
        }
        BeanUtil.copyPropertiesIgnoreNull(fundTargetDto, baseInfoDto.getFinProduct());
        //附属表信息
        fundTargetDto.setPurSpeed(StringUtil.isEmpty(StringUtil.numberToString(baseInfoDto.getFinProductAdd().getPurSpeed()))?"0":StringUtil.numberToString(baseInfoDto.getFinProductAdd().getPurSpeed()));
        fundTargetDto.setRedSpeed(StringUtil.isEmpty(StringUtil.numberToString(baseInfoDto.getFinProductAdd().getRedSpeed()))?"0":StringUtil.numberToString(baseInfoDto.getFinProductAdd().getPurSpeed()));
        fundTargetDto.setIsCompoundInt(baseInfoDto.getFinProductAdd().getIsCompoundInt());
        //基金类型
        fundTargetDto.setFundType(baseInfoDto.getFinProductType().getType4());
        fundTargetDto.setSecondType(baseInfoDto.getFinProductType().getType5());
        fundTargetDto.setType9(baseInfoDto.getFinProductType().getType9());
        fundTargetDto.setType10(baseInfoDto.getFinProductType().getType10());
        if (StringUtil.equals(CacheUtil.getSysParam("IS_SHOW_TYPE"),"01")){
            fundTargetDto.setType11(baseInfoDto.getFinProductType().getType11());
            fundTargetDto.setType12(baseInfoDto.getFinProductType().getType12());
        }
        fundTargetDto.setTrmFundAbbr(baseInfoDto.getFinProductAdd().getTrmFundAbbr());
        //fundTargetDto.setTrmFundType(baseInfoDto.getFinProductAdd().getTrmFundType());
        //fundTargetDto.setTrmFundType("01");
        String trmFundTypeTmp = baseInfoDto.getFinProductAdd().getTrmFundType();
        if(StringUtils.equals(trmFundTypeTmp,"ETF")){
            fundTargetDto.setTrmFundType("01");
        }else if(StringUtils.equals(trmFundTypeTmp,"LOF")){
            fundTargetDto.setTrmFundType("02");
        }else if(StringUtils.equals(trmFundTypeTmp,"分级基金")){
            fundTargetDto.setTrmFundType("03");
        }else if(StringUtils.equals(trmFundTypeTmp,"封闭式基金")){
            fundTargetDto.setTrmFundType("04");
        }else if(StringUtils.equals(trmFundTypeTmp,"实时申赎货币基金")){
            fundTargetDto.setTrmFundType("05");
        }else{
            fundTargetDto.setTrmFundType("");
        }

        fundTargetDto.setPurRedId(baseInfoDto.getFinProductAdd().getPurRedId());
        fundTargetDto.setPurRedAbbr(baseInfoDto.getFinProductAdd().getPurRedAbbr());
        fundTargetDto.setPurStartLow(baseInfoDto.getFinProductAdd().getPurStartLow());
        fundTargetDto.setRedStartLow(baseInfoDto.getFinProductAdd().getRedStartLow());
        //fundTargetDto.setEtfType(baseInfoDto.getFinProductAdd().getEtfType());
        //fundTargetDto.setEtfType("01");
//        String etfTypeTmp = baseInfoDto.getFinProductAdd().getEtfType();
//        if(StringUtils.equals(etfTypeTmp,"单市场ETF")){
//            fundTargetDto.setTrmFundType("01");
//        }else if(StringUtils.equals(etfTypeTmp,"跨市场ETF")){
//            fundTargetDto.setTrmFundType("02");
//        }else if(StringUtils.equals(etfTypeTmp,"跨境ETF")){
//            fundTargetDto.setTrmFundType("03");
//        }else if(StringUtils.equals(etfTypeTmp,"债券ETF")){
//            fundTargetDto.setTrmFundType("04");
//        }else if(StringUtils.equals(etfTypeTmp,"黄金ETF")){
//            fundTargetDto.setTrmFundType("05");
//        }else if(StringUtils.equals(etfTypeTmp,"货币ETF")){
//            fundTargetDto.setTrmFundType("06");
//        }else{
//            fundTargetDto.setTrmFundType("");
//        }

        fundTargetDto.setEtfMinPrunit(baseInfoDto.getFinProductAdd().getEtfMinPrunit());

        //分红计划
        BussFinIntPayDto payDto = new BussFinIntPayDto();
        BeanUtil.copyPropertiesIgnoreNull(payDto, baseInfoDto.getFinCashPayRuleList().get(0));
        fundTargetDto.setBussFinIntPayDto(payDto);
//        payDto.setPayCycle(baseInfoDto.getFinCashPayRuleList().get(0).getPayCycle());
        //中债
        fundTargetDto.setRepfund(baseInfoDto.getRepfund());
        fundTargetDto.setRepAssetDept(baseInfoDto.getRepAssetDept());
        return fundTargetDto;
    }
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
    	String isShowType = (String) exportParam.getSelectParam().get("isShowType");
		if (StringUtil.equals(isShowType, "01")) {
			exportParam.config("/com/joyintech/tams/invest/asset/fund/conf/export_conf.xml")
					.dataSource(DataSourceType.MASERT);
		} else {
			exportParam.config("/com/joyintech/tams/invest/asset/fund/conf/export_conf002.xml")
					.dataSource(DataSourceType.MASERT);
		}
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinFundTargetDto> selectPageData(Map<String, Object> selectParam) {
        try {
            return selectForPage(selectParam);
        }
        catch (Exception e) {
             e.printStackTrace();
        }
        return null;
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getManager(Map<String,Object> param) {
        return restTemplate.postForPage(
            ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/getManager",param,
                SelectOptionDto.class);
    }
    
}
