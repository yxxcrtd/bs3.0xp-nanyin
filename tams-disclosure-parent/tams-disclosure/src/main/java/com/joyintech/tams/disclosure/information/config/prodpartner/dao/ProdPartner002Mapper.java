

package com.joyintech.tams.disclosure.information.config.prodpartner.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.dao.DisProdPartnerMapper;
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
 * @see ProdPartner002Mapper
 * @since 1.0
 */
@Repository(value="ProdPartner002Mapper")
public interface ProdPartner002Mapper extends DisProdPartnerMapper {

    /**
     * 根据产品主代码查询合作机构信息
     * @param map
     * @return
     */
    ProdPartnerDto selectDetail(Map<String, Object> map);

    /**
     * 根据产品主代码查询合作机构信息
     * @param map
     * @return
     */
    List<ProdPartnerDto> selectPartnerByProdId(Map<String, Object> map);


    int updateProdPartner(ProdPartnerDto dto);

    int deleteProdPartner(ProdPartnerDto dto);
}
