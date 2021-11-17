package com.joyintech.tams.invest.basicdata.multiportfoliomaintenance.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.basicdata.multiportfoliomaintenance.dto.MutiptlPortfolioDetailDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 对账参数设置
 */
@Service
public class MultiPortfolioMaintenance002Service extends CommonExportService<MutiptlPortfolioDetailDto>
        implements BaseProcessService<MutiptlPortfolioDetailDto> {




    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<TreeNode> getMutiptlPortfolioAcctnoTree(Map<String,Object> parmMap) {
        List<TreeNode> treeProList = restTemplate.postForList(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MultiPortfolioMaintenance002/getMutiptlPortfolioAcctnoTree",
                parmMap,TreeNode.class);
        return treeProList;
    }

    /**
     * 继承框架doComplete，完成增删改
     * @param entity 业务实体
     * @param type 操作类型
     */
    @Override
    public void doComplete(MutiptlPortfolioDetailDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        String action = StringUtil.EMPTY;
        // 删除
        if (entity.getOperateType().equals("DELETE")) {
            action = "delete";
            // 插入
        } else if (entity.getOperateType().equals("NEW")) {
            action = "insert";
        } else {
            // 更新
            action = "update";
        }
        restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME + "/StlSecReconPara/" + action, entity, Integer.class);
    }
}
