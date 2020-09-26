/*
 * 文件名：TradeDetail.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved. 描述：中信银行资管系统
 * 修改人：qiangchengxi 修改时间：2016年12月24日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.batch.lendbookdetail.dto;

import java.math.BigDecimal;

/**
 * 明细科目信息DTO
 * 
 * @author 吴德宝
 * @version 1.0 2018年04月26日
 * @see DetailSubjectDTO
 * @since 1.0
 */
public class DetailSubjectDTO {

  //账套代码
    private String bokSetId;
    //明细科目号
    private String detailSubjectNo;
    //业务明细代码
    private String busiId;
    //科目号
    private String subjectNo;
    //是否计息
    private String accureFlg;
    //计息利率编号
    private BigDecimal accureyieldNo;
    //是否允许透支
    private String overdraftflg;

    //账套代码 取值
    public String getBokSetId() {
       return bokSetId;
    }

    
    /**
     * 获取 detailSubjectNo
     * @return detailSubjectNo.
     */
    public String getDetailSubjectNo() {
        return detailSubjectNo;
    }

    
    /**
     * 设置 detailSubjectNo
     * @param detailSubjectNo detailSubjectNo
     */
    public void setDetailSubjectNo(String detailSubjectNo) {
        this.detailSubjectNo = detailSubjectNo;
    }

    
    /**
     * 获取 busiId
     * @return busiId.
     */
    public String getBusiId() {
        return busiId;
    }

    
    /**
     * 设置 busiId
     * @param busiId busiId
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    
    /**
     * 获取 subjectNo
     * @return subjectNo.
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    
    /**
     * 设置 subjectNo
     * @param subjectNo subjectNo
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    
    /**
     * 获取 accureFlg
     * @return accureFlg.
     */
    public String getAccureFlg() {
        return accureFlg;
    }

    
    /**
     * 设置 accureFlg
     * @param accureFlg accureFlg
     */
    public void setAccureFlg(String accureFlg) {
        this.accureFlg = accureFlg;
    }

    
    /**
     * 获取 accureyieldNo
     * @return accureyieldNo.
     */
    public BigDecimal getAccureyieldNo() {
        return accureyieldNo;
    }

    
    /**
     * 设置 accureyieldNo
     * @param accureyieldNo accureyieldNo
     */
    public void setAccureyieldNo(BigDecimal accureyieldNo) {
        this.accureyieldNo = accureyieldNo;
    }

    
    /**
     * 获取 overdraftflg
     * @return overdraftflg.
     */
    public String getOverdraftflg() {
        return overdraftflg;
    }

    
    /**
     * 设置 overdraftflg
     * @param overdraftflg overdraftflg
     */
    public void setOverdraftflg(String overdraftflg) {
        this.overdraftflg = overdraftflg;
    }

    
    /**
     * 设置 bokSetId
     * @param bokSetId bokSetId
     */
    public void setBokSetId(String bokSetId) {
        this.bokSetId = bokSetId;
    }
}
