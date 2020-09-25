package com.joyintech.service.risk.creditmanagement.creditrightsmanagement.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.risk.creditmanagement.creditrightsmanagement.dto.CreditRightsManagementDto;
import com.joyintech.service.risk.creditmanagement.creditrightsmanagement.service.CreditRightsManagementService;


/**
 * 风险权数管理
 * @author huwenbin
 *
 */
@RestController("CreditRightsManagementCtl_dtx")
@RequestMapping("/CreditRightsManagement")
public class CreditRightsManagementController {
	
	/**
	 * 自定义service
	 */
    @Autowired
	private CreditRightsManagementService creditRightsManagementService;
	
	/**
	 * 
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param pageParam Map<String, Object>
	 * @param page int
	 * @param length int
	 * @return CreditRightsManagementDto
	 * @throws Exception Exception
	 * @see
	 * @since 1.0
	 */
    @PostMapping("/findForPage/{page}/{length}")
    public Page<CreditRightsManagementDto> selectForPage(@RequestBody Map<String, Object> pageParam,@PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return creditRightsManagementService.selectForPage(page,length,pageParam);
    }
    
    /**
     * 
     * 工作流完成操作
     * <功能详细描述>
     * @param map 业务实体
     * @see
     * @since 1.0
     */
	@Transactional
	@DTXTransaction
    @PostMapping("/doComplete")
    public void doComplete(@RequestBody CreditRightsManagementDto dto) {
    	creditRightsManagementService.doComplete(dto);
    }
	
    /**
     * 
     * 双击、编辑查看详情
     * <功能详细描述>
     * @param seqno String
     * @return CreditRightsManagementDto
     * @see
     * @since 1.0
     */
    @GetMapping("selectDetail")
    public CreditRightsManagementDto selectDetail(String seqno){
    	return creditRightsManagementService.selectDetail(seqno);
    }
    
    /**
     * 
     * 同一个资产二级类型、同一生效日只能有一条数据
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return List<CreditRightsManagementDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/checkalreadyhave")
    public List<CreditRightsManagementDto> checkalreadyhavve(@RequestBody Map<String, Object> pageParam){
    	return creditRightsManagementService.checkalreadyhave(pageParam);
    }
}
