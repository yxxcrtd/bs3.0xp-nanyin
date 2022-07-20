/**
 * 
 */

package com.joyintech.tams.disclosure.rptdata.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.dao.JoyinTestBaseMapper;
import com.joyintech.tams.disclosure.rptdata.dto.BonusReportDto;

/**
 * @author Administrator
 *
 */

@RunWith(SpringRunner.class)
public class BonusReportMapperTest {
	
	private Logger logger = LoggerFactory.getLogger(BonusReportMapperTest.class);
	
	private BonusReportMapper mapper;

    @Before
    public void init() {
    	mapper = JoyinTestBaseMapper.getMapper(
    			 "com/joyintech/tams/disclosure/rptdata/dao/sqlmap/BonusReportMapper.xml",
    			 BonusReportMapper.class);
    }
    
	/**
	 * 需要测试的方法
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testSelectBonusreport() throws ParseException {

		Map<String, Object> map = new HashMap<String, Object>();
		Date td = DateUtil.parse("2022-02-10");
		map.put("genDate",td);

		List<BonusReportDto> list = mapper.selectBonusReport(map);
		for ( BonusReportDto dto:list ) {
			System.out.println(dto.getProdId()+","+dto.getAchievementBasisProp());
		}

	}
	
    @After
    public void close() {
        JoyinTestBaseMapper.close();
    }
}
