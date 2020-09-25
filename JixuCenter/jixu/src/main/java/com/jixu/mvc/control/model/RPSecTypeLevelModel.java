package com.jixu.mvc.control.model;
/** 
 * @ClassName 		RPSecTypeLevelModel
 * @author  		Pan Wei 
 * @Date 			2016年8月15日 上午11:27:54 	
 * @Description		[]  
 */

public class RPSecTypeLevelModel {
	private int level1id;
	private String level1name;
	private int level2id;
	private String level2name;
	private int level3id;
	private String level3name;
	
	public RPSecTypeLevelModel(int level1id,String level1name,int level2id,String level2name,int level3id,String level3name) {
		setLevel1id(level1id);
		setLevel1name(level1name);
		setLevel2id(level2id);
		setLevel2name(level2name);
		setLevel3id(level3id);
		setLevel3name(level3name);
	}
	
	public RPSecTypeLevelModel() {
		// TODO Auto-generated constructor stub
	}

	public int getLevel1id() {
		return level1id;
	}

	public void setLevel1id(int level1id) {
		this.level1id = level1id;
	}

	public String getLevel1name() {
		return level1name;
	}

	public void setLevel1name(String level1name) {
		this.level1name = level1name;
	}

	public int getLevel2id() {
		return level2id;
	}

	public void setLevel2id(int level2id) {
		this.level2id = level2id;
	}

	public String getLevel2name() {
		return level2name;
	}

	public void setLevel2name(String level2name) {
		this.level2name = level2name;
	}

	public int getLevel3id() {
		return level3id;
	}

	public void setLevel3id(int level3id) {
		this.level3id = level3id;
	}

	public String getLevel3name() {
		return level3name;
	}

	public void setLevel3name(String level3name) {
		this.level3name = level3name;
	}
}
