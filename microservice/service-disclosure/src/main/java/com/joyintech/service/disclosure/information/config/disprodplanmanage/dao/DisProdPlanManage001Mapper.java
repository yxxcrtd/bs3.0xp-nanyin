/*
 * 文件名：ReportPageManage001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.service.disclosure.information.config.disprodplanmanage.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage001Dto;
import com.joyintech.service.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage002Dto;
import com.joyintech.service.disclosure.information.config.prodbonusplanmanage.dto.ProdBonusplanDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.disclosure.information.config.reportpage.dao.ReportPageManage001Mapper;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author jiangsucheng
 * @version 1.0 2018年11月7日
 * @see ReportPageManage001Mapper
 * @since 1.0
 */
@Repository(value="serviceDisProdPlanManage001Mapper")
public interface DisProdPlanManage001Mapper {

	List<DisProdPlanManage001Dto> searchReports(Map<String, Object> params);

	DisProdPlanManage002Dto selectByProdId(@Param("prodId") String prodId, @Param("dataStatus")String dataStatus);


}
