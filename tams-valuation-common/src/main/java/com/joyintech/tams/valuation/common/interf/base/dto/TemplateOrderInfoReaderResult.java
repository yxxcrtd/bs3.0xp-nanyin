/*
 * 文件名：TodoDeal.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wudebao
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.FinProduct;

/**
 * 计提基础信息DTO 〈功能详细描述〉
 * 
 * @author wudebao
 * @version 1.0 2018年5月23日
 * @see TemplateOrderInfoReaderResult
 * @since 1.0
 */
public class TemplateOrderInfoReaderResult extends InterfNeedOperateEntity {

	/**
	 * 接口名称，一个接口可能对应多个模版
	 */
	private String interfName;
    /**
     * 模板类型
     */
    private String tmpType;
    /**
     * 模版中文名称
     */
    private String tmpCName;
    /**
     * 模版英文名称
     */
    private String tmpEName;
    /**
     * 文件目录
     */
    private String filePath;
    /**
     * 接入批次
     */
    private String orderNo;
    
    /**
     * 日志连接主键
     */
    private String parentSeqno;
    
    /**
     * dto名称
     */
    private String dtoName;
    
    /**
     * 系统ID
     */
    private String systemid;
    
	public String getParentSeqno() {
		return parentSeqno;
	}

	public void setParentSeqno(String parentSeqno) {
		this.parentSeqno = parentSeqno;
	}


	/**
     * 模板字段验证信息列表
     */
    private List<TemplateOrderInfoReaderDTO> listTemplateOrderInfoDTO = new ArrayList<TemplateOrderInfoReaderDTO>();

    /**
     * 所有产品list
     */
    private List<FinProduct> listValFinProduct= new ArrayList<FinProduct>();
    
    /**
     * 匹配List
     */
    private List<TemplateColMatchDTO> listTemplateColMatchDTO = new ArrayList<TemplateColMatchDTO>();
	
    /**
     * 系统参数
     */
    private Map<String,Object> params = new HashMap<>();

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
    /**
     *  取得 模板类型
     *  @return 模板类型
     */
    public String getTmpType() {
       return tmpType;
    }

    /**
     *  设置 模板类型
     *  @param tmpType 模板类型
     */
    public void setTmpType( String tmpType)  {
       this.tmpType = tmpType;
    }

    /**
     *  取得 模版中文名称
     *  @return 模版中文名称
     */
    public String getTmpCName() {
       return tmpCName;
    }

    /**
     *  设置 模版中文名称
     *  @param tmpCName 模版中文名称
     */
    public void setTmpCName( String tmpCName)  {
       this.tmpCName = tmpCName;
    }

    /**
     *  取得 模版英文名称
     *  @return 模版英文名称
     */
    public String getTmpEName() {
       return tmpEName;
    }

    /**
     *  设置 模版英文名称
     *  @param tmpEName 模版英文名称
     */
    public void setTmpEName( String tmpEName)  {
       this.tmpEName = tmpEName;
    }

    /**
     *  取得 文件目录
     *  @return 文件目录
     */
    public String getFilePath() {
       return filePath;
    }

    /**
     *  设置 文件目录
     *  @param filePath 文件目录
     */
    public void setFilePath( String filePath)  {
       this.filePath = filePath;
    }

    /**
     *  取得 接入批次
     *  @return 接入批次
     */
    public String getOrderNo() {
       return orderNo;
    }

    /**
     *  设置 接入批次
     *  @param order 接入批次
     */
    public void setOrderNo( String orderNo)  {
       this.orderNo = orderNo;
    }

    /**
     *  取得 模板字段验证信息列表
     *  @return 模板字段验证信息列表
     */
    public List<TemplateOrderInfoReaderDTO> getListTemplateOrderInfoDTO() {
       return listTemplateOrderInfoDTO;
    }

    /**
     *  设置 模板字段验证信息列表
     *  @param listTemplateOrderInfoDTO 模板字段验证信息列表
     */
    public void setListTemplateOrderInfoDTO(List<TemplateOrderInfoReaderDTO> listTemplateOrderInfoDTO) {
       this.listTemplateOrderInfoDTO = listTemplateOrderInfoDTO;
    }

    
    /**
     * 获取 listValFinProduct
     * @return listValFinProduct.
     */
    public List<FinProduct> getListValFinProduct() {
        return listValFinProduct;
    }

    
    /**
     * 设置 listValFinProduct
     * @param listValFinProduct listValFinProduct
     */
    public void setListValFinProduct(List<FinProduct> listValFinProduct) {
        this.listValFinProduct = listValFinProduct;
    }

	public String getInterfName() {
		return interfName;
	}

	public void setInterfName(String interfName) {
		this.interfName = interfName;
	}

	public String getDtoName() {
		return dtoName;
	}

	public void setDtoName(String dtoName) {
		this.dtoName = dtoName;
	}

    
    
    /**
     * 获取 systemid
     * @return systemid.
     */
    public String getSystemid() {
        return systemid;
    }

    
    /**
     * 设置 systemid
     * @param systemid systemid
     */
    public void setSystemid(String systemid) {
        this.systemid = systemid;
    }

    /**
     * 获取 listTemplateColMatchDTO
     * @return listTemplateColMatchDTO.
     */
    public List<TemplateColMatchDTO> getListTemplateColMatchDTO() {
        return listTemplateColMatchDTO;
    }

    
    /**
     * 设置 listTemplateColMatchDTO
     * @param listTemplateColMatchDTO listTemplateColMatchDTO
     */
    public void setListTemplateColMatchDTO(List<TemplateColMatchDTO> listTemplateColMatchDTO) {
        this.listTemplateColMatchDTO = listTemplateColMatchDTO;
    }
	/**
     * 存款类、拆借类资产是否生成四级科目号
     * add by wujie on 20181213 start
     */
    private String fourLevelSubjectKg;

	/**
	 * @return the fourLevelSubjectKg
	 */
	public String getFourLevelSubjectKg() {
		return fourLevelSubjectKg;
	}

	/**
	 * @param fourLevelSubjectKg the fourLevelSubjectKg to set
	 */
	public void setFourLevelSubjectKg(String fourLevelSubjectKg) {
		this.fourLevelSubjectKg = fourLevelSubjectKg;
	}
    /**add by wujie on 20181213 end**/
	
	/**add by wujie on 20181229 start remark:拆借、债券回购、债券借贷标的信息**/
	private Map<String,TargetFinProduct> mapTargetFinProduct = new HashMap<String,TargetFinProduct>();

	/**
	 * @return the mapTargetFinProduct
	 */
	public Map<String, TargetFinProduct> getMapTargetFinProduct() {
		return mapTargetFinProduct;
	}

	/**
	 * @param mapTargetFinProduct the mapTargetFinProduct to set
	 */
	public void setMapTargetFinProduct(Map<String, TargetFinProduct> mapTargetFinProduct) {
		this.mapTargetFinProduct = mapTargetFinProduct;
	}
	
	/**add by wujie on 20181229 end**/
}
