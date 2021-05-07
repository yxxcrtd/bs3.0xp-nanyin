package com.joyintech.tams.report.system.chbmst.service;

import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.system.chbmst.dao.ChbMstCounterParty001Mapper;
import com.joyintech.tams.report.system.chbmst.dto.ChbMstCounterPartyDto;
import com.joyintech.tams.report.system.chbmst.dto.ChbMstCounterPartyErrodataWithBLOBs;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateInfoErrodataWithBLOBs;
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
public class ChbMstCounterParty001Service extends CommonExportService<ChbMstCounterParty> {

    @Autowired
    private ChbMstCounterParty001Mapper chbMstCounterParty001Mapper;
    @Autowired
    private ChbDataCheckCommonUtil chbDataCheckCommonUtil;
    /**
     * 当在做导入前的check就是判断业务数据是否需要覆盖的时候在webresult的data中塞入这个值可以不会弹框两次
     */
    private static final String NO_INFO_SHOWOUT = "noInfoShowOut";

    public Page<ChbMstCounterParty> searchForPage(Map<String, Object> param){
        Page<ChbMstCounterParty> page;
        int pagenum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
        String counterId = (String)param.get("counterId");
        String counterType2 = (String)param.get("counterType2");
        String status = (String)param.get("status");
        List<String> selectcounterId = StringUtil.splitToList(GlobalConstant.COMMA, counterId);
        List<String> selectcounterType2 = StringUtil.splitToList(GlobalConstant.COMMA, counterType2);
        List<String> selectStatus = StringUtil.splitToList(GlobalConstant.COMMA, status);
        param.put("selectcounterId", selectcounterId);
        param.put("selectcounterType2", selectcounterType2);
        param.put("selectStatus", selectStatus);
        page=  super.queryForPage(pagenum,legth,()->chbMstCounterParty001Mapper.queryForPage(param));
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
    private void dealDataList(List<ChbMstCounterParty> list) {
        list.forEach(item -> {
            item.setCounterType1Name (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.客户一级类型, item.getCounterType1()));
            item.setCounterType2Name (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.客户二级类型, item.getCounterType2()));
            item.setStatusName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.维护数据状态, item.getStatus()));
            item.setDataSourceName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.数据来源, item.getDataSource()));
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

    public List<ChbMstCounterParty> getCounterId(Map<String, Object> param) {
        return chbMstCounterParty001Mapper.getCounterId( param);
    }
    public List<SysDataDict> getSysDataDict(Map<String, Object> param) {
        return chbMstCounterParty001Mapper.getSysDataDict( param);
    }

    public List<Integer> selectChbMstCounterPartyData(List<ChbMstCounterParty> dtoList) {
        return chbMstCounterParty001Mapper.selectChbMstCounterPartyData(dtoList);
    }

    public void insertChbMstCounterPartyData(List<ChbMstCounterParty> dtoList) {
         chbMstCounterParty001Mapper.insertChbMstCounterPartyData(dtoList);
        return;
    }

    public void updateChbMstCounterParty(List<ChbMstCounterParty> dtoList) {
        //recoverDataList(dtoList);
        chbMstCounterParty001Mapper.updateChbMstCounterParty(dtoList);
        return;
    }

    public void deleteChbMstCounterPartyErrorData(List<ChbMstCounterParty> dtoList) {
        chbMstCounterParty001Mapper.deleteChbMstCounterPartyErrorData(dtoList);
        return;
    }

    public List<ChbMstCounterParty> queryReportList(Map<String, Object> param) {
        String keys = (String)param.get("keys");
        List<String> selectkeys = StringUtil.splitToList(GlobalConstant.COMMA, keys);
        param.put("selectkeys", selectkeys);
        return chbMstCounterParty001Mapper.queryReportList( param);
    }

    public AjaxResult checkConfirmMsgForImportData(List<List<String>> dataList) {
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();


        AjaxResult webResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        ChbMstCounterPartyDto chbMstCounterParty=new ChbMstCounterPartyDto();
        for (int i = 0; i < dataList.size(); i++ ) {
            //交易对手代码
            String counterId ="";
            if(null!=dataList.get(i).get(1)){
                counterId =dataList.get(i).get(1).toString();
            }
            //交易对手名称
            String counterName ="";
            if(null!=dataList.get(i).get(2)){
                counterName=dataList.get(i).get(2).toString();
            }
            //交易对手二级类型
            String counterType2 ="";
            if(null!=dataList.get(i).get(3)){
                String [] arr = dataList.get(i).get(3).toString().split("\\s+");
                counterType2=arr[0];
            }
            chbMstCounterParty.setCounterId(counterId);
            chbMstCounterParty.setCounterName(counterName);
            chbMstCounterParty.setCounterType2(counterType2);
            resultList = chbDataCheckCommonUtil.chbCheckCommon(resultList, chbMstCounterParty, "ChbMstCounterParty002", "", ValidMessage.class);
        }
        errorList=resultList;
        //新建时,判断excel表中是否存在重复产品,启用日期,信披费用类型的数据
        //更新删除时,查询数据不存在则提示 不存在数据请核查
        /*for (int i = 0; i < dataList.size(); i++ ) {
            for (int j = i+1; j < dataList.size(); j++ ) {
                if(StringUtils.equals(dataList.get(i).get(1), dataList.get(j).get(1)) &&
                        StringUtils.equals(dataList.get(i).get(4), dataList.get(j).get(4))){
                    ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行利率名称、生效日期与"+"第"+(j+2)+"行利率名称、生效日期相同");
                    errorList.add(validMessage);
                }
            }
        }*/

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
            headList.add("交易对手代码");
            headList.add("交易对手名称");
            headList.add("交易对手二级类型");
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
                    //交易对手代码
                    String counterId ="";
                    if(null!=dataList.get(i).get(1)){
                        counterId =dataList.get(i).get(1).toString();
                    }
                    //交易对手名称
                    String counterName ="";
                    if(null!=dataList.get(i).get(2)){
                        counterName=dataList.get(i).get(2).toString();
                    }
                    //交易对手二级类型
                    String counterType2 ="";
                    //String counterType2Name ="";
                    if(null!=dataList.get(i).get(3)){
                        String [] arr = dataList.get(i).get(3).toString().split("\\s+");
                        counterType2=arr[0];
                        //counterType2Name=arr[1];
                    }
                    //根据产品代码，启用日期，信披费用类型 查询出数据,有数据则更新,无数据则插入
                    map.put("counterId",counterId);
                    List<ChbMstCounterParty> ChbMstCounterPartys= chbMstCounterParty001Mapper.selectOneDate(map);

                    if("新增".equals(type) || "修改".equals(type)){
                        updateInfo(ChbMstCounterPartys,counterId, counterName, counterType2,deptCode,userName);
                    }
                    if("删除".equals(type)){
                        chbMstCounterParty001Mapper.deleteOne(counterId);
                        chbMstCounterParty001Mapper.deleteErrorDataOne(counterId);
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

    private void addDisRepCashYield(String counterId,String counterName,String counterType2,String deptCode,String userName){
        ChbMstCounterParty entity=new ChbMstCounterParty();
        Date nowDate=new Date();
        entity.setCounterId(counterId);
        entity.setCounterName(counterName);
        entity.setCreateUser(userName);
        entity.setCreateDept(deptCode);
        entity.setCreateTime(nowDate);
        entity.setUpdateUser(userName);
        entity.setUpdateTime(nowDate);
        entity.setDataSource("02");
        entity.setCounterType2(counterType2);
        entity.setStatus("E");
        chbMstCounterParty001Mapper.insertOne(entity);
    }

    private void updateInfo(List<ChbMstCounterParty> chbMstCounterPartys,String counterId,String counterName,String counterType2,String deptCode,String userName){
        if(chbMstCounterPartys.size()>0){
            if(!"".equals(chbMstCounterPartys.get(0).getCounterId())){
                ChbMstCounterParty chbMstCounterParty=chbMstCounterPartys.get(0);
                chbMstCounterParty.setCounterName(counterName);
                chbMstCounterParty.setCounterType2(counterType2);
                chbMstCounterParty.setStatus("E");
                chbMstCounterParty.setDataSource("02");
                chbMstCounterParty001Mapper.updateOne(chbMstCounterParty);
            }
        }
        else{
            addDisRepCashYield(counterId, counterName, counterType2,deptCode,userName);
        }
    }

    public List<ChbMstCounterParty> selectOneDate(Map<String, Object> param) {
        return chbMstCounterParty001Mapper.selectOneDate( param);
    }

    public List<ChbMstCounterPartyErrodataWithBLOBs> selectChbMstCounterPartyErrordata(String datauuid) {
        return chbMstCounterParty001Mapper.selectChbMstCounterPartyErrordata( datauuid);
    }

    public void updateOne(ChbMstCounterParty chb) {
        chbMstCounterParty001Mapper.updateOne(chb);
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
