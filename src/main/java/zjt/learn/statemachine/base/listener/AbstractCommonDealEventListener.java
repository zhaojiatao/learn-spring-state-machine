package zjt.learn.statemachine.base.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import zjt.learn.statemachine.base.BaseEvent;
import zjt.learn.statemachine.base.BaseState;
import zjt.learn.statemachine.base.action.AbstractOrderAction;
import zjt.learn.statemachine.base.event.BasicOrderEvent;

import java.util.List;
import java.util.Objects;

@Slf4j
public abstract class AbstractCommonDealEventListener extends StateMachineListenerAdapter<BaseState, BaseEvent> {

    /**
     * 获取所有的转换结束动作
     * <p>
     * 可以根据需要判断更多的阶段枚举StateContext.Stage
     */
    public abstract List<AbstractOrderAction> getTransitionEndedActionList();

    @Override
    public void stateContext(StateContext<BaseState, BaseEvent> context) {

        //TRANSITION_END代表转换已经完成，在此之前已经persist落地了订单新的状态。
        if (context.getStage() == StateContext.Stage.TRANSITION_END) {
            // 屏蔽掉状态机初始化状态
            if (!Objects.isNull(context.getTransition().getSource())) {
                log.info("common 执行TransitionEndedAction...");
                List<AbstractOrderAction> actions = getTransitionEndedActionList();
                for (AbstractOrderAction action : actions) {
                    action.execute(context);
                }
            }
        }

        if (context.getStage() == StateContext.Stage.EVENT_NOT_ACCEPTED) {
            BasicOrderEvent<BaseEvent> event = (BasicOrderEvent<BaseEvent>) context.getMessageHeader("_event");
            log.error("触发事件时状态非法：orderId={},状态={},statemachine={}", event.getOrderId(),
                    context.getSource().getId().code(), event.getEventType());
            context.getStateMachine().setStateMachineError(new RuntimeException("触发事件与订单状态不匹配"));
        }
    }
}
