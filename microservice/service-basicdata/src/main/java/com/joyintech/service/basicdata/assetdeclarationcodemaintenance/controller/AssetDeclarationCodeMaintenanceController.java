package com.joyintech.service.basicdata.assetdeclarationcodemaintenance.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.assetdeclarationcodemaintenance.dto.AssetDeclarationCodeMaintenanceDto;
import com.joyintech.service.basicdata.assetdeclarationcodemaintenance.service.AssetDeclarationCodeMaintenanceService;

@RestController("AssetDeclarationCodeMaintenanceCtr_dtx")
@RequestMapping("/AssetDeclarationCodeMaintenance")
public class AssetDeclarationCodeMaintenanceController {

	/**
	 * 汇率行情service
	 */
	@Autowired
	private AssetDeclarationCodeMaintenanceService assetDeclarationCodeMaintenanceService;

	/**
	 * 汇率行情分页查询 <功能详细描述>
	 * 
	 * @param pageParam
	 *            查询参数
	 * @return 返回查询结果
	 * @throws Exception
	 *             抛出异常
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("/selectForPage/{page}/{length}")
	public Page<AssetDeclarationCodeMaintenanceDto> selectForPage(@RequestBody Map<String, Object> pageParam,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return assetDeclarationCodeMaintenanceService.selectForPage(page, length, pageParam);
	}

    // add by zhangrui1 2020-07-25 资产分页查询 start
    /**
     * 资产代码联想
     * 
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/getFinprodIdPage/{page}/{length}")
    public Page<SelectOptionDto> getFinprodIdPage(@RequestBody Map<String, Object> map,
                                                  @PathVariable("page") int page,
                                                  @PathVariable("length") int length) {
        return assetDeclarationCodeMaintenanceService.getFinprodIdPage(page, length, map);
    }
    // add by zhangrui1 2020-07-25 资产分页查询 end

	/**
	 * 资产代码联想
	 * 
	 * @return 查询结果集
	 * @throws Exception
	 *             异常
	 */
	@PostMapping("/getFinprodIds")
	public List<SelectOptionDto> getFinprodIds(@RequestBody Map<String, Object> map) {
		return assetDeclarationCodeMaintenanceService.getFinprodIds(map);
	}

	/**
	 * 资产代码联想
	 * 
	 * @return 查询结果集
	 * @throws Exception
	 *             异常
	 */
	@PostMapping("/getfinprodType")
	public List<SelectOptionDto> getfinprodType(@RequestBody Map<String, Object> map) {
		return assetDeclarationCodeMaintenanceService.getfinprodType(map);
	}
	
	/**
	 * 资产代码联想
	 * 
	 * @return 根据资产代码+资产申报类型 主键唯一性验证
	 * @throws Exception
	 *             异常
	 */
	@PostMapping("/selectByPrimaryKey")
	public AssetDeclarationCodeMaintenanceDto selectByPrimaryKey(@RequestBody AssetDeclarationCodeMaintenanceDto dto) {
		return assetDeclarationCodeMaintenanceService.selectByPrimaryKey(dto);
	}
	
	/**
	 * 资产申报代码 唯一性验证
	 * 
	 * @return 查询结果集
	 * @throws Exception
	 *             异常
	 */
	@PostMapping("/selectByUniqueKey")
	public AssetDeclarationCodeMaintenanceDto selectByUniqueKey(@RequestBody AssetDeclarationCodeMaintenanceDto dto) {
		return assetDeclarationCodeMaintenanceService.selectByUniqueKey(dto);
	}

	/**
	 * 删除
	 *
	 * @param dto
	 *            页面选中的数据
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("delete")
	public int delete(@RequestBody AssetDeclarationCodeMaintenanceDto dto) throws Exception {
		return assetDeclarationCodeMaintenanceService.delete(dto);
	}

	/**
	 * 插入
	 *
	 * @param dto
	 *            页面选中的数据
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("insert")
	public int insert(@RequestBody AssetDeclarationCodeMaintenanceDto dto) throws Exception {
		return assetDeclarationCodeMaintenanceService.insert(dto);
	}

	/**
	 * 更新
	 *
	 * @param dto
	 *            页面选中的数据
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("update")
	public int update(@RequestBody AssetDeclarationCodeMaintenanceDto dto) throws Exception {
		return assetDeclarationCodeMaintenanceService.update(dto);
	}

	/**
	 * 批量导入
	 *
	 * @param list
	 *            导入的数据
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("insertBatch")
	public int insertBatch(@RequestBody List<AssetDeclarationCodeMaintenanceDto> list) throws Exception {
		int record = 0;
		for (AssetDeclarationCodeMaintenanceDto entity : list) {
			record += assetDeclarationCodeMaintenanceService.insert(entity);
		}
		return record;
	}

	/**
	 * 批量更新
	 *
	 * @param list 导入的数据
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("updateBatch")
	public int updateBatch(@RequestBody List<AssetDeclarationCodeMaintenanceDto> list) throws Exception {
		int record = 0;
		for (AssetDeclarationCodeMaintenanceDto entity : list) {
			record += assetDeclarationCodeMaintenanceService.update(entity);
		}
		return record;
	}

	@PostMapping("/selectForInterf")
	public List<Map<String,String>> selectForInterf(@RequestBody Map<String, Object> map) {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<AssetDeclarationCodeMaintenanceDto> assetDeclarationlist = assetDeclarationCodeMaintenanceService.selectAssetDeclarationList(map);

		if(!StringUtil.isEmptyList(assetDeclarationlist)) {
			assetDeclarationlist.forEach(dto -> {
				Map<String,String> tmp =new HashMap<String,String>();
				tmp.put("finprodId",dto.getFinprodId());
				tmp.put("othCodeType",dto.getOthCodeType());
				tmp.put("othCode",dto.getOthCode());
				list.add(tmp);
			});
		}
		return list;
	}
}
