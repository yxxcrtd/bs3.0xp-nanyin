package com.joyintech.tams.disclosure.busicommon.poireport.echarts.dto;

/**
 * echarts标题
 */
public class Title {

	public Title(String text, String subtext, String left, String top,TextStyle textStyle) {
		setText(text);
		setSubtext(subtext);
		setLeft(left);
		setTop(top);
		setTextStyle(textStyle);
	}

	/**
	 * 文本
	 */
	private String text;

	/**
	 * 子标题文本
	 */
	private String subtext;

	/**
	 * 离容器左侧的距离（auto、left、right、center、20、20%）
	 */
	private String left;

	/**
	 * 离容器上侧的距离（auto、top、middle、bottom、20、20%）
	 */
	private String top;

	/**
	 * 离容器下侧的距离（auto、20、20%）
	 */
	private String bottom;

	/**
	 * 样式
	 */
	private TextStyle textStyle;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSubtext() {
		return subtext;
	}

	public void setSubtext(String subtext) {
		this.subtext = subtext;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public TextStyle getTextStyle() {
		return textStyle;
	}

	public void setTextStyle(TextStyle textStyle) {
		this.textStyle = textStyle;
	}

	public String getBottom() {
		return bottom;
	}

	public void setBottom(String bottom) {
		this.bottom = bottom;
	}

}
