package com.joyintech.tams.invest.entrust.etrbondbiddingresult.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.entrust.etrbondbiddingresult.dto.Etrbondbiddingresult001Dto;
import com.joyintech.tams.invest.entrust.etrbondbiddingresult.service.EtrbondbiddingresultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@PageId("EtrBondBiddingResult001")
@RequestMapping("/invest/entrust/etrbondbiddingresult/EtrBondBiddingResult001")
public class EtrBondBiddingResult001Controller extends BaseController {

    @Autowired
    private EtrbondbiddingresultService etrbondbiddingresultService;
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, BussinessDictCode.交易方向, BussinessDictCode.交易场所,
                BussinessDictCode.交易流程状态, BussinessDictCode.交易平台, BussinessDictCode.交易市场, BussinessDictCode.缴款状态);
        return super.init(model);
    }


    /**
     *
     * 基金分页查询
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<Etrbondbiddingresult001Dto> selectForPage(){
        Page<Etrbondbiddingresult001Dto> page = etrbondbiddingresultService.selectForPage(
                RequestUtil.getPageParam());
        return page;
    }

    /**
     * 文件导出 <功能详细描述>
     *
     * @return 导出返回参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return etrbondbiddingresultService.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }

    @ResponseBody
    @RequestMapping("/findFinProductByOFinprodId")
    public FinProduct findFinProductByOFinprodId(@RequestBody Etrbondbiddingresult001Dto dto){
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("oFinprodId",dto.getFinprodId());
        List<FinProduct> result = etrbondbiddingresultService.findFinProductByOFinprodId(objectMap);
        if (result == null || result.isEmpty()) {
            return new FinProduct();
        }
        return result.get(0);
    }
}
