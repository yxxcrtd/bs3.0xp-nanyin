package com.joyintech.service.portfolio.actualtimecapital.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.actualtimecapital.dao.ActualTimeCapitalMapper;
import com.joyintech.service.portfolio.actualtimecapital.dto.ActualTimeCapitalDto;
import com.joyintech.service.portfolio.actualtimecapital.dto.CashArtDealDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrCashPositionFlowDto;

/**
 * @program: tams-invest-parent
 * @description: ActualTimeAssetService
 * @author: yanya
 * @create: 2019-10-15
 **/
@Service
public class ActualTimeCapitalService extends BaseService<ActualTimeCapitalDto> {
    /**
     * 实时资金头寸mapper
     */
    @Autowired
    private ActualTimeCapitalMapper actualTimeCapitalMapper;

    /**
     * 资金账户下拉框
     *
     * @param map 输入参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAcctInfo(Map<String,Object> map) {
        return actualTimeCapitalMapper.getAcctInfo(map);
    }
    
    /**
     * 获取现金余额调整
     *
     * @param dto 输入参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public CashArtDealDto getCashArtDealInfo(CashArtDealDto dto) {
        List<CashArtDealDto> list = actualTimeCapitalMapper.getCashArtDealInfo(dto);
        if(StringUtil.isEmptyList(list)) {
            return null;
        }
        else {
            return list.get(0);
        }
        //return actualTimeCapitalMapper.getCashArtDealInfo(dto);
    }

    /**
     * 画面查询方法检索分页数据。返回类型是Service的泛型。
     * @param pageParam pageParam
     * @param page 页码
     * @param length 页面长度
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    public Page<ActualTimeCapitalDto> selectForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        //组合现金余额表持仓类型
        List<String> ptlHodingTypeList = Arrays.asList(DictCodeList.持仓类型_实际.getValue(), 
            DictCodeListExt1.持仓类型_不可支取.getValue(), DictCodeListExt1.持仓类型_预占用.getValue());
        
        pageParam.put("ptlHodingTypeList", ptlHodingTypeList);
        //组合现金流水表流水类型
        List<String> ptlFlowHodingTypeList =Arrays.asList(DictCodeList.持仓类型_可用.getValue(), DictCodeListExt1.持仓类型_人工调整.getValue());
        pageParam.put("ptlFlowHodingTypeList", ptlFlowHodingTypeList);
        //指令现金余额表持仓类型
        List<String> etrHodingTypeList = Arrays.asList(DictCodeListExt1.持仓类型_委托指令可用.getValue(), DictCodeListExt1.持仓类型_委托指令预增.getValue());
        pageParam.put("etrHodingTypeList", etrHodingTypeList);
        
        return super.queryForPage(page, length, () -> actualTimeCapitalMapper.findPage(pageParam));
    }
    
    /**
     * 现金余额调整
     * @param cashArtDealDto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    public void insertAdjustmentBalance(CashArtDealDto cashArtDealDto)
        throws Exception {
        String seqno = getSeqNo();
        cashArtDealDto.setSeqno(seqno);
        //现金头寸调整表
        actualTimeCapitalMapper.insertCashArtDealInfoData(cashArtDealDto);
        //组合现金流水
        actualTimeCapitalMapper.insertPtlCashPositionFlowInfoData(getPtlCashPositionFlowDto(cashArtDealDto));
        //指令现金流水
        actualTimeCapitalMapper.insertEtrCashPositionFlowInfoData(getEtrCashPositionFlowDto(cashArtDealDto));
    }
    
    /**
     * 生成流水号获取
     * 
     * @return
     */
    public String getSeqNo() {
        return IDGenerator.generate();
    }
    
    /**
     * 获取组合现金流水dto
     * <功能详细描述>
     * @param cashArtDealDto
     * @return
     * @see
     * @since 1.0
     */
    public PtlCashPositionFlowDto getPtlCashPositionFlowDto(CashArtDealDto cashArtDealDto) {
        PtlCashPositionFlowDto ptlCashPositionFlowDto = new PtlCashPositionFlowDto();
        //流水号 
        ptlCashPositionFlowDto.setSeqno(getSeqNo());
        //组合代码    
        ptlCashPositionFlowDto.setPortfolioId(cashArtDealDto.getPortfolioId());
        //账户  
        ptlCashPositionFlowDto.setAcctId(cashArtDealDto.getAcctId());
        //持仓类型    
        ptlCashPositionFlowDto.setHodingType(DictCodeListExt1.持仓类型_人工调整.getValue());;
        //日期  
        ptlCashPositionFlowDto.setCdate(cashArtDealDto.getAdjDate());
        //币种  
        ptlCashPositionFlowDto.setCcy(cashArtDealDto.getCcy());
        //数量  
        ptlCashPositionFlowDto.setAmount(cashArtDealDto.getAdjAmt());
        //指令编号/交易编号   
        ptlCashPositionFlowDto.setOrderId(cashArtDealDto.getSeqno());
        //通道金融产品代码    
        ptlCashPositionFlowDto.setChlFinprodId("");
        //拆分标识    
        ptlCashPositionFlowDto.setSplitType("");
        return ptlCashPositionFlowDto;
    }
    
    /**
     * 获取指令现金流水dto
     * <功能详细描述>
     * @param cashArtDealDto
     * @return
     * @see
     * @since 1.0
     */
    public EtrCashPositionFlowDto getEtrCashPositionFlowDto(CashArtDealDto cashArtDealDto) {
        EtrCashPositionFlowDto etrCashPositionFlowDto = new EtrCashPositionFlowDto();
        //流水号 
        etrCashPositionFlowDto.setSeqno(getSeqNo());
        //组合代码    
        etrCashPositionFlowDto.setPortfolioId(cashArtDealDto.getPortfolioId());
        //账户  
        etrCashPositionFlowDto.setAcctId(cashArtDealDto.getAcctId());
        //持仓类型    
        etrCashPositionFlowDto.setHodingType(DictCodeListExt1.持仓类型_人工调整.getValue());;
        //日期  
        etrCashPositionFlowDto.setCdate(cashArtDealDto.getAdjDate());
        //币种  
        etrCashPositionFlowDto.setCcy(cashArtDealDto.getCcy());
        //数量  
        etrCashPositionFlowDto.setAmount(cashArtDealDto.getAdjAmt());
        //业务编号
        etrCashPositionFlowDto.setOrderId(cashArtDealDto.getSeqno());
        //通道金融产品代码
        etrCashPositionFlowDto.setChlFinprodId("");
        //冲正标识
        etrCashPositionFlowDto.setOffsetFlag("");
        //冲销业务编号
        etrCashPositionFlowDto.setOffsetId("");
        return etrCashPositionFlowDto;
    }
}
