package com.joyintech.service.entrust.common.service;

import com.joyintech.background.component.common.pack.common.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.EtrEntrustMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.entrust.impl.BaseEtrInvestInfoServiceImpl;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装添加流程中数据需求的增删改
 */
@Component
public class WFlowCommonService {
    private static BaseEtrInvestInfoServiceImpl baseEtrInvestInfoServiceImpl; // 流程数据相关修改
    private static BaseEtrCashPositionCommService baseEtrCashPositionCommService; // 持仓处理
    private static EtrEntrustMapper etrEntrustMapper; // 指令

    /**
     * 操作类型为submit时执行新增流程数据 & 持仓处理
     * @param operateType
     * @param dto
     */
    public static void doInsertFlow(String operateType, BaseEtrInvestInfoDto dto){
        if ("submit".equals(operateType)) {
            if(StringUtil.isEmpty(dto.getWorkFlowId())){
                throw new RuntimeException("WorkFlowId初始化不能为空!");
            }
            insertFlow(dto);

            if(baseEtrCashPositionCommService != null){
                EntrustCommonDto entrustCommonDto = new EntrustCommonDto();
                entrustCommonDto.setEtrEntrust(dto.getEtrEntrust());
                entrustCommonDto.setEtrEntrustAdd(dto.getEtrEntrustAdd());
                // 债券扣减指令现金时，通过清算日期计算单位利息 + 交易净价  = 全价
                // 再用全价* 券面计算金额值
                // 所以非T+0指令金额有问题 修复
                if(dto.getEtrEntrust()!=null&&dto.getEtrEntrustAdd()!=null&&DictCodeListExt1.金融产品类型_投管用_债券.getValue().equals(dto.getEtrEntrust().getFinprodType2())
                    &&StringUtil.isNotEmpty(dto.getEtrEntrustAdd().getSettleSpreed())){
                    entrustCommonDto.setSettleDate(DateUtil.addDay(dto.getEtrEntrust().getVdate(),dto.getEtrEntrustAdd().getSettleSpreed().intValue()));
                }
                baseEtrCashPositionCommService.entrustStartExecute(entrustCommonDto);
            }
        }
    }

    /**
     * 操作类型不为revoke、delete时执行删除 & 新增 & 持仓处理
     * @param operateType
     * @param dto
     */
    public static void doUpdateFlow(String operateType, BaseEtrInvestInfoDto dto){
        if (!StringUtil.equals("revoke", operateType) && !StringUtil.equals("delete", operateType)) {
            deleteFlow(dto.getWorkFlowId());
            insertFlow(dto);

            if(baseEtrCashPositionCommService != null){
                EntrustCommonDto entrustCommonDto = new EntrustCommonDto();
                entrustCommonDto.setEtrEntrust(dto.getEtrEntrust());
                entrustCommonDto.setEtrEntrustAdd(dto.getEtrEntrustAdd());
                // 债券扣减指令现金时，通过清算日期计算单位利息 + 交易净价  = 全价
                // 再用全价* 券面计算金额值
                // 所以非T+0指令金额有问题 修复
                if(dto.getEtrEntrust()!=null&&dto.getEtrEntrustAdd()!=null&&DictCodeListExt1.金融产品类型_投管用_债券.getValue().equals(dto.getEtrEntrust().getFinprodType2())
                        &&StringUtil.isNotEmpty(dto.getEtrEntrustAdd().getSettleSpreed())){
                    entrustCommonDto.setSettleDate(DateUtil.addDay(dto.getEtrEntrust().getVdate(),dto.getEtrEntrustAdd().getSettleSpreed().intValue()));
                }
                // 持仓处理
                baseEtrCashPositionCommService.entrustStartExecute(entrustCommonDto);
            }
        }
    }

    public void entrustStartExecute(BaseEtrInvestInfoDto dto){
        if(baseEtrCashPositionCommService != null){
            EntrustCommonDto entrustCommonDto = new EntrustCommonDto();
            entrustCommonDto.setEtrEntrust(dto.getEtrEntrust());
            entrustCommonDto.setEtrEntrustAdd(dto.getEtrEntrustAdd());
            // 持仓处理
            baseEtrCashPositionCommService.entrustStartExecute(entrustCommonDto);
        }
    }
    
    /**
     * 修改状态为已退回 & 持仓关闭
     * @param workflowId
     * @param entrustId
     */
    public static void doBackFlow(String workflowId, String entrustId){
        if (baseEtrCashPositionCommService != null) {
            // 持仓关闭
            baseEtrCashPositionCommService.entrustCloseExecute(entrustId);
        }
        // ZGZGST-1472:先持仓关闭再删除流程记录 fuyongsheng 20210825 edit start
        deleteFlow(workflowId);
        // ZGZGST-1472 fuyongsheng 20210825 edit end
    }
    
    public static void doCompleteFlow(String workflowId){
        deleteFlow(workflowId);
    }

    public static boolean insertFlow(BaseEtrInvestInfoDto dto){
        return baseEtrInvestInfoServiceImpl.insertWithQuery(dto);
    }

    public static boolean deleteFlow(String workflowId) {
        Map<String, Object> param = new HashMap<>();
        param.put("workflowId", workflowId);
        return baseEtrInvestInfoServiceImpl.deleteDelegateFlow(param);
    }

    @Autowired
    public void setBaseEtrInvestInfoServiceImpl(BaseEtrInvestInfoServiceImpl baseEtrInvestInfoServiceImpl){
        WFlowCommonService.baseEtrInvestInfoServiceImpl=baseEtrInvestInfoServiceImpl;
    }
    
    @Autowired(required = false)
    public void setBaseEtrCashPositionCommService(BaseEtrCashPositionCommService baseEtrCashPositionCommService){
        WFlowCommonService.baseEtrCashPositionCommService=baseEtrCashPositionCommService;
    }

    @Autowired
    public void setEtrEntrustMapper(EtrEntrustMapper etrEntrustMapper){
        WFlowCommonService.etrEntrustMapper=etrEntrustMapper;
    }
}
