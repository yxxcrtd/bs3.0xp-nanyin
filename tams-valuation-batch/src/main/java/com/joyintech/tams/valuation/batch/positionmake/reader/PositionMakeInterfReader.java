package com.joyintech.tams.valuation.batch.positionmake.reader;

import com.csii.dc.client.util.StringUtil;
import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.BookConstant;

import org.apache.commons.collections.MapUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 头寸生成接口 - reader
 * 
 * @author WUJIE
 */
@Service
@Scope("prototype")
public class PositionMakeInterfReader extends BaseReader<NeedOperateEntity> {
	
	@Override
    public List<NeedOperateEntity> createList() {
        // 获取task传递参数
        Map<String, Object> params = this.getParams();
        if (MapUtils.isEmpty(params)) {
            return new ArrayList<>();
        }
        List<NeedOperateEntity> result = new ArrayList<NeedOperateEntity>();
        String isSubBookset = (String)params.get(BookConstant.IS_SUB_BOOKSET);
        if (!DictCodeListExt1.是或否_否.getValue().equals(isSubBookset)) {
        	return result;
        }
        String  是否自动推数头寸数据 = CacheUtil.getSysParam("IS_CALLINTERF_POSITION");
        if(StringUtil.isEmpty(是否自动推数头寸数据) 
        		|| DictCodeList.是否_否.getValue().equals(是否自动推数头寸数据)){
        	return result;
        }
        result.add(new NeedOperateEntity());
        return result;
    }
}
