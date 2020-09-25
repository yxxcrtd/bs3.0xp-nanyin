package com.joyintech.service.product.rddesgin.feasibilityanalysis.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.product.rddesgin.feasibilityanalysis.dao.FinProductFeaAnaMapper;
import com.joyintech.service.product.rddesgin.feasibilityanalysis.dto.FinProductFeaAnaDto;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 产品研发设计-可行性分析
 *
 * @author guyunfei
 * @version 1.0 2019年9月24日
 */
@Service("serviceFinProductFeaAna")
public class FinProductFeaAnaService extends BaseService<FinProductFeaAnaDto> {

    /**
     * 产品可行性分析
     */
    @Autowired
    private FinProductFeaAnaMapper finProductFeaAnaMapper;

    /**
     * 可行性分析查询
     * @param page
     * @param length
     * @param params
     * @return
     */
    public Page<FinProductFeaAnaDto> selectForPage(int page, int length,Map<String,Object> params)
        throws Exception{
        params.put("STATUS_VALID",DictCodeListExt1.有效状态_有效.getValue());
        params.put("REVIEW_RESULT", DictCodeListExt1.初评结果_初评通过.getValue());
        return super.queryForPageForSelectReturn(page,length,()->finProductFeaAnaMapper.selectForPage(params));
    }

    /**
     * 根据产品需求编号查询产品需求数据
     * @param id
     * @return
     */
    public FinProductFeaAnaDto queryByRequirementId(String id){
        return finProductFeaAnaMapper.queryById(id);
    }

    /**
     * 维护分析结论、可行需求分类
     * @param dto
     * @return
     */
    public Integer update(FinProductFeaAnaDto dto){
        return finProductFeaAnaMapper.update(dto);
    }

    /**
     * 删除产品需求数据
     * @param requirementId
     * @return
     */
    public Integer delete(String requirementId){
        Integer result = finProductFeaAnaMapper.deleteAnalyse(requirementId);
        return result;
    }
}
