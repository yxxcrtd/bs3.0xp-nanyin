/**  
 * @Project     service-chb3  
 * @File        ProShareCheckController.java  
 * @Package     com.joyintech.service.chb3.queryanalysis.controller  
 * @Version     V1.0  
 * @Date        2019年12月30日 下午3:20:53  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */  
    
package com.joyintech.service.chb3.queryanalysis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.queryanalysis.prosharecheck.dto.ProShareCheck001Dto;
import com.joyintech.tams.report.queryanalysis.prosharecheck.service.ProShareCheck001Service;

/**  
 * @Description 产品份额检查微服务  
 * @ClassName   ProShareCheckController  
 * @Date        2019年12月30日 下午3:20:53  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */
@RestController
@RequestMapping("/ProShareCheck")
public class ProShareCheckController {
	
	/**
	 * 产品份额检查 servive
	 */
	@Autowired
	private ProShareCheck001Service proShareCheck001Service;
	
	 /**
    *
    * 业务画面查询事件
    *
    * @return 结果列表
    * @see
    * @since 1.0
    */
   @PostMapping("searchPage/{page}/{length}")
   public Page<ProShareCheck001Dto> searchPage(@RequestBody Map<String, Object> param) {
       Page<ProShareCheck001Dto> page = proShareCheck001Service.searchPage(param);
       return page;
   }

   /**
   *
   * 业务画面导出查询
   * @return 結果
   * @see
   * @since 1.0
   */
  @PostMapping("setDataList")
  public List<ProShareCheck001Dto> setDataList(@RequestBody Map<String, Object> param) {
  	return proShareCheck001Service.setDataList(param);
  }
}
