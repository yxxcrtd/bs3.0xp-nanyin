package com.joyintech.interf.bankname.cfetsup.secdialogquote.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.interf.bankname.cfetsup.secdialogquote.dto.FxSecdialogQuote002Dto;
import com.joyintech.interf.bankname.cfetsup.secdialogquote.service.FxSecdialogQuote002Service;
import com.joyintech.tams.common.base.dao.EtrEntrustMapper;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 现券买卖报价验证
 *auther lixiang
 * date 2020-01-10
 */
@Component
@Transactional
public class FxSecdialogQuote002Validator implements BeanValidator {
    @Autowired
    private FxSecdialogQuote002Service fxSecdialogQuoteService;
    @Autowired
    private EtrEntrustMapper etrEntrustMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FxSecdialogQuote002Dto dto = (FxSecdialogQuote002Dto ) value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        if(StringUtil.equals(dto.getPageModel(),"add")) {//新增
            //委托编号
            if (StringUtils.isEmpty(dto.getTrustid())) {
                errorList.add(new ValidMessage("委托编号不能为空！"));
                return errorList;
            }
            //组合代码
            if (StringUtils.isEmpty(dto.getPortfolioId())) {
                errorList.add(new ValidMessage("组合代码不能为空！"));
                return errorList;
            }
            //券面总额
            EtrEntrust trust = etrEntrustMapper.selectByPrimaryKey(dto.getTrustid());
            if (null != dto.getPrinamt()) {
                if(new BigDecimal(0).compareTo(dto.getPrinamt()) >= 0){
                    errorList.add(new ValidMessage("券面总额必须为正数"));
                }else if(dto.getPrinamt().divide(new BigDecimal(100000)) != new BigDecimal(0)){
                    errorList.add(new ValidMessage("券面总额必须为十万的整数倍"));
                }
                //报价券面总额不能超过委托的券面总额
                boolean flag =true;
                if(trust.getEntrustAmt().compareTo(dto.getPrinamt()) < 0){
                    errorList.add(new ValidMessage("券面总额不能大于委托的券面总额"));
                }
            }
            //债券须在RDI存在
            //模糊指令买入，下面的逻辑需判断勾选值
            // 报价的净价应该小于等于委托净价，到期收益率大于等于委托到期收益率，行权收益率大于等于委托行权收益率
            //全价总额按照净价校验，也是小于等于
            if(StringUtil.equals("P",dto.getPs())){

            }
            //卖出，净价需要大于等于委托的净价
            if (dto.getCleanprice().compareTo(trust.getPrice())  < 0){
                errorList.add(new ValidMessage("净价不能小于委托的净价"));
            }
            //对手方交易员不能与本方相同
            if(StringUtil.equals(dto.getHomecstpid(),dto.getCountercstpid())){
                errorList.add(new ValidMessage("对手方交易账户不能与本方交易账户相同"));
            }
            return errorList;
        }else if(StringUtil.equals(dto.getPageModel(),"edit")) {//修改

        }else if(StringUtil.equals(dto.getPageModel(),"delt")) {//删除
            if(StringUtil.equals(dto.getTradestatus(),"added") || StringUtil.equals(dto.getTradestatus(),"dealed") || StringUtil.equals(dto.getTradestatus(),"dealing") || StringUtil.equals(dto.getTradestatus(),"preapply") || StringUtil.equals(dto.getTradestatus(),"revoked") || StringUtil.equals(dto.getTradestatus(),"revoking") || StringUtil.equals(dto.getTradestatus(),"terminated") || StringUtil.equals(dto.getTradestatus(),"terminating") || StringUtil.equals(dto.getTradestatus(),"updated") || StringUtil.equals(dto.getTradestatus(),"updating") ){
                errorList.add(new ValidMessage("交易状态为已新增、已成交、成交中、待回复、已撤回、撤回中、已终止、终止中、已修改、修改中的数据不允许被删除"));
            }
        }else if(StringUtil.equals(dto.getPageModel(),"cancel")) {//撤回报价
            if(StringUtil.equals(dto.getTradestatus(),"adding") || StringUtil.equals(dto.getTradestatus(),"dealed") || StringUtil.equals(dto.getTradestatus(),"dealing") || StringUtil.equals(dto.getTradestatus(),"preapply") || StringUtil.equals(dto.getTradestatus(),"revoked") || StringUtil.equals(dto.getTradestatus(),"revoking") || StringUtil.equals(dto.getTradestatus(),"terminated") || StringUtil.equals(dto.getTradestatus(),"terminating") || StringUtil.equals(dto.getTradestatus(),"updating") ){
                errorList.add(new ValidMessage("交易状态为新增中、已成交、成交中、待回复、已撤回、撤回中、已终止、终止中、修改中的数据不允许被撤回"));
            }
        }else if(StringUtil.equals(dto.getPageModel(),"update")) {//修改报价
            if(StringUtil.equals(dto.getTradestatus(),"adding") || StringUtil.equals(dto.getTradestatus(),"dealed") || StringUtil.equals(dto.getTradestatus(),"dealing") || StringUtil.equals(dto.getTradestatus(),"preapply") || StringUtil.equals(dto.getTradestatus(),"revoked") || StringUtil.equals(dto.getTradestatus(),"revoking") || StringUtil.equals(dto.getTradestatus(),"terminated") || StringUtil.equals(dto.getTradestatus(),"terminating") || StringUtil.equals(dto.getTradestatus(),"updating") ){
                errorList.add(new ValidMessage("交易状态为新增中、已成交、成交中、待回复、已撤回、撤回中、已终止、终止中、修改中的数据不允许被修改"));
            }
        }else if(StringUtil.equals(dto.getPageModel(),"confirm")) {//成交报价
            if(StringUtil.equals(dto.getTradestatus(),"adding") || StringUtil.equals(dto.getTradestatus(),"dealed") || StringUtil.equals(dto.getTradestatus(),"dealing") || StringUtil.equals(dto.getTradestatus(),"revoked") || StringUtil.equals(dto.getTradestatus(),"revoking") || StringUtil.equals(dto.getTradestatus(),"terminated") || StringUtil.equals(dto.getTradestatus(),"terminating") || StringUtil.equals(dto.getTradestatus(),"updating") ){
                errorList.add(new ValidMessage("交易状态为新增中、已成交、成交中、已撤回、撤回中、已终止、终止中、修改中的数据不允许被成交"));
            }
        }else if(StringUtil.equals(dto.getPageModel(),"refuse")) {//拒绝报价
            if(StringUtil.equals(dto.getTradestatus(),"adding") || StringUtil.equals(dto.getTradestatus(),"dealed") || StringUtil.equals(dto.getTradestatus(),"dealing") || StringUtil.equals(dto.getTradestatus(),"revoked") || StringUtil.equals(dto.getTradestatus(),"revoking") || StringUtil.equals(dto.getTradestatus(),"terminated") || StringUtil.equals(dto.getTradestatus(),"terminating") || StringUtil.equals(dto.getTradestatus(),"updating") ){
                errorList.add(new ValidMessage("交易状态为新增中、已成交、成交中、已撤回、撤回中、已终止、终止中、修改中的数据不允许被拒绝"));
            }
        }
        return errorList;
    }



}
