/*
 * 
 */
package com.joyintech.service.disclosure.information.manage.workrule.dto;

import java.util.List;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.RepRule;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCode;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author ZhangYangFei
 * @version 1.0 2018年11月12日
 * @see ReportRule001Dto
 * @since 1.0
 */
public class ReportRule001Dto extends RepRule{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    private String channleName;

    private String defaultFileType;

    private String defaultFileName;

    private String  basisDateTypeName;
    
    /**
	 * 批量删除集合
	 */
	private List<ReportRule001Dto> selectedItem;
	
    public String getChannleName() {
        return this.getRevealChannel();
    }

    
    public String getDefaultFileType() {
        return CacheUtil.getSysParam(ReportGlobalConstant.REPORT_DOWNLOAD_PATH);
    }

    
    public String getDefaultFileName() {
        return CacheUtil.getSysParam(ReportGlobalConstant.REPORT_DEFAULT_FILENAME);
    }
	public String getBasisDateTypeName() {
		return  this.basisDateTypeName;
	}

	public void setBasisDateTypeName(String basisDateTypeName) {
		this.basisDateTypeName = basisDateTypeName;
	}


	public List<ReportRule001Dto> getSelectedItem() {
		return selectedItem;
	}


	public void setSelectedItem(List<ReportRule001Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}
    
	

}
