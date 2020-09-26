/*
 * 文件名：BalTableGenerate001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：王波
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.balancetablegeneration.service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.tams.common.base.dao.BokValbalTableLogMapper;
import com.joyintech.tams.common.base.dao.BokValbalTableStatusMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.accounadjustmanage.balancetablegeneration.dao.BalTableGenerate001Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.balancetablegeneration.dto.BalTableGenerate001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.balancetablegeneration.dto.BalTableGenerate001SearchDto;
import com.joyintech.tams.valuation.accounadjustmanage.bokbalancestatementquery.dao.BokBalanceStatementQuery001Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.bokbalancestatementquery.dto.BokBalanceStatementQuery001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.valuationstatementquery.dto.ValValuationStatementQuery001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto.ExportFileDto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValAssetbook001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValTablelist001Dto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tools.util.ExcelTools;

/**
 * 余额表生成service
 * 
 * @author 王波
 * @version 2.0 2018年5月3日
 * @see BalTableGenerate001Service
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BalTableGenerate001Service extends CommonExportService<BalTableGenerate001Dto> {

	/**
	 * ValTableGenerate001Mapper
	 */
	@Autowired
	private BalTableGenerate001Mapper mapper;

	@Autowired
	private BokValbalTableStatusMapper bokValbalTableStatusMapper;

	@Autowired
	private BokValbalTableLogMapper bokValbalTableLogMapper;

	@Autowired
	private BokBalanceStatementQuery001Mapper bokBalanceStatementQuery001Mapper;
	
	  /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(BalTableGenerate001Service.class);

	/**
	 * 所属账套下拉框获取
	 *
	 * @return 返回的数据
	 * @exception Exception
	 *                异常
	 * @since 1.0
	 */
	public List<SelectOptionDto> getBooksetId() {
		List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
		List<ValAssetbook001Dto> listBookset = mapper.getBooksetId();
		listBookset.forEach(item -> {
			listSelectDto.add(createSelect(item.getBooksetId(), item.getBooksetName()));
		});
		return listSelectDto;
	}

	/**
	 * 业务类别下拉框获取
	 *
	 * @return 返回的数据
	 * @exception Exception
	 *                异常
	 * @since 1.0
	 */
	public List<SelectOptionDto> getBusiClass() {
		List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
		List<ValTablelist001Dto> listTable = mapper.getBusiClass();
		listTable.forEach(item -> {
			listSelectDto.add(createSelect(item.getTableId(), item.getTableName()));
		});
		return listSelectDto;
	}

	/**
	 * 格式化创建SelectOptionDto对象 <功能详细描述>
	 * 
	 * @param selectId
	 *            下拉Id值
	 * @param text
	 *            下拉文本值
	 * @return dto
	 * @see
	 * @since 1.0
	 */
	public SelectOptionDto createSelect(String selectId, String text) {
		SelectOptionDto dto = new SelectOptionDto();
		dto.setId(selectId);
		dto.setText(text);
		return dto;
	}

	/**
	 * 根据条件获取表格信息
	 * 
	 * @param params
	 *            筛选条件
	 * @return 表格信息
	 * @throws Exception
	 */
	public Page<BalTableGenerate001Dto> findPage(Map<String, Object> params) throws Exception {
	    
	    
	    return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/BalTableGenerate001/findPage", params, BalTableGenerate001Dto.class);
	    
//		// 获取交易类型
//		String[] prodTypeArr = (String[]) params.get("prodTypes");
//		// //获取交易类型
//		// String prodType = (String)params.get("prodTypes");
//		// //交易方向多选，转为数组
//		//
//		// if(StringUtil.isNotEmpty(prodType)){
//		// prodTypeArr = prodType.split(",");
//		// }
//
//		// 获取交易代码
//		String[] prodIdArr = (String[]) params.get("prodIds");
//		// String prodId = (String)params.get("prodIds");
//		// //交易方向多选，转为数组
//		//
//		// if(StringUtil.isNotEmpty(prodId)){
//		// prodIdArr = prodId.split(",");
//		// }
//
//		// 获取生成状态
//		String buildStatus = (String) params.get("buildStatuses");
//		// 交易方向多选，转为数组
//		String[] buildStatusArr = null;
//		if (StringUtil.isNotEmpty(buildStatus)) {
//			buildStatusArr = buildStatus.split(",");
//		}
//		BalTableGenerate001SearchDto dto = (BalTableGenerate001SearchDto) JsonUtils.map2entity(params,
//				BalTableGenerate001SearchDto.class);
//		dto.setProdIds(prodIdArr);
//		dto.setProdTypes(prodTypeArr);
//		dto.setBuildStatuses(buildStatusArr);
//		dto.setFileType(DictCodeList.文件类型_余额表.getValue());
//		return super.queryForPage(() -> mapper.queryPageList(dto));
	}

	/**
	 * 
	 * 根据条件查询全部信息
	 * 
	 * @param params
	 *            筛选条件
	 * @return 全部信息
	 * @see
	 * @since 1.0
	 */
	// public List<ValVoucherQuery001Dto> selectAll(ValVoucherQuery001SearchDto
	// dto){
	// return mapper.queryPageList(dto);
	// }

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @return
	 * @see
	 * @since 1.0
	 */
//	public List<SelectOptionDto> getFinprodId(Map<String, Object> map) {
//		List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
//		List<ValProductInfoQuery001Dto> listBookset = mapper.getFinprodId(map);
//		listBookset.forEach(item -> {
//			SelectOptionDto dto = new SelectOptionDto();
//			dto.setId(item.getFinprodId());
//			dto.setText(item.getFinprodAbbr());
//			listSelectDto.add(dto);
//		});
//		return listSelectDto;
//	}

	
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodId(Map<String,Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/BalTableGenerate001/getFinprodId", map, SelectOptionDto.class);
    }
	
	/**
	 * 
	 * 手动生成更新估值余额状态表
	 * 
	 * @return
	 * @see
	 * @since 1.0
	 */
	public int handle(List<BalTableGenerate001SearchDto> listdto) {
	    
	    return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/BalTableGenerate001/handle", listdto, Integer.class);
	    
//		int flag = 0;
//		int n = 0;
//		int m = 0;
//		// 删除估值表余额表状态表数据
//		mapper.deleteValbalTableStatus(dto);
//		// 删除估值表余额表状态表数据
//		mapper.deleteValbalTableLog(dto);
//		// 从余额表数据抽取数据生成估值表余额表状态表数据
//		List<BalTableGenerate001HandleDto> blist = mapper.queryValbalTableStatus(dto);
//		for (BalTableGenerate001HandleDto bdto : blist) {
//			BokValbalTableStatus bokValbalTableStatus = new BokValbalTableStatus();
//			// 产品代码
//			bokValbalTableStatus.setFinprodId(bdto.getFinProdId());
//			// 产品名称
//			bokValbalTableStatus.setFinprodName(bdto.getFinprodName());
//			// 估值日期
//			bokValbalTableStatus.setValDate(bdto.getBalDate());
//			// 对账状态--未对账
//			bokValbalTableStatus.setCheckStatus(DictCodeList.对账状态_未对账.getValue());
//			// 对账结果--空
//			bokValbalTableStatus.setCheckResult("");
//			// 确认状态--空
//			bokValbalTableStatus.setConfirmStatus("");
//			// 下发状态--空
//			bokValbalTableStatus.setNoticeStatus("");
//			bokValbalTableStatus.setValBalFlag(DictCodeList.文件类型_余额表.getValue());
//			// 入库
//			int result1 = bokValbalTableStatusMapper.insert(bokValbalTableStatus);
//			if (result1 > 0) {
//				n++;
//			}
//
//			BokValbalTableLog bldto = new BokValbalTableLog();
//			// 产品代码
//			bldto.setFinprodId(bdto.getFinProdId());
//			// 估值日期
//			bldto.setValDate(bdto.getBalDate());
//			if (bdto.getErrorCount() > 0) {
//				bldto.setBuildStatus(DictCodeList.生成状态_已生成_有错误凭证.getValue());
//			} else {
//				bldto.setBuildStatus(DictCodeList.生成状态_已生成_无错误凭证.getValue());
//			}
//			bldto.setValBalFlag(DictCodeList.文件类型_余额表.getValue());
//			bldto.setValDate(bdto.getBalDate());
//			int result2 = bokValbalTableLogMapper.insert(bldto);
//			if (result2 > 0) {
//				m++;
//			}
//		}
//
//		if (n == m) {
//			flag = 1;
//		}
//		return flag;
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param map
	 * @see
	 * @since 1.0
	 */
	public int handleByHandCheck(Map<String, Object> map) {
	    
	    return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/BalTableGenerate001/handleByHandCheck", map,Integer.class);
	    
//		int count = 0;
//		map.put("prodId", map.get("data[prodId]"));
//		map.put("valDateBegin", map.get("data[valDateBegin]"));
//		map.put("valDateEnd", map.get("data[valDateEnd]"));
//		map.put("fileType", DictCodeList.文件类型_余额表.getValue());
//		map.put("checkStatus", DictCodeList.对账状态_已对账.getValue());
//		count = mapper.queryValbalTableStatusCount(map);
//		return count;
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param map
	 * @see
	 * @since 1.0
	 */
	public int handleByHandCheck2(Map<String, Object> map) {
		int res = 0;
		String buildStaus = (String) map.get("data[buildStaus]");
		if (DictCodeList.生成状态_已生成_无错误凭证.getValue().equals(buildStaus)) {
			res++;
		}
		return res;
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param map
	 * @see
	 * @since 1.0
	 */
	public ValAssetbook001Dto queryBooksetId(String prodid) {
	    return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/BalTableGenerate001/queryBooksetId", prodid,ValAssetbook001Dto.class);
//		ValAssetbook001Dto booksetid = mapper.queryBooksetId(prodid);
//		return booksetid;
	}

	/**
	 * 查询余额表信息 <一句话功能简述> <功能详细描述>
	 * 
	 * @param dto
	 * @return
	 * @see
	 * @since 1.0
	 */
	public ValValuationStatementQuery001Dto queryExcelInfo(ValValuationStatementQuery001Dto dto) {
		return mapper.queryExcelInfo(dto);
	}

	/**
	 * <一句话功能简述> 导出估值表
	 * 
	 * @param map
	 * @throws IOException
	 * @see
	 * @since 1.0
	 */
	public ResponseEntity<byte[]> exportExcel(List<BokBalanceStatementQuery001Dto> list) throws IOException {
		List<ExportFileDto> listFileDto = new ArrayList<ExportFileDto>();
		for (BokBalanceStatementQuery001Dto dto : list) {
			// 根据产品代码查询账套id
			ValAssetbook001Dto bookdto = queryBooksetId(dto.getFinprodId());
			listFileDto.add(this.createExcel(bookdto.getBooksetId(), dto.getValDate(), dto.getFinprodId(),
					dto.getFinprodName()));
		}

		if (!CollectionUtils.isEmpty(listFileDto)) {
			// 超过一条时，用zip打包下载
			if (listFileDto.size() > 1) {
				ResponseEntity<byte[]> set = null;
				ZipOutputStream zipOut = null;
				try {
					String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
					String zipFileName = FileNameUtil.createFileName("zip");
					File zipFile = new File(exportFilePath + File.separator + zipFileName);
					zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
					InputStream input = null;
					for (ExportFileDto item : listFileDto) {
						try {
							File file = new File(item.getFilePath());
							input = new FileInputStream(file);
							zipOut.putNextEntry(new ZipEntry(file.getName()));
							int tmp = 0;
							while ((tmp = input.read()) != -1) {
								zipOut.write(tmp);
							}
							// 打包后删除生成的源文件
							boolean rec=file.delete();
							if(rec){
								logger.info("处理成功");
							}
						} catch (Exception e) {
							logger.error("",e);
						} finally {
							if (input != null) {
								input.close();
							}
						}
					}
					zipOut.flush();
					set = ExportUtil.export(zipFileName, zipFile, true);
				} catch (Exception e) {
					logger.error("",e);
				} finally {
					if (zipOut != null) {
						zipOut.close();
					}
				}
				return set;
			}
			// 一条时单个文件导出
			else {
				File file = new File(listFileDto.get(0).getFilePath());
				return ExportUtil.export(listFileDto.get(0).getFileName(), file, true);
			}
		} else {
			return null;
		}
	}

	public ExportFileDto createExcel(String booksetId, String balDate, String finprodId, String finprodName)
			throws IOException {
		ExportFileDto exportFileDto = new ExportFileDto();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("booksetId", booksetId);
		param.put("balDate", balDate);
//		List<BokBalanceStatementQuery001Dto> listAll = bokBalanceStatementQuery001Mapper.selectAll(param);
		
		List<BokBalanceStatementQuery001Dto> listAll = 
		    restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/BalTableGenerate001/selectAll",param,BokBalanceStatementQuery001Dto.class);
		if (listAll != null && listAll.size() > 0) {
			for (BokBalanceStatementQuery001Dto dtoo : listAll) {
				// 币种转换
				String oCcyFmt = dtoo.getoCcyFmt();
				if (oCcyFmt != null) {
					dtoo.setoCcy(oCcyFmt);
				}
			}
		}
		String fileName = booksetId + "_" + balDate + "_余额表" + ".xls";
		// 无论是净值型还是货币型，查询结果都是listSum，故不用区分
		String tempName = GlobalConstant.BALANCE_TMP_NAME;
		String outPath = CacheUtil.getSysParam("EXPORT_VALBOKBANLANCE") + File.separator + fileName;
		/*String tplPath = CacheUtil.getSysParam("BATCH_TPL_OUT") + File.separator + tempName;*/
//	     File tplFile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "tpl/tplPath");
//	     String tplPath = tplFile + File.separator + tempName;
	     
		InputStream stream = null;
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("prodTitle", String.format("%s（%s）余额表", finprodId, finprodName));
		paraMap.put("dateStr", balDate);
		paraMap.put("list", listAll);
		exportFileDto.setFileName(fileName);
		exportFileDto.setFilePath(outPath);
		try {
//			stream = new FileInputStream(tplPath);
			stream = this.getClass().getResourceAsStream("/tpl/tplPath/"+ tempName);
			ExcelTools.writeExcel(stream, tempName, paraMap, outPath);
		} catch (Exception e) {
			logger.error("",e);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					logger.error("",e);
				}
			}
		}
		return exportFileDto;
	}
}
