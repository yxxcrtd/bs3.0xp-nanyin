package com.joyintech.tams.settle.batch.datacreate.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.SzdJsmx;
import com.joyintech.tams.common.base.entity.SzdSjsjg;
import com.joyintech.tams.common.base.entity.SzdSjsmx;

/**
 * 上中登及深中登的DBF文件生成用Write Dto
 * @author 陈卓
 * @date 2020/11/24
 * @see StlDataCreateWriteDto
 * @since 1.0
 */
public class StlDataCreateWriteDto extends NeedToWriteEntity {

    /**
     * 上中登JSMX-jsmx02
     */
    private List<SzdJsmx> jsmx02SzdJsmxList;
    
    /**
     * 上中登JSMX-jsmx03
     */
    private List<SzdJsmx> jsmx03SzdJsmxList;

    /**
     * 深中登SJSMX-sjsmx0
     */
    private List<SzdSjsmx> sjsmx0SzdSjsmxList;

    /**
     * 深中登SJSMX-sjsmx1
     */
    private List<SzdSjsmx> sjsmx1SzdSjsmxList;
    
    /**
     * 深中登SJSJG
     */
    private List<SzdSjsjg> szdSjsjgList;
    
    /**
     * 执行日期
     */
    private String tradeDate;
    
    public StlDataCreateWriteDto() {
    	jsmx02SzdJsmxList = new ArrayList<SzdJsmx>();
    	jsmx03SzdJsmxList = new ArrayList<SzdJsmx>();
    	sjsmx0SzdSjsmxList = new ArrayList<SzdSjsmx>();
    	sjsmx1SzdSjsmxList = new ArrayList<SzdSjsmx>();
    	szdSjsjgList = new ArrayList<SzdSjsjg>();
    	tradeDate = null;
    }
    

	public List<SzdJsmx> getJsmx02SzdJsmxList() {
		return jsmx02SzdJsmxList;
	}

	public void setJsmx02SzdJsmxList(List<SzdJsmx> jsmx02SzdJsmxList) {
		this.jsmx02SzdJsmxList = jsmx02SzdJsmxList;
	}

	public List<SzdJsmx> getJsmx03SzdJsmxList() {
		return jsmx03SzdJsmxList;
	}

	public void setJsmx03SzdJsmxList(List<SzdJsmx> jsmx03SzdJsmxList) {
		this.jsmx03SzdJsmxList = jsmx03SzdJsmxList;
	}

	public List<SzdSjsmx> getSjsmx0SzdSjsmxList() {
		return sjsmx0SzdSjsmxList;
	}

	public void setSjsmx0SzdSjsmxList(List<SzdSjsmx> sjsmx0SzdSjsmxList) {
		this.sjsmx0SzdSjsmxList = sjsmx0SzdSjsmxList;
	}

	public List<SzdSjsmx> getSjsmx1SzdSjsmxList() {
		return sjsmx1SzdSjsmxList;
	}

	public void setSjsmx1SzdSjsmxList(List<SzdSjsmx> sjsmx1SzdSjsmxList) {
		this.sjsmx1SzdSjsmxList = sjsmx1SzdSjsmxList;
	}

	public List<SzdSjsjg> getSzdSjsjgList() {
		return szdSjsjgList;
	}

	public void setSzdSjsjgList(List<SzdSjsjg> szdSjsjgList) {
		this.szdSjsjgList = szdSjsjgList;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
}
