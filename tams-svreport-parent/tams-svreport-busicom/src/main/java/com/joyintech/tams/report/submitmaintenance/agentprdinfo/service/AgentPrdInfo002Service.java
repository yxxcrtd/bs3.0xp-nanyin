package com.joyintech.tams.report.submitmaintenance.agentprdinfo.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.report.submitmaintenance.agentprdinfo.dao.ChbAgentPrdInfoMapper;
import com.joyintech.tams.report.submitmaintenance.agentprdinfo.dto.AgentPrdInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AgentPrdInfo002Service extends BaseService{

    @Autowired
    private ChbAgentPrdInfoMapper chbAgentPrdInfoMapper;

    /**
     * 双击查看详细信息查询 修改页面信息查询 info
     *
     */
    public AgentPrdInfoDto selectDetail(Map<String,Object> param) {
        return chbAgentPrdInfoMapper.selectDetail(param);
    }

    /**
     * 新建保存
     * @param
     */
    public void saveAndSubmit(AgentPrdInfoDto dto) {
        //check表中是否存在相同的逻辑删除数据 如果存在就先删除再插入
        if(chbAgentPrdInfoMapper.checkAgentInstitutionCodeD(dto.getAgentInstitutionCode(),dto.getProdRegistCode()) != 0){
            chbAgentPrdInfoMapper.delete(dto.getAgentInstitutionCode(),dto.getProdRegistCode());
        }
        //获取产品代码及产品名称
        String prodRegistCode = dto.getProdRegistCode();
        AgentPrdInfoDto agentPrdInfo = chbAgentPrdInfoMapper.getProdIdAndName(prodRegistCode);
        //赋值产品代码等信息
        dto.setProdId(agentPrdInfo.getProdId());
        dto.setProdName(agentPrdInfo.getProdName());
        dto.setStatus("E");
        chbAgentPrdInfoMapper.insert(dto);
    }

    /**
     * 查询发行机构代码
     * @param orgCode
     * @return
     */
    public String selectIssuePartyId(String orgCode){
        return  chbAgentPrdInfoMapper.selectIssuePartyId(orgCode);
    }

    /**
     * 代理销售机构代码相同时，产品登记编码不可重复
     * @param agentInstitutionCode
     * @param prodRegistCode
     * @return
     */
    public int checkProdRegistCode(String agentInstitutionCode,String prodRegistCode){
        return  chbAgentPrdInfoMapper.checkProdRegistCode(agentInstitutionCode,prodRegistCode);
    }

    /**
     * 代理销售机构代码已被删除请重新选择
     * @param agentInstitutionCode
     * @return
     */
    public int checkAgentInstitutionCode(String agentInstitutionCode){
        return  chbAgentPrdInfoMapper.checkAgentInstitutionCode(agentInstitutionCode);
    }
}