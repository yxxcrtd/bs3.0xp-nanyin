package com.joyintech.tams.mobileApproval.intef;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.mobileApproval.dto.MessageDto;
import com.joyintech.tams.mobileApproval.util.HttpKit;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.workflow.engine.entity.FlowEntity;
import com.joyintech.workflow.engine.service.MessageInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "messageService")
public class MobileMessageService implements MessageInterface {

    @Override
    public void sendMeg(FlowEntity flowEntity) {
        System.out.println("发送消息开始.......");
        List<MessageDto> lst = new ArrayList<MessageDto>();
        MessageDto dto = new MessageDto();
        dto.setThirdpartyMessageId(InterfHelper.getUUid());
        dto.setThirdpartyRegisterCode("TAMS");
        dto.setMessageContent("您有一条代办事项。");
        dto.setThirdpartySenderId(flowEntity.getCurrentUser());
        dto.setThirdpartyReceiverId(flowEntity.getNextUser());
        dto.setCreation_date(DateUtil.format(DateUtil.platDateFromCache(),"yyyy-MM-dd HH:mm:ss"));
        dto.setMessageType("0");
        dto.setMessageH5URL("http://localhost:8080/tams/mobileApproval/AssetMobileApproval/" +
                "getNewTodoList?userId="+flowEntity.getNextUser()+"&pageNo=1&pageSize=10&auditType="+flowEntity.getBusinessType());
        lst.add(dto);
        System.out.println("发送消息内容为：" + JsonUtils.object2Json(lst));

        //此部分代码现场自己调试 start
//        String res = HttpKit.post("http://127.0.0.1/seeyon/rest/thirdpartyMessage/receive/singleMessage?token=" + token,JsonKit.toJson(msg));
//        System.out.println(res);
        //此部分代码现场自己调试 end

        System.out.println("发送消息结束.......");
    }
}
