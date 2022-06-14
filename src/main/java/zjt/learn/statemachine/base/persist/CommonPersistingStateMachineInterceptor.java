package zjt.learn.statemachine.base.persist;

import lombok.Setter;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.AbstractPersistingStateMachineInterceptor;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.support.StateMachineInterceptor;
import zjt.learn.statemachine.base.BaseEvent;
import zjt.learn.statemachine.base.BaseState;

public class CommonPersistingStateMachineInterceptor
        extends AbstractPersistingStateMachineInterceptor<BaseState, BaseEvent, String>
        implements StateMachineRuntimePersister<BaseState, BaseEvent, String> {

    @Setter
    private StateMachinePersist<BaseState, BaseEvent, String> stateMachinePersist;

    @Override
    public void write(StateMachineContext<BaseState, BaseEvent> context, String contextObj) throws Exception {
        stateMachinePersist.write(context, contextObj);
    }

    @Override
    public StateMachineContext<BaseState, BaseEvent> read(String contextObj) throws Exception {
        return stateMachinePersist.read(contextObj);
    }

    @Override
    public StateMachineInterceptor<BaseState, BaseEvent> getInterceptor() {
        return this;
    }
}
