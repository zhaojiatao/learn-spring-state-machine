package zjt.learn.service.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zjt.learn.repository.dataobj.OrderInfoDO;
import zjt.learn.repository.mapper.OrderInfoMapper;
import zjt.learn.service.IOrderService;
import zjt.learn.statemachine.base.event.PushOrderEvent;
import zjt.learn.statemachine.base.eventbus.IOrderEventBus;
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
@Service
public class OrderServiceImpl implements IOrderService {
    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private IOrderEventBus orderEventBus;

    public Long makeOrder(String orderNo){
        OrderInfoDO orderInfoDO=new OrderInfoDO();
        orderInfoDO.setOrderNo(orderNo);
        orderInfoDO.setState(SimpleOrderStatus.WAIT_PAYMENT.code());
        orderInfoMapper.insert(orderInfoDO);
        return orderInfoDO.getId();
    }


    public void payOrder(Long orderId){
        OrderInfoDO orderInfoDO = orderInfoMapper.selectById(orderId);
        orderEventBus.submitEvent(new PushOrderEvent(orderId, SimpleOrderEvent.PAYED));

    }



}
