package zjt.learn.statemachine.base.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import zjt.learn.statemachine.base.BaseEvent;
import zjt.learn.statemachine.base.BaseState;


@Configuration
public class StateMachineCommonSpringConfig {

    @Autowired
    @Qualifier(value = "commonStateMachinePersist")
    private StateMachinePersist<BaseState, BaseEvent, String> commonStateMachinePersist;

    @Bean(name = "commonStateMachineRuntimePersister")
    public StateMachineRuntimePersister<BaseState, BaseEvent, String> commonStateMachineRuntimePersister() {
        CommonPersistingStateMachineInterceptor commonPersistingStateMachineInterceptor = new CommonPersistingStateMachineInterceptor();
        commonPersistingStateMachineInterceptor.setStateMachinePersist(commonStateMachinePersist);
        return commonPersistingStateMachineInterceptor;
    }

}
