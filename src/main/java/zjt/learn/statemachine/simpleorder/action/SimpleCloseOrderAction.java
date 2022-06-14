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
 * @Date: 2022/6/14 22:06
 * @ClassName: CloseOrderAction
 */
@Slf4j
@Component
public class SimpleCloseOrderAction extends AbstractOrderAction {
    @Override
    protected void doAction(BasicOrderEvent<BaseEvent> event, BaseState source, BaseState target) {
        Long orderId = event.getOrderId();
        log.info("★★★★★★★ CloseOrderAction，orderId={} ★★★★★★★",orderId);
        if (TransactionSynchronizationManager.isActualTransactionActive()){
            log.info("存在事务");
        }
        //TODO do something...
    }
}
