package zjt.learn.statemachine.depositorder.config;

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
import zjt.learn.statemachine.depositorder.DepositOrderEvent;
import zjt.learn.statemachine.depositorder.DepositOrderStatus;
import zjt.learn.statemachine.depositorder.action.DepositPayAction;
import zjt.learn.statemachine.depositorder.action.DepositDeliveryAction;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 功能：
 * 定金订单状态机配置
 *
 * @Author: zhaojiatao
 * @Date: 2021/11/7 20:56
 * @ClassName: DepositOrderStateMachineFactoryConfig
 * @Version: 1.0.0
 */

@Configuration
@EnableStateMachineFactory(name = "depositOrderFactory")
public class DepositOrderStateMachineFactoryConfig extends StateMachineConfigurerAdapter<BaseState, BaseEvent> {

    /**
     * 持久化组件使用公共的
     */
    @Autowired
    @Qualifier(value = "commonStateMachineRuntimePersister")
    private StateMachineRuntimePersister<BaseState, BaseEvent, String> commonStateMachineRuntimePersister;

    /**
     * 每次状态流转都会执行的事件监听器
     */
    @Autowired
    @Qualifier(value = "depositOrderEventListener")
    private CommonDealEventListener simpleOrderEventListener;

    /**
     * 流程特有的action
     */
    @Autowired
    private DepositPayAction depositPayAction;

    /**
     * 流程特有的action
     */
    @Autowired
    private DepositDeliveryAction depositDeliveryAction;


    /**
     * 配置通用事件监听器+持久化组件
     * @param config
     * @throws Exception
     */
    @Override
    public void configure(StateMachineConfigurationConfigurer<BaseState, BaseEvent> config) throws Exception {
        config.withConfiguration().listener(simpleOrderEventListener)
                .and()
                .withPersistence().runtimePersister(commonStateMachineRuntimePersister);
    }

    /**
     * 配置状态
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<BaseState, BaseEvent> states) throws Exception {
        Set<BaseState> statusSet = new HashSet<>();
        Collections.addAll(statusSet, DepositOrderStatus.values());
        states.withStates()
                .initial(DepositOrderStatus.WAIT_PAYMENT_DEPOSIT)//初始化状态
                .states(statusSet);
    }

    /**
     * 配置事件流转
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<BaseState, BaseEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(DepositOrderStatus.WAIT_PAYMENT_DEPOSIT).target(DepositOrderStatus.WAIT_DELIVER_DEPOSIT)
                .event(DepositOrderEvent.PAYED_DEPOSIT).action(depositPayAction)

                .and().withExternal()
                .source(DepositOrderStatus.WAIT_DELIVER_DEPOSIT).target(DepositOrderStatus.WAIT_RECEIVE_DEPOSIT)
                .event(DepositOrderEvent.DELIVERY_DEPOSIT).action(depositDeliveryAction)

                .and().withExternal()
                .source(DepositOrderStatus.WAIT_RECEIVE_DEPOSIT).target(DepositOrderStatus.FINISH_DEPOSIT)
                .event(DepositOrderEvent.RECEIVED_DEPOSIT).action(depositDeliveryAction)
        ;


    }

}
