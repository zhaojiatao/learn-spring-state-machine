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
    // 支付，发货，确认收货
    PAYED_DEPOSIT, DELIVERY_DEPOSIT, RECEIVED_DEPOSIT;
}
