package com.joyintech.service.risk.valtblimport.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.valtblimport.dao.RskValTableQueryMapper;
import com.joyintech.service.risk.valtblimport.dto.RskValtblAssetSubDto;
import com.joyintech.service.risk.valtblimport.dto.RskValtblAssetSumDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("backRskValTableQueryService")
public class RskValTableQueryService extends BaseService<RskValtblAssetSumDto> {

    @Autowired
    private RskValTableQueryMapper rskValTableQueryMapper;


    public Page<RskValtblAssetSumDto> selectForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        String valDate = String.valueOf(pageParam.get("valDate"));
        //前一日
        pageParam.put("previousDay", DateUtil.addDay(valDate,-1));
        //上个月
        pageParam.put("lastMonth",DateUtil.format(DateUtil.addDate(DateUtil.parse(valDate),0,-1,0)));
        //年初
        pageParam.put("earlyYear",DateUtil.getYear(valDate)+"-01"+"-01");
        if(StringUtil.isNotEmpty(pageParam.get("ptlId"))){
            pageParam.put("ptlIdList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("ptlId")));
        }
        return super.queryForPage(page, length, () -> rskValTableQueryMapper.queryForPage(pageParam));
    }

    public List<RskValtblAssetSubDto> selectRskValTableDetaile(Map<String,Object> param){
        return rskValTableQueryMapper.selectRskValTableDetaile(param);
    }
}
