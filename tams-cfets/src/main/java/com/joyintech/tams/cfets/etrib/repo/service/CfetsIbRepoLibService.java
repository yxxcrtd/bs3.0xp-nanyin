package com.joyintech.tams.cfets.etrib.repo.service;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.cfets.etrib.repo.dto.CfetsEtrIbRepoLibAvaPledgeBondResponse;
import com.joyintech.tams.cfets.etrib.repo.dto.CfetsEtrRepoZLCXPositionRequest;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.cfets.etrib.repo.dao.CfetsEtrIbRepoLib001Mapper;
import com.joyintech.tams.cfets.etrib.repo.dto.CfetsEtrIbRepoLib001Dto;
import com.joyintech.tams.cfets.etrib.repo.dto.CfetsEtrIbRepoLibDto;
import com.joyintech.tams.common.base.dao.EtrEntrustPledgeMapper;
import com.joyintech.tams.common.base.entity.EtrEntrustPledge;
import com.joyintech.tams.common.base.entity.EtrEntrustPledgeKey;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
public class CfetsIbRepoLibService extends BaseService<CfetsEtrIbRepoLibDto> implements BaseProcessService<CfetsEtrIbRepoLibDto>{

	@Autowired
	private CfetsEtrIbRepoLib001Mapper cfetsEtrIbRepoLib001Mapper;

    @Autowired
    private EtrEntrustPledgeMapper etrEntrustPledgeMapper;

    /**
     * 平台日期
     */
    @Autowired(required = false)
    private IPlatformDate iPlatformDate;
    /**
     * 质押券入库画面，查询可质押债券列表
     *
     * @return List<TrdStandardBondDto>
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public List<CfetsEtrIbRepoLibAvaPledgeBondResponse> selectAvaPledgeBondList(CfetsEtrRepoZLCXPositionRequest cfetsEtrRepoZLCXPositionRequest) {
        Map<String,Object> pageParam = BeanUtil.object2Map(cfetsEtrRepoZLCXPositionRequest);
        this.setParam(pageParam);
        return cfetsEtrIbRepoLib001Mapper.selectAvaPledgeBondList(pageParam);
    }
    /**
     * 质押券入库画面，查询已入库债券列表
     *
     * @return List<TrdStandardBondDto>
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public List<CfetsEtrIbRepoLibAvaPledgeBondResponse> selectPledgeBondList( CfetsEtrRepoZLCXPositionRequest cfetsEtrRepoZLCXPositionRequest){
        Map<String,Object> pageParam = BeanUtil.object2Map(cfetsEtrRepoZLCXPositionRequest);
        this.setParam(pageParam);
        return cfetsEtrIbRepoLib001Mapper.selectPledgeBondList(pageParam);
    }
    
    /**
     * 检索参数设置：
     * 标准券查询画面，查询标准券信息列表
     * 质押券入库画面，查询可质押债券列表
     * 质押券出库画面，查询待出库信息
     *
     * @param pageParam
     */
    private void setParam(Map<String, Object> pageParam) {
        pageParam.put("A_001", DictCodeListExt3.资产类型_资产账户持仓.getValue());
        pageParam.put("S_002", DictCodeListExt3.资产类型_可质押持仓.getValue());
        pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        pageParam.put("TRADEPLACE01", DictCodeListExt1.交易场所_银行间.getValue());
        pageParam.put("TRADEPLACE03", DictCodeListExt1.交易场所_上海证券交易所.getValue());
        pageParam.put("TRADEPLACE04", DictCodeListExt1.交易场所_深圳证券交易所.getValue());
        pageParam.put("userId", SecurityUtil.getLoginUser().getUserName());
        pageParam.put("plateDate",iPlatformDate.getSysDateStr());
    }
    
    /**
     * 质押券入库画面，保存处理
     *
     * @param dto 参数
     * @return saveCount
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Long inSave(CfetsEtrIbRepoLib001Dto dto) {
        Long saveCount = 0L;
        if (!StringUtil.isEmptyList(dto.getPledgeBondList())) {
            for (CfetsEtrIbRepoLib001Dto temp : dto.getPledgeBondList()) {
                EtrEntrustPledge etrEntrustPledge = new EtrEntrustPledge();
                etrEntrustPledge.setPortfolioId(temp.getPortfolioId());
                etrEntrustPledge.setFinprodId(temp.getFinProdId());
                etrEntrustPledge.setBranch(temp.getBranch());
                etrEntrustPledge.setSecAcctId(temp.getSecAcctId());
                etrEntrustPledge.setScale(temp.getScale());
                etrEntrustPledge.setPledgeStockId(dto.getPledgeStockId());
                
                EtrEntrustPledgeKey etrEntrustPledgeKey = new EtrEntrustPledge();
                etrEntrustPledgeKey.setPortfolioId(temp.getPortfolioId());
                etrEntrustPledgeKey.setFinprodId(temp.getFinProdId());
                etrEntrustPledgeKey.setBranch(temp.getBranch());
                etrEntrustPledgeKey.setSecAcctId(temp.getSecAcctId());
                
                // 若存在数据，则删除后，在插入。
                EtrEntrustPledge count = cfetsEtrIbRepoLib001Mapper.selectEtrEntrustPledge(etrEntrustPledge) ;
                if(count != null){
                	cfetsEtrIbRepoLib001Mapper.deleteEtrEntrustPledge(etrEntrustPledge) ;
                }
                saveCount += etrEntrustPledgeMapper.insert(etrEntrustPledge);
            }
        }
        return saveCount;
    }
	@Override
	public void doComplete(CfetsEtrIbRepoLibDto entity, OperationType type) {
		 if (!StringUtil.isEmptyList(entity.getPledgeBondList())) {
			 inSave(entity);
		 }
	}
}
