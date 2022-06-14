package zjt.learn.statemachine.simpleorder;


import zjt.learn.statemachine.base.BaseEvent;

/**
 * 功能：
 * 订单状态改变事件
 * @Author: zhaojiatao
 * @Date: 2021/11/7 13:03
 * @ClassName: OrderStatusChangeEvent
 * @Version: 1.0.0
 */
public enum SimpleOrderEvent implements BaseEvent {
    /**
     * 支付
     */
    PAYED,
    /**
     * 发货
     */
    DELIVERY,
    /**
     * 确认收货
     */
    RECEIVED,

    ;
}
