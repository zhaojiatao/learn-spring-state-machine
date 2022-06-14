package zjt.learn.statemachine.base.eventbus;


import zjt.learn.statemachine.base.BaseEvent;
import zjt.learn.statemachine.base.event.BasicOrderEvent;

/**
 * 流程推动
 */
public interface IOrderEventBus {
    /**
     * 提交事件
     *
     * @param orderEvent
     * @return
     */
    void submitEvent(BasicOrderEvent<? extends BaseEvent> orderEvent);
}
