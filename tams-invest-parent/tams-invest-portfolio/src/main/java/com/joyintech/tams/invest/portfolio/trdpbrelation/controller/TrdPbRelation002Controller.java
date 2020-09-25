package com.joyintech.tams.invest.portfolio.trdpbrelation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.portfolio.trdpbrelation.dto.TrdPbRelation001Dto;
import com.joyintech.tams.invest.portfolio.trdpbrelation.service.TrdPbRelation001Service;
import com.joyintech.tams.invest.portfolio.trdpbrelation.service.TrdPbRelation002Service;
/**
 * PB编号管理
 * 〈功能详细描述〉
 * @author caoxue
 * @version 1.0 2019年12月24日
 * @see TrdPbRelation002Controller
 * @since 1.0
 *
 */
@Controller
@PageId("TrdPbRelation002")
@RequestMapping("/invest/portfolio/trdpbrelation/TrdPbRelation002")
public class TrdPbRelation002Controller extends BaseController {
	
    /**
     * PB编号管理
     */
    @Autowired
    private TrdPbRelation002Service trdPbRelation002Service;
    
    /**
     * PB编号管理
     */
    @Autowired
    private TrdPbRelation001Service trdPbRelation001Service;
    
	/**
	 * 界面初始化 {@inheritDoc}
	 */
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		super.setSelectOptionToModel(model, DictCode.PB要素类型);
		String seqno = getParam("seqno");
		String pageModel = getParam("pageModel");
		if(StringUtils.isNotEmpty(seqno)){
			// 设置到model中
            model.addAttribute("seqno", seqno);
		}
		return super.init(model);
	}
	
	/**
	 * 查询PB和产品/子组合关联信息
	 * 
	 * @param parameters
	 * @return
	 */
    @ResponseBody
    @RequestMapping("getTrdPbRelation")
    public List<TrdPbRelation001Dto> getTrdPbRelation() {
    	List<TrdPbRelation001Dto> list = new ArrayList<TrdPbRelation001Dto>();
        if(StringUtil.isNotEmpty(this.getParam("seqno"))){
        	TrdPbRelation001Dto entity =new TrdPbRelation001Dto();
			entity.setSeqno(this.getParam("seqno"));
			TrdPbRelation001Dto resultDto=trdPbRelation001Service.getTrdPbRelation(entity);
			list.add(resultDto);
            return list;
        }
        return list;
    }
	
	/**
	 * 画面点击保存按钮时check
	 *
	 * @param dto
	 *            实体类
	 * @param result
	 * @return check结果
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) TrdPbRelation001Dto dto,
			BindingResult result) {
		return commonCheck(result, dto.getOperateType());
	}
	/**
     * 保存
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) TrdPbRelation001Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
		boolean falg=true;
		//返回信息
		String msg = "处理失败！请联系管理员！";
        if(StringUtil.isNotEmpty(dto.getSeqno())){//修改：先删除原数据
        	trdPbRelation001Service.delete(dto);
        }
		if (!StringUtil.isEmptyList(dto.getSelectedItem())) {
			for (TrdPbRelation001Dto detail : dto.getSelectedItem()) {
				// 循环添加
				int result = trdPbRelation002Service.insert(detail);
				if (result == 0) {
					falg = false;
				}
			}
			if (falg) {
				msg = "处理成功,共保存" + dto.getSelectedItem().size() + "条数据";
			}
		}
        return new AjaxResult(msg);
    }

    /**
     * 查询产品/子组合代码
     * 
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryPrdPortfolioId")
    public Page<SelectOptionDto> queryPrdPortfolioId() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return trdPbRelation002Service.queryPrdPortfolioId(map);
    }
    
    /**
     * 查询产品/子组合名称
     * 
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryPrdPortfolioName")
    public SelectOptionDto queryPrdPortfolioName() {
    	Map<String, Object> map = RequestUtil.getPageParam();
    	return trdPbRelation002Service.queryPrdPortfolioName(map);
    }
}
