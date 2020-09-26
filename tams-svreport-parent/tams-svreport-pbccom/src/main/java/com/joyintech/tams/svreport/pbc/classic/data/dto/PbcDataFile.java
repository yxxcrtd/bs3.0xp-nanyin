package com.joyintech.tams.svreport.pbc.classic.data.dto;

import java.util.Date;

import com.joyintech.framework.export.paginator.domain.ITableRow;

/**
 * 描述:PBC_DATFILE表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-06-10
 */
public class PbcDataFile implements ITableRow{
    /**
     * 文件编号
     */
    private String fileid;

    /**
     * 文件代码
     */
    private String filecode;

    /**
     * 生成日期
     */
    private Date cdate;

    /**
     * 总行机构代码
     */
    private String headOrgcode;

    /**
     * 以日为周期的文件顺序号
     */
    private String fileseqno;

    /**
     * 文件存放路径
     */
    private String filepath;

    /**
     * 数据状态
     */
    private String status;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 报送日期
     */
    private Date submitTime;
    
    /**
     * zip文件名称
     */
    private String zipName;
    
    /**
     * 生成日期的字符串形式，便于前台显示
     */
    private String cDateStr;
    
    public String getcDateStr() {
		return cDateStr;
	}

	public void setcDateStr(String cDateStr) {
		this.cDateStr = cDateStr;
	}

	public String getZipName() {
		return zipName;
	}

	public void setZipName(String zipName) {
		this.zipName = zipName;
	}

    /**
     * 文件编号
     * @return FILEID 文件编号
     */
    public String getFileid() {
        return fileid;
    }

    /**
     * 文件编号
     * @param fileid 文件编号
     */
    public void setFileid(String fileid) {
        this.fileid = fileid == null ? null : fileid.trim();
    }

    /**
     * 文件代码
     * @return FILECODE 文件代码
     */
    public String getFilecode() {
        return filecode;
    }

    /**
     * 文件代码
     * @param filecode 文件代码
     */
    public void setFilecode(String filecode) {
        this.filecode = filecode == null ? null : filecode.trim();
    }

    /**
     * 生成日期
     * @return CDATE 生成日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 生成日期
     * @param cdate 生成日期
     */
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 总行机构代码
     * @return HEAD_ORGCODE 总行机构代码
     */
    public String getHeadOrgcode() {
        return headOrgcode;
    }

    /**
     * 总行机构代码
     * @param headOrgcode 总行机构代码
     */
    public void setHeadOrgcode(String headOrgcode) {
        this.headOrgcode = headOrgcode == null ? null : headOrgcode.trim();
    }

    /**
     * 以日为周期的文件顺序号
     * @return FILESEQNO 以日为周期的文件顺序号
     */
    public String getFileseqno() {
        return fileseqno;
    }

    /**
     * 以日为周期的文件顺序号
     * @param fileseqno 以日为周期的文件顺序号
     */
    public void setFileseqno(String fileseqno) {
        this.fileseqno = fileseqno == null ? null : fileseqno.trim();
    }

    /**
     * 文件存放路径
     * @return FILEPATH 文件存放路径
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * 文件存放路径
     * @param filepath 文件存放路径
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    /**
     * 数据状态
     * @return STATUS 数据状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 数据状态
     * @param status 数据状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 机构代码
     * @return ORG_CODE 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建部门
     * @return CREATE_DEPT 创建部门
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门
     * @param createDept 创建部门
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 报送日期
     * @return SUBMIT_TIME 报送日期
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 报送日期
     * @param submitTime 报送日期
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }
}