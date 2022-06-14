package zjt.learn.statemachine.depositorder;


import zjt.learn.statemachine.base.BaseEvent;

/**
 * 功能：
 * 订单状态改变事件
 * @Author: zhaojiatao
 * @Date: 2021/11/7 13:03
 * @ClassName: OrderStatusChangeEvent
 * @Version: 1.0.0
 */
public enum DepositOrderEvent implements BaseEvent {
    /**
     * 支付
     */
    PAYED_DEPOSIT,
    /**
     * 发货
     */
    DELIVERY_DEPOSIT,
    /**
     * 确认收货
     */
    RECEIVED_DEPOSIT,
    /**
     * 关单
     */
    CLOSE_DEPOSIT,
    ;
}
