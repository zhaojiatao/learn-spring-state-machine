package zjt.learn.statemachine.depositorder.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zjt.learn.statemachine.base.action.AbstractOrderAction;
import zjt.learn.statemachine.base.listener.CommonDealEventListener;
import zjt.learn.statemachine.depositorder.action.DepositCommonAction01;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/11/7 21:22
 * @ClassName: SimpleOrderEventListenerConfig
 * @Version: 1.0.0
 */
@Configuration
public class DepositOrderEventListenerConfig {

    @Autowired
    private DepositCommonAction01 depositCommonAction01;

    //TODO 可以配置多个action


    @Bean("depositOrderEventListener")
    public CommonDealEventListener simpleOrderEventListener() {
        CommonDealEventListener listener = new CommonDealEventListener();
        List<AbstractOrderAction> actionList = new ArrayList<>();
        actionList.add(depositCommonAction01);

        listener.setTransitionEndedActionList(actionList);
        return listener;
    }
}
