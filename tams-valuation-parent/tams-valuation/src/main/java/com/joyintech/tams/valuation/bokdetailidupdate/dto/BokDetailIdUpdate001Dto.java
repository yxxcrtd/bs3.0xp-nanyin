package com.joyintech.tams.valuation.bokdetailidupdate.dto;

import com.joyintech.framework.base.BaseEntity;


/**
 * @author tanxuan
 * @createTime 2019-10-10
 */
public class BokDetailIdUpdate001Dto extends BaseEntity {
	
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;

    /**
     * 处理操作
     */
    private String operateType;
    
    /**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 账务明细代码
     */
    private String  bokDetailId;

    /**
     * 业务明细代码
     */
    private String busiId;

    /**
     * 业务明细类型
     */
    private String busiDetailType;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 证券管理户代码
     */
    private String secManageAcctId;

    /**
     * 四级科目尾号
     */
    private String detailSubjectNoTail;

    /**
     * 四级科目尾号名称
     */
    private String detailSubnameTail;

    /**
     * 分支序列
     */
    private String branch;
    
    /**
     * 币种
     */
    private String ccy;
    
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
     * 取得 业务明细类型
     * @return 业务明细类型
     */
    public String getBusiDetailType() {
		return busiDetailType;
	}
    
    /**
     * 设置 业务明细类型
     * @param busiDetailType 业务明细类型
     */
	public void setBusiDetailType(String busiDetailType) {
		this.busiDetailType = busiDetailType;
	}
	
    /**
     * 取得 投资目的
     * @return 投资目的
     */
    public String getInvAim() {
        return invAim;
    }
    
    /**
     * 设置 投资目的
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }
    
    /**
     * 取得 证券管理户代码
     * @return 证券管理户代码
     */
    public String getSecManageAcctId() {
        return secManageAcctId;
    }
    
    /**
     * 设置 证券管理户代码
     * @param secManageAcctId 证券管理户代码
     */
    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId;
    }
    
    /**
     * 取得 四级科目尾号
     * @return 四级科目尾号
     */
    public String getDetailSubjectNoTail() {
        return detailSubjectNoTail;
    }
    
    /**
     * 设置 四级科目尾号
     * @param detailSubjectNoTail 四级科目尾号
     */
    public void setDetailSubjectNoTail(String detailSubjectNoTail) {
        this.detailSubjectNoTail = detailSubjectNoTail;
    }
    
    /**
     * 取得 四级科目尾号名称
     * @return 四级科目尾号名称
     */
    public String getDetailSubnameTail() {
        return detailSubnameTail;
    }
    
    /**
     * 设置 四级科目尾号名称
     * @param detailSubnameTai 四级科目尾号名称
     */
    public void setDetailSubnameTail(String detailSubnameTail) {
        this.detailSubnameTail = detailSubnameTail;
    }
    
    /**
     * 取得 分支序列
     * @return 分支序列
     */
    public String getBranch() {
        return branch;
    }
    
    /**
     * 设置 分支序列
     * @param branch 分支序列
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }
    
    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 取得 处理操作
     * @return 处理操作
     */
    public String getOperateType() {
        return operateType;
    }
    
    /**
     * 设置 处理操作
     * @param operateType 处理操作
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

}