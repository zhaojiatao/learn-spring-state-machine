package zjt.learn.statemachine.simpleorder.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zjt.learn.statemachine.base.action.AbstractOrderAction;
import zjt.learn.statemachine.base.listener.CommonDealEventListener;
import zjt.learn.statemachine.simpleorder.action.SimpleCommonAction01;

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
public class SimpleOrderEventListenerConfig {

    @Autowired
    private SimpleCommonAction01 simpleCommonAction01;

    //TODO 可以配置多个action


    @Bean("simpleOrderEventListener")
    public CommonDealEventListener simpleOrderEventListener() {
        CommonDealEventListener listener = new CommonDealEventListener();
        List<AbstractOrderAction> actionList = new ArrayList<>();
        actionList.add(simpleCommonAction01);


        listener.setTransitionEndedActionList(actionList);
        return listener;
    }
}
