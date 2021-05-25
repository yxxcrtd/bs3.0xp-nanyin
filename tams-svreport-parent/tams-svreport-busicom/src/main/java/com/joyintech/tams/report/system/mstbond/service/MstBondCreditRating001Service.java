package com.joyintech.tams.report.system.mstbond.service;

import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.BeanUtil;
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
import com.joyintech.tams.report.system.mstbond.dto.MstBondCreditRatingDto;
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
public class MstBondCreditRating001Service extends CommonExportService<MstBondCreditRating> {

    @Autowired
    private MstBondCreditRating001Mapper mstBondCreditRating001Mapper;

    @Autowired
    private ChbDataCheckCommonUtil chbDataCheckCommonUtil;
    /**
     * 当在做导入前的check就是判断业务数据是否需要覆盖的时候在webresult的data中塞入这个值可以不会弹框两次
     */
    private static final String NO_INFO_SHOWOUT = "noInfoShowOut";

    public Page<MstBondCreditRating> searchForPage(Map<String, Object> param){
        Page<MstBondCreditRating> page;
        int pagenum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
        dealMap(param);
        page=  super.queryForPage(pagenum,legth,()->mstBondCreditRating001Mapper.queryForPage(param));
        dealDataList(page.getRoot());
        return page;
    }

    /**
     * 处理参数
     * @param param
     */
    private void dealMap(Map<String, Object> param) {
        String oCode = (String)param.get("oCode");
        String oType = (String)param.get("oType");
        String status = (String)param.get("status");
        List<String> selectoCode = StringUtil.splitToList(GlobalConstant.COMMA, oCode);
        List<String> selectoType = StringUtil.splitToList(GlobalConstant.COMMA, oType);
        List<String> selectStatus = StringUtil.splitToList(GlobalConstant.COMMA, status);
        param.put("selectoCode", selectoCode);
        param.put("selectoType", selectoType);
        param.put("selectStatus", selectStatus);
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
            item.setoTypeName(SysDataUtil.getZZDictCodeAndName(
                    DictCode.评级类型, item.getoType() ));
            item.setStatusName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.维护数据状态, item.getStatus2()));
            item.setSvrDataSourcesName (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.数据来源, item.getSvrDataSources()));
            item.setGradeOrgIdName (SysDataUtil.getZZDictCodeAndName(
                    DictCode.评级机构, item.getGradeOrgId()));
            item.setGradeResultName (SysDataUtil.getZZDictCodeAndName(
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

    /**
     * 查询导出数据
     * @param param
     */
    public List<MstBondCreditRating> queryReportList(Map<String, Object> param) {
//        String keys = (String)param.get("keys");
//        String[] strs = keys.split(" ,");
//        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
//        List<MstBondCreditRating> dtoList=new ArrayList<>();
//        for(int i=0;i<strs.length;i++){
//            String arr =strs[i];
//            String[] s =arr.split(",");
//            String oCode=s[0];
//            String gradeOrgId=s[1];
//            String oType=s[3];
//            MstBondCreditRating ms=new MstBondCreditRating();
//            ms.setoCode(oCode);
//            ms.setGradeOrgId(gradeOrgId);
//            ms.setGradeDate(DateUtil.parse(dfs.format(Long.parseLong(String.valueOf(s[2])))));
//            ms.setoType(oType);
//            dtoList.add(ms);
//        }
//        /*List<String> selectkeys = StringUtil.splitToList(GlobalConstant.COMMA, keys);
//        param.put("selectkeys", selectkeys);*/
//
//        return mstBondCreditRating001Mapper.queryReportList(dtoList);
        dealMap(param);
        List<MstBondCreditRating> list = mstBondCreditRating001Mapper.queryForPage(param);
        dealDataList(list);
        return list;
    }

    public AjaxResult checkConfirmMsgForImportData(List<List<String>> dataList) throws ParseException {
        AjaxResult webResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            for (int j = i+1; j < dataList.size(); j++ ) {
                if(StringUtils.equals(dataList.get(i).get(1), dataList.get(j).get(1)) &&
                        StringUtils.equals(dataList.get(i).get(2), dataList.get(j).get(2))&&
                        StringUtils.equals(dataList.get(i).get(3), dataList.get(j).get(3))&&
                        StringUtils.equals(dataList.get(i).get(4), dataList.get(j).get(4))&&
                        StringUtils.equals(dataList.get(i).get(5), dataList.get(j).get(5))){
                    ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行债券代码、评级机构、评级日期、评级类型、评级结果与"+"第"+(j+2)+"行债券代码、评级机构、评级日期、评级类型、评级结果相同");
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
                //债券代码
                String oCode ="";
                if(null!=dataList.get(i).get(1) && !"".equals(dataList.get(i).get(1))){
                    oCode =dataList.get(i).get(1).toString();
                }
                //评级机构
                String gradeOrgId ="";
                if(null!=dataList.get(i).get(2) && !"".equals(dataList.get(i).get(2))){
                    String [] arr =dataList.get(i).get(2).toString().split("\\s+");
                    gradeOrgId=arr[0];
                }
                //评级日期
                SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
                Date gradeDate=null;
                if(null!=dataList.get(i).get(3) && !"".equals(dataList.get(i).get(3))){
                    if(dataList.get(i).get(3).contains("-")){
                        gradeDate=dfs.parse(dataList.get(i).get(3));
                    }else{
                        gradeDate=new Date(dataList.get(i).get(3));
                    }
                }
                //评级类型
                String oType ="";
                if(null!=dataList.get(i).get(4) && !"".equals(dataList.get(i).get(4))){
                    String [] arr = dataList.get(i).get(4).toString().split("\\s+");
                    oType=arr[0];
                }
                //评级结果
                String gradeResult ="";
                if(null!=dataList.get(i).get(5) && !"".equals(dataList.get(i).get(5))){
                    String [] arr = dataList.get(i).get(5).toString().split("\\s+");
                    gradeResult=arr[0];
                }
                //根据债券代码，评级机构，评级日期 ,评级类型 查询出数据,有数据则更新,无数据则插入
                map.put("oCode",oCode);
                map.put("gradeOrgId",gradeOrgId);
                map.put("gradeDate",gradeDate);
                map.put("oType",oType);
                List<MstBondCreditRating> mstBondCreditRatings= mstBondCreditRating001Mapper.selectOneDate(map);
                if("新增".equals(type)){
                    if(null!=mstBondCreditRatings && mstBondCreditRatings.size()>0){
                        ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行已存在重复债券代码、评级机构、评级日期、评级类型的数据");
                        errorList.add(validMessage);
                    }
                }
                if("修改".equals(type) || "删除".equals(type)){
                    if(mstBondCreditRatings.size()<=0){
                        ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行不存在债券代码、评级机构、评级日期、评级类型的数据,请核查");
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

    public List<ExcelValidDto> importData(List<List<String>> dataList, List<List<String>> tempList,String userName, String orgCode, String deptCode) throws ParseException {
        List<ExcelValidDto> checkResult = new ArrayList<>();
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
                tempCheckResult = this.checkExcelValidMsg(dataList);
                if(!StringUtil.isEmptyList(tempCheckResult)) {
                    return tempCheckResult;
                }
                for (int i = 0; i < dataList.size(); i++) {
                    List<String> message = new ArrayList<>();
                    //操作类型
                    String type = "";
                    if (null != dataList.get(i).get(0) && !"".equals(dataList.get(i).get(0))) {
                        type = dataList.get(i).get(0).toString();
                    }
                    MstBondCreditRating dto = this.getMstBondCreditRatingByExcel(dataList.get(i), message);
                    //根据债券代码，评级机构，评级日期 ,评级类型 查询出数据,有数据则更新,无数据则插入
                    Map<String, Object> map = this.getExcelMap(dto);
                    //map.put("gradeResult",gradeResult);
                    List<MstBondCreditRating> mstBondCreditRatings = mstBondCreditRating001Mapper.selectOneDate(map);

                    if ("新增".equals(type) || "修改".equals(type)) {
                            updateInfo(mstBondCreditRatings, dto, deptCode, userName);
                    } else if ("删除".equals(type)) {
                            mstBondCreditRating001Mapper.deleteOne(map);
                    } else {
                        message.add("操作类型值不在可选范围内");
                    }
                }
            }
        } else {
            ExcelValidDto excelValidDto = new ExcelValidDto();
            String errorMsg = "模板为空，请输入数据！";
            excelValidDto.setErrorInfo(errorMsg);
            checkResult.add(excelValidDto);
        }
        return checkResult;
    }


    private void updateInfo(List<MstBondCreditRating> mstBondCreditRatings, MstBondCreditRating dto,String deptCode,String userName){
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
                param.put("gradeOrgId",dto.getGradeOrgId());
                param.put("gradeDate",dto.getGradeDate());
                param.put("oType",dto.getoType());
                param.put("gradeResult",dto.getGradeResult());
                //下方为固定值
                param.put("status","E");
                param.put("svrDataSources","02");
                param.put("updateUser", LoginUserUtil.getUserName());
                param.put("updateTime",new Date());
                mstBondCreditRating001Mapper.updateOne(param);
            }
        }
        else{
            addMstBondCreditRating(dto,deptCode,userName);
        }
    }

    private void addMstBondCreditRating(MstBondCreditRating dto,String deptCode,String userName){
        //MstBondCreditRating entity=new MstBondCreditRating();
        Date nowDate=new Date();
//        entity.setoCode(oCode);
//        entity.setGradeOrgId(gradeOrgId);
//        entity.setGradeDate(gradeDate);
//        entity.setoType(oType);
//        entity.setGradeResult(gradeResult);
        dto.setCreateUser(userName);
        dto.setCreateDept(deptCode);
        dto.setCreateTime(nowDate);
        dto.setUpdateUser(userName);
        dto.setUpdateTime(nowDate);
        dto.setSvrDataSources("02");
        dto.setStatus("E");
        mstBondCreditRating001Mapper.insertOne(dto);
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

    /**
     * 根据excel组装参数
     * @param list
     * @param message
     */
    private MstBondCreditRating getMstBondCreditRatingByExcel(List<String> list, List<String> message) {
        MstBondCreditRating dto = new MstBondCreditRating();
        //债券代码
        String oCode = "";
        if (null != list.get(1) && !"".equals(list.get(1))) {
            oCode = list.get(1).toString();
        }
        //评级机构
        String gradeOrgId = "";
        if (null != list.get(2) && !"".equals(list.get(2))) {
            String[] arr = list.get(2).toString().split("\\s+");
            gradeOrgId = arr[0];
        }
        //评级日期
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
        Date gradeDate = null;
        if (null != list.get(3) && !"".equals(list.get(3))) {
            try{
                gradeDate = DateUtil.parse(list.get(3));
            }
            catch (Exception e) {
                message.add("评级类型必须为日期格式数据。");
            }
        }
        //评级类型
        String oType = "";
        if (null != list.get(4) && !"".equals(list.get(4))) {
            String[] arr = list.get(4).toString().split("\\s+");
            oType = arr[0];
        }
        //评级结果
        String gradeResult = "";
        if (null != list.get(5) && !"".equals(list.get(5))) {
            String[] arr = list.get(5).toString().split("\\s+");
            gradeResult = arr[0];
        }
        dto.setoCode(oCode);//债券代码
        dto.setGradeOrgId(gradeOrgId);//评级机构
        dto.setGradeDate(gradeDate);//评级日期
        dto.setoType(oType);//评级类型
        dto.setGradeResult(gradeResult);//评级结果
        return dto;
    }

    /**
     * 处理参数
     * @param dto
     * @return
     */
    private Map<String, Object> getExcelMap(MstBondCreditRating dto) {
        Map<String, Object> map = new HashMap<>();
        map.put("oCode", dto.getoCode());//债券代码
        map.put("gradeOrgId", dto.getGradeOrgId());//评级机构
        map.put("gradeDate", dto.getGradeDate());//评级日期
        map.put("oType", dto.getoType());//评级类型
        map.put("gradeResult", dto.getGradeResult());//评级结果
        return map;
    }

    /**
     * 检验数据
     * @param dataList
     * @return
     */
    private List<ExcelValidDto> checkExcelValidMsg(List<List<String>> dataList) {
        List<ExcelValidDto> errorList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            for (int j = i+1; j < dataList.size(); j++ ) {
                if(StringUtils.equals(dataList.get(i).get(1), dataList.get(j).get(1))
                        && StringUtils.equals(dataList.get(i).get(2), dataList.get(j).get(2))
                        &&  StringUtils.equals(dataList.get(i).get(3), dataList.get(j).get(3))
                        &&  StringUtils.equals(dataList.get(i).get(4), dataList.get(j).get(4))){
                    errorList.add(new ExcelValidDto("","第"+(i+2)+"行","第"+(i+2)+"行债券代码、评级机构、评级日期、评级类型与"+"第"+(j+2)+"行重复。"));
                }
            }
            List<String> message = new ArrayList<>();
            //操作类型
            String type ="";
            if(null != dataList.get(i).get(0) && !"".equals(dataList.get(i).get(0))){
                type = dataList.get(i).get(0).toString();
            }
            MstBondCreditRating dto = this.getMstBondCreditRatingByExcel(dataList.get(i), message);
            List<ValidMessage> resultList = new ArrayList<ValidMessage>();
            resultList = chbDataCheckCommonUtil.chbCheckCommon(resultList, dto, "MstBondCreditRating002", null, ValidMessage.class);
            if (!StringUtil.isEmptyList(resultList)) {
                resultList.forEach(validMessage -> {
                    message.add(validMessage.getMessage());
                });
            }
            if (!message.isEmpty()) {
                errorList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2),
                        StringUtil.joinString(message, "")));
            }
        }
        return errorList;
    }
}
