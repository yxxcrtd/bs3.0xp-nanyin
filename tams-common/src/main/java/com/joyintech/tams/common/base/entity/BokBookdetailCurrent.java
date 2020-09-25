package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class BokBookdetailCurrent extends BaseEntity {
    /**
     * 流水
     */
    private String seqno;

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
     * 任务优先级
     */
    private Long taskPri;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 入账日期
     */
    private Date bookDate;

    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 在途日期，对于T+1交割的债券交易，存交割日
     */
    private Date onwayDate;

    /**
     * 基础科目性质
     */
    private String baseSubjectNature;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 分支序号，无分支序号的存0
     */
    private Long branch;

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
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oHappenAmt;

    /**
     * 本位币
     */
    private String bCcy;

    /**
     * 本位币发生额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bHappenAmt;

    /**
     * 发生数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal happenNumber;

    /**
     * 结息日
     */
    private Date mdate;

    /**
     * 凭证编号
     */
    private String bookId;

    /**
     * 凭证说明
     */
    private String bookRemark;

    /**
     * 冲正标识
     */
    private String offsetFlag;

    /**
     * 冲正日期
     */
    private Date offsetDate;

    /**
     * 反向流水号，冲正存正常流水，正常存冲正流水
     */
    private String rSeqno;

    /**
     * 凭证类型：实时、日终、手工
     */
    private String bookType;

    /**
     * 处理序列
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bookOrder;

    /**
     * 余额方向（发生日）
     */
    private String balFlagHappenDate;

    /**
     * 原币余额（发生日）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oAmtHappenDate;

    /**
     * 本位币余额（发生日）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bAmtHappenDate;

    /**
     * 数量余额（发生日）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal numAmtHappenDate;

    /**
     * 余额方向（入账日）
     */
    private String balFlagBookDate;

    /**
     * 原币余额（入账日）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal oAmtBookDate;

    /**
     * 本位币余额（入账日）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bAmtBookDate;

    /**
     * 数量余额（入账日）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal numAmtBookDate;

    /**
     * 换汇日期
     */
    private Date exDate;

    /**
     * 折算汇率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal exRate;

    /**
     * 0金额是否处理
     */
    private String isexeZeroAmt;

    /**
     * 收息已结清份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shareAmtClean;

    /**
     * 取得 流水
     * @return 流水
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水
     * @param seqno 流水
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

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
        this.booksetId = booksetId == null ? null : booksetId.trim();
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
        this.tableId = tableId == null ? null : tableId.trim();
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
        this.tradeId = tradeId == null ? null : tradeId.trim();
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
        this.busiId = busiId == null ? null : busiId.trim();
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
        this.taskId = taskId == null ? null : taskId.trim();
    }

    /**
     * 取得 任务优先级
     * @return 任务优先级
     */
    public Long getTaskPri() {
        return taskPri;
    }

    /**
     * 设置 任务优先级
     * @param taskPri 任务优先级
     */
    public void setTaskPri(Long taskPri) {
        this.taskPri = taskPri;
    }

    /**
     * 取得 发生日期
     * @return 发生日期
     */
    public Date getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 发生日期
     * @param happenDate 发生日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 取得 入账日期
     * @return 入账日期
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 入账日期
     * @param bookDate 入账日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 取得 账套日期
     * @return 账套日期
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    /**
     * 设置 账套日期
     * @param booksetDate 账套日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    /**
     * 取得 在途日期，对于T+1交割的债券交易，存交割日
     * @return 在途日期，对于T+1交割的债券交易，存交割日
     */
    public Date getOnwayDate() {
        return onwayDate;
    }

    /**
     * 设置 在途日期，对于T+1交割的债券交易，存交割日
     * @param onwayDate 在途日期，对于T+1交割的债券交易，存交割日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOnwayDate(Date onwayDate) {
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
        this.baseSubjectNature = baseSubjectNature == null ? null : baseSubjectNature.trim();
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
        this.bokDetailId = bokDetailId == null ? null : bokDetailId.trim();
    }

    /**
     * 取得 分支序号，无分支序号的存0
     * @return 分支序号，无分支序号的存0
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号，无分支序号的存0
     * @param branch 分支序号，无分支序号的存0
     */
    public void setBranch(Long branch) {
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
     * @param subjectNo 科目号
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo == null ? null : subjectNo.trim();
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
        this.detailSubjectNo = detailSubjectNo == null ? null : detailSubjectNo.trim();
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
        this.cdFlag = cdFlag == null ? null : cdFlag.trim();
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
        this.oCcy = oCcy == null ? null : oCcy.trim();
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
        this.bCcy = bCcy == null ? null : bCcy.trim();
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
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 结息日
     * @param mdate 结息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
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
        this.bookId = bookId == null ? null : bookId.trim();
    }

    /**
     * 取得 凭证说明
     * @return 凭证说明
     */
    public String getBookRemark() {
        return bookRemark;
    }

    /**
     * 设置 凭证说明
     * @param bookRemark 凭证说明
     */
    public void setBookRemark(String bookRemark) {
        this.bookRemark = bookRemark == null ? null : bookRemark.trim();
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
        this.offsetFlag = offsetFlag == null ? null : offsetFlag.trim();
    }

    /**
     * 取得 冲正日期
     * @return 冲正日期
     */
    public Date getOffsetDate() {
        return offsetDate;
    }

    /**
     * 设置 冲正日期
     * @param offsetDate 冲正日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOffsetDate(Date offsetDate) {
        this.offsetDate = offsetDate;
    }

    /**
     * 取得 反向流水号，冲正存正常流水，正常存冲正流水
     * @return 反向流水号，冲正存正常流水，正常存冲正流水
     */
    public String getrSeqno() {
        return rSeqno;
    }

    /**
     * 设置 反向流水号，冲正存正常流水，正常存冲正流水
     * @param rSeqno 反向流水号，冲正存正常流水，正常存冲正流水
     */
    public void setrSeqno(String rSeqno) {
        this.rSeqno = rSeqno == null ? null : rSeqno.trim();
    }

    /**
     * 取得 凭证类型：实时、日终、手工
     * @return 凭证类型：实时、日终、手工
     */
    public String getBookType() {
        return bookType;
    }

    /**
     * 设置 凭证类型：实时、日终、手工
     * @param bookType 凭证类型：实时、日终、手工
     */
    public void setBookType(String bookType) {
        this.bookType = bookType == null ? null : bookType.trim();
    }

    /**
     * 取得 处理序列
     * @return 处理序列
     */
    public BigDecimal getBookOrder() {
        return bookOrder;
    }

    /**
     * 设置 处理序列
     * @param bookOrder 处理序列
     */
    public void setBookOrder(BigDecimal bookOrder) {
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
        this.balFlagHappenDate = balFlagHappenDate == null ? null : balFlagHappenDate.trim();
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
        this.balFlagBookDate = balFlagBookDate == null ? null : balFlagBookDate.trim();
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
    public Date getExDate() {
        return exDate;
    }

    /**
     * 设置 换汇日期
     * @param exDate 换汇日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setExDate(Date exDate) {
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
     * 取得 0金额是否处理
     * @return 0金额是否处理
     */
    public String getIsexeZeroAmt() {
        return isexeZeroAmt;
    }

    /**
     * 设置 0金额是否处理
     * @param isexeZeroAmt 0金额是否处理
     */
    public void setIsexeZeroAmt(String isexeZeroAmt) {
        this.isexeZeroAmt = isexeZeroAmt == null ? null : isexeZeroAmt.trim();
    }

    /**
     * 取得 收息已结清份额
     * @return 收息已结清份额
     */
    public BigDecimal getShareAmtClean() {
        return shareAmtClean;
    }

    /**
     * 设置 收息已结清份额
     * @param shareAmtClean 收息已结清份额
     */
    public void setShareAmtClean(BigDecimal shareAmtClean) {
        this.shareAmtClean = shareAmtClean;
    }
}