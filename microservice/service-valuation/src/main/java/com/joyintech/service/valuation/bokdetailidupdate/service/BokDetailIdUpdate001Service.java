package com.joyintech.service.valuation.bokdetailidupdate.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.bokdetailidupdate.dao.BokDetailIdUpdate001Mapper;
import com.joyintech.service.valuation.bokdetailidupdate.dto.BokDetailIdUpdate001Dto;
import com.joyintech.service.valuation.bokdetailidupdate.dto.BokDetailIdUpdate002Dto;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;


/**
 * 账务明细代码更新
 *
 * @author tanxuan
 * @createTime 2019-10-10
 */
@SuppressWarnings("rawtypes")
@Service("seriveBokDetailIdUpdate001Service")
public class BokDetailIdUpdate001Service extends BaseService {

    @Autowired
    private BokDetailIdUpdate001Mapper bokDetailIdUpdate001Mapper;
    
    @Autowired
    private InterfCommonService interfCommonService;

//    /**
//     * 账套代码获取
//     * @param length 
//     * @param page 
//     * @param params 
//     * @return 返回的数据
//     */
//    public List<SelectOptionDto> getBooksetId(Map<String, Object> params) {
//    	 List<SelectOptionDto> resultList = bokDetailIdUpdate001Mapper.getBooksetId(params);
//    	
//    	List<SelectOptionDto> selectOptionDtoList = new ArrayList<>();
//        for (SelectOptionDto single : resultList) { // 组装select节点对象
//            SelectOptionDto selectOptionDto = new SelectOptionDto();
//            selectOptionDto.setId(single.getId());
//            selectOptionDto.setText(single.getId());
//            selectOptionDtoList.add(selectOptionDto);
//        }
//        return selectOptionDtoList;
//	}
    
    /**
     * 账务明细代码获取
     * @param length 
     * @param page 
     * @param params 
     * @return 返回的数据
     */
    public List<SelectOptionDto> getBokDetailId(Map<String, Object> params) {
   	 List<SelectOptionDto> resultList = bokDetailIdUpdate001Mapper.getBokDetailId(params);
   	
   	List<SelectOptionDto> selectOptionDtoList = new ArrayList<>();
       for (SelectOptionDto single : resultList) { // 组装select节点对象
           SelectOptionDto selectOptionDto = new SelectOptionDto();
           selectOptionDto.setId(single.getId());
           selectOptionDto.setText(single.getId());
           selectOptionDtoList.add(selectOptionDto);
       }
       return selectOptionDtoList;
	}
    
    /**
     * 列表查询 - 分页
     * @param page
     * @param length
     * @param params
     * @return
     */
    @SuppressWarnings("unchecked")
    public Page<BokDetailIdUpdate001Dto> queryPageList(int page, int length, Map<String, Object> params) {
        return super.queryForPage(page,length,()->bokDetailIdUpdate001Mapper.selectList(params));
    }

    /**
     * 列表查询
     * @param page
     * @param length
     * @param params
     * @return
     */
    public List<BokDetailIdUpdate001Dto> queryAllList(Map<String, Object> params) {
        return bokDetailIdUpdate001Mapper.selectList(params);
    }
    
    /**
     * 校验是否存在跑批业务
     * @param 
     * @return
     * @see
     * @since 1.0
     */
//    @DataSource(DataSourceType.OTHER)
    public int checkExecute() {
    	DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
    	int rst = bokDetailIdUpdate001Mapper.checkExecute();
    	DynamicDataSourceHolder.clear();
        return rst;
    }
    
	/**
	 *查询接口任务
	 * @param params
	 * @return
	 */
	public int checkInterLog(){
		return bokDetailIdUpdate001Mapper.checkInterLog();
	}

    /**
     * 更新
     * @param params
     * @return
     * @throws ParseException
     */
    public String update(List<BokDetailIdUpdate001Dto> params) throws ParseException{
        String msg = "";
        List<BokDetailIdUpdate002Dto> updateList = new ArrayList<>();
    	for (BokDetailIdUpdate001Dto oldId:params){
    		BokDetailIdUpdate002Dto updatedto = new BokDetailIdUpdate002Dto();
    	    
    		//调用接口共通方法【生成MD5账务明细代码】获取新账务明细代码
            BokDetailId bokDetailId = new BokDetailId();
            bokDetailId.setBooksetId(oldId.getBooksetId());
            bokDetailId.setBusiId(oldId.getBusiId());
            bokDetailId.setBusiDetailType(oldId.getBusiDetailType());
            bokDetailId.setCcy(oldId.getCcy());
            bokDetailId.setInvAim(oldId.getInvAim());
            String newBokDetailId = interfCommonService.genMd5BokDetailId(bokDetailId);

            if (oldId.getBokDetailId().equals(newBokDetailId)){
        	    continue;
            }
            updatedto.setBooksetId(oldId.getBooksetId());
            updatedto.setOldBokDetailId(oldId.getBokDetailId());
            updatedto.setNewBokDetailId(newBokDetailId);
            updateList.add(updatedto);
            //每五十条记录更新相关表
            if(updateList.size()==50){
            	bokDetailIdUpdate001Mapper.updateBatch1(updateList);
            	bokDetailIdUpdate001Mapper.updateBatch2(updateList);
            	bokDetailIdUpdate001Mapper.updateBatch3(updateList);
            	bokDetailIdUpdate001Mapper.updateBatch4(updateList);
            	bokDetailIdUpdate001Mapper.updateBatch5(updateList);
            	bokDetailIdUpdate001Mapper.updateBatch6(updateList);
            	bokDetailIdUpdate001Mapper.updateBatch7(updateList);
            	bokDetailIdUpdate001Mapper.updateBatch8(updateList);
            	bokDetailIdUpdate001Mapper.updateBatch9(updateList);
            	bokDetailIdUpdate001Mapper.updateBatch10(updateList);
            	bokDetailIdUpdate001Mapper.updateBatch11(updateList);
            	bokDetailIdUpdate001Mapper.updateBatch12(updateList);
//            	bokDetailIdUpdate001Mapper.updateBatch13(updateList);
            	bokDetailIdUpdate001Mapper.updateBatch14(updateList);
            	updateList.clear();
            }
        }
    	if(!updateList.isEmpty()){
    	bokDetailIdUpdate001Mapper.updateBatch1(updateList);
    	bokDetailIdUpdate001Mapper.updateBatch2(updateList);
    	bokDetailIdUpdate001Mapper.updateBatch3(updateList);
    	bokDetailIdUpdate001Mapper.updateBatch4(updateList);
    	bokDetailIdUpdate001Mapper.updateBatch5(updateList);
    	bokDetailIdUpdate001Mapper.updateBatch6(updateList);
    	bokDetailIdUpdate001Mapper.updateBatch7(updateList);
    	bokDetailIdUpdate001Mapper.updateBatch8(updateList);
    	bokDetailIdUpdate001Mapper.updateBatch9(updateList);
    	bokDetailIdUpdate001Mapper.updateBatch10(updateList);
    	bokDetailIdUpdate001Mapper.updateBatch11(updateList);
    	bokDetailIdUpdate001Mapper.updateBatch12(updateList);
//    	bokDetailIdUpdate001Mapper.updateBatch13(updateList);
    	bokDetailIdUpdate001Mapper.updateBatch14(updateList);
    	}
        msg = "更新成功！";
        return msg;
    }
}
