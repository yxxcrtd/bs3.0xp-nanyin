package com.joyintech.tams.invest.risk.creditmanagement.creditrulemanage.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.CreditRuleInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.risk.creditmanagement.creditrulemanage.validator.CreditRuleValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

@PageId("CreditRule002")
@ValidLogic(targetClass = CreditRuleValidator.class, groups = {ValidateGroupEdit.class,
	    ValidateGroupDelete.class})
public class CreditRuleDto extends CreditRuleInfo implements ProcessEntity{

    /**
     * 标签名称
     */
    private String creditTagName;

    /**
     * 画面处理模式
     */
    private String pageModel;

   /**
	* 选中列表
	*/
	 private List<CreditRuleDto> selectedItem;
	
	 public List<CreditRuleDto> getSelectedItem() {
		return selectedItem;
	 }

	 public void setSelectedItem(List<CreditRuleDto> selectedItem) {
		this.selectedItem = selectedItem;
	 }
	
    public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

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

	/**
     * 操作类型 - 工作流参数
     */
    private String operateType;

    /**
     * 按钮ID - 工作流参数
     */
    private String elementId;

    /**
     * 取得 标签名称
     * @return 标签名称
     */
    public String getCreditTagName() {
        return creditTagName;
    }

    @Override
    @PrimaryKey(value=Field.PK_1)
    @NotEmpty(fieldName = "CeRule002.astType1", groups = {ValidateGroupEdit.class})
    public String getAstType1() {
        return super.getAstType1();
    }

    @Override
    @PrimaryKey(value=Field.PK_2)
    @NotEmpty(fieldName = "CeRule002.deductObjType", groups = {ValidateGroupEdit.class})
    public String getDeductObjType() {
        return super.getDeductObjType();
    }

    @Override
    @NotEmpty(fieldName = "CeRule002.creditTag", groups = {ValidateGroupEdit.class})
    @PrimaryKey(value=Field.PK_3)
    public String getCreditTagId() {
        return super.getCreditTagId();
    }

    @Override
    @PrimaryKey(value=Field.PK_8)
    public String getCreditRuleId() {
        return super.getCreditRuleId();
    }

    /**
     * 设置 标签名称
     * @param creditTagName 标签名称
     */
    public void setCreditTagName(String creditTagName) {
        this.creditTagName = creditTagName == null ? null : creditTagName.trim();
    }

    /**
     * 资产大类名称
     */
    private String astType1Name;

    /**
     * 资产二级分类名称
     */
    private String astType2Name;

    /**
     * 资产三级分类名称
     */
    private String astType3Name;

    /**
     * 资产四级分类名称
     */
    private String astType4Name;

    /**
     * 资产五级分类名称
     */
    private String astType5Name;

    /**
     * 扣减对象名称
     */
    private String deductObjTypeName;

    /**
     * 额度分类名称
     */
    private String creditType;
    /**
     * 额度分类名称
     */
    private String creditTypeName;

    public String getCreditTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.额度分类, this.getCreditType());
    }

    public void setCreditTypeName(String creditTypeName) {
        this.creditTypeName = creditTypeName;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public String getAstType1Name() {
        return SysDataUtil.getNameFromDataDict(DictCode.授信资产类型, super.getAstType1());
    }

    @PrimaryKey(value=Field.PK_4)
    public String getAstType2Name() {
        String TypeName2 = super.getAstType2();
        if(TypeName2 != null){
            String TypeNameList[] = TypeName2.split (",");
            ArrayList<String> AstTypeName2 = new ArrayList();
            for (int i = 0; i < TypeNameList.length; i++){
                AstTypeName2.add (SysDataUtil.getNameFromDataDict(DictCode.授信资产二级分类, TypeNameList[i]));
            }
            String str = StringUtils.join(AstTypeName2.toArray(), ",");
            return str;
        }
        return "";
    }
    @PrimaryKey(value=Field.PK_5)
    public String getAstType3Name() {
        String TypeName3 = super.getAstType3();
        if(TypeName3 != null) {
            String TypeNameList[] = TypeName3.split (",");
            ArrayList<String> AstTypeName3 = new ArrayList ();
            for (int i = 0; i < TypeNameList.length; i++) {
                AstTypeName3.add (SysDataUtil.getNameFromDataDict (DictCode.授信资产三级分类, TypeNameList[i]));
            }
            String str = StringUtils.join (AstTypeName3.toArray (), ",");
            return str;
        }
        return "";
    }
    @PrimaryKey(value=Field.PK_6)
    public String getAstType4Name() {
        String TypeName4 = super.getAstType4();
        if(TypeName4 != null) {
            String TypeNameList[] = TypeName4.split (",");
            ArrayList<String> AstTypeName4 = new ArrayList ();
            for (int i = 0; i < TypeNameList.length; i++) {
                AstTypeName4.add (SysDataUtil.getNameFromDataDict (DictCode.授信资产四级分类, TypeNameList[i]));
            }
            String str = StringUtils.join (AstTypeName4.toArray (), ",");
            return str;
        }
        return "";
    }
    @PrimaryKey(value=Field.PK_7)
    public String getAstType5Name() {
        String TypeName5 = super.getAstType5();
        if(TypeName5 != null) {
            String TypeNameList[] = TypeName5.split (",");
            ArrayList<String> AstTypeName5 = new ArrayList ();
            for (int i = 0; i < TypeNameList.length; i++) {
                AstTypeName5.add (SysDataUtil.getNameFromDataDict (DictCode.授信资产五级分类, TypeNameList[i]));
            }
            String str = StringUtils.join (AstTypeName5.toArray (), ",");
            return str;
        }
        return "";
    }

    public String getDeductObjTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.扣减对象, super.getDeductObjType());
    }
}