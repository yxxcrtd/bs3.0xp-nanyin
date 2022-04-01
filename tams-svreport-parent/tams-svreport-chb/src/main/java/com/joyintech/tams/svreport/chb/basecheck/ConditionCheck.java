package com.joyintech.tams.svreport.chb.basecheck;

import com.joyintech.tams.svreport.chb.basecheck.dao.ColumnSelector;
import com.joyintech.tams.svreport.chb.basecheck.dto.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wubingbing
 * @date 2022-03-31 14:52
 */
@Component
public class ConditionCheck {
    @Autowired
    private ColumnSelector columnSelector;

    public List<Column> getColumns(String schema,String table){
        return columnSelector.select(schema,table);
    }

}
