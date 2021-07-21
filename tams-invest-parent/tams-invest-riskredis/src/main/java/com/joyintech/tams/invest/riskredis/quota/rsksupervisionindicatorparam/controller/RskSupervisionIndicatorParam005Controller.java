package com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParam005Dto;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.service.RskSupervisionIndicatorParam001Service;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.service.RskSupervisionIndicatorParam005Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@PageId("RskSupervisionIndicatorParam005")
@RequestMapping("/invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam005")
public class RskSupervisionIndicatorParam005Controller extends BaseController {
	@Autowired
	private RskSupervisionIndicatorParam005Service rskSupervisionIndicatorParam005Service;
	/**
     * 业务标号生成共通
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;
    
    /**
     * 指标参数管理service
     */
    @Autowired
    private RskSupervisionIndicatorParam001Service rskSupervisionIndicatorParamService;

    private static Map<String, String> treeMapping = new HashMap<>();
	static {
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_农_林_牧_渔业.getValue(), "A");
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_采矿业.getValue(), "B");
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_制造业.getValue(), "C");
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_电力_热力_燃气及水生产和供应业.getValue(), "D");
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_建筑业.getValue(), "E");
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_批发和零售业.getValue(), "F");
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_交通运输_仓储和邮政业.getValue(), "G");
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_住宿和餐饮业.getValue(), "H");
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_信息传输_软件和信息技术服务业.getValue(), "I");
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_金融业.getValue(), "J");
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_房地产.getValue(), "K");
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_租赁和商务服务业.getValue(), "L");
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_科学研究和技术服务业.getValue(), "M");
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_水利_环境和公共设施管理业.getValue(), "N");
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_居民服务_修理和其他服务业.getValue(), "O");
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_教育.getValue(), "P");
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_卫生和社会工作.getValue(), "Q");
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_文化_体育和娱乐业.getValue(), "R");
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_公共管理_社会保障和社会组织.getValue(), "S");
		treeMapping.put(BussinessDictCodeListRds.机构所属行业一级分类_国际组织.getValue(), "T");
	}
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		this.setSelectOptionToModel(model, BussinessDictCodeRds.资产类型分类, BussinessDictCodeRds.是否, BussinessDictCodeRds.指标参数分类维度, BussinessDictCodeRds.评级分类,
				BussinessDictCodeRds.日期类型, BussinessDictCodeRds.日期范围类型, BussinessDictCodeRds.所属行业分类, BussinessDictCodeRds.复合模板取值类型);
		List<SelectOptionDto> listFirstIndustry = addIndustry();
		List<SelectOptionDto> listSecondIndustry = addSecondIndustry();
		List<SelectOptionDto> listGrade = addGrade();
		List<SelectOptionDto> listDateRange = addDateRange();
		List<SelectOptionDto> listVariableRange = VariableRange();
		// 所属行业一级分类
		model.addAttribute("FIRST_INDUSTRY", JsonUtils.objectArray2Json(listFirstIndustry.toArray()));
		// 所属行业二级分类
		model.addAttribute("SECOND_INDUSTRY", JsonUtils.objectArray2Json(listSecondIndustry.toArray()));
		// 评级类型明细
		model.addAttribute("BODY_GRADE", JsonUtils.objectArray2Json(listGrade.toArray()));
		// N日内到期取值范围
		model.addAttribute("DATE_RANGE", JsonUtils.objectArray2Json(listDateRange.toArray()));
		// 变量值取值范围
		model.addAttribute("VARIABLE_RANGE", JsonUtils.objectArray2Json(listVariableRange.toArray()));
		model.addAttribute("DATE_RANGE_TYPE_CUSTOM", BussinessDictCodeListRds.日期范围类型_自定义.getValue());
		model.addAttribute("PARA_TEMPLATE_FHMB", BussinessDictCodeListRds.参数模板_复合模板.getValue());
		model.addAttribute("PARA_TEMPLATE_ZCSZ", BussinessDictCodeListRds.参数模板_前N日净资产市值.getValue());
		model.addAttribute("PARA_TEMPLATE_ZCCB", BussinessDictCodeListRds.参数模板_前N日净资产成本.getValue());
		model.addAttribute("PARA_DISPLAY_TYPE_CUSTOM", BussinessDictCodeListRds.指标参数实现类型_自定义.getValue());
		if (StringUtil.isNotEmpty(getParam("workflowId"))&&!WorkFlowUtil.isNotFromToDoList()) {
	       	 String workflowId = getParam("workflowId");
	            model.addAttribute("workflowId", workflowId);
	            RskSupervisionIndicatorParam005Dto entity = rskSupervisionIndicatorParam005Service.getBussinessEntityByWorkflowId(workflowId);
	            model.addAttribute(GlobalConstant.ENTITY, entity);
	        }
	        else if(StringUtil.isNotEmpty(getParam("paraCode"))){
	        	List<RskSupervisionIndicatorParam005Dto> list = rskSupervisionIndicatorParam005Service.selectCustomParam(getParam("paraCode"));
	            if(!StringUtil.isEmptyList(list)){
	            	model.addAttribute("entity",list.get(0));
	            }
	        }else{
	        	/*RskSupervisionIndicatorParam003Dto rskSupervisionIndicatorParamInfo = new RskSupervisionIndicatorParam003Dto();
	        	rskSupervisionIndicatorParamInfo.setValidDate(DateUtil.platDateFromCache());
	        	model.addAttribute("entity",rskSupervisionIndicatorParamInfo);*/
	        }
		return super.init(model);
	}

	/**获取所属行业下拉框树
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @SuppressWarnings("rawtypes")
    @RequestMapping("getIndustryTreeNode")
    public List<TreeNode> getIndustryTreeNode(HttpServletRequest request) {
		List<TreeNode> list = new ArrayList<>();
		String parentId = request.getParameter("id");
        String parentName = request.getParameter("name");
		String parentChecked = request.getParameter("checked");
        String category = request.getParameter("category");
		String initValue = request.getParameter("initValue");
		List<String> initValueList = StringUtil.splitToList(",",initValue);
        if(StringUtil.isNotEmpty(category)&&StringUtil.equals(category, "WIND")){
        	if(null == parentId) {
        		SelectDto selectDto1 = this.getSelectDataFromDataDic(BussinessDictCodeRds.机构所属行业一级分类, null);
        		for(SelectOptionDto optionDto:selectDto1.getOptions()){
        			TreeNode node = new TreeNode();
                    node.setName(optionDto.getText());
                    node.setId(optionDto.getId());
                    node.setParent(true);
					if (initValueList!=null && initValueList.size()>0) {
						for (String item : initValueList) {
							if (StringUtil.equals(optionDto.getId(), item)) {
								node.setChecked(true);
							}
						}
					}
                    list.add(node);
        		}
            }
        	else {
				SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.机构所属行业二级分类, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(optionDto.getId());
					node.setpId(parentId);
					if (initValueList!=null && initValueList.size()>0) {
						for (String item : initValueList) {
							if (StringUtil.equals(optionDto.getId(), item)) {
								node.setChecked(true);
							}
						}
					}else {
						if (StringUtil.equals(parentChecked, "true")) {
							node.setChecked(true);
						}
					}
					if(StringUtil.startsWith(optionDto.getId(), treeMapping.get(parentId))) {
						list.add(node);
					}
				}
			}
        }
		return list;
	}


	public void setNodeCheck(List<String> initValueList,SelectDto selectDto,SelectOptionDto optionDto,TreeNode node,String parentChecked){
		if (initValueList!=null && initValueList.size()>0) {
			for (String item : initValueList) {
				if (StringUtil.equals(selectDto.getCode() + '.' + optionDto.getId(),
					item)) {
					node.setChecked(true);
				}
			}
		}else {
			if (StringUtil.equals(parentChecked, "true")) {
				node.setChecked(true);
			}
		}
	}
	/**获取资产类型
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
    @SuppressWarnings("rawtypes")
    @RequestMapping("getAssetTreeNode")
    public List<TreeNode> getAssetTreeNode(HttpServletRequest request) {
		List<TreeNode> list = new ArrayList<>();
		String parentId = request.getParameter("id");
        String parentName = request.getParameter("name");
        String parentChecked = request.getParameter("checked");
        String assetType = request.getParameter("assetType");
        String initValue = request.getParameter("initValue");
        List<String> initValueList = StringUtil.splitToList(",",initValue);
        if(StringUtil.isNotEmpty(assetType)&&StringUtil.equals(assetType, BussinessDictCodeListRds.资产类型分类_授权分类.getValue())){
        	if(null == parentId) {
        		SelectDto selectDto1 = this.getSelectDataFromDataDic(BussinessDictCodeRds.授权资产一级分类, null);
        		for(SelectOptionDto optionDto:selectDto1.getOptions()){
        			TreeNode node = new TreeNode();
                    node.setName(optionDto.getText());
                    node.setId(selectDto1.getCode()+'.'+optionDto.getId());
                    node.setParent(true);
					setNodeCheck(initValueList,selectDto1,optionDto,node,parentChecked);
                    list.add(node);
        		}
            }
        	else if(StringUtil.equals(parentId, BussinessDictCodeListRds.授权资产一级分类_货币市场类业务.getDictCode().getValue()+'.'+BussinessDictCodeListRds.授权资产一级分类_货币市场类业务.getValue())){
				SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.授权资产_货币市场类业务, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.授权资产一级分类_货币市场类业务.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
			}
        	else if(StringUtil.equals(parentId, BussinessDictCodeListRds.授权资产一级分类_货币市场类业务.getDictCode().getValue()+'.'+BussinessDictCodeListRds.授权资产一级分类_公募证券投资基金.getValue())){
				SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.授权资产_公募证券投资基金, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.授权资产一级分类_货币市场类业务.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
			}
        	else if(StringUtil.equals(parentId, BussinessDictCodeListRds.授权资产一级分类_货币市场类业务.getDictCode().getValue()+'.'+BussinessDictCodeListRds.授权资产一级分类_基金专户及资产管理计划.getValue())){
				SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.授权资产_基金专户及资产管理计划, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.授权资产一级分类_货币市场类业务.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
			}
        	else if(StringUtil.equals(parentId, BussinessDictCodeListRds.授权资产一级分类_货币市场类业务.getDictCode().getValue()+'.'+BussinessDictCodeListRds.授权资产一级分类_权益类资产.getValue())){
				SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.授权资产_权益类资产, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.授权资产一级分类_货币市场类业务.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
			}
        	else if(StringUtil.equals(parentId, BussinessDictCodeListRds.授权资产一级分类_货币市场类业务.getDictCode().getValue()+'.'+BussinessDictCodeListRds.授权资产一级分类_债券类业务.getValue())){
				SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.授权资产_债券类业务, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.授权资产一级分类_货币市场类业务.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
			}
        }else if(StringUtil.isNotEmpty(assetType)&&StringUtil.equals(assetType, BussinessDictCodeListRds.资产类型分类_资管分类.getValue())){
        	if(null == parentId) {
        		SelectDto selectDto1 = this.getSelectDataFromDataDic(BussinessDictCodeRds.资管一级分类, null);
        		for(SelectOptionDto optionDto:selectDto1.getOptions()){
        			TreeNode node = new TreeNode();
                    node.setName(optionDto.getText());
                    node.setId(selectDto1.getCode()+'.'+optionDto.getId());
                    node.setParent(true);
					setNodeCheck(initValueList,selectDto1,optionDto,node,parentChecked);
                    list.add(node);
        		}
            }else if(StringUtil.equals(parentId, BussinessDictCodeListRds.资管_存款类资产.getDictCode().getValue()+'.'+BussinessDictCodeListRds.资管_存款类资产.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.资管_存款类资产, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.资管_存款类资产.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.资管_存款类资产.getDictCode().getValue()+'.'+BussinessDictCodeListRds.资管_债券类资产.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.资管_债券类资产, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.资管_存款类资产.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.资管_存款类资产.getDictCode().getValue()+'.'+BussinessDictCodeListRds.资管_基金类资产.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.资管_基金类资产, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.资管_存款类资产.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.资管_存款类资产.getDictCode().getValue()+'.'+BussinessDictCodeListRds.资管_质押式回购.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.资管_质押式回购, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.资管_存款类资产.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.资管_存款类资产.getDictCode().getValue()+'.'+BussinessDictCodeListRds.资管_计划类资产.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.投资性质, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.资管_存款类资产.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.资管_存款类资产.getDictCode().getValue()+'.'+BussinessDictCodeListRds.资管_项目类资产.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.资管_项目类资产, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.资管_存款类资产.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
        }else if(StringUtil.isNotEmpty(assetType)&&StringUtil.equals(assetType, BussinessDictCodeListRds.资产类型分类_中债分类.getValue())){
        	if(null == parentId) {
        		SelectDto selectDto1 = this.getSelectDataFromDataDic(BussinessDictCodeRds.中债_监管一级分类, null);
        		for(SelectOptionDto optionDto:selectDto1.getOptions()){
        			TreeNode node = new TreeNode();
                    node.setName(optionDto.getText());
                    node.setId(selectDto1.getCode()+'.'+optionDto.getId());
                    if(StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.监管_其他.getValue())
                    		||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.监管_货币市场工具.getValue())
                    				||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.监管_货币市场_负债.getValue())){
                    	node.setParent(false);
                    }else{
                    	node.setParent(true);
                    }
					setNodeCheck(initValueList,selectDto1,optionDto,node,parentChecked);
                    list.add(node);
        		}
            }else if(StringUtil.equals(parentId, BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+BussinessDictCodeListRds.监管_现金及银行存款.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.监管_现金及银行存款, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+BussinessDictCodeListRds.监管_同业存单.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.监管_同业存单, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+BussinessDictCodeListRds.监管_拆放同业及买入返售.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.监管_拆放同业及买入返售, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+BussinessDictCodeListRds.监管_债券.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.监管_债券, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+BussinessDictCodeListRds.监管_理财直接融资工具.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.监管_理财直接融资工具, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+BussinessDictCodeListRds.监管_新增可投资资产.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.监管_新增可投资资产, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+BussinessDictCodeListRds.监管_非标准化债权类资产.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.监管_非标准化债权类资产, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+BussinessDictCodeListRds.监管_金融衍生品.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.监管_金融衍生品, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+BussinessDictCodeListRds.监管_代客境外理财投资QDII.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.监管_代客境外理财投资QDII, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+BussinessDictCodeListRds.监管_商品类资产.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.监管_商品类资产, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+BussinessDictCodeListRds.监管_另类资产.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.监管_另类资产, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+BussinessDictCodeListRds.监管_公募基金.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.监管_公募基金, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+BussinessDictCodeListRds.监管_私募基金.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.监管_私募基金, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+BussinessDictCodeListRds.监管_资产管理产品.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.监管_资产管理产品, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+BussinessDictCodeListRds.监管_委外投资_协议方式.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.监管_委外投资_协议方式, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
            else if(StringUtil.equals(parentId, BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+BussinessDictCodeListRds.监管_同业拆入及卖出回购.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.监管_同业拆入及卖出回购, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }else if(StringUtil.equals(parentId, BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+BussinessDictCodeListRds.监管_权益类资产.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.监管_权益类资产, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.监管_现金及银行存款.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
				}
            }
        }else if(StringUtil.isNotEmpty(assetType)&&StringUtil.equals(assetType, BussinessDictCodeListRds.资产类型分类_债券wind分类.getValue())){
        	if(null == parentId) {
        		SelectDto selectDto1 = this.getSelectDataFromDataDic(BussinessDictCodeRds.WIND一级分类_含债券一级分类等, null);
        		for(SelectOptionDto optionDto:selectDto1.getOptions()){
        			TreeNode node = new TreeNode();
                    node.setName(optionDto.getText());
                    node.setId(selectDto1.getCode()+'.'+optionDto.getId());
                    if(StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_国债.getValue())
                    		||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_地方政府债.getValue())
                    		||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_央行票据.getValue())
                    		||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_国际机构债.getValue())
                    		||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_可转债.getValue())
                    		||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_可交换债.getValue())
                    		||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_可分离转债存债.getValue())
                    		||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_优先股.getValue())
                    		||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_同业存单.getValue())
                    		||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_定向工具.getValue())
                    		||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_资产支持证券.getValue())){
                    	node.setParent(false);
                    }else{
                    	node.setParent(true);
                    }
					setNodeCheck(initValueList,selectDto1,optionDto,node,parentChecked);
                    list.add(node);
        		}
            }else if(StringUtil.equals(parentId, BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_国债.getDictCode().getValue()+'.'+BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_金融债.getValue())){
            	SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.WIND二级分类_含债券二级分类等, null);
				for(SelectOptionDto optionDto: selectDto.getOptions()){
					if(StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND二级分类_含债券二级分类等_政策银行债.getValue())
							||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND二级分类_含债券二级分类等_商业银行债.getValue())
							||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND二级分类_含债券二级分类等_商业银行次级债券.getValue())
							||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND二级分类_含债券二级分类等_保险公司债.getValue())
							||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND二级分类_含债券二级分类等_证券公司债.getValue())
							||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND二级分类_含债券二级分类等_证券公司短期融资券.getValue())
							||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND二级分类_含债券二级分类等_其它金融机构债.getValue())){
						TreeNode node = new TreeNode();
						node.setName(optionDto.getText());
						node.setId(selectDto.getCode()+'.'+optionDto.getId());
						node.setpId(BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_金融债.getDictCode().getValue()+'.'+parentId);
						setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
						list.add(node);
					}
					
				}
            }else if(StringUtil.equals(parentId, BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_国债.getDictCode().getValue()+'.'+BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_企业债.getValue())){
        		SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.WIND二级分类_含债券二级分类等, null);
			    for(SelectOptionDto optionDto: selectDto.getOptions()){
			    	if(StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND二级分类_含债券二级分类等_一般企业债.getValue())
			    			||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND二级分类_含债券二级分类等_集合企业债.getValue())){
					TreeNode node = new TreeNode();
					node.setName(optionDto.getText());
					node.setId(selectDto.getCode()+'.'+optionDto.getId());
					node.setpId(BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_企业债.getDictCode().getValue()+'.'+parentId);
					setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
					list.add(node);
			    	}
			    }
			 }else if(StringUtil.equals(parentId, BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_国债.getDictCode().getValue()+'.'+BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_公司债.getValue())){
				 SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.WIND二级分类_含债券二级分类等, null);
		         for(SelectOptionDto optionDto: selectDto.getOptions()){
		        	 if(StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND二级分类_含债券二级分类等_一般公司债.getValue())
					         ||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND二级分类_含债券二级分类等_私募债.getValue())){
				     TreeNode node = new TreeNode();
				     node.setName(optionDto.getText());
				     node.setId(selectDto.getCode()+'.'+optionDto.getId());
				     node.setpId(BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_公司债.getDictCode().getValue()+'.'+parentId);
				     setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
				     list.add(node);
				     }
		         }
		      }else if(StringUtil.equals(parentId, BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_国债.getDictCode().getValue()+'.'+BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_中期票据.getValue())){
		    	  SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.WIND二级分类_含债券二级分类等, null);
		    	  for(SelectOptionDto optionDto: selectDto.getOptions()){
		    		  if(StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND二级分类_含债券二级分类等_一般中期票据.getValue())
		    				  ||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND二级分类_含债券二级分类等_集合票据.getValue())){
		    			  TreeNode node = new TreeNode();
		    			  node.setName(optionDto.getText());
		    			  node.setId(selectDto.getCode()+'.'+optionDto.getId());
		    			  node.setpId(BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_中期票据.getDictCode().getValue()+'.'+parentId);
		    			  setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
		    			  list.add(node);
		    		  }
		    	  }
		      }else if(StringUtil.equals(parentId, BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_国债.getDictCode().getValue()+'.'+BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_短期融资券.getValue())){
		    	  SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.WIND二级分类_含债券二级分类等, null);
		    	  for(SelectOptionDto optionDto: selectDto.getOptions()){
		    		  if(StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND二级分类_含债券二级分类等_超短期融资债券.getValue())
		    				  ||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND二级分类_含债券二级分类等_一般短期融资券.getValue())){
		    			  TreeNode node = new TreeNode();
		    			  node.setName(optionDto.getText());
		    			  node.setId(selectDto.getCode()+'.'+optionDto.getId());
		    			  node.setpId(BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_短期融资券.getDictCode().getValue()+'.'+parentId);
		    			  setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
		    			  list.add(node);
		    		  }
		    	  }
		      }else if(StringUtil.equals(parentId, BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_国债.getDictCode().getValue()+'.'+BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_政府支持机构债.getValue())){
		    	  SelectDto selectDto = this.getSelectDataFromDataDic(BussinessDictCodeRds.WIND二级分类_含债券二级分类等, null);
		    	  for(SelectOptionDto optionDto: selectDto.getOptions()){
		    		  if(StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND二级分类_含债券二级分类等_证监会主管ABS.getValue())
		    				  ||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND二级分类_含债券二级分类等_银监会主管ABS.getValue())
		    				  ||StringUtil.equals(optionDto.getId(), BussinessDictCodeListRds.WIND二级分类_含债券二级分类等_交易商协会ABN.getValue())){
		    			  TreeNode node = new TreeNode();
		    			  node.setName(optionDto.getText());
		    			  node.setId(selectDto.getCode()+'.'+optionDto.getId());
		    			  node.setpId(BussinessDictCodeListRds.WIND一级分类_含债券一级分类等_政府支持机构债.getDictCode().getValue()+'.'+parentId);
		    			  setNodeCheck(initValueList,selectDto,optionDto,node,parentChecked);
		    			  list.add(node);
		    		  }
		    	  }
		      }
        }
		return list;
	}
	private List<SelectOptionDto> VariableRange() {
		List<SelectOptionDto> listVariableRange = new ArrayList<SelectOptionDto>();
		SelectOptionDto dto1 = new SelectOptionDto();
		dto1.setId(BussinessDictCodeListRds.日期范围类型_上一日.getValue());
        dto1.setText(BussinessDictCodeListRds.日期范围类型_上一日.getCodeName());
        listVariableRange.add(dto1);
        SelectOptionDto dto2 = new SelectOptionDto();
        dto2.setId(BussinessDictCodeListRds.日期范围类型_上月末.getValue());
        dto2.setText(BussinessDictCodeListRds.日期范围类型_上月末.getCodeName());
        listVariableRange.add(dto2);
        SelectOptionDto dto3 = new SelectOptionDto();
        dto3.setId(BussinessDictCodeListRds.日期范围类型_上季度末.getValue());
        dto3.setText(BussinessDictCodeListRds.日期范围类型_上季度末.getCodeName());
        listVariableRange.add(dto3);
        SelectOptionDto dto4 = new SelectOptionDto();
        dto4.setId(BussinessDictCodeListRds.日期范围类型_自定义.getValue());
        dto4.setText(BussinessDictCodeListRds.日期范围类型_自定义.getCodeName());
        listVariableRange.add(dto4);
		return listVariableRange;
	}

	private List<SelectOptionDto> addDateRange() {
		List<SelectOptionDto> listDateRange = new ArrayList<SelectOptionDto>();
		SelectOptionDto dto1 = new SelectOptionDto();
		dto1.setId(BussinessDictCodeListRds.日期范围类型_一年内.getValue());
        dto1.setText(BussinessDictCodeListRds.日期范围类型_一年内.getCodeName());
        listDateRange.add(dto1);
        SelectOptionDto dto2 = new SelectOptionDto();
        dto2.setId(BussinessDictCodeListRds.日期范围类型_半年内.getValue());
        dto2.setText(BussinessDictCodeListRds.日期范围类型_半年内.getCodeName());
        listDateRange.add(dto2);
        SelectOptionDto dto3 = new SelectOptionDto();
        dto3.setId(BussinessDictCodeListRds.日期范围类型_一季度内.getValue());
        dto3.setText(BussinessDictCodeListRds.日期范围类型_一季度内.getCodeName());
        listDateRange.add(dto3);
        SelectOptionDto dto4 = new SelectOptionDto();
        dto4.setId(BussinessDictCodeListRds.日期范围类型_一月内.getValue());
        dto4.setText(BussinessDictCodeListRds.日期范围类型_一月内.getCodeName());
        listDateRange.add(dto4);
        SelectOptionDto dto5 = new SelectOptionDto();
        dto5.setId(BussinessDictCodeListRds.日期范围类型_自定义.getValue());
        dto5.setText(BussinessDictCodeListRds.日期范围类型_自定义.getCodeName());
        listDateRange.add(dto5);
		return listDateRange;
	}

	private List<SelectOptionDto> addGrade() {
		List<SelectOptionDto> listGrade = new ArrayList<SelectOptionDto>();
		SelectDto selectDto1 = this.getSelectDataFromDataDic(BussinessDictCodeRds.主体评级_中债, null);
		for (SelectOptionDto dto : selectDto1.getOptions()) {
			dto.setId(dto.getCol() + GlobalConstant.POINT + dto.getId());
			listGrade.add(dto);
		}
		return listGrade;
	}

	private List<SelectOptionDto> addSecondIndustry() {
		// 获取行业二级分类枚举
		List<SelectOptionDto> listSecondIndustry = new ArrayList<SelectOptionDto>();

		SelectDto selectDto1 = this.getSelectDataFromDataDic(BussinessDictCodeRds.机构所属行业二级分类, null);
		for (SelectOptionDto dto : selectDto1.getOptions()) {
			dto.setId(dto.getCol() + GlobalConstant.POINT + dto.getId());
			listSecondIndustry.add(dto);
		}
		return listSecondIndustry;
	}

	private List<SelectOptionDto> addIndustry() {
		// 获取行业一级分类枚举
		List<SelectOptionDto> listIndustry = new ArrayList<SelectOptionDto>();

		SelectDto selectDto1 = this.getSelectDataFromDataDic(BussinessDictCodeRds.机构所属行业一级分类, null);
		for (SelectOptionDto dto : selectDto1.getOptions()) {
			dto.setId(dto.getCol() + GlobalConstant.POINT + dto.getId());
			listIndustry.add(dto);
		}
		return listIndustry;
	}
	/**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) RskSupervisionIndicatorParam005Dto dto, BindingResult result ) {
        return commonCheck(result, dto.getOperateType());
    }
    
    @ResponseBody
    @RequestMapping("checkEdit")
    public AjaxResult checkEdit(@RequestBody @Validated({
        ValidateGroupEdit.class}) RskSupervisionIndicatorParam005Dto dto, BindingResult result ) {
    	if(rskSupervisionIndicatorParamService.isConfigure(dto.getParaCode())>0){
    		List<JoyinMessage> joyinMessages = new ArrayList<>();
    		JoyinMessage ms = new JoyinMessage(getMessage("RSKRDS_W_0064"), MessageType.确认消息);
    		joyinMessages.add(ms);
    		AjaxResult wr = commonCheck(result, dto.getOperateType());
            wr.setJoyinMessages(joyinMessages);
            return wr;
    	}
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 保存操作
     *
     * @param dto dto
     * @return 结果
     * @see
     * @since 1.0
     */
    @DTXTransaction
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody RskSupervisionIndicatorParam005Dto dto, BindingResult br)
        throws Exception{
    	if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 监督指标代码生成
        BussinessCodeParameter parameter = new BussinessCodeParameter();
		parameter.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_自定义参数管理));
        ProcessParameter<RskSupervisionIndicatorParam005Dto> processParameter = new ProcessParameter<RskSupervisionIndicatorParam005Dto>();
        WorkFlowParameter<RskSupervisionIndicatorParam005Dto> params = new WorkFlowParameter<RskSupervisionIndicatorParam005Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
        	BussinessCodeParameter param = new BussinessCodeParameter();
			param.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_自定义参数管理));
            String code = baseGetBusinessId != null ? baseGetBusinessId.buildBussinessCode(
              param) : IDGenerator.generate();
            dto.setParaCode(code);
        }
        
        // 参数代码作为业务编号
        String businessCode = dto.getParaCode();
        params.setBusinessCode(businessCode);
        params.setBusinessType(BussinessDictCodeListRds.共通业务类型_自定义参数管理.getValue());
        
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment("指标参数",
                GlobalConstant.OPEN_PARENTHESIS, businessCode,GlobalConstant.SPLIT, entity.getParaName(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = rskSupervisionIndicatorParam005Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    /**
     * 删除
     * @param dto
     * @return
     * @throws Exception
     */
    @DTXTransaction
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) RskSupervisionIndicatorParam005Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<RskSupervisionIndicatorParam005Dto> processParameter = new ProcessParameter<RskSupervisionIndicatorParam005Dto>();
        WorkFlowParameter<RskSupervisionIndicatorParam005Dto> params = new WorkFlowParameter<RskSupervisionIndicatorParam005Dto>();
        params.setPageId("RskSupervisionIndicatorParam005");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getParaCode());
        params.setBusinessType(BussinessDictCodeListRds.共通业务类型_自定义参数管理.getValue());
     // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("RskSupervisionIndicatorParam002.paraCode"),
                GlobalConstant.OPEN_PARENTHESIS, dto.getParaCode(), GlobalConstant.CLOSE_PARENTHESIS,
                getMessage("RskSupervisionIndicatorParam003.paraName"), GlobalConstant.OPEN_PARENTHESIS,
                entity.getParaName(), GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = rskSupervisionIndicatorParam005Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    /**
     * 获取模板变量值
     * @return
     */
    @ResponseBody
    @RequestMapping("/getModelValue")
    public String getModelValue(@RequestParam(value="modelCode") String modelCode){
    	return rskSupervisionIndicatorParam005Service.getModelValue(modelCode);
    }
}
