package com.joyintech.tams.report.system.chbprd.service;

import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.system.chbprd.dao.ChbPrdBaseinfo001Mapper;
import com.joyintech.tams.report.system.chbprd.dto.ChbPrdBaseinfo001Dto;
import com.joyintech.tams.report.system.chbprd.dto.ChbPrdBaseinfoErrodataWithBLOBs;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateInfoErrodataWithBLOBs;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import com.joyintech.tams.svreport.interf.dis.prodcashyield.dto.DisRepCashYield;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ChbPrdBaseinfo001Service extends CommonExportService<ChbPrdBaseinfo> {

    @Autowired
    private ChbPrdBaseinfo001Mapper chbPrdBaseinfo001Mapper;
    /**
     * 当在做导入前的check就是判断业务数据是否需要覆盖的时候在webresult的data中塞入这个值可以不会弹框两次
     */
    private static final String NO_INFO_SHOWOUT = "noInfoShowOut";

    @Autowired
    private ChbDataCheckCommonUtil chbDataCheckCommonUtil;

    public Page<ChbPrdBaseinfo> searchForPage(Map<String, Object> param){
        Page<ChbPrdBaseinfo> page;
        int pagenum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
        String prodId = (String)param.get("prodId");
        /*String raiseVdate = (String)param.get("raiseVdate");
        String raiseEnddate = (String)param.get("raiseEnddate");
        String prdVdate = (String)param.get("prdVdate");
        String prdMdate = (String)param.get("prdMdate");*/
        String status = (String)param.get("status");
        List<String> selectprodId = StringUtil.splitToList(GlobalConstant.COMMA, prodId);
        List<String> selectStatus = StringUtil.splitToList(GlobalConstant.COMMA, status);
        param.put("selectprodId", selectprodId);
        param.put("selectStatus", selectStatus);
        page=  super.queryForPage(pagenum,legth,()->chbPrdBaseinfo001Mapper.queryForPage(param));
        dealDataList(page.getRoot());
        return page;
    }

    /**
     * 处理查询结果 <功能详细描述>
     *
     * @param list
     *            待处理数据
     * @see
     * @since 1.0
     */
    private void dealDataList(List<ChbPrdBaseinfo> list) {
        list.forEach(item -> {
            item.setRaiseTypeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.募集方式_中债 , item.getRaiseType()));
            item.setProfitTypeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.产品收益标识 , item.getProfitType()));
            item.setIsFhxgcpName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.是或否 , item.getIsFhxgcp()));
            item.setTermtypeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.产品期限 , item.getTermtype()));
            item.setExcFinSectorName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.是或否 , item.getExcFinSector()));
            item.setInvestAraeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.资金投向地区 , item.getInvestArae()));
            item.setInvestCountryName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.所属国家或地区 , item.getInvestCountry()));
            item.setFinanSerModelName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.理财业务服务模式 , item.getFinanSerModel()));
            item.setOperateModeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.产品运作模式 , item.getOperateMode()));
            item.setIsShortTermName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.是或否 , item.getIsShortTerm()));
            item.setIsFreeRedemName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.是或否 , item.getIsFreeRedem()));
            item.setCashManaCateName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.是或否 , item.getCashManaCate()));
            item.setCmmodeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.产品资产配置方式_中债 , item.getCmmode()));
            item.setPmmodeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.产品管理模式 , item.getPmmode()));
            item.setPricedModeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.产品定价方式 , item.getPricedMode()));
            item.setIvstorientationName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.投资性质 , item.getIvstorientation()));
            item.setContModeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.合作模式 , item.getContMode()));
            item.setInvTypeRatName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.是或否 , item.getInvTypeRat()));
            item.setRiskTypeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.投资者风险偏好 , item.getRiskType()));
            item.setSaleAreaName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.区域划分_省 , item.getSaleArea()));
            item.setRaiseCcyName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.币种_中债 , item.getRaiseCcy()));
            item.setCashPrinCcyName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.币种_中债 , item.getCashPrinCcy()));
            item.setCashProfCcyName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.币种_中债 , item.getCashProfCcy()));
            item.setInvestPrinDateName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.投资本金_收益_到账日_中债 , item.getInvestPrinDate()));
            item.setInvestProfDateName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.投资本金_收益_到账日_中债 , item.getInvestProfDate()));
            item.setInDepNameStr(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.境内托管机构名称 , item.getInDepName()));
            item.setOutDepCtryName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.所属国家或地区 , item.getOutDepCtry()));
            item.setRiskLevelName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.产品风险等级_中债 , item.getRiskLevel()));
            item.setTermRightFlagName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.有无_中债 , item.getTermRightFlag()));
            item.setCusRedeemFlagName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.有无_中债 , item.getCusRedeemFlag()));
            item.setCreditFlagName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.有无_中债 , item.getCreditFlag()));
            item.setCreditTypeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.产品增信机构类型 , item.getCreditType()));
            item.setCreditModeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.产品增信形式 , item.getCreditMode()));
            item.setManageTypeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.管理方式_发行登记 , item.getManageType()));
            item.setIsConstracteName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.是或否 , item.getIsConstracte()));
            item.setOpenModeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.开放模式_中债 , item.getOpenMode()));
            item.setOpenCircleName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.规律开放周期 , item.getOpenCircle()));
            item.setHolidayOnFlagName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.是或否 , item.getHolidayOnFlag()));
            item.setOpenBussName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.开放期业务 , item.getOpenBuss()));
            item.setFiduciaryDutyName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.产品受托职责_中债 , item.getFiduciaryDuty()));
            item.setCustomerTypeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.人行客户类型 , item.getCustomerType()));
            item.setIsTransferName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.是或否 , item.getIsTransfer()));
            item.setIsFullyCompliantName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.是或否 , item.getIsFullyCompliant()));
            item.setIsYllccpName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.是或否 , item.getIsYllccp()));
            item.setTransferTypeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.产品转让类型_中债 , item.getTransferType()));
            item.setStatusName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.维护数据状态, item.getStatus()));
            item.setDataSourceName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.数据来源, item.getDataSource()));
            if("DB".equals(item.getDataSource())){
                item.setDataSourceName("DB");
            }
        });


    }
    private void recoverDataList(List<DisRepCashYield> list) {
        list.forEach(item -> {
            item.setCashType2(SysDataUtil.getZZDictCodeFromName(
                    DictCode.信披费用类型, item.getCashType2()));
            item.setStatus(SysDataUtil.getZZDictCodeFromName(
                    DictCode.维护数据状态, item.getStatus()));
            item.setDataSource(SysDataUtil.getZZDictCodeFromName(
                    DictCode.数据来源, item.getDataSource()));
        });
    }

    public List<ChbPrdBaseinfo> getProdinfo(Map<String, Object> param) {
        return chbPrdBaseinfo001Mapper.getProdinfo( param);
    }
    public List<SysDataDict> getSysDataDict(Map<String, Object> param) {
        return chbPrdBaseinfo001Mapper.getSysDataDict( param);
    }

    public List<Integer> selectPrdFeeData(List<DisRepCashYield> dtoList) {
        return chbPrdBaseinfo001Mapper.selectPrdFeeData(dtoList);
    }

    public void insertPrdFeeData(List<ChbPrdBaseinfo> dtoList) {
         chbPrdBaseinfo001Mapper.insertPrdFeeData(dtoList);
        return;
    }

    public void updateChbPrdBaseinfo(List<ChbPrdBaseinfo> dtoList) {
        chbPrdBaseinfo001Mapper.updateChbPrdBaseinfo(dtoList);
        return;
    }

    public void deleteChbPrdBaseinfoErrorData(List<ChbPrdBaseinfo> dtoList) {
        chbPrdBaseinfo001Mapper.deleteChbPrdBaseinfoErrorData(dtoList);
        return;
    }

    public List<ChbPrdBaseinfo> queryReportList(Map<String, Object> param) {
        String prodIds = (String)param.get("keyprodIdList");
        List<String> selectprodIds = StringUtil.splitToList(GlobalConstant.COMMA, prodIds);
        param.put("selectprodIds", selectprodIds);
        return chbPrdBaseinfo001Mapper.queryReportList( param);
    }

    public List<ChbPrdBaseinfo> selectOneDate(Map<String, Object> param) {
        return chbPrdBaseinfo001Mapper.selectOneDate( param);
    }

    public List<ChbPrdBaseinfoErrodataWithBLOBs> selectchbPrdBaseinfoErrordata(String dataUuid) {
        return chbPrdBaseinfo001Mapper.selectchbPrdBaseinfoErrordata( dataUuid);
    }

    public AjaxResult checkConfirmMsgForImportData(List<List<String>> dataList) throws ParseException {
        AjaxResult webResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        //新建时,判断excel表中是否存在重复产品代码的数据
        //更新删除时,查询数据不存在则提示 不存在数据请核查
        for (int i = 0; i < dataList.size(); i++ ) {
            for (int j = i+1; j < dataList.size(); j++ ) {
                if(StringUtils.equals(dataList.get(i).get(1), dataList.get(j).get(1))){
                    ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行产品代码与"+"第"+(j+2)+"行产品代码相同");
                    errorList.add(validMessage);
                }
            }
        }
        Map<String,Object> map=new HashMap<>();
        if(errorList.size() == 0){
            for (int i = 0; i < dataList.size(); i++ ) {
                //操作类型
                String type ="";
                if(null!=dataList.get(i).get(0) && !"".equals(dataList.get(i).get(0))){
                    type = dataList.get(i).get(0).toString();
                }
                //产品代码
                String prodId ="";
                if(null!=dataList.get(i).get(1)&& !"".equals(dataList.get(i).get(1))){
                    prodId =dataList.get(i).get(1).toString();
                }
                //发行机构代码
                String orgCode ="";
                if(null!=dataList.get(i).get(2)&& !"".equals(dataList.get(i).get(2))){
                    orgCode =dataList.get(i).get(2).toString();
                }
                DateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
                //募集起始日期
                Date raiseVdate=null;
                if(null!=dataList.get(i).get(3)&& !"".equals(dataList.get(i).get(3))){
                    if(dataList.get(i).get(3).contains("-")){
                        raiseVdate=dfs.parse(dataList.get(i).get(3));
                    }else{
                        raiseVdate=new Date(dataList.get(i).get(3));
                    }
                }
                //募集结束日期
                Date raiseEnddate=null;
                if(null!=dataList.get(i).get(4)&& !"".equals(dataList.get(i).get(4))){
                    if(dataList.get(i).get(4).contains("-")){
                        raiseEnddate=dfs.parse(dataList.get(i).get(4));
                    }else{
                        raiseEnddate=new Date(dataList.get(i).get(4));
                    }
                }
                //起息日
                Date vdate=null;
                if(null!=dataList.get(i).get(5)&& !"".equals(dataList.get(i).get(5))){
                    if(dataList.get(i).get(5).contains("-")){
                        vdate=dfs.parse(dataList.get(i).get(5));
                    }else{
                        vdate=new Date(dataList.get(i).get(5));
                    }
                }
                //到期日
                Date mdate=null;
                if(null!=dataList.get(i).get(6)&& !"".equals(dataList.get(i).get(6))){
                    if(dataList.get(i).get(6).contains("-")){
                        mdate=dfs.parse(dataList.get(i).get(6));
                    }else{
                        mdate=new Date(dataList.get(i).get(6));
                    }
                }
                //管理方式
                String manageType ="";
                if(null!=dataList.get(i).get(7)&& !"".equals(dataList.get(i).get(7))){
                    String [] arr = dataList.get(i).get(7).toString().split("\\s+");
                    manageType=arr[0];
                }
                //是否结构化分级产品
                String isConstracte ="";
                if(null!=dataList.get(i).get(8)&& !"".equals(dataList.get(i).get(8))){
                    String [] arr = dataList.get(i).get(8).toString().split("\\s+");
                    isConstracte=arr[0];
                }
                //业绩比较基准上限%
                BigDecimal yieldHigh=null;
                if(null!=dataList.get(i).get(9)&& !"".equals(dataList.get(i).get(9))){
                    yieldHigh=new BigDecimal(dataList.get(i).get(9).toString());
                }
                //业绩比较基准下限%
                BigDecimal yieldLow=null;
                if(null!=dataList.get(i).get(10)&& !"".equals(dataList.get(i).get(10))){
                    yieldLow=new BigDecimal(dataList.get(i).get(10).toString());
                }
                //业绩比较基准说明
                String yieldRemark ="";
                if(null!=dataList.get(i).get(11)&& !"".equals(dataList.get(i).get(11))){
                    yieldRemark =dataList.get(i).get(11).toString();
                }
                //开放模式
                String openMode ="";
                if(null!=dataList.get(i).get(12)&& !"".equals(dataList.get(i).get(12))){
                    String [] arr = dataList.get(i).get(12).toString().split("\\s+");
                    openMode=arr[0];
                }
                //规律开放周期
                String openCircle ="";
                if(null!=dataList.get(i).get(13)&& !"".equals(dataList.get(i).get(13))){
                    String [] arr = dataList.get(i).get(13).toString().split("\\s+");
                    openCircle=arr[0];
                }
                //其他规律开放周期（天）
                BigDecimal openCircleDays=null;
                if(null!=dataList.get(i).get(14)&& !"".equals(dataList.get(i).get(14))){
                    openCircleDays=new BigDecimal(dataList.get(i).get(14).toString());
                }
                //无规律开放说明
                String ruleComment ="";
                if(null!=dataList.get(i).get(15)&& !"".equals(dataList.get(i).get(15))){
                    ruleComment =dataList.get(i).get(15).toString();
                }
                //首次开放周期起始日
                Date firstOpenDay=null;
                if(null!=dataList.get(i).get(16)&& !"".equals(dataList.get(i).get(16))){
                    if(dataList.get(i).get(16).contains("-")){
                        firstOpenDay=dfs.parse(dataList.get(i).get(16));
                    }else{
                        firstOpenDay=new Date(dataList.get(i).get(16));
                    }
                }
                //节假日是否开放
                String holidayOnFlag ="";
                if(null!=dataList.get(i).get(17)&& !"".equals(dataList.get(i).get(17))){
                    String [] arr = dataList.get(i).get(17).toString().split("\\s+");
                    holidayOnFlag=arr[0];
                }
                //平均开放次数（年化）
                BigDecimal avgOpenCount=null;
                if(null!=dataList.get(i).get(18)&& !"".equals(dataList.get(i).get(18))){
                    avgOpenCount=new BigDecimal(dataList.get(i).get(18).toString());
                }
                //开放期业务
                String openBuss ="";
                if(null!=dataList.get(i).get(19)&& !"".equals(dataList.get(i).get(19))){
                    String [] arr = dataList.get(i).get(19).toString().split("\\s+");
                    openBuss=arr[0];
                }
                //开放期业务说明
                String openBussRemark ="";
                if(null!=dataList.get(i).get(20)&& !"".equals(dataList.get(i).get(20))){
                    openBussRemark =dataList.get(i).get(20).toString();
                }
                //资金托管账号
                String busiAcctNo ="";
                if(null!=dataList.get(i).get(21)&& !"".equals(dataList.get(i).get(21))){
                    busiAcctNo =dataList.get(i).get(21).toString();
                }
                //资金托管账户
                String acctName ="";
                if(null!=dataList.get(i).get(22)&& !"".equals(dataList.get(i).get(22))){
                    acctName =dataList.get(i).get(22).toString();
                }
                //产品类型
                String prodtype ="";
                if(null!=dataList.get(i).get(23)&& !"".equals(dataList.get(i).get(23))){
                    String [] arr = dataList.get(i).get(23).toString().split("\\s+");
                    prodtype=arr[0];
                }
                //计息基础
                String basic ="";
                if(null!=dataList.get(i).get(24)&& !"".equals(dataList.get(i).get(24))){
                    String [] arr = dataList.get(i).get(24).toString().split("\\s+");
                    basic=arr[0];
                }
                //受托职责
                String fiduciaryDuty ="";
                if(null!=dataList.get(i).get(25)&& !"".equals(dataList.get(i).get(25))){
                    String [] arr = dataList.get(i).get(25).toString().split("\\s+");
                    fiduciaryDuty=arr[0];
                }
                //客户类型
                String customerType ="";
                if(null!=dataList.get(i).get(26)&& !"".equals(dataList.get(i).get(26))){
                    String [] arr = dataList.get(i).get(26).toString().split("\\s+");
                    customerType=arr[0];
                }
                //是否母行划转产品
                String isTransfer ="";
                if(null!=dataList.get(i).get(27)&& !"".equals(dataList.get(i).get(27))){
                    String [] arr = dataList.get(i).get(27).toString().split("\\s+");
                    isTransfer=arr[0];
                }
                //现金管理类产品-是否完全符合
                String isFullyCompliant ="";
                if(null!=dataList.get(i).get(28)&& !"".equals(dataList.get(i).get(28))){
                    String [] arr = dataList.get(i).get(28).toString().split("\\s+");
                    isFullyCompliant=arr[0];
                }
                //是否养老理财
                String isYllccp ="";
                if(null!=dataList.get(i).get(29)&& !"".equals(dataList.get(i).get(29))){
                    String [] arr = dataList.get(i).get(29).toString().split("\\s+");
                    isYllccp=arr[0];
                }
                //是否收益权转让产品
                String isSyqzccp ="";
                if(null!=dataList.get(i).get(30)&& !"".equals(dataList.get(i).get(30))){
                    String [] arr = dataList.get(i).get(30).toString().split("\\s+");
                    isSyqzccp=arr[0];
                }
                //发行状态
                String issuingStatus ="";
                if(null!=dataList.get(i).get(31)&& !"".equals(dataList.get(i).get(31))){
                    String [] arr = dataList.get(i).get(31).toString().split("\\s+");
                    issuingStatus=arr[0];
                }
                ChbPrdBaseinfo001Dto dto=new ChbPrdBaseinfo001Dto();
                //dto.setProduuid(IDGenerator.generate(36));
                dto.setProdId(prodId);
                dto.setOrgCode(orgCode);
                dto.setRaiseVdate(raiseVdate);
                dto.setRaiseEnddate(raiseEnddate);
                dto.setVdate(vdate);
                dto.setMdate(mdate);
                dto.setManageType(manageType);
                dto.setIsConstracte(isConstracte);
                dto.setYieldHigh(yieldHigh);
                dto.setYieldLow(yieldLow);
                dto.setYieldRemark(yieldRemark);
                dto.setOpenMode(openMode);
                dto.setOpenCircle(openCircle);
                dto.setOpenCircleDays(openCircleDays);
                dto.setRuleComment(ruleComment);
                dto.setFirstOpenDay(firstOpenDay);
                dto.setHolidayOnFlag(holidayOnFlag);
                dto.setAvgOpenCount(avgOpenCount);
                dto.setOpenBuss(openBuss);
                dto.setOpenBussRemark(openBussRemark);
                dto.setBusiAcctNo(busiAcctNo);
                dto.setAcctName(acctName);
                dto.setProdtype(prodtype);
                dto.setBasic(basic);
                dto.setFiduciaryDuty(fiduciaryDuty);
                dto.setCustomerType(customerType);
                dto.setIsTransfer(isTransfer);
                dto.setIsFullyCompliant(isFullyCompliant);
                dto.setIsYllccp(isYllccp);
                dto.setIsSyqzccp(isSyqzccp);
                dto.setIssuingStatus(issuingStatus);
                errorList = chbDataCheckCommonUtil.chbCheckCommon(errorList, dto, "ChbPrdBaseinfo002", null, ValidMessage.class);
                //if(errorList.size() == 0){
                    map.put("prodId",prodId);
                    List<ChbPrdBaseinfo> chbPrdBaseinfos= chbPrdBaseinfo001Mapper.selectOneDate(map);
                    if("新增".equals(type)){
                        if(null!=chbPrdBaseinfos && chbPrdBaseinfos.size()>0){
                            ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行已存在重复产品代码的数据");
                            errorList.add(validMessage);
                        }
                    }
                    if("修改".equals(type) || "删除".equals(type)){
                        if(chbPrdBaseinfos.size()<=0){
                            ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行不存在产品代码的数据,请核查");
                            errorList.add(validMessage);
                        }
                    }
                }
            //}
        }
        if(errorList.size() == 0){
            webResult.setData(NO_INFO_SHOWOUT);
        }else{
            webResult.setErrorList(errorList);
        }
        return webResult;
    }

    public List<ExcelValidDto> importData(List<List<String>> dataList, List<List<String>> tempList,String userName, String orgCode, String deptCode) throws ParseException {
        if (!StringUtil.isEmptyList(dataList)) {
            List<String> headList = new ArrayList<String>();
            headList.add("操作类型");
            headList.add("产品代码");
            headList.add("发行机构代码");
            headList.add("募集起始日期");
            headList.add("募集结束日期");
            headList.add("起息日");
            headList.add("到期日");
            headList.add("管理方式");
            headList.add("是否结构化分级产品");
            headList.add("业绩比较基准上限%");
            headList.add("业绩比较基准下限%");
            headList.add("业绩比较基准说明");
            headList.add("开放模式");
            headList.add("规律开放周期");
            headList.add("其他规律开放周期（天）");
            headList.add("无规律开放说明");
            headList.add("首次开放周期起始日");
            headList.add("节假日是否开放");
            headList.add("平均开放次数（年化）");
            headList.add("开放期业务");
            headList.add("开放期业务说明");
            headList.add("资金托管账号");
            headList.add("资金托管账户");
            headList.add("产品类型");
            headList.add("计息基础");
            headList.add("受托职责");
            headList.add("客户类型");
            headList.add("是否母行划转产品");
            headList.add("现金管理类产品-是否完全符合");
            headList.add("是否养老理财");
            headList.add("是否收益权转让产品");
            headList.add("发行状态");
            List<ExcelValidDto> tempCheckResult = ExcelUtil.checkExcelTemp(tempList, headList);
            if (!StringUtil.isEmptyList(tempCheckResult)) {
                return tempCheckResult;
            }
            if (null != dataList) {
                List<DisRepCashYield> entities = new ArrayList<>(dataList.size());
                Map<String,Object> map=new HashMap<>();
                for (int i = 0; i < dataList.size(); i++ ) {
                    //操作类型
                    String type ="";
                    if(null!=dataList.get(i).get(0) && !"".equals(dataList.get(i).get(0))){
                        type = dataList.get(i).get(0).toString();
                    }
                    //产品代码
                    String prodId ="";
                    if(null!=dataList.get(i).get(1)&& !"".equals(dataList.get(i).get(1))){
                        prodId =dataList.get(i).get(1).toString();
                    }
                    //发行机构代码
                    String orgCode1 ="";
                    if(null!=dataList.get(i).get(2)&& !"".equals(dataList.get(i).get(2))){
                        orgCode1 =dataList.get(i).get(2).toString();
                    }
                    SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
                    //募集起始日期
                    Date raiseVdate=null;
                    if(null!=dataList.get(i).get(3)&& !"".equals(dataList.get(i).get(3))){
                        if(dataList.get(i).get(3).contains("-")){
                            raiseVdate=dfs.parse(dataList.get(i).get(3));
                        }else{
                            raiseVdate=new Date(dataList.get(i).get(3));
                        }
                    }
                    //募集结束日期
                    Date raiseEnddate=null;
                    if(null!=dataList.get(i).get(4)&& !"".equals(dataList.get(i).get(4))){
                        if(dataList.get(i).get(4).contains("-")){
                            raiseEnddate=dfs.parse(dataList.get(i).get(4));
                        }else{
                            raiseEnddate=new Date(dataList.get(i).get(4));
                        }
                    }
                    //起息日
                    Date vdate=null;
                    if(null!=dataList.get(i).get(5)&& !"".equals(dataList.get(i).get(5))){
                        if(dataList.get(i).get(5).contains("-")){
                            vdate=dfs.parse(dataList.get(i).get(5));
                        }else{
                            vdate=new Date(dataList.get(i).get(5));
                        }
                    }
                    //到期日
                    Date mdate=null;
                    if(null!=dataList.get(i).get(6)&& !"".equals(dataList.get(i).get(6))){
                        if(dataList.get(i).get(6).contains("-")){
                            mdate=dfs.parse(dataList.get(i).get(6));
                        }else{
                            mdate=new Date(dataList.get(i).get(6));
                        }
                    }
                    //管理方式
                    String manageType ="";
                    if(null!=dataList.get(i).get(7)&& !"".equals(dataList.get(i).get(7))){
                        String [] arr = dataList.get(i).get(7).toString().split("\\s+");
                        manageType=arr[0];
                    }
                    //是否结构化分级产品
                    String isConstracte ="";
                    if(null!=dataList.get(i).get(8)&& !"".equals(dataList.get(i).get(8))){
                        String [] arr = dataList.get(i).get(8).toString().split("\\s+");
                        isConstracte=arr[0];
                    }
                    //业绩比较基准上限%
                    BigDecimal yieldHigh=null;
                    if(null!=dataList.get(i).get(9)&& !"".equals(dataList.get(i).get(9))){
                        yieldHigh=new BigDecimal(dataList.get(i).get(9).toString());
                    }
                    //业绩比较基准下限%
                    BigDecimal yieldLow=null;
                    if(null!=dataList.get(i).get(10)&& !"".equals(dataList.get(i).get(10))){
                        yieldLow=new BigDecimal(dataList.get(i).get(10).toString());
                    }
                    //业绩比较基准说明
                    String yieldRemark ="";
                    if(null!=dataList.get(i).get(11)&& !"".equals(dataList.get(i).get(11))){
                        yieldRemark =dataList.get(i).get(11).toString();
                    }
                    //开放模式
                    String openMode ="";
                    if(null!=dataList.get(i).get(12)&& !"".equals(dataList.get(i).get(12))){
                        String [] arr = dataList.get(i).get(12).toString().split("\\s+");
                        openMode=arr[0];
                    }
                    //规律开放周期
                    String openCircle ="";
                    if(null!=dataList.get(i).get(13)&& !"".equals(dataList.get(i).get(13))){
                        String [] arr = dataList.get(i).get(13).toString().split("\\s+");
                        openCircle=arr[0];
                    }
                    //其他规律开放周期（天）
                    BigDecimal openCircleDays=null;
                    if(null!=dataList.get(i).get(14)&& !"".equals(dataList.get(i).get(14))){
                        openCircleDays=new BigDecimal(dataList.get(i).get(14).toString());
                    }
                    //无规律开放说明
                    String ruleComment ="";
                    if(null!=dataList.get(i).get(15)&& !"".equals(dataList.get(i).get(15))){
                        ruleComment =dataList.get(i).get(15).toString();
                    }
                    //首次开放周期起始日
                    Date firstOpenDay=null;
                    if(null!=dataList.get(i).get(16)&& !"".equals(dataList.get(i).get(16))){
                        if(dataList.get(i).get(16).contains("-")){
                            firstOpenDay=dfs.parse(dataList.get(i).get(16));
                        }else{
                            firstOpenDay=new Date(dataList.get(i).get(16));
                        }
                    }
                    //节假日是否开放
                    String holidayOnFlag ="";
                    if(null!=dataList.get(i).get(17)&& !"".equals(dataList.get(i).get(17))){
                        String [] arr = dataList.get(i).get(17).toString().split("\\s+");
                        holidayOnFlag=arr[0];
                    }
                    //平均开放次数（年化）
                    BigDecimal avgOpenCount=null;
                    if(null!=dataList.get(i).get(18)&& !"".equals(dataList.get(i).get(18))){
                        avgOpenCount=new BigDecimal(dataList.get(i).get(18).toString());
                    }
                    //开放期业务
                    String openBuss ="";
                    if(null!=dataList.get(i).get(19)&& !"".equals(dataList.get(i).get(19))){
                        String [] arr = dataList.get(i).get(19).toString().split("\\s+");
                        openBuss=arr[0];
                    }
                    //开放期业务说明
                    String openBussRemark ="";
                    if(null!=dataList.get(i).get(20)&& !"".equals(dataList.get(i).get(20))){
                        openBussRemark =dataList.get(i).get(20).toString();
                    }
                    //资金托管账号
                    String busiAcctNo ="";
                    if(null!=dataList.get(i).get(21)&& !"".equals(dataList.get(i).get(21))){
                        busiAcctNo =dataList.get(i).get(21).toString();
                    }
                    //资金托管账户
                    String acctName ="";
                    if(null!=dataList.get(i).get(22)&& !"".equals(dataList.get(i).get(22))){
                        acctName =dataList.get(i).get(22).toString();
                    }
                    //产品类型
                    String prodtype ="";
                    if(null!=dataList.get(i).get(23)&& !"".equals(dataList.get(i).get(23))){
                        String [] arr = dataList.get(i).get(23).toString().split("\\s+");
                        prodtype=arr[0];
                    }
                    //计息基础
                    String basic ="";
                    if(null!=dataList.get(i).get(24)&& !"".equals(dataList.get(i).get(24))){
                        String [] arr = dataList.get(i).get(24).toString().split("\\s+");
                        basic=arr[0];
                    }
                    //受托职责
                    String fiduciaryDuty ="";
                    if(null!=dataList.get(i).get(25)&& !"".equals(dataList.get(i).get(25))){
                        String [] arr = dataList.get(i).get(25).toString().split("\\s+");
                        fiduciaryDuty=arr[0];
                    }
                    //客户类型
                    String customerType ="";
                    if(null!=dataList.get(i).get(26)&& !"".equals(dataList.get(i).get(26))){
                        String [] arr = dataList.get(i).get(26).toString().split("\\s+");
                        customerType=arr[0];
                    }
                    //是否母行划转产品
                    String isTransfer ="";
                    if(null!=dataList.get(i).get(27)&& !"".equals(dataList.get(i).get(27))){
                        String [] arr = dataList.get(i).get(27).toString().split("\\s+");
                        isTransfer=arr[0];
                    }
                    //现金管理类产品-是否完全符合
                    String isFullyCompliant ="";
                    if(null!=dataList.get(i).get(28)&& !"".equals(dataList.get(i).get(28))){
                        String [] arr = dataList.get(i).get(28).toString().split("\\s+");
                        isFullyCompliant=arr[0];
                    }
                    //是否养老理财
                    String isYllccp ="";
                    if(null!=dataList.get(i).get(29)&& !"".equals(dataList.get(i).get(29))){
                        String [] arr = dataList.get(i).get(29).toString().split("\\s+");
                        isYllccp=arr[0];
                    }
                    //是否收益权转让产品
                    String isSyqzccp ="";
                    if(null!=dataList.get(i).get(30)&& !"".equals(dataList.get(i).get(30))){
                        String [] arr = dataList.get(i).get(30).toString().split("\\s+");
                        isSyqzccp=arr[0];
                    }
                    //发行状态
                    String issuingStatus ="";
                    if(null!=dataList.get(i).get(31)&& !"".equals(dataList.get(i).get(31))){
                        String [] arr = dataList.get(i).get(31).toString().split("\\s+");
                        issuingStatus=arr[0];
                    }
                    //根据产品代码，启用日期，信披费用类型 查询出数据,有数据则更新,无数据则插入
                    map.put("prodId",prodId);
                    List<ChbPrdBaseinfo> chbPrdBaseinfos= chbPrdBaseinfo001Mapper.selectOneDate(map);

                    if("新增".equals(type) || "修改".equals(type)){
                        updateInfo(chbPrdBaseinfos,prodId, orgCode1, raiseVdate,raiseEnddate,vdate,
                                mdate,manageType,isConstracte,yieldHigh,yieldLow,yieldRemark,openMode,
                                openCircle,openCircleDays,ruleComment,firstOpenDay,holidayOnFlag,avgOpenCount,
                                openBuss,openBussRemark,busiAcctNo,acctName,prodtype,basic,fiduciaryDuty,
                                customerType,isTransfer,isFullyCompliant,isYllccp,isSyqzccp,issuingStatus,
                                deptCode,userName);
                    }

                    if("删除".equals(type)){
                        chbPrdBaseinfo001Mapper.deleteOne(prodId);
                        chbPrdBaseinfo001Mapper.deleteErrorDataOne(prodId);
                    }
                }
            }
        }else{
            List<ExcelValidDto> checkResult = new ArrayList<>();
            ExcelValidDto excelValidDto = new ExcelValidDto();
            String errorMsg = "模板为空，请输入数据！";
            excelValidDto.setErrorInfo(errorMsg);
            checkResult.add(excelValidDto);
            return checkResult;
        }
        return null;
    }

    private void addDisRepCashYield(String prodId,String orgCode1,Date raiseVdate,Date raiseEnddate,Date vdate,
                                    Date mdate,String manageType,String isConstracte,
                                    BigDecimal yieldHigh,BigDecimal yieldLow,
                                    String yieldRemark,String openMode,
                                    String openCircle,BigDecimal openCircleDays,String ruleComment,Date firstOpenDay,
                                    String holidayOnFlag,BigDecimal avgOpenCount,
                                    String openBuss,String openBussRemark,String busiAcctNo,String acctName,String prodtype,
                                    String basic,String fiduciaryDuty,
                                    String customerType,String isTransfer,String isFullyCompliant,String isYllccp,
                                    String isSyqzccp,String issuingStatus,
                                    String deptCode,String userName){
        ChbPrdBaseinfo entity=new ChbPrdBaseinfo();
        Date nowDate=new Date();
        entity.setDataSource("02");
        entity.setProduuid(IDGenerator.generate(36));
        entity.setProdId(prodId);
        entity.setOrgCode(orgCode1);
        entity.setRaiseVdate(raiseVdate);
        entity.setRaiseEnddate(raiseEnddate);
        entity.setVdate(vdate);
        entity.setMdate(mdate);
        entity.setManageType(manageType);
        entity.setIsConstracte(isConstracte);
        entity.setYieldHigh(yieldHigh);
        entity.setYieldLow(yieldLow);
        entity.setYieldRemark(yieldRemark);
        entity.setOpenMode(openMode);
        entity.setOpenCircle(openCircle);
        entity.setOpenCircleDays(openCircleDays);
        entity.setRuleComment(ruleComment);
        entity.setFirstOpenDay(firstOpenDay);
        entity.setHolidayOnFlag(holidayOnFlag);
        entity.setAvgOpenCount(avgOpenCount);
        entity.setOpenBuss(openBuss);
        entity.setOpenBussRemark(openBussRemark);
        entity.setBusiAcctNo(busiAcctNo);
        entity.setAcctName(acctName);
        entity.setProdtype(prodtype);
        entity.setBasic(basic);
        entity.setFiduciaryDuty(fiduciaryDuty);
        entity.setCustomerType(customerType);
        entity.setIsTransfer(isTransfer);
        entity.setIsFullyCompliant(isFullyCompliant);
        entity.setIsYllccp(isYllccp);
        entity.setIsSyqzccp(isSyqzccp);
        entity.setIssuingStatus(issuingStatus);
        entity.setCreateUser(userName);
        entity.setCreateTime(nowDate);
        entity.setUpdateUser(userName);
        entity.setUpdateTime(nowDate);
        entity.setStatus("E");
        chbPrdBaseinfo001Mapper.insertOne(entity);
    }

    private void updateInfo(List<ChbPrdBaseinfo> chbPrdBaseinfos,String prodId,String orgCode1,Date raiseVdate,Date raiseEnddate,Date vdate,
                            Date mdate,String manageType,String isConstracte,BigDecimal yieldHigh,BigDecimal yieldLow,String yieldRemark,String openMode,
                            String openCircle,BigDecimal openCircleDays,String ruleComment,Date firstOpenDay,String holidayOnFlag,BigDecimal avgOpenCount,
                            String openBuss,String openBussRemark,String busiAcctNo,String acctName,String prodtype,String basic,String fiduciaryDuty,
                            String customerType,String isTransfer,String isFullyCompliant,String isYllccp,String isSyqzccp,String issuingStatus,
                            String deptCode,String userName){
        Map<String, Object> mapOld=new HashMap<>();
        if(chbPrdBaseinfos.size()>0){
            if(!"".equals(chbPrdBaseinfos.get(0).getProdId())){
                mapOld.put("prodId",prodId);
                mapOld.put("orgCode1",orgCode1);
                mapOld.put("raiseVdate",raiseVdate);
                mapOld.put("raiseEnddate",raiseEnddate);
                mapOld.put("vdate",vdate);
                mapOld.put("mdate",mdate);
                mapOld.put("manageType",manageType);
                mapOld.put("isConstracte",isConstracte);
                mapOld.put("yieldHigh",yieldHigh);
                mapOld.put("yieldLow",yieldLow);
                mapOld.put("yieldRemark",yieldRemark);
                mapOld.put("openMode",openMode);
                mapOld.put("openCircle",openCircle);
                mapOld.put("openCircleDays",openCircleDays);
                mapOld.put("ruleComment",ruleComment);
                mapOld.put("firstOpenDay",firstOpenDay);
                mapOld.put("holidayOnFlag",holidayOnFlag);
                mapOld.put("avgOpenCount",avgOpenCount);
                mapOld.put("openBuss",openBuss);
                mapOld.put("openBussRemark",openBussRemark);
                mapOld.put("busiAcctNo",busiAcctNo);
                mapOld.put("acctName",acctName);
                mapOld.put("prodtype",prodtype);
                mapOld.put("basic",basic);
                mapOld.put("fiduciaryDuty",fiduciaryDuty);
                mapOld.put("customerType",customerType);
                mapOld.put("isTransfer",isTransfer);
                mapOld.put("isFullyCompliant",isFullyCompliant);
                mapOld.put("isYllccp",isYllccp);
                mapOld.put("isSyqzccp",isSyqzccp);
                mapOld.put("issuingStatus",issuingStatus);
                mapOld.put("status","E");
                mapOld.put("dataSource","02");
                mapOld.put("updateUser", LoginUserUtil.getUserName());
                mapOld.put("updateTime",new Date());
                chbPrdBaseinfo001Mapper.updateOne(mapOld);
            }
        }
        else{
            addDisRepCashYield(prodId, orgCode1, raiseVdate,raiseEnddate,vdate,
                    mdate,manageType,isConstracte,yieldHigh,yieldLow,yieldRemark,openMode,
                    openCircle,openCircleDays,ruleComment,firstOpenDay,holidayOnFlag,avgOpenCount,
                    openBuss,openBussRemark,busiAcctNo,acctName,prodtype,basic,fiduciaryDuty,
                    customerType,isTransfer,isFullyCompliant,isYllccp,isSyqzccp,issuingStatus,
                    deptCode,userName);
        }
    }


    private List<ExcelValidDto> convertToEntity(List<List<String>> dataList,List<DisRepCashYield> entities,String userName,String deptCode) throws ParseException {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        Date nowDate = new Date();
        for (int i = 0; i < dataList.size(); i++ ) {
            //是否正常标志
            boolean flag = true;
            List<String> list = dataList.get(i);
            // 验证消息
            List<String> message = new ArrayList<>();
            Map<String,Object> param = new HashMap<String,Object>();
            //操作类型
            String type ="";
            if(null!=dataList.get(i).get(0)){
                type = dataList.get(i).get(0).toString();
            }
            if("修改".equals(type)){

            }

            if(flag){
                DisRepCashYield entity = new DisRepCashYield();
                entity.setDataSource("02");
                entity.setBusiId(dataList.get(i).get(1).toString());
                SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
                Date date=new Date(dataList.get(i).get(2));
                entity.setStartDate(date);
                String [] arr = dataList.get(i).get(3).toString().split("\\s+");
                entity.setCashType2(arr[0]);
                entity.setCashName(arr[1]);
                BigDecimal bigDecimal=new BigDecimal(dataList.get(0).get(4).toString());
                entity.setRate(bigDecimal);
                entity.setCashId(IDGenerator.generate());
                entity.setAdapterId("无");
                entity.setCreateUser(userName);
                entity.setCreateDept(deptCode);
                entity.setCreateTime(nowDate);
                entity.setUpdateUser(userName);
                entity.setUpdateTime(nowDate);
                entity.setStatus("E");
                entities.add(entity);
            }

            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(list.get(1), String.valueOf(i+2),
                        StringUtil.joinString(message, GlobalConstant.SEMICOLON)));
            }
        }
        return checkResult;
    }

    public void updateOne(Map<String, Object> mapOld) {
        chbPrdBaseinfo001Mapper.updateOne(mapOld);
    }
}
