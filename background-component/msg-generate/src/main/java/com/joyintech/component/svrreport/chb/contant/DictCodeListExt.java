package com.joyintech.component.svrreport.chb.contant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.background.component.common.pack.common.base.BaseDictCode;
import com.joyintech.background.component.common.pack.common.base.BaseDictCodeList;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
/**
 * 数据字典值
 * 
 * @description
 * @author zhaoyongsheng
 * @version 1.0 2020年10月17日
 * @see DictCodeListExt
 * @since 1.0
 */
public enum DictCodeListExt implements BaseDictCodeList {
	/* 中债报送从业人员相关数字字典 @Add by zhaoyongsheng 20201017 start*/
	从业人员类型_投资经理(DictCode.从业人员类型, "01","投资经理"),
	从业人员类型_产品审批人(DictCode.从业人员类型, "02","产品审批人"),
	从业人员类型_产品设计人(DictCode.从业人员类型, "03","产品设计人"),
	从业人员类型_产品营销人(DictCode.从业人员类型, "04","产品营销人"),
	从业人员类型_理财登记人(DictCode.从业人员类型, "05","理财登记人"),
	
	所属总行或分支行_总行部门(DictCode.所属总行或分支行, "01","总行部门"),
	所属总行或分支行_总行直属中心(DictCode.所属总行或分支行, "02","总行直属中心"),
	所属总行或分支行_分行(DictCode.所属总行或分支行, "03","分行"),
	所属总行或分支行_支行(DictCode.所属总行或分支行, "04","支行"),

	学历_初中(DictCode.学历, "01","初中"),
	学历_高中(DictCode.学历, "02","高中"),
	学历_中专(DictCode.学历, "03","中专"),
	学历_大专(DictCode.学历, "04","大专"),
	学历_本科(DictCode.学历, "05","本科"),
	学历_硕士研究生(DictCode.学历, "06","硕士研究生"),
	学历_博士研究生(DictCode.学历, "07","博士研究生"),
	学历_其他(DictCode.学历, "99","其他"),
	
	学位_学士(DictCode.学位, "01","学士"),
	学位_硕士(DictCode.学位, "02","硕士"),
	学位_博士(DictCode.学位, "03","博士"),
	学位_其他(DictCode.学位, "99","其他"),

	专业技术职称_助理级(DictCode.专业技术职称, "01","助理级"),
	专业技术职称_中级(DictCode.专业技术职称, "02","中级"),
	专业技术职称_高级(DictCode.专业技术职称, "03","高级"),
	专业技术职称_其他(DictCode.专业技术职称, "99","其他"),

	理财专业证书_行内专业证书(DictCode.理财专业证书, "01","行内专业证书"),
	理财专业证书_银行业专业人员职业资格证书(DictCode.理财专业证书, "02","银行业专业人员职业资格证书"),
	理财专业证书_其他(DictCode.理财专业证书, "99","其他"),
	
	登记业务分类_理财产品信息登记(DictCode.登记业务分类, "01","理财产品信息登记"),
	登记业务分类_投资者信息登记(DictCode.登记业务分类, "02","投资者信息登记"),

	登记人员类别_理财信息登记工作领导小组成员(DictCode.登记人员类别, "01","理财信息登记工作领导小组成员"),
	登记人员类别_系统管理员(DictCode.登记人员类别, "02","系统管理员"),
	登记人员类别_系统操作员(DictCode.登记人员类别, "03","系统操作员"),
	登记人员类别_系统开发及运维人员(DictCode.登记人员类别, "04","系统开发及运维人员"),
    ;
	/* 中债报送从业人员相关数字字典 @Add by zhaoyongsheng 20201017 end*/
	

	/**
	 * 对应于数据字典code
	 */
	private String value;

	/**
	 * 数据字典枚举
	 */
	private BaseDictCode dictCode;

	/**
	 * 明细名称
	 */
	private String codeName;

	DictCodeListExt(BaseDictCode dictCode, String value, String codeName) {
		this.dictCode = dictCode;
		this.value = value;
		this.setCodeName(codeName);
	}

	/**
	 * 获得数据字典详细类
	 *
	 * @param value    数据字典值
	 * @param dictCode 数据字典DictCode
	 * @return DictCodeList
	 * @see
	 * @since 1.0
	 */
	public static DictCodeListExt from(BaseDictCode dictCode, String value) {
		DictCodeListExt[] values = DictCodeListExt.values();
		for (DictCodeListExt code : values) {
			if (StringUtil.equals(value, code.getValue()) && code.getDictCode().equals(dictCode)) {
				return code;
			}
		}
		return null;
	}

	/**
	 * 获取数据字典明细valueList
	 *
	 * @param dictCode DictCode
	 * @return List&lt;SelectOptionDto&gt;
	 * @see
	 * @since 1.0
	 */
	public static List<String> valuesfromDictCode(BaseDictCode dictCode) {
		List<String> result = new ArrayList<>();
		DictCodeListExt[] values = DictCodeListExt.values();
		for (DictCodeListExt code : values) {
			if (code.getDictCode() == dictCode) {
				result.add(code.getValue());
			}
		}
		return result;
	}



	/**
	 * 获取 DictCode
	 *
	 * @return value.
	 */
	@Override
	public BaseDictCode getDictCode() {
		return dictCode;
	}

	/**
	 * 获取 value
	 *
	 * @return value.
	 */
	@Override
	public String getValue() {
		return value;
	}

	/**
	 * 设置 value
	 *
	 * @param value value
	 */
	@Override
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 获取 codeName
	 *
	 * @return codeName.
	 */
	@Override
	public String getCodeName() {
		return codeName;
	}

	/**
	 * 获取 带有引号的名称
	 *
	 * @return 带有引号的名称.
	 */
	public String getQuotesCodeName() {
		return "“" + codeName + "”";
	}

	/**
	 * 获取Integer value
	 *
	 * @return Integer value
	 */
	public Integer getIntegerCodeName() {
		Integer result = null;
		try {
			result = Integer.parseInt(codeName);
		} catch (Exception e) {
			// nothing to do
		}
		return result;
	}

	/**
	 * 获取Integer value
	 *
	 * @return Integer value
	 */
	public BigDecimal getBigDecimalCodeName() {
		BigDecimal result = null;
		try {
			result = new BigDecimal(codeName);
		} catch (Exception e) {
			// nothing to do
		}
		return result;
	}

	/**
	 * 设置 codeName
	 *
	 * @param codeName codeName
	 */
	@Override
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
}
