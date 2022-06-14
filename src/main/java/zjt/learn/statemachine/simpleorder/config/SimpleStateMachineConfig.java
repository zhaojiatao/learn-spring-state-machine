package zjt.learn.statemachine.simpleorder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.service.DefaultStateMachineService;
import org.springframework.statemachine.service.StateMachineService;
import zjt.learn.statemachine.base.BaseEvent;
import zjt.learn.statemachine.base.BaseState;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/11/8 15:33
 * @ClassName: SimpleStateMachineConfig
 */
@Configuration
public class SimpleStateMachineConfig {

    @Autowired
    @Qualifier("simpleOrderFactory")
    StateMachineFactory<BaseState, BaseEvent> simpleOrderFactory;

    @Bean(name = "simpleOrderStateMachineService")
    public StateMachineService<BaseState, BaseEvent> stateMachineService(
            StateMachineRuntimePersister<BaseState, BaseEvent, String> commonStateMachineRuntimePersister) {
        return new DefaultStateMachineService<>(simpleOrderFactory, commonStateMachineRuntimePersister);
    }

}
