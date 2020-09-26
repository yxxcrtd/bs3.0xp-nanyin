package com.joyintech.tams.valuation.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.base.dto.Product;

@Repository("valuationBooksetComMapper")
public interface BooksetComMapper {
  public List<String> selectAllBooksets(Map<String,Object> map);
  
  public List<Product> findProductByIds(@Param("prodGroupSeqNos") String[] prodGroupSeqNos);
  
  /**modify by wujie on 20190328 start
  public List<Product> findProductByName(@Param("notInProductIds") List<String> notInProductIds,@Param("prodName") String prodName);
  **/
  public List<Product> findProductByName(Map<String,Object> map);
  /**modify by wujie on 20190328 end**/
  //多级法人改造start add by hxy 20200617
  /**
   * 银行模式时，返回机构
   * @return
   */
  public String selectBankModeOrgName();
  /**
   * 联社机构时，选择账套，返回机构
   * @return
   */
  public String selectAssociationModeOrgName(Map<String,Object> orgmap);
  /**
   * 联社机构时，选择账套组返回机构
   * @return
   */
  public String selectAssociationModeOrgGrpName(Map<String,Object> orgmap);
  //多级法人改造end add by hxy 20200617
}