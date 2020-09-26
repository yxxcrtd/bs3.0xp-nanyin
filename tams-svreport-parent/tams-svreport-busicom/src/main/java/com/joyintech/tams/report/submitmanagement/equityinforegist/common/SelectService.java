package com.joyintech.tams.report.submitmanagement.equityinforegist.common;

import java.util.List;
import java.util.concurrent.atomic.LongAdder;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.framework.util.StringUtil;

@Service
public class SelectService<T extends ITableRow> extends BaseService<T> {
	public void getCount(ISelect select,List<T> list){
        LongAdder current = new LongAdder();
        long total = 0;
        int pageNum = 1;
        int pageSize = StringUtil.integerValueOf(SvrDataUtil.getSysParam(GlobalConstant.EXPORT_EXCEL_ROW_COUNT), 50000);
        Page<T> page = null;
        do{
            page = selectPageData(pageNum++ , pageSize, select, total == 0);
            list.addAll((List<T>) page);
            //current.
        }
        while(current.longValue() < total);

	}
    private Page<T> selectPageData(int pageNum, int pageSize, ISelect select, boolean count) {
        return PageHelper.startPage(pageNum, pageSize).count(count).doSelectPage(select);
    }
}
