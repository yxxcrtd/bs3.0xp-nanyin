/*
 * 文件名：ExprTree.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：加减乘除括号运算封装
 * 修改人：裴家俊
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.common.risktrial.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExprTree {

	private static final Pattern patternOp = Pattern.compile("[*/+-]");
	private static final Pattern patternOpLvl2 = Pattern.compile("[+-]");
	private static final Pattern patternOpLvl1 = Pattern.compile("[*/]");
	
	private static final String paramPref = "${";
	private static final String paramSuff = "}";
	
	private ExprNode root;
	
	private class QuItem {
		private String reverseExpr;
		private ExprNode node;
		
		public String getReverseExpr() {
			return reverseExpr;
		}
		public void setReverseExpr(String reverseExpr) {
			this.reverseExpr = reverseExpr;
		}
		public ExprNode getNode() {
			return node;
		}
		public void setNode(ExprNode node) {
			this.node = node;
		}
	}
	
	public ExprTree(String expr) {
		root = new ExprNode();
		
		QuItem item = new QuItem();
		item.setReverseExpr(new StringBuffer(expr).reverse().toString().trim());
		item.setNode(root);

		Queue<QuItem> qu = new LinkedList<QuItem>();
		qu.offer(item);
		
		while(qu.size() > 0) {
			item = qu.poll();
			String rExpr = item.getReverseExpr();
			ExprNode node = item.getNode();
			
			Matcher matcher = null;
			
			matcher = patternOpLvl2.matcher(rExpr);
			if(matcher.find()) {
				node.setValue(matcher.group(0));
				node.setLeft(new ExprNode());
				node.setRight(new ExprNode());
				
				QuItem itemRight = new QuItem();
				itemRight.setReverseExpr(rExpr.substring(0, matcher.start()).trim());
				itemRight.setNode(node.getRight());
				qu.offer(itemRight);
				
				QuItem itemLeft = new QuItem();
				itemLeft.setReverseExpr(rExpr.substring(matcher.end()).trim());
				itemLeft.setNode(node.getLeft());
				qu.offer(itemLeft);
				
				continue;
			}
			
			matcher = patternOpLvl1.matcher(rExpr);
			if(matcher.find()) {
				node.setValue(matcher.group(0));
				node.setLeft(new ExprNode());
				node.setRight(new ExprNode());
				
				QuItem itemRight = new QuItem();
				itemRight.setReverseExpr(rExpr.substring(0, matcher.start()).trim());
				itemRight.setNode(node.getRight());
				qu.offer(itemRight);
				
				QuItem itemLeft = new QuItem();
				itemLeft.setReverseExpr(rExpr.substring(matcher.end()).trim());
				itemLeft.setNode(node.getLeft());
				qu.offer(itemLeft);
				
				continue;
			}
			
			String param = new StringBuffer(rExpr).reverse().toString();
			node.setValue(parseParamName(param));
		}
	}
	
	private String parseParamName(String param) {
		String paramName;
		if(param.startsWith(paramPref)) {
			paramName = param.substring(paramPref.length());
			if(paramName.endsWith(paramSuff)) {
				paramName = paramName.substring(0, paramName.length() - paramSuff.length());
			}
		} else {
			paramName = param;
		}
		
		return paramName;
	}
	
	//------------------------------
	//
	//------------------------------
	public Set<String> getParamNameSet() {
		Set<String> paramNames = new HashSet<String>();
		_getParamNameSet(root, paramNames);
		return paramNames;
	}
	
	private void _getParamNameSet(ExprNode node, Set<String> paramNames) {
		boolean isLeaf = true;
		
		ExprNode left = node.getLeft();
		if(left != null) {
			_getParamNameSet(left, paramNames);
			isLeaf = false;
		}
		
		ExprNode right = node.getRight();
		if(right != null) {
			_getParamNameSet(right, paramNames);
			isLeaf = false;
		}
		
		if(isLeaf) {
			String v = node.getValue();
			if(! paramNames.contains(v)) paramNames.add(v);
		}
	}
	
	//------------------------------
	//
	//------------------------------
	public String getExpr() {
		StringBuffer sBuf = new StringBuffer();
		_getExpr(root, sBuf);
		return sBuf.toString();
	}
	
	private void _getExpr(ExprNode node, StringBuffer sBuf) {
		ExprNode left = node.getLeft();
		if(left != null) _getExpr(left, sBuf);
		
		String v = node.getValue();
		sBuf.append(v).append(" ");
		
		ExprNode right = node.getRight();
		if(right != null) _getExpr(right, sBuf);
	}
	
	//------------------------------
	//
	//------------------------------
	public Object calc(Map<String, Object> params) throws Exception {
		String v = root.getValue();
		if(! patternOp.matcher(v).find()) {
			return params.get(v);
		} else {
			Stack<Double> vStack = new Stack<Double>();
			_calc(root, vStack, params);
			return vStack.pop();
		}
	}
	
	private void _calc(ExprNode node, Stack<Double> vStack, Map<String, Object> params) throws Exception {
		ExprNode left = node.getLeft();
		if(left != null) _calc(left, vStack, params);
		
		ExprNode right = node.getRight();
		if(right != null) _calc(right, vStack, params);
		
		String v = node.getValue();
		if(! patternOp.matcher(v).find()) {
			Object paramValue = params.get(v);
			if(paramValue instanceof Double) {
				vStack.push((Double)paramValue);
			} else if(paramValue instanceof Integer) {
				vStack.push(((Integer)paramValue).doubleValue());
			} else if(paramValue instanceof Long) {
				vStack.push(((Long)paramValue).doubleValue());
			} else if(paramValue instanceof BigDecimal) {
				vStack.push(((BigDecimal)paramValue).doubleValue());
			} else {
				throw new Exception("cannot get number from parameters for expression");
			}
		} else {
			Double vRight = vStack.pop();
			Double vLeft = vStack.pop();
			
			Double result = null;
			switch(v) {
				case "+": result = vLeft + vRight; break;
				case "-": result = vLeft - vRight; break;
				case "*": result = vLeft * vRight; break;
				case "/": result = vLeft / vRight; break;
			}
			System.out.println(
					new StringBuffer().append(vLeft)
					.append(v).append(vRight)
					.append("=").append(result));
			vStack.push(result);
		}
	}
	
	//------------------------------
	//just for test
	//------------------------------
	public void travelCalc() {
		Stack<String> vStack = new Stack<String>();
		_travelCalc(root, vStack);
		System.out.println(vStack.pop());
	}
	
	private void _travelCalc(ExprNode node, Stack<String> vStack) {
		ExprNode left = node.getLeft();
		if(left != null) _travelCalc(left, vStack);
		
		ExprNode right = node.getRight();
		if(right != null) _travelCalc(right, vStack);
		
		String v = node.getValue();
		if(! patternOp.matcher(v).find()) {
			vStack.push(v);
		} else {
			String vRight = vStack.pop();
			String vLeft = vStack.pop();
			StringBuffer result = new StringBuffer(vLeft).append(v).append(vRight);
			System.out.println(result);
			vStack.push(result.insert(0, '(').append(')').toString());
		}
	}
}
