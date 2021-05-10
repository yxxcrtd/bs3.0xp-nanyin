/*
 * 文件名：MstYieldCurveInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.marketdata.exchangerateinfo.service;

import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.exchangerateinfo.dao.ComExchangeQuotationInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.MstExchargeQuoteMapper;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.marketdata.exchangerateinfo.dto.ComExchangeQuotationInfoDto;

/**
 * 汇率行情service 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhao
 * @version 1.0 2018年8月8日
 * @see ComExchangeQuotationInfoService
 * @since 1.0
 */
@Service("serviceComExchangeQuotationInfoService")
@Transactional(rollbackFor = Exception.class)
public class ComExchangeQuotationInfoService extends BaseService<ComExchangeQuotationInfoDto>{

    /**
     * 汇率行情mapper
     */
    @Autowired
    private ComExchangeQuotationInfoMapper comExchangeQuotationInfoMapper;

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
    public Page<ComExchangeQuotationInfoDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page,length,() -> comExchangeQuotationInfoMapper.select(pageParam));
    }

    /**
     * 查询所有数据验证删除是否存在 <功能详细描述>
     * 
     * @param pageParam 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<ComExchangeQuotationInfoDto> selectAllList(Map<String, Object> pageParam) {
        return comExchangeQuotationInfoMapper.select(pageParam);
    }
    
    /**
     * 
     * 工作流完成操作
     * <功能详细描述>
     * @param dto 业务实体
     * @see
     * @since 1.0
     */
    public void doComplete(ComExchangeQuotationInfoDto dto) {
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

    // add by zwq 20210222 增加基准汇率行情导入功能 start
    public void mergeInfoList(List<ComExchangeQuotationInfoDto> list){
        int batchCount = 50;// 每批commit的个数
        int batchLastIndex = batchCount;// 每批最后一个的下标
        for (int index = 0; index < list.size();) {
            if (batchLastIndex > list.size() - 1) {
                batchLastIndex = list.size();
                comExchangeQuotationInfoMapper.mergeInfoList(list.subList(index, batchLastIndex));
                break;// 数据插入完毕,退出循环
            }
            else {
            	comExchangeQuotationInfoMapper.mergeInfoList(list.subList(index, batchLastIndex));
                index = batchLastIndex;// 设置下一批下标
                batchLastIndex = index + batchCount;
            }
        }
    }
    // add by zwq 20210222 增加基准利率行情导入功能 end
}
