package com.joyintech.tams.valuation.interf.valuation.recv.informationsys.rate.writer;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.valuation.interf.core.InterfBaseWriter;

/**
 * 利率行情接口Writer
* @author dongxiangyang
* @version 1.0 2018年7月06日
* @see BookGenerateResultForCurrentCommonWriter
* @since 1.0
*/
@Service
@Scope("prototype")
public class RateRecWriter extends InterfBaseWriter<BaseEntity> {

//	 /**
//     * 利率行情mapper
//     */
//	@Autowired
//    private ValIntRateBatchMapper valIntRateBatchMapper;
//	 /**
//     * 行情信息mapper
//     */
//	@Autowired
//    private ValRateInfoBatchMapper valRateInfoBatchMapper;
//	 /**
//     * 日志
//     */
//    @Autowired
//    private ValInterflogBatchMapper valInterflogBatchMapper;
	 /**
     * 参数
     */
    @SuppressWarnings("unused")
    private Map<String, Object> param;

    /**
     * 设置 param
     * 
     * @param param param
     */
    public void setParam(Map<String, Object> param) {
        this.param = param;
    }
    /**
     * 更新数据 {@inheritDoc}
     */
	@Override
	public void write(List<? extends BaseEntity> arg0) throws Exception {
    }

}
