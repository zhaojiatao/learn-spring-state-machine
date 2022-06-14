package zjt.learn.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import zjt.learn.SpringStateMachineApplicationTests;
import zjt.learn.enums.OrderTypeEnum;
import zjt.learn.service.IOrderService;

@Slf4j
public class OrderServiceImplTest extends SpringStateMachineApplicationTests {

    @Autowired
    private IOrderService orderService;

    @Test
    public void makeSimpleOrder() {
        Long id = orderService.makeOrder("SO20220614", OrderTypeEnum.SIMPLE_ORDER.getCode());
        log.info("id={}",id);
    }

    @Test
    public void payOrder() {
        orderService.payOrder(1L);
    }

    @Test
    public void delivery() {
        orderService.delivery(1L);
    }

    @Test
    public void received() {
        orderService.received(1L);
    }

    @Test
    public void closeOrder() {
        orderService.closeOrder(1L);
    }

    //************************************************************
    @Test
    public void makeDepositOrder() {
        Long id = orderService.makeOrder("SO20220615", OrderTypeEnum.DEPOSIT_ORDER.getCode());
        log.info("id={}",id);
    }

    @Test
    public void payDepositOrder() {
        orderService.payOrder(2L);
    }

    @Test
    public void deliveryDeposit() {
        orderService.delivery(2L);
    }

    @Test
    public void receivedDeposit() {
        orderService.received(2L);
    }

}