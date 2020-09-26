/*
 * 文件名：ProdFacotrInfo002Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：Administrator
 * 修改时间：2017年12月26日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.report.submitmaintenance.prodfactorinfo.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.common.base.entity.ChbPrdDeclareSub;
import com.joyintech.tams.common.base.entity.ChbPrdIssueSub;
import com.joyintech.tams.common.base.entity.ChbPrdOrgDeclareSub;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2017年12月26日
 * @see ProdFacotrInfo002Dto
 * @since 1.0
 */
public class ProdFacotrInfo002Dto {
    /**
     * 基础信息list(维护表中为空)
     */
    private List<ChbPrdBaseinfo> baseListN = new ArrayList<ChbPrdBaseinfo>();
    /**
     * 基础信息list（维护表中不为空）
     */
    private List<ChbPrdBaseinfo> baseListO = new ArrayList<ChbPrdBaseinfo>();
    /**
     * 申报登记list
     */
    private List<ChbPrdDeclareSub> decalList = new ArrayList<ChbPrdDeclareSub>();
    /**
     * 发行登记list
     */
    private List<ChbPrdIssueSub> issueList = new ArrayList<ChbPrdIssueSub>();
    /**
     * 更新申报登记
     */
    private List<ChbPrdDeclareSub> decalListO = new ArrayList<ChbPrdDeclareSub>();
    /**
     * 更新发行登记
     */
    private List<ChbPrdIssueSub> issueListO = new ArrayList<ChbPrdIssueSub>();
    /**
     * 机构和私人银行list
     */
    private List<ChbPrdOrgDeclareSub> orgList = new ArrayList<ChbPrdOrgDeclareSub>();
    /**
     * 机构和私人银行Olist
     */
    private List<ChbPrdOrgDeclareSub> orgListO = new ArrayList<ChbPrdOrgDeclareSub>();
    /**
     * 获取 baseListN
     * @return baseListN.
     */
    public List<ChbPrdBaseinfo> getBaseListN() {
        return baseListN;
    }
    
    /**
     * 设置 baseListN
     * @param baseListN baseListN
     */
    public void setBaseListN(List<ChbPrdBaseinfo> baseListN) {
        this.baseListN = baseListN;
    }
    
    /**
     * 获取 baseListO
     * @return baseListO.
     */
    public List<ChbPrdBaseinfo> getBaseListO() {
        return baseListO;
    }
    
    /**
     * 设置 baseListO
     * @param baseListO baseListO
     */
    public void setBaseListO(List<ChbPrdBaseinfo> baseListO) {
        this.baseListO = baseListO;
    }
    
    /**
     * 获取 decalList
     * @return decalList.
     */
    public List<ChbPrdDeclareSub> getDecalList() {
        return decalList;
    }
    
    /**
     * 设置 decalList
     * @param decalList decalList
     */
    public void setDecalList(List<ChbPrdDeclareSub> decalList) {
        this.decalList = decalList;
    }
    
    /**
     * 获取 issueList
     * @return issueList.
     */
    public List<ChbPrdIssueSub> getIssueList() {
        return issueList;
    }
    
    /**
     * 设置 issueList
     * @param issueList issueList
     */
    public void setIssueList(List<ChbPrdIssueSub> issueList) {
        this.issueList = issueList;
    }

    
    /**
     * 获取 decalListO
     * @return decalListO.
     */
    public List<ChbPrdDeclareSub> getDecalListO() {
        return decalListO;
    }

    
    /**
     * 设置 decalListO
     * @param decalListO decalListO
     */
    public void setDecalListO(List<ChbPrdDeclareSub> decalListO) {
        this.decalListO = decalListO;
    }

    
    /**
     * 获取 issueListO
     * @return issueListO.
     */
    public List<ChbPrdIssueSub> getIssueListO() {
        return issueListO;
    }

    
    /**
     * 设置 issueListO
     * @param issueListO issueListO
     */
    public void setIssueListO(List<ChbPrdIssueSub> issueListO) {
        this.issueListO = issueListO;
    }

    
    /**
     * 获取 orgList
     * @return orgList.
     */
    public List<ChbPrdOrgDeclareSub> getOrgList() {
        return orgList;
    }

    
    /**
     * 设置 orgList
     * @param orgList orgList
     */
    public void setOrgList(List<ChbPrdOrgDeclareSub> orgList) {
        this.orgList = orgList;
    }

    
    /**
     * 获取 orgListO
     * @return orgListO.
     */
    public List<ChbPrdOrgDeclareSub> getOrgListO() {
        return orgListO;
    }

    
    /**
     * 设置 orgListO
     * @param orgListO orgListO
     */
    public void setOrgListO(List<ChbPrdOrgDeclareSub> orgListO) {
        this.orgListO = orgListO;
    }
    
}
