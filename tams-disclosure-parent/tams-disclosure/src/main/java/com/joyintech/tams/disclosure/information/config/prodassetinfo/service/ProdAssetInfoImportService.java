package com.joyintech.tams.disclosure.information.config.prodassetinfo.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.dao.ProdAssetInfo001Mapper;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.dto.ProdAssetInfoDto;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ProdAssetInfoImportService extends CommonExportService<ProdAssetInfoDto> {
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProdAssetInfo001Mapper prodAssetInfo001Mapper;
    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;

    /**
     * 当在做导入前的check就是判断业务数据是否需要覆盖的时候在webresult的data中塞入这个值可以不会弹框两次
     */
    private static final String NO_INFO_SHOWOUT = "noInfoShowOut";

    /**
     * 导入check
     * @param fileName
     * @return
     */
    public AjaxResult checkImportData(String fileName) throws FileNotFoundException, ParseException {
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(fileName);
        joyinDFSTemplate.fetch(suf);
        AjaxResult wr = new AjaxResult();
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        //获取系统参数：单次导入的最大条数 todo:添加系统参数
        int maxCount = CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT")==null?50000:Integer.parseInt(CacheUtil.getSysParam("IMPORT_EXCEL_ROW_COUNT"));
        //todo:数据量较大时，操作耗时时间过长
        ExcelUtil excelUtil = ExcelUtil.build(FileNameUtil.getUploadFilePath(fileName),
                null);
        List<List<String>> dataList = excelUtil.readToList(0, 1, 0);
        //校验导入文件条数是否大于50000
        if(dataList.size()>maxCount){
            JoyinMessage ms = new JoyinMessage("导入数据超过"+maxCount+"条，可能导致系统较慢，是否确认导入？",
                    MessageType.确认消息);
            joyinMessages.add(ms);
        }else{
            wr.setData(NO_INFO_SHOWOUT);
        }
        if (null != dataList && dataList.size() > 0) {
            // 文件内容检查
            List<ExcelValidDto> checkResult = new ArrayList<>();
            List<ProdAssetInfoDto> entities = new ArrayList<>(dataList.size());
//            convertToEntity(dataList, entities);
            StringBuffer msg=new StringBuffer();
            String s="";
            for (int i = 0; i < dataList.size(); i++) {
                List<String> list = dataList.get(i);
                try{
                    ProdAssetInfoDto prodAssetInfoDto =convertToBean(list,i);
                    List<ProdAssetInfoDto> dtos = prodAssetInfo001Mapper.selectAssetInfoByProdId(prodAssetInfoDto.getProdId());
                    if (dtos!=null && dtos.size()!=0){
                        for (ProdAssetInfoDto prodAssetInfoDto1 : dtos){
                            if (StringUtil.equals(prodAssetInfoDto.getProdId(),prodAssetInfoDto1.getProdId()) &&
                                    DateUtil.compareDate(prodAssetInfoDto.getEffDate(),prodAssetInfoDto1.getEffDate())==0&&
                                    DateUtil.compareDate(prodAssetInfoDto.getEffEndDate(),prodAssetInfoDto1.getEffEndDate())==0){
                                msg.append(prodAssetInfoDto.getExcelRowNum()+ GlobalConstant.COMMA);
                                break;
                            }
                        }
                    }
                }catch (Exception e){
                    break;
                }
            }
            if(StringUtil.isNotEmpty(msg)){
                s=msg.substring(0,msg.length()-1);
            }
            if(StringUtil.isNotEmpty(s)){
                JoyinMessage ms = new JoyinMessage("第"+s+"行数据重复，请确认是否覆盖",
                        MessageType.确认消息);
                joyinMessages.add(ms);
            }

//           for (ProdAssetInfoDto prodAssetInfoDto: entities){
//               List<ProdAssetInfoDto> dtos = prodAssetInfo001Mapper.selectAssetInfoByProdId(prodAssetInfoDto.getProdId());
//               if (dtos!=null && dtos.size()!=0){
//                   for (ProdAssetInfoDto prodAssetInfoDto1 : dtos){
//                       if (StringUtil.equals(prodAssetInfoDto.getProdId(),prodAssetInfoDto1.getProdId()) &&
//                               DateUtil.compareDate(prodAssetInfoDto.getEffDate(),prodAssetInfoDto1.getEffDate())==0&&
//                               DateUtil.compareDate(prodAssetInfoDto.getEffEndDate(),prodAssetInfoDto1.getEffEndDate())==0){
//                           JoyinMessage ms = new JoyinMessage("导入文件与系统存在重复的数据，是否确认导入",
//                                   MessageType.确认消息);
//                           joyinMessages.add(ms);
//                       }
//                   }
//               }
//           }

        }

        wr.setJoyinMessages(joyinMessages);
        return wr;
    }

    /**
     * excel导入功能
     *
     * @param flieName 文件名称
     * @return 检验list
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    public List<ExcelValidDto> importData(String flieName)
            throws Exception {
        SysUploadFileInfo suf = new SysUploadFileInfo();
        suf.setPath(flieName);
        joyinDFSTemplate.fetch(suf);
        //文件检查结果
        List<ExcelValidDto> checkFileResult = new ArrayList<>();
        // 检查文件名是否为空
        if (StringUtil.isNotEmpty(flieName)) {
            // 检查文件名是否包含产品资产组合情况导入
//            if (flieName.indexOf("产品资产组合情况导入") != -1) {
            ExcelUtil excelUtil = ExcelUtil.build(FileNameUtil.getUploadFilePath(flieName),
                    null);
            List<List<String>> dataList = excelUtil.readToList(0, 1, 0);
            // 检查读出信息是否为空
            if (null != dataList && dataList.size() > 0) {
                // 文件内容检查
                List<ExcelValidDto> checkResult = new ArrayList<>();
                List<ProdAssetInfoDto> entities = new ArrayList<>(dataList.size());
                checkResult = convertToEntity(dataList, entities);
                if (checkResult.isEmpty()) {
                    dealAfterCheck(entities);
                }
                return checkResult;
            }else{
                checkFileResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,
                        "文件内容为空,没有需要导入的数据！"));
                return checkFileResult;
            }
        }
        return null;
    }

    /**
     * 检查导入数据，转换成dto
     * @param dataList
     * @param entities
     * @return
     */
    private List<ExcelValidDto> convertToEntity(List<List<String>> dataList, List<ProdAssetInfoDto> entities) throws ParseException {
        List<ExcelValidDto> checkContextResult = new ArrayList<>();
        // 验证消息
        for (int i = 0; i < dataList.size(); i++) {
            List<String> message = new ArrayList<>();
            List<String> list = dataList.get(i);
            List<ExcelValidDto> excelValidDtolist = new ArrayList<ExcelValidDto>();
            // 模板中列数应为4
            if (null == list || list.size() != 11) {
                ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i),
                        this.getMessage("MST_INFO_E_0001"));
                excelValidDtolist.add(dto);
                return excelValidDtolist;
            }
            //字段非空检查
            String[] fildsStr = {"主产品代码", "穿透前固定收益类资产占总资产比例", "穿透后固定收益类资产占总资产比例"
                    ,"穿透前权益类资产占总资产比例","穿透后权益类资产占总资产比例","穿透前商品及金融衍生品类资产占总资产比例",
                    "穿透后商品及金融衍生品类资产占总资产比例","穿透前混合类资产占总资产比例","穿透后混合类资产占总资产比例","生效日","生效截止日期"};
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) == null || list.get(j).length()==0) {
                    message.add(fildsStr[j] + "不能为空！");
                }
            }
            //检查表内的产品主代码在系统中是否存在
            if(StringUtil.isNotEmpty(list.get(0))){
                if(!checkProdIdExist(list.get(0).trim())){
                    message.add("产品主代码不存在！");
                }
            }
            //验证比例值是否合法
            for (int index = 1; index < list.size()-2; index++){
                if(StringUtil.isNotEmpty(list.get(index))){
                    if(!isBigDecimal(list.get(index).trim())){
                        message.add(fildsStr[index]+"字段数据格式不正确！");
                    }
                    if (isBigDecimal(list.get(index).trim()) && new BigDecimal(list.get(index).trim()).signum()== -1){
                        message.add(fildsStr[index]+"字段不能为负数！");
                    }
                }
            }
            //验证穿透前，穿透后资产占比总和是否>100
            if(isBigDecimal(list.get(1).trim()) && isBigDecimal(list.get(3).trim()) && isBigDecimal(list.get(5).trim()) && isBigDecimal(list.get(7).trim())){
                if(ArithmeticUtil.doAdd(new BigDecimal(list.get(1).trim()),new BigDecimal(list.get(3).trim()),new BigDecimal(list.get(5).trim()),new BigDecimal(list.get(7).trim())).compareTo(new BigDecimal(100))>0){
                    message.add("穿透前资产占比总和大于100");
                }
            }

            if(isBigDecimal(list.get(2).trim()) && isBigDecimal(list.get(4).trim()) && isBigDecimal(list.get(6).trim()) && isBigDecimal(list.get(8).trim())){
                if(ArithmeticUtil.doAdd(new BigDecimal(list.get(2).trim()),new BigDecimal(list.get(4).trim()),new BigDecimal(list.get(6).trim()),new BigDecimal(list.get(8).trim())).compareTo(new BigDecimal(100))>0){
                    message.add("穿透后资产占比总和大于100");
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DEFAULT_ANOTHER_FORMAT);
            // 获取平台日期
            Date sysDate = DateUtil.platDateFromCache();
            // 验证生效日
            if(StringUtil.isNotEmpty(list.get(9))){
                if(!isDateStr(list.get(9))){
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0032","生效日"));
                }else{
                    // 验证生效日是否大于等于平台日期
                    if (DateUtil.compareDate(sysDate, sdf.parse(list.get(9))) > 0) {
//                        ExcelValidDto dto = new ExcelValidDto(list.get(9), String.valueOf(i + 2),
//                                this.getMessage("PRODFILE_E_0001", "生效日", "平台日期"));
//                        excelValidDtolist.add(dto);
                    }
                }
            }
            //验证生效日截止日
            if(StringUtil.isNotEmpty(list.get(10))){
                if(!isDateStr(list.get(10))){
                    message.add(this.getMessage("REPORT_COMMON_FILE_E_0032","生效截止日"));
                }else{
                    if(isDateStr(list.get(9))){
                        Date effEndDate=sdf.parse(list.get(10).trim());
                        Date effDate=sdf.parse(list.get(9).trim());
                        if(DateUtil.compareDate(effDate, effEndDate) > 0){
                            message.add("生效日截止日期要大于等于生效日！");
                        }else if (StringUtil.isNotEmpty(list.get(0))){
                            //校验同一产品生效区间是否重叠
                            List<ProdAssetInfoDto> dtos = prodAssetInfo001Mapper.selectAssetInfoByProdId(list.get(0).trim());
                            for (ProdAssetInfoDto dto : dtos) {

                                if (StringUtil.equals(list.get(0).trim(),dto.getProdId()) &&
                                        DateUtil.compareDate(DateUtil.parse(list.get(9).trim()),dto.getEffDate())==0&&
                                        DateUtil.compareDate(DateUtil.parse(list.get(10).trim()),dto.getEffEndDate())==0){
                                    continue;
                                }

                                if(!((DateUtil.compareDate(effDate,dto.getEffDate())<0 && DateUtil.compareDate(effEndDate,dto.getEffDate())<0)
                                        || (DateUtil.compareDate(effDate,dto.getEffEndDate())>0 && DateUtil.compareDate(effEndDate,dto.getEffEndDate())>0))
                                && !(DateUtil.compareDate(effDate,dto.getEffDate())==0 && DateUtil.compareDate(effEndDate,dto.getEffEndDate())==0)){
                                    message.add("生效区间重叠！<br/> 系统中已存在的生效区间:"+DateUtil.format(dto.getEffDate(),"yyyyMMdd")
                                            +"-"+DateUtil.format(dto.getEffEndDate(),"yyyyMMdd"));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(message)) {
                excelValidDtolist.add(new ExcelValidDto(list.get(0)
                        + GlobalConstant.UNDER_LINE + list.get(9)
                        + GlobalConstant.UNDER_LINE + list.get(10),
                        String.valueOf(i + 2), StringUtil.joinString(message, "<br>")));
            }
            //数据格式校验都通过后，转成目标bean
            if(StringUtil.isEmptyList(excelValidDtolist)){
                entities.add(convertToBean(list,i));
            }
            checkContextResult.addAll(excelValidDtolist);
        }

        // 检查表内数据同一个产品是否有生效区间重合的
        if (entities.size() >= 2) {
            Map<String, List<ProdAssetInfoDto>> map = entities.stream().collect(Collectors.groupingBy(ProdAssetInfoDto::getProdId));
            for (String key : map.keySet()) {
                List<ProdAssetInfoDto> prodAssetInfoDtoList = map.get(key);
                if(prodAssetInfoDtoList.size()>1){
                    loop:for(int m=0;m<prodAssetInfoDtoList.size()-1;m++){
                        ProdAssetInfoDto dto1 = prodAssetInfoDtoList.get(m);
                        for(int n=m+1;n<prodAssetInfoDtoList.size();n++){
                            ProdAssetInfoDto dto2 = prodAssetInfoDtoList.get(n);
                            if(!((DateUtil.compareDate(dto2.getEffDate(),dto1.getEffDate())<0 && DateUtil.compareDate(dto2.getEffEndDate(),dto1.getEffDate())<0)
                                    || (DateUtil.compareDate(dto2.getEffDate(),dto1.getEffEndDate())>0 && DateUtil.compareDate(dto2.getEffEndDate(),dto1.getEffEndDate())>0))){
                                checkContextResult.add(new ExcelValidDto(key, dto1.getExcelRowNum(),
                                        "文件中同一产品生效区间有重叠：第"+dto1.getExcelRowNum()+"行和第"+dto2.getExcelRowNum()+"行"));
                                break loop;
                            }
                        }
                    }
                }
            }
        }
        return checkContextResult;
    }

    /**
     * 字符串是否能转换成数字
     * @param numberStr
     * @return
     */
    public static boolean isBigDecimal(String numberStr) {
        Pattern NUMERIC_PATTERN = Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");
        Matcher m = NUMERIC_PATTERN.matcher(numberStr);
        if (m.find()) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * Bean转换
     * @param list
     * @return
     */
    private ProdAssetInfoDto convertToBean(List<String> list,int excelRowNum) {
        ProdAssetInfoDto prodAssetInfoDto=new ProdAssetInfoDto();
        prodAssetInfoDto.setProdAssetId(IDGenerator.generate(36));
        prodAssetInfoDto.setProdId(list.get(0).trim());
        prodAssetInfoDto.setFixPreRatio(new BigDecimal(list.get(1).trim()));
        prodAssetInfoDto.setFixAfterRatio(new BigDecimal(list.get(2)));
        prodAssetInfoDto.setEquityPreRatio(new BigDecimal(list.get(3).trim()));
        prodAssetInfoDto.setEquityAfterRatio(new BigDecimal(list.get(4).trim()));
        prodAssetInfoDto.setFdPreRatio(new BigDecimal(list.get(5).trim()));
        prodAssetInfoDto.setFdAfterRatio(new BigDecimal(list.get(6).trim()));
        prodAssetInfoDto.setMixPreRatio(new BigDecimal(list.get(7).trim()));
        prodAssetInfoDto.setMixAfterRatio(new BigDecimal(list.get(8).trim()));
        prodAssetInfoDto.setEffDate(DateUtil.parse(list.get(9).trim()));
        prodAssetInfoDto.setEffEndDate(DateUtil.parse(list.get(10).trim()));
        prodAssetInfoDto.setCreateUser(CommonUtil.getUserName());
        prodAssetInfoDto.setCreateDept(CommonUtil.getDeptCode());
        prodAssetInfoDto.setUpdateUser(CommonUtil.getUserName());
        prodAssetInfoDto.setDataSource(DictCodeListExt3.信披_数据来源_EXCEL导入.getValue());
        prodAssetInfoDto.setDataStatus(DictCodeListExt3.信披_数据状态_有效E.getValue());
        prodAssetInfoDto.setExcelRowNum(String.valueOf(excelRowNum+2));
        return prodAssetInfoDto;
    }


    /**
     * 检查导入文件中的产品主代码是否存在
     * @param prodId
     * @return
     */
    private boolean checkProdIdExist(String prodId) {
        return prodAssetInfo001Mapper.selectByProdId(prodId)>0;
    }

    /**
     * check后的保存 <功能详细描述>
     *
     * @param equityList 需要保存的数据
     * @see
     * @since 1.0
     */
    public void dealAfterCheck(List<ProdAssetInfoDto> equityList) {
//        equityList.forEach(item->{prodAssetInfo001Mapper.deleteDisProdAsset(item.getProdId(),item.getEffDateStr(),item.getEffEndDateStr());});
        this.insertBtachEquityInfoByPart(equityList);
        /*List<ProdAssetInfoDto> listN = new ArrayList<ProdAssetInfoDto>();
        ProdAssetInfoDto prodAssetInfoDto=new ProdAssetInfoDto();
        for (int i = 0; i < equityList.size(); i++ ) {
            prodAssetInfoDto = equityList.get(i);
            listN.add(prodAssetInfoDto);
        }
        if (listN != null && listN.size() > 0) {
            this.insertBtachEquityInfoByPart(listN);
        }*/
        /*List<ProdPartnerDto> listN = new ArrayList<ProdPartnerDto>();
        List<ProdPartnerDto> listA = new ArrayList<ProdPartnerDto>();
        for (int i = 0; i < equityList.size(); i++ ) {
            ProdPartnerDto partnerDto = equityList.get(i);
            if(this.checkProdIdExist(partnerDto.getProdId())){
                listA.add(partnerDto);
            }else{
                listN.add(partnerDto);
            }
        }
        if (listN != null && listN.size() > 0) {
            this.insertBtachEquityInfoByPart(listN);
        }
        if (listA != null && listA.size() > 0) {
            this.updateBtachEquityInfoByPart(listA);
        }*/
    }

    /**
     * 分批次执行insertBtachEquityInfo
     *
     * @param dataList 数据
     * @see
     * @since 1.0
     */
    private void insertBtachEquityInfoByPart(List<ProdAssetInfoDto> dataList) {
        if (StringUtil.isEmptyList(dataList)) {
            return;
        }
        else {

            int maxDataCountOneTime = 20;
            int dataCount = dataList.size();
            if (dataCount <= maxDataCountOneTime) {
                insertBtachEquityInfo(dataList);
            }
            else {
                List<ProdAssetInfoDto> newDataList = new ArrayList<ProdAssetInfoDto>();
                newDataList.addAll(dataList);

                // 分批处理
                int part = dataCount / maxDataCountOneTime; // 分批数
                for (int i = 0; i < part; i++ ) {
                    insertBtachEquityInfo(newDataList.subList(0, maxDataCountOneTime));
                    newDataList.subList(0, maxDataCountOneTime).clear();
                }
                // 最后剩下的数据
                if (CollectionUtils.isNotEmpty(newDataList)) {
                    insertBtachEquityInfo(newDataList);
                }
            }
        }
    }


    /**
     * 插入数据 <功能详细描述>
     *
     * @param list 参数
     * @see
     * @since 1.0
     */
    private void insertBtachEquityInfo(List<ProdAssetInfoDto> list) {
        prodAssetInfo001Mapper.insertBtachEquityInfo(list);
    }

    public static boolean isDateStr(String str) {
        boolean result=true;
        SimpleDateFormat format = new SimpleDateFormat(DateUtil.DEFAULT_ANOTHER_FORMAT);
        try {
            format.setLenient(false);
            format.parse(str);
            result=true;

        } catch (Exception var3) {
            result=false;
        }
        String yearStr=str.split("/")[0];
        if(yearStr.startsWith("0")||yearStr.length()!=4){
            result=false;
        }
        return result;
    }

}
