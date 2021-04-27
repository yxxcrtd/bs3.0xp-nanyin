package com.joyintech.tams.report.system.prorate.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.common.base.entity.MstIntRateInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.system.prorate.dao.ProRateInfo001Mapper;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateInfoErrodataWithBLOBs;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import com.joyintech.tams.svreport.interf.dis.prodcashyield.dto.DisRepCashYield;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProRateInfo001Service extends CommonExportService<DisRepCashYield> {

    @Autowired
    private ProRateInfo001Mapper proRateInfo001Mapper;
    /**
     * 当在做导入前的check就是判断业务数据是否需要覆盖的时候在webresult的data中塞入这个值可以不会弹框两次
     */
    private static final String NO_INFO_SHOWOUT = "noInfoShowOut";

    public Page<DisRepCashYield> searchForPage(Map<String, Object> param){
        Page<DisRepCashYield> page;
        int pagenum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
        String busiId = (String)param.get("busiId");
        String cashType2 = (String)param.get("cashType2");
        String status = (String)param.get("status");
        List<String> selectbusiId = StringUtil.splitToList(GlobalConstant.COMMA, busiId);
        List<String> selectcashType2 = StringUtil.splitToList(GlobalConstant.COMMA, cashType2);
        List<String> selectStatus = StringUtil.splitToList(GlobalConstant.COMMA, status);
        param.put("selectbusiId", selectbusiId);
        param.put("selectcashType2", selectcashType2);
        param.put("selectStatus", selectStatus);
        page=  super.queryForPage(pagenum,legth,()->proRateInfo001Mapper.queryForPage(param));
        List<ChbPrdBaseinfo> list =proRateInfo001Mapper.getProdinfo(param);
        Map<String,String> map=new HashMap<String,String>();
        if(null!=list && list.size()>0){
            for(ChbPrdBaseinfo chbPrdBaseinfo:list){
                map.put(chbPrdBaseinfo.getProdId(),chbPrdBaseinfo.getProdName());
            }
        }
        dealDataList(page.getRoot(),map);
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
    private void dealDataList(List<DisRepCashYield> list,Map<String,String> map) {
        list.forEach(item -> {
            item.setCashType2Name(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.信披费用类型, item.getCashType2()));
            item.setStatusName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.维护数据状态, item.getStatus()));
            item.setDataSourceName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.数据来源, item.getDataSource()));
            item.setBusiIdName(map.get(item.getBusiId()));
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
        return proRateInfo001Mapper.getProdinfo( param);
    }
    public List<SysDataDict> getSysDataDict(Map<String, Object> param) {
        return proRateInfo001Mapper.getSysDataDict( param);
    }

    public List<Integer> selectPrdFeeData(List<DisRepCashYield> dtoList) {
        return proRateInfo001Mapper.selectPrdFeeData(dtoList);
    }

    public void insertPrdFeeData(List<DisRepCashYield> dtoList) {
         proRateInfo001Mapper.insertPrdFeeData(dtoList);
        return;
    }

    public void updateDisRepCashYield(List<DisRepCashYield> dtoList) {
        //recoverDataList(dtoList);
        proRateInfo001Mapper.updateDisRepCashYield(dtoList);
        return;
    }

    public void deleteDisRepCashYieldErrorData(List<DisRepCashYield> dtoList) {
        proRateInfo001Mapper.deleteDisRepCashYieldErrorData(dtoList);
        return;
    }

    public List<DisRepCashYield> queryReportList(Map<String, Object> param) {
        return proRateInfo001Mapper.queryReportList( param);
    }

    public List<DisRepCashYield> selectOneDate(Map<String, Object> param) {
        return proRateInfo001Mapper.selectOneDate( param);
    }

    public List<DisPrdFeeRateInfoErrodataWithBLOBs> selectdisPrdFeeRateErrordata(String datauuid) {
        return proRateInfo001Mapper.selectdisPrdFeeRateErrordata( datauuid);
    }

    public AjaxResult checkConfirmMsgForImportData(List<List<String>> dataList) {
        AjaxResult webResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        //新建时,判断excel表中是否存在重复产品,启用日期,信披费用类型的数据
        //更新删除时,查询数据不存在则提示 不存在数据请核查
        for (int i = 0; i < dataList.size(); i++ ) {
            for (int j = i+1; j < dataList.size(); j++ ) {
                if(StringUtils.equals(dataList.get(i).get(1), dataList.get(j).get(1)) &&
                        StringUtils.equals(dataList.get(i).get(4), dataList.get(j).get(4))){
                    ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行利率名称、生效日期与"+"第"+(j+2)+"行利率名称、生效日期相同");
                    errorList.add(validMessage);
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

    public List<ExcelValidDto> importData(List<List<String>> dataList, List<List<String>> tempList,String userName, String orgCode, String deptCode) throws ParseException {
        if (!StringUtil.isEmptyList(dataList)) {
            List<String> headList = new ArrayList<String>();
            headList.add("操作类型");
            headList.add("产品代码");
            headList.add("启用日期");
            headList.add("信披费用类型");
            headList.add("费率");
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
                    if(null!=dataList.get(i).get(0)){
                        type = dataList.get(i).get(0).toString();
                    }
                    //产品代码
                    String busiId ="";
                    if(null!=dataList.get(i).get(1)){
                        busiId =dataList.get(i).get(1).toString();
                    }
                    //启用日期
                    SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
                    Date date=null;
                    if(null!=dataList.get(i).get(2)){
                        date=new Date(dataList.get(i).get(2));
                    }
                    //信披费用类型
                    String cashType2 ="";
                    String cashType2Name ="";
                    if(null!=dataList.get(i).get(3)){
                        String [] arr = dataList.get(i).get(3).toString().split("\\s+");
                        cashType2=arr[0];
                        cashType2Name=arr[1];
                    }
                    //费率
                    BigDecimal bigDecimal=null;
                    if(null!=dataList.get(i).get(4)){
                        bigDecimal=new BigDecimal(dataList.get(0).get(4).toString());
                    }
                    //根据产品代码，启用日期，信披费用类型 查询出数据,有数据则更新,无数据则插入
                    map.put("busiId",busiId);
                    map.put("startDate",date);
                    map.put("cashType2",cashType2);
                    List<DisRepCashYield> disRepCashYields= proRateInfo001Mapper.selectOneDate(map);

                    if("新增".equals(type) || "修改".equals(type)){
                        updateInfo(disRepCashYields,busiId, date, cashType2,cashType2Name,bigDecimal,deptCode,userName);
                    }
                    /*if("修改".equals(type)){
                        updateInfo(disRepCashYields,busiId, date, cashType2, cashType2Name,bigDecimal,deptCode,userName);
                    }*/
                    if("删除".equals(type)){
                        proRateInfo001Mapper.deleteOne(busiId,date,cashType2);
                        proRateInfo001Mapper.deleteErrorDataOne(busiId,date,cashType2);
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

    private void addDisRepCashYield(String busiId,Date date,String cashType2,String cashType2Name,BigDecimal bigDecimal,String deptCode,String userName){
        DisRepCashYield entity=new DisRepCashYield();
        Date nowDate=new Date();
        entity.setDataSource("02");
        entity.setBusiId(busiId);
        entity.setStartDate(date);
        entity.setCashType2(cashType2);
        entity.setCashName(cashType2Name);
        entity.setRate(bigDecimal);
        entity.setCashId(IDGenerator.generate());
        entity.setAdapterId("无");
        entity.setCreateUser(userName);
        entity.setCreateDept(deptCode);
        entity.setCreateTime(nowDate);
        entity.setUpdateUser(userName);
        entity.setUpdateTime(nowDate);
        entity.setStatus("E");
        proRateInfo001Mapper.insertOne(entity);
    }

    private void updateInfo(List<DisRepCashYield> disRepCashYields,String busiId,Date date,String cashType2,String cashType2Name,BigDecimal bigDecimal,String deptCode,String userName){
        if(disRepCashYields.size()>0){
            if(!"".equals(disRepCashYields.get(0).getBusiId())){
                DisRepCashYield disRepCashYield=disRepCashYields.get(0);
                disRepCashYield.setRate(bigDecimal);
                proRateInfo001Mapper.updateOne(disRepCashYield);
            }
        }
        else{
            addDisRepCashYield( busiId, date, cashType2, cashType2Name,bigDecimal,deptCode,userName);
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
}
