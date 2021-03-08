package com.joyintech.tams.invest.basicdata.mstfeevarieties.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFeeInfoDto;
import com.joyintech.tams.invest.basicdata.mstfeevarieties.dto.MstFeeVarietiesDto;
import com.joyintech.tams.invest.basicdata.mstfeevarieties.service.MstFeeVarietiesService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * @author wangzehui
 */
@Component
public class MstFeeVarietiesValidator implements BeanValidator {

	/**
	 * 定义service对象
	 */
	@Autowired
	private MstFeeVarietiesService mstFeeVarietiesService;

	/**
	 * 校验
	 * 
	 * @param value
	 *            页面数据
	 * @return 校验结果
	 */
	public List<ValidMessage> valid(Object value) {
		MstFeeVarietiesDto dto = (MstFeeVarietiesDto) value;
		List<ValidMessage> resultList = new ArrayList<ValidMessage>();

		if (StringUtil.equals(dto.getOperateType(), "delete")) {
			List<MstFeeVarietiesDto> list = dto.getSelectedItem();
			// 判断是否选中数据
			if (list.isEmpty()) {

			} else {
				for (MstFeeVarietiesDto mst : list) {
					// 费用类型为浮动差额中收的无法删除
					if (StringUtils.isNotBlank(mst.getFeeType())) {
						if (StringUtils.equalsIgnoreCase(DictCodeList.费用品种类型_浮动差额中收.getValue(), mst.getFeeType())) {
							resultList.add(new ValidMessage(
									this.getMessage("BUSS_COM_E_0005", getMessage("MstFeeVarieties002.feeType"),
											getMessage(mst.getFeeName()), getMessage("MstFeeVarieties002.feeType"),
											getMessage(DictCodeList.费用品种类型_浮动差额中收.getCodeName()))));
						}
					}
					// 通过费用代码判断记录是否被使用
					if (mstFeeVarietiesService.isUseByFeeId(mst.getFeeId()) != 0) {
						resultList.add(new ValidMessage(this.getMessage("COMMON_E_0029")));
					}
				}
			}

		} else {
			if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
				// 通过费用名称查询数据，验证费用名称的唯一性
				List<MstFeeVarietiesDto> list = null;
				if(MultilevelDataAuthorityUtil.isEnabled()) {
					list = mstFeeVarietiesService.getDtoByFeeNameInstituon(dto.getFeeName());
				}
				else {
					list = mstFeeVarietiesService.getDtoByFeeName(dto.getFeeName());
				}
				if (list.size() > 0) {
					resultList.add(new ValidMessage(
							this.getMessage("BUSS_COM_E_0006", getMessage("MstFeeVarieties002.feeName"),"")));
				}
			}

			// 费用类型为浮动差额中收的无法修改
			// 关联范围必须是产品
			// 二级机构费用类型必须是本机构-管理费
			if (StringUtils.isNotBlank(dto.getFeeType())) {
				if (StringUtils.equalsIgnoreCase(DictCodeList.费用品种类型_浮动差额中收.getValue(), dto.getFeeType())) {
					if (StringUtils.isNotBlank(dto.getOperateType())) {
						if (StringUtils.equalsIgnoreCase(dto.getOperateType(), "edit")) {
							resultList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0034",
									getMessage("MstFeeVarieties002.feeType"), getMessage(dto.getFeeName()))));
						}
					}
					if (StringUtils.isNotBlank(dto.getApplyType())) {
						if (!StringUtils.equalsIgnoreCase(DictCodeList.关联范围_产品.getValue(), dto.getApplyType())) {
							resultList.add(new ValidMessage(
									this.getMessage("BUSS_COM_E_0048", getMessage("MstFeeVarieties002.applyType"),
											getMessage(DictCodeList.关联范围_产品.getCodeName(),
													getMessage(DictCodeList.关联范围_产品.getValue())))));
						}
					}
					if (StringUtils.isNotBlank(dto.getOrgType2()) 
							&& !StringUtils.equalsIgnoreCase(dto.getOperateType(), "edit")) {
						if (!StringUtils.equalsIgnoreCase(DictCodeListExt2.费用二级类型_本机构管理费.getValue(),
								dto.getOrgType2())) {
							resultList.add(new ValidMessage(
									this.getMessage("BUSS_COM_E_0048", getMessage("MstFeeVarieties002.orgType2"),
											getMessage(DictCodeListExt2.费用二级类型_本机构管理费.getCodeName(),
													getMessage(DictCodeListExt2.费用二级类型_本机构管理费.getValue())))));
						}
					}
				}
			}

			if (DictCodeList.收费类型_计提式费用.getValue().equals(dto.getChargeType())) {
				if (!StringUtils.equalsIgnoreCase(DictCodeList.费用品种类型_浮动差额中收.getValue(), dto.getFeeType())) {
					if ("".equals(dto.getBasicBill()) || dto.getBasicBill() == null) {
						resultList.add(new ValidMessage(
								this.getMessage("FW_E_0006", getMessage("MstFeeVarieties002.basicBill"))));
					}
					if ("".equals(dto.getBasis()) || dto.getBasis() == null) {
						resultList.add(
								new ValidMessage(this.getMessage("FW_E_0006", getMessage("MstFeeVarieties002.basis"))));
					}
				}
			}

			if (!StringUtil.isEmptyList(dto.getFeeList())) {
				for (BussFinProductFeeInfoDto bussFinProductFeeInfoDto : dto.getFeeList()) {
					if (StringUtil.equals(bussFinProductFeeInfoDto.getFeeName(), dto.getFeeName())) {
						resultList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0019")));
						break;
					}
				}
			}
			if (StringUtil.isNotEmpty(dto.getProfitType())
					&& (StringUtil.equals(dto.getProfitType(), DictCodeList.收益类型_净值型.getValue())
							|| StringUtil.equals(dto.getProfitType(), DictCodeList.收益类型_货币型.getValue()))) {
				if (StringUtil.equals(dto.getFeeType(), DictCodeList.费用品种类型_浮动差额中收.getValue())) {
					resultList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0071")));
				}
			}
		}
		return resultList;
	}
}
