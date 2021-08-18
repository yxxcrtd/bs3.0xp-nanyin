package com.joyintech.tams.invest.risk.valtblimport.dto;


public enum ValIndex {

	SUBJECTCODE("科目代码"),
	SUBJECTNAME("科目名称"),
	QUANTITY("数量"), // 数量										/**修改**/
	UNIT_COST("单位成本"), // 单位成本
	COST("成本（本币）"), // 成本（本币）								/**修改**/
	CTNWR("成本占比"), // 成本占比									/**修改**/
	MCP("行情价格"), // 行情价格										/**修改**/
	SMV("市值（本币）"), // 市值（本币）								/**修改**/
	MCR("市值占比"), // 市值占比										/**修改**/
	VAL_INC("估值增值（本币）"), // 估值增值（本币）						/**修改**/
	SUSPENSION_INFO("停牌信息"), // 停牌信息
	EQUITY_INFO("权益信息"), // 权益信息
	COST_ORI("成本（原币）"), // 成本（原币）							/**新增 **/
	SMV_ORI("市值（原币）"), // 市值（原币）							/**新增 **/
	VAL_INC_ORI("估值增值（原币）"), // 估值增值（原币）					/**新增 **/
	CCY("币种"), // 币种												/**新增 **/
	EXCHANGE_RATE("汇率");// 汇率									/**新增 **/

	private final String desc;

	ValIndex(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return this.desc;
	}
}
