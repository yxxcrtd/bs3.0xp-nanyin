package com.joyintech.tams.svreport.chbreport.chbinvestoperation.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.chbreport.chbinvestoperation.dto.ChbInvestPrdInfo;
import com.joyintech.tams.svreport.chbreport.chbinvestoperation.dto.ChbInvsetAssetInfo;
import com.joyintech.tams.svreport.chbreport.chbinvestoperation.service.ChbInvestOperationService;
import com.joyintech.tams.svreport.chbreport.utils.WordBatchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @ClassName ChbInvestOperation001Controller
 * @Description: 非标投资运行报告查询controller
 * @Author yanya
 * @Date 2021-04-25
 * @Version V1.0
 **/
@Controller
@PageId("ChbInvestOperation001")
@RequestMapping("/svreport/chbreport/chbinvestoperation/ChbInvestOperation001")
public class ChbInvestOperation001Controller extends BaseController {

    @Autowired
    private ChbInvestOperationService chbInvestOperationService;

    @Override
    public String init(Model model) {

        return super.init(model);
    }

    /**
     * 分页查询投资运作报告
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/findPage")
    public Page<ChbInvestPrdInfo> findPage() throws Exception {
        return chbInvestOperationService.findPage(RequestUtil.getPageParam());
    }

    /**
     * 查询分页产品下拉框
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryPrdInfoPage")
    public Page<SelectOptionDto> queryPrdInfoPage() {
        return chbInvestOperationService.queryPrdInfoPage(RequestUtil.getPageParam());
    }

    /**
     * 生成到期公告
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "createDoc", method = RequestMethod.POST)
    public ResponseEntity<byte[]> createDoc() throws Exception {
        Map<String, Object> map = RequestUtil.getPageParam();
        String datas = this.getParam("datas");
        List<ChbInvestPrdInfo> list = null;
        if(StringUtil.isNotEmpty(datas)) {
            list = JsonUtils.json2List(datas, ChbInvestPrdInfo.class);
        }
        else {
            return null;
        }
        //String srcFilePath = getClass().getClassLoader().getResource("template/chbreport").getPath();
        String srcFilePath = FileNameUtil.getTplFile("chbreport");
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        List<String> titleList = new ArrayList<String>();
        list.stream().forEach(item -> {
            Map<String, Object> param = new HashMap<>();
            param.put("dealdateFmt", item.getDealdateFmt());//交易日
            param.put("prodId", item.getProdId());//产品代码
            ChbInvestPrdInfo chbInvestPrdInfo = chbInvestOperationService.qryChbInvestPrdInfo(param);
            param.put("curAssetType", DictCodeListExt2.资金流动类型_资产买入.getValue());//买入
            List<ChbInvsetAssetInfo> mr =  chbInvestOperationService.qryChbInvsetAssetInfo(param);
            param.put("curAssetType", DictCodeListExt2.资金流动类型_资产卖出或到期.getValue());//买入
            List<ChbInvsetAssetInfo> mc =  chbInvestOperationService.qryChbInvsetAssetInfo(param);
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("dealdateFmt", item.getDealdateFmt());//变动日
            dataMap.put("prdInfo", chbInvestPrdInfo);//产品信息
            dataMap.put("assetBuyList", mr);//非标买入
            if(StringUtil.isEmptyList(mr)) {
                dataMap.put("list1", Arrays.asList("新增空"));//
            }
            else {
                dataMap.put("list1", new ArrayList<>());//
            }
            dataMap.put("assetSelllist", mc);//非标到期
            if(StringUtil.isEmptyList(mc)) {
                dataMap.put("list2", Arrays.asList("到期空"));//
            }
            else {
                dataMap.put("list2", new ArrayList<>());//
            }
            mapList.add(dataMap);
            titleList.add(chbInvestPrdInfo.getProdName() + "_理财产品非标投资运作报告" + "_" + item.getDealdateFmt() + ".doc");
        });
        if(mapList.size() == 1) {
            return WordBatchUtil.exportMillCertificateWord(getRequest(), getResponse(), mapList.get(0), srcFilePath,
                    "ChbInvestOperation.ftl", titleList.get(0));
        }
        else {
            String zipname = "理财产品非标投资运作报告_" + DateUtil.format(new Date(), "yyyyMMddHHmmss");
            return WordBatchUtil.exportWordBatch(getRequest(), getResponse(), mapList, titleList, "" +
                    "ChbInvestOperation.ftl", srcFilePath, zipname);
        }
    }
}
