package com.joyintech.tams.svreport.chb.mstbondcreditrating.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.chb.boksubject.dto.BokSubjectDto;
import com.joyintech.tams.svreport.chb.mstbondcreditrating.dao.MstBondCreditRatingDao;
import com.joyintech.tams.svreport.chb.mstbondcreditrating.dto.MstBondCreditRatingDto;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wubingbing
 * @date 2022-04-01 14:59
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MstBondCreditRateService extends CommonExportService<MstBondCreditRatingDto> implements BaseProcessService<MstBondCreditRatingDto> {
    @Autowired
    private MstBondCreditRatingDao mstBondCreditRatingDao;
    @Override
    public void doComplete(MstBondCreditRatingDto entity, OperationType type) {
        if (OperationType.DELETE.equals(type)) {
            entity.setStatus(DictCodeListExt1.维护数据状态_删除.getValue());
            mstBondCreditRatingDao.update(entity);

        }
        else if (OperationType.NEW.equals(type)) {
            entity.setDataSource(DictCodeListExt2.数据来源_新建导入.getValue());
            entity.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
            mstBondCreditRatingDao.insert(entity);
        }
        else if(OperationType.MODIFY.equals(type)){
            entity.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
            mstBondCreditRatingDao.update(entity);
        }

    }
    /**
     * 获取金融产品简称
     * @return
     */
    public List<SuggestDto> getFinProdAbbr(String param) {

        return mstBondCreditRatingDao.getFinProdAbbr(param);
    }

    public MstBondCreditRatingDto selcetByKey(String oCode, String oObject) {
        return mstBondCreditRatingDao.selcetByKey(oCode,oObject);
    }

    public Page<MstBondCreditRatingDto> selectForPage(Map<String, Object> pageParam) {
        Page<MstBondCreditRatingDto> page = queryForPage(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")), () -> mstBondCreditRatingDao.selectAll(pageParam));
//        page.getRoot().forEach(item->{
//            item.setGradeOrgId(SysDataUtil.getNameFromDataDict(DictCode.评级机构,item.getGradeOrgId()));
//            item.setShortLongTerm(SysDataUtil.getNameFromDataDict(DictCode.长期短期,item.getShortLongTerm()));
//            item.setGradeResult(SysDataUtil.getNameFromDataDict(DictCode.评级结果,item.getGradeResult()));
//            item.setDataSource(SysDataUtil.getNameFromDataDict(DictCode.数据来源,item.getDataSource()));
//            item.setStatus(SysDataUtil.getNameFromDataDict(DictCode.维护数据状态,item.getStatus()));
//            item.setoObject(SysDataUtil.getNameFromDataDict(DictCode.评级类型,item.getoObject()));
//            item.setSource(SysDataUtil.getNameFromDataDict(DictCode.评级来源类型,item.getSource()));
//        });
        return page;
    }
    @Transactional(rollbackFor = Exception.class)
    public List<ExcelValidDto> importData(String fileName) throws Exception{
        // 检查文件名是否为空
        if (StringUtil.isNotEmpty(fileName)) {
            if (fileName.indexOf("债券评级") != -1) {
                List<List<String>> dataList = dataAnalysis(fileName);
                if (null != dataList && dataList.size() > 0) {
                    // 将excel表格中的数据转化为dto
                    List<ExcelValidDto> checkResult = checkExcelData(dataList);
//                    if (checkResult.isEmpty()) {
//                        List<ComBondQuotationInfoDto> equityList = converToBean(dataList);
//                        // 保存，已存在则更新
//                        dealAfterCheck(equityList);
//                    }
                    return checkResult;
                }else{
                    List<ExcelValidDto> checkResult = new ArrayList<>();
                    checkResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,
                            "文件为空"));
                    return checkResult;
                }
            }
            else {
                List<ExcelValidDto> checkResult = new ArrayList<>();
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,
                        "文件名称错误，请下载模板后上传"));
                return checkResult;
            }
        }
        return null;
    }
    /**
     * excel导入数据整理
     *
     * @param flieName 文件名称
     * @return 检验list
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public List<List<String>> dataAnalysis(String flieName)
            throws Exception {
        String prefix = CacheUtil.getSysParam(GlobalConstant.COM_PROP_UPLOAD_PATH);
        if(!StringUtil.startsWith(flieName, prefix)
                && !StringUtil.startsWith(flieName, prefix.replace("/", File.separator))) {
            flieName = prefix + File.separator + flieName;
        }
        ExcelUtil excelUtil = ExcelUtil.build(flieName, null);
        List<List<String>> dataList = excelUtil.readToList(0, 1, 0);
        return dataList;
    }
    /**
     * 导入文件内容检查 <功能详细描述>
     *
     * @param dataList 导入数据
     * @return 返回检查结果
     * @throws ParseException 抛出异常
     * @see
     * @since 1.0
     */
    private List<ExcelValidDto> checkExcelData(List<List<String>> dataList)
            throws ParseException {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(dataList)) {
            for (int i = 0; i < dataList.size(); i++ ) {
                List<ExcelValidDto> result = checkExcelCow(i, dataList.get(i));
                // 检查表内数据每行是否合法
                if (CollectionUtils.isNotEmpty(result)) {
                    checkResult.addAll(result);
                }
            }
        }
        if (checkResult.size()>0){
            return checkResult;
        }
        List<MstBondCreditRatingDto> equityList = converToBean(dataList,null);
        MstBondCreditRatingDto mstBondCreditRatingDto = null;
        List<Integer> duplicateDataList=new ArrayList<Integer>();

        if (equityList.size() >= 1) {
            for (int i = 0; i < equityList.size(); i++ ) {
                mstBondCreditRatingDto = equityList.get(i);
                // 检查表内数据是否有主键重复
                if (equityList.size() >= 2) {
                    for (int k = i + 1; k < equityList.size(); k++ ) {
                        if (equityList.get(k).getoCode().equals(mstBondCreditRatingDto.getoCode()) && equityList.get(k).getoObject().equals(mstBondCreditRatingDto.getoObject()) ) {
                            if(!duplicateDataList.contains(k)){
                                duplicateDataList.add(k);
                                checkResult.add(new ExcelValidDto(dataList.get(k).get(1),
                                        String.valueOf(k + 2), "表中数据重复"));
                            }
                        }
                    }
                }
            }
        }
        return checkResult;
    }
    /**
     * 检查excel是否为空或者传入列是否正确
     *
     * @param i 第几行
     * @param list 每一列解析后的List
     * @return 检查结构
     * @see
     * @since 1.0
     */
    private List<ExcelValidDto> checkExcelCow(int i, List<String> list) {
        List<ExcelValidDto> excelValidDtolist = new ArrayList<ExcelValidDto>();
        // 列数为15
        if (null == list || list.size() != 8) {
            ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i), "数据列数错误");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(0))) {
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "债券代码为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(1))) {
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "债券名称为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(2))) {
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "评级对象为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(3))) {
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "评级机构为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(4))) {
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "评级类型为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(5))) {
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "评级结果为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(6))) {
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "评级来源为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(7))) {
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i+2), "评级日期为空");
            excelValidDtolist.add(dto);
        }
        return excelValidDtolist;
    }
    /**
     * list转换为Bean
     *
     * @param dataList excel解析后的list
     * @return List<mstMarketCalendarInfo001Dto>
     * @throws ParseException 抛出异常
     * @see
     * @since 1.0
     */
    public List<MstBondCreditRatingDto> converToBean(List<List<String>> dataList,List<Integer> checkResult)
            throws ParseException {
        // 返回的list定义
        List<MstBondCreditRatingDto> returnInfoList = new ArrayList<MstBondCreditRatingDto>();
        MstBondCreditRatingDto mstBondCreditRatingDto = null;
        for (int i = 0; i < dataList.size(); i++ ) {
            if(null !=checkResult && checkResult.contains(i)){
                continue;
            }
            List<String> list = dataList.get(i);
            mstBondCreditRatingDto = new MstBondCreditRatingDto();
            if (StringUtil.isNotEmpty(list.get(0))) {
                mstBondCreditRatingDto.setoCode(list.get(0).trim());
            }
            if (StringUtil.isNotEmpty(list.get(1))) {
                mstBondCreditRatingDto.setoName(list.get(1).trim());
            }
            if (StringUtil.isNotEmpty(list.get(2))) {
                mstBondCreditRatingDto.setoObject(list.get(2).trim());
            }
            if (StringUtil.isNotEmpty(list.get(3))) {
                mstBondCreditRatingDto.setGradeOrgId(list.get(3).trim());
            }
            if (StringUtil.isNotEmpty(list.get(4))) {
                mstBondCreditRatingDto.setShortLongTerm(list.get(4).trim());
            }
            if (StringUtil.isNotEmpty(list.get(5))) {
                mstBondCreditRatingDto.setGradeResult(list.get(5).trim());
            }
            if (StringUtil.isNotEmpty(list.get(6))) {
                mstBondCreditRatingDto.setSource(list.get(6).trim());
            }
            if (StringUtil.isNotEmpty(list.get(7))) {
                mstBondCreditRatingDto.setGradeDate(list.get(7).trim());
            }

            mstBondCreditRatingDto.setCreateUser(CommonUtil.getUserName());
            mstBondCreditRatingDto.setCreateDept(CommonUtil.getDeptCode());
            mstBondCreditRatingDto.setCreateTime(DateUtil.now());
            mstBondCreditRatingDto.setUpdateUser(CommonUtil.getUserName());
            mstBondCreditRatingDto.setUpdateTime(DateUtil.now());
            mstBondCreditRatingDto.setDataSource(DictCodeListExt2.数据来源_新建导入.getValue());
            mstBondCreditRatingDto.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
            returnInfoList.add(mstBondCreditRatingDto);
        }
        return returnInfoList;
    }
    /**
     * 导入文件重复数据检查 <功能详细描述>
     *
     * @param dataList 导入数据
     * @return 返回检查结果
     * @throws ParseException 抛出异常
     * @see
     * @since 1.0
     */
    public List<Integer> duplicateCheck(List<List<String>> dataList)
            throws ParseException {
        List<ExcelValidDto> checkResult = checkExcelData(dataList);
        if(CollectionUtils.isNotEmpty(checkResult)){
            return new ArrayList<>();
        }
        List<MstBondCreditRatingDto> equityList = converToBean(dataList,null);
        List<Integer> duplicateData=new ArrayList<Integer>();
        for (int i = 0; i < dataList.size(); i++ ) {
            MstBondCreditRatingDto dto = equityList.get(i);
            if(dto.getoCode() == null
                    || dto.getoName() == null
                    || dto.getoObject() == null
                    || dto.getGradeOrgId() == null
                    || dto.getGradeResult()== null
                    || dto.getShortLongTerm()== null
                    || dto.getSource()== null
                    || duplicateData.contains(i)){
                continue;
            }
            // 检查表内数据是否有主键重复
            for (int k = i + 1; k < dataList.size(); k++ ) {
                if(equityList.get(k).getoCode() == null){
                    continue;
                }
                if (equityList.get(k).getoCode().equals(dto.getoCode())
                    && equityList.get(k).getoObject().equals(dto.getoObject())) {
                    if(!duplicateData.contains(k)){
                        duplicateData.add(k);
                    }
                }
            }
        }
        if(duplicateData.isEmpty()){
            duplicateData.add(-1);
        }
        return duplicateData;
    }

    /**
     * check后的保存
     *
     * @param list 需要保存的数据
     * @see
     * @since 1.0
     */
    public void dealAfterCheck(List<MstBondCreditRatingDto> list) {

        mstBondCreditRatingDao.batchInsert(list);
    }

    public List<MstBondCreditRatingDto> selectAll(Map<String, Object> params) {
        List<MstBondCreditRatingDto> list = mstBondCreditRatingDao.selectAll(params);
//        list.forEach(item->{
//            item.setGradeOrgId(SysDataUtil.getNameFromDataDict(DictCode.评级机构,item.getGradeOrgId()));
//            item.setShortLongTerm(SysDataUtil.getNameFromDataDict(DictCode.长期短期,item.getShortLongTerm()));
//            item.setGradeResult(SysDataUtil.getNameFromDataDict(DictCode.评级结果,item.getGradeResult()));
//            item.setDataSource(SysDataUtil.getNameFromDataDict(DictCode.数据来源,item.getDataSource()));
//            item.setStatus(SysDataUtil.getNameFromDataDict(DictCode.维护数据状态,item.getStatus()));
//            item.setoObject(SysDataUtil.getNameFromDataDict(DictCode.评级类型,item.getoObject()));
//            item.setSource(SysDataUtil.getNameFromDataDict(DictCode.评级来源类型,item.getSource()));
//        });
        return list;
    }
}
