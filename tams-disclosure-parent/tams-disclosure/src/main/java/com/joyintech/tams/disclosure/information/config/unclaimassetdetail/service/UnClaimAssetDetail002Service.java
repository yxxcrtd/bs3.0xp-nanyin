package com.joyintech.tams.disclosure.information.config.unclaimassetdetail.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dao.UnClaimAssetDetailMapper;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dto.UnClaimAssetDetailDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UnClaimAssetDetail002Service extends CommonExportService<UnClaimAssetDetailDto>
    implements BaseProcessService<UnClaimAssetDetailDto> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UnClaimAssetDetailMapper unClaimAssetDetailMapper;

    /**
     * 双击查看,修改
     *
     * @param nostdAssetId
     * @return
     */
    public UnClaimAssetDetailDto selectDetail(String nostdAssetId) {
        Map<String,Object> param=new HashMap<>();
        param.put("nostdAssetId",nostdAssetId);
        return unClaimAssetDetailMapper.selectDetail(param);
    }


    public List<UnClaimAssetDetailDto> selectDetailList(String nostdAssetId) {
        Map<String,Object> param=new HashMap<>();
        param.put("nostdAssetId",nostdAssetId);
        return unClaimAssetDetailMapper.selectDetailList(param);
    }
    @Override
    public void doComplete(UnClaimAssetDetailDto dto, OperationType type) {

        if (OperationType.DELETE.equals(type)) {
            //删除
            unClaimAssetDetailMapper.deleteByKey(dto.getNostdAssetId());
        }
        else if (OperationType.NEW.equals(type)) {//新建
            //数据状态

//            dto.setProjectNo(dto.getUncliamList().get(0).getProjectNo());
//            dto.setFinancier(dto.getUncliamList().get(0).getFinancier());
//            dto.setProjectName(dto.getUncliamList().get(0).getProjectName());
//            dto.setRemFinancingPeriod(dto.getUncliamList().get(0).getRemFinancingPeriod());
//            dto.setIncomeDistribute(dto.getUncliamList().get(0).getIncomeDistribute());
//            dto.setTrdConstruct(dto.getUncliamList().get(0).getTrdConstruct());
//            dto.setRiskInfo(dto.getUncliamList().get(0).getRiskInfo());
            dto.setDataStatus(DictCodeListExt3.信披_数据状态_有效.getValue());
            //数据来源
            dto.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
            unClaimAssetDetailMapper.insert(dto);
        }
        else {
            //更新
            unClaimAssetDetailMapper.updateByKey(dto);
        }
    }
    /**
     * 查询产品所有的非标资产信息
     * @param param
     * @return
     */
    public List<UnClaimAssetDetailDto> checkNostdExistByProdId(Map<String, Object> param) {
        param.put("status",DictCodeListExt3.信披_数据状态_有效.getValue());
        return unClaimAssetDetailMapper.checkNostdExistByProdId(param);
    }
}
