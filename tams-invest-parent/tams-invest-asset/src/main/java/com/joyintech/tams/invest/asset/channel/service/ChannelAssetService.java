/*
 * 文件名：ChannelAssetQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.channel.service;

import java.math.BigDecimal;
import java.util.*;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinRateInfo;
import com.joyintech.tams.common.base.entity.RepAssetDept;
import com.joyintech.tams.common.base.entity.RepAssetMngPlan;
import com.joyintech.tams.common.base.entity.RepAssetOther;
import com.joyintech.tams.common.base.entity.StlFinProductAcct;
import com.joyintech.tams.component.business.common.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.dto.FileCommonDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.asset.channel.dto.ChannelAssetDto;
import com.joyintech.tams.invest.asset.common.service.AssetCommonService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 通道资产查询-处理详细
 * 〈功能详细描述〉
 * @author yangjm
 * @version 1.0 2019年3月13日
 * @see ChannelAssetQuery001Service
 * @since 1.0
 */
@Service("ChannelAssetService")
@Transactional(rollbackFor = Exception.class)
public class ChannelAssetService extends CommonExportService<ChannelAssetDto>
implements BaseProcessService<ChannelAssetDto>{
    
    @Autowired
    private CommonBusinessService commonBusinessService;
    
    /**
     * 附件处理接口
     */
    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;
    
    //通道资产查询 add by yanya 20190903
    @Autowired(required = false)
    private ChannelAssetService channelAssetService;
    @Autowired
    private AssetCommonService assetCommonService;
    /**
     * 
     * 分页查询
     * <功能详细描述>
     * @param pageParam 查询参数
     * @return 通道资产查询结果列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<ChannelAssetDto> selectForPage(Map<String,Object> pageParam) 
        throws Exception{
        return restTemplate.postForPage(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinChannelAsset/findPage", 
            pageParam, ChannelAssetDto.class);
        
    }

    public ChannelAssetDto getBasicInfo(String finprodId){
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("finprodId", finprodId);
        param.put("ccy", DictCodeList.币种_人民币_CNY.getValue());
        return restTemplate.postForObject(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinChannelAsset/getBasicInfo", 
            param, ChannelAssetDto.class);
    }
        
    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ChannelAssetDto dto, OperationType type) {
        String action = StringUtil.EMPTY;
        dto = encapBaseFinProductBaseInfoList(dto);
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
            //删除时同步删除附件 add by yanya 20190903
            List<UploadFile> files = fileAttachmentProcess.fetchByBusinessCode(dto.getFinprodId());
            if(files.size()>0) {
                files.forEach(uploadFile -> {
                    fileAttachmentProcess.remove(uploadFile.getFileNo());
                });
            }
        }
        else if (OperationType.NEW.equals(type)) {
        	//新建通道时，自动创建交易对手 add by hxy 20200421 start
    		assetCommonService.autoCreateTradeCounter(dto.getManager());
    		//新建通道时，自动创建交易对手 add by hxy 20200421 end
            action = "insert";
            //新增时将临时附件id更改为该id add by yanya 20190903
            List<UploadFile> files = fileAttachmentProcess.fetchByBusinessCode(dto.getFjId());
            if(files.size()>0) {
                List<String> fileNo = new ArrayList<>();
                files.forEach(uploadFile -> {
                    fileNo.add(uploadFile.getFileNo());
                });
                fileAttachmentProcess.updateBussinessNo(fileNo, dto.getFinprodId());
            }
        }
        else {
            action = "update";
        }

        // 二级法人权限处理
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            //创建机构代码
            String createInst =   MultilevelDataAuthorityUtil.getService().queryOrganizationCode(dto.getCreateUser());
            dto.setCreateInst(createInst);
            // 有查询权限的机构代码
            List<String> searchOrgCodeList = null;
            // 有编辑权限的机构代码
            List<String> editOrgCodeList = null;
            // 新建或修改时重新设置二级法人权限，删除时则清空
            if (!OperationType.DELETE.equals(type)) {
                //获取所有父级机构。  拥有查看权限
                searchOrgCodeList = CommonUtil.getParentInstitution(createInst);
                // 仅资产的创建机构（当前登录用户所在机构）有编辑权限
                editOrgCodeList = Arrays.asList(dto.getCreateInst());
            }
            // 记录二级法人权限
            insertDataAuthTypeInst(dto.getFinprodId(), searchOrgCodeList, editOrgCodeList,DictCodeList.共通业务类型_通道管理.getValue());
        }

        restTemplate.postForObject(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinChannelAsset/" + action, dto,
            Integer.class);


        
    }
    
    public ChannelAssetDto encapBaseFinProductBaseInfoList(ChannelAssetDto dto){
        Long branch =StringUtil.isEmpty(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE))?0:Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        BaseFinProductBaseInfoDto baseFinProductBaseInfoDto = new BaseFinProductBaseInfoDto();
        //现金流信息List
        List<FinCashInfo> finCashInfoList = new ArrayList<FinCashInfo>();
        List<FinRateInfo> finRateInfoList = new ArrayList<FinRateInfo>();
        List<FinCashCalcRule> finCashCalcRuleList = new ArrayList<FinCashCalcRule>();
        //标的类金融产品entity
        FinProduct finProduct = new FinProduct();
        BeanUtil.copyPropertiesIgnoreNull(finProduct, dto);
        finProduct.setIsChl(DictCodeList.是否_是.getValue());
        finProduct.setFinprodType(DictCodeListExt1.金融产品类型_通道.getValue());
        finProduct.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_通道.getValue());
        finProduct.setIsChl(DictCodeList.是否_是.getValue());
        finProduct.setCcy(DictCodeList.币种_人民币_CNY.getValue());
        finProduct.setFinprodAbbr(finProduct.getFinprodName());
        //期限
        finProduct.setTermDays(DateUtil.getBetweenDayNumber(dto.getVdate(),dto.getMdate()));
        //金融产品附属表entity
        FinProductAdd finProductAdd = new FinProductAdd();
        BeanUtil.copyPropertiesIgnoreNull(finProductAdd, dto);
        //金融产品账户表
        StlFinProductAcct stlFinProductAcct = new StlFinProductAcct();
        stlFinProductAcct.setFinprodId(dto.getFinprodId());
        stlFinProductAcct.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_通道.getValue());
        stlFinProductAcct.setAcctNo(dto.getBusiAcctNo());
        stlFinProductAcct.setCcy(DictCodeList.币种_人民币_CNY.getValue());
        stlFinProductAcct.setAcctType(DictCodeListExt1.资金账户类型_产品托管户.getValue());
        //现金流信息
        if(null!=dto.getBussFinProductFeeInfoDto()){
            dto.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList().forEach(item -> {
                FinCashInfo finCashInfo = new FinCashInfo();
                if(StringUtil.equals(item.getFeeType(), DictCodeList.费用品种类型_通道托管费.getValue())){
                    finCashInfo = encapFinCashInfo(finProduct,DictCodeListExt1.金融产品类型_理财产品.getValue(),DictCodeListExt1.现金流一级类型_费用.getValue(),DictCodeListExt1.现金流二级类型_通道托管费.getValue(),branch,StringUtil.EMPTY);
                }else if(StringUtil.equals(item.getFeeType(), DictCodeList.费用品种类型_通道管理费.getValue())){
                    finCashInfo = encapFinCashInfo(finProduct,DictCodeListExt1.金融产品类型_理财产品.getValue(),DictCodeListExt1.现金流一级类型_费用.getValue(),DictCodeListExt1.现金流二级类型_通道管理费.getValue(),branch,StringUtil.EMPTY);
                }else if(StringUtil.equals(item.getFeeType(), DictCodeList.费用品种类型_其他费用.getValue())){
                    finCashInfo = encapFinCashInfo(finProduct,DictCodeListExt1.金融产品类型_理财产品.getValue(),DictCodeListExt1.现金流一级类型_费用.getValue(),DictCodeListExt1.现金流二级类型_通道其他费.getValue(),branch,StringUtil.EMPTY);
                }
                finCashInfo.setFinprodType(DictCodeListExt1.金融产品类型_通道.getValue());
                finCashInfo.setFeeId(item.getFeeId());
                finCashInfo.setCollectType(item.getCollectType());
                finCashInfo.setIsCalc(DictCodeList.是否_否.getValue());
                //现金流利率
                FinRateInfo finRateInfo = new FinRateInfo();
                finRateInfo.setCashId(finCashInfo.getCashId());
                finRateInfo.setIntType(DictCodeList.利率类型_固定利率.getValue());
                finRateInfo.setEffDate(dto.getVdate());
                finRateInfo.setBasis(item.getBasis());
                finRateInfo.setRate(ArithmeticUtil.divide(item.getFeeRate(), 100));
                finRateInfo.setFinprodId(dto.getFinprodId());
                finRateInfo.setFinprodType(DictCodeListExt1.金融产品类型_通道.getValue());
                finRateInfo.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_通道.getValue());
                finRateInfo.setBranch(branch);
                //计算规则
                FinCashCalcRule  finCashCalcRule = new FinCashCalcRule();
                finCashCalcRule.setCashId(finCashInfo.getCashId());
                finCashCalcRule.setYield(ArithmeticUtil.divide(item.getFeeRate(), 100));
                finCashCalcRule.setEffDate(dto.getVdate());
                finCashCalcRule.setCalcType(DictCodeList.利率类型_固定利率.getValue());
                finCashCalcRule.setBaseType(item.getBasicBill());
                finCashCalcRule.setBasis(item.getBasis());
                if(StringUtil.isNotEmpty(item.getNetAssetValueDay())){
                    finCashCalcRule.setBaseDateType(item.getNetAssetValueDay());
                } else {
                    finCashCalcRule.setBaseDateType(DictCodeList.基数日期类型_前一自然日.getValue());
                }
                finCashCalcRule.setIsInitial(DictCodeList.是否_是.getValue());
                finCashCalcRule.setFinprodId(dto.getFinprodId());
                finCashCalcRule.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_通道.getValue());
                finCashCalcRule.setBranch(branch);
                finCashCalcRule.setFinprodType(DictCodeListExt1.金融产品类型_通道.getValue());
                finCashInfoList.add(finCashInfo);
                finRateInfoList.add(finRateInfo);
                finCashCalcRuleList.add(finCashCalcRule);
            });
             //资产负债报送信息
            RepAssetDept repAssetDept = new RepAssetDept();
            BeanUtil.copyPropertiesIgnoreNull(repAssetDept, dto.getBussRepAssetMngProdInfo());
            repAssetDept.setRepFinprodId(dto.getFinprodId());
            repAssetDept.setFinprodMarketId(dto.getFinprodMarketId());
            repAssetDept.setRepFinprodName(dto.getFinprodName());
            repAssetDept.setRepCcy(DictCodeList.币种_人民币_CNY.getValue());
            // 资产管理计划报送信息entity
            RepAssetMngPlan repAssetMngPlan = new RepAssetMngPlan();
            BeanUtil.copyPropertiesIgnoreNull(repAssetMngPlan, dto.getBussRepAssetMngProdInfo());
            if(StringUtil.isNotEmpty(dto.getBussRepAssetMngProdInfo().getRepMaxRate())) {
                repAssetMngPlan.setRepMaxRate(dto.getBussRepAssetMngProdInfo().getRepMaxRate().divide(new BigDecimal(100)));
            }
            if(StringUtil.isNotEmpty(dto.getBussRepAssetMngProdInfo().getRepMinRate())) {
                repAssetMngPlan.setRepMinRate(dto.getBussRepAssetMngProdInfo().getRepMinRate().divide(new BigDecimal(100)));
            }
            repAssetMngPlan.setRepMgrFeeRate(dto.getBussRepAssetMngProdInfo().getRepMgrFeeRate().divide(new BigDecimal(100)));
            repAssetMngPlan.setRepTruFeeRate(dto.getBussRepAssetMngProdInfo().getRepTruFeeRate().divide(new BigDecimal(100)));
            repAssetMngPlan.setRepTradeFeeRate(dto.getBussRepAssetMngProdInfo().getRepTradeFeeRate().divide(new BigDecimal(100)));
            repAssetMngPlan.setRepAgencyFeeRate(dto.getBussRepAssetMngProdInfo().getRepAgencyFeeRate().divide(new BigDecimal(100)));
            repAssetMngPlan.setRepOtherFeeRate(dto.getBussRepAssetMngProdInfo().getRepOtherFeeRate().divide(new BigDecimal(100)));
            repAssetMngPlan.setRepFinprodId(dto.getFinprodId());
            repAssetMngPlan.setRepManagerId(dto.getManager());
            repAssetMngPlan.setRepTrusteeId(dto.getTrusteeId());
            repAssetMngPlan.setRepSetDate(dto.getVdate());
            repAssetMngPlan.setRepEndDate(dto.getMdate());
            //中债资产其他要素
            RepAssetOther repAssetOther = new RepAssetOther();
            BeanUtil.copyPropertiesIgnoreNull(repAssetOther, dto.getBussRepAssetMngProdInfo());
            repAssetOther.setRepFinprodId(dto.getFinprodId());
            
            baseFinProductBaseInfoDto.setFinProduct(finProduct);baseFinProductBaseInfoDto.setFinProductAdd(finProductAdd);
            baseFinProductBaseInfoDto.setFinCashInfoList(finCashInfoList);
            baseFinProductBaseInfoDto.setFinRateInfoList(finRateInfoList);
            baseFinProductBaseInfoDto.setFinCashCalcRuleList(finCashCalcRuleList);
            List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList = new ArrayList<BaseFinProductBaseInfoDto>();
            baseFinProductBaseInfoList.add(baseFinProductBaseInfoDto);
            dto.setBaseFinProductBaseInfoList(baseFinProductBaseInfoList);
            dto.setStlFinProductAcct(stlFinProductAcct);
            dto.setRepAssetDept(repAssetDept);
            dto.setRepAssetMngPlan(repAssetMngPlan);
            dto.setRepAssetOther(repAssetOther);
        }
        return dto;
    }
    /**
     * 模糊查询通道代码
     * @param search 查询条件
     * @return 通道代码 金融产品代码
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getSecAcctId(Map<String,Object> map) {
        return restTemplate.postForList(ServiceApplication.ASSET_SERVICE_NAME +
            "/FinChannelAsset/getSecAcctId?search",
            map , SelectOptionDto.class);
    }

    /**
     * 模糊查询通道名称
     * @param search 查询条件
     * @return 通道名称 金融产品代码
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getSecAcctName(Map<String,Object> map) {
        return restTemplate.postForList(ServiceApplication.ASSET_SERVICE_NAME +
            "/FinChannelAsset/getSecAcctName?search",
            map , SelectOptionDto.class);
    }
    
    
    /**
     * 生成金融产品代码/市场代码
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public ChannelAssetDto getFinprodId(ChannelAssetDto dto){
        String finprodId = StringUtil.EMPTY;
        String  finprodMarketId = dto.getFinprodMarketId();
        finprodId = commonBusinessService!=null?commonBusinessService.getFinProdId(finprodMarketId, DictCodeListExt1.金融产品类型_投管用_通道.getValue(), dto.getChlFinprodId()):IDGenerator.generate();
        dto.setFinprodId(finprodId);
        return dto;
    }
    
    
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/asset/channel/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ChannelAssetDto> selectPageData(Map<String, Object> selectParam) {
        try {
            return selectForPage(selectParam);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 组装现金流信息
     * @see
     * @since 1.0
     */
    public FinCashInfo encapFinCashInfo(FinProduct dto,String commonBusinessType,String cashType1,String cashType2,Long branch,String counterId){
        FinCashInfo finCashInfo = new FinCashInfo();
        //工作流ID
        String cashId = commonBusinessService.generateCashFlowCode(dto.getFinprodId()+counterId, commonBusinessType, branch, cashType2);
        BeanUtil.copyPropertiesIgnoreNull(finCashInfo, dto);
        finCashInfo.setCashId(cashId);
        finCashInfo.setCashType1(cashType1);
        finCashInfo.setCashType2(cashType2);
        finCashInfo.setBranch(branch);
        return  finCashInfo;
    }
    
    /**
     * 资金账户
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getAcctNo(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.COMMON_SERVICE_NAME
            + "/CommonSelect/getAcctNo", map,
            SelectOptionDto.class);
    }
    
    /**
     * 资产是否重复
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public int isRepeated(Map<String,Object> param){
        return restTemplate.postForObject(
            ServiceApplication.ASSET_SERVICE_NAME + "/AssetCommon/isRepeated"
            ,param,Integer.class);
    }

    /**
     * 判断是否存在交易
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public int isTransInAsset(String finprodId) {
        return restTemplate.getForObject(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinChannelAsset/isTransInAsset?finprodId="+finprodId,Integer.class);
    }

    /**
     * 增加附件  yanya 20190903
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public void doInsert(ChannelAssetDto entity, OperationType type) {
        updateFiles(entity);
    }
    
    /**
     * 修改附件  yanya 20190903
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public void doUpdate(ChannelAssetDto entity) {
    	 updateFiles(entity);
    }
    		
    /**
     * 删除附件  yanya 20190903
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public void doDelete(String workflowId) {
    	ChannelAssetDto channelAssetDto = channelAssetService.getBussinessEntityByWorkflowId(workflowId);
    	 List<UploadFile> files = fileAttachmentProcess.fetchByBusinessCode(channelAssetDto.getFjId());
         if(files.size()>0) {
             files.forEach(uploadFile -> {
                 fileAttachmentProcess.remove(uploadFile.getFileNo());
             });
         }
    }
    private void updateFiles(ChannelAssetDto entity) {
        List<FileCommonDto> files = entity.getFileCommonList();
        if(files.size()>0) {
            List<String> fileNo = new ArrayList<>();
            files.forEach(fileCommonDto -> {
                fileNo.add(fileCommonDto.getFileNo());
            });
            fileAttachmentProcess.updateBussinessNo(fileNo, entity.getFjId());
        }
    }

    /**
     * 资金账户已经有通道挂的话，不允许再挂
     * @param map
     * @return
     */
    public int isReBusiAcctNo(Map<String, Object> map) {
        return restTemplate.postForObject(
                ServiceApplication.ASSET_SERVICE_NAME + "/FinChannelAsset/isReBusiAcctNo", map,Integer.class);
    }
}
