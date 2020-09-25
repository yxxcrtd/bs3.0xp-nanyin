/*
 * 文件名：TaxScheme001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.taxparametermanage.taxscheme.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.dto.TaxScheme001Dto;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.service.TaxScheme001Service;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.service.TaxScheme002Service;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.service.TaxScheme003Service;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.service.TaxScheme004Service;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.service.TaxScheme005Service;
import com.joyintech.service.valuation.taxparametermanage.taxscheme.service.TaxScheme006Service;
import com.joyintech.system.constant.SystemGlobalConstant;

/**
 * 税务方案 - 主界面
 * 
 * @author zhangwei
 * @version 1.0 2018年10月10日
 * @see TaxScheme001Controller
 * @since 1.0
 */
@RestController(value="serviceTaxScheme001Controller")
@RequestMapping("/TaxScheme001")
public class TaxScheme001Controller extends BaseController {

	@Autowired
	private TaxScheme001Service taxScheme001Service;

	/** 税务方案 **/
	@Autowired
	private TaxScheme002Service taxScheme002Service;

	/** 增值税则表 **/
	@Autowired
	private TaxScheme003Service taxScheme003Service;

	/** 附加税则表 **/
	@Autowired
	private TaxScheme004Service taxScheme004Service;
	
	/** 资产应税信息 **/
    @Autowired
    private TaxScheme005Service taxScheme005Service;
    
    /** 参数设置 **/
    @Autowired
    private TaxScheme006Service taxScheme006Service;
    
	/**
	 * 获取税务方案 - 分页
	 * 
	 * @return
	 * @throws Exception
	 */
    @PostMapping("/findScheme/{page}/{length}")
	public Page<TaxScheme001Dto> findScheme(@RequestBody Map<String, Object> param,
	                                        @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return taxScheme001Service.findSchemeByPage(page, length, param);
	}

	/**
	 * 获取增值税则表 - 分页
	 * 
	 * @return
	 * @throws Exception
	 */
    @PostMapping("/findVatTax/{page}/{length}")
	public Page<TaxScheme001Dto> findVatTax(@RequestBody Map<String, Object> param,
                                            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return taxScheme001Service.findVatTaxByPage(page, length, param);
	}

	/**
	 * 获取附加税则表 - 分页
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/findAdtTax/{page}/{length}")
	public Page<TaxScheme001Dto> findAdtTax(@RequestBody Map<String, Object> param,
                                            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return taxScheme001Service.findAdtTaxByPage(page, length, param);
	}

	/**
     * 获取资产应税表 - 分页
     * 
     * @return
     * @throws Exception
     */
    @PostMapping("/findTaxable/{page}/{length}")
    public Page<TaxScheme001Dto> findTaxable(@RequestBody Map<String, Object> param,
                                             @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return taxScheme001Service.findTaxableByPage(page, length, param);
    }
	
    /**
     * 获取参数设置 - 分页
     * 
     * @return
     * @throws Exception
     */
    @PostMapping("/findConfig/{page}/{length}")
    public Page<TaxScheme001Dto> findConfig(@RequestBody Map<String, Object> param,
                                            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return taxScheme001Service.findConfigByPage(page, length, param);
    }

    /**
     * 获取参数设置 - 分页
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/findTaxSchaBokConfig/{page}/{length}")
    public Page<TaxScheme001Dto> findTaxSchaBokConfig(@RequestBody Map<String, Object> param,
                                            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return taxScheme001Service.findTaxSchaBokConfigByPage(page, length, param);
    }

    /**
     * 获取增值税则表 - 不分页
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("findVatTax")
    public List<TaxScheme001Dto> findVatTax(@RequestBody Map<String, Object> param) throws Exception {
        return taxScheme001Service.findVatTax(param);
    }
    
    /**
     * 获取附加税则表 - 不分页
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("findAdtTax")
    public List<TaxScheme001Dto> findAdtTax(@RequestBody Map<String, Object> param) throws Exception {
        return taxScheme001Service.findAdtTax(param);
    }
    
    /**
     * 获取资产应税表 - 不分页
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("findTaxable")
    public List<TaxScheme001Dto> findTaxable(@RequestBody Map<String, Object> param) throws Exception {
        return taxScheme001Service.findTaxable(param);
    }
    
    /**
     * 获取资产应税表 - 不分页
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("findConfig")
    public List<TaxScheme001Dto> findConfig(@RequestBody Map<String, Object> param) throws Exception {
        return taxScheme001Service.findConfig(param);
    }

    /**
     * 获取参数设置 - 分页
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/findTaxSchaBokConfig")
    public List<TaxScheme001Dto> findTaxSchaBokConfig(@RequestBody Map<String, Object> param){
        return taxScheme001Service.findTaxSchaBokConfig(param);
    }
    
    /**
     * 下拉框 ： 资产代码
     * 
     * @return
     * @throws Exception
     */
    @PostMapping("/findAsset")
    public List<SelectOptionDto> findAsset(@RequestBody Map<String, Object> param) throws Exception {
        return taxScheme001Service.findAsset(param);
    }
    
    /**
     * 下拉框 ： 项目
     * 
     * @return
     * @throws Exception
     */
    @PostMapping("/findProject")
    public List<SelectOptionDto> findProject(@RequestBody Map<String, Object> param) throws Exception {
        return taxScheme006Service.findProject(param);
    }

    
	/**
	 * 
	 * 导出：增值税则表
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("exportVatTax")
	public ResponseEntity<byte[]> exportVatTax(@RequestBody Map<String, Object> param) throws Exception {

		List<TaxScheme001Dto> dtoList = taxScheme001Service.findVatTax(param);
		File outFile = taxScheme001Service.makeFile(
				"/com/joyintech/tams/valuation/taxparametermanage/taxscheme/conf/export_exportvattax.xml",
				dtoList);
		return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
				+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
				+ FileNameUtil.getFileExtName(outFile), outFile, true);
	}
	
	/**
	 * 
	 * 导出：附加税则表
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("exportAdtTax")
	public ResponseEntity<byte[]> exportAdtTax() throws Exception {

		List<TaxScheme001Dto> dtoList = taxScheme001Service.findAdtTax(RequestUtil.getPageParam());
		File outFile = taxScheme001Service.makeFile(
				"/com/joyintech/tams/valuation/taxparametermanage/taxscheme/conf/export_exportadttax.xml",
				dtoList);
		return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
				+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
				+ FileNameUtil.getFileExtName(outFile), outFile, true);
	}

	/**
     * 
     * 导出：资产应税信息
     * 
     * @return
     * @throws Exception
     */
    @PostMapping("exportTaxable")
    public ResponseEntity<byte[]> exportTaxable() throws Exception {

        List<TaxScheme001Dto> dtoList = taxScheme001Service.findTaxable(RequestUtil.getPageParam());
        File outFile = taxScheme001Service.makeFile(
                "/com/joyintech/tams/valuation/taxparametermanage/taxscheme/conf/export_exporttaxable.xml",
                dtoList);
        return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile), outFile, true);
    }
    
    /**
     * 
     * 导出：参数设置
     * 
     * @return
     * @throws Exception
     */
    @PostMapping("exportConfig")
    public ResponseEntity<byte[]> exportConfig() throws Exception {

        List<TaxScheme001Dto> dtoList = taxScheme001Service.findConfig(RequestUtil.getPageParam());
        File outFile = taxScheme001Service.makeFile(
                "/com/joyintech/tams/valuation/taxparametermanage/taxscheme/conf/export_exportconfig.xml",
                dtoList);
        return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile), outFile, true);
    }

    
}
