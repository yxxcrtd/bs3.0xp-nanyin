/*
 * 文件名：ValMstCapAcctInfoService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.squaremanagement.mstcapacctinfo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.squaremanagement.mstcapacctinfo.dao.ValMstCapAcctInfoMapper;
import com.joyintech.tams.valuation.squaremanagement.mstcapacctinfo.dto.ValMstCapAcctInfoDto;

@Service
@Transactional
public class ValMstCapAcctInfoService extends CommonExportService<ValMstCapAcctInfoDto> {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private ValMstCapAcctInfoMapper valMstCapAcctInfoMapper;

//    /**
//     * 根据条件获取表格信息
//     * 
//     * @param params 筛选条件
//     * @return 表格信息
//     * @throws Exception 
//     */
//    @DataSource(DataSourceType.MASERT)
//    public Page<ValMstCapAcctInfoDto> findPage(Map<String, Object> pageParam)
//        throws Exception {
//    	 return super.queryForPage(()-> valMstCapAcctInfoMapper.queryPageList(pageParam));
//    }
    
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValMstCapAcctInfoDto> findPage(Map<String, Object> parameters) throws Exception {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValMstCapAcctInfo001/findPage",
            parameters, ValMstCapAcctInfoDto.class);
    }
    
//    /**
//     * 根据条件获取表格信息
//     * 
//     * @param params 账号
//     * @return 是否删除
//     * @throws Exception 
//     */
//    @DataSource(DataSourceType.MASERT)
//    public boolean deleteValMatAcctInfoByAcctno(ValMstCapAcctInfoDto dto)
//        throws Exception {
//    	 boolean flag = false;
//    	 String acctNo = dto.getAcctNo();
//    	 int num = valMstCapAcctInfoMapper.deleteValMatAcctInfoByAcctno(acctNo);
//    	 if(num >0){
//    		 flag = true; 
//    	 }
//    	 return flag;
//    }
    
      /**
      * 根据条件获取表格信息
      * 
      * @param params 账号
      * @return 是否删除
      * @throws Exception 
      */
     @DataSource(DataSourceType.MASERT)
     public boolean deleteValMatAcctInfoByAcctno(ValMstCapAcctInfoDto dto)
         throws Exception {
        return restTemplate.postForObject(
             ServiceApplication.VALUATION_SERVICE_NAME + "/ValMstCapAcctInfo001/delete",
             dto, Boolean.class); 
     }

    public String save(ValMstCapAcctInfoDto dto) {
        return restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValMstCapAcctInfo002/save",
            dto, String.class); 
    }

    
    public ValMstCapAcctInfoDto queryValMstAcctInfoByAcctno(String acctNo) {
        return restTemplate.getForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValMstCapAcctInfo002/queryValMstAcctInfoByAcctno?acctNo="+acctNo, 
            ValMstCapAcctInfoDto.class);
    }
    
   
}
