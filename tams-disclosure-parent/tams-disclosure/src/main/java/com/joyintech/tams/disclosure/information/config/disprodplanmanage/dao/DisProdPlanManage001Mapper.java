/*
 * 文件名：ReportPageManage001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.information.config.disprodplanmanage.dao;

import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage001Dto;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage002Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author jiangsucheng
 * @version 1.0 2018年11月7日
 * @see
 * @since 1.0
 */
@Repository
public interface DisProdPlanManage001Mapper {

	List<DisProdPlanManage001Dto> searchReports(Map<String, Object> params);

	DisProdPlanManage002Dto selectByProdId(@Param("prodId") String prodId, @Param("dataStatus")String dataStatus);

	String getProdId(@Param("prodId") String prodId);

    List<DisProdPlanManage002Dto> isRepeated(DisProdPlanManage002Dto dto);

	List<DisProdPlanManage002Dto> selectProdPlanList(@Param("list") List<DisProdPlanManage002Dto> list);
}
