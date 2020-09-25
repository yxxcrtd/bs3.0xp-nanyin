/*
 * 文件名：FundSecuritiesAccountDao.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：lizhi
 * 修改时间：2020年1月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.fundsecuritiesaccount.dto;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.StlSecAcctInfo;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.basicdata.fundsecuritiesaccount.validator.FundSecuritiesAccountValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;


/**
 * 基金账户管理信息
 * 〈功能详细描述〉
 * @author lizhi
 * @version 1.0 2020年1月14日
 * @see FundSecuritiesAccountDto
 * @since 1.0
 */
@PageId("FundSecuritiesAccount002")
@ValidLogic(targetClass = FundSecuritiesAccountValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class FundSecuritiesAccountDto extends StlSecAcctInfo implements ITableRow,ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;
    
    /**
     * 选中的数据
     */
    private transient List<FundSecuritiesAccountDto> selectedItem;
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;
    
    /**
     * 文件名称
     */
    private String fileName;
    
    /**
     * 文件编号
     */
    private String seqNo;
    /**
     * 业务代码（关联其他表的）
     */
    private String bussinessId;
    /**
     * 文件服务器路径
     */
    private String path;

    /**
     * 文件列表
     */
    private transient List<UploadFile> fileArray;
    
    /**
     * 获取 fileArray
     * @return fileArray.
     */
    public List<UploadFile> getFileArray() {
        return fileArray;
    }

    /**
     * 设置 fileArray
     * @param fileArray fileArray
     */
    public void setFileArray(List<UploadFile> fileArray) {
        this.fileArray = fileArray;
    }
    
    public String getTradePlaceStr(){
        if (super.getTradePlace() == null) {
            return StringUtil.EMPTY;
        }
    	if(super.getTradePlace().contains(",")) {
    		StringBuffer str=new StringBuffer();
    		String[] split = super.getTradePlace().split(",");
    		for (int i = 0; i < split.length; i++) {
    			if(i==split.length-1) {
    				str.append(SysDataUtil.getNameFromDataDict(DictCode.交易场所, split[i]));
    			}else {
    				str.append(SysDataUtil.getNameFromDataDict(DictCode.交易场所, split[i])+",");
    			}
			}
    		return str.toString();
    	}else {
    		   return SysDataUtil.getNameFromDataDict(DictCode.交易场所, super.getTradePlace());
    	}
    }
    
    public String getSecAcctSourceStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.账户来源, super.getSecAcctSource());
    }
    
    /**
     * 托管机构Text值
     * @return String
     */
    public String getRegistOrgStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.登记托管机构,super.getRegistOrg());
    }

    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
    
    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<FundSecuritiesAccountDto> getSelectedItem() {
        return selectedItem;
    }
    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<FundSecuritiesAccountDto> selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }
    
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }
    
    /**
     * 获取 fileName
     * @return fileName.
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * 设置 fileName
     * @param fileName fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取 seqNo
     * @return seqNo.
     */
    public String getSeqNo() {
        return seqNo;
    }

    
    /**
     * 设置 seqNo
     * @param seqNo seqNo
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    
    /**
     * 获取 bussinessId
     * @return bussinessId.
     */
    public String getBussinessId() {
        return bussinessId;
    }

    /**
     * 设置 bussinessId
     * @param bussinessId bussinessId
     */
    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }

    /**
     * 获取 path
     * @return path.
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置 path
     * @param path path
     */
    public void setPath(String path) {
        this.path = path;
    }
    /**
     * 基金账户账号
     */
    @Override
    @NotEmpty(fieldName = "MstSecuritiesAccount001.secAcctId", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstSecuritiesAccount001.secAcctId", max = 30, groups = {ValidateGroupEdit.class})
    @PrimaryKey(value=Field.PK_1)
    public String getSecAcctId() {
        return super.getSecAcctId();
    }
    /**
     * 
     * 基金账户名称
     */
    @Override
    @NotEmpty(fieldName = "FundSecuritiesAccount001.secAcctName", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "FundSecuritiesAccount001.secAcctName", max = 30, groups = {ValidateGroupEdit.class})
    public String getSecAcctName() {
        return super.getSecAcctName();
    }
    /**
     * 基金管理人
     */
    @Override
    @NotEmpty(fieldName = "FundSecuritiesAccount001.manager", groups = {ValidateGroupEdit.class})
    public String getManager() {
        return super.getManager();
    }
    
    // 基金管理人名称
    private String managerName ;

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    
}
