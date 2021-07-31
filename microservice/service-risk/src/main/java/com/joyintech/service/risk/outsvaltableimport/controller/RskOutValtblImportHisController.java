package com.joyintech.service.risk.outsvaltableimport.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.risk.outsvaltableimport.dto.RskOutValtblImportHisDto;
import com.joyintech.service.risk.outsvaltableimport.service.RskOutValtblImportHisService;
import com.joyintech.tams.common.base.entity.RskOutValtblImportHis;
import com.joyintech.tams.common.base.entity.RskValtblPrtImpMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rskOutValtblImportHis")
public class RskOutValtblImportHisController {

    @Autowired
    private RskOutValtblImportHisService rskOutValtblImportHisService;

    /**
     *
     * 画面查询方法
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<RskOutValtblImportHisDto> selectForPage(@RequestBody Map<String, Object> param,
                                                        @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return rskOutValtblImportHisService.selectForPage(page, length, param);
    }
    /**
     *
     * <功能详细描述>
     * @param param param
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    @PostMapping("selectPortfolioId")
    public Map<String,String> selectPortfolioId(@RequestBody Map<String,Object> param) {
        return rskOutValtblImportHisService.selectPortfolioId(param);
    }

    /**
     *
     * <功能详细描述>
     * @param param
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    @PostMapping("selectFinProductByFinprodMarketId")
    public Map<String,String> selectFinProductByFinprodMarketId(@RequestBody Map<String,Object> param) {
        return rskOutValtblImportHisService.selectFinProductByFinprodMarketId(param);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return CreditRightsManagementDto
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/insert")
    public void insert(@RequestBody RskOutValtblImportHis dto) {
        rskOutValtblImportHisService.insert(dto);
    }

    /**
     *
     * <功能详细描述>
     * @param param
     * @return int
     * @see
     * @since 1.0
     */
    @PostMapping("queryorgCodeCount")
    public int queryorgCodeCount(@RequestBody String param) {
        return rskOutValtblImportHisService.queryorgCodeCount(param);
    }

    @PostMapping("selectInfoById")
    public RskValtblPrtImpMap selectInfoById(@RequestBody String param) {
        return rskOutValtblImportHisService.selectInfoById(param);
    }

    /**
     *
     * 根据估值表导入信息记录表主键获取历史记录
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectTraceByKey")
    public List<RskOutValtblImportHisDto> selectTraceByKey(@RequestBody RskOutValtblImportHisDto dto){
        return rskOutValtblImportHisService.selectTraceByKey(dto);
    }
}
