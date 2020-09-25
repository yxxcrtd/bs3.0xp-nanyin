package com.joyintech.service.product.nolimit.maxissuablesize.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.nolimit.common.dao.NolimitCommonMapper;
import com.joyintech.service.product.nolimit.maxissuablesize.dao.MaxIssuableSizeMapper;
import com.joyintech.service.product.nolimit.maxissuablesize.dto.MaxIssuableSizeDto;
import com.joyintech.tams.constant.DictCodeListExt1;
/**
 * 微服务端最大可发行规模查询Service
 * 〈功能详细描述〉
 * @author qufangxu
 * @version 1.0 2020年1月1日
 * @see MaxIssuableSizeService
 * @since 1.0
 */
@Service("backMaxIssuableSizeService")
public class MaxIssuableSizeService extends BaseService<MaxIssuableSizeDto>{
	/**
	 * 最大可发行规模查询mapper
	 */
	@Autowired
	private MaxIssuableSizeMapper maxIssuableSizeMapper;
	
	@Autowired
	private NolimitCommonMapper nolimitCommonMapper;	
    /**
     * 最大可发行规模查询 分页查询
     * <功能详细描述>
     * @param page
     * @param length
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<MaxIssuableSizeDto> selectForPage(int page, int length,Map<String, Object> pageParam)
            throws Exception {
    	
    	Page<MaxIssuableSizeDto> pages = super.queryForPage(page, length, () -> maxIssuableSizeMapper.selectForPage(pageParam));
        List<MaxIssuableSizeDto> getroot = pages.getRoot();
    	Map<String,Object> mapInfo = new HashMap<String,Object>();
    	mapInfo.put("cdate", (String)pageParam.get("queryDate"));
    	List<Map<String,Object>> AssetList = nolimitCommonMapper.queryLimitAmt(mapInfo);//共通：非标资产额度查询(非标限额、整体非标比例上限)
    	BigDecimal nonStandardLimit = (BigDecimal) AssetList.get(0).get("NON_STANDARD_LIMIT");//非标限额
    	BigDecimal nonStandardRitio = (BigDecimal) AssetList.get(0).get("NON_STANDARD_ROTIO");//整体非标比例上限
    	mapInfo.put("isNonstandard", DictCodeListExt1.是否_是.getValue());
    	BigDecimal totalAsset = nolimitCommonMapper.getShareAmt(mapInfo);//共通：可用额度查询(当前非标总资产)
    	//暂时处理null值,不处理会报空指针异常，查询应有值
    	if (totalAsset==null){
    		totalAsset=new BigDecimal(0);
    	}
    	BigDecimal totalNonLimitAsset = nonStandardLimit.subtract(totalAsset);//当前整体非标总可用限额
    	Iterator<MaxIssuableSizeDto> iteratorPage = getroot.listIterator();//遍历getroot，不为空
    	while(iteratorPage.hasNext()){
    		MaxIssuableSizeDto dtolist = iteratorPage.next();
    		if (dtolist.getNonStandardLimit()==null){
    			iteratorPage.remove();
    		}
    		else{
    			BigDecimal nonStandardLimitDetail = dtolist.getNonStandardLimit();//明细.非标资产比例上限
    			BigDecimal resultTemp = nonStandardLimitDetail.subtract(nonStandardRitio);
    			//除数resultTemp为零，最大可发行规模值设为-1
    			if (resultTemp.equals(new BigDecimal("0"))){
    				BigDecimal maxIssuableSize = new BigDecimal("-1");
    				dtolist.setMaxIssuableSize(maxIssuableSize);
    			}
    			else{
    				BigDecimal maxIssuableSize = totalNonLimitAsset.divide(resultTemp, 2, BigDecimal.ROUND_HALF_UP);//最大可发行规模
    				dtolist.setMaxIssuableSize(maxIssuableSize);
    			}

    		}
    	}
     	pages.setRoot(getroot);
        return pages;
    }
    
    /**
     * 产品规划系列联想框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryPrdPlanSerisIdInfo(@RequestBody Map<String, Object> pageParam){
    	return maxIssuableSizeMapper.queryPrdPlanSerisIdInfo(pageParam);
    }
    
    
}
