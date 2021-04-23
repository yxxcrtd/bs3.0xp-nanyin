/*
 * 文件名：BatchCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年7月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.service;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListTableId;
import com.joyintech.tams.systemtools.service.SystemToolService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokBookdetailCurrent;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.valuation.common.dao.BookAccountCommonMapper;
import com.joyintech.tams.valuation.common.interf.base.util.MD5Util;
import com.joyintech.tams.valuation.common.utils.ValStringUtil;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhiang
 * @version 1.0 2018年7月13日
 * @see BookAccountCommonService
 * @since 1.0
 */
@Service
public class BookAccountCommonService {
    
    @Autowired
    private BookAccountCommonMapper bookAccountCommonMapper;
    
    /**
     * 1
     */
    private static final String I1 = "1";

    /**
     * 2
     */
    private static final String I2 = "2";

    /**
     * 3
     */
    private static final String I3 = "3";

    /**
     * 4
     */
    private static final String I4 = "4";
    
    /**
     * 5
     */
    private static final String I5 = "5";

    /**
     * 6
     */
    private static final String I6 = "6";
    
    /**
     * 系统工具类
     */
    @Autowired
    private SystemToolService systemToolService;
    
    /**
     * 生成MD5账务明细代码
     *
     * @param valBokDetailId 账务明细代码生成参数
     * @param busiDetailType 类型
     * @return String 账务明细代码
     * @see
     * @since 1.0
     */
    public String genMd5BokDetailId(BokDetailId valBokDetailId) {
        String tempBokDetailId = null;
        // 判断分支: 除了债券，均为业务明细代码 + 业务明细类型
        if (DictCodeListExt1.金融产品类型_债券.getValue().equals(valBokDetailId.getBusiDetailType())) {
            // 债券
            // 账务明细代码 = 业务明细代码 + 业务明细类型 + 投资目的 + 证券管理户代码
            tempBokDetailId = I1 + ValStringUtil.getNotNullStr(valBokDetailId.getBusiId()) + I2
                              + ValStringUtil.getNotNullStr(valBokDetailId.getBusiDetailType());
            // 融入债券走的也是债券逻辑，不需要3,4加密，其他债券投资目的一定不为空
            if(StringUtil.isNotEmpty(valBokDetailId.getInvAim())){
                tempBokDetailId += I3 + ValStringUtil.getNotNullStr(valBokDetailId.getInvAim()) + I4
                + ValStringUtil.getNotNullStr(valBokDetailId.getSecManageAcctId());
            }

        }
        /**add by wujie on 20201015 start remark:计划类收益型资产改造**/
        else if (DictCodeListExt1.金融产品类型_计划类资产.getValue().equals(valBokDetailId.getBusiDetailType())) {
            tempBokDetailId = I1 + ValStringUtil.getNotNullStr(valBokDetailId.getBusiId()) + I2
                              + ValStringUtil.getNotNullStr(valBokDetailId.getBusiDetailType());
        	if(StringUtil.isNotEmpty(valBokDetailId.getInvAim())){
        		 tempBokDetailId += I3 + ValStringUtil.getNotNullStr(valBokDetailId.getInvAim());
        	}
        }
        /**add by wujie on 20201015 end**/
        else {
            // 账务明细代码 = 业务明细代码 + 业务明细类型  
            tempBokDetailId = I1 + ValStringUtil.getNotNullStr(valBokDetailId.getBusiId()) + I2
                              + ValStringUtil.getNotNullStr(valBokDetailId.getBusiDetailType());
            // (如果传了分支序号，则需要增加分支序号)
            tempBokDetailId = addBokDetailIdParam(tempBokDetailId, I5, valBokDetailId.getBranch());

            // IF 输入.账务明细代码Dto.币种 IS NOT NULL
            if (StringUtils.isNotEmpty(valBokDetailId.getCcy())) {
                // 变量.账务明细代码 += 6 + 输入.账务明细代码Dto.币种
                if(DictCodeListExt1.金融产品类型_资金账户.getValue().equals(valBokDetailId.getBusiDetailType())
                		||systemToolService.getDataDictItem(DictCode.资金账户类型.getValue(), valBokDetailId.getBusiDetailType()) != null){
                	tempBokDetailId += I6 + valBokDetailId.getCcy();
                }
                
            }

        }
        // MD5转换
        String md5BokDetailId = MD5Util.string2MD5(tempBokDetailId);
        valBokDetailId.setBokDetailId(md5BokDetailId);
        return md5BokDetailId;
    }
    
    // 增加账务明细代码组装内容
    private String addBokDetailIdParam(String str, String index, Object value) {
        if (value == null) {
            return str;
        }
        else if (value instanceof Long && ((Long)value).longValue() != 0) {
            // Long型时，如果为0 也不增加组装内容
            str = str + index + String.valueOf(value);
        }
        
        return str;
    }
    /**
     * 凭证编码生成
     * @param bookDetail
     * @see
     * @since 1.0
     */
    public String genBookId(BokBookdetailCurrent bookDetail) {
        String bookId = null;
        // modified by wangzhiang 20180807 增加 start
        if (DictCodeListTableId.会计场景代码_产品_已实现损益结转.getValue().equals(bookDetail.getTableId())
            || DictCodeListTableId.会计场景代码_产品_未实现损益结转.getValue().equals(bookDetail.getTableId())
            || DictCodeListTableId.会计场景代码_产品_未分配利润结转.getValue().equals(bookDetail.getTableId())
            || DictCodeListTableId.会计场景代码_产品_损益平准金结转.getValue().equals(bookDetail.getTableId())) {
            // 损益结转等特殊类：场景代码 + 发生日 + 冲销标识
            bookId = bookDetail.getBooksetId() + bookDetail.getTableId()
                     + bookDetail.getHappenDate() + bookDetail.getOffsetFlag();
        }
        else if (!StringUtils.isEmpty(bookDetail.getTradeId())) {
            // 交易类：场景代码 + 交易编号 + 冲销标识
            bookId = bookDetail.getBooksetId() + bookDetail.getTableId() + bookDetail.getTradeId()
                     + bookDetail.getOffsetFlag();
        }
        else {
            // 计提类：场景代码 + 发生日 + 账务明细代码 + 冲销标识
            bookId = bookDetail.getBooksetId() + bookDetail.getTableId()
                     + bookDetail.getHappenDate() + bookDetail.getBokDetailId()
                     + bookDetail.getOffsetFlag();
        }
        // modified by wangzhiang 20180807 end
        bookId = MD5Util.string2MD5(bookId);
        bookDetail.setBookId(bookId);
        return bookId;
    }
    

    /**
     * 组装账务明细(资金帐户/现金类)
     *
     * @param busiId
     * @param bokDetailId
     * @return
     * @see
     * @since 1.0
     */
    public BokDetailId getBokDetailId(String busiId, String bokDetailId,
                                            String busiDetailType) {
        BokDetailId valBokDetailId = new BokDetailId();
        valBokDetailId.setBusiId(busiId);
        valBokDetailId.setBusiDetailType(busiDetailType);
        if (bokDetailId == null) {
            valBokDetailId.setBokDetailId(genMd5BokDetailId(valBokDetailId));
        }
        else {
            valBokDetailId.setBokDetailId(bokDetailId);
        }
        return valBokDetailId;
    }
    
    /**
     * 获取最大凭证序号
     *
     * @param vouchYear 年份
     * @param vouchMonth 月份
     * @param booksetId 账套代码
     * @return 最大凭证序号
     * @see
     * @since 1.0
     */
    public String getMaxBookAccountNum(String vouchYear, String vouchMonth, String booksetId) {
        return bookAccountCommonMapper.getMaxBookAccountNum(vouchYear, vouchMonth, booksetId);
    }
    
    /**
     * 凭证序号补零
     *
     * @param num 凭证序号
     * @param length 长度
     * @return 凭证序号
     * @see
     * @since 1.0
     */
    public String padBookAccountNum(String num, int length) {
        return StringUtil.leftPad(num, length, "0");
    }
    
    /**
     * 
     * 更新历史业务凭证编号
     * 
     * @param vouchYear
     * @param vouchMonth
     * @see
     * @since 1.0
     */
    public void updateHistory(String vouchYear, String vouchMonth) {
        bookAccountCommonMapper.updateHistory(vouchYear, vouchMonth);
    }
    /**
     * 查询账务明细代码
     */
	public List<BokDetailId> queryBokDetailIdList(Map<String, Object> map) {
		return bookAccountCommonMapper.queryBokDetailIdList(map);
	}
}
