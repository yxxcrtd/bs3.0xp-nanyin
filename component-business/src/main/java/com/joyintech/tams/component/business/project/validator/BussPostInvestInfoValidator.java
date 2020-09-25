/*
 * 文件名：BussPostInvestInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.project.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.project.dto.BussPostInvestInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 投后信息--业务验证
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月29日
 * @see BussPostInvestInfoValidator
 * @since 1.0
 */
@Component
public class BussPostInvestInfoValidator implements BeanValidator{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        BussPostInvestInfoDto dto = (BussPostInvestInfoDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if(StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_非标债权.getValue())){
            
        }else if(StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_结构化证券.getValue())){
            //结构化证券
            //业务品种
           /* if(StringUtil.isEmpty(dto.getPrdTypes())){
                resultList.add(new ValidMessage("prdTypes",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.prdTypes"))));
            }*/
            //单票集中度
            /*if(null==dto.getSingleVoteRatio()){
                resultList.add(new ValidMessage("singleVoteRatio",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.singleVoteRatio"))));
            }*/
        }/*else if(StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_券商债权收益权投资.getValue())
            ||StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_券商收益凭证.getValue())){
            //受让资产性质
            if(StringUtil.isEmpty(dto.getNatureAssignAssets())){
                resultList.add(new ValidMessage("natureAssignAssets",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.natureAssignAssets"))));
            }
            if(StringUtil.equals(dto.getIsFloat(),DictCodeList.是否_是.getValue())){
                if(StringUtil.isEmpty(dto.getFloatIssueCmt())){
                    resultList.add(new ValidMessage("floatIssueCmt",
                        getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.floatIssueCmt"))));
                }
            }
        }else if(StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_股票质押式回购.getValue())){
            //质押股票
            if(StringUtil.isEmpty(dto.getPawnShare())){
                resultList.add(new ValidMessage("pawnShare",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.pawnShare"))));
            }
            //初始质押率
            if(null==dto.getPawnSharePer()){
                resultList.add(new ValidMessage("pawnSharePer",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.pawnSharePer"))));
            }
            //初始质押价格 
             if(null==dto.getPawnShareMoney()){
                 resultList.add(new ValidMessage("pawnShareMoney",
                     getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.pawnShareMoney"))));
             }
            //原质押股本数
             if(null==dto.getPawnShareQuanlity()){
                 resultList.add(new ValidMessage("pawnShareQuanlity",
                     getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.pawnShareQuanlity"))));
             }
            //占总股本比例
            if(null==dto.getPropTotalCapitalShare()){
                resultList.add(new ValidMessage("propTotalCapitalShare",
                        getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.propTotalCapitalShare"))));
            }
            //股票质押
            if(StringUtil.isEmpty(dto.getPledgeShare())){
                resultList.add(new ValidMessage("pledgeShare",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.pledgeShare"))));
            }
            //罚息比例
            if(null==dto.getPenaltyRate()){
                resultList.add(new ValidMessage("penaltyRate",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.penaltyRate"))));
            }
        }*/else if(StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_股票定向增发投资.getValue())){
            //股票代码
           /* if(StringUtil.isEmpty(dto.getShareCode())){
                resultList.add(new ValidMessage("shareCode",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.shareCode"))));
            }*/
            //股票名称
           /* if(StringUtil.isEmpty(dto.getShareName())){
                resultList.add(new ValidMessage("shareName",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.shareName"))));
            }*/
            if(StringUtil.equals(dto.getIsBuyBack(), DictCodeList.是否_是.getValue())){
                //代持利率
                if(null==dto.getBuyBackRate()){
                    resultList.add(new ValidMessage("buyBackRate",
                        getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.buyBackRate"))));
                }
                //代持购回条件
                if(StringUtil.isEmpty(dto.getBuyBackPolicy())){
                    resultList.add(new ValidMessage("buyBackPolicy",
                        getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.buyBackPolicy"))));
                }
            }
        }
        
        //股票质押式回购、结构化证券、股票定向增发投资
        if(StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_结构化证券.getValue())
            ||StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_股票定向增发投资.getValue())){
            //预警线
            /*if(StringUtil.isEmpty(dto.getWarnLine())){
                resultList.add(new ValidMessage("warnLine",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.warnLine"))));
            }*/
            //预警机制
           /* if(StringUtil.isEmpty(dto.getWarnRule())){
                resultList.add(new ValidMessage("warnRule",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.warnRule"))));
            }*/
            //平仓线
            /*if(StringUtil.isEmpty(dto.getFlatLine())){
                resultList.add(new ValidMessage("flatLine",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.flatLine"))));
            }*/
           //平仓机制
           /* if(StringUtil.isEmpty(dto.getFlatRule())){
                resultList.add(new ValidMessage("flatRule",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.flatRule"))));
            }*/
            //批复方
            /*if(StringUtil.isEmpty(dto.getCompoundBatch())){
                resultList.add(new ValidMessage("compoundBatch",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.compoundBatch"))));
            }*/
        }
        
        //股票定向增发投资、结构化证券
        if(StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_结构化证券.getValue())
            ||StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_股票定向增发投资.getValue())){
          //限售期截止日
            /*if(null==dto.getSaleDeadline()){
                resultList.add(new ValidMessage("saleDeadline",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.saleDeadline"))));
            }
            //优先劣后比例
            if(StringUtil.isEmpty(dto.getPropPrioPer())){
                resultList.add(new ValidMessage("propPrioPer",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.propPrioPer")))); 
            }
            //劣后出资金额
            if(null==dto.getAmountInvesBad()){
                resultList.add(new ValidMessage("amountInvesBad",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.amountInvesBad"))));
            }
            //差补方名称
            if(StringUtil.isEmpty(dto.getDiffrentPartyName())){
                resultList.add(new ValidMessage("diffrentPartyName",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.diffrentPartyName"))));
            }  */
        }
        
        //股票质押式回购、股票定向增发投资
        if(StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_股票定向增发投资.getValue())){
            //标的股票持有主体
           /* if(StringUtil.isEmpty(dto.getShareHolder())){
                resultList.add(new ValidMessage("shareHolder",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.shareHolder"))));
            }
            //交易场所
            if(StringUtil.isEmpty(dto.getTradeMarket())){
                resultList.add(new ValidMessage("tradeMarket",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.tradeMarket"))));
            }
            //回购方名称
            if(StringUtil.isEmpty(dto.getRepurchasePartyName())){
                resultList.add(new ValidMessage("repurchasePartyName",
                    getMessage("COMMON_E_0057", getMessage("BussPostInvestInfo.repurchasePartyName"))));
            }*/
        }
            
        return resultList;
    }

}
