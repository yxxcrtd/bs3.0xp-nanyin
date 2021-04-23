package com.joyintech.tams.invest.basicdata.mstsaledepartment.dto;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Address;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.MobilePhone;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.OfficePhone;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.basicdata.mstsaledepartment.validator.MstSaleDepartment001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;

@PageId("MstSaleDepartment002")
@ValidLogic(targetClass = MstSaleDepartment001Validator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class MstSaleDepartmentDto extends MstSaleDepartmentBaseDto{
	  /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
	/**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 页面元素
     */
    private String elementId;
    
    /**
     * 选中的数据
     */
    private transient List<MstSaleDepartmentDto> selectedItem;
    
    /**
     * 页面模式
     */
    private String pageModel;
    
    
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public List<MstSaleDepartmentDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<MstSaleDepartmentDto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}
	
	 /**
     * 营业部代码
     */
    @Override
    @NotEmpty(fieldName = "MstSaleDepartment001.saledepid", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstSaleDepartment001.saledepid", max = 32, groups = {ValidateGroupEdit.class})
    @PrimaryKey(value=Field.PK_1)
	public String getSaledepid() {
		return super.getSaledepid();
	}
	 /**
     * 营业部名称
     */
    @Override
    @NotEmpty(fieldName = "MstSaleDepartment001.saledepname", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstSaleDepartment001.saledepname", max = 50, groups = {ValidateGroupEdit.class})
	public String getSaledepname() {
		return super.getSaledepname();
	}
    
	 /**
     * 所属券商
     */
    @Override
    @NotEmpty(fieldName = "MstSaleDepartment001.brokerId", groups = {ValidateGroupEdit.class})
	public String getBrokerid() {
		return super.getBrokerid();
	}
    
    /**
     * 上交所席位号
     */
    @Override
//    @NotEmpty(fieldName = "MstSaleDepartment002.shseatno", groups = {ValidateGroupEdit.class})
    public String getShseatno() {
		return super.getShseatno();
	}

    /**
     * 深交所席位号
     */
    @Override
//    @NotEmpty(fieldName = "MstSaleDepartment002.szseatno", groups = {ValidateGroupEdit.class})
	public String getSzseatno() {
		return super.getSzseatno();
	}
    
    
    /**
     * 上交所席位号
     */
    @Override
//    @NotEmpty(fieldName = "MstSaleDepartment002.shseatno", groups = {ValidateGroupEdit.class})
    public String getZjseatno() {
		return super.getZjseatno();
	}

    /**
     * 深交所席位号
     */
    @Override
//    @NotEmpty(fieldName = "MstSaleDepartment002.szseatno", groups = {ValidateGroupEdit.class})
	public String getSqseatno() {
		return super.getSqseatno();
	}
    
    
    /**
    
    /**
     * IP地址
     */
    @Override
    @NotEmpty(fieldName = "MstSaleDepartment001.ipaddr", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstSaleDepartment001.ipaddr", max = 50, groups = {ValidateGroupEdit.class})
	public String getIpaddr() {
		return super.getIpaddr();
	}
    
    /**
     *端口号 
     */
    @Override
    @NotEmpty(fieldName = "MstSaleDepartment001.portnum", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstSaleDepartment001.portnum", max = 50, groups = {ValidateGroupEdit.class})
	public String getPortnum() {
		return super.getPortnum();
	}
    
    /**
     * 联系地址
     */
    @Override
    @NotEmpty(fieldName = "MstSaleDepartment001.contactaddr", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstSaleDepartment001.contactaddr", max = 500, groups = {ValidateGroupEdit.class})
    @Address(fieldName = "MstSaleDepartment001.contactaddr", groups = {ValidateGroupEdit.class})
	public String getContactaddr() {
		return super.getContactaddr();
	}
    
    /**
     * 联系电话
     */
    @Override
    @NotEmpty(fieldName = "MstSaleDepartment001.contacttel", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstSaleDepartment001.contacttel", max = 50, groups = {ValidateGroupEdit.class})
    @MobilePhone(fieldName = "MstSaleDepartment001.contacttel", groups = {ValidateGroupEdit.class})
	public String getContacttel() {
		return super.getContacttel();
	}
    
    /**
     * 传真号码
     */
    @Override
    @Length(fieldName = "MstSaleDepartment001.faxno", max = 50, groups = {ValidateGroupEdit.class})
    @OfficePhone(fieldName = "MstSaleDepartment001.faxno", groups = {ValidateGroupEdit.class})
	public String getFaxno() {
		return super.getFaxno();
	}
    
    /**
     * 备用IP地址
     */
    @Override
    @Length(fieldName = "MstSaleDepartment001.ipaddr2", max = 50, groups = {ValidateGroupEdit.class})
	public String getIpaddr2() {
		return super.getIpaddr2();
	}
    
    /**
     * 备用端口
     */
    @Override
    @Length(fieldName = "MstSaleDepartment001.portnum2", max = 50, groups = {ValidateGroupEdit.class})
	public String getPortnum2() {
		return super.getPortnum2();
	}
    
    /**
     * 申报单位
     */
    @Override
    @NotEmpty(fieldName = "MstSaleDepartment001.declareunitName", groups = {ValidateGroupEdit.class})
	public String getDeclareunit(){
        return super.getDeclareunit();
    }
    
    /**
     * 是否净价回报
     */
    @Override
	public String getIsnetreturn(){
        return super.getIsnetreturn();
    }
    
    /**
     * 申报单位
     */
    @Override
	public String getHandletype(){
        return super.getHandletype();
    }
    
    /**
     * 柜台接口类型
     */
    @Override
    @NotEmpty(fieldName = "MstSaleDepartment001.intertypeName", groups = {ValidateGroupEdit.class})
	public String getIntertype(){
        return super.getIntertype();
    }
    
    /**
     * 柜台参数
     */
    @Override
    @NotEmpty(fieldName = "MstSaleDepartment002.serverinfo", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstSaleDepartment002.serverinfo", max = 1024, groups = {ValidateGroupEdit.class})
	public String getServerinfo(){
        return super.getServerinfo();
    }
    
    /**
     * 申报单位Text值
     * @return String
     */
	public String getDeclareunitName(){
        return SysDataUtil.getNameFromDataDict(DictCode.申报单位, super.getDeclareunit());
    }
    
    /**
     * 是或否Text值
     * @return String
     */
	public String getIsnetreturnName(){
        return SysDataUtil.getNameFromDataDict(DictCode.是或否, super.getIsnetreturn());
    }
    
    /**
     * 特殊处理业务Text值
     * @return String
     */
	public String getHandletypeName(){
        return SysDataUtil.getNameFromDataDict(DictCode.特殊处理业务, super.getHandletype());
    }
    
    /**
     * 柜台接口类型Text值
     * @return String
     */
	public String getIntertypeName(){
        return SysDataUtil.getNameFromDataDict(DictCode.柜台接口类型, super.getIntertype());
    }
}
