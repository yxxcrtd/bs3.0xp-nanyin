/**   
 * 功能描述：
 * @Package: com.joyintech.tams.svreport.interf.chb.prodinfo.dao 
 * @author: 王冠/wangguan   
 * @date: 2019年8月8日 下午2:52:02 
 */
package com.joyintech.tams.svreport.interf.chb.prodinfo.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.interf.chb.prodinfo.dto.ChbPrdBaseinfoExtend;

/**   
* @ClassName: ChbPrdBaseinfoExtemdMapper.java
* @Description: ChbPrdBaseinfo 扩展字段接入dao 用于CASH_MANA_CATE，YIELD，INVEST_FEE_RATE三个字段的接入使用
*
* @author: 王冠/wangguan
* @date: 2019年8月8日 下午2:52:02 
*/
@Repository
public interface ChbPrdBaseinfoExtemdMapper {

	
	/**   
	* @Function: ChbPrdBaseinfoExtemdMapper.java
	* @Description: 获取产品业绩比较基准信息
	*
	* @param: 数据起始日
	* @return：产品业绩比较基准信息
	*
	* @author: 王冠/wangguan
	* @date: 2019年8月8日 下午3:22:38 
	*/
	List<ChbPrdBaseinfoExtend> selectProdYieldInfo(@Param("batchTime")Date batchTime);
	
	
	List<ChbPrdBaseinfoExtend> selectProdInvestFeeRate(@Param("batchTime")Date batchTime);
	
	
}
