package com.joyintech.tams.report.system.disrelorg.service;

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
import com.joyintech.tams.report.system.disrelorg.dao.DisRelOrgInfo001Mapper;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateInfoErrodataWithBLOBs;
import com.joyintech.tams.svreport.interf.dis.institutiondata.dto.DisAssociatedInstitutions;
import com.joyintech.tams.svreport.interf.dis.prodcashyield.dto.DisRepCashYield;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DisRelOrgInfo001Service extends CommonExportService<DisAssociatedInstitutions> {

    @Autowired
    private DisRelOrgInfo001Mapper disRelOrgInfo001Mapper;

    @Autowired
    private ChbDataCheckCommonUtil chbDataCheckCommonUtil;
    /**
     * 当在做导入前的check就是判断业务数据是否需要覆盖的时候在webresult的data中塞入这个值可以不会弹框两次
     */
    private static final String NO_INFO_SHOWOUT = "noInfoShowOut";

    public Page<DisAssociatedInstitutions> searchForPage(Map<String, Object> param){
        Page<DisAssociatedInstitutions> page;
        int pagenum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
        String status = (String)param.get("status");
        List<String> selectStatus = StringUtil.splitToList(GlobalConstant.COMMA, status);
        param.put("selectStatus", selectStatus);
        page=  super.queryForPage(pagenum,legth,()->disRelOrgInfo001Mapper.queryForPage(param));

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
    private void dealDataList(List<DisAssociatedInstitutions> list) {
        list.forEach(item -> {
            item.setStatusName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.维护数据状态, item.getStatus()));
            item.setSvrDataSourcesName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.数据来源, item.getSvrDataSources()));
        });
    }

    public void updateDisAssociatedInstitutions(List<DisAssociatedInstitutions> dtoList) {
        disRelOrgInfo001Mapper.updateDisAssociatedInstitutions(dtoList);
        return;
    }

    public List<SysDataDict> getSysDataDict(Map<String, Object> param) {
        return disRelOrgInfo001Mapper.getSysDataDict( param);
    }

    public List<DisAssociatedInstitutions> queryReportList(Map<String, Object> param) {
        String relorgIds = (String)param.get("keys");
        List<String> selectrelorgIds = StringUtil.splitToList(GlobalConstant.COMMA, relorgIds);
        param.put("selectrelorgIds", selectrelorgIds);
        return disRelOrgInfo001Mapper.queryReportList( param);
    }

    public AjaxResult checkConfirmMsgForImportData(List<List<String>> dataList) {
        AjaxResult webResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++ ) {
            for (int j = i+1; j < dataList.size(); j++ ) {
                if(StringUtils.equals(dataList.get(i).get(1), dataList.get(j).get(1)) &&
                        StringUtils.equals(dataList.get(i).get(2), dataList.get(j).get(2))){
                    ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行关联机构代码、关联机构名称与"+"第"+(j+2)+"行关联机构代码、关联机构名称相同");
                    errorList.add(validMessage);
                }
            }
        }
        if(errorList.size() == 0){
            for (int i = 0; i < dataList.size(); i++ ) {
                //操作类型
                String type ="";
                if(null!=dataList.get(i).get(0)&& !"".equals(dataList.get(i).get(0))){
                    type = dataList.get(i).get(0).toString();
                }
                //关联机构代码
                String relorgId ="";
                if(null!=dataList.get(i).get(1)&& !"".equals(dataList.get(i).get(1))){
                    relorgId =dataList.get(i).get(1).toString();
                }
                //关联机构名称
                String organName ="";
                if(null!=dataList.get(i).get(2)&& !"".equals(dataList.get(i).get(2))){
                    organName=dataList.get(i).get(2).toString();
                }
                DisAssociatedInstitutions dis=new DisAssociatedInstitutions();
                dis.setRelorgId(relorgId);
                dis.setOrganName(organName);
                errorList = chbDataCheckCommonUtil.chbCheckCommon(errorList, dis, "DisRelOrg002", null, ValidMessage.class);
                List<DisAssociatedInstitutions> entitys=disRelOrgInfo001Mapper.selectOne(relorgId);
                if("新增".equals(type)){
                    if(null!=entitys && entitys.size()>0){
                        ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行已存在该关联机构代码的数据");
                        errorList.add(validMessage);
                    }
                }
                if("修改".equals(type) || "删除".equals(type)){
                    if(entitys.size()<=0){
                        ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行不存在该关联机构代码的数据,请核查");
                        errorList.add(validMessage);
                    }
                }
            }
        }
        if(errorList.size() == 0){
            webResult.setData(NO_INFO_SHOWOUT);
        }else{
            webResult.setErrorList(errorList);
        }
        return webResult;
    }

    public List<DisAssociatedInstitutions> selectOne(String relorgId) {
        return disRelOrgInfo001Mapper.selectOne(relorgId);
    }

    public List<ExcelValidDto> importData(List<List<String>> dataList, List<List<String>> tempList,String userName, String orgCode, String deptCode) throws ParseException {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        if (!StringUtil.isEmptyList(dataList)) {
            List<String> headList = new ArrayList<String>();
            headList.add("操作类型");
            headList.add("关联机构代码");
            headList.add("关联机构名称");
            List<ExcelValidDto> tempCheckResult = ExcelUtil.checkExcelTemp(tempList, headList);
            if (!StringUtil.isEmptyList(tempCheckResult)) {
                return tempCheckResult;
            }
            if (null != dataList) {
                List<DisAssociatedInstitutions> entities = new ArrayList<DisAssociatedInstitutions>();
                Map<String,Object> map=new HashMap<>();
                for (int i = 0; i < dataList.size(); i++ ) {
                    List<String> message = new ArrayList<>();
                    //操作类型
                    String type ="";
                    if(null!=dataList.get(i).get(0)&& !"".equals(dataList.get(i).get(0))){
                        type = dataList.get(i).get(0).toString();
                    }
                    //关联机构代码
                    String relorgId ="";
                    if(null!=dataList.get(i).get(1)&& !"".equals(dataList.get(i).get(1))){
                        relorgId =dataList.get(i).get(1).toString();
                    }
                    //关联机构名称
                    String organName ="";
                    if(null!=dataList.get(i).get(2)&& !"".equals(dataList.get(i).get(2))){
                        organName=dataList.get(i).get(2).toString();
                    }
                    if("新增".equals(type)){
                        addDisAssociatedInstitutions(relorgId,userName,organName);
                    }else if("修改".equals(type)){
                        //根据关联机构代码查询出数据,有数据则更新,无数据则插入
                        List<DisAssociatedInstitutions> entitys=disRelOrgInfo001Mapper.selectOne(relorgId);
                        if(null!=entitys.get(0) && !"".equals(entitys.get(0).getRelorgId())){
                            DisAssociatedInstitutions entity=entitys.get(0);
                            entity.setOrganName(organName);
                            entity.setStatus("E");
                            entity.setSvrDataSources("02");
                            entity.setUpdateteUser(LoginUserUtil.getUserName());
                            entity.setUpdateTime(new Date());
                            disRelOrgInfo001Mapper.updateOne(entity);
                        }else{
                            addDisAssociatedInstitutions(relorgId,userName,organName);
                        }
                    }else if("删除".equals(type)){
                        disRelOrgInfo001Mapper.updateOneStatus(relorgId);
                    }else {
                        message.add("操作类型值不在可选范围内");
                    }
                    if (!message.isEmpty()) {
                        checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1),
                                StringUtil.joinString(message, GlobalConstant.COMMA)));
                    }
                }
            }
        }else{
            ExcelValidDto excelValidDto = new ExcelValidDto();
            String errorMsg = "模板为空，请输入数据！";
            excelValidDto.setErrorInfo(errorMsg);
            checkResult.add(excelValidDto);
        }
        return checkResult;
    }

    private void addDisAssociatedInstitutions(String relorgId,String userName,String organName){
        DisAssociatedInstitutions entity = new DisAssociatedInstitutions();
        Date nowDate = new Date();
        entity.setRelorgId(relorgId);
        entity.setCreateUser(userName);
        entity.setCreateTime(nowDate);
        entity.setUpdateUser(userName);
        entity.setUpdateTime(nowDate);
        entity.setOrganName(organName);
        entity.setStatus("E");
        entity.setSvrDataSources("02");
        disRelOrgInfo001Mapper.insertOne(entity);
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

            if(flag){

            }
            if (CollectionUtils.isNotEmpty(message)) {
                checkResult.add(new ExcelValidDto(list.get(1), String.valueOf(i+2),
                        StringUtil.joinString(message, GlobalConstant.SEMICOLON)));
            }
        }
        return checkResult;
    }

    public void insertOne(DisAssociatedInstitutions disAssociatedInstitutions) {
        disRelOrgInfo001Mapper.insertOne(disAssociatedInstitutions);
    }

    public void updateOne(DisAssociatedInstitutions disAssociatedInstitutions) {
        disRelOrgInfo001Mapper.updateOne(disAssociatedInstitutions);
    }
}
