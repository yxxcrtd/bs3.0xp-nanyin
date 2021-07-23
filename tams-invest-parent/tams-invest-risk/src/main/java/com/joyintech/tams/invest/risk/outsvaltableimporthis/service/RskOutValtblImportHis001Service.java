package com.joyintech.tams.invest.risk.outsvaltableimporthis.service;


import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.RskOutValtblImportHisMapper;
import com.joyintech.tams.common.base.entity.RskOutValtblImportHis;
import com.joyintech.tams.common.base.entity.RskValtblPrtImpMap;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.outsvaltableimporthis.dto.RskOutValtblImportHis001Dto;
import com.joyintech.tams.invest.risk.valtblimport.dto.RskValTableImportParamDto;
import com.joyintech.tams.invest.risk.valtblimport.dto.RskValtblAssetSubDto;
import com.joyintech.tams.invest.risk.valtblimport.service.RskValTableImportService;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RskOutValtblImportHis001Service extends CommonExportService<RskOutValtblImportHis001Dto> {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 估值表导入Service
     */
    @Autowired
    private RskValTableImportService rskValTableImportService;

    @Autowired
    private RskOutValtblImportHisMapper rskOutValtblImportHisMapper;

    // 平台日期
    @Autowired(required = false)
    private IPlatformDate iPlatformDate;

    private final static String BAK_START_STR = "BAK_";

    /**
     * 画面查询 <功能详细描述>
     *
     * @param parameters parameters
     * @return
     * @see
     * @since 1.0
     */
    public Page<RskOutValtblImportHis001Dto> selectPageData(Map<String, Object> parameters) {
        String filePathStart = CacheUtil.getSysParam("RSK_OUTS_VALTABLE_IMPORT_PATH");
        Page<RskOutValtblImportHis001Dto> page = restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME + "/rskOutValtblImportHis/selectForPage",
                parameters, RskOutValtblImportHis001Dto.class);
        page.getRoot().forEach(item->{
            if (StringUtil.isNotEmpty(item.getFilePath()) && (item.getFilePath().replaceAll("\\\\", "/"))
                    .startsWith(filePathStart.replaceAll("\\\\", "/"))){
                if (StringUtil.equals(item.getImportType(),DictCodeListExt1.估值表自动解析处理状态_异常结束.getValue())){
                    item.setRunFlag(DictCodeListExt1.是否_是.getValue());
                }else{
                    item.setRunFlag(DictCodeListExt1.是否_否.getValue());
                }
            }else{
                item.setRunFlag(DictCodeListExt1.是否_否.getValue());
            }
        });
        return page;
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/risk/outsvaltableimporthis/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * <功能详细描述>
     *
     * @param param param
     * @return
     * @see
     * @since 1.0
     */
    public int queryorgCodeCount(String param) {
        return restTemplate.postForObject(
                ServiceApplication.RISK_SERVICE_NAME + "/rskOutValtblImportHis/queryorgCodeCount",
                param, int.class);
    }

    /**
     * <功能详细描述>
     *
     * @param ptlId ptlId
     * @return
     * @see
     * @since 1.0
     */
    public RskValtblPrtImpMap selectInfoById(String ptlId) {
        return restTemplate.postForObject(
                ServiceApplication.RISK_SERVICE_NAME + "/rskOutValtblImportHis/selectInfoById",
                ptlId, RskValtblPrtImpMap.class);
    }

    /**
     * 运行方法 <功能详细描述>
     *
     * @param dto dto
     * @return
     * @see
     * @since 1.0
     */
    public RskOutValtblImportHis runExecute(RskOutValtblImportHis dto)  {
        Date startDate = DateUtil.now();
        RskOutValtblImportHis returnDto = new RskOutValtblImportHis();
        String valTableFilePath = CacheUtil.getSysParam("RSK_OUTS_VALTABLE_IMPORT_PATH");
        //资管计划名称_估值日期的规则
        String RSK_FILE_NAME_RULE = CacheUtil.getSysParam("RSK_FILE_NAME_RULE");
        String filePath = valTableFilePath+ File.separator+DateUtil.format(dto.getImpdate(),DateUtil.DEFAULT_OTHER_FORMAT);
        File file = new File(filePath);
        if (file.isDirectory() && file.listFiles().length > 0) {
            //开始解析文件内容入库
            Map<String,List<File>> valTableFileMap = Arrays.asList(file.listFiles()).stream()
                     .collect(JoyinCollectors.groupingBy(item->item.getName()));
            if (!StringUtil.isNotEmpty(valTableFileMap) || valTableFileMap.size() ==0 ){
                return this.saveRskOutValtblImportHis(dto.getFileName(), dto.getFilePath(), dto.getPortfolioId(), dto.getPortfolioName(), dto.getValdate(),
                  DictCodeListExt1.估值表自动解析处理状态_异常结束.getValue(), DictCodeListExt1.估值表自动解析错误码_路径下无此文件.getValue(), startDate,dto.getImpdate());
            }
            File valTableFile = StringUtil.isEmptyList(valTableFileMap.get(dto.getFileName()))? (StringUtil.isEmptyList(valTableFileMap.get(BAK_START_STR+dto.getFileName()))?
                    null:valTableFileMap.get(BAK_START_STR+dto.getFileName()).get(0)):valTableFileMap.get(dto.getFileName()).get(0);
            if (!StringUtil.isNotEmpty(valTableFile)){
                return this.saveRskOutValtblImportHis(dto.getFileName(), dto.getFilePath(), dto.getPortfolioId(), dto.getPortfolioName(), dto.getValdate(),
                        DictCodeListExt1.估值表自动解析处理状态_异常结束.getValue(), DictCodeListExt1.估值表自动解析错误码_路径下无此文件.getValue(), startDate,dto.getImpdate());
            }
            try {
                String fileName = StringUtil.isNotEmpty(valTableFile.getName())
                        ? (new String(valTableFile.getName().getBytes(System.getProperty("file.encoding")), "UTF-8")) : valTableFile.getName();
                logger.info("解析委外估值表文件" + fileName);
                if (StringUtil.isNotEmpty(RSK_FILE_NAME_RULE)) {
                    Pattern pattern = Pattern.compile(RSK_FILE_NAME_RULE);
                    Matcher matcher = pattern.matcher(fileName);
                    if (!matcher.matches()) {
                         logger.info(fileName + ",文件名异常组合or日期格式错误。");
                         return this.saveRskOutValtblImportHis(dto.getFileName(), dto.getFilePath(), dto.getPortfolioId(), dto.getPortfolioName(), dto.getValdate(),
                                DictCodeListExt1.估值表自动解析处理状态_异常结束.getValue(), DictCodeListExt1.估值表自动解析错误码_文件名异常组合or日期格式错误.getValue(), startDate,dto.getImpdate());

                    }
                }
                fileName = dto.getFileName();
                String valDate = StringUtil.EMPTY;
                List<String> valTableFiles = StringUtil.splitToList(".", fileName);
                try {
                    valDate = valTableFiles.get(0);
                    valDate = valDate.substring(valDate.length()-8,valDate.length());
                }catch (Exception e){
                    logger.info(fileName+",文件名异常组合or日期格式错误。");
                    return this.saveRskOutValtblImportHis(dto.getFileName(), dto.getFilePath(), dto.getPortfolioId(), dto.getPortfolioName(), dto.getValdate(),
                            DictCodeListExt1.估值表自动解析处理状态_异常结束.getValue(),DictCodeListExt1.估值表自动解析错误码_文件名异常组合or日期格式错误.getValue(),startDate,dto.getImpdate());
                }
                String organCode = StringUtil.EMPTY;
                String portfolioName = StringUtil.EMPTY;
                if (valTableFiles.size() >= 2){
//                    valDate = valTableFiles.get(valTableFiles.size()-1);
                    organCode = this.queryorgCodeCount(valTableFiles.get(0)) > 0? valTableFiles.get(0):StringUtil.EMPTY;
                    if (StringUtil.isNotEmpty(organCode)){
                        portfolioName = fileName.replaceAll(fileName.substring(fileName.lastIndexOf(".")), "").replaceAll(organCode+"估值表","").replaceAll("估值表"+valDate,"");
                    }else{
                        portfolioName = fileName.replaceAll(fileName.substring(fileName.lastIndexOf(".")), "").replaceAll("估值表"+valDate,"");
                    }
                }else{
                    logger.info(fileName+",文件名异常组合or日期格式错误。");
                    return this.saveRskOutValtblImportHis(dto.getFileName(), dto.getFilePath(), dto.getPortfolioId(), dto.getPortfolioName(), dto.getValdate(),
                            DictCodeListExt1.估值表自动解析处理状态_异常结束.getValue(),DictCodeListExt1.估值表自动解析错误码_文件名异常组合or日期格式错误.getValue(),startDate,dto.getImpdate());
                }
                //去掉文件后缀
                valDate = valDate.replaceAll(fileName.substring(fileName.lastIndexOf(".")), "");
                Date resValDate = DateUtil.parse(valDate);
                String days = StringUtil.defaultString(CacheUtil.getSysParam("MAX_SUB_VALDATE"), "16");
                if (DateUtil.compareDate(resValDate, DateUtil.parse(DateUtil.addDay(iPlatformDate.getSysDateStr(), -1 * Integer.valueOf(days)))) < 0) {
                    logger.error(fileName + "超过可配置的估值日期");
                    return saveRskOutValtblImportHis(dto.getFileName(), dto.getFilePath(), dto.getPortfolioId(), dto.getPortfolioName(), dto.getValdate(),
                            DictCodeListExt1.估值表自动解析处理状态_异常结束.getValue(), DictCodeListExt1.估值表自动解析错误码_超过可配置的估值日期.getValue(), startDate,dto.getImpdate());
                }
                //1、组合
                Map<String, String> prtfolioIdMap = this.selectPortfolioId(portfolioName);
                if (prtfolioIdMap == null || prtfolioIdMap.size() == 0) {
                    logger.info(fileName + ",未找到委外组合。");
                    return saveRskOutValtblImportHis(dto.getFileName(), dto.getFilePath(), dto.getPortfolioId(), dto.getPortfolioName(), dto.getValdate(),
                            DictCodeListExt1.估值表自动解析处理状态_异常结束.getValue(), DictCodeListExt1.估值表自动解析错误码_无对应委外组合.getValue(), startDate,dto.getImpdate());
                }
                String ptlId = prtfolioIdMap.get("PORTFOLIO_ID");
                //1、组合
                Map<String, String> FinprodMarketIdMap = this.selectFinProductByFinprodMarketId(ptlId);
                String finprodType = StringUtil.EMPTY; //
                String finprodType2 = StringUtil.EMPTY;
                if (FinprodMarketIdMap != null && FinprodMarketIdMap.size() > 0) {
                    finprodType = FinprodMarketIdMap.get("FINPROD_TYPE");
                    finprodType2 = FinprodMarketIdMap.get("FINPROD_TYPE2");
                }
                //2、查询组合对应的映射
                RskValtblPrtImpMap rskValtblPrtImpMap = this.selectInfoById(ptlId);
                RskValTableImportParamDto paramDto = new RskValTableImportParamDto();
                paramDto.setPtlId(ptlId);
                paramDto.setStructMapId(rskValtblPrtImpMap.getStructMapId());
                paramDto.setSumsubMapId(rskValtblPrtImpMap.getSumsubMapId());
                paramDto.setAssetSubMapId(rskValtblPrtImpMap.getAssetSubMapId());
                paramDto.setSubjectCodeCol(StringUtil.numberToString(rskValtblPrtImpMap.getSubjectCodeCol()));
                paramDto.setStartRow(StringUtil.numberToString(rskValtblPrtImpMap.getStartRow()));
//                paramDto.setTrimChars(rskValtblPrtImpMap.getTrimChars());
                paramDto.setFilterId(rskValtblPrtImpMap.getFilterId());
                paramDto.setValTableFileName(valTableFile.getPath());
                List<RskValtblAssetSubDto> valHand_oneList = rskValTableImportService.viewValTable(paramDto);
                if (paramDto.getImportHisQueue() != null &&  paramDto.getImportHisQueue().size()>0){
                    RskOutValtblImportHis importHis = paramDto.getImportHisQueue().element();
                     return saveRskOutValtblImportHis(dto.getFileName(), dto.getFilePath(), dto.getPortfolioId(), dto.getPortfolioName(), dto.getValdate(),
                             importHis.getImportType(),importHis.getErrorCode(),startDate,dto.getImpdate());
                }
                if (valHand_oneList.size() > 0) {
                    //保存
                    RskValtblAssetSubDto rskValtblAssetSubDto = new RskValtblAssetSubDto();
                    rskValtblAssetSubDto.setPtlId(paramDto.getPtlId());
                    rskValtblAssetSubDto.setValDate(resValDate);
                    rskValtblAssetSubDto.setSelectedItem(valHand_oneList);
                    rskValtblAssetSubDto.setImpMode(DictCodeListExt1.导入方式_自动.getValue());
                    Queue<RskOutValtblImportHis> importHisQueue= new LinkedList<>();
                    RskOutValtblImportHis importHisDto =  saveRskOutValtblImportHis(dto.getFileName(), dto.getFilePath(), dto.getPortfolioId(), dto.getPortfolioName(), dto.getValdate(),
                            DictCodeListExt1.估值表自动解析处理状态_正常结束.getValue(),StringUtil.EMPTY,startDate,dto.getImpdate());
                    importHisQueue.offer(importHisDto);
                    rskValtblAssetSubDto.setImportHisQueue(importHisQueue);
//                    rskValTableImportService.checkAuto(rskValtblAssetSubDto);
                    rskValTableImportService.insert(rskValtblAssetSubDto);
                    return importHisDto;
                }else{
                    return saveRskOutValtblImportHis(dto.getFileName(), dto.getFilePath(), dto.getPortfolioId(), dto.getPortfolioName(), dto.getValdate(),
                            DictCodeListExt1.估值表自动解析处理状态_异常结束.getValue(),DictCodeListExt1.估值表自动解析错误码_估值表关联导入映射异常.getValue(),startDate,dto.getImpdate());
                }
            }  catch (Exception e) {
                logger.error("委外估值表接口批处理Error", e);
                return saveRskOutValtblImportHis(dto.getFileName(), dto.getFilePath(), dto.getPortfolioId(), dto.getPortfolioName(), dto.getValdate(),
                        dto.getImportType(),dto.getErrorCode(),startDate,dto.getImpdate());

            }
         }
        return saveRskOutValtblImportHis(dto.getFileName(), dto.getFilePath(), dto.getPortfolioId(), dto.getPortfolioName(), dto.getValdate(),
                dto.getImportType(),dto.getErrorCode(),startDate,dto.getImpdate());
    }

    /**
     * 保存委托估值导入记录表
     *
     * @param
     */
    private RskOutValtblImportHis saveRskOutValtblImportHis(String name,String path,String ptlId,String portfolioName,
                 Date resValDate,String importType,String errorCode,Date startDate,Date ImpDate) {
        //保存委托估值表接入记录
        RskOutValtblImportHis dto = new RskOutValtblImportHis();
        dto.setFileName(name);
        dto.setFilePath(path);
        dto.setPortfolioId(ptlId);
        dto.setPortfolioName(portfolioName);
        dto.setValdate(resValDate);
        dto.setImpdate(ImpDate);
        dto.setImportType(importType);
        dto.setErrorCode(errorCode);
        dto.setStartTime(startDate);
        dto.setEndTime(DateUtil.now());
        dto.setTimedTaskStatus("S");
        rskOutValtblImportHisMapper.updateByPrimaryKey(dto);
        return dto;
    }

    /**
     * 查询组合
     *
     * @param
     */
    public Map<String,String> selectPortfolioId(String portfolioName) {
        Map<String, Object> pageParam = new HashMap<>();
        pageParam.put("portfolioType", DictCodeListExt1.组合类型_委外组合.getValue());
        pageParam.put("portfolioType2", BussinessDictCodeList.组合类型_自主组合.getValue());
        pageParam.put("initValue", portfolioName);
        return restTemplate.postForObject(
                ServiceApplication.RISK_SERVICE_NAME + "/rskOutValtblImportHis/selectPortfolioId",
                pageParam, Map.class);
    }

    /**
     * 查询FinprodMarketId
     *
     * @param
     */
    public Map<String,String> selectFinProductByFinprodMarketId(String ptlId) {
        Map<String, Object> pageParam = new HashMap<>();
        pageParam.put("finprodMarketId", ptlId);
        return restTemplate.postForObject(
                ServiceApplication.RISK_SERVICE_NAME + "/rskOutValtblImportHis/selectFinProductByFinprodMarketId",
                pageParam, Map.class);
    }

}
