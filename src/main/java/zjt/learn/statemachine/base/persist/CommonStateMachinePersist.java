package zjt.learn.statemachine.base.persist;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultExtendedState;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import zjt.learn.enums.OrderTypeEnum;
import zjt.learn.repository.dataobj.OrderInfoDO;
import zjt.learn.repository.mapper.OrderInfoMapper;
import zjt.learn.statemachine.base.BaseEvent;
import zjt.learn.statemachine.base.BaseState;
import zjt.learn.statemachine.depositorder.DepositOrderStatus;
import zjt.learn.statemachine.simpleorder.SimpleOrderStatus;

import java.util.ArrayList;

@Slf4j
@Component("commonStateMachinePersist")
public class CommonStateMachinePersist implements StateMachinePersist<BaseState, BaseEvent, String> {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /**
     * 落地状态
     * @param context
     * @param contextObj
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void write(StateMachineContext<BaseState, BaseEvent> context, String contextObj) {
        Long orderId = Long.valueOf(contextObj);
        OrderInfoDO orderInfoDO = orderInfoMapper.selectById(orderId);
        log.info("修改前订单状态={}",orderInfoDO.getState());
        BaseState statusEnum = context.getState();
        orderInfoDO.setState(statusEnum.code());
        orderInfoMapper.updateById(orderInfoDO);
        log.info("修改后订单状态={}",orderInfoDO.getState());

    }


    /**
     * 根据订单号构造状态机上下文
     * @param contextObj
     * @return
     */
    @Override
    public StateMachineContext<BaseState, BaseEvent> read(String contextObj) {
        Long orderId = Long.valueOf(contextObj);
        OrderInfoDO orderInfoDO = orderInfoMapper.selectById(orderId);
        BaseState state;
        switch (OrderTypeEnum.fromCode(orderInfoDO.getOrderType())){
            case SIMPLE_ORDER:
                state = SimpleOrderStatus.getOrderStatusFromCode(orderInfoDO.getState());
                break;
            case DEPOSIT_ORDER:
                state = DepositOrderStatus.getOrderStatusFromCode(orderInfoDO.getState());
                break;
            default:
                throw new RuntimeException();
        }

        return new DefaultStateMachineContext<>(
                new ArrayList<>(),
                state,
                null,
                null,
                new DefaultExtendedState(),
                null,
                contextObj);
    }
}
