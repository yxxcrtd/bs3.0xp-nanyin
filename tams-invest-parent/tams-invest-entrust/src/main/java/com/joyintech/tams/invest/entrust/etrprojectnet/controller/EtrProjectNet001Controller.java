package com.joyintech.tams.invest.entrust.etrprojectnet.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.entrust.etrprojectnet.dto.EtrProjectNetDto;
import com.joyintech.tams.invest.entrust.etrprojectnet.service.EtrProjectNetService;
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
import java.util.Map;

/**
 *  权益类资产委托
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月8日
 * @see EtrProjectNet001Controller
 * @since 1.0
 */
@Controller
@PageId("EtrProjectNet001")
@RequestMapping("/invest/entrust/etrprojectnet/EtrProjectNet001")
public class EtrProjectNet001Controller extends BaseController {
    /**
     * service
     */
    @Autowired
    private EtrProjectNetService etrProjectNetService;
    /**
     * 页面初始化
     */
    @Override
    public String init(Model model) {
        /* add by zhangpuyu 2019-8-13 start*/
        this.setSelectOptionToModel(model, DictCode.行内资产二级分类,DictCode.行内资产三级分类,DictCode.行内资产四级分类);
        /* add by zhangpuyu 2019-8-13 end*/
        this.setSelectOptionToModel(model, DictCode.交易方向);
        this.setSelectOptionToModel(model, DictCode.币种);
        this.setSelectOptionToModel(model, DictCode.计划类资产一级分类);
        this.setSelectOptionToModel(model, DictCode.委托状态);
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return super.init(model);
    }

    /**
     *
     * 权益类委托分页查询
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<EtrProjectNetDto> selectForPage(){
        Page<EtrProjectNetDto> page = etrProjectNetService.selectForPage(
                RequestUtil.getPageParam());
        return page;
    }

    /**
     *
     * 获取组合代码联想文本框信息
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getentrustId")
    public List<SuggestDto> getentrustId(String param)
            throws Exception {
        List<SuggestDto> temp = etrProjectNetService.getentrustId(param);
        return temp;
    }
    /**
     *
     * 获取资产代码联想文本框信息
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getfinprodId")
    public List<SuggestDto> getfinprodId(String param)
            throws Exception {
        List<SuggestDto> temp = etrProjectNetService.getfinprodId(param);
        return temp;
    }
    /**
     * 按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) EtrProjectNetDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 撤回委托 <功能详细描述>
     *
     * @param dto 实体类
     * @return boolean
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("recall")
    public AjaxResult recallDelegate(@RequestBody EtrProjectNetDto dto)
    // modify by yushilong start
            throws Exception {
        // modify by yushilong end
        List<EtrProjectNetDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && dtolist.size()>0){
            for(EtrProjectNetDto entity : dtolist){
                etrProjectNetService.recallDelegate(entity);
            }
//            EntrustRdsCommonAop.doListPreOccupy(dtolist, DictCodeListExt3.平安资产类型_权益类资产.getValue(),
//                    BussinessDictCodeListRds.接口系统ID_预占释放批量接口.getValue(), false, null);
            return new AjaxResult(this.getMessage("ETR_X_001"));
        }
        return new AjaxResult(this.getMessage("ETR_X_002"));
    }
    /**
     * 关闭委托
     *
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("close")
    public AjaxResult closeDelegate(@RequestBody EtrProjectNetDto dto)
            throws Exception {
        List<EtrProjectNetDto> dtolist = dto.getSelectedItem();
        if(dtolist != null && dtolist.size()>0){
            for(EtrProjectNetDto entity : dtolist){
                etrProjectNetService.closeDelegate(entity.getEntrustId(), entity.getCloseWay());
            }
//            // add by zhangrui1 批量委托关闭 start
//            // 若预占下没有实占则预占信息清除，否则预占信息保留，撤销交易时可恢复原状态
//            List<String> entrustIdList = dtolist.stream().map(
//                EtrProjectNetDto::getEntrustId).collect(Collectors.toList());
//            EntrustCompleteUtil.doComplete(entrustIdList);
//            // add by zhangrui1 批量委托关闭 end
            return new AjaxResult(this.getMessage("ETR_X_003"));
        }
        return new AjaxResult(this.getMessage("ETR_X_004"));
    }

    /**
     *
     * 删除投资委托
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult deleteDelegate(@RequestBody EtrProjectNetDto dto)
            throws Exception {
        List<EtrProjectNetDto> list = dto.getSelectedItem();
        if(list!=null && list.size()>0){
            for(EtrProjectNetDto entity : list){
                String entrustId = entity.getEntrustId();
                etrProjectNetService.deleteDelegate(entrustId);
            }
//            EntrustRdsCommonAop.doListPreOccupy(list, DictCodeListExt3.平安资产类型_权益类资产.getValue(),
//                    BussinessDictCodeListRds.接口系统ID_预占释放批量接口.getValue(), false, null);
            return new AjaxResult("删除成功");
        }
        return new AjaxResult("删除失败");
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
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        param.put("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return etrProjectNetService.export(this.getPageId(),
                param);
    }
}
