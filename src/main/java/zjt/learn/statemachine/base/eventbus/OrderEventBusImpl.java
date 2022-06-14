package zjt.learn.statemachine.base.eventbus;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import zjt.learn.enums.OrderTypeEnum;
import zjt.learn.repository.dataobj.OrderInfoDO;
import zjt.learn.repository.mapper.OrderInfoMapper;
import zjt.learn.statemachine.base.BaseEvent;
import zjt.learn.statemachine.base.BaseState;
import zjt.learn.statemachine.base.event.BasicOrderEvent;

import javax.annotation.Resource;

@Slf4j
@Service("orderEventBus")
public class OrderEventBusImpl implements IOrderEventBus {

    @Autowired
    private OrderInfoMapper orderInfoMapper;


    @Autowired
    @Qualifier("simpleOrderStateMachineService")
    private StateMachineService<BaseState, BaseEvent> simpleOrderStateMachineService;

    @Resource(name = "depositOrderStateMachineService")
    private StateMachineService<BaseState, BaseEvent> depositOrderStateMachineService;



    private final static String EXCEPTION_CODE = "SYSTEM_EXCEPTION";


    private StateMachineService<BaseState, BaseEvent> choseMachineServiceByOrderType(Long orderId ){
        OrderInfoDO orderInfoDO = orderInfoMapper.selectById(orderId);

        switch (OrderTypeEnum.fromCode(orderInfoDO.getOrderType())){
            case SIMPLE_ORDER:
                return simpleOrderStateMachineService;
            case DEPOSIT_ORDER:
                return depositOrderStateMachineService;
            default:
                break;
        }

        return null;
    }


    @Override
    public void submitEvent(BasicOrderEvent<? extends BaseEvent> orderEvent) {
        Long orderId = orderEvent.getOrderId();
        Assert.notNull(orderId, "orderId不能为空");
        StateMachineService<BaseState, BaseEvent> stateMachineService=choseMachineServiceByOrderType(orderId);
        try {
            //获取状态机
            StateMachine<BaseState, BaseEvent> machine = stateMachineService.acquireStateMachine(orderId.toString(), false);
            Message<?> message = MessageBuilder.withPayload(orderEvent.getEventType()).setHeader("_event", orderEvent).build();
            machine.getStateMachineAccessor().doWithRegion(function -> function.addStateMachineInterceptor(
                    new StateMachineInterceptorAdapter<BaseState, BaseEvent>() {
                        @Override
                        public Exception stateMachineError(StateMachine<BaseState, BaseEvent> stateMachine, Exception exception) {
                            stateMachine.getExtendedState().getVariables().put(EXCEPTION_CODE, exception);
                            return exception;
                        }
                    }));
            machine.start();

            machine.sendEvent((Message<BaseEvent>) message);
            if (machine.hasStateMachineError()) {
                log.error("状态机推动发生错误,machine.getExtendedState().getVariables()==>{}", JSONObject.toJSONString(machine.getExtendedState().getVariables()));
                throw (Exception) machine.getExtendedState().getVariables().get(EXCEPTION_CODE);
            }
        } catch (Exception e) {
            log.error("订单{}状态机处理异常.", orderId, e);
            throw new RuntimeException("LOAN_SECOND_CAR_SYSTEM_EXCEPTION");
        } finally {
            stateMachineService.releaseStateMachine(orderId.toString(), true);
        }
    }
}
