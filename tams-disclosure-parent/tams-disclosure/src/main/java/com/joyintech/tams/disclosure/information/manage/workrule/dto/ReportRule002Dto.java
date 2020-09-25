/*
 * 
 */
package com.joyintech.tams.disclosure.information.manage.workrule.dto;

import com.joyintech.tams.common.base.entity.RepRule;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author longhao
 * @version 1.0 2018年11月12日
 * @see ReportRule002Dto
 * @since 1.0
 */
public class ReportRule002Dto extends RepRule{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
  
    /**
     * 官网
     */
    private String revealChannelW;
    /**
     * 点对点
     */
    private String revealChannelO;
    /**
     * 其他
     */
    private String revealChannelC;
    private String pageModel;
    



    /**
     *  披露渠道（官网）
     * @return
     */
	public String getRevealChannelW() {
		return revealChannelW;
	}

	/**
	 * 设置 披露渠道（官网）
	 */
	public void setRevealChannelW(String revealChannelW) {
		this.revealChannelW = revealChannelW;
	}

	/**
	 * 披露渠道（点对点）
	 * @return
	 */
	public String getRevealChannelO() {
		return revealChannelO;
	}

	/**
	 * 设置 披露渠道（点对点）
	 * @param revealChannelO
	 */
	public void setRevealChannelO(String revealChannelO) {
		this.revealChannelO = revealChannelO;
	}

	/**
	 * 披露渠道 （其他）
	 * @return
	 */
	public String getRevealChannelC() {
		return revealChannelC;
	}

	/**
	 * 设置披露渠道（其他）
	 * @param revealChannelC
	 */
	public void setRevealChannelC(String revealChannelC) {
		this.revealChannelC = revealChannelC;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}



	
    

}
