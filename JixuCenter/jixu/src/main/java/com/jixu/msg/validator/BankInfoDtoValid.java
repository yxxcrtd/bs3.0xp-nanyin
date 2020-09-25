package com.jixu.msg.validator;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.BkInfoDao;
import com.jixu.framework.validator.JixuBeanValidatorAbs;
import com.jixu.framework.validator.JixuValidMessage;
import com.jixu.msg.dto.BankInfoDto;
import com.orm.obj.BkInfo;
//import com.jixu.msg.service.MsgService;

@Component
public class BankInfoDtoValid extends JixuBeanValidatorAbs {

	@Resource(name = "bkInfoDao")
	private BkInfoDao bkDao;
	
	//@Autowired
	//private MsgService bankInfoMsg;
	//private MsgService<BankInfoDto> bankInfoMsg;
	
	@Override
	public List<JixuValidMessage> valid(Object value) {
		List<JixuValidMessage> validMessageList = new ArrayList<JixuValidMessage>();
		BankInfoDto dto = (BankInfoDto) value;
		//检查银行是否有相同的名称
		BkInfo bf=bkDao.loadloadBkInfoIDByName(dto.getName());
		if(bf!=null){
			//如果id为0 则证明是创建    不允许创建有相同名称的银行    如果是更新放开(界面上银行代码和名称disabled)
			if(dto.getId()==0){
				JixuValidMessage errmsg=new JixuValidMessage(ReturnResult.getResultHM().get(ReturnResult.bk_name_exist));
				validMessageList.add(errmsg);
			}
		}
//		boolean checkProcessExist=bankInfoMsg.checkBusinessDataInFlow(dto);
//	    if(checkProcessExist)
//	    	validMessageList.add(new JixuValidMessage(ReturnResult.getResultHM().get(ReturnResult.process_entity_exist)+dto.getHeader()));

		return validMessageList;
	}
}
