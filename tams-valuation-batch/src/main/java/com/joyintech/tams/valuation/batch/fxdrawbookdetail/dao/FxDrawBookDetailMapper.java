package com.joyintech.tams.valuation.batch.fxdrawbookdetail.dao;

import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComDTO;
import com.joyintech.tams.valuation.batch.fxdrawbookdetail.dto.FxDrawBookFinTradeProductDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yanya
 * @create 2019-12-12 10:51
 */
@Repository
public interface FxDrawBookDetailMapper {

    /**
     * 获取存款类资产计提账务待处理交易数据
     * @param param
     * @return
     */
    public List<DrawBaseInfoSelectComDTO> selectDrawBaseInfoFromHis(Map<String, Object> param);

    /**
     * 查询交易类金融产品附属表
     * @param param
     * @return
     */
    public List<FxDrawBookFinTradeProductDto> selectFinTradeProductAdd(Map<String, Object> param);
}
