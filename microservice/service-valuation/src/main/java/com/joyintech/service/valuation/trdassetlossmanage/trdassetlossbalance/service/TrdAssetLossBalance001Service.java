package com.joyintech.service.valuation.trdassetlossmanage.trdassetlossbalance.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.trdassetlossmanage.trdassetlossbalance.dao.TrdAssetLossBalance001Mapper;
import com.joyintech.service.valuation.trdassetlossmanage.trdassetlossbalance.dto.TrdAssetLossBalance001Dto;
import com.joyintech.service.valuation.trdassetlossmanage.trdassetlossbalance.dto.TrdAssetLossBalance002Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Service("backTrdAssetLossBalance001Service")
public class TrdAssetLossBalance001Service  extends CommonExportService<TrdAssetLossBalance001Dto> {

    @Autowired
    private TrdAssetLossBalance001Mapper trdAssetLossBalance001Mapper;

    /**
     * 查询画面
     * @param params
     * @return
     * @throws Exception
     */
    public Page<TrdAssetLossBalance001Dto> queryPageList(int page, int length,Map<String, Object> params) throws Exception {
        return this.queryForPage(page, length,
                () -> trdAssetLossBalance001Mapper.queryPageList(params));
    }

    /**
     * 查询资产代码
     */
    public Page<SelectOptionDto> getFinprodId( Map<String, Object> params,int page, int length){
        return this.queryForPageForSelectReturn(page,length,()->trdAssetLossBalance001Mapper.getFinprodId(params));
    }

    /**
     * 查询账套代码
     */
    public Page<SelectOptionDto> getBooksetId( Map<String, Object> params,int page, int length){
        return this.queryForPageForSelectReturn(page,length,()->trdAssetLossBalance001Mapper.getBooksetId(params));
    }

    /**
     * 导出数据查询
     */
    public List<TrdAssetLossBalance001Dto> getAllList(@RequestBody Map<String, Object> params){
        List<TrdAssetLossBalance001Dto> allList = trdAssetLossBalance001Mapper.queryPageList(params);
        return allList;
    }
}
