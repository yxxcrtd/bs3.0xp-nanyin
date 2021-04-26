package com.joyintech.tams.settle.batch;

import com.joyintech.batch.config.BatchApplication;
import com.joyintech.batch.config.BatchConfiguration;
import com.joyintech.framework.JoyinApplication;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 何阳阳
 * @date 2020/7/2
 * @see SettleBatchApplication
 * @since 1.0
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, DataSourceAutoConfiguration.class })
@Import({ BatchConfiguration.class })
@EnableScheduling
public class SettleBatchApplication extends BatchApplication {
//
//    @Autowired
//    private SysBatchgrpExecInfoMapper sysBatchgrpExecInfoMapper;
//    @Autowired
//    private BatchCoreService batchCoreService;
//
//    @Service
//    class TaskTest {
//        @Scheduled(initialDelay=5000, fixedDelay = 6000)
//        void execute() throws Exception {
//            JoyinTask task = SpringContextUtil.getBean(DetailTask.class);
//
//            SysBatchgrpExecInfo detail = sysBatchgrpExecInfoMapper.selectByPrimaryKey("d1e1c8023bb941e5b79975dfd264a540");
//            if(StringUtil.equals(detail.getStatus(), BatchDictCodeList.批处理执行状态_中断.getValue())) {
//                detail.setStatus(BatchDictCodeList.批处理执行状态_执行中.getValue());
//                Date curr = new Date();
//                detail.setUpdateTime(curr);
//                detail.setCreateTime(curr);
//                detail.setUpdateUser(GlobalConstant.SYSTEM_USER);
//                batchCoreService.updateBatchgrpExecInfo(detail);
//                SysBatchgrpExecInfoDto detailDto = new SysBatchgrpExecInfoDto();
//                BeanUtils.copyProperties(detailDto, detail);
//                task.setDetail(detailDto);
//                task.call();
//            }
//        }
//    }

    public static void main(String[] args) {
        JoyinApplication.run(SettleBatchApplication.class);
    }
}
