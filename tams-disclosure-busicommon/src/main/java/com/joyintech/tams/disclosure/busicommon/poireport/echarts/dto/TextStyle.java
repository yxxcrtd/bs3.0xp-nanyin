package com.joyintech.tams.disclosure.busicommon.poireport.echarts.dto;

/**
 * 文本样式
 */
public class TextStyle {
	
	public TextStyle(String fontSize,String fontWeight,String color) {
		setFontSize(fontSize);
		setFontWeight(fontWeight);
		setColor(color);
	}

	/**
	 * 大小
	 */
	private String fontSize;

	/**
	 * 粗细
	 * 
	 * normal、bold、bolder、lighter、100|200|300|400...
	 */
	private String fontWeight;

	/**
	 * 颜色
	 */
	private String color;

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public String getFontWeight() {
		return fontWeight;
	}

	public void setFontWeight(String fontWeight) {
		this.fontWeight = fontWeight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
