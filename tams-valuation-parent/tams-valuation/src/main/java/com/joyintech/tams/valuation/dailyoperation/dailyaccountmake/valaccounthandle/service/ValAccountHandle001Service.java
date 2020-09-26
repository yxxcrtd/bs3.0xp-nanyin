package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.batch.batchprocess.dto.BatchTaskDto;
import com.joyintech.tams.common.base.dao.BokBooksetMapper;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.dto.ClassForStringLIst;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dao.ValAccountHandle001Mapper;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dto.ValAccountHandle001Dto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dto.ValAccountHandle002Dto;

@Service
@Transactional(rollbackFor = Exception.class)
public class ValAccountHandle001Service extends CommonExportService<ValAccountHandle001Dto>{
	
	@Autowired
	private ValAccountHandle001Mapper valAccountHandle001Mapper;
   
	/** modify by dongjun on 20181207 start **/
	@Autowired
	private BokBooksetMapper bokBooksetMapper;
	
    public List<ValAccountHandle001Dto> getInterGroup(Map<String,Object> params){
		
		return valAccountHandle001Mapper.getInterGroup(params);
	}
	
	public List<SelectOptionDto> getInterGroupCode(Map<String,Object> params){
		
		return valAccountHandle001Mapper.getInterGroupCode(params);
	}
	
	
	
	/**
     * 封装参数
     * 
     * @param listdto 接口任务
     * @param startDate 开始日
     * @param endDate 结束日
     * @return urlParams
     * @see
     * @since 1.0
     */
    public Map<String, String> getParm(String datas, String startDate,
                                       String endDate) {
        Map<String, String> urlParams = new HashMap<>();

        String date = "";
        if(datas.length()>0){
        	date=datas.substring(0, datas.length()-1);
        }
        
        urlParams.put(InterfCommonConstant.接口_运行接口名称, date);
        urlParams.put(InterfCommonConstant.接口_对接系统ID, DictCodeInterfList.接口对接系统ID_资管.getValue());
        urlParams.put(InterfCommonConstant.接口_发送接收, DictCodeInterfList.接口发送接收状态_接收.getValue());
        if (DateUtil.isSameDate(DateUtil.parse(startDate), DateUtil.parse(endDate))) {
            urlParams.put(InterfCommonConstant.接口_接口时间, startDate);
        }
        else {
            urlParams.put(InterfCommonConstant.接口_开始日, startDate);
            urlParams.put(InterfCommonConstant.接口_结束日, endDate);
        }
        return urlParams;
    }
    @SuppressWarnings("rawtypes")
    public List<TreeNode> getTreeNodeList(Map<String,Object> params){
//        List<ValAccountHandle001Dto> list=valAccountHandle001Mapper.findAllTask(params);
//        List<TreeNode> treeList = new ArrayList<TreeNode>();
//        TreeNode node=null;
//    	for (ValAccountHandle001Dto dto : list) {
//    		node = new TreeNode();
//            node.setId(dto.getTaskGrpId());
//            node.setName(dto.getTaskGrpName());
//            node.setpId(dto.getpTaskGrpId());
//            treeList.add(node);
//		}
//    	int i = 3;
//    	if("InterfData".equals(params.get("taskGrpType"))){
//    		i = 2;
//    	}
//        if("task".equals(params.get("taskGrpType"))){
//            i = 2;
//        }
//    	if(treeList.size()<i){
//    		return new ArrayList<TreeNode>();
//    	}
//    	return treeList;
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAccountHandle001/findAllTask",
                params, TreeNode.class);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getBookSetId(Map<String, Object> map) {
        List<SelectOptionDto> listSelectDto=new ArrayList<SelectOptionDto>();
        map.put("bookSetType", DictCodeList.账套类别_理财账套.getValue());
        map.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
        List<BokBookset> listBookset = valAccountHandle001Mapper.getBookSetId(map);
        listBookset.forEach(item -> {
            SelectOptionDto dto=new SelectOptionDto();
            dto.setId(item.getBooksetId());
            dto.setText(item.getBooksetName());
            listSelectDto.add(dto);
        });
        return listSelectDto;
    }
    
    /**
     * 分页下拉框（账套信息）
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBookSetId2(Map<String, Object> map) {
//        map.put("bookSetType", DictCodeList.账套类别_理财账套.getValue());
//        map.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
//        return super.queryForPageForSelectReturn(()->valAccountHandle001Mapper.getBookSetId2(map));
    	return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAccountHandle001/getBookSetId2",
                map, SelectOptionDto.class);
    }
 
    /**
     * 分页下拉框（产品信息）
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getProdId(Map<String, Object> map) {
//        map.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
//    	map.put("booksetType", DictCodeList.账套类别_理财账套.getValue());
//        return super.queryForPageForSelectReturn(()->valAccountHandle001Mapper.getProdId(map));
    	return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValTransactionDataImport001/getProdId",
                map, SelectOptionDto.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    public List<ValAccountHandle001Dto> findLogPage(Map<String, Object> map) throws Exception {
        
//        return valAccountHandle001Mapper.selectBatch(map);
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAccountHandle001/findLogPage",
                map, ValAccountHandle001Dto.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public String checkBokkSetDate(Map<String, Object> map) {
//        String msg="";
//        List<BokBooksetDate> list=valAccountHandle001Mapper.checkBokkSetDate(map);
//        if(list.size()>1){
//            msg="-1";
//        }
//        if(list.size()==1){
//            if(StringUtil.isNotEmpty(list.get(0).getBooksetDate())){
//                msg=DateUtil.format(list.get(0).getBooksetDate(), "yyyy-MM-dd");
//            }
//        }
//        return msg;
    	return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAccountHandle001/checkBokkSetDate",
                map, String.class);
    }

    /**
     * 估值表账套日期
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    /** modify by dongjun on 20181207 start ***/
    public String checkBokkSetDateForVluTab(Map<String, Object> map) {
        String msg="";
        String booksetId = (String)map.get("booksetId");
        String[] booksetIdArr = null;
        if(StringUtil.isNotEmpty(booksetId)){
            booksetIdArr = booksetId.split(",");
        }
        map.put("booksetIdArr", booksetIdArr);
        List<BokBooksetDate> list=valAccountHandle001Mapper.checkBokkSetDate(map);
        if(list.size()>1){
            msg="-1";
        }
        if(list.size()==1){
            if(StringUtil.isNotEmpty(list.get(0).getBooksetDate())){
                BokBookset bokBookset = bokBooksetMapper.selectByPrimaryKey(booksetIdArr==null?null:booksetIdArr[0]);
                if(DateUtil.compareDate(list.get(0).getBooksetDate(), bokBookset.getStartDate()) == 0){
                    msg=DateUtil.format(list.get(0).getBooksetDate(), "yyyy-MM-dd");
                }else {
                    msg=DateUtil.format(DateUtil.addDay(list.get(0).getBooksetDate(), -1), "yyyy-MM-dd");
                }           
            }
        }
        return msg;
    }
    /** modify by dongjun on 20181207 end ***/
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    public List<BatchTaskDto> getBatchTaskid(Map<String, Object> map) {
//        return valAccountHandle001Mapper.getBatchTaskid(map);
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAccountHandle001/getBatchTaskid",
                map, BatchTaskDto.class);
    }

    /**
     * 校验当前产品是否存在在途跑批业务
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    public int checkExecute(Map<String, Object> map) {
//    	String prodGroupType = String.valueOf(map.get("prodGroupType"));
//        String booksetId = (String)map.get("booksetId");
//        String[] booksetIdArr = null;
//        if(StringUtil.isNotEmpty(booksetId)){
//        	if (GlobalConstant.BATCH_EXECUTE_TYPE_GROUP.equals(prodGroupType)) {
//        		// 账套组
//        		String[] prodGroupSeqNos = StringUtil.split(booksetId, ",");
//        		booksetIdArr = findAllProdbyGroup(prodGroupSeqNos).toArray(new String[0]);
//        	} else {
//        		// 账套
//        		booksetIdArr = booksetId.split(",");
//        	}
//        }
//        map.put("booksetIdArr", booksetIdArr);
//        return valAccountHandle001Mapper.checkExecute(map);
    	return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAccountHandle001/checkExecute",
                map, int.class);
    }


    /**
     * 获取账套到期日
     * @param map
     * @return
     */
    public List<String> getMdateForBookSet(Map<String, Object> map) {
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAccountHandle001/getMdateForBookSet",
                map, String.class);
    }


   
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param 债券投资目的对应的估值方案检查
     * @return List<ValAccountHandle002Dto>
     * @throws ParseException 
     * @see
     * @since 1.0
     */
//    @SuppressWarnings("unchecked")
	public List<ValAccountHandle002Dto> checkBokPosition(Map<String,Object> map) throws Exception{
//    	List<String> list = (List<String>) map.get("prodList");
//    	String sysDate = (String)map.get("endDate");
//    	User user = SecurityUtil.getLoginUser();
//        
//        Map<String,Object> cmap = new HashMap<String,Object>();
//        cmap.put("list", list);
//        cmap.put("sysDate", sysDate);
//        // 删除批前检查日志
//        valAccountHandle001Mapper.deleteLogInfo(cmap);
//    	//获取处理结果数据
//    	List<ValAccountHandle002Dto> handelList=valAccountHandle001Mapper.selectBokPosition(cmap);
//    	//定义返回结果
//    	List<ValAccountHandle002Dto> resultListTwo =new ArrayList<ValAccountHandle002Dto>();
//    	Map<String,ValAccountHandle002Dto> reMap=new HashMap<String,ValAccountHandle002Dto>();
//    	//循环处理结果
//    	for(ValAccountHandle002Dto dto:handelList){
//    		//调用共通查询投资目的
//    		InvAimDto invAimDto=finProductCommonBatchService.searchInvAim(dto.getHappenDate(), dto.getBooksetId(), DictCodeListExt1.金融产品类型_债券.getValue(), dto.getBokDetailId(), dto.getInvAim());
//    		if(invAimDto!=null){
//    			if(invAimDto.getSchemeCnt()== null || invAimDto.getSchemeCnt() == 0){
//        			ValAccountHandle002Dto valAccountHandle002Dto=new ValAccountHandle002Dto();
//        			valAccountHandle002Dto.setSeqNo(UUID.randomUUID().toString().replaceAll("-", ""));
//        			//批检类型="手工检查"
//        			valAccountHandle002Dto.setCheckType("手工检查");
//        			//账套代码=DTO.账套代码
//        			valAccountHandle002Dto.setBooksetId(dto.getBooksetId());
//        			//检查日期=参数
//        			DateFormat dformat=new SimpleDateFormat("yyyy-MM-dd");
//        			valAccountHandle002Dto.setCheckDate(dformat.parse(sysDate));
//        			SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd");
//        			String pageDate=format.format(valAccountHandle002Dto.getCheckDate());
//        			//显示日期
//        			valAccountHandle002Dto.setPageDate(pageDate);
//        			//账套日期=DTO.账套日期
//        			valAccountHandle002Dto.setBooksetDate(dformat.parse(sysDate));
//        			//账套页面显示日期
//        			valAccountHandle002Dto.setBookPDate(sysDate);
//        			valAccountHandle002Dto.setBookDate(dto.getBookDate());
//        			valAccountHandle002Dto.setBooksetDate(dto.getBooksetDate());
//        			//日志信息=数据字典.金融产品类型.债券 + DTO.业务明细代码 +"：投资目的（" + DTO.投资目的+ "）" + "不存在估值方案"
//        			if(dto.getInvAim().equals("AC")){
//        				valAccountHandle002Dto.setLogInfo(DictCodeListExt1.金融产品类型_债券.getCodeName()+dto.getBokDetailId()+":投资目的("+"以摊余成本计量（AC）"+")"+"不存在估值方案");
//        			}else if(dto.getInvAim().equals("FVOCI")){
//        				valAccountHandle002Dto.setLogInfo(DictCodeListExt1.金融产品类型_债券.getCodeName()+dto.getBokDetailId()+":投资目的("+"以公允价值计量且其变动计入其他综合收益（FVOCI）"+")"+"不存在估值方案");
//        			}else if(dto.getInvAim().equals("FVTPL")){
//        				valAccountHandle002Dto.setLogInfo(DictCodeListExt1.金融产品类型_债券.getCodeName()+dto.getBokDetailId()+":投资目的("+"以公允价值计量且其变动计入当期损益（FVTPL）"+")"+"不存在估值方案");
//        			}
//        			valAccountHandle002Dto.setCreateUser(user.getUserName());
//        			valAccountHandle002Dto.setCreateTime(new Date());
//        			valAccountHandle002Dto.setCreateDept(user.getDeptCode());
//        			valAccountHandle002Dto.setUpdateUser(user.getUserName());
//        			valAccountHandle002Dto.setUpdateTime(new Date());
//        			
//        			reMap.put(valAccountHandle002Dto.getBooksetId() + valAccountHandle002Dto.getLogInfo(), valAccountHandle002Dto);
//        			//批处理检查信息入库
//        			valAccountHandle001Mapper.insertLogInfo(valAccountHandle002Dto);
//        		}
//    		}
//    		
//    	}
//    	
//    	//遍历map
//    	for(ValAccountHandle002Dto dto:reMap.values()){
//    		resultListTwo.add(dto);
//    	}
//    	 return resultListTwo;
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAccountHandle001/checkBokPosition",
                map, ValAccountHandle002Dto.class);
    }
    
	/**
     * <一句话功能简述>
     * <功能详细描述>
     * @param 查询批检信息表是否有系统平台日的信息
     * @return int
     * @see
     * @since 1.0
     */
    public int bokCheckBefore(Map<String,Object> map){
//    	//获取前台参数
//    	String booksetId=(String)map.get("booksetId");
//    	String sysDate=(String)map.get("endDate");
//    	String[] booksetIdArray = null;
//    	List<String> list=new ArrayList<String>();
//        if(StringUtil.isNotEmpty(booksetId)){
//        	booksetIdArray = booksetId.split(",");
//        }
//        if(booksetIdArray!=null){
//        	list=Arrays.asList(booksetIdArray);
//        }
//        Map<String,Object> cmap = new HashMap<String,Object>();
//        cmap.put("list", list);
//        cmap.put("sysDate", sysDate);
//    
//    	//判断批检日志表是否有今天的数据
//    	int count=valAccountHandle001Mapper.selectBokCheckInfoCount(cmap);
//    
//    return  count;
    	return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAccountHandle001/bokCheckBefore",
                map, int.class);
    	
    }

    @DataSource(DataSourceType.OTHER)
	public Page<SelectOptionDto> getBooksetIdByGrp(Map<String, Object> map) {
//        return super.queryForPageForSelectReturn(()->valAccountHandle001Mapper.selectBooksetIdByGrp(map));
    	return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAccountHandle001/getBooksetIdByGrp",
                map, SelectOptionDto.class);
    }
    
    @DataSource(DataSourceType.OTHER)
    public List<String> findAllProdbyGroup(String[] prodGroupSeqNos) {
//		List<String> list = new ArrayList<String>();
//		try {
//			list = valBatchStart001Mapper.findAllProdbyGroup(prodGroupSeqNos);
//		} catch (Exception e) {
//		}
//		return list;
    	ClassForStringLIst dto = restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAccountHandle001/findAllProdbyGroup",
                prodGroupSeqNos, ClassForStringLIst.class);
    	return dto.getPara();
	}

	public List<ValAccountHandle002Dto> selectCheckLog(Map<String, Object> map) {
//		return valAccountHandle001Mapper.selectCheckLog(map);
		return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAccountHandle001/selectCheckLog",
                map, ValAccountHandle002Dto.class);
	}
	
	/**
     * 产品组
     * @param pageParam
     * @return
     */
    @DataSource(DataSourceType.OTHER)
    public Page<SelectOptionDto> findBooksetGroup(Map<String, Object> pageParam) {
//        return super.queryForPageForSelectReturn(()->valAccountHandle001Mapper.selectBatchGroup(pageParam));
    	return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValTransactionDataImport001/findBooksetGroup",
                pageParam, SelectOptionDto.class);
    }
    
    /**
     * 获取产品组下的账套
     * @param object
     * @return
     */
    @DataSource(DataSourceType.OTHER)
    public List<SysBatchGrpPrdlink> findAllProduct(Map<String, Object> params) {
//        List<SysBatchGrpPrdlink> list = valAccountHandle001Mapper.findAllProduct(params);
//        return list;
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValTransactionDataImport001/findAllProduct",
                params, SysBatchGrpPrdlink.class);
    }
    
    /**
     * 根据账套代码获取分层代码
     * @param bookSetIds
     * @return
     */
    public List<String> getBooksetIdbyLayeringInfo(List<String> bookSetIds){
//    	Map<String,Object> map = new HashMap<>();
//    	map.put("booksetIds", bookSetIds);
//    	List<String> busiIds = valAccountHandle001Mapper.getBooksetIdbyLayeringInfo(map);
//    	return busiIds;
    	ClassForStringLIst result= restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValTransactionDataImport001/getBooksetIdbyLayeringInfo",
                bookSetIds, ClassForStringLIst.class); 
        return result.getPara();
    }

    /**
     * 根据账套代码获取子账套代码
     * @param bookSetIds
     * @return
     */
    public List<String> getBooksetIdSon(List<String> bookSetIds){
//    	Map<String,Object> map = new HashMap<>();
//    	map.put("booksetIds", bookSetIds);
//    	List<String> busiIds = valAccountHandle001Mapper.getBooksetIdSon(map);
//    	return busiIds;
    	ClassForStringLIst rstDto = restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAccountHandle001/getBooksetIdSon",
                bookSetIds, ClassForStringLIst.class);
    	return rstDto.getPara();
    }
}
