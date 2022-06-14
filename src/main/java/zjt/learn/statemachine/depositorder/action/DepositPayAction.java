package zjt.learn.statemachine.depositorder.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import zjt.learn.statemachine.base.BaseEvent;
import zjt.learn.statemachine.base.BaseState;
import zjt.learn.statemachine.base.action.AbstractOrderAction;
import zjt.learn.statemachine.base.event.BasicOrderEvent;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/11/8 16:42
 * @ClassName: DepositAction01
 */
@Slf4j
@Component
public class DepositPayAction extends AbstractOrderAction {
    @Override
    protected void doAction(BasicOrderEvent<BaseEvent> event, BaseState source, BaseState target) {
        Long orderId = event.getOrderId();
        log.info("★★★★★★★ DepositPayAction do something，orderId={} ★★★★★★★",orderId);
        //TODO do something...
    }
}
