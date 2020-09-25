
package com.joyintech.tams.invest.riskredis.quota.interlog.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.InterfRiskLogWithBLOBs;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.interlog.validator.RepInterLogValidator;

/**
 * 接口日志管理
 * @author 郑俊平
 * @version 1.0 2019年9月4日
 * @see RepInterLogDto
 * @since 1.0  
 */
@PageId("RepInterLog001")
@ValidLogic(targetClass = RepInterLogValidator.class, groups = {ValidateGroupEdit.class})
public class RepInterLogDto  extends InterfRiskLogWithBLOBs {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;
	private String pageModel;
	
	private String operateType;
	
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	public String getDoStatusAbbr(){
        return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.处理状态, this.getDoStatus());
    }
	
	public String getInfIdAbbr(){
        return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.接口系统ID, this.getInfId());
    }
	
	public String getSysCodeAbbr(){
        return StringUtil.EMPTY;
    }
	public String getTranDateRet() {
		return StringUtil.isNotEmpty(this.getTranDate())?DateUtil.format(DateUtil.parse(this.getTranDate())):"";
	}
	 
	public String getTranTimeRet() {
		if(StringUtil.isBlank(this.getTranTime())){
			return null;
		}
		SimpleDateFormat f = new SimpleDateFormat("hhmmss");
		Date d;
		try{
			d = f.parse(this.getTranTime());
		}catch(ParseException e){
			return this.getTranTime();
		}
		SimpleDateFormat f2 = new SimpleDateFormat("HH:mm:ss");
		return f2.format(d);
	}
}
