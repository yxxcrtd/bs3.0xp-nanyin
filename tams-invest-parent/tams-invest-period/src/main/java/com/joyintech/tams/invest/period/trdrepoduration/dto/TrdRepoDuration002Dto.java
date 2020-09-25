package com.joyintech.tams.invest.period.trdrepoduration.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.RepoInfo;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.TrdRepoDurationDto;
import com.joyintech.tams.invest.period.trdrepoduration.validator.TrdRepoDuration002DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: tams-invest-parent
 * @description: 详情页面dto
 * @author: 强成西
 * @create: 2018-10-22 10:15
 **/
@PageId("TrdRepoDuration002")
@ValidLogic(targetClass = TrdRepoDuration002DtoValidator.class, groups = { ValidateGroupEdit.class})
public class TrdRepoDuration002Dto extends TrdRepoDurationDto implements ProcessEntity {

    /**
     * 获取 busiType
     *
     * @return busiType
     */
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getBusiType() {
        return DictCodeList.共通业务类型_质押式回购存续期.getValue();
    }

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getFinprodId() {
        return super.getFinprodId();
    }

    /**
     * 划款指令信息
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;

    private String workflowId;

    private String elementId;
    /**
     * 交易类型
     */
    private String pstype;
    /**
     * 续做到期日
     */
    private String mDatex;
    /**
     * 续做回购期限
     */
    private String termex;

    /**
     * 续做本金
     */
    private BigDecimal theoryPrinex;
    /**
     * 续做利息
     */
    private BigDecimal theoryIntex;
    /**
     * 续做到期结算金额
     */
    private BigDecimal tradeAmtex;

    /**
     * 佣金
     */
    private BigDecimal commissionAmt;

    /**
     * 结算金额
     */
    private BigDecimal setAmt;
    /**
     * 联系方式
     */
    private String teletype;
    /**
     * 续做交易对手
     */
    private String counterIdex;

    /**
     * 续做交易对手名称
     */
    private String counterNamex;
    /**
     * 续做对手方交易员
     */
    private String counterTraderx;
    /**
     * 续做交易员
     */
    private String traderx;
    /**
     * 分支序号
     */
    private Long branch;
    /**
     * 期限品种
     */
    private String termTypeReback;
    /**
     * 收支方向
     */
    private String ieFlag;

    /**
     * 添加质押券信息,用于APP显示
     */
    private List<RepoInfo> repoInfoList;


    /**
     * 获取 ieFlag
     *
     * @return ieFlag
     */
    public String getIeFlag() {
        return ieFlag;
    }

    /**
     * 设置 ieFlag
     *
     * @param ieFlag ieFlag
     */
    public void setIeFlag(String ieFlag) {
        this.ieFlag = ieFlag;
    }

    @Override
    @NotEmpty(fieldName = "TrdRepoDuration002.settleDate",groups = {ValidateGroupEdit.class})
    public String getSettleDate() {
        return super.getSettleDate();
    }

    /**
     * 获取 termTypeReback
     *
     * @return termTypeReback
     */
    public String getTermTypeReback() {
        return termTypeReback;
    }

    /**
     * 设置 termTypeReback
     *
     * @param termTypeReback termTypeReback
     */
    public void setTermTypeReback(String termTypeReback) {
        this.termTypeReback = termTypeReback;
    }

    /**
     * 获取 branch
     *
     * @return branch
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 branch
     *
     * @param branch branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 续做利率
     */
    private BigDecimal intRatex;

    /**
     * 金融产品分类实体类
     */
    private FinProductType finProductType;

    private TrdProductDeal trdProductDeal;

    /**
     * 获取 elementId
     *
     * @return elementId
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     *
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 bussTrdTransferOrder
     *
     * @return bussTrdTransferOrder
     */
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    /**
     * 设置 bussTrdTransferOrder
     *
     * @param bussTrdTransferOrder bussTrdTransferOrder
     */
    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

    /**
     * 获取 workflowId
     *
     * @return workflowId
     */
    @Override
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 workflowId
     *
     * @param workflowId workflowId
     */
    @Override
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    /**
     * 获取 finprodType2String
     *
     * @return finprodType2String
     */
    public String getFinprodType2String() {
        String result = SysDataUtil.getNameFromDataDict(DictCode.金融产品类型_投管用,
            getFinprodType2());
        return result == null ? getFinprodType2() : result;
    }

    /**
     * 获取 statusValidString
     *
     * @return statusValidString
     */
    public String getStatusValidString() {
        String result = SysDataUtil.getNameFromDataDict(DictCode.画面数据状态,
            getStatusValid());
        return result == null ? getStatusValid() : result;
    }

    /**
     * 获取 type6String
     *
     * @return type6String
     */
    public String getType6String() {
        String result = SysDataUtil.getNameFromDataDict(DictCode.回购类型, getType6());
        return result == null ? getType6() : result;
    }

    /**
     * 获取 type6String
     *
     * @return type6String
     */
    public String getTradeMarketString() {
        String result = SysDataUtil.getNameFromDataDict(DictCode.交易场所, getTradeMarket());
        return result == null ? getTradeMarket() : result;
    }

    /**
     * 获取 pstype
     *
     * @return pstype
     */
    public String getPstype() {
        return pstype;
    }

    /**
     * 设置 pstype
     *
     * @param pstype pstype
     */
    public void setPstype(String pstype) {
        this.pstype = pstype;
    }

    /**
     * 获取 mDatex
     *
     * @return mDatex
//     */
    public String getmDatex() {
        return mDatex;
    }

    /**
     * 设置 mDatex
     *
     * @param mDatex mDatex
     */
    public void setmDatex(String mDatex) {
        this.mDatex = mDatex;
    }

    /**
     * 获取 termex
     *
     * @return termex
     */
    @Override
    public String getTermex() {
        return termex;
    }

    /**
     * 设置 termex
     *
     * @param termex termex
     */
    @Override
    public void setTermex(String termex) {
        this.termex = termex;
    }

    /**
     * 获取 theoryPrinex
     *
     * @return theoryPrinex
     */
    public BigDecimal getTheoryPrinex() {
        return theoryPrinex;
    }

    /**
     * 设置 theoryPrinex
     *
     * @param theoryPrinex theoryPrinex
     */
    public void setTheoryPrinex(BigDecimal theoryPrinex) {
        this.theoryPrinex = theoryPrinex;
    }

    /**
     * 获取 theoryIntex
     *
     * @return theoryIntex
     */
    public BigDecimal getTheoryIntex() {
        return theoryIntex;
    }

    /**
     * 设置 theoryIntex
     *
     * @param theoryIntex theoryIntex
     */
    public void setTheoryIntex(BigDecimal theoryIntex) {
        this.theoryIntex = theoryIntex;
    }

    /**
     * 获取 tradeAmtex
     *
     * @return tradeAmtex
     */
    public BigDecimal getTradeAmtex() {
        return tradeAmtex;
    }

    /**
     * 设置 tradeAmtex
     *
     * @param tradeAmtex tradeAmtex
     */
    public void setTradeAmtex(BigDecimal tradeAmtex) {
        this.tradeAmtex = tradeAmtex;
    }

    /**
     * 获取 commissionAmt
     *
     * @return commissionAmt
     */
    public BigDecimal getCommissionAmt() {
        return commissionAmt;
    }

    /**
     * 设置 commissionAmt
     *
     * @param commissionAmt commissionAmt
     */
    public void setCommissionAmt(BigDecimal commissionAmt) {
        this.commissionAmt = commissionAmt;
    }

    /**
     * 获取 setAmt
     *
     * @return setAmt
     */
    public BigDecimal getSetAmt() {
        return setAmt;
    }

    /**
     * 设置 setAmt
     *
     * @param setAmt setAmt
     */
    public void setSetAmt(BigDecimal setAmt) {
        this.setAmt = setAmt;
    }

    /**
     * 获取 teletype
     *
     * @return teletype
     */
    public String getTeletype() {
        return teletype;
    }

    /**
     * 设置 teletype
     *
     * @param teletype teletype
     */
    public void setTeletype(String teletype) {
        this.teletype = teletype;
    }

    /**
     * 获取 counterIdex
     *
     * @return counterIdex
     */
    public String getCounterIdex() {
        return counterIdex;
    }

    /**
     * 设置 counterIdex
     *
     * @param counterIdex counterIdex
     */
    public void setCounterIdex(String counterIdex) {
        this.counterIdex = counterIdex;
    }

    /**
     * 获取 counterNamex
     *
     * @return counterNamex
     */
    public String getCounterNamex() {
        return counterNamex;
    }

    /**
     * 设置 counterNamex
     *
     * @param counterNamex counterNamex
     */
    public void setCounterNamex(String counterNamex) {
        this.counterNamex = counterNamex;
    }

    /**
     * 获取 counterTraderx
     *
     * @return counterTraderx
     */
    public String getCounterTraderx() {
        return counterTraderx;
    }

    /**
     * 设置 counterTraderx
     *
     * @param counterTraderx counterTraderx
     */
    public void setCounterTraderx(String counterTraderx) {
        this.counterTraderx = counterTraderx;
    }

    /**
     * 获取 intRatex
     *
     * @return intRatex
     */
    public BigDecimal getIntRatex() {
        return intRatex;
    }

    /**
     * 设置 intRatex
     *
     * @param intRatex intRatex
     */
    public void setIntRatex(BigDecimal intRatex) {
        this.intRatex = intRatex;
    }


    /**
     * 获取 traderx
     *
     * @return traderx
     */
    public String getTraderx() {
        return traderx;
    }

    /**
     * 设置 traderx
     *
     * @param traderx traderx
     */
    public void setTraderx(String traderx) {
        this.traderx = traderx;
    }

    /**
     * 获取 finProductType
     *
     * @return finProductType
     */
    public FinProductType getFinProductType() {
        return finProductType;
    }

    /**
     * 设置 finProductType
     *
     * @param finProductType finProductType
     */
    public void setFinProductType(FinProductType finProductType) {
        this.finProductType = finProductType;
    }

    /**
     * 获取 trdProductDeal
     *
     * @return trdProductDeal
     */
    public TrdProductDeal getTrdProductDeal() {
        return trdProductDeal;
    }

    /**
     * 设置 trdProductDeal
     *
     * @param trdProductDeal trdProductDeal
     */
    public void setTrdProductDeal(TrdProductDeal trdProductDeal) {
        this.trdProductDeal = trdProductDeal;
    }

    /**
     * 获取 附件上传
     *
     * @return uploadFileList 附件上传
     */
    public List<UploadFile> getUploadFileList() {
        return this.uploadFileList;
    }

    /**
     * 设置 附件上传
     *
     * @param uploadFileList 附件上传
     */
    public void setUploadFileList(List<UploadFile> uploadFileList) {
        this.uploadFileList = uploadFileList;
    }

    /**
     * 获取 续做到期日
     *
     * @return mDatex 续做到期日
     */
    public String getMDatex() {
        return this.mDatex;
    }

    /**
     * 设置 续做到期日
     *
     * @param mDatex 续做到期日
     */
    public void setMDatex(String mDatex) {
        this.mDatex = mDatex;
    }

    /**
     * 画面类型
     */
    private String pageModel;

    /**
     * 获取 pageModel
     *
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置 pageModel
     *
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }


    public List<RepoInfo> getRepoInfoList() {
        return repoInfoList;
    }

    public void setRepoInfoList(List<RepoInfo> repoInfoList) {
        this.repoInfoList = repoInfoList;
    }

}
