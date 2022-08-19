package com.joyintech.tams.disclosure.common;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.disclosure.information.config.reportpage.service.ReportPageManage002Service;
import org.springframework.stereotype.Component;

@Component
public class RepInfoUpdate extends BaseService {

    //更新模板状态
   public void updateRepInfo(){
       ReportPageManage002Service pageManage002Service = (ReportPageManage002Service)SpringContextUtil.getBean("reportPageManage002Service");
       //同步模板状态
       pageManage002Service.synchroRepInfoStatus();
   }
}
