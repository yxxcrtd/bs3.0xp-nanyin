package com.jixu.job.service.Impl;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.job.service.PrintJNL;
import com.jixu.journal.obj.GLCOOABase;
import com.orm.obj.VoucherDetails;

@Service("printjnlENG")
public class PrintJNLENG extends PrintJNL {

	private static final Logger logger = LoggerFactory.getLogger(PrintJNLENG.class);

	@Override
	public ResultContainer printJNL(List<GLCOOABase> pursaleJnlList) {
		// TODO Auto-generated method stub
		String printTmp = "科目号\t|借方金额本币\t|贷方金额本币\t|借方金额原币\t|贷方金额原币\t";
		VoucherDetails baseJNL = null;
		//Collections.sort(list,new Comparator<JournalBaseObj>(){});
		int i = 0;
		if (pursaleJnlList != null) {
			Iterator<GLCOOABase> glcooait = pursaleJnlList.iterator();
			GLCOOABase glcooa = null;
			//Comparator<JournalBaseObj> comp = new SortByDC();
			while (glcooait.hasNext()) {
				glcooa = glcooait.next();
				//Iterator<JournalBaseObj> jnlobjIt = mergetSameAcctJNL(glcooa.getCooaList()).iterator();
				Iterator<VoucherDetails> jnlobjIt = glcooa.getVoucherDetails().iterator();
//				System.out.println("\t\t\t凭证" + (++i) + "\t\t\t");
				logger.info("\t\t\t凭证" + (++i) + "\t\t\t");
//				System.out.println(printTmp);
				logger.info(printTmp);
				while (jnlobjIt.hasNext()) {
					baseJNL = jnlobjIt.next();
					if (Math.abs(baseJNL.getCr_local() + baseJNL.getDr_local() + baseJNL.getDr_func() + baseJNL.getCr_func()) < 0.0001) {
						continue;
					}
//					System.out.print(baseJNL.getAccountNum());
					logger.info(baseJNL.getAccountNum());
//					System.out.print("\t");
					logger.info("\t");
//					System.out.print(Math.abs(baseJNL.getDr_func()) > 0.00001 ? baseJNL.getDr_func():"-");
					logger.info(String.valueOf(Math.abs(baseJNL.getDr_func()) > 0.00001 ? baseJNL.getDr_func():"-"));
//					System.out.print("\t");
					logger.info("\t");
//					System.out.print(Math.abs(baseJNL.getCr_func()) > 0.00001 ? baseJNL.getCr_func():"-");
					logger.info(String.valueOf(Math.abs(baseJNL.getCr_func()) > 0.00001 ? baseJNL.getCr_func():"-"));
//					System.out.print("\t");
					logger.info("\t");
//					System.out.print(Math.abs(baseJNL.getDr_local()) > 0.00001 ? baseJNL.getDr_local():"-");
					logger.info(String.valueOf(Math.abs(baseJNL.getDr_local()) > 0.00001 ? baseJNL.getDr_local():"-"));
//					System.out.print("\t");
					logger.info("\t");
//					System.out.print(Math.abs(baseJNL.getCr_local()) > 0.00001 ? baseJNL.getCr_local():"-");
					logger.info(String.valueOf(Math.abs(baseJNL.getCr_local()) > 0.00001 ? baseJNL.getCr_local():"-"));
//					System.out.println("");
					logger.info("");
				}
			}
			
		}
		return null;
	}

}
