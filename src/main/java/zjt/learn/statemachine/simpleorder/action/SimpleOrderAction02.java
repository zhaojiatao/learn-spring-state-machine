package zjt.learn.statemachine.simpleorder.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import zjt.learn.repository.dataobj.OrderInfoDO;
import zjt.learn.repository.mapper.OrderInfoMapper;
import zjt.learn.statemachine.base.BaseEvent;
import zjt.learn.statemachine.base.BaseState;
import zjt.learn.statemachine.base.action.AbstractOrderAction;
import zjt.learn.statemachine.base.event.BasicOrderEvent;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/11/7 21:26
 * @ClassName: SendDingDingMsgAction
 * @Version: 1.0.0
 */
@Component
@Slf4j
public class SimpleOrderAction02 extends AbstractOrderAction {
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Override
    protected void doAction(BasicOrderEvent<BaseEvent> event, BaseState source, BaseState target) {
        Long orderId = event.getOrderId();
        OrderInfoDO orderInfoDO = orderInfoMapper.selectById(orderId);
        log.info("★★★★★★★ SimpleOrderAction02，orderId={} ★★★★★★★",orderId);

        //TODO 发送钉钉消息
        if (TransactionSynchronizationManager.isActualTransactionActive()){
            log.info("存在事务");
        }
    }
}
