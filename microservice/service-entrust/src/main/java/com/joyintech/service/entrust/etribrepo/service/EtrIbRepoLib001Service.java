package com.joyintech.service.entrust.etribrepo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.entrust.etribrepo.dao.EtrIbRepoLib001Mapper;
import com.joyintech.service.entrust.etribrepo.dto.EtrIbRepoLib001Dto;
import com.joyintech.service.entrust.etrstandardbond.dto.EtrStandardBondInDto;
import com.joyintech.tams.common.base.dao.EtrEntrustPledgeMapper;
import com.joyintech.tams.common.base.entity.EtrEntrustPledge;
import com.joyintech.tams.common.base.entity.EtrEntrustPledgeKey;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

@Service("backEtrIbRepoLib001Service")
public class EtrIbRepoLib001Service {

	@Autowired
	private EtrIbRepoLib001Mapper etrIbRepoLib001Mapper;

    @Autowired
    private EtrEntrustPledgeMapper etrEntrustPledgeMapper;
    /**
     * 质押券入库画面，查询可质押债券列表
     *
     * @param param 参数
     * @return List<TrdStandardBondDto>
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public List<EtrIbRepoLib001Dto> selectAvaPledgeBondList(Map<String, Object> pageParam)
            throws Exception {
        this.setParam(pageParam);
        return etrIbRepoLib001Mapper.selectAvaPledgeBondList(pageParam);
    }
    /**
     * 质押券入库画面，查询已入库债券列表
     *
     * @param param 参数
     * @return List<TrdStandardBondDto>
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public List<EtrIbRepoLib001Dto> selectPledgeBondList(Map<String, Object> pageParam)
        throws Exception {
        this.setParam(pageParam);
        return etrIbRepoLib001Mapper.selectPledgeBondList(pageParam);
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

        pageParam.put("POSITION_TYPE_01", DictCodeList.持仓类型_可用.getValue());
        pageParam.put("POSITION_TYPE_03", DictCodeList.持仓类型_实际.getValue());
        pageParam.put("POSITION_TYPE_04", DictCodeList.持仓类型_质押.getValue());
        pageParam.put("POSITION_TYPE_05", DictCodeList.持仓类型_融入.getValue());
        pageParam.put("POSITION_TYPE_06", DictCodeList.持仓类型_融出.getValue());
        pageParam.put("POSITION_TYPE_08", DictCodeListExt1.持仓类型_预占用.getValue());
        pageParam.put("POSITION_TYPE_11", DictCodeListExt1.持仓类型_卖空.getValue());
        pageParam.put("POSITION_TYPE_12", DictCodeListExt1.持仓类型_卖空可用.getValue());
        
        pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());

        pageParam.put("TRADEPLACE01", DictCodeListExt1.交易场所_银行间.getValue());
        pageParam.put("TRADEPLACE03", DictCodeListExt1.交易场所_上海证券交易所.getValue());
        pageParam.put("TRADEPLACE04", DictCodeListExt1.交易场所_深圳证券交易所.getValue());
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
    public Long inSave(EtrIbRepoLib001Dto dto) throws Exception {
        Long saveCount = 0L;
        if (!StringUtil.isEmptyList(dto.getPledgeBondList())) {
            for (EtrIbRepoLib001Dto temp : dto.getPledgeBondList()) {
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
                EtrEntrustPledge count = etrIbRepoLib001Mapper.selectEtrEntrustPledge(etrEntrustPledge) ;
                if(count != null){
                	etrIbRepoLib001Mapper.deleteEtrEntrustPledge(etrEntrustPledge) ;
                }
                saveCount += etrEntrustPledgeMapper.insert(etrEntrustPledge);
            }
        }
        return saveCount;
    }
}
