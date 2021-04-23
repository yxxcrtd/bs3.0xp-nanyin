/*
 * 文件名：MstCaptialAccountService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstsaledepartment.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.mstsaledepartment.dao.BasicMstSaleDepartmentMapper;
import com.joyintech.service.basicdata.mstsaledepartment.dto.MstCustomerDto;
import com.joyintech.service.basicdata.mstsaledepartment.dto.MstSaleDepartmentDto;
import com.joyintech.service.basicdata.mstsaledepartment.dto.MstSaleDepartmentDto2;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.MstFileOutputParameter;

/**
 * 
 * 营业部查询接口 〈功能详细描述〉
 * 
 * @author fuxj
 * @version 1.0 2020年3月18日
 * @see MstSaleDepartmentService
 * @since 1.0
 */
@Service("backMstSaleDepartmentService")
public class MstSaleDepartmentService extends BaseService<MstSaleDepartmentDto> {

	/**
	 * 附件信息共通Service
	 */
	@Autowired(required = false)
	private MstFileCommonService mstFileCommonService;

	/**
	 * 资金账户管理mapper
	 */
	@Autowired
	private BasicMstSaleDepartmentMapper basicMstSaleDepartmentMapper;

	/**
	 * 
	 * 查询上传文件 <功能详细描述>
	 * 
	 * @param businessId
	 *            businessId
	 * @return List<FileArray>
	 * @see
	 * @since 1.0
	 */
	public List<SysUploadFileInfo> selectFileByBusinessId(String businessId) {
		MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
		mstFileInputParameter.setBussinessId(businessId);
		if (mstFileCommonService != null) {
			MstFileOutputParameter output = mstFileCommonService.getFileInfoByBusiId(businessId);
			return output.getUploadFileList();
		} else {
			return null;
		}
	}

	public List<MstSaleDepartmentDto2> selectForInterf(Map<String, Object> map) {
		return basicMstSaleDepartmentMapper.selectForInterf(map);
	}

	/************************************************************************************/

	/**
	 * 营业部信息分页查询 <功能详细描述>
	 * 
	 * @param length
	 *            长度
	 * @param page
	 *            页数
	 * 
	 * @param pageParam
	 *            查询参数
	 * @return 返回查询结果
	 * @throws Exception
	 *             抛出异常
	 * @see
	 * @since 1.0
	 */
	public Page<MstSaleDepartmentDto> selectForPage(int page, int length, Map<String, Object> pageParam)
			throws Exception {
		return super.queryForPage(page, length, () -> basicMstSaleDepartmentMapper.selectForPage(pageParam));
	}

	/**
	 * 修改、查看营业部信息、主键唯一性验证
	 *
	 * @param map
	 *            检索参数
	 * @return SelectOptionDto
	 */
	public MstSaleDepartmentDto selectByPrimaryKey(MstSaleDepartmentDto dto) {
		return basicMstSaleDepartmentMapper.selectByPrimaryKey(dto);
	}

	/**
	 * 营业部名称搜索
	 *
	 * @param map
	 *            检索参数
	 * @return SelectOptionDto
	 */
	public List<SuggestDto> getMstSaleDepartmentName(Map<String, String> map) {
		return basicMstSaleDepartmentMapper.getMstSaleDepartmentName(map);
	}

	/**
	 * 获取券商
	 *
	 * @param map
	 *            检索参数
	 * @return SelectOptionDto
	 */
	public List<SelectOptionDto> getMstCustomerInfo(Map<String, Object> map) {
		return basicMstSaleDepartmentMapper.getMstCustomerInfo(map);
	}
	
	/**
	 * 获取席位号
	 *
	 * @param map
	 *            检索参数
	 * @return SelectOptionDto
	 */
	public List<SelectOptionDto> getSeatInfoByCustomer(Map<String, Object> map) {
		return basicMstSaleDepartmentMapper.getSeatInfoByCustomer(map);
	}
	/**
	 * 删除操作
	 */
	public int delete(MstSaleDepartmentDto entity) {
		int res = 0;
		if (entity.getSelectedItem() != null && entity.getSelectedItem().size() > 0) {
			for (MstSaleDepartmentDto unit : entity.getSelectedItem()) {
				res += basicMstSaleDepartmentMapper.delete(unit);
			}
		} else {
			res = basicMstSaleDepartmentMapper.delete(entity);
		}
		return res;
	}

	/**
	 * 插入操作
	 */
	public int insert(MstSaleDepartmentDto dto) {
		return basicMstSaleDepartmentMapper.insert(dto);
	}

	/**
	 * 更新操作
	 */
	public int update(MstSaleDepartmentDto dto) {
		return basicMstSaleDepartmentMapper.update(dto);
	}
	
	//add by huanghao 20201117 获取券商详情 start
		/**
		 * 查询券商详情
		 * @param map
		 * @return
		 */
		public List<MstCustomerDto> getMstCustomerForTradeMarket(Map<String, Object> map) {
			return basicMstSaleDepartmentMapper.getMstCustomerForTradeMarket(map);
		}
		//add by huanghao 20201117 获取券商详情 end
}
