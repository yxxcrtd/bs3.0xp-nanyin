import com.joyintech.framework.rabbitmq.entity.SimpleRabbitMessage;
import com.joyintech.tams.exchange.service.trade.TradeServiceApplication;
import com.joyintech.tams.exchange.service.trade.processor.MessageConsumerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * create By MAXX on 2020/9/4
 */
@SpringBootTest(classes = TradeServiceApplication.class)
@RunWith(SpringRunner.class)
public class InstructionTest {
    @Autowired
    private  MessageConsumerService messageConsumerService;
    @Test
    public void getInstructionFromMQ() {
        SimpleRabbitMessage instructionKeyMsg = new SimpleRabbitMessage();
        instructionKeyMsg.setId("3f45ba1b38e043458dccef16f444fd2b");
        instructionKeyMsg.setMessage("3f45ba1b38e043458dccef16f444fd2b");
        messageConsumerService.handleInstructionFromMQ(instructionKeyMsg);
    }
}
