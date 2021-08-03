/*
 * 文件名：FinFundTargetValidator.java
 * 版权：Copyright JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：广州银行poc
 * 修改人：余飞
 * 修改时间：2020年11月09日
 * 修改内容：新建
 * 系统名称：广州银行poc
 */

package com.joyintech.tams.invest.asset.futures.valid;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.invest.asset.futures.dto.FinFuturesDto;
import com.joyintech.tams.invest.asset.futures.service.FinFuturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author yufei
 * @version 1.0 2020年11月09日
 * @see FinFuturesValidator
 * @since 1.0
 */
@Component
public class FinFuturesValidator implements BeanValidator {

    /**
     * 共通接口
     */
    @Autowired
    private CommonBusinessService commonBusinessService;

    @Autowired
    private FinFuturesService finFuturesService;

    /**
     * 校验
     *
     * @param value 页面数据
     * @return 校验结果
     */
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        FinFuturesDto dto = (FinFuturesDto)value;
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            List<FinFuturesDto> list = dto.getSelectedItem();
            // 判断是否选中数据
            if (list != null && list.size() > 0) {
                for (FinFuturesDto mst : list) {
                    //判断是否存在交易
                    if (null != commonBusinessService) {
                        if (commonBusinessService.isExistTransInAsset(mst.getFinprodId()) > 0) {
                            resultList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0043")));
                        }
                    }
                }
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "save") || StringUtil.equals(
            dto.getOperateType(), "submit")) {

        }
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            //通过资产代码查询数据，验证资产代码的唯一性
            Map<String, Object> map = new HashMap<String, Object>();
            if (dto.getFinprodMarketId() != null && !"".equals(dto.getFinprodMarketId())) {
                map.put("finprodMarketId", dto.getFinprodMarketId());
                FinFuturesDto param = finFuturesService.findEntity(map);
                if (param != null) {
                    resultList.add(new ValidMessage(this.getMessage("BUSS_COM_I_0017",
                        getMessage("FinFutures001.finprodMarketId") + "("
                        + dto.getFinprodMarketId() + ")")));
                }
            }
        }
        return resultList;
    }

}
