package com.joyintech.service.valuation.selectbokdetailcurrent.dto;


import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * @author tanxuan
 * @createTime 2019-10-16
 */
public class SelectBokdetailCurrent001Dto extends BaseEntity {
	
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = -6014428789141170392L;

	/**
     * 区间开始日期
     */
    private String bookDateStart;
    
    /**
     * 区间结束日期
     */
    private String bookDateEnd;
    
    /**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 场景代码
     */
    private String tableId;
    
    /**
     * 交易编号
     */
    private String tradeId;
    
    /**
     * 业务明细代码
     */
    private String busiId;
    
    /**
     * 任务代码
     */
    private String taskId;
    
    /**
     * 入账日期
     */
    private String bookDate;
    
    /**
     * 在途日期
     */
    private String onwayDate;
    
    /**
     * 基础科目性质
     */
    private String baseSubjectNature;
    
    /**
     * 账务明细代码
     */
    private String bokDetailId;
    
    /**
     * 分支序号
     */
    private String branch;
    
    /**
     * 科目号
     */
    private String subjectNo;
    
    /**
     * 四级科目号
     */
    private String detailSubjectNo;
    
    /**
     * 借贷方向
     */
    private String cdFlag;
    
    /**
     * 原币
     */
    private String oCcy;
    
    /**
     * 原币发生额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal oHappenAmt;
    
    /**
     * 本位币
     */
    private String bCcy;
    
    /**
     * 本位币发生额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bHappenAmt;
    
    /**
     * 发生数量
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal happenNumber;
    
    /**
     * 结息日
     */
    private String mdate;
    
    /**
     * 凭证编号
     */
    private String bookId;
    
    /**
     * 凭证说明
     */
    private String bookRemapk;
    
    /**
     * 冲正标识
     */
    private String offsetFlag;
    
    /**
     * 冲正日期
     */
    private String offsetDate;
    
    /**
     * 反向流水号
     */
    private String rSeqno;
    
    /**
     * 凭证类型
     */
    private String bookType;
    
    /**
     * 处理序列
     */
    private String bookOrder;
    
    /**
     * 余额方向（发生日）
     */
    private String balFlagHappenDate;
    
    /**
     * 原币余额（发生日）
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal oAmtHappenDate;
    
    /**
     * 本位币余额（发生日）
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAmtHappenDate;
    
    /**
     * 数量余额（发生日）
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal numAmtHappenDate;
    
    /**
     * 余额方向（入账日）
     */
    private String balFlagBookDate;
    
    /**
     * 原币余额（入账日）
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal oAmtBookDate;
    
    /**
     * 本位币余额（入账日）
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bAmtBookDate;
    
    /**
     * 数量余额（入账日）
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal numAmtBookDate;
    
    /**
     * 换汇日期
     */
    private String exDate;
    
    /**
     * 折算汇率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal exRate;
   
    
    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }
    
    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }
    
    /**
     * 取得 场景代码
     * @return 场景代码
     */
    public String getTableId() {
        return tableId;
    }
    
    /**
     * 设置 场景代码
     * @param tableId 场景代码
     */
    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
    
    /**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }
    
    /**
     * 设置 交易编号
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }
    
    /**
     * 取得 业务明细代码
     * @return 业务明细代码
     */
    public String getBusiId() {
        return busiId;
    }
    
    /**
     * 设置 业务明细代码
     * @param busiId 业务明细代码
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }
    
    /**
     * 取得 任务代码
     * @return 任务代码
     */
    public String getTaskId() {
        return taskId;
    }
    
    /**
     * 设置 任务代码
     * @param taskId 任务代码
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    
    /**
     * 取得 入账日期
     * @return 入账日期
     */
    public String getBookDate() {
        return bookDate;
    }
    
    /**
     * 设置 入账日期
     * @param bookDate 入账日期
     */
    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }
    
    /**
     * 取得 在途日期
     * @return 在途日期
     */
    public String getOnwayDate() {
        return onwayDate;
    }
    
    /**
     * 设置 在途日期
     * @param onwayDate 在途日期
     */
    public void setOnwayDate(String onwayDate) {
        this.onwayDate = onwayDate;
    }
    
    /**
     * 取得 基础科目性质
     * @return 基础科目性质
     */
    public String getBaseSubjectNature() {
        return baseSubjectNature;
    }
    
    /**
     * 设置 基础科目性质
     * @param baseSubjectNature 基础科目性质
     */
    public void setBaseSubjectNature(String baseSubjectNature) {
        this.baseSubjectNature = baseSubjectNature;
    }
    
    /**
     * 取得 账务明细代码
     * @return 账务明细代码
     */
    public String getBokDetailId() {
        return bokDetailId;
    }
    
    /**
     * 设置 账务明细代码
     * @param bokDetailId 账务明细代码
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }
    
    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public String getBranch() {
        return branch;
    }
    
    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }
    
    /**
     * 取得 科目号
     * @return 科目号
     */
    public String getSubjectNo() {
        return subjectNo;
    }
    
    /**
     * 设置 科目号
     * @param subjectNo科目号
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }
    
    /**
     * 取得 四级科目号
     * @return 四级科目号
     */
    public String getDetailSubjectNo() {
        return detailSubjectNo;
    }
    
    /**
     * 设置 四级科目号
     * @param detailSubjectNo 四级科目号
     */
    public void setDetailSubjectNo(String detailSubjectNo) {
        this.detailSubjectNo = detailSubjectNo;
    }
    
    /**
     * 取得 借贷方向
     * @return 借贷方向
     */
    public String getCdFlag() {
        return cdFlag;
    }
    
    /**
     * 设置 借贷方向
     * @param cdFlag 借贷方向
     */
    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag;
    }
    
    /**
     * 取得 原币
     * @return 原币
     */
    public String getoCcy() {
        return oCcy;
    }
    
    /**
     * 设置 原币
     * @param oCcy 原币
     */
    public void setoCcy(String oCcy) {
        this.oCcy = oCcy;
    }
    
    /**
     * 取得 原币发生额
     * @return 原币发生额
     */
    public BigDecimal getoHappenAmt() {
        return oHappenAmt;
    }
    
    /**
     * 设置 原币发生额
     * @param oHappenAmt 原币发生额
     */
    public void setoHappenAmt(BigDecimal oHappenAmt) {
        this.oHappenAmt = oHappenAmt;
    }
    
    /**
     * 取得 本位币
     * @return 本位币
     */
    public String getbCcy() {
        return bCcy;
    }
    
    /**
     * 设置 本位币
     * @param bCcy 本位币
     */
    public void setbCcy(String bCcy) {
        this.bCcy = bCcy;
    }
    
    /**
     * 取得 本位币发生额
     * @return 本位币发生额
     */
    public BigDecimal getbHappenAmt() {
        return bHappenAmt;
    }
    
    /**
     * 设置 本位币发生额
     * @param bHappenAmt 本位币发生额
     */
    public void setbHappenAmt(BigDecimal bHappenAmt) {
        this.bHappenAmt = bHappenAmt;
    }
    
    /**
     * 取得 发生数量
     * @return 发生数量
     */
    public BigDecimal getHappenNumber() {
        return happenNumber;
    }
    
    /**
     * 设置 发生数量
     * @param happenNumber 发生数量
     */
    public void setHappenNumber(BigDecimal happenNumber) {
        this.happenNumber = happenNumber;
    }
    
    /**
     * 取得 结息日
     * @return 结息日
     */
    public String getMdate() {
        return mdate;
    }
    
    /**
     * 设置 结息日
     * @param mdate 结息日
     */
    public void setMdate(String mdate) {
        this.mdate = mdate;
    }
    
    /**
     * 取得 凭证编号
     * @return 凭证编号
     */
    public String getBookId() {
        return bookId;
    }
    
    /**
     * 设置 凭证编号
     * @param bookId 凭证编号
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    
    /**
     * 取得 凭证说明
     * @return 凭证说明
     */
    public String getBookRemapk() {
        return bookRemapk;
    }
    
    /**
     * 设置 凭证说明
     * @param bookRemapk 凭证说明
     */
    public void setBookRemapk(String bookRemapk) {
        this.bookRemapk = bookRemapk;
    }
    
    /**
     * 取得 冲正标识
     * @return 冲正标识
     */
    public String getOffsetFlag() {
        return offsetFlag;
    }
    
    /**
     * 设置 冲正标识
     * @param offsetFlag 冲正标识
     */
    public void setOffsetFlag(String offsetFlag) {
        this.offsetFlag = offsetFlag;
    }
    
    /**
     * 取得 冲正日期
     * @return 冲正日期
     */
    public String getOffsetDate() {
        return offsetDate;
    }
    
    /**
     * 设置 冲正日期
     * @param offsetDate 冲正日期
     */
    public void setOffsetDate(String offsetDate) {
        this.offsetDate = offsetDate;
    }
    
    /**
     * 取得 反向流水号
     * @return 反向流水号
     */
    public String getRSeqno() {
        return rSeqno;
    }
    
    /**
     * 设置 反向流水号
     * @param rSeqno 反向流水号
     */
    public void setRSeqno(String rSeqno) {
        this.rSeqno = rSeqno;
    }
    
    /**
     * 取得 凭证类型
     * @return 凭证类型
     */
    public String getBookType() {
        return bookType;
    }
    
    /**
     * 设置 凭证类型
     * @param bookType 凭证类型
     */
    public void setBookType(String bookType) {
        this.bookType =bookType;
    }
    
    /**
     * 取得 处理序列
     * @return 处理序列
     */
    public String getBookOrder() {
        return bookOrder;
    }
    
    /**
     * 设置 处理序列
     * @param bookOrder 处理序列
     */
    public void setBookOrder(String bookOrder) {
        this.bookOrder = bookOrder;
    }
    
    /**
     * 取得 余额方向（发生日）
     * @return 余额方向（发生日）
     */
    public String getBalFlagHappenDate() {
        return balFlagHappenDate;
    }
    
    /**
     * 设置 余额方向（发生日）
     * @param balFlagHappenDate 余额方向（发生日）
     */
    public void setBalFlagHappenDate(String balFlagHappenDate) {
        this.balFlagHappenDate = balFlagHappenDate;
    }
    
    /**
     * 取得 原币余额（发生日）
     * @return 原币余额（发生日）
     */
    public BigDecimal getoAmtHappenDate() {
        return oAmtHappenDate;
    }
    
    /**
     * 设置 原币余额（发生日）
     * @param oAmtHappenDate 原币余额（发生日）
     */
    public void setoAmtHappenDate(BigDecimal oAmtHappenDate) {
        this.oAmtHappenDate = oAmtHappenDate;
    }
    
    /**
     * 取得 本位币余额（发生日）
     * @return 本位币余额（发生日）
     */
    public BigDecimal getbAmtHappenDate() {
        return bAmtHappenDate;
    }
    
    /**
     * 设置 本位币余额（发生日）
     * @param bAmtHappenDate 本位币余额（发生日）
     */
    public void setbAmtHappenDate(BigDecimal bAmtHappenDate) {
        this.bAmtHappenDate = bAmtHappenDate;
    }
    
    /**
     * 取得 数量余额（发生日）
     * @return 数量余额（发生日）
     */
    public BigDecimal getNumAmtHappenDate() {
        return numAmtHappenDate;
    }
    
    /**
     * 设置 数量余额（发生日）
     * @param numAmtHappenDate 数量余额（发生日）
     */
    public void setNumAmtHappenDate(BigDecimal numAmtHappenDate) {
        this.numAmtHappenDate = numAmtHappenDate;
    }
    
    /**
     * 取得 余额方向（入账日）
     * @return 余额方向（入账日）
     */
    public String getBalFlagBookDate() {
        return balFlagBookDate;
    }
    
    /**
     * 设置 余额方向（入账日）
     * @param balFlagBookDate 余额方向（入账日）
     */
    public void setBalFlagBookDate(String balFlagBookDate) {
        this.balFlagBookDate = balFlagBookDate;
    }
    
    /**
     * 取得 原币余额（入账日）
     * @return 原币余额（入账日）
     */
    public BigDecimal getoAmtBookDate() {
        return oAmtBookDate;
    }
    
    /**
     * 设置 原币余额（入账日）
     * @param oAmtBookDate 原币余额（入账日）
     */
    public void setoAmtBookDate(BigDecimal oAmtBookDate) {
        this.oAmtBookDate = oAmtBookDate;
    }
    
    /**
     * 取得 本位币余额（入账日）
     * @return 本位币余额（入账日）
     */
    public BigDecimal getbAmtBookDate() {
        return bAmtBookDate;
    }
    
    /**
     * 设置 本位币余额（入账日）
     * @param bAmtBookDate 本位币余额（入账日）
     */
    public void setbAmtBookDate(BigDecimal bAmtBookDate) {
        this.bAmtBookDate = bAmtBookDate;
    }
    
    /**
     * 取得 数量余额（入账日）
     * @return 数量余额（入账日）
     */
    public BigDecimal getNumAmtBookDate() {
        return numAmtBookDate;
    }
    
    /**
     * 设置 数量余额（入账日）
     * @param numAmtBookDate 数量余额（入账日）
     */
    public void setNumAmtBookDate(BigDecimal numAmtBookDate) {
        this.numAmtBookDate = numAmtBookDate;
    }
    
    /**
     * 取得 换汇日期
     * @return 换汇日期
     */
    public String getExDate() {
        return exDate;
    }
    
    /**
     * 设置 换汇日期
     * @param exDate 换汇日期
     */
    public void setExDate(String exDate) {
        this.exDate = exDate;
    }
    
    /**
     * 取得 折算汇率
     * @return 折算汇率
     */
    public BigDecimal getExRate() {
        return exRate;
    }
    
    /**
     * 设置 折算汇率
     * @param exRate 折算汇率
     */
    public void setExRate(BigDecimal exRate) {
        this.exRate = exRate;
    }
    
    /**
     * 取得 区间开始日期
     * @return 区间开始日期
     */
    public String getBookDateStart() {
        return bookDateStart;
    }
    
    /**
     * 设置 区间开始日期
     * @param bookDateStart 区间开始日期
     */
    public void setBookDateStart(String bookDateStart) {
        this.bookDateStart = bookDateStart;
    }
    
    /**
     * 取得 区间结束日期
     * @return 区间结束日期
     */
    public String getBookDateEnd() {
        return bookDateEnd;
    }
    
    /**
     * 设置 区间结束日期
     * @param bookDateEnd 区间结束日期
     */
    public void setBookDateEnd(String bookDateEnd) {
        this.bookDateEnd = bookDateEnd;
    }
    
    /**
     * 获取 原位币数据字典
     * 
     * @return 原位币数据字典.
     */
    public String getoCcyFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, this.getoCcy()))?
                SysDataUtil.getNameFromDataDict(DictCode.币种, this.getoCcy()):this.getoCcy();
    }

    /**
     * 获取 本位币数据字典
     * 
     * @return 本位币数据字典.
     */
    public String getbCcyFmt() {
    	 return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, this.getbCcy()))?
                 SysDataUtil.getNameFromDataDict(DictCode.币种, this.getbCcy()):this.getbCcy();
    }
}