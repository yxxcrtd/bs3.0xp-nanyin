/*
 * 文件名：BondDialogueQuetoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：caoguo
 * 修改时间：2020年5月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.background.component.uplink.interbank.business.dto;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.joyintech.background.component.common.pack.common.base.BaseEntity;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteBondDetHis;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteBondHis;

/**
 * 现券对话报价DTO
 * @author caoguo
 * @version 1.0 2020年5月7日
 * @see BondDialogueQuoteDto
 * @since 1.0
 */
public class BondDialogueQuoteDto extends BaseEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2255095189310448122L;

    /**
     * 银行间上行报价属性配置
     */
    private Map<String, String> attrMap;

    /**
     * 银行间报价主信息
     */
    private IbuQuoteBondDto ibuQuoteBondDto;
    
    /**
     * 银行间报价金额基对手信息
     */
    private IbuQuoteBondDetDto ibuQuoteBondDetDto;
    
    /**
     * 银行间报价主流程信息
     */
    private WflIbuQuoteBond2Dto wflIbuQuoteBond2Dto;
    
    /**
     * 银行间报价金额基对手信息
     */
    private WflIbuQuoteBondDetDto wflIbuQuoteBondDetDto;
    
    
    /**
     * 银行间报价指令关系业务表
     */
    private List<IbuQuoteEtrReDto> ibuQuoteEtrReDtoList;


    /**
     * 银行间报价指令关系流程表
     */
    private List<WflIbuQuoteEtrReDto> wflIbuQuoteEtrReDtoList;

    /**
     * 是否多组合
     */
    private String isDzh;
    
    
	public BondDialogueQuoteDto(IbuQuoteBondDto ibuQuoteBondDto, IbuQuoteBondDetDto ibuQuoteBondDetDto) {
		this.ibuQuoteBondDto = ibuQuoteBondDto;
		this.ibuQuoteBondDetDto = ibuQuoteBondDetDto;
	}

	public BondDialogueQuoteDto(WflIbuQuoteBond2Dto wflIbuQuoteBond2Dto, WflIbuQuoteBondDetDto wflIbuQuoteBondDetDto) {
		this.wflIbuQuoteBond2Dto = wflIbuQuoteBond2Dto;
		this.wflIbuQuoteBondDetDto = wflIbuQuoteBondDetDto;
	}
    
    public BondDialogueQuoteDto() {
        
    }

    /**
     * 获取 ibuQuoteBondDto
     * @return ibuQuoteBondDto.
     */
    public IbuQuoteBondDto getIbuQuoteBondDto() {
        return ibuQuoteBondDto;
    }

    /**
     * 设置 ibuQuoteBondDto
     * @param ibuQuoteBondDto ibuQuoteBondDto
     */
    public void setIbuQuoteBondDto(IbuQuoteBondDto ibuQuoteBondDto) {
        this.ibuQuoteBondDto = ibuQuoteBondDto;
    }

    /**
     * 获取 ibuQuoteBondDetDto
     * @return ibuQuoteBondDetDto.
     */
    public IbuQuoteBondDetDto getIbuQuoteBondDetDto() {
        return ibuQuoteBondDetDto;
    }

    /**
     * 设置 ibuQuoteBondDetDto
     * @param ibuQuoteBondDetDto ibuQuoteBondDetDto
     */
    public void setIbuQuoteBondDetDto(IbuQuoteBondDetDto ibuQuoteBondDetDto) {
        this.ibuQuoteBondDetDto = ibuQuoteBondDetDto;
    }

    /**
     * 获取 exeTypeIBU
     * @return exeTypeIBU.
     */
    public String getExeTypeIBU() {
        if (this.ibuQuoteBondDto != null) {
            return this.ibuQuoteBondDto.getExeType();
        }
        return null;
    }

    /**
     * 设置 exeTypeIBU
     * @param exeTypeIBU exeTypeIBU
     */
    public void setExeTypeIBU(String exeTypeIBU) {
        if (this.ibuQuoteBondDto != null) {
            this.ibuQuoteBondDto.setExeType(exeTypeIBU);
        }
    }

    /**
     * 获取 attrMap
     * @return attrMap.
     */
    public Map<String, String> getAttrMap() {
        return attrMap;
    }

    /**
     * 设置 attrMap
     * @param attrMap attrMap
     */
    public void setAttrMap(Map<String, String> attrMap) {
        this.attrMap = attrMap;
    }

    /**
     * 设置报价信息及报价明细信息的版本号
     * @param version Long 当前版本号
     * @param bVersion Long 前置版本号
     */
    public void setVersion(Long version, Long bVersion) {
        if (this.ibuQuoteBondDto != null) {
            this.ibuQuoteBondDto.setVersion(version);
            this.ibuQuoteBondDto.setbVersion(bVersion);
        }
        if (this.ibuQuoteBondDetDto != null) {
            this.ibuQuoteBondDetDto.setVersion(version);
        }
    }

    public void setIbuQuoteBondDtoByHis(IbuQuoteBondHis quoteBondHis) {
        IbuQuoteBondDto quoteBond = new IbuQuoteBondDto();
        try {
            BeanUtils.copyProperties(quoteBond, quoteBondHis);
            this.ibuQuoteBondDto = quoteBond;
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    public IbuQuoteBondHis getIbuQuoteBondDtoByHis(){
    	return null;
    }

    public void setIbuQuoteBondDetDtoByHis(IbuQuoteBondDetHis quoteBondDetHis) {
        IbuQuoteBondDetDto quoteBondDet = new IbuQuoteBondDetDto();
        try {
            BeanUtils.copyProperties(quoteBondDet, quoteBondDetHis);
            this.ibuQuoteBondDetDto = quoteBondDet;
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    public IbuQuoteBondDetHis getIbuQuoteBondDetDtoByHis(){
    	return null;
    }
    
    public boolean isEmpty() {
        return this.ibuQuoteBondDto == null && this.ibuQuoteBondDetDto == null;
    }

	public WflIbuQuoteBond2Dto getWflIbuQuoteBond2Dto() {
		return wflIbuQuoteBond2Dto;
	}

	public void setWflIbuQuoteBond2Dto(WflIbuQuoteBond2Dto wflIbuQuoteBond2Dto) {
		this.wflIbuQuoteBond2Dto = wflIbuQuoteBond2Dto;
	}

	public WflIbuQuoteBondDetDto getWflIbuQuoteBondDetDto() {
		return wflIbuQuoteBondDetDto;
	}

	public void setWflIbuQuoteBondDetDto(WflIbuQuoteBondDetDto wflIbuQuoteBondDetDto) {
		this.wflIbuQuoteBondDetDto = wflIbuQuoteBondDetDto;
	}

	public List<IbuQuoteEtrReDto> getIbuQuoteEtrReDtoList() {
		return ibuQuoteEtrReDtoList;
	}

	public void setIbuQuoteEtrReDtoList(List<IbuQuoteEtrReDto> ibuQuoteEtrReDtoList) {
		this.ibuQuoteEtrReDtoList = ibuQuoteEtrReDtoList;
	}

	public List<WflIbuQuoteEtrReDto> getWflIbuQuoteEtrReDtoList() {
		return wflIbuQuoteEtrReDtoList;
	}

	public void setWflIbuQuoteEtrReDtoList(List<WflIbuQuoteEtrReDto> wflIbuQuoteEtrReDtoList) {
		this.wflIbuQuoteEtrReDtoList = wflIbuQuoteEtrReDtoList;
	}

	public String getIsDzh() {
		return isDzh;
	}

	public void setIsDzh(String isDzh) {
		this.isDzh = isDzh;
	}

}
