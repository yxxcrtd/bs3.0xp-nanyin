package com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.service;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dto.PbcPrdBaseinfoSubDat;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.service.PbcPrdBaseinfoSubDatService;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dao.PbcPrdBaseinfoEndSubDatMapper;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dto.PbcPrdBaseinfoEndSubDat;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dto.PbcPrdBaseinfoEndSubDto;
import com.joyintech.tams.svreport.pbc.common.util.ExcelListToEntity;
import com.joyintech.tams.systemtools.dto.CommonAjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class PbcPrdBaseinfoEndSubDatService extends BaseService<PbcPrdBaseinfoSubDat> {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(PbcPrdBaseinfoSubDatService.class);

    private static final String TIPS_SUFFIX_ERROR = "只支持2003版的excel导入，请修改文件重新导入";

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
        AjaxResult ajaxResult = new AjaxResult();
        if(!fileName.endsWith(".xls")){
            ValidMessage message = new ValidMessage();
            message.setMessage(TIPS_SUFFIX_ERROR);
            ajaxResult.setErrorList(Arrays.asList(message));
            return ajaxResult;
        }
        ajaxResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
        return ajaxResult;

    }

    public List<ExcelValidDto> importBaseInfoSubDat(Map<String,Object> param){
        List<ExcelValidDto> errorList = new ArrayList<>();
        List<List<String>> dataList =(List<List<String>>) param.get("dataList");
        if(!StringUtil.isEmptyList(dataList)){
            List<PbcPrdBaseinfoEndSubDto> pbcPrdBaseinfoEndSubDtos = new ArrayList<>();
            //将excel中的行数据转换为实体
            List<ExcelValidDto> excelValidDtos = convertToEntity(dataList, pbcPrdBaseinfoEndSubDtos);
            if(!excelValidDtos.isEmpty()){
                return excelValidDtos;
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
                Optional<PbcPrdBaseinfoEndSubDat> any = pbcPrdBaseinfoSubDats.stream()//todo
                        .filter(baseinfoDat -> baseinfoDat.getProdId().equals(pbcPrdBaseinfoSubDatDto.getProdId())).findAny();
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
                        //设置报送流水号   重新生成报送流水号
                        baseinfoSubDat.setSumitSeq(null);
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
                    //设置报送流水号   重新生成报送流水号
                    baseinfoSubDat.setSumitSeq(null);
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

    private List<ExcelValidDto> convertToEntity(List<List<String>> dataList,List<PbcPrdBaseinfoEndSubDto> pbcPrdBaseinfoSubDatDtos){
        List<ExcelValidDto> checkResult = new ArrayList<>();
        // 验证消息
        List<String> message = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(dataList)){
            for (int i = 0; i < dataList.size(); i++ ) {
                // 验证消息
                List<ExcelValidDto> errorMessage = new ArrayList<>();
                List<String> rowsData = dataList.get(i);
                PbcPrdBaseinfoEndSubDto dto = new PbcPrdBaseinfoEndSubDto();
                int row = 0;
                //序号
                String number = rowsData.get(row++);
                dto.setNumber(number);
                //人行产品代码
                String pbcProdId = rowsData.get(row++);
                dto.setPbcProdId(pbcProdId);
                //发行机构内部产品代码
                String prodId = rowsData.get(row++);
                if(checkIsNotEmpty(prodId,i,"发行机构内部产品代码",errorMessage)){
                    dto.setProdId(prodId);
                }
                //产品实际终止日期
                String mdate = rowsData.get(row++);
                if(checkIsNotEmpty(mdate,i,"产品实际终止日期",errorMessage)){
                    if(checkDate(mdate)){
                        dto.setMdate(DateUtil.parse(mdate));
                    }else{
                        errorMessage.add(new ExcelValidDto("产品实际终止日期",String.valueOf(i),"第"+i+"行:产品预计终止日期格式不正确！"));
                    }
                }
                //兑付客户收益率
                String cusYearYield = rowsData.get(row++);
                if(checkIsNotEmpty(cusYearYield,i,"兑付客户收益率",errorMessage)){
                    if(checkFigure(cusYearYield)){
                        dto.setCusYearYield(new BigDecimal(cusYearYield));
                    }else{
                        errorMessage.add(new ExcelValidDto("兑付客户收益率",String.valueOf(i),"第"+i+"行:兑付客户收益率非数字！"));
                    }
                }

                //币种代码
                String ccy = rowsData.get(row++);
                if(checkIsNotEmpty(ccy,i,"币种代码",errorMessage)){
                    dto.setCcy(ccy);
                }

                //兑付客户收益
                String customProfit = rowsData.get(row++);
                if(checkIsNotEmpty(customProfit,i,"兑付客户收益",errorMessage)){
                    if(checkFigure(customProfit)){
                        dto.setCustomProfit(new BigDecimal(customProfit));
                    }else{
                        errorMessage.add(new ExcelValidDto("兑付客户收益",String.valueOf(i),"第"+i+"行:兑付客户收益非数字！"));
                    }
                }
                //兑付客户收益折人民币
                String customProfitCny = rowsData.get(row++);
                if(checkIsNotEmpty(customProfit,i,"兑付客户收益折人民币",errorMessage)){
                    if(checkFigure(customProfitCny)){
                        dto.setCustomProfit(new BigDecimal(customProfitCny));
                    }else{
                        errorMessage.add(new ExcelValidDto("兑付客户收益折人民币",String.valueOf(i),"第"+i+"行:兑付客户收益折人民币非数字！"));
                    }
                }

                if(CollectionUtil.isNotEmpty(message)){
                    checkResult.addAll(errorMessage);
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
     * 判断是否为空
     * @return 返回boolean
     */
    private boolean checkIsNotEmpty(String value,int i,String desc,List<ExcelValidDto> errorMessage){
        if(StrUtil.isEmpty(value)){
            errorMessage.add(new ExcelValidDto(desc,String.valueOf(i),"第"+i+"行:"+desc+"不能为空！"));
            return false;
        }
        return true;
    }

    /**
     * 日期格式校验
     *
     * @param
     * @return 返回boolean
     */
    private boolean checkDate(String date) {
        boolean flag = true;
        if(!DateUtil.isDateStr(date, DateUtil.DEFAULT_DATE_FORMAT_EN)){
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
