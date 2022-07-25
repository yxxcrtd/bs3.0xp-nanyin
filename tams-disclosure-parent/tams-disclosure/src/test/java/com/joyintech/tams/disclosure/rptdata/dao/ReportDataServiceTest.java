/**
 * 
 */
package com.joyintech.tams.disclosure.rptdata.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.joyintech.tams.disclosure.DisclosureApplication;
import com.joyintech.tams.disclosure.rptdata.service.DurationReportDataService;

/**
 * @author Administrator
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DisclosureApplication	.class)
public class ReportDataServiceTest {

	    @Autowired
	    private DurationReportDataService dataService;

	    /**
	     * 测试获取季度报表数据
	     */
	    @Test
	    public void testQueryDatas() {
	    	Map<String,Object> paras = new HashMap<String,Object>();
	    	paras.put("year", 2022);
	    	paras.put("quarter",2);
	    	paras.put("prodId","LEO-001");
	    	List<Map<String,Object>> listDatas = dataService.queryDatas(paras);
	    	System.out.println("ok");
	    }
}
