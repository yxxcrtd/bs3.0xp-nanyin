package com.joyintech.service.entrust.etrstandardbond.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.entrust.etrstandardbond.dao.EtrStandardBondMapper;
import com.joyintech.service.entrust.etrstandardbond.dto.EtrStandardBond003Dto;
import com.joyintech.service.entrust.etrstandardbond.dto.EtrStandardBondInDto;
import com.joyintech.service.entrust.etrstandardbond.dto.EtrStandardBondSearchDto;
import com.joyintech.tams.common.base.dao.EtrEntrustPledgeMapper;
import com.joyintech.tams.common.base.dao.EtrSecPriorityDetailMapper;
import com.joyintech.tams.common.base.dao.EtrSecPriorityMapper;
import com.joyintech.tams.common.base.entity.EtrEntrustPledge;
import com.joyintech.tams.common.base.entity.EtrEntrustPledgeKey;
import com.joyintech.tams.common.base.entity.EtrSecPriorityDetail;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;

/**
 * 质押券微服务Service
 */
@Service
public class EtrStandardBondService extends BaseService<EtrStandardBondSearchDto> {

    @Autowired
    private EtrStandardBondMapper etrStandardBondMapper;


    @Autowired
    private EtrEntrustPledgeMapper etrEntrustPledgeMapper;

    @Autowired
    private EtrSecPriorityMapper etrSecPriorityMapper;
    @Autowired
    private EtrSecPriorityDetailMapper etrSecPriorityDetailMapper;


    /**
     * 质押券查询画面，查询标准券信息列表
     *
     * @param page   page
     * @param length length
     * @param param  参数
     * @return Page<TrdStandardBondDto>
     * @throws Exception 异常
     * @see
     * @since 1.0selectForPage
     */
    public Page<EtrStandardBondSearchDto> selectSearchDataList(int page, int length,
                                                               Map<String, Object> pageParam)
            throws Exception {
        this.setParam(pageParam);
        Page<EtrStandardBondSearchDto> pageResult = this.queryForPage(page, length,
                () -> this.etrStandardBondMapper.selectSearchDataList(pageParam));
        return pageResult;
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
     * 质押券入库画面，查询可质押债券列表
     *
     * @param param 参数
     * @return List<TrdStandardBondDto>
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public List<EtrStandardBondInDto> selectAvaPledgeBondList(Map<String, Object> pageParam)
            throws Exception {
        this.setParam(pageParam);
        return this.etrStandardBondMapper.selectAvaPledgeBondList(pageParam);
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
    public List<EtrStandardBondInDto> selectPledgeBondList(Map<String, Object> pageParam)
        throws Exception {
        this.setParam(pageParam);
        return this.etrStandardBondMapper.selectPledgeBondList(pageParam);
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
    public Long inSave(EtrStandardBondInDto dto) throws Exception {
        Long saveCount = 0L;
        if (!StringUtil.isEmptyList(dto.getPledgeBondList())) {
            for (EtrStandardBondInDto temp : dto.getPledgeBondList()) {
                EtrEntrustPledge etrEntrustPledge = new EtrEntrustPledge();
                etrEntrustPledge.setPortfolioId(temp.getPortfolioId());
                etrEntrustPledge.setFinprodId(temp.getFinProdId());
                etrEntrustPledge.setBranch(temp.getBranch());
                etrEntrustPledge.setSecAcctId(temp.getSecAcctId());
                etrEntrustPledge.setScale(temp.getScale());
                
                EtrEntrustPledgeKey etrEntrustPledgeKey = new EtrEntrustPledge();
                etrEntrustPledgeKey.setPortfolioId(temp.getPortfolioId());
                etrEntrustPledgeKey.setFinprodId(temp.getFinProdId());
                etrEntrustPledgeKey.setBranch(temp.getBranch());
                etrEntrustPledgeKey.setSecAcctId(temp.getSecAcctId());
                
                // 若存在数据，则删除后，在插入。
                EtrEntrustPledge count = etrEntrustPledgeMapper.selectByPrimaryKey(etrEntrustPledgeKey) ;
                if(count != null){
                    etrEntrustPledgeMapper.deleteByPrimaryKey(etrEntrustPledgeKey) ;
                }
                saveCount += etrEntrustPledgeMapper.insert(etrEntrustPledge);
            }
        }
        return saveCount;
    }

    /**
     * 质押券出库，删除即可
     *
     * @param param 参数
     * @return saveCount
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Long outSave(EtrStandardBondSearchDto dto) throws Exception {
        Long deleteCount = 0L;
        if (!StringUtil.isEmptyList(dto.getSelectedItem())) {
            for (EtrStandardBondSearchDto temp : dto.getSelectedItem()) {
                EtrEntrustPledgeKey etrEntrustPledgeKey = new EtrEntrustPledge();
                etrEntrustPledgeKey.setPortfolioId(temp.getPortfolioId());
                etrEntrustPledgeKey.setFinprodId(temp.getFinProdId());
                etrEntrustPledgeKey.setBranch(temp.getBranch());
                etrEntrustPledgeKey.setSecAcctId(temp.getSecAcctId());
                
                deleteCount += etrEntrustPledgeMapper.deleteByPrimaryKey(etrEntrustPledgeKey) ;
            }
        }
        return deleteCount;
    }
    
    /**
     * 优先级设置明细查询，填充画面列表
     *
     */
    public EtrStandardBond003Dto selectEtrSecPriDetailList() throws Exception {
        EtrStandardBond003Dto dto = new EtrStandardBond003Dto();
        dto = this.etrStandardBondMapper.selectEtrSecPriority();
        // 查询明细
        if(StringUtil.isNotEmpty(dto)){
            List<EtrSecPriorityDetail> detailList = etrStandardBondMapper.selectEtrSecPriDetailList(dto.getConfigId());
            dto.setDetailList(detailList);
        }
        return dto ;
    }
    
    /**
     * 优先级设置入库，先删后插
     *
     */
    public Long etrSecPrioritySave(EtrStandardBond003Dto dto) throws Exception {
        Long deleteCount = 0L;
        if (StringUtil.isNotEmpty(dto)) {
            //删除优先级及明细数据，全删全插
            etrStandardBondMapper.deleteEtrSecPriority() ;
            etrStandardBondMapper.deleteEtrSecPriDetail() ;
            // 插入数据
            dto.setConfigId(IDGenerator.generate()) ;
            deleteCount += etrSecPriorityMapper.insert(dto) ;
            // 插入明细数据
            for (EtrSecPriorityDetail detail : dto.getDetailList()) {
                detail.setConfigId(dto.getConfigId());
                etrSecPriorityDetailMapper.insert(detail) ;
            }
        }
        return deleteCount;
    }

    /**
     * 自动导入质押券 查询
     *
     * @param param 参数
     * @return list
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public List<PledgeBondInfoDto> selectAutoPledge(@RequestBody Map<String, Object> param)throws Exception {
        this.setParam(param);
        return etrStandardBondMapper.selectAutoPledge(param);
    }
    
    /**
     * 根据组合代码查询证券托管户：联想框文本框
     *
     * @param param 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getSecManageAcctId(Map<String, Object> param) {
        return this.queryForSuggest(() -> this.etrStandardBondMapper.getSecManageAcctId(param),
            null);
    }
    /**
     * 查询登录用户是否满足指定的角色岗位 
     */
    public Long selectUserRolePosList(Map<String, Object> param){
        return etrStandardBondMapper.selectUserRolePosList(param) ;
    }
}
