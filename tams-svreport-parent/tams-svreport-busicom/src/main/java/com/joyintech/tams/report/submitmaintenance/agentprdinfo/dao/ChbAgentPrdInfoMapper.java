package com.joyintech.tams.report.submitmaintenance.agentprdinfo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.submitmaintenance.agentprdinfo.dto.AgentPrdInfoDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChbAgentPrdInfoMapper {

    /**
     * 分页查询
     */
    List<AgentPrdInfoDto> selectForPage(Map<String, Object> param);

    /**
     * 双击查看详细信息查询，修改信息查询 <功能详细描述>info
     *
     */
    AgentPrdInfoDto selectDetail(Map<String, Object> param);

    int insert(AgentPrdInfoDto record);

    int batchInsert(@Param("list") List<AgentPrdInfoDto> list);

    AgentPrdInfoDto selectByPrimaryKey(String agentInstitutionCode);

    /**
     * 根据主键逻辑删除info
     * @param
     * @return
     */
    int deleteInfo(@Param("agentInstitutionCode") String agentInstitutionCode,@Param("prodRegistCode") String prodRegistCode);

    /**
     * 根据主键删除info
     * @param
     * @return
     */
    int delete(@Param("agentInstitutionCode") String agentInstitutionCode,@Param("prodRegistCode") String prodRegistCode);

//    check表中是否存在相同的逻辑删除数据
    int checkAgentInstitutionCodeD(@Param("agentInstitutionCode") String agentInstitutionCode,@Param("prodRegistCode") String prodRegistCode);

    //    check表中是否存在相同的逻辑删除数据导入
    List<AgentPrdInfoDto> checkAgentInstitutionCodeDImport();

    //    check表中是否存在相同的有效数据导入
    List<AgentPrdInfoDto> checkProdRegistCodeImport();

    /**
     * check代理销售机构代码相同时，产品登记编码不可重复
     * @param
     * @return
     */
    int checkProdRegistCode(@Param("agentInstitutionCode") String agentInstitutionCode,@Param("prodRegistCode") String prodRegistCode);

    /**
     * check代理销售机构代码相同时，产品登记编码不可重复
     * @param
     * @return
     */
    int checkAgentInstitutionCode(@Param("agentInstitutionCode") String agentInstitutionCode);

    /**
     * 根据机构代码查询中债登记代码
     * @param orgCode
     * @return
     */
    String selectIssuePartyId(@Param("orgCode")String orgCode);

    /**
     * 查询已维护的代理销售机构代码
     * @param
     * @return
     */
    List<AgentPrdInfoDto> selectAgentInstitutionCodeList();

    /**
     * 查询已维护的产品登记编码
     * @param
     * @return
     */
    List<AgentPrdInfoDto> selectProdRegistCodeList();

    /**
     * 代理销售机构代码下拉框
     */
    List<SuggestDto> getAgentInstitutionCode(Map<String, Object> map);

    /**
     * 产品代码下拉框
     */
    List<SuggestDto> getProdId(Map<String, Object> map);

    /**
     * 产品登记编码下拉框
     */
    List<SuggestDto> getProdRegistCode(Map<String, Object> map);

    //产品代码及名称
    List<AgentPrdInfoDto> selectPrdCodeList();

    //新建获取产品代码及名称
    AgentPrdInfoDto getProdIdAndName (String prodRegistCode);

    //代理销售机构代码下拉框（新建）
    List<SelectOptionDto> selectAgentInstitutionCode(Map<String, Object> pageParam);

    //产品登记编码下拉框(新建)
    List<SelectOptionDto> selectProdRegistCode(Map<String, Object> pageParam);
}