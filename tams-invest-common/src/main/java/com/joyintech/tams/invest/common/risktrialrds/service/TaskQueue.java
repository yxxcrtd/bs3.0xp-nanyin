package com.joyintech.tams.invest.common.risktrialrds.service;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Component;

import com.joyintech.tams.invest.common.risktrialrds.dto.Task;

@Component("taskQueueRds")
public class TaskQueue {

	private Queue<Task> taskQu;
	
	public TaskQueue() {
		taskQu = new LinkedList<Task>();
	}
	
	public void push(Task task) {
		taskQu.add(task);
	}
	
	public Task pop() {
		return taskQu.poll();
	}
	
	public int size(){
		return taskQu.size();
	}
	
	public Queue<Task> getTaskList(){
		return taskQu;
	}
	
}
