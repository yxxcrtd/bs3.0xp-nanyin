package com.joyintech.tams.invest.common.service.dao;

import com.joyintech.framework.base.dto.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductComMapper {
	/**modify by yflv on 20210201 start ----系统瘦身注释
  public List<String> selectAllBooksets(Map<String,Object> map);
  modify by yflv on 20210201 end**/
  
  public List<Product> findProductByIds(@Param("prodGroupSeqNos") String[] prodGroupSeqNos);
  
  /**modify by wujie on 20190328 start
  public List<Product> findProductByName(@Param("notInProductIds") List<String> notInProductIds,@Param("prodName") String prodName);
  **/
  public List<Product> findProductByName(Map<String,Object> map);
  /**modify by wujie on 20190328 end**/
 
  
}