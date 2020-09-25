package com.jixu.data.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName 		MsgEvent
 * @author  		Pan Wei 
 * @Date 			2018年3月1日 下午4:41:19 	
 * @Description		[]  
 * @return  
 */

@Entity 
@Table(name="MsgEvent") 
public class MsgEvent {
	
	private int id;
	
	//消息标题
	private String eventTitle;
	
	//消息内容
	private String eventContent;
	
	//消息时间
	private Date eventTime;
	
	//是否已读
	private boolean readFlag;
	
	private int userID;
	
	private String portnum;
	
	private int clientID;
	
	//消息等级，紧急，普通，一般等
	private int eventLevel;
	
	//消息类型,系统消息，用户消息，投资组合消息
	private int eventType;
	
	//目标链接地址
	private String targetURL;
	
	/**
	 * @param eventType	消息类型 1.系统消息 2.用户层面消息 3.投资组合层面消息 4.私募公司层面消息
	 * 		如果是3，需要给portnum设值
	 * 		如果是4，需要给clientID设值
	 * @param userID	用户ID
	 * @param eventTitle	消息标题
	 * @param eventContent	消息内容
	 */
	public MsgEvent(int eventType, int userID, String eventTitle, String eventContent) {
		this.setEventTitle(eventTitle);
		this.setUserID(userID);
		this.setEventTitle(eventTitle);
		this.setEventContent(eventContent);
		this.setReadFlag(false);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="eventTitle")
	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	@Column(name="eventContent")
	public String getEventContent() {
		return eventContent;
	}

	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}

	@Column(name="eventTime")
	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	@Column(name="readFlag")
	public boolean isReadFlag() {
		return readFlag;
	}

	public void setReadFlag(boolean readFlag) {
		this.readFlag = readFlag;
	}

	@Column(name="userID")
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	@Column(name="portnum")
	public String getPortnum() {
		return portnum;
	}

	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}

	@Column(name="clientID")
	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	@Column(name="eventLevel")
	public int getEventLevel() {
		return eventLevel;
	}

	public void setEventLevel(int eventLevel) {
		this.eventLevel = eventLevel;
	}

	@Column(name="eventType")
	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	@Column(name="targetURL")
	public String getTargetURL() {
		return targetURL;
	}

	public void setTargetURL(String targetURL) {
		this.targetURL = targetURL;
	}
}
