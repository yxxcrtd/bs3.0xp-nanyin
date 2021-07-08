package com.joyintech.tams.invest.entrust.etribrepo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtrIbRepo001Dto;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtrIbRepoLibDto;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
public class EtrIbRepoLibService extends BaseService<EtrIbRepoLibDto> implements BaseProcessService<EtrIbRepoLibDto>{



    @Autowired
    private IPlatformDate platformDateImpl;

    /**
     * 工作流doInsert
     *
     * @param entity        entity
     * @param operationType operationType
     */
    @Override
    public void doInsert(EtrIbRepoLibDto entity, OperationType operationType) {
        // do nothing
    }

    /**
     * 工作流中doUpdate
     *
     * @param input input
     */
    @Override
    public void doUpdate(EtrIbRepoLibDto input) {
        // do nothing
    }

    /**
     * 工作流中doDelete
     *
     * @param workflowId workflowId
     */
    @Override
    public void doDelete(String workflowId) {
        // do nothing
    }

    /**
     * 工作流审核完成doComplete
     *
     * @param input         entity
     * @param operationType operationType
     */
    @Override
    public void doComplete(EtrIbRepoLibDto input, OperationType operationType) {
        Long insertCount = 0L;
        if (!StringUtil.isEmptyList(input.getPledgeBondList())) {
//            input.setBranch(Long.parseLong((String) CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
            insertCount += restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbRepoLib001/inSave",
                    input, Long.class);
        }
    }

    /**
     * 检索可质押债券列表
     *
     * @param params 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public List<EtrIbRepoLibDto> selectAvaPledgeBondList(Map<String, Object> params) {
        params.put("BRANCH_0", CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        List<EtrIbRepoLibDto> list = new ArrayList<EtrIbRepoLibDto>() ;
        
        if (StringUtil.isNotEmpty(params.get("tradePlace"))) {
            params.put("tradePlace", StringUtil.splitToList(",", (String)params.get("tradePlace")));
        }
        
        params.put("plateDate", platformDateImpl.getSysDateStr());
        params.put("userId", SecurityUtil.getLoginUser().getUserName());
        Page<EtrIbRepo001Dto> page = restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbRepo001/findZlglPositionPage",
                params, EtrIbRepo001Dto.class);
        // 多选下拉框值处理
        if (StringUtil.isNotEmpty(params.get("portfolioId"))) {
            params.put("portfolioId", StringUtil.splitToList(",", (String)params.get("portfolioId")));
        }

        if (StringUtil.isNotEmpty(params.get("secAcctId"))) {
            params.put("secAcctId", StringUtil.splitToList(",", (String)params.get("secAcctId")));
        }
        list = restTemplate.postForList(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbRepoLib001/selectAvaPledgeBondList",
                params, EtrIbRepoLibDto.class);
        


        if(list.size() >0 && list.size() > 0){
            Iterator<EtrIbRepoLibDto> iterator = list.iterator();
            while(iterator.hasNext()){
            	EtrIbRepoLibDto avaDto=  iterator.next();
                for (EtrIbRepo001Dto pledDto : page.getRoot()) {
                    if(StringUtil.equals(avaDto.getPortfolioId(), pledDto.getPortfolioId())&&
                        StringUtil.equals(avaDto.getSecAcctId(), pledDto.getSecAcctId())&&
                        StringUtil.equals(avaDto.getFinProdId(), pledDto.getFinprodId())){
                    	//iterator.next();
                        iterator.remove() ;
                    }
                }
            }
        }

        return list ;
    }
    /**
     * 检索已入库债券信息
     *
     * @param params 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public List<EtrIbRepoLibDto> selectPledgeBondList(Map<String, Object> params) {
        params.put("BRANCH_0", CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        
        // 多选下拉框值处理
        if (StringUtil.isNotEmpty(params.get("portfolioId"))) {
            params.put("portfolioId", StringUtil.splitToList(",", (String)params.get("portfolioId")));
        }
        if (StringUtil.isNotEmpty(params.get("tradePlace"))) {
            params.put("tradePlace", StringUtil.splitToList(",", (String)params.get("tradePlace")));
        }
        if (StringUtil.isNotEmpty(params.get("secAcctId"))) {
            params.put("secAcctId", StringUtil.splitToList(",", (String)params.get("secAcctId")));
        }
        params.put("plateDate", platformDateImpl.getSysDateStr());
        params.put("userId", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForList(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbRepoLib001/selectPledgeBondList",
            params, EtrIbRepoLibDto.class);
    }

    // add by zhangrui1 start
    /**
     * {@inheritDoc}
     */
    @Override
    public void doBack(EtrIbRepoLibDto entity) {
        // nothing to do just for aspect
    }
    // add by zhangrui1 end

}
