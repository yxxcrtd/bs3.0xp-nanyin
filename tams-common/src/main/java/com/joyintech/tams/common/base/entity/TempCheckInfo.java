package com.joyintech.tams.common.base.entity;


public class TempCheckInfo {

	private String dtoEname;//check模板

	private String colprdName;//产品代码

	private String investType;//投资类型  PRD:产品端交易 INVEST:资产端交易

	private String colprdTypeName;//产品-金融产品类型

	private String colprdVdateName;//产品-产品起息日

	private String finprodTypes;//金融产品类型

	private String assetId;//资产代码

	private String colCcyName;//交易币种

    private String colChlId;//通道代码

	private String scheId;//方案代码
	private String acctNo;//modify by dongjun on 20190402 本方资金账户

	public TempCheckInfo(){}

	public TempCheckInfo(String dtoEname,String colprdName,String investType){
		this.setDtoEname(dtoEname);
		this.setColprdName(colprdName);
		this.setInvestType(investType);
	}

	/**add by wujie on 20210507 start**/
	public TempCheckInfo(String dtoEname,String colprdName,String investType,String assetId){
		this.setDtoEname(dtoEname);
		this.setColprdName(colprdName);
		this.setInvestType(investType);
		this.setAssetId(assetId);
	}
	/**add by wujie on 20210507 end**/

	/**
	 * @return the dtoEname
	 */
	public String getDtoEname() {
		return dtoEname;
	}

	/**
	 * @param dtoEname the dtoEname to set
	 */
	public void setDtoEname(String dtoEname) {
		this.dtoEname = dtoEname;
	}

	/**
	 * @return the colprdName
	 */
	public String getColprdName() {
		return colprdName;
	}

	/**
	 * @param colprdName the colprdName to set
	 */
	public void setColprdName(String colprdName) {
		this.colprdName = colprdName;
	}

	/**
	 * @return the investType
	 */
	public String getInvestType() {
		return investType;
	}

	/**
	 * @param investType the investType to set
	 */
	public void setInvestType(String investType) {
		this.investType = investType;
	}

	/**
	 * @return the finprodType
	 */
	public String getFinprodTypes() {
		return finprodTypes;
	}

	/**
	 * @param finprodTypes the finprodType to set
	 */
	public void setFinprodTypes(String finprodTypes) {
		this.finprodTypes = finprodTypes;
	}

	/**
	 * @return the assetId
	 */
	public String getAssetId() {
		return assetId;
	}

	/**
	 * @param assetId the assetId to set
	 */
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	/**
	 * @return the colCcyName
	 */
	public String getColCcyName() {
		return colCcyName;
	}

	/**
	 * @param colCcyName the colCcyName to set
	 */
	public void setColCcyName(String colCcyName) {
		this.colCcyName = colCcyName;
	}

	/**
	 * @return the colprdTypeName
	 */
	public String getColprdTypeName() {
		return colprdTypeName;
	}

	/**
	 * @param colprdTypeName the colprdTypeName to set
	 */
	public void setColprdTypeName(String colprdTypeName) {
		this.colprdTypeName = colprdTypeName;
	}

	/**
	 * @return the colprdVdateName
	 */
	public String getColprdVdateName() {
		return colprdVdateName;
	}

	/**
	 * @param colprdVdateName the colprdVdateName to set
	 */
	public void setColprdVdateName(String colprdVdateName) {
		this.colprdVdateName = colprdVdateName;
	}

	public String getColChlId() {
		return colChlId;
	}

	public void setColChlId(String colChlId) {
		this.colChlId = colChlId;
	}

	public String getScheId() {
		return scheId;
	}

	public void setScheId(String scheId) {
		this.scheId = scheId;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

}
