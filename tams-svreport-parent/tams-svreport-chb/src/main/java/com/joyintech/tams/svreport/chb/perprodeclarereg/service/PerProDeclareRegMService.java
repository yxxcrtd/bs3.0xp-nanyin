package com.joyintech.tams.svreport.chb.perprodeclarereg.service;


import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dto.PerProDeclareRegDto;

/**
 * 产品个人申报登记登服务
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see PerProDeclareRegMService
 * @since 1.0
 */
@Service
public class PerProDeclareRegMService extends BaseService<PerProDeclareRegDto>{

    
    /**
    *
    * 业务画面查询事件
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
   public Page<PerProDeclareRegDto> selectForPage(Map<String, Object> param) {
	   Page<PerProDeclareRegDto> page = restTemplate.postForPage(
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/PerProDeclareReg/findPage",param,
	       		PerProDeclareRegDto.class);
	   //按钮权限控制  add by caojin  2020/7/2 start
	   page.getRoot().forEach(item -> {
//	       if(!SecurityUtil.isPermitted("SvrPerProDeclareReg001_btn_upload")){
		   if(!SecurityUtil.isPermitted("SvrPerProDeclareReg001_TBLROW_batch")){
	       	item.setFlg1("02");   
	       }else{
	       	item.setFlg1("01");
	       }
	       if(!SecurityUtil.isPermitted("SvrPerProDeclareReg001_btn_delete")){
	       	item.setFlg2("02");
	       }else{
	       	item.setFlg2("01");
	       }
	   });
		//按钮权限控制  add by caojin  2020/7/2 end
       return page;
   }
   
   public List<PerProDeclareRegDto> selectAllList(Map<String, Object> param) {
         return restTemplate.postForList(
            		SvrServiceApplication.CHB_SERVICE_NAME + "/PerProDeclareReg/selectAllList",param,
            		PerProDeclareRegDto.class);
   }
   
   
   
	public void updateSumStaus(Map<String, Object> map) {
	   restTemplate.postForObject(
       		SvrServiceApplication.CHB_SERVICE_NAME + "/PerProDeclareReg/updateSumStaus",map,
       		Integer.class);
	}
   
   
   /**
	 * 取消手工处理功能
	 *
	 * @return 提示信息
	 * @param map
	 *            条件map
	 * @seea
	 * @since 1.0
	 */
	public String cancelMannul(Map<String, Object> map) {
		return restTemplate.postForObject(
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/PerProDeclareReg/cancelMannul",map,
	       		String.class);
	}
	
	public void updateFileNo(PerProDeclareRegDto perProDeclareRegDto) {
		restTemplate.postForObject(
	       		SvrServiceApplication.CHB_SERVICE_NAME + "/PerProDeclareReg/updateFileNo",perProDeclareRegDto,
	       		Integer.class);
		
	}
	/**statr  中债一二期直连报送功能  add  by  ***  20201010 */
    /**
     * 报送检查
     *
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public Map<String,Object> submitBeforeCheck(Map<String, Object> param) {
    	return restTemplate.postForObject(
 	       		SvrServiceApplication.CHB_SERVICE_NAME + "/PerProDeclareReg/submitBeforeCheck", param,
 	       	Map.class);
    }
    
	/**end  中债一二期直连报送功能  add  by  ***  20201010 */

	/**
	 *  查询行内标识码
	 * @param map 入参
	 * @return 行内标识码列表
	 */
	public List<SelectOptionDto> queryProdInsIdList(Map<String, Object> map) {
		return restTemplate.postForList(
				SvrServiceApplication.CHB_SERVICE_NAME + "/PerProDeclareReg/queryProdInsIdList",map,
				SelectOptionDto.class);
	}
}
