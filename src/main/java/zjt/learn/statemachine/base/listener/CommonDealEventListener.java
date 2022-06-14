package zjt.learn.statemachine.base.listener;

import lombok.Setter;
import zjt.learn.statemachine.base.action.AbstractOrderAction;
import java.util.List;

/**
 * 注意，所有的action的操作，在抽象类AbstractCommonDealEventListener的action.execute(context);循环中执行
 */
public class CommonDealEventListener extends AbstractCommonDealEventListener {

    @Setter
    private List<AbstractOrderAction> transitionEndedActionList;

    @Override
    public List<AbstractOrderAction> getTransitionEndedActionList() {
        return transitionEndedActionList;
    }

}
