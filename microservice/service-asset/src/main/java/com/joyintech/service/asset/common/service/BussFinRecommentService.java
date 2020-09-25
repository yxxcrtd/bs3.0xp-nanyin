/*
 * 文件名：BussFinRecommentService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.asset.common.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.asset.common.dao.BussFinRecommentMapper;
import com.joyintech.tams.common.base.entity.FinProductRecomment;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 资产推荐方及费率信息 〈功能详细描述〉
 * 
 * @author renping
 * @version 1.0 2019年10月10日
 * @see BussFinRecommentService
 * @since 1.0
 */
@Service
public class BussFinRecommentService extends BaseService {

    @Autowired
    private BussFinRecommentMapper bussFinRecommentMapper;

    /**
     * 初始化资产推荐方及费率信息
     * 
     * @param finprodId 金融产品代码
     * @return 资产推荐方及费率信息
     * @see
     * @since 1.0
     */
    public List<FinProductRecomment> getFinProductRecommentByProductId(String finprodId) {
        List<FinProductRecomment> list = bussFinRecommentMapper.getFinProductRecommentByProductId(
            finprodId);
        if (!StringUtil.isEmptyList(list)) {
            list.forEach(entity -> entity.setRecfeeRate(
                ArithmeticUtil.multiply(entity.getRecfeeRate(), new BigDecimal(100))));
        }
        return list;
    }
    
    /**
     * 删除资产推荐方及费率信息
     * 
     * @param finprodId 金融产品代码
     * @return 删除数据条数
     * @see
     * @since 1.0
     */
    public int deleteByProductId(String finprodId) {
        if (StringUtil.isNotEmpty(finprodId)) {
            return bussFinRecommentMapper.deleteByProductId(finprodId);
        }
        else {
            return 0;
        }
    }

    /**
     * 构建推荐方下拉框
     * 
     * @param param 参数
     * @return 下拉框查询结果
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> qryRecommenderSelect(int page, int length,
                                                      Map<String, Object> param) {
        param.put("counterType", DictCodeListExt1.交易对手类型_分行.getValue());
        return this.queryForPageForSelectReturn(page, length,
            () -> bussFinRecommentMapper.qryRecommenderSelect(param));
    }
}
