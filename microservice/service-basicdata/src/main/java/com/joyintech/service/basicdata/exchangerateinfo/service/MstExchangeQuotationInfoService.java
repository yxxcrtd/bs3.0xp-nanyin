/*
 * 文件名：MstYieldCurveInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.exchangerateinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.MstExchargeQuoteMapper;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.exchangerateinfo.dao.MstExchangeQuotationInfoMapper;
import com.joyintech.service.basicdata.exchangerateinfo.dto.MstExchangeQuotationInfoDto;

/**
 * 汇率行情service 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhao
 * @version 1.0 2018年8月8日
 * @see MstExchangeQuotationInfoService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MstExchangeQuotationInfoService extends BaseService<MstExchangeQuotationInfoDto>{

    /**
     * 汇率行情mapper
     */
    @Autowired
    private MstExchangeQuotationInfoMapper mstExchangeQuotationInfoMapper;

    /**
     * 汇率行情自动生成mapper
     */
    @Autowired
    private MstExchargeQuoteMapper mstExchargeQuoteMapper;

    /**
     * 汇率行情分页查询 <功能详细描述>
     * @param length 长度
     * @param page 页数
     * 
     * @param pageParam 查询参数
     * @return 返回查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    public Page<MstExchangeQuotationInfoDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page,length,() -> mstExchangeQuotationInfoMapper.select(pageParam));
    }

    /**
     * 查询所有数据验证删除是否存在 <功能详细描述>
     * 
     * @param pageParam 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<MstExchangeQuotationInfoDto> selectAllList(Map<String, Object> pageParam) {
        return mstExchangeQuotationInfoMapper.select(pageParam);
    }
    
    /**
     * 
     * 工作流完成操作
     * <功能详细描述>
     * @param dto 业务实体
     * @see
     * @since 1.0
     */
    public void doComplete(MstExchangeQuotationInfoDto dto) {
        if (StringUtil.equals(dto.getOperateType(), "DELETE")) {
            mstExchargeQuoteMapper.deleteByPrimaryKey(dto);
        }
        else if (StringUtil.equals(dto.getOperateType(), "NEW")) {
//             设置基本币种，单位币种等
            String exchargeCode = dto.getExchargeCode();
            if (exchargeCode != null) {
                List<String> splitToList = StringUtil.splitToList("/", exchargeCode);
                dto.setBasisCyy(splitToList.get(0));
                dto.setQuotCyy(splitToList.get(1));
            }
            mstExchargeQuoteMapper.insert(dto);
        }
        else {
            mstExchargeQuoteMapper.updateByPrimaryKeySelective(dto);
        }
    }

}
