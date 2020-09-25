package com.joyintech.tams.invest.common.autoperiod.ptlreceinfo;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.autoperiod.ptlreceinfo.dao.PtlReceInfoMapper;
import com.joyintech.tams.invest.common.autoperiod.ptlreceinfo.dto.PtlReceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * 应收款标的共通
 */
@Service
public class BasePtlReceInfoService extends BaseService {
    @Autowired
    private PtlReceInfoMapper ptlReceInfoMapper;

    /**
     * 获取应收款标的信息
     * @param map 参数[finprodId 金融产品代码,branch 分支序号]
     * @return 查询结果
     */
    public PtlReceInfo genPtlReceInfo(Map<String,Object> map){
        map.put("CASH_CLASS_TYPE_ZJSX", DictCodeList.现金流类型_中间收息.getValue());
        map.put("CASH_CLASS_TYPE_DQSX", DictCodeList.现金流类型_到期收息.getValue());
        map.put("CASH_TYPE_2_FX", DictCodeListExt1.现金流二级类型_罚息.getValue());
        map.put("BRANCH_0", 0L);
        return ptlReceInfoMapper.genPtlReceInfo(map);
    }
}
