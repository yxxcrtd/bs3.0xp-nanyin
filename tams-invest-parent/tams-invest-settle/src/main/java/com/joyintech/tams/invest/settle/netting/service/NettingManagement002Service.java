package com.joyintech.tams.invest.settle.netting.service;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.StlCapacctInfoMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.service.MapperDelegateService;
import com.joyintech.tams.invest.common.threadservice.JasperUtil;
import com.joyintech.tams.invest.settle.dvporder.dto.DvpOrdersManagement001Dto;
import com.joyintech.tams.invest.settle.dvporder.dto.DvpOrdersProcessDto;
import com.joyintech.tams.invest.settle.netting.dto.NettingManagement001Dto;
import com.joyintech.tams.invest.settle.netting.dto.NettingProcessDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 姚轶文
 * @create 2019- 11-07 17:07
 */
@Service
public class NettingManagement002Service extends BaseService<NettingProcessDto> implements BaseProcessService<NettingProcessDto> {

    @Autowired
    private StlCapacctInfoMapper stlCapacctInfoMapper;

    @Autowired
    private JasperUtil jasperUtil;

    @Autowired
    private MapperDelegateService mapperDelegateService;



    public Page<NettingManagement001Dto> findTradeData(String[] bizCodes, String orderId) {
        Map<String, Object> params = new HashMap<>();
        params.put("bizCodes", bizCodes);
        params.put("orderId", orderId);
        List<NettingManagement001Dto> result = restTemplate.postForList(
                ServiceApplication.SETTLE_SERVICE_NAME + "/NettingManagement/findTradeData", params, NettingManagement001Dto.class);

        /**
         * resule出现2个条记录，并且期中一个的业务类型是空

         if(result!=null && result.size()>1)
         {
         for(NettingManagement001Dto dto : result)
         {
         if(dto.getPaBussTypeString()==null || dto.getPaBussTypeString().trim().equals(""))
         {
         result.remove(dto);
         }
         }
         }*/
       /* if(result!=null && result.size()>0)
        {
            if(result.get(0).getCounterAcctNo()==null || result.get(0).getCounterAcctNo().trim().equals(""))
            {
                result.get(0).setCounterAcctNo(result.get(0).getMyAcctNo());
            }

        }*/
       // 产品赎回  产品现金分红  产品到期  产品提前终止  产品提前赎回  产品强减等，如果收款账户为空，则为募集户
         if(result!=null && result.size()>0)
        {
            if(result.get(0).getCounterAcctNo()==null || result.get(0).getCounterAcctNo().trim().equals(""))
            {
                if(StringUtil.isNotEmpty(result.get(0).getRaiseAcctNo())){
                    result.get(0).setCounterAcctNo(result.get(0).getRaiseAcctNo());
                }
            }

        }

        if (StringUtil.isNotEmpty(orderId)) {
            NettingProcessDto orders = restTemplate.postForObject(
                    ServiceApplication.SETTLE_SERVICE_NAME + "/NettingManagement/findDvpOrdersByOrderId", orderId,
                    NettingProcessDto.class);
            RequestUtil.getSession().setAttribute("NettingProcessDto_orderDto", orders);
        }
        else {
            生成指令信息数据(result);
        }
        return new Page<>(result);
    }

    private void 生成指令信息数据(List<NettingManagement001Dto> result) {
        if (result == null || result.isEmpty()) {
            return;
        }
        // 1、 定义变量
        BigDecimal 金额 = BigDecimal.ZERO;
        String 划款类型;
        boolean 是否甲类户 = false;
        String 是否发送指令;
        String 支付日期;
        String 币种;
        String 付款账户;
        String 收款账户;
        String 组合代码;
        String 组合名称;
        String 收支方向 = DictCodeListExt1.收支方向_支出.getValue();
        // 2、 循环上面查询出来的List<交易信息>
        for (NettingManagement001Dto dto : result) {
            // 如果交易信息.平安业务类型 = 债券类资产买入，则
            if (DictCodeListExt1.收支方向_支出.getValue().equals(dto.getPs())) {
                // 金额 = 金额 + 交易信息.结算金额
                金额 = ArithmeticUtil.doAdd(金额, dto.getSettleAmt());
            }
            else {
                // 否则
                // 金额 = 金额 - 交易信息.结算金额
                金额 = ArithmeticUtil.subtract(金额, dto.getSettleAmt());
            }
            // 如果交易信息.账户类型 = 数据字典.账户类型.甲类户，则
            if (DictCodeListExt1.资金账户类型_甲类户.getValue().equals(dto.getAcctType())) {
                // 是否甲类户 = TRUE
                是否甲类户 = true;
            }
        }

        // 3、 如果计算出来的金额>0，则划款类型 = 托管划款，是否发送指令 = Y。
        if (金额.compareTo(BigDecimal.ZERO) > 0) {
            // 一体化对应etliu start
//            划款类型 = DictCodeList.划款类型_托管划款.getValue();
            //划款类型 = DictCodeListExt1.平安划款类型_托管划款.getValue();
            划款类型 = result.get(0).getPay();
            // 一体化对应etliu end
            是否发送指令 = DictCodeList.是否_是.getValue();

            // 4、 如果计算出来的金额>0，则付款账户 = List<交易信息>[0].本方资金账户，收款账户 = List<交易信息>[0].对手方资金账户
            付款账户 = result.get(0).getMyAcctNo();
            收款账户 = result.get(0).getCounterAcctNo();
        }
        else {
            // 如果计算出来的金额<0，并且是否甲类户 = TRUE，则划款类型 = 数据字典.划款类型.清算划款，是否发送指令 = Y。
            if (是否甲类户) {
                // 一体化对应etliu start
//                划款类型 = DictCodeList.划款类型_清算划款.getValue();
                划款类型 = DictCodeListExt1.平安划款类型_清算划款.getValue();
                // 一体化对应etliu end
                是否发送指令 = DictCodeList.是否_是.getValue();
            }
            else {
                // 如果计算出来的金额<0，并且是否甲类户 = FALSE，则划款类型 = 数据字典.划款类型.DVP划款，是否发送指令 = N。
                // 一体化对应etliu start
//                划款类型 = DictCodeList.划款类型_DVP划款.getValue();
                //划款类型 = DictCodeListExt1.平安划款类型_DVP划款.getValue();
                划款类型 = result.get(0).getPay();
                // 一体化对应etliu end
                是否发送指令 = DictCodeList.是否_是.getValue();
            }

            // 如果计算出来的金额<0，则付款账户 = List<交易信息>[0].对手资金账户，收款账户 = List<交易信息>[0].本方资金账户
            付款账户 = result.get(0).getCounterAcctNo();
            收款账户 = result.get(0).getMyAcctNo();
            收支方向 = DictCodeListExt1.收支方向_收入.getValue();
        }

        // 5、 组合代码 = List<交易信息>[0].组合代码
        组合代码 = result.get(0).getPortfolioId();
        // 组合名称 = List<交易信息>[0].组合名称
        组合名称 = result.get(0).getPortfolioName();
        // 支付日期 = List<交易信息>[0].交割日
        支付日期 = result.get(0).getSettleDate();
        // 币种 = List<交易信息>[0].币种
        币种 = result.get(0).getCcy();
        // 6、 按上面设置的变量，构建指令信息DTO，并展示到指令信息列表中。

        NettingProcessDto orderDto = new NettingProcessDto();
        // 指令信息DTO.是否发送指令 = 变量.是否发送指令
        orderDto.setIsNeedSend(是否发送指令);
        // 指令信息DTO.划款类型 = 变量.划款类型
        orderDto.setPayType(划款类型);
        // 指令信息DTO.支付日期 = 变量.支付日期
        orderDto.setSettleDate(DateUtil.parse(支付日期));
        // 指令信息DTO.金额 = 变量.金额
        orderDto.setSettleAmt(金额.abs());
        orderDto.setPs(收支方向);
        // 指令信息DTO.币种 = 变量.币种
        orderDto.setCcy(币种);
        // 指令信息DTO.组合代码 = 变量.组合代码
        orderDto.setPortfolioId(组合代码);
        orderDto.setPortfolioName(组合名称);
        StlCapacctInfo 收款账户Info = new StlCapacctInfo();
        收款账户Info = getStlCapacctInfo(币种, 收款账户, 收款账户Info);
        if(null!=收款账户Info){
            orderDto.setRecAcctNo(收款账户Info.getAcctNo());
            orderDto.setRecAcctName(收款账户Info.getAcctName());
            orderDto.setLargePayNo(收款账户Info.getLargePayno());
            orderDto.setInOutBank(收款账户Info.getInOutBank());
        }


        StlCapacctInfo 付款账户Info = new StlCapacctInfo();
        付款账户Info = getStlCapacctInfo(币种, 付款账户, 付款账户Info);
        if(null!=付款账户Info){
            orderDto.setPayAcctNo(付款账户Info.getAcctNo());
            orderDto.setPayAcctName(付款账户Info.getAcctName());
            if (金额.compareTo(BigDecimal.ZERO) > 0) {
                orderDto.setMyAcctNo(付款账户Info.getAcctNo());
            }
            else {
                orderDto.setCounterAcctNo(付款账户Info.getAcctNo());
            }

        }
        if(null!=收款账户Info){
            if (金额.compareTo(BigDecimal.ZERO) > 0) {
                orderDto.setCounterAcctNo(收款账户Info.getAcctNo());
            }
            else {
                orderDto.setMyAcctNo(收款账户Info.getAcctNo());
            }
        }
        /*orderDto.setPayAcctNo(付款账户Info.getAcctNo());
            orderDto.setPayAcctName(付款账户Info.getAcctName());
            if (金额.compareTo(BigDecimal.ZERO) > 0) {
                orderDto.setCounterAcctNo(收款账户Info.getAcctNo());
                orderDto.setMyAcctNo(付款账户Info.getAcctNo());
            }
            else {
                orderDto.setCounterAcctNo(付款账户Info.getAcctNo());
                orderDto.setMyAcctNo(收款账户Info.getAcctNo());
        }*/
        orderDto.setOrderStatus(DictCodeListExt1.指令状态_新建.getValue());
        RequestUtil.getSession().setAttribute("NettingProcessDto_orderDto", orderDto);
    }


    @Override
    public void doComplete(NettingProcessDto ordersDto, OperationType operationType) {
        if(operationType.equals(OperationType.DELETE) || operationType.equals(OperationType.REVOKE)){
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("bizCode", ordersDto.getActOrderId());
            param.put("bizCodeR", ordersDto.getActOrderId() + "R");
            param.put("orderStatus02", DictCodeListExt1.指令状态_撤销.getValue());
            restTemplate.postForObject(ServiceApplication.SETTLE_SERVICE_NAME + "/NettingManagement/updateStlDvpOrdersStatus", param, Integer.class);
            restTemplate.postForObject(ServiceApplication.SETTLE_SERVICE_NAME + "/NettingManagement/updateStlDvpTradeMatchStatus", param, Integer.class);
        }else{

            StlActOrder orders = new NettingProcessDto();
            BeanUtil.copyPropertiesIgnoreNull(orders, ordersDto);
            orders.setBusiType(DictCodeListExt3.平安业务类型_货币型产品轧差清算.getValue());
            List<StlActTradeMatch> matchs = new ArrayList<>();
            List<NettingManagement001Dto> tradeDatas = ordersDto.getTradeDatas();

            /*
            tradeDatas.forEach(trade -> {
                StlActTradeMatch match = new StlActTradeMatch();
                match.setOrderId(ordersDto.getOrderId());
                match.setActOrderId(orders.getActOrderId());
                match.setTradeId(trade.getBizCode());
                matchs.add(match);
            });
*/
            for(NettingManagement001Dto dto : tradeDatas)
            {
                StlActTradeMatch match = new StlActTradeMatch();
                match.setOrderId(ordersDto.getOrderId());
                match.setActOrderId(orders.getActOrderId());
                match.setTradeId(dto.getBizCode());
                matchs.add(match);
            }

            mapperDelegateService.insertAutoClassifyBatch(orders, matchs);

            jasperExecute(tradeDatas);
        }
    }


    public void jasperExecute(List<NettingManagement001Dto> tradeDatas) {
        if("001".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))){
            //生成DVP划款指令附件:调用自定义接口项目生成Jasper报表 add by caoxue 2019-08-14 start
            List<TrdProductDeal> tradeList = new ArrayList<>();
            tradeDatas.forEach(trade -> {
                TrdProductDeal trdProductDeal = new TrdProductDeal();
                trdProductDeal.setTradeId(trade.getBizCode());
                tradeList.add(trdProductDeal);
            });
            jasperUtil.jasperExecute(tradeList, restTemplate);
            //生成DVP划款指令附件:调用自定义接口项目生成Jasper报表 add by caoxue 2019-08-14 end
        }
    }

    private StlCapacctInfo getStlCapacctInfo(String 币种, String 收款账户, StlCapacctInfo 收款账户Info) {
        收款账户Info.setAcctNo(收款账户);
        收款账户Info.setCcy(币种);
        收款账户Info = stlCapacctInfoMapper.selectByPrimaryKey(收款账户Info);
        return 收款账户Info;
    }

    public int findMatchedNum(List<NettingManagement001Dto> tradeDatas) {
        int i = restTemplate.postForObject(ServiceApplication.SETTLE_SERVICE_NAME + "/NettingManagement/findMatchedNum", tradeDatas, Integer.class);
        return i;
    }
}
