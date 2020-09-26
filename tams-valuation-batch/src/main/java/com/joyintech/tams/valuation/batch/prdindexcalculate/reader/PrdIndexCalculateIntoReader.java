package com.joyintech.tams.valuation.batch.prdindexcalculate.reader;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.prdindexcalculate.dao.PrdIndexCalculateMapper;
import com.joyintech.tams.valuation.batch.prdindexcalculate.dto.PrdIndexCalculateReaderIntoResult;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 信息披露-产品指标计算(数据插入)-reader
 * 
 * @author yangjian
 * @version 1.0 2018年12月21日
 * @see PrdIndexCalculateIntoReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PrdIndexCalculateIntoReader extends BaseReader<PrdIndexCalculateReaderIntoResult> {

	@Autowired
	private PrdIndexCalculateMapper prdIndexCalculateMapper;

	@Override
	public List<PrdIndexCalculateReaderIntoResult> createList() {
		// 获取task传递过来的参数
		Map<String, Object> thisParams = new HashMap<>();
		thisParams.putAll(this.getParams());
		
		@SuppressWarnings("unchecked")
		List<String> booksetIdList = (List<String>)thisParams.get(GlobalConstant.BOOKSETS);//账套信息
        Date bookSetDate = (Date) thisParams.get(BookConstant.BOOKSET_DATE);//所选账套日
          
		// 查询reader返回数据 - 指定帐套的核算主体和TA产品
		Map<String, Object> sqlmap = new HashMap<String, Object>();
		sqlmap.put("bookSetList", booksetIdList);// 所选账套list
		sqlmap.put("bookSetDate", bookSetDate);
		List<PrdIndexCalculateReaderIntoResult> resultList = prdIndexCalculateMapper.getIntoReaderResult(sqlmap);

		// 循环塞入[入账日] = 参数.账套日
		if(resultList!=null && !resultList.isEmpty()){
			for(PrdIndexCalculateReaderIntoResult dto : resultList){
				dto.setBookDate(bookSetDate);
			}
		}
		
		return resultList;
	}

}
