package com.jixu.job.service.Impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.valuation.common.interf.common.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.JixuDictCode;
import com.jixu.dao.intf.VoucherDao;
import com.jixu.dao.intf.VoucherDetailsDao;
import com.jixu.job.service.PrintJNL;
import com.jixu.journal.obj.GLCOOABase;
import com.joyintech.tams.valuation.common.interf.howmany.dto.BokBookdetailCurrentDto;
import com.joyintech.tams.valuation.common.interf.howmany.service.HowManyService;
import com.orm.obj.Voucher;
import com.orm.obj.VoucherDetails;

@Service("printjnlCHS")
public class PrintJNLCHS extends PrintJNL {
	@Autowired
	private VoucherDao voucherDao;
	
	@Autowired
	private VoucherDetailsDao voucherDetailsDao; 
	
	@Autowired(required=false)
	private HowManyService howManyService;

	@Override
	public ResultContainer printJNL(List<GLCOOABase> JnlList) {
		ResultContainer container=new ResultContainer();
			
		for(int i=0;i<JnlList.size();i++){
			GLCOOABase glcooa = JnlList.get(i);
				
			double dfunc=0;
			double cfunc=0;
			double dlocal=0;
			double clocal=0;
				
			List<VoucherDetails> jnlobjIt=glcooa.getVoucherDetails();
				
			//合并相同科目的凭证
			//去除借贷为0的凭证
			if(glcooa.isRemoveZeroFlag()){
				jnlobjIt = mergetSameAcctJNL(jnlobjIt);
				jnlobjIt = removeZeroJNL(jnlobjIt);
			}
			glcooa.setVoucherDetails(jnlobjIt);
				
			for(VoucherDetails baseJNL:jnlobjIt) {
				dfunc=dfunc+baseJNL.getDr_func();
				cfunc=cfunc+baseJNL.getCr_func();
				dlocal=dlocal+baseJNL.getDr_local();
				clocal=clocal+baseJNL.getCr_local();
			}

		}
			
		for(int i=0;i<JnlList.size();){
			List<VoucherDetails> vds=JnlList.get(i).getVoucherDetails();
			List<VoucherDetails> offVoucherDetails=JnlList.get(i).getOffVoucherDetails();
			boolean zeroremoveFlag=JnlList.get(i).isRemoveZeroFlag();
			double total_func_d=0;
			double total_func_c=0;
			double total_local_d=0;
			double total_local_c=0;
				
			for(VoucherDetails vd:vds){
				total_func_d=total_func_d+vd.getDr_func();
				total_func_c=total_func_c+vd.getCr_func();
				total_local_d=total_local_d+vd.getDr_local();
				total_local_c=total_local_c+vd.getCr_local();
			}
				
			if(zeroremoveFlag && Math.abs(total_func_d)<0.0001 && Math.abs(total_func_c)<0.0001 && Math.abs(total_local_d)<0.0001 && Math.abs(total_local_c)<0.0001){
				//之前的情况是单独出表外账  会因为没有voucherdetails而删除这笔凭证 所以加个判断
				if(offVoucherDetails.size()>0){
					i++;
				}else{
					JnlList.remove(i);
				}
			}
			else{
				i++;
			}
		}
		//voucherDao.saveVouchers(JnlList);
		for(GLCOOABase coaBase:JnlList){
			Voucher voucher=coaBase.getVoucher();
			/*String vouchernum=systemSelect.generateVouchernum();
			voucher.setVouchernum(vouchernum);*/
			List<VoucherDetails> voucherDetailsList=coaBase.getVoucherDetails();
			List<VoucherDetails> offVoucherDetails=coaBase.getOffVoucherDetails();
				
			if(!JixuUserUtil.isJoyinTablesFlag()) {
				voucherDao.saveEntity(voucher);
				for(VoucherDetails vd:voucherDetailsList){
					vd.setVoucher(voucher);
					voucherDetailsDao.saveEntity(vd);
				}
			}
				
			//joyin添加凭证
			if(!JixuUserUtil.jixuUserSystemFlag() && JixuUserUtil.isJoyinTablesFlag()){
				//如果是单独出表外账   没有voucherdetails取不到securityID
				String securityID="";
				if(voucherDetailsList.size()>0){
					VoucherDetails vd=voucherDetailsList.get(0);
					securityID=vd.getSecurityID();
				}
				if(securityID.equals("") && offVoucherDetails.size()>0){
					VoucherDetails vd=offVoucherDetails.get(0);
					securityID=vd.getSecurityID();
				}
				
				container=createBokVouch(coaBase,container);
				if(!container.isCorrectFlag()) {
					//joyin凭证入账错误，直接停止，并抛出错误
					break;
				}
			}
		}
		return container;
	}

	
	@Override
	public boolean checkJournalRunable(String portNum, Date journalDate, int journalType){
		return voucherDao.checkJournalRunable(portNum, journalDate, journalType);
	}
	
	
	private ResultContainer createBokVouch(GLCOOABase coaBase,ResultContainer container){
		//表内账
		Voucher voucher=coaBase.getVoucher();
		List<VoucherDetails> voucherList=coaBase.getVoucherDetails();
		//container=bokVoucherList(voucher,voucherList,bokDetailId,coaBase.getTableID(),coaBase.getBusiId(),coaBase.getBusiDetailType(),container);
		container=bokVoucherList(voucher,voucherList,coaBase.getTableID(),container);
		//表外账
		List<VoucherDetails> offvoucherList=coaBase.getOffVoucherDetails();
		if(container.isCorrectFlag() && offvoucherList.size()>0){
			//表外账和其他凭证一起出
			//container=bokVoucherList(voucher,offvoucherList,bokDetailId,coaBase.getOfftableID(), coaBase.getBusiId(),coaBase.getBusiDetailType(), container);
			container=bokVoucherList(voucher,offvoucherList,coaBase.getOfftableID(),container);
		}
		return container;
	}

	/**
	 *
	 * @param voucher 凭证
	 * @param voucherList 凭证明细
	 * @param tableID 会计场景
	 * @param container 业务明细代码，资产类型
	 * @return
	 */
	private ResultContainer bokVoucherList(Voucher voucher,List<VoucherDetails> voucherList,String tableID, ResultContainer container){
		for (VoucherDetails vd : voucherList) {
			BokBookdetailCurrentDto bct=new BokBookdetailCurrentDto();
			//账套代码
			bct.setBooksetId(voucher.getPortfolio());
			//账户明细代码  查兆尹数据库返回是md5加密
			String bokDetailId=voucherDao.genBokDetailID(voucher.getPortfolio(),vd.getBusiID());
			bct.setBokDetailId(bokDetailId);
			//发生日期
			bct.setHappenDate(voucher.getTradeDate());
			//入账日期
			bct.setBookDate(voucher.getLedgerDate());
			//任务代码
			bct.setTaskId("603");
			//任务优先级
			bct.setTaskPri(Long.valueOf(31));
			//凭证会计事件代码
			bct.setTableId(tableID); 
			//凭证  发生科目号
			bct.setSubjectNo(vd.getAccountNum());
			//凭证  科目尾号
			bct.setDetailSubjectNo(vd.getSecurityID());
			//凭证 类型
			bct.setBookType(String.valueOf(voucher.getJournalSourceCode()));
			//凭证  原币发生额  本币发生额
			if(vd.getDr_func()>0){
				bct.setCdFlag(DBCR.DEBIT);
				bct.setoHappenAmt(new BigDecimal(vd.getDr_local()));
				bct.setbHappenAmt(new BigDecimal(vd.getDr_func()));
			}else{
				bct.setCdFlag(DBCR.CREDIT);
				bct.setoHappenAmt(new BigDecimal(vd.getCr_local()));
				bct.setbHappenAmt(new BigDecimal(vd.getCr_func()));
			}
			//凭证  原币币种
			bct.setoCcy(vd.getLocalCurrency());
			//凭证  发生数量
			bct.setHappenNumber(new BigDecimal(vd.getAmount()));
			//凭证  本币币种
			bct.setbCcy(vd.getFunctionCurrency());
			//交易编号
			bct.setTradeId(voucher.getTaNum());
			//业务明细代码
			bct.setBusiId(vd.getBusiID());
			//业务明细类型
			bct.setBusiDetailType(vd.getBusiDetailType());
			 //汇率日期
			bct.setExDate(voucher.getLedgerDate());
			//汇率
			bct.setExRate(new BigDecimal(vd.getFxRate()));
			//备注
			bct.setBookRemark(voucher.getDescription());
			ResultObject result=howManyService.generateBokBookdetailCurrent(bct);
			if(!result.getResultCode().equals(JixuDictCode.JOYIN_SUCCESS_STATUS)){
				container.setCorrectFlag(false);
				container.setErrorMsg(result.getResultMsg());
				break;
			}
		}
		return container;
	}
}
