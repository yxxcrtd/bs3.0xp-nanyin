/**
 * 文件名：PbcClassicsService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chenxc
 * 修改时间：2019年6月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.pbc.classic.pbcNewDat.service;

import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.*;
import com.joyintech.tams.svreport.pbc.classic.pbcNewDat.dao.PbcNewPrdDatFileMapper;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dto.PbcPrdBaseinfoSubDat;
import com.joyintech.tams.svreport.pbc.report.dao.PbcMonthReportMapper;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2019年6月10日
 * @see PbcNewReportPrdDatFileService
 * @since 1.0
 */
@Service
public class PbcNewReportPrdDatFileService {


	@Autowired
	private PbcMonthReportMapper pbcMonthReportMapper;

	@Autowired
	private PbcNewPrdDatFileMapper pbcNewPrdDatFileMapper;

	@Autowired
	private PbcReportProdDatMapper pbcReportProdDatMapper;

	@Autowired
	private PbcReportAreaDatDetailMapper pbcReportAreaDatDetailMapper;

	@Autowired
	private PbcReportAssetDebtDatDetailMapper pbcReportAssetDebtDatDetailMapper;

	@Autowired
	private PbcReportAssetIncomeDatDetailMapper pbcReportAssetIncomeDatDetailMapper;

	@Autowired
	private PbcReportCompanyDatDetailMapper pbcReportCompanyDatDetailMapper;

	@Autowired
	private PbcReportDebtTermDatDetailMapper pbcReportDebtTermDatDetailMapper;

    /**
     * 生成数据文件
     * @param reportType，报表类型，如果为空，则生成所有
     * @param listProds，产品列表，如果为空，则生成所有
     * @param genDate，生成日期，如果为空，则为当前平台时间
     * @see
     * @since 1.0
     */
    public void genDataFile(String reportType,  List<String> listProds,Date genDate, Map<String, Object> param,String OrgCode) throws UnsupportedEncodingException {
		String sendStatus = DictCodeListExt2.报送状态_未报送.getValue();

		//设置产品端字符
		List<String> prodList = Arrays.asList(DictCodeListExt2.人行报送明细类型_募集余额报表.getValue(),DictCodeListExt2.人行报送明细类型_募集兑付报表.getValue(),
				DictCodeListExt2.人行报送明细类型_只数情况报表.getValue(),DictCodeListExt2.人行报送明细类型_募集余额报表分地区报表.getValue(),
				DictCodeListExt2.人行报送明细类型_提前及延期兑付报表.getValue(),DictCodeListExt2.人行报送明细类型_到期未兑付报表.getValue(),
				DictCodeListExt2.人行报送明细类型_存续产品分期限募集余额报表.getValue(),DictCodeListExt2.人行报送明细类型_新发产品分期限募集金额报表.getValue());

		//1、查询处理期次对象  获取状态为未报送,且只取包含机构编码的
		List<PbcMonthReport> termList = pbcMonthReportMapper.selectTermIdList(Arrays.asList(sendStatus),OrgCode);
		Map<String,List<PbcMonthReport>> OrgList = termList.stream().collect(Collectors.groupingBy(PbcMonthReport::getOrgCode));

		List<PbcSendZipDatFile> zipFileList = new ArrayList<>();
		//多级法人改造
		for(Map.Entry<String,List<PbcMonthReport>> tmpMap:OrgList.entrySet()){

			String relOrgCode = tmpMap.getKey();
			//联社模式.碰到空数据直接跳出
			if(CommonUtil.isBAmodel()){
				if("All".equals(tmpMap.getKey())){
					break;
				}
				//银行模式取当前总行的银行代码
			}else{
				relOrgCode = CommonUtil.getYHGeneralBankCode();
			}


			//2、以【1】取得期次为对象，进行循环处理
			//2.1、zip包名获取  每个法人获取各自的数据
			String zipCode = PbcDatUtil.genZipCode(DictCodeListExt2.人行报送类型_统计模板数据.getValue(),genDate,1,relOrgCode);

			//2.2、资管产品统计模板表数据处理
			for(PbcMonthReport report : tmpMap.getValue()){
				//月报生成
				PbcSendZipDatFile zipFile = new PbcSendZipDatFile();
				zipFile.setDatZipCode(zipCode);
				zipFile.setDatZipDatType(report.getReporttype());
				zipFile.setDatZipType(DictCodeListExt2.人行报送类型_统计模板数据.getValue());
				zipFile.setDatZipStatus(DictCodeListExt3.zip包状态_已使用.getValue());
				zipFile.setStatusVdate(report.getTermid());
				zipFile.setZipSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
				zipFile.setOrgCode(report.getOrgCode());
				zipFile.setPibDetailCnt(1);
				zipFile.setReportuuid(report.getReportuuid());

				zipFile.setIssuePartyId(report.getIssuePartyId());
				zipFile.setIssuePartyName(report.getIssuePartyName());
				zipFile.setReportOrgName(report.getReportOrgName());
				zipFile.setSocialCreditCode(report.getSocialCreditCode());
				zipFile.setFinInstitutionCode(report.getFinInstitutionCode());
				zipFile.setProdVariety(report.getProdVariety());
				zipFile.setPersonLiable(report.getPersonLiable());
				zipFile.setContact(report.getContact());
				zipFile.setReportDate(report.getReportDate());

				zipFileList.add(zipFile);
				//产品类型的报表处理
				if(prodList.contains(report.getReporttype())){
					//根据报表uuid进行删除
					pbcReportProdDatMapper.deleteByReportUUid(report.getReportuuid());
					//重新导入报表uuid中的数据
					pbcReportProdDatMapper.insertDataBySelect(report.getReportuuid());
				}else if(DictCodeListExt2.人行报送明细类型_除回购和拆借外贷款分行业企业规模统计.getValue().equals(report.getReporttype())
						||DictCodeListExt2.人行报送明细类型_企业债券分行业企业规模统计.getValue().equals(report.getReporttype())){
					//根据报表uuid进行删除
					pbcReportCompanyDatDetailMapper.deleteByReportUUid(report.getReportuuid());
					//重新导入报表uuid中的数据
					pbcReportCompanyDatDetailMapper.insertDataBySelect(report.getReportuuid());
				}else if(DictCodeListExt2.人行报送明细类型_资管产品资产负债统计.getValue().equals(report.getReporttype())){
					//根据报表uuid进行删除
					pbcReportAssetDebtDatDetailMapper.deleteByReportUUid(report.getReportuuid());
					//重新导入报表uuid中的数据
					pbcReportAssetDebtDatDetailMapper.insertDataBySelect(report.getReportuuid());
				}else if(DictCodeListExt2.人行报送明细类型_除回购和拆借外贷款分地区统计表.getValue().equals(report.getReporttype())){
					//根据报表uuid进行删除
					pbcReportAreaDatDetailMapper.deleteByReportUUid(report.getReportuuid());
					//重新导入报表uuid中的数据
					pbcReportAreaDatDetailMapper.insertDataBySelect(report.getReportuuid());
				}else if(DictCodeListExt2.人行报送明细类型_资产收益权按基础资产投向分类统计报表.getValue().equals(report.getReporttype())){
					//根据报表uuid进行删除
					pbcReportAssetIncomeDatDetailMapper.deleteByReportUUid(report.getReportuuid());
					//重新导入报表uuid中的数据
					pbcReportAssetIncomeDatDetailMapper.insertDataBySelect(report.getReportuuid());
				}else if(DictCodeListExt2.人行报送明细类型_资管产品资产负债剩余期限统计.getValue().equals(report.getReporttype())){
					//根据报表uuid进行删除
					pbcReportDebtTermDatDetailMapper.deleteByReportUUid(report.getReportuuid());
					//重新导入报表uuid中的数据
					pbcReportDebtTermDatDetailMapper.insertDataBySelect(report.getReportuuid());
				}

			}
		}

		pbcNewPrdDatFileMapper.deleteZipDataMI(OrgCode);
		//批量插入月报时间  因为月报不存在大数据量.不处理
		if(null!=zipFileList){
			pbcNewPrdDatFileMapper.insertBatchZipData(zipFileList);
		}

    }



}
