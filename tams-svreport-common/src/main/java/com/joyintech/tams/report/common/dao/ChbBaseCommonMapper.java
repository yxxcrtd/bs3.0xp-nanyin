package com.joyintech.tams.report.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.base.dto.Product;

@Repository
public interface ChbBaseCommonMapper {
  public List<String> selectAllBooksets();
  
  public List<Product> findProductByIds(@Param("prodGroupSeqNos") String[] prodGroupSeqNos);
  
  public List<Product> findProductByName(Map<String,Object> map);
 
  
}