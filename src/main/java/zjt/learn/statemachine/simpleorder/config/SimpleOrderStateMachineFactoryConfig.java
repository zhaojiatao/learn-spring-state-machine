package zjt.learn.statemachine.simpleorder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import zjt.learn.statemachine.base.BaseEvent;
import zjt.learn.statemachine.base.BaseState;
import zjt.learn.statemachine.base.listener.CommonDealEventListener;
import zjt.learn.statemachine.simpleorder.SimpleOrderEvent;
import zjt.learn.statemachine.simpleorder.SimpleOrderStatus;
import zjt.learn.statemachine.simpleorder.action.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 功能：
 * 普通订单状态机配置
 *
 * @Author: zhaojiatao
 * @Date: 2021/11/7 20:56
 * @ClassName: SimpleOrderStateMachineConfig
 * @Version: 1.0.0
 */

@Configuration
@EnableStateMachineFactory(name = "simpleOrderFactory")
public class SimpleOrderStateMachineFactoryConfig extends StateMachineConfigurerAdapter<BaseState, BaseEvent> {

    @Autowired
    @Qualifier(value = "commonStateMachineRuntimePersister")
    private StateMachineRuntimePersister<BaseState, BaseEvent, String> commonStateMachineRuntimePersister;

    @Autowired
    @Qualifier(value = "simpleOrderEventListener")
    private CommonDealEventListener simpleOrderEventListener;

    @Autowired
    private SimpleOrderAction01 simpleOrderAction01;
    @Autowired
    private SimpleOrderAction02 simpleOrderAction02;

    @Autowired
    private SimpleOrderPayAction simpleOrderPayAction;

    @Autowired
    private SimpleOrderDeliveryAction simpleOrderDeliveryAction;

    @Autowired
    private SimpleOrderReceiveAction simpleOrderReceiveAction;

    @Autowired
    private SimpleCloseOrderAction simpleCloseOrderAction;


    @Override
    public void configure(StateMachineConfigurationConfigurer<BaseState, BaseEvent> config) throws Exception {
        config.withConfiguration().listener(simpleOrderEventListener)
                .and()
                .withPersistence().runtimePersister(commonStateMachineRuntimePersister);
    }

    @Override
    public void configure(StateMachineStateConfigurer<BaseState, BaseEvent> states) throws Exception {
        Set<BaseState> statusSet = new HashSet<>();
        Collections.addAll(statusSet, SimpleOrderStatus.values());
        states.withStates().initial(SimpleOrderStatus.WAIT_PAYMENT).states(statusSet);
    }


    @Override
    public void configure(StateMachineTransitionConfigurer<BaseState, BaseEvent> transitions) throws Exception {
        transitions.withExternal()
                //待付款->待发货
                .source(SimpleOrderStatus.WAIT_PAYMENT).target(SimpleOrderStatus.WAIT_DELIVER)
                .event(SimpleOrderEvent.PAYED)
                .action(simpleOrderPayAction).action(simpleOrderAction01)//可以配置多个action
                .and().withExternal()
                //待付款->订单关闭
                .source(SimpleOrderStatus.WAIT_PAYMENT).target(SimpleOrderStatus.CLOSED)
                .event(SimpleOrderEvent.CLOSE).action(simpleCloseOrderAction)

                .and().withExternal()
                //待发货->待收货
                .source(SimpleOrderStatus.WAIT_DELIVER).target(SimpleOrderStatus.WAIT_RECEIVE)
                .event(SimpleOrderEvent.DELIVERY)
                .action(simpleOrderDeliveryAction).action(simpleOrderAction02)
                .and().withExternal()
                //待发货->订单关闭
                .source(SimpleOrderStatus.WAIT_DELIVER).target(SimpleOrderStatus.CLOSED)
                .event(SimpleOrderEvent.CLOSE).action(simpleCloseOrderAction)

                .and().withExternal()
                //待收货->订单完成
                .source(SimpleOrderStatus.WAIT_RECEIVE).target(SimpleOrderStatus.FINISH)
                .event(SimpleOrderEvent.RECEIVED).action(simpleOrderReceiveAction)
                .and().withExternal()
                //待收货->订单关闭
                .source(SimpleOrderStatus.WAIT_RECEIVE).target(SimpleOrderStatus.CLOSED)
                .event(SimpleOrderEvent.CLOSE).action(simpleCloseOrderAction)
        ;


    }

}
