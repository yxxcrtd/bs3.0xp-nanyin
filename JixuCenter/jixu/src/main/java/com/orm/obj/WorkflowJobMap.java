package com.orm.obj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** 
 * @ClassName 		WorkflowJobMap
 * @author  		Pan Wei 
 * @Date 			2016年10月26日 下午11:15:03 	
 * @Description		[工作流中的基金会计对应的JobID，目前该表为原始数据，无法修改]  
 */

@Entity 
@Table(name="JXJ_WORKFLOWJOBMAP") 
public class WorkflowJobMap {
	private int id;
	private int workflowStep;
	private int jobID;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="WorkflowJobMap_ID_SEQ", allocationSize=1, initialValue=12)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="workflowStep")
	public int getWorkflowStep() {
		return workflowStep;
	}
	public void setWorkflowStep(int workflowStep) {
		this.workflowStep = workflowStep;
	}
	
	@Column(name="jobID")
	public int getJobID() {
		return jobID;
	}
	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
}
