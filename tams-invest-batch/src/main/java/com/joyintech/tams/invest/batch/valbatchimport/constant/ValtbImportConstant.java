package com.joyintech.tams.invest.batch.valbatchimport.constant;
/**
 * 估值表导入枚举值转化常量
 * @author xzp
 *
 */
public enum ValtbImportConstant {
	
	VALUATION_TAB_ENUM_SSZB("VALUATION_TAB_ENUM_SSZB", "实收资本"),
	VALUATION_TAB_ENUM_SSZBJE_SKIP("VALUATION_TAB_ENUM_SSZB_SKIP", "实收资本金额;实收资本金额:;实收资本金额：;实收资本（金额）;实收资本（金额）:;实收资本（金额）：;实收资本(金额);实收资本(金额):;实收资本(金额)：;"),
	//实收资本识别枚举值增加  mody by jiale 20200910  begin
	VALUATION_TAB_ENUM_SSZB2("VALUATION_TAB_ENUM_SSZB2", "实收资本 ;实收信托 ;实收资本：;实收资本:;实收资本 ;实收资本;实收信托;实收信托:;实收信托：;实收资本数量;实收资本数量:;实收资本数量：;实收资本（数量）;实收资本（数量）:;实收资本（数量）：;实收资本(数量);实收资本(数量):;实收资本(数量)：;实收资本数量 ;实收资本数量: ;实收资本数量： ;实收资本（数量） ;实收资本（数量）: ;实收资本（数量）： ;实收资本(数量) ;实收资本(数量): ;实收资本(数量)： ;"),
	//实收资本识别枚举值增加  mody by jiale 20200910  end
	VALUATION_TAB_ENUM_ZCHJ("VALUATION_TAB_ENUM_ZCHJ", "资产类合计"),
	VALUATION_TAB_ENUM_FZHJ("VALUATION_TAB_ENUM_FZHJ", "负债类合计"),
	VALUATION_TAB_ENUM_JJZCJZ("VALUATION_TAB_ENUM_JJZCJZ", "基金资产净值"),
	VALUATION_TAB_ENUM_JRDWJZ("VALUATION_TAB_ENUM_JRDWJZ", "今日单位净值"),
	VALUATION_TAB_ENUM_LJDWJZ("VALUATION_TAB_ENUM_LJDWJZ", "累计单位净值"),
	VALUATION_TAB_ENUM_RESOURCE_PATH("PATH", "/valenmu/enmu.properties");
		
	private String code;
	private String desc;

	private ValtbImportConstant(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
