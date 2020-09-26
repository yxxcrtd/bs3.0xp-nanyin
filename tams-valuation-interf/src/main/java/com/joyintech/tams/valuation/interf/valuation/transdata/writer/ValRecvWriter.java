/*
 * 文件名：ValRecvWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.transdata.writer;

import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.interf.core.InterfBaseWriter;
import com.joyintech.tams.valuation.interf.valuation.transdata.entity.ValRecvFileEntity;

@Service
@Scope("prototype")
public class ValRecvWriter extends InterfBaseWriter<ValRecvFileEntity>{
    
    @Override
    public void write(List<? extends ValRecvFileEntity> recvFileEntityList)
        throws Exception {
        if (recvFileEntityList != null && !recvFileEntityList.isEmpty()) {
            recvFileEntityList.forEach(x->{
                insertValInterflog(x.getInterfLogs());
            });
        }  
    }
    
}
