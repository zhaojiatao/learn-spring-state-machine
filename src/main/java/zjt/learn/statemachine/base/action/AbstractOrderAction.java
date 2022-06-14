package zjt.learn.statemachine.base.action;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.transition.Transition;
import org.springframework.util.Assert;
import zjt.learn.statemachine.base.BaseEvent;
import zjt.learn.statemachine.base.BaseState;
import zjt.learn.statemachine.base.event.BasicOrderEvent;

/**
 * 功能：
 * 所有的action都继承于此
 * @Author: zhaojiatao
 * @Date: 2021/11/7 21:23
 * @ClassName: AbstractOrderAction
 * @Version: 1.0.0
 */
@Slf4j
public abstract class AbstractOrderAction implements Action<BaseState, BaseEvent> {

    @Override
    public void execute(StateContext<BaseState, BaseEvent> context) {
        try {
            BasicOrderEvent<BaseEvent> event = (BasicOrderEvent<BaseEvent>) context.getMessageHeader("_event");
            Assert.isTrue(event.getEventType() == context.getEvent(), "事件类型不一致");
            Transition<BaseState, BaseEvent> transition = context.getTransition();
            doAction(event, transition.getSource().getId(), transition.getTarget().getId());
        } catch (Exception e) {
            log.error(String.format("调用action业务异常:context=%s", JSON.toJSONString(context)), e);
            context.getStateMachine().setStateMachineError(e);
        }
    }

    /**
     * 事件处理
     *
     * @param event
     * @param source
     * @param target
     */
    protected abstract void doAction(BasicOrderEvent<BaseEvent> event, BaseState source, BaseState target);

}