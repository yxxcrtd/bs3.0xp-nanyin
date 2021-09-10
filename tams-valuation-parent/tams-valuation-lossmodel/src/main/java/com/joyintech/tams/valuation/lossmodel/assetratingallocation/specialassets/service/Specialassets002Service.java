package com.joyintech.tams.valuation.lossmodel.assetratingallocation.specialassets.service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.specialassets.dto.ModAssetGradeSpecialConfigDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 资产端品种与期限分布明细表
 * @author yuluan
 * @version 1.0 2019年08月01日
 * @see Specialassets002Service
 * @since 1.0
 */
@Service
public class Specialassets002Service extends CommonExportService<ModAssetGradeSpecialConfigDto>
												implements BaseProcessService<ModAssetGradeSpecialConfigDto>{
    /**
     * 分页查询
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    public Page<ModAssetGradeSpecialConfigDto> findPage(Map<String,Object> map){
        
        return restTemplate.postForPage(
            ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/Specialassets/findPage",
            map, ModAssetGradeSpecialConfigDto.class);
    }
    public String check(@RequestBody Map<String, Object> param) {
        return restTemplate.postForObject(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/Specialassets/check", param,
                String.class);
    }
    public ModAssetGradeSpecialConfigDto getBussinessEntityByIndId(String seqno) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seqno", seqno);
        return restTemplate.postForObject(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/Specialassets/queryDtoBySeqno",
                map, ModAssetGradeSpecialConfigDto.class);
    }
	@Override
	public void doComplete(ModAssetGradeSpecialConfigDto entity, OperationType type) {
		String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        } else if (OperationType.NEW.equals(type)) {
            action = "insert";
        } else {
            action = "update";
            entity.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        }
        restTemplate.postForObject(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/Specialassets/" + action, entity,
                Integer.class);
		
	}

	/**
	 * @Description:根据产品获取产品的证券品种
	 * @auther: yuzj
	 * @date: 2021/8/19
	 * @param:
	 * @return:
	 */
    public String getSecTypeByFinprodId(@RequestBody Map<String, Object> param){
        return restTemplate.postForObject(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/Specialassets/getSecTypeByFinprodId", param,
                String.class);
    }


    /**
     * 根据资产代码查询条数
     * @param
     * @return
     */
    public Integer getSpecialCountByFinprodId(@RequestBody ModAssetGradeSpecialConfigDto  dto){
        return restTemplate.postForObject(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/Specialassets/getSpecialCountByFinprodId", dto,
                Integer.class);
    }

    
    
}
