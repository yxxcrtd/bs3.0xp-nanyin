package com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.dto;

import com.joyintech.tams.common.base.entity.RepFtpDetail;

public class FtpDetailDto extends RepFtpDetail{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String sysFilepath;
	
	private String revealChannel;
	
	private String filename;
	
	private String option;
	

	public String getSysFilepath() {
		return sysFilepath;
	}

	public void setSysFilepath(String sysFilepath) {
		this.sysFilepath = sysFilepath;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRevealChannel() {
		return revealChannel;
	}

	public void setRevealChannel(String revealChannel) {
		this.revealChannel = revealChannel;
	}

    
    public String getFilename() {
        return filename;
    }

    
    public void setFilename(String filename) {
        this.filename = filename;
    }

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
}
