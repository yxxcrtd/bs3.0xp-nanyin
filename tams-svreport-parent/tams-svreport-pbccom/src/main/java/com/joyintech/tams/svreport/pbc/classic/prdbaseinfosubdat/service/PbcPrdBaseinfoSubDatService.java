package com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.service;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.poi.excel.WorkbookUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dao.PbcPrdBaseinfoSubDatMapper;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dto.PbcPrdBaseinfoSubDat;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dto.PbcPrdBaseinfoSubDatDto;
import com.joyintech.tams.svreport.pbc.common.util.ExcelListToEntity;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class PbcPrdBaseinfoSubDatService extends BaseService<PbcPrdBaseinfoSubDat> {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(PbcPrdBaseinfoSubDatService.class);

    private static final String TIPS_SUFFIX_ERROR = "只支持2003版的excel导入，请修改文件重新导入";

    @Autowired
    private PbcPrdBaseinfoSubDatMapper pbcPrdBaseinfoSubDatMapper;

    /**
     *
     * 业务导入检查事件
     *
     * @return
     * @see
     * @since 1.0
     */
    public AjaxResult checkBaseInfoSubDat(String fileName) {
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> validMessages = new ArrayList<>();
        if(!fileName.endsWith(".xls")){
            return new AjaxResult(TIPS_SUFFIX_ERROR, MessageType.错误消息);
        }
        return new AjaxResult();

    }

    public List<ExcelValidDto> importBaseInfoSubDat(Map<String,Object> param){
        List<ExcelValidDto> errorList = new ArrayList<>();
        List<List<String>> dataList =(List<List<String>>) param.get("dataList");
        if(!StringUtil.isEmptyList(dataList)){
            List<PbcPrdBaseinfoSubDatDto> pbcPrdBaseinfoSubDatDtos = new ArrayList<>();
            //将excel中的行数据转换为实体
            List<ExcelValidDto> excelValidDtos = convertToEntity(dataList, pbcPrdBaseinfoSubDatDtos);
            if(!excelValidDtos.isEmpty()){
                return excelValidDtos;
            }
            //组装查询条件理财产品代码
            List<String> prodIds = new ArrayList<>();
            for (PbcPrdBaseinfoSubDatDto pbcPrdBaseinfoSubDatDto : pbcPrdBaseinfoSubDatDtos) {
                String prodId = pbcPrdBaseinfoSubDatDto.getProdId();
                if(StringUtil.isNotEmpty(prodId)){
                    if(!prodIds.contains(prodId)){
                        prodIds.add(prodId);
                    }
                }
            }
            List<PbcPrdBaseinfoSubDat> pbcPrdBaseinfoSubDats = new ArrayList<>();
            //根据理财产品代码查询数据
            if(CollectionUtil.isNotEmpty(prodIds)){
                pbcPrdBaseinfoSubDats = pbcPrdBaseinfoSubDatMapper.queryPbcPrdBaseInfoByprodId(prodIds);
            }
            //未报送、数据有修改
            List<PbcPrdBaseinfoSubDat> baseinfoSubDatsNoSend = new ArrayList<>();
            //未报送、数据需要删除的id
            List<PbcPrdBaseinfoSubDat> baseinfoSubDatsNoSendProdIds = new ArrayList<>();
            //已报送 数据有修改
            List<PbcPrdBaseinfoSubDat> baseinfoSubDatsSend = new ArrayList<>();
            //新建数据
            List<PbcPrdBaseinfoSubDat> baseinfoSubDatsNew = new ArrayList<>();
            for (PbcPrdBaseinfoSubDatDto pbcPrdBaseinfoSubDatDto : pbcPrdBaseinfoSubDatDtos) {
                Optional<PbcPrdBaseinfoSubDat> any = pbcPrdBaseinfoSubDats.stream().filter(baseinfoDat -> baseinfoDat.getProdId().equals(pbcPrdBaseinfoSubDatDto.getProdId())).findAny();
                if(any.isPresent()){
                    PbcPrdBaseinfoSubDat pbcPrdBaseinfoSubDat = any.get();
                    String sendStatus = pbcPrdBaseinfoSubDat.getSendStatus();
                    if(DictCodeListExt2.报送状态_未报送.getValue().equals(sendStatus)){
                        PbcPrdBaseinfoSubDat baseinfoSubDat = updatePbcPrdBaseinfoSubDat(pbcPrdBaseinfoSubDatDto, pbcPrdBaseinfoSubDat);
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
                        PbcPrdBaseinfoSubDat baseinfoSubDat = updatePbcPrdBaseinfoSubDat(pbcPrdBaseinfoSubDatDto, pbcPrdBaseinfoSubDat);
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
                    PbcPrdBaseinfoSubDat baseinfoSubDat = updatePbcPrdBaseinfoSubDat(pbcPrdBaseinfoSubDatDto, new PbcPrdBaseinfoSubDat());
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
                pbcPrdBaseinfoSubDatMapper.deleteByProdid(baseinfoSubDatsNoSendProdIds);
                pbcPrdBaseinfoSubDatMapper.insertBatch(baseinfoSubDatsNoSend);
            }
            //更新已报送有修改数据
            if(CollectionUtil.isNotEmpty(baseinfoSubDatsSend)){
                pbcPrdBaseinfoSubDatMapper.updateBatchByPrimaryKey(baseinfoSubDatsSend);
            }
            //插入新建的数据
            if(CollectionUtil.isNotEmpty(baseinfoSubDatsNew)){
                pbcPrdBaseinfoSubDatMapper.insertBatch(baseinfoSubDatsNew);
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


    private PbcPrdBaseinfoSubDat updatePbcPrdBaseinfoSubDat(PbcPrdBaseinfoSubDatDto pbcPrdBaseinfoSubDatDto,PbcPrdBaseinfoSubDat pbcPrdBaseinfoSubDat){
        BeanUtil.copyPropertiesIgnoreNull(pbcPrdBaseinfoSubDat,pbcPrdBaseinfoSubDatDto);
        return pbcPrdBaseinfoSubDat;
    }

    private List<ExcelValidDto> convertToEntity(List<List<String>> dataList,List<PbcPrdBaseinfoSubDatDto> pbcPrdBaseinfoSubDatDtos){
        List<ExcelValidDto> checkResult = new ArrayList<>();
        // 验证消息
        List<String> message = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(dataList)){
            for (int i = 0; i < dataList.size(); i++ ) {
                List<String> rowsData = dataList.get(i);
                PbcPrdBaseinfoSubDatDto dto = new PbcPrdBaseinfoSubDatDto();
                int row = 0;
                //序号
                String number = rowsData.get(row++);
                dto.setNumber(number);
                //产品代码
                String pbcProdId = rowsData.get(row++);
                dto.setPbcProdId(pbcProdId);
                //发行机构代码
                String issuePartyId = rowsData.get(row++);
                dto.setIssuePartyId(issuePartyId);
                //产品名称
                String prodName = rowsData.get(row++);
                dto.setProdName(prodName);
                //产品品种
                String prodType = rowsData.get(row++);
                dto.setProdType(prodType);
                //产品类型
                String ivstorientation = rowsData.get(row++);
                dto.setIvstorientation(ivstorientation);
                //产品品牌
                String prodBrand = rowsData.get(row++);
                dto.setProdBrand(prodBrand);
                //产品期次
                String prodStage = rowsData.get(row++);
                if(checkFigure(prodStage)){
                    dto.setProdStage(new BigDecimal(prodStage));
                }else{
                    message.add("产品期次非数字");
                }
                //发行机构内部产品代码
                String prodId = rowsData.get(row++);
                dto.setProdId(prodId);
                //募集方式
                String raiseType = rowsData.get(row++);
                dto.setRaiseType(raiseType);
                //管理方式
                String manageType = rowsData.get(row++);
                dto.setManageType(manageType);
                //运行方式
                String operateMode = rowsData.get(row++);
                dto.setOperateMode(operateMode);
                //募集起始日期
                String raiseVdate01 = rowsData.get(row++);
                if(checkDate(raiseVdate01)){
                    dto.setRaiseVdate01(DateUtil.parse(raiseVdate01));
                }else{
                    message.add("募集起始日期格式不正确");
                }
                //募集结束日期
                String raiseEnddate01 = rowsData.get(row++);
                if(checkDate(raiseEnddate01)){
                    dto.setRaiseVdate01(DateUtil.parse(raiseEnddate01));
                }else{
                    message.add("募集结束日期格式不正确");
                }
                //发行机构提前终止权标识
                String termRightFlag = rowsData.get(row++);
                dto.setTermRightFlag(termRightFlag);
                //客户赎回权标识
                String cusRedeemFlag = rowsData.get(row++);
                dto.setCusRedeemFlag(cusRedeemFlag);
                //产品增信标识
                String creditFlag = rowsData.get(row++);
                dto.setCreditFlag(creditFlag);
                //境内托管机构代码
                String inDepCode = rowsData.get(row++);
                dto.setInDepCode(inDepCode);
                //境外托管机构国别
                String outDepCtry = rowsData.get(row++);
                dto.setOutDepCtry(outDepCtry);
                //境外托管机构名称
                String inDepName = rowsData.get(row++);
                dto.setInDepName(inDepName);
                //产品起始日期
                String vdate = rowsData.get(row++);
                if(checkDate(vdate)){
                    dto.setVdate(DateUtil.parse(vdate));
                }else{
                    message.add("产品起始日期格式不正确");
                }
                //产品预计终止日期
                String mdate = rowsData.get(row++);
                if(checkDate(mdate)){
                    dto.setMdate(DateUtil.parse(mdate));
                }else{
                    message.add("产品预计终止日期格式不正确");
                }
                //受托职责
                String fiduciaryDuty = rowsData.get(row++);
                dto.setFiduciaryDuty(fiduciaryDuty);
                //分级产品标识
                String isConstracte = rowsData.get(row++);
                dto.setIsConstracte(isConstracte);
                //收益权转让产品标识
                String transferType = rowsData.get(row++);
                dto.setTransferType(transferType);
                //货币市场基金标识
                String transferType1 = rowsData.get(row++);
                dto.setTransferType1(transferType1);
                //其他产品标识1
                String transferType2 = rowsData.get(row++);
                dto.setTransferType2(transferType2);
                //其他产品标识2
                String transferType3 = rowsData.get(row++);
                dto.setTransferType3(transferType3);
                //其他产品标识3
                String transferType4 = rowsData.get(row++);
                dto.setTransferType1(transferType4);
                //募集资金币种
                String raiseCcy = rowsData.get(row++);
                dto.setRaiseCcy(raiseCcy);
                //兑付本金币种
                String cashPrinCcy = rowsData.get(row++);
                dto.setCashPrinCcy(cashPrinCcy);
                //兑付收益币种
                String cashProfCcy = rowsData.get(row++);
                dto.setCashProfCcy(cashProfCcy);
                //增信机构类型
                String creditType = rowsData.get(row++);
                dto.setCreditType(creditType);
                //增信形式
                String creditMode = rowsData.get(row++);
                dto.setCreditMode(creditMode);
                if(CollectionUtil.isNotEmpty(message)){
                    checkResult.add(new ExcelValidDto(rowsData.get(0), String.valueOf(i+2),
                            StringUtil.joinString(message, GlobalConstant.SEMICOLON)));
                }else{
                    pbcPrdBaseinfoSubDatDtos.add(dto);
                }
            }
        }
        return checkResult;

    }

    /**
     * 判断是否为数字
     *
     * @param
     * @return 返回boolean
     */
    private boolean checkFigure(String figure) {
        try{
            new BigDecimal(figure);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /**
     * 日期格式校验
     *
     * @param
     * @return 返回boolean
     */
    private boolean checkDate(String date) {
        boolean flag = true;
        if(!DateUtil.isDateStr(date, DateUtil.DEFAULT_OTHER_FORMAT)){
            flag = false;
        }else{
            date = date.replace("-", "").replace("/", "");
            //年
            int year = Integer.valueOf(date.substring(0, 4));
            //月
            int month = Integer.valueOf(date.substring(4, 6));
            //日
            int day = Integer.valueOf(date.substring(6));
            if(month < 1 || month > 12){
                flag = false;
            }else{
                if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                    if(day < 1 || day > 31){
                        flag = false;
                    }
                }else if(month == 4 || month == 6 || month == 9 || month == 11){
                    if(day < 1 || day > 30){
                        flag = false;
                    }
                }else if(month == 2){
                    if((year % 4 == 0 && year % 100 != 0) ||year % 400 == 0){
                        if(day < 1 || day > 29){
                            flag = false;
                        }
                    }else{
                        if(day < 1 || day > 28){
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }
}
