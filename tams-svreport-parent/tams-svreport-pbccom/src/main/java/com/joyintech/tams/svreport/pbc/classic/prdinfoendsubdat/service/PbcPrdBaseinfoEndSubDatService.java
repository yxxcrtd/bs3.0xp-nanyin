package com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.service;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.poi.excel.WorkbookUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dto.PbcPrdBaseinfoSubDat;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dao.PbcPrdBaseinfoEndSubDatMapper;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dto.PbcPrdBaseinfoEndSubDat;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dto.PbcPrdBaseinfoEndSubDto;
import com.joyintech.tams.svreport.pbc.common.util.ExcelListToEntity;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class PbcPrdBaseinfoEndSubDatService extends BaseService<PbcPrdBaseinfoSubDat> {

    private static final String SHEET_BASE_INFO_SUB_NAME = "产品基本信息";
    private static final String SHEET_BASE_INFO_END_SUB_NAME = "产品终止信息";
    private static final String TIPS_SUFFIX_ERROR = "只支持2003版的excel导入，请修改文件重新导入";
    private static final String TIPS_SHEET_NAME_ERROR = "文件名称不规范，请确认文件名称后，重新导入";

    @Autowired
    private PbcPrdBaseinfoEndSubDatMapper pbcPrdBaseinfoEndSubDatMapper;

    /**
     *
     * 业务导入检查事件
     *
     * @return
     * @see
     * @since 1.0
     */
    public AjaxResult checkBaseInfoSubDat(String fileName) {
        //文件名称不规范，请确认文件名称后，重新导入。
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> validMessages = new ArrayList<>();
        if(!fileName.endsWith(".xls")){
            ajaxResult.setStatuCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            ValidMessage message = new ValidMessage();
            message.setMessage(TIPS_SUFFIX_ERROR);
            validMessages.add(message);
            ajaxResult.setErrorList(validMessages);
            return ajaxResult;
        }
        Workbook book = WorkbookUtil.createBook(fileName);
        Iterator<Sheet> sheetIterator = book.sheetIterator();
        Set<String> sheetNames = new HashSet<>();
        while(sheetIterator.hasNext()){
            Sheet sheet = sheetIterator.next();
            String sheetName = sheet.getSheetName();
            if(StringUtil.isNotEmpty(sheetName)){
                sheetNames.add(sheetName);
            }
        }
        if(!sheetNames.contains(SHEET_BASE_INFO_SUB_NAME) && !sheetNames.contains(SHEET_BASE_INFO_END_SUB_NAME)){
            ajaxResult.setStatuCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            ValidMessage message = new ValidMessage();
            message.setMessage(TIPS_SHEET_NAME_ERROR);
            validMessages.add(message);
            ajaxResult.setErrorList(validMessages);
            return ajaxResult;
        }
        return ajaxResult;

    }

    public List<ExcelValidDto> importBaseInfoSubDat(Map<String,Object> param){
        List<ExcelValidDto> errorList = new ArrayList<>();
        List<List<String>> dataList =(List<List<String>>) param.get("dataList");
        if(!StringUtil.isEmptyList(dataList)){
            List<PbcPrdBaseinfoEndSubDto> pbcPrdBaseinfoEndSubDtos;
            try {
                //将excel中的行数据转换为实体
                pbcPrdBaseinfoEndSubDtos = ExcelListToEntity.convertToEntity(dataList, PbcPrdBaseinfoEndSubDto.class);
            }catch (Exception e){
                throw new BusinessException(e);
            }
            //组装查询条件理财产品代码
            List<String> prodIds = new ArrayList<>();
            for (PbcPrdBaseinfoEndSubDto pbcPrdBaseinfoSubDatDto : pbcPrdBaseinfoEndSubDtos) {
                String prodId = pbcPrdBaseinfoSubDatDto.getProdId();
                if(StringUtil.isNotEmpty(prodId)){
                    if(!prodIds.contains(prodId)){
                        prodIds.add(prodId);
                    }
                }
            }
            List<PbcPrdBaseinfoEndSubDat> pbcPrdBaseinfoSubDats = new ArrayList<>();
            //根据理财产品代码查询数据
            if(CollectionUtil.isNotEmpty(prodIds)){
                pbcPrdBaseinfoSubDats = pbcPrdBaseinfoEndSubDatMapper.queryPbcPrdBaseInfoByprodId(prodIds);
            }
            //未报送、数据有修改
            List<PbcPrdBaseinfoEndSubDat> baseinfoSubDatsNoSend = new ArrayList<>();
            //未报送、数据需要删除的id
            List<PbcPrdBaseinfoEndSubDat> baseinfoSubDatsNoSendProdIds = new ArrayList<>();
            //已报送 数据有修改
            List<PbcPrdBaseinfoEndSubDat> baseinfoSubDatsSend = new ArrayList<>();
            //新建数据
            List<PbcPrdBaseinfoEndSubDat> baseinfoSubDatsNew = new ArrayList<>();
            for (PbcPrdBaseinfoEndSubDto pbcPrdBaseinfoSubDatDto : pbcPrdBaseinfoEndSubDtos) {
                Optional<PbcPrdBaseinfoEndSubDat> any = pbcPrdBaseinfoSubDats.stream().filter(baseinfoDat -> baseinfoDat.getProdId().equals(pbcPrdBaseinfoSubDatDto.getProdId())).findAny();
                if(any.isPresent()){
                    PbcPrdBaseinfoEndSubDat pbcPrdBaseinfoSubDat = any.get();
                    String sendStatus = pbcPrdBaseinfoSubDat.getSendStatus();
                    if(DictCodeListExt2.报送状态_未报送.getValue().equals(sendStatus)){
                        PbcPrdBaseinfoEndSubDat baseinfoSubDat = updatePbcPrdBaseinfoEndSubDat(pbcPrdBaseinfoSubDatDto, pbcPrdBaseinfoSubDat);
                        //设置人行产品代码
                        baseinfoSubDat.setPbcProdId(pbcPrdBaseinfoSubDat.getPbcProdId());
                        //设置报送状态为未报送
                        baseinfoSubDat.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
                        //设置数据状态为新建
                        baseinfoSubDat.setStatus(DictCodeList.数据状态_新建.getValue());
                        //设置报送流水号为查询的流水号
                        baseinfoSubDat.setSumitSeq(pbcPrdBaseinfoSubDat.getSumitSeq());
                        //设置压缩包编码
                        baseinfoSubDat.setDatZipCode(pbcPrdBaseinfoSubDat.getDatZipCode());
                        baseinfoSubDatsNoSend.add(baseinfoSubDat);
                        baseinfoSubDatsNoSendProdIds.add(baseinfoSubDat);
                    }else{
                        PbcPrdBaseinfoEndSubDat baseinfoSubDat = updatePbcPrdBaseinfoEndSubDat(pbcPrdBaseinfoSubDatDto, pbcPrdBaseinfoSubDat);
                        //设置人行产品代码
                        baseinfoSubDat.setPbcProdId(pbcPrdBaseinfoSubDat.getPbcProdId());
                        //设置报送状态为未报送
                        baseinfoSubDat.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
                        //设置数据状态为修改
                        baseinfoSubDat.setStatus(DictCodeList.数据状态_修改.getValue());
                        //设置报送流水号   重新生成报送流水号 TODO
                        baseinfoSubDat.setSumitSeq(UUID.randomUUID().toString());
                        //设置压缩包编码为空
                        baseinfoSubDat.setDatZipCode(null);
                        baseinfoSubDatsSend.add(baseinfoSubDat);
                    }
                }else{
                    //新建数据
                    PbcPrdBaseinfoEndSubDat baseinfoSubDat = updatePbcPrdBaseinfoEndSubDat(pbcPrdBaseinfoSubDatDto, new PbcPrdBaseinfoEndSubDat());
                    //设置人行产品代码
                    baseinfoSubDat.setPbcProdId(null);
                    //设置报送状态为未报送
                    baseinfoSubDat.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
                    //设置数据状态为新建
                    baseinfoSubDat.setStatus(DictCodeList.数据状态_新建.getValue());
                    //设置报送流水号   重新生成报送流水号 TODO
                    baseinfoSubDat.setSumitSeq(UUID.randomUUID().toString());
                    //设置压缩包编码为空
                    baseinfoSubDat.setDatZipCode(null);
                    baseinfoSubDatsNew.add(baseinfoSubDat);
                }
            }
            //删除未报送有修改的记录 并重新插入
            if(CollectionUtil.isNotEmpty(baseinfoSubDatsNoSendProdIds)){
                pbcPrdBaseinfoEndSubDatMapper.deleteBatchByProdId(baseinfoSubDatsNoSendProdIds);
                pbcPrdBaseinfoEndSubDatMapper.insertBatch(baseinfoSubDatsNoSend);
            }
            //更新已报送有修改数据
            if(CollectionUtil.isNotEmpty(baseinfoSubDatsSend)){
                pbcPrdBaseinfoEndSubDatMapper.updateBatchByPrimaryKey(baseinfoSubDatsSend);
            }
            //插入新建的数据
            if(CollectionUtil.isNotEmpty(baseinfoSubDatsNew)){
                pbcPrdBaseinfoEndSubDatMapper.insertBatch(baseinfoSubDatsNew);
            }
            return errorList;
        }else{
            ExcelValidDto excelValidDto = new ExcelValidDto();
            String errorMsg = "模板为空，请输入数据！";
            excelValidDto.setErrorInfo(errorMsg);
            errorList.add(excelValidDto);
            return errorList;
        }
    }


    private PbcPrdBaseinfoEndSubDat updatePbcPrdBaseinfoEndSubDat(PbcPrdBaseinfoEndSubDto pbcPrdBaseinfoSubDatDto,PbcPrdBaseinfoEndSubDat pbcPrdBaseinfoSubDat){
        BeanUtil.copyPropertiesIgnoreNull(pbcPrdBaseinfoSubDat,pbcPrdBaseinfoSubDatDto);
        return pbcPrdBaseinfoSubDat;
    }
}
