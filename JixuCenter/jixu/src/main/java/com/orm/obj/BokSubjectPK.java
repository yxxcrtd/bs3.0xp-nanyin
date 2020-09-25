package com.orm.obj;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BokSubjectPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8698812293417002400L;
	
	//账套代码
	private String bookset_id;
	
	//科目号
	private String subject_no;

	@Column(name="BOOKSET_ID")
	public String getBookset_id() {
		return bookset_id;
	}

	public void setBookset_id(String bookset_id) {
		this.bookset_id = bookset_id;
	}

	@Column(name="SUBJECT_NO")
	public String getSubject_no() {
		return subject_no;
	}

	public void setSubject_no(String subject_no) {
		this.subject_no = subject_no;
	}
}
