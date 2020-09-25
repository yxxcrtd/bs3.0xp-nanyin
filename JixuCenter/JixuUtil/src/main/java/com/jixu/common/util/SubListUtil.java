package com.jixu.common.util;
import java.util.List;


public class SubListUtil {
	
	//private static final int pageSize=50; //每页显示条数

	public static <T> Pages<T> getPageList(List<T> list,int currentPage,Pages<T> pages){
		
		int totalPages=1;   //总页数
		int totalCount=list.size();  //总条数
		int m=totalCount%pages.getPageMaxRecords();
		
		 //如果没有记录 直接返回
		if(list==null||list.size()==0){
			pages.setTotalPages(totalPages);
			return pages;
		}
		
		if(m>0)
			totalPages=totalCount/pages.getPageMaxRecords()+1;
		else
			totalPages=totalCount/pages.getPageMaxRecords();
		
		
		int fromIndex=0;   //从第几条开始截取
		int toIndex=0;     //截取到第几条记录
		
		if(currentPage<=totalPages&&currentPage!=0){
			fromIndex=(currentPage-1)*pages.getPageMaxRecords();   //起始位    (当前页数-1)*显示条数
			if(currentPage==totalPages){          //如果当前页数等于总页数  则结束位=list.size()
				toIndex=list.size();
			}else{
				toIndex=currentPage*pages.getPageMaxRecords();    //结束位       当前页数*显示条数
			}
		}
		
		list=list.subList(fromIndex, toIndex);   //调用list.sublist进行分页
		pages.setTotalPages(totalPages);
		pages.setResultList(list);
		return pages;
	}
	
}
