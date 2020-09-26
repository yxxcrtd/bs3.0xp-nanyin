package com.joyintech.tams.valuation.batch.unitfycaccountant.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.unitfycaccountant.dao.UnitfycaccountantInfoMapper;
import com.joyintech.tams.common.base.entity.SysUnaccountantInfo;

@Service
@Scope("prototype")
public class UnitfycaccountantInfoReader extends BaseReader<NeedOperateEntity> {

    /**
     * 数据提取mapper
     */
    @Autowired
    private UnitfycaccountantInfoMapper mapper;

    @Override
    public List<NeedOperateEntity> createList() {
        // 如果存在已执行的则不处理
        List<SysUnaccountantInfo> listSysUnitfycaccountantInfo = mapper.findInExecUnitfycaccountantInfo();
        if (listSysUnitfycaccountantInfo.size() == 0) {
            NeedOperateEntity entity = new NeedOperateEntity();
            List<NeedOperateEntity> list = new ArrayList<>();
            list.add(entity);
            return list;
        }
        return  null;
    }
}
