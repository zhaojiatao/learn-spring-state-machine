package zjt.learn.statemachine.simpleorder.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import zjt.learn.statemachine.base.BaseEvent;
import zjt.learn.statemachine.base.BaseState;
import zjt.learn.statemachine.base.action.AbstractOrderAction;
import zjt.learn.statemachine.base.event.BasicOrderEvent;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/11/8 17:06
 * @ClassName: SimpleCommonAction01
 */
@Slf4j
@Component
public class SimpleCommonAction01 extends AbstractOrderAction {
    @Override
    protected void doAction(BasicOrderEvent<BaseEvent> event, BaseState source, BaseState target) {
        Long orderId = event.getOrderId();
        log.info("★★★★★★★ SimpleCommonAction01 do something，orderId={} ★★★★★★★",orderId);
        //TODO do something...
        if (TransactionSynchronizationManager.isActualTransactionActive()){
            log.info("存在事务");
        }
        throw new RuntimeException("手动抛出");
    }
}
