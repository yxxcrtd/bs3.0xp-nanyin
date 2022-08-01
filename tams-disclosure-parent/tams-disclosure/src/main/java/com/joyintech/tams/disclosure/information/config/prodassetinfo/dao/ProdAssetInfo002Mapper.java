

package com.joyintech.tams.disclosure.information.config.prodassetinfo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.dao.DisProdAssetMapper;
import com.joyintech.tams.common.base.dao.DisProdPartnerMapper;
import com.joyintech.tams.disclosure.information.config.prodassetinfo.dto.ProdAssetInfoDto;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author zhouqiang
 * @version 1.0 2022年07月12日
 * @see ProdAssetInfo002Mapper
 * @since 1.0
 */
@Repository(value="ProdAssetInfo002Mapper")
public interface ProdAssetInfo002Mapper extends DisProdAssetMapper {

    /**
     * 根据产品主代码查询合作机构信息
     * @param map
     * @return
     */
    ProdAssetInfoDto selectDetail(Map<String, Object> map);

    /**
     * 根据产品主代码查询合作机构信息
     * @param map
     * @return
     */
    List<ProdAssetInfoDto> selectProdAssetInfoByProdId(Map<String, Object> map);


    int updateProdPartner(ProdAssetInfoDto dto);

    int deleteProdPartner(ProdAssetInfoDto dto);

    int deleteProdAssetByPrimaryKey(ProdAssetInfoDto dto);
}
