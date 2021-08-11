package com.joyintech.tams.valuation.lossmodel.lossgivendefaultmanage.lossgivendefaultmaintain.service;
import java.util.HashMap;
import java.util.Map;

import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.lossmodel.lossgivendefaultmanage.lossgivendefaultmaintain.dto.ModLgdConfigDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
 * @see LossGivenDefaultMaintain002Service
 * @since 1.0
 */
@Service
public class LossGivenDefaultMaintain002Service extends CommonExportService<ModLgdConfigDto>
												implements BaseProcessService<ModLgdConfigDto>{
    /**
     * 分页查询
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    public Page<ModLgdConfigDto> findPage(Map<String,Object> map){
        
        return restTemplate.postForPage(
            ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/LossGivenDefaultMaintain/findPage",
            map, ModLgdConfigDto.class);
    }
    public String check(@RequestBody Map<String, Object> param) {
        return restTemplate.postForObject(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/LossGivenDefaultMaintain/check", param,
                String.class);
    }
    public ModLgdConfigDto getBussinessEntityByIndId(String seqno) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seqno", seqno);
        return restTemplate.postForObject(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/LossGivenDefaultMaintain/queryDtoBySeqno",
                map, ModLgdConfigDto.class);
    }
	@Override
	public void doComplete(ModLgdConfigDto entity, OperationType type) {
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
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/LossGivenDefaultMaintain/" + action, entity,
                Integer.class);
		
	}
   public String checkIfUsed(@RequestParam Map<String, Object> param) {
	   String result = restTemplate.postForObject(
               ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/LossGivenDefaultMaintain/checkIfUsed",
               param, String.class);
       return result;

   }
    
}
