/*
 * 文件名：FinFuturesService.java
 * 版权：Copyright JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：广州银行poc
 * 修改人：余飞
 * 修改时间：2020年11月09日
 * 修改内容：新建
 * 系统名称：广州银行poc
 */

package com.joyintech.tams.invest.asset.futures.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.asset.futures.dto.FinFuturesDto;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 期货标的信息service 〈功能详细描述〉
 *
 * @author yufei
 * @version 1.0 2020年11月09日
 * @see FinFuturesService
 * @since 1.0
 */
@Service
public class FinFuturesService extends CommonExportService<FinFuturesDto>
    implements BaseProcessService<FinFuturesDto> {

    /**
     * DateCalService
     */
    @Autowired(required = false)
    private DateCalService dateCalService;

    /**
     * 附件信息共通Service
     */
    @Autowired(required = false)
    private MstFileCommonService mstFileCommonService;

    @Override
    public void doInsert(FinFuturesDto entity, OperationType type) {
        updateFiles(entity);
    }

    private void updateFiles(FinFuturesDto entity) {
        //文件
        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
        mstFileInputParameter.setFileList(entity.getFileArray());
        mstFileInputParameter.setBussinessId(entity.getWorkflowId());
        if (mstFileCommonService != null) {
            mstFileCommonService.updateBusinessId(mstFileInputParameter);
        }
    }

    @Override
    public void doUpdate(FinFuturesDto entity) {
        updateFiles(entity);
    }

    @Override
    public void doComplete(FinFuturesDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME + "/FinFutures/" + action,
            entity, Integer.class);
    }

    /**
     * 修改/双击查看根据金融产品代码查询dto <功能详细描述>
     *
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinFuturesDto selectByFinprodId(String finprodId) {
        FinFuturesDto dto = restTemplate.getForObject(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinFutures/selectByFinprodId?finprodId="
            + finprodId, FinFuturesDto.class);
        if (dto != null) {
            String contractCode = dto.getFinprodMarketId();
//            dto.setChlFinprodId(contractCode.substring(contractCode.indexOf(".")));
            dto.setChlFinprodId(contractCode);
            if (StringUtil.equals(dto.getFuturesType(), DictCodeListExt3.期货类型_国债期货.getValue())) {
                if (dto.getFinprodMarketId().startsWith(DictCodeListExt3.期货合约代码前缀_TS.getValue())) {
                    dto.setFuturesContractPre(DictCodeListExt3.期货合约代码前缀_TS.getValue());
                    contractCode = contractCode.substring(
                        DictCodeListExt3.期货合约代码前缀_TS.getValue().length());
                }
                else if (dto.getFinprodMarketId().startsWith(
                    DictCodeListExt3.期货合约代码前缀_TF.getValue())) {
                    dto.setFuturesContractPre(DictCodeListExt3.期货合约代码前缀_TF.getValue());
                    contractCode = contractCode.substring(
                        DictCodeListExt3.期货合约代码前缀_TF.getValue().length());
                }
                else if (dto.getFinprodMarketId().startsWith(
                    DictCodeListExt3.期货合约代码前缀_T.getValue())) {
                    dto.setFuturesContractPre(DictCodeListExt3.期货合约代码前缀_T.getValue());
                    contractCode = contractCode.substring(
                        DictCodeListExt3.期货合约代码前缀_T.getValue().length());
                }
            }
            else {
                if (dto.getFinprodMarketId().startsWith(DictCodeListExt3.期货合约代码前缀_IC.getValue())) {
                    dto.setFuturesContractPre(DictCodeListExt3.期货合约代码前缀_IC.getValue());
                    contractCode = contractCode.substring(
                        DictCodeListExt3.期货合约代码前缀_IC.getValue().length());
                }
                else if (dto.getFinprodMarketId().startsWith(
                    DictCodeListExt3.期货合约代码前缀_IF.getValue())) {
                    dto.setFuturesContractPre(DictCodeListExt3.期货合约代码前缀_IF.getValue());
                    contractCode = contractCode.substring(
                        DictCodeListExt3.期货合约代码前缀_IF.getValue().length());
                }
                else if (dto.getFinprodMarketId().startsWith(
                    DictCodeListExt3.期货合约代码前缀_IH.getValue())) {
                    dto.setFuturesContractPre(DictCodeListExt3.期货合约代码前缀_IH.getValue());
                    contractCode = contractCode.substring(
                        DictCodeListExt3.期货合约代码前缀_IH.getValue().length());
                }
            }
            if(contractCode.contains(".")){
                contractCode = contractCode.substring(0,contractCode.indexOf("."));
            }
            dto.setContractCode(contractCode);
        }
        return dto;
    }

    /**
     * 获取下一个工作日
     *
     * @param parameters 画面参数
     * @return 下一个工作日
     * @see
     * @since 1.0
     */
    public String getNextWorkDay(Map<String, Object> parameters) {
        if (dateCalService != null) {
            Date tradeDate = DateUtil.parse((String)parameters.get("tradeDate"));
            List<String> holiday = dateCalService.getDealCalendarHoliday(tradeDate,
                DateUtil.addDays(tradeDate, 31), (String)parameters.get("calendarId"));
            Date tradingDay = dateCalService.calculationTradingDay(tradeDate, 3, holiday, "1");
            return DateUtil.format(tradingDay);
        }
        return null;
    }

    /**
     * <一句话功能简述> <功能详细描述>
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public FinFuturesDto findEntity(Map<String, Object> map) {
        return restTemplate.postForObject(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinFutures/findEntity", map,
            FinFuturesDto.class);
    }

    /**
     * 使用给定的sqlID检索分页数据。
     *
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<FinFuturesDto> selectForPage(Map<String, Object> pageParam)
        throws Exception {
    	if(StringUtil.isNotEmpty((String)pageParam.get("futuresType"))){
            List<String> dateList =StringUtil.splitToList(",", (String)pageParam.get("futuresType"));
            pageParam.put("futuresTypeList",dateList);
        }
    	if(StringUtil.isNotEmpty((String)pageParam.get("futuresContractId"))){
            List<String> dateList =StringUtil.splitToList(",", (String)pageParam.get("futuresContractId"));
            pageParam.put("futuresContractIdList",dateList);
        }
        Page<FinFuturesDto> page = restTemplate.postForPage(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinFutures/findPage", pageParam,
            FinFuturesDto.class);

        if (page != null && !StringUtil.isEmptyList(page.getRoot())) {
            for (FinFuturesDto resultDto : page.getRoot()) {
                if (StringUtil.isNotBlank(resultDto.getFinprodMarketId())
                    && StringUtil.contains(resultDto.getFinprodMarketId(), ".")) {
                    resultDto.setFinprodMarketId(
                        StringUtil.splitToList(".", resultDto.getFinprodMarketId()).get(0));
                }
            }
        }

        return page;
    }

    /**
     * 查询资产代码 <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFinprodMarketIdList(String param) {
        return restTemplate.getForList(ServiceApplication.ASSET_SERVICE_NAME
                                       + "/FinFutures/getFinprodMarketIdList?finprodMarketId="
                                       + param, SuggestDto.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/asset/futures/conf/export_conf.xml").dataSource(
            DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinFuturesDto> selectPageData(Map<String, Object> selectParam) {
        try {
            return selectForPage(selectParam);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if ((StringUtil.equals("lastTradeDate", propName) || StringUtil.equals("lastSettleDate",
            propName)) && null != value) {
            if (value instanceof Date) {
                cell.setCellValue(DateUtil.format((Date)value));
            }
            else if (value instanceof Long) {
                cell.setCellValue(DateUtil.format(new Date((Long)value)));
            }
        }
    }
}
