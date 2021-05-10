package com.joyintech.service.valuation.trdassetlossmanage.finproductlossstage.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.trdassetlossmanage.finproductlossstage.dao.FinProductLossStage001Mapper;
import com.joyintech.service.valuation.trdassetlossmanage.finproductlossstage.dto.FinProductLossStage001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(value="serviceFinProductLossStage001Service")
public class FinProductLossStage001Service extends CommonExportService<FinProductLossStage001Dto> {

    @Autowired
    private FinProductLossStage001Mapper finProductLossStage001Mapper;

    /**
     * 查询画面
     */
    public Page<FinProductLossStage001Dto> queryPageList(int page, int length, Map<String, Object> params) throws Exception {
//        if (StringUtil.isNotEmpty(params.get("effDate"))){
//            Date date = DateUtil.parse(params.get("effDate").toString());
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            String dateString = formatter.format(date);
//            params.put("effDate",dateString);
//        }

        return this.queryForPage(page, length,
                () -> finProductLossStage001Mapper.queryPageList(params));
    }

    /**
     * 查询所有画面，不进行分页
     */
    public List<FinProductLossStage001Dto> getAllList(Map<String, Object> params)throws Exception{
        return finProductLossStage001Mapper.queryPageList(params);
    }

    /**
     * 查询资产名称
     */
    public Page<SelectOptionDto> getFinprodName(int page, int length, Map<String, Object> params){
       return this.queryForPageForSelectReturn(page,length,()->finProductLossStage001Mapper.getFinprodName(params));
    }

    /**
     * 查询账套日期
     * @param dto
     * @return
     */
    public FinProductLossStage001Dto getBooksetDate(FinProductLossStage001Dto dto){
        return finProductLossStage001Mapper.getBooksetDate(dto);
    }

    /**
     * 删除数据
     */
    public int deleteFinProLosSta(FinProductLossStage001Dto dto){
        return finProductLossStage001Mapper.deleteFinProLosSta(dto);
    }

    /**
     * 查询上一条有效的的资产减值阶段对象
     */
    public List<FinProductLossStage001Dto> getPrevValidObj(FinProductLossStage001Dto dto){
        return finProductLossStage001Mapper.getPrevValidObj(dto);
    }

    /**
     * 查询下一条有效的的资产减值阶段对象
     */
    public List<FinProductLossStage001Dto> getNextValidObj(FinProductLossStage001Dto dto){
        return finProductLossStage001Mapper.getNextValidObj(dto);
    }
}
