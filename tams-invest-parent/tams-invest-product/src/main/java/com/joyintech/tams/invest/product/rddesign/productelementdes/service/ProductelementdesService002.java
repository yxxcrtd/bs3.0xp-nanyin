/*
 * 文件名：MaintainProductService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2019年7月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.rddesign.productelementdes.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.rddesign.productelementdes.dto.Productelementdes002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;


/**
 *
 * 产品要素设计查询
 * @author jiangyunlong
 * @version 1.0 2019年9月25日
 * @see ProductelementdesService002
 * @since 1.0
 */
@Service

public class ProductelementdesService002 extends BaseService<Productelementdes002Dto> implements BaseProcessService<Productelementdes002Dto>{
  public void createProductelementdes002(Productelementdes002Dto dto){
      restTemplate.postForObject(
              ServiceApplication.PRODUCT_SERVICE_NAME + "/Productelementdes/createProductelementdes002", dto, Productelementdes002Dto.class);
  }
  public Productelementdes002Dto queryByPrdDesignId(String prdDesignId){
      return restTemplate.getForObject(
              ServiceApplication.PRODUCT_SERVICE_NAME + "/Productelementdes/findprdDesignId?pageParam="+prdDesignId, Productelementdes002Dto.class);
  }

    @Override
    public void doComplete(Productelementdes002Dto productelementdes002Dto, OperationType operationType) {
        System.out.println(productelementdes002Dto+""+operationType);
if (operationType.name()=="NEW"){
    String prdDesignId=productelementdes002Dto.getPrdDesignId();
    restTemplate.getForObject
            (ServiceApplication.PRODUCT_SERVICE_NAME + "/Productelementdes/deleteProductelementdes002?prdDesignId="+prdDesignId, Integer.class);
    restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/Productelementdes/insertProductelementdes", productelementdes002Dto, Productelementdes002Dto.class);
}
    else if (operationType.name()=="DELETE"){
        String prdDesignId=productelementdes002Dto.getPrdDesignId();
       restTemplate.getForObject
               (ServiceApplication.PRODUCT_SERVICE_NAME + "/Productelementdes/deletePro002Dto?prdDesignId="+prdDesignId,Integer.class);

    }else if(operationType.name()=="MODIFY")
        {
            restTemplate.postForObject(
                    ServiceApplication.PRODUCT_SERVICE_NAME + "/Productelementdes/updateProductelementdes", productelementdes002Dto, Productelementdes002Dto.class);

        }
}

}
