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
 * @Date: 2022/6/14 22:42
 * @ClassName: DepositReveiveAction
 */
@Slf4j
@Component
public class DepositReveiveAction  extends AbstractOrderAction {
    @Override
    protected void doAction(BasicOrderEvent<BaseEvent> event, BaseState source, BaseState target) {
        Long orderId = event.getOrderId();
        log.info("★★★★★★★ DepositReveiveAction do something，orderId={} ★★★★★★★",orderId);
        //TODO do something...
    }
}