package com.joyintech.tams.utils.poi;

import java.util.List;

public class PoiTableRule {
	
	public final static String loopMode_Col="col";
	public final static String loopMode_Row="row";
	
	private String foreach;
	private String loopMode;
	private List<String> fields;
	private int startRow;
	private int startCol;
	private int mergeRow;
	private int mergeCol;
	
	public String getForeach() {
		return foreach;
	}
	public void setForeach(String foreach) {
		this.foreach = foreach;
	}
	public String getLoopMode() {
		return loopMode;
	}
	public void setLoopMode(String loopMode) {
		this.loopMode = loopMode;
	}
	public List<String> getFields() {
		return fields;
	}
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getStartCol() {
		return startCol;
	}
	public void setStartCol(int startCol) {
		this.startCol = startCol;
	}
	public int getMergeRow() {
		return mergeRow;
	}
	public void setMergeRow(int mergeRow) {
		this.mergeRow = mergeRow;
	}
	public int getMergeCol() {
		return mergeCol;
	}
	public void setMergeCol(int mergeCol) {
		this.mergeCol = mergeCol;
	}
	
}
