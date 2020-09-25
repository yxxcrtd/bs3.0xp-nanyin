package com.joyintech.tams.common.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.base.dto.Product;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfoKey;

@Repository
public interface ChbPrdBaseinfoMapper {
    int deleteByPrimaryKey(ChbPrdBaseinfoKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbPrdBaseinfo record);

    int insertSelective(ChbPrdBaseinfo record);

    int insertBasicByBasicInfo(ChbPrdBaseinfo record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbPrdBaseinfo selectByPrimaryKey(ChbPrdBaseinfoKey key);

    int updateByPrimaryKeySelective(ChbPrdBaseinfo record);

    int updateByWorkflowIdSelective(ChbPrdBaseinfo record);

    int updateByWorkflowId(ChbPrdBaseinfo record);

    int updateByPrimaryKey(ChbPrdBaseinfo record);

    ChbPrdBaseinfo selectByProdid(@Param("prodId")String prodId);

    /**
     * 功能:根据查询参数查询批处理产品
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年6月24日
     * @param map
     * @return 
     * List<Product>
     */
    public List<Product> selectProductByName(Map<String, Object> map);

    /**
     * 功能:查询所有的产品代码
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年6月24日
     * @return 
     * List<String>
     */
    public List<String> selectAllProductId();

    /**
     * 功能:根据产品id查询产品
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年6月24日
     * @param prodGroupSeqNos
     * @return 
     * List<Product>
     */
    public List<Product> selectProductByIds(@Param("prodGroupSeqNos")String[] prodGroupSeqNos);
}