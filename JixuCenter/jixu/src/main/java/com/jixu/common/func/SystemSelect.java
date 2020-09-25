package com.jixu.common.func;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jixu.dao.intf.VoucherDao;

@Service
public class SystemSelect {
	
	/*@Autowired
	private DBUtil dBUtil;
	*/
	@Autowired
	private VoucherDao voucherDao;
	
	private static int systemDBFrameWork;
	
	//系统为jixu独立运行
	
	//系统为jixu可以合入joyin运行，采用joyin的主键生成策略
	private int joyinFlag=1;
	
	@Value("${jixu.keyGenerate.flag:0}")  
	private int jixuFlag;
	
	/**
	 * 生成凭证的主键
	 * @return
	 */
	public String generateVouchernum(){
		String vouchernum="";
		if(jixuFlag==joyinFlag){
			//vouchernum=dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型);
		}else{
			int voucherKey=voucherDao.generateKey();
			vouchernum=String.valueOf(voucherKey);
		}
		return vouchernum;
	}

	public static int getSystemDBFrameWork() {
		return systemDBFrameWork;
	}

	@Value("${jixu.frameWork.flag:0}")
	public void setSystemDBFrameWork(int systemDBFrameWork) {
		SystemSelect.systemDBFrameWork = systemDBFrameWork;
	}
}
