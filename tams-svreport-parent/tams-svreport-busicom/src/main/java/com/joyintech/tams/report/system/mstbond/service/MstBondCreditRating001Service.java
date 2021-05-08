package com.joyintech.tams.report.system.mstbond.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.system.chbmst.dto.ChbMstCounterPartyErrodataWithBLOBs;
import com.joyintech.tams.report.system.mstbond.dao.MstBondCreditRating001Mapper;
import com.joyintech.tams.report.system.prorate.dto.DisPrdFeeRateInfoErrodataWithBLOBs;
import com.joyintech.tams.svreport.interf.dis.prodcashyield.dto.DisRepCashYield;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MstBondCreditRating001Service extends CommonExportService<MstBondCreditRating> {

    @Autowired
    private MstBondCreditRating001Mapper mstBondCreditRating001Mapper;
    /**
     * 当在做导入前的check就是判断业务数据是否需要覆盖的时候在webresult的data中塞入这个值可以不会弹框两次
     */
    private static final String NO_INFO_SHOWOUT = "noInfoShowOut";

    public Page<MstBondCreditRating> searchForPage(Map<String, Object> param){
        Page<MstBondCreditRating> page;
        int pagenum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
        String oCode = (String)param.get("oCode");
        String oType = (String)param.get("oType");
        String status = (String)param.get("status");
        List<String> selectoCode = StringUtil.splitToList(GlobalConstant.COMMA, oCode);
        List<String> selectoType = StringUtil.splitToList(GlobalConstant.COMMA, oType);
        List<String> selectStatus = StringUtil.splitToList(GlobalConstant.COMMA, status);
        param.put("selectoCode", selectoCode);
        param.put("selectoType", selectoType);
        param.put("selectStatus", selectStatus);
        page=  super.queryForPage(pagenum,legth,()->mstBondCreditRating001Mapper.queryForPage(param));
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
    private void dealDataList(List<MstBondCreditRating> list) {
        list.forEach(item -> {
            item.setoTypeName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.评级类型, item.getoType() ));
            item.setStatusName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.维护数据状态, item.getStatus()));
            item.setSvrDataSourcesName (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.数据来源, item.getSvrDataSources()));
            item.setGradeOrgIdName (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.评级机构, item.getGradeOrgId()));
            item.setGradeResultName (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.主体评级_中债, item.getGradeResult()));
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

    public List<ChbAssetInfo> getoCode(Map<String, Object> param) {
        return mstBondCreditRating001Mapper.getoCode( param);
    }
    public List<SysDataDict> getSysDataDict(Map<String, Object> param) {
        return mstBondCreditRating001Mapper.getSysDataDict( param);
    }

    public List<Integer> selectChbMstCounterPartyData(List<ChbMstCounterParty> dtoList) {
        return mstBondCreditRating001Mapper.selectChbMstCounterPartyData(dtoList);
    }

    public void insertMstBondCreditRatingData(List<MstBondCreditRating> dtoList) {
         mstBondCreditRating001Mapper.insertMstBondCreditRatingData(dtoList);
        return;
    }

    public void updateMstBondCreditRating(List<MstBondCreditRating> dtoList) {
        mstBondCreditRating001Mapper.updateMstBondCreditRating(dtoList);
        return;
    }



    public List<MstBondCreditRating> queryReportList(Map<String, Object> param) {
        String keys = (String)param.get("keys");
        String[] strs = keys.split(" ,");
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
        List<MstBondCreditRating> dtoList=new ArrayList<>();
        for(int i=0;i<strs.length;i++){
            String arr =strs[i];
            String[] s =arr.split(",");
            String oCode=s[0];
            String gradeOrgId=s[1];
            String oType=s[3];
            MstBondCreditRating ms=new MstBondCreditRating();
            ms.setoCode(oCode);
            ms.setGradeOrgId(gradeOrgId);
            ms.setGradeDate(DateUtil.parse(dfs.format(Long.parseLong(String.valueOf(s[2])))));
            ms.setoType(oType);
            dtoList.add(ms);
        }
        /*List<String> selectkeys = StringUtil.splitToList(GlobalConstant.COMMA, keys);
        param.put("selectkeys", selectkeys);*/

        return mstBondCreditRating001Mapper.queryReportList(dtoList);
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
            headList.add("债券代码");
            headList.add("评级机构");
            headList.add("评级日期");
            headList.add("评级类型");
            headList.add("评级结果");
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
                    //债券代码
                    String oCode ="";
                    if(null!=dataList.get(i).get(1)){
                        oCode =dataList.get(i).get(1).toString();
                    }
                    //评级机构
                    String gradeOrgId ="";
                    if(null!=dataList.get(i).get(2)){
                        String [] arr =dataList.get(i).get(2).toString().split("\\s+");
                        gradeOrgId=arr[0];
                    }
                    //评级日期
                    SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
                    Date gradeDate=null;
                    if(null!=dataList.get(i).get(3)){
                        gradeDate=new Date(dataList.get(i).get(3));
                    }
                    //评级类型
                    String oType ="";
                    if(null!=dataList.get(i).get(4)){
                        String [] arr = dataList.get(i).get(4).toString().split("\\s+");
                        oType=arr[0];
                    }
                    //评级结果
                    String gradeResult ="";
                    if(null!=dataList.get(i).get(5)){
                        String [] arr = dataList.get(i).get(5).toString().split("\\s+");
                        gradeResult=arr[0];
                    }
                    //根据债券代码，评级机构，评级日期 ,评级类型 查询出数据,有数据则更新,无数据则插入
                    map.put("oCode",oCode);
                    map.put("gradeOrgId",gradeOrgId);
                    map.put("gradeDate",gradeDate);
                    map.put("oType",oType);
                    //map.put("gradeResult",gradeResult);
                    List<MstBondCreditRating> mstBondCreditRatings= mstBondCreditRating001Mapper.selectOneDate(map);

                    if("新增".equals(type) || "修改".equals(type)){
                        updateInfo(mstBondCreditRatings,oCode, gradeOrgId,gradeDate,oType,gradeResult,deptCode,userName);
                    }
                    if("删除".equals(type)){
                        mstBondCreditRating001Mapper.deleteOne(map);
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


    private void updateInfo(List<MstBondCreditRating> mstBondCreditRatings,String oCode,String gradeOrgId,Date gradeDate,String oType,String gradeResult,String deptCode,String userName){
        Map<String, Object> param =new HashMap<>();
        if(mstBondCreditRatings.size()>0){
            if(!"".equals(mstBondCreditRatings.get(0).getoCode())){
                MstBondCreditRating mstBondCreditRating=mstBondCreditRatings.get(0);
                //下方为数据库已有数据
                param.put("oCode",mstBondCreditRating.getoCode());
                param.put("oldGradeOrgId",mstBondCreditRating.getGradeOrgId());
                param.put("oldGradeDate",mstBondCreditRating.getGradeDate());
                param.put("oldOType",mstBondCreditRating.getoType());
                //下方为excel数据来源
                param.put("gradeOrgId",gradeOrgId);
                param.put("gradeDate",gradeDate);
                param.put("oType",oType);
                param.put("gradeResult",gradeResult);
                //下方为固定值
                param.put("status","E");
                param.put("svrDataSources","02");
                mstBondCreditRating001Mapper.updateOne(param);
            }
        }
        else{
            addMstBondCreditRating(oCode, gradeOrgId, gradeDate,oType,gradeResult,deptCode,userName);
        }
    }

    private void addMstBondCreditRating(String oCode,String gradeOrgId,Date gradeDate,String oType,String gradeResult,String deptCode,String userName){
        MstBondCreditRating entity=new MstBondCreditRating();
        Date nowDate=new Date();
        entity.setoCode(oCode);
        entity.setGradeOrgId(gradeOrgId);
        entity.setGradeDate(gradeDate);
        entity.setoType(oType);
        entity.setGradeResult(gradeResult);
        entity.setCreateUser(userName);
        entity.setCreateDept(deptCode);
        entity.setCreateTime(nowDate);
        entity.setUpdateUser(userName);
        entity.setUpdateTime(nowDate);
        entity.setSvrDataSources("02");
        entity.setStatus("E");
        mstBondCreditRating001Mapper.insertOne(entity);
    }

    public List<MstBondCreditRating> selectOneDate(Map<String, Object> param) {
        return mstBondCreditRating001Mapper.selectOneDate( param);
    }

    public List<ChbMstCounterPartyErrodataWithBLOBs> selectChbMstCounterPartyErrordata(String datauuid) {
        return mstBondCreditRating001Mapper.selectChbMstCounterPartyErrordata( datauuid);
    }

    public void updateOne(Map<String, Object> param) {
        mstBondCreditRating001Mapper.updateOne(param);
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
