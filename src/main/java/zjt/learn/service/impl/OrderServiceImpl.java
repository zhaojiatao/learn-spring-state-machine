package zjt.learn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zjt.learn.enums.OrderTypeEnum;
import zjt.learn.repository.dataobj.OrderInfoDO;
import zjt.learn.repository.mapper.OrderInfoMapper;
import zjt.learn.service.IOrderService;
import zjt.learn.statemachine.base.event.PushOrderEvent;
import zjt.learn.statemachine.base.eventbus.IOrderEventBus;
import zjt.learn.statemachine.depositorder.DepositOrderEvent;
import zjt.learn.statemachine.simpleorder.SimpleOrderEvent;
import zjt.learn.statemachine.simpleorder.SimpleOrderStatus;

import javax.annotation.Resource;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/6/14 17:00
 * @ClassName: OrderServiceImpl
 */
@Component
public class OrderServiceImpl implements IOrderService {
    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private IOrderEventBus orderEventBus;

    @Override
    public Long makeOrder(String orderNo,Integer orderType){
        OrderInfoDO orderInfoDO=new OrderInfoDO();
        orderInfoDO.setOrderNo(orderNo);
        orderInfoDO.setState(SimpleOrderStatus.WAIT_PAYMENT.code());
        orderInfoDO.setOrderType(orderType);
        orderInfoMapper.insert(orderInfoDO);
        return orderInfoDO.getId();
    }

    @Override
    public void payOrder(Long orderId){
        OrderInfoDO orderInfoDO = orderInfoMapper.selectById(orderId);
        switch (OrderTypeEnum.fromCode(orderInfoDO.getOrderType())){
            case SIMPLE_ORDER:
                orderEventBus.submitEvent(new PushOrderEvent(orderId, SimpleOrderEvent.PAYED));
                break;
            case DEPOSIT_ORDER:
                orderEventBus.submitEvent(new PushOrderEvent(orderId, DepositOrderEvent.PAYED_DEPOSIT));
                break;
            default:
                break;
        }

    }

    @Override
    public void delivery(Long orderId) {
        OrderInfoDO orderInfoDO = orderInfoMapper.selectById(orderId);
        switch (OrderTypeEnum.fromCode(orderInfoDO.getOrderType())){
            case SIMPLE_ORDER:
                orderEventBus.submitEvent(new PushOrderEvent(orderId, SimpleOrderEvent.DELIVERY));
                break;
            case DEPOSIT_ORDER:
                orderEventBus.submitEvent(new PushOrderEvent(orderId, DepositOrderEvent.DELIVERY_DEPOSIT));
                break;
            default:
                break;
        }
    }

    @Override
    public void received(Long orderId) {
        OrderInfoDO orderInfoDO = orderInfoMapper.selectById(orderId);
        switch (OrderTypeEnum.fromCode(orderInfoDO.getOrderType())){
            case SIMPLE_ORDER:
                orderEventBus.submitEvent(new PushOrderEvent(orderId, SimpleOrderEvent.RECEIVED));
                break;
            case DEPOSIT_ORDER:
                orderEventBus.submitEvent(new PushOrderEvent(orderId, DepositOrderEvent.RECEIVED_DEPOSIT));
                break;
            default:
                break;
        }
    }

    @Override
    public void closeOrder(Long orderId) {
        OrderInfoDO orderInfoDO = orderInfoMapper.selectById(orderId);
        switch (OrderTypeEnum.fromCode(orderInfoDO.getOrderType())){
            case SIMPLE_ORDER:
                orderEventBus.submitEvent(new PushOrderEvent(orderId, SimpleOrderEvent.CLOSE));
                break;
            case DEPOSIT_ORDER:
                orderEventBus.submitEvent(new PushOrderEvent(orderId, DepositOrderEvent.CLOSE_DEPOSIT));
                break;
            default:
                break;
        }
    }


}
