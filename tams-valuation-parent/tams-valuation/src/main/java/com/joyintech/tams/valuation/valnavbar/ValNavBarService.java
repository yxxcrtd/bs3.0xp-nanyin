/*
 * 文件名：ValNavBarService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.valnavbar;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.joyintech.framework.navbar.UserNavBarService;
import com.joyintech.framework.navbar.UserNavbar;

@Component
@Primary
public class ValNavBarService implements UserNavBarService {

	@Override
	public List<UserNavbar> getUserNavbar() {

		List<UserNavbar> list = new ArrayList<>();
//        UserNavbar bar = new UserNavbar();
//        bar.setId("DocumentManagement001");
//        bar.setName("操作手册管理");
//        bar.setUrl("valuation/documentmanagement/DocumentManagement001/init");
//        bar.setAction("layui.layer.open({type : 2,title:'操作手册管理', shade : 0.8, area:['90%', '80%'], content:'valuation/documentmanagement/DocumentManagement001/init'})");
//        list.add(bar);
        
		return list;
	}

}
