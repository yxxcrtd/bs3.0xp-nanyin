package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.jixu.common.util.Pages;
import com.jixu.framework.dao.intf.CommFuncDAO;
import com.jixu.mvc.control.model.IssuerModel;
import com.jixu.mvc.control.model.VouchersModel;
import com.orm.obj.Issuer;

/**
 * @ClassName 		IssuerDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface IssuerDao extends CommFuncDAO{

	public Issuer loadIssuer(@Param("issuerID")String issuerID);
	
	public Issuer loadIssuerByCode(@Param("issuerCode")String issuerCode);
	
	public List<Issuer> loadAllIssuer();
	
	/**
	 * 通过名字获取Issuer
	 * @return
	 */
	public List<Issuer> getIssuersByName(@Param("name")String name);
	
	public Issuer loadIssuerByID(@Param("issuerID")String issuerID);
	
	public Pages<IssuerModel> loadIssuerModel(@Param("pages")Pages<VouchersModel> pages, @Param("issuername") String issuername);
	
	public Page<IssuerModel> loadPageHelperIssuerModel(@Param("page")Page<IssuerModel> page, @Param("issuername") String issuername);
}
