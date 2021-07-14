package com.joyintech.tams.invest.entrust.etrnewstock.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.entrust.etrnewstock.dto.EtrNewStockDto;
import com.joyintech.tams.invest.entrust.etrnewstock.service.EtrNewStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**   
 * @title: EtrNewStock001Controller
 * @package com.joyintech.tams.invest.entrust.etrnewstock.controller
 * @description: 股票委托查询
 * @author: junYin
 * @date: 2019-06-04 0:36
*/
@Controller
@PageId("EtrNewStock001")
@RequestMapping("/invest/entrust/etrnewstock/EtrNewStock001")
public class EtrNewStock001Controller extends BaseController {

    /**
     * 股票委托service
     */
    @Autowired
    private EtrNewStockService etrNewStockService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, new DictCode[]{ DictCode.交易方向, DictCode.交易场所, DictCode.申购方式,
            DictCode.委托状态});
        return super.init(model);
    }

    /**
     *@title: selectForPage
     *@description: 股票委托分页查询
     *@author: junYin
     *@date: 2019/06/04 13:57
     *@param: * @param  1
     *@return: com.joyintech.framework.grid.Page<com.joyintech.tams.invest.entrust.etrstock.dto.EtrNewStockDto>
     *@throws:
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<EtrNewStockDto> selectForPage(){
        Page<EtrNewStockDto> page = etrNewStockService.selectForPage(RequestUtil.getPageParam());
        return page;
    }

    /**
     *@title: getentrustId
     *@description: 获取组合代码联想文本框信息
     *@author: junYin
     *@date: 2019/06/04 10:06
     *@param: * @param param 1
     *@return: java.util.List<com.joyintech.framework.select.SuggestDto>
     *@throws:
     */
    @ResponseBody
    @RequestMapping("/getentrustId")
    public List<SuggestDto> getentrustId(String param) throws Exception {
        List<SuggestDto> temp = etrNewStockService.getentrustId(param);
        return temp;
    }
    /**
     *@title: getfinprodId
     *@description: 获取资产代码联想文本框信息
     *@author: junYin
     *@date: 2019/06/04 10:06
     *@param: * @param param 1
     *@return: java.util.List<com.joyintech.framework.select.SuggestDto>
     *@throws:
     */
    @ResponseBody
    @RequestMapping("/getfinprodId")
    public List<SuggestDto> getfinprodId(String param) throws Exception {
        List<SuggestDto> temp = etrNewStockService.getfinprodId(param);
        return temp;
    }
    /**
     *@title: check
     *@description: 按钮点击时的check
     *@author: junYin
     *@date: 2019/06/04 10:07
     *@param: * @param dto 1
     * @param result 2
     *@return: com.joyintech.framework.ajax.AjaxResult
     *@throws:
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) EtrNewStockDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     *@title: recallDelegate
     *@description: 撤回委托
     *@author: junYin
     *@date: 2019/06/04 10:07
     *@param: * @param dto 1
     *@return: com.joyintech.framework.ajax.AjaxResult
     *@throws:
     */
    @ResponseBody
    @RequestMapping("recall")
    public AjaxResult recallDelegate(@RequestBody EtrNewStockDto dto) {
        List<EtrNewStockDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && dtolist.size()>0){
            for(EtrNewStockDto entity : dtolist){
                etrNewStockService.recallDelegate(entity);
            }
            return new AjaxResult("撤销成功");
        }
        return new AjaxResult("撤销失败");
    }
    /**
     *@title: closeDelegate
     *@description: 关闭委托
     *@author: junYin
     *@date: 2019/06/04 10:08
     *@param: * @param dto 1
     *@return: com.joyintech.framework.ajax.AjaxResult
     *@throws:
     */
    @ResponseBody
    @RequestMapping("close")
    public AjaxResult closeDelegate(@RequestBody EtrNewStockDto dto) {
        List<EtrNewStockDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && dtolist.size()>0){
            for(EtrNewStockDto entity : dtolist){
                etrNewStockService.closeDelegate(entity.getEntrustId(), entity.getCloseWay());
            }
            return new AjaxResult("关闭成功");
        }
        return new AjaxResult("关闭失败");
    }

    /**
     *@title: export
     *@description: 文件导出
     *@author: junYin
     *@date: 2019/06/04 10:08
     *@param: * @param  1
     *@return: org.springframework.http.ResponseEntity<byte[]>
     *@throws:
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() throws Exception {
        return etrNewStockService.export(this.getPageId(),RequestUtil.getParameters(getRequest()));
    }
}
