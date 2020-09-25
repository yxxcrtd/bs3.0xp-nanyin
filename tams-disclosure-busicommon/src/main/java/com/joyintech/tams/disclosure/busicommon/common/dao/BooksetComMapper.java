package com.joyintech.tams.disclosure.busicommon.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.base.dto.Product;

@Repository("disclosureBooksetComMapper")
public interface BooksetComMapper {
  public List<String> selectAllBooksets();
  
  public List<Product> findProductByIds(@Param("prodGroupSeqNos") String[] prodGroupSeqNos);
  
  public List<Product> findProductByName(@Param("notInProductIds") List<String> notInProductIds,@Param("prodName") String prodName);
}