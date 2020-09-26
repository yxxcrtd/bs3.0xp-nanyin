package com.joyintech.tams.report.submitmaintenance.transformrule.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.report.submitmaintenance.transformrule.dao.TransformRule001Mapper;
import com.joyintech.tams.report.submitmaintenance.transformrule.dto.TransformRule001Dto;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;


/**
 * 变形转换规则 service
 *
 * @author zhengxiaolian
 * @version 1.0 2019年12月10日
 * @see TransformRule001Service
 * @since 1.0
 */
@Service("transformRule001Service")
@Transactional(rollbackFor = Exception.class)
public class TransformRule001Service extends CommonExportService<TransformRule001Dto>{

	@Autowired
	private TransformRule001Mapper transformRule001Mapper;
	
	private static Logger logger = LoggerFactory.getLogger(TransformRule001Service.class);
	
    /**
     * 查询页面数据
     *
     * @param pageParam 查询条件
     * @return 结果列表
     * @see
     * @since 1.0
     */
    public Page<TransformRule001Dto> searchPage(Map<String, Object> pageParam) {
        String dataStatus = (String)pageParam.get("dataStatus");
        if (StringUtil.isNotBlank(dataStatus)) {
            pageParam.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, dataStatus));
        }
        int pagenum = StringUtil.integerValueOf(""+pageParam.get(GlobalConstant.PARAM_PAGE), 1);
        int length  = StringUtil.integerValueOf(""+pageParam.get(GlobalConstant.PARAM_LENGTH), GlobalConstant.DEFAULT_PAGE_SIZE);
        Page<TransformRule001Dto> page = queryForPage(pagenum, length, ()->transformRule001Mapper.selectAllData(pageParam));
//        Page<TransformRule001Dto> page = queryForPage(TransformRule001Mapper.class, "selectAllData", pageParam);
        dealDataList(page.getRoot());
        return page;
    }
    
    
    private void dealDataList(List<TransformRule001Dto> list) {
		list.forEach(item -> {
			//item.setStatus(dataStatus);
			item.setStatusName(SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, item.getDataStatus()));
            item.setTransTypeStr(SysDataUtil.getNameFromDataDict(DictCode.生僻字类型, item.getTransType()));
            item.setIsModifyStr(
                    SysDataUtil.getNameFromDataDict(DictCode.是或否, item.getIsModify()));
		});
    }
    
    
    public void doDelete(List<TransformRule001Dto> deleteList){
    	transformRule001Mapper.deleteByList(deleteList);
    }
    
  	/**
	 * 导出数据来源
	 * 
	 * @param map
	 *            参数
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	public List<TransformRule001Dto> setDataList(Map<String, Object> map) {
		List<TransformRule001Dto> resultList = new ArrayList<>();
		String keyList = (String) map.get("keyList");
		if (StringUtil.isNotEmpty(keyList)) {
			try {
				resultList = JsonUtils.json2List(keyList, TransformRule001Dto.class);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				return null;
			}
		} else {
	        String status = (String)map.get("status");
	        if (StringUtil.isNotBlank(status)) {
	        	map.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, status));
	        }
	        resultList=transformRule001Mapper.selectAllData(map);
	        dealDataList(resultList);
		}
		return resultList;
	}
	public void genPendingData(String ss){
	    TransformRule001Dto bb=new  TransformRule001Dto();
	    bb.setOrgField(ss);
	    bb.setTransType("02");
	    bb.setDataStatus(DictCodeListExt1.维护数据状态_待处理.getValue());
	    bb.setIsModify("01");
	    if(transformRule001Mapper.getTransFieldCount(bb)<=0){
	        transformRule001Mapper.insertTransField(bb);
	    }
	}
}
