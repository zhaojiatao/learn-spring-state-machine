package zjt.learn.statemachine.base.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import zjt.learn.statemachine.base.BaseEvent;

/**
 * 推动订单状态
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PushOrderEvent extends HumanOperationOrderEvent<BaseEvent> {

    public PushOrderEvent(Long orderId, BaseEvent event) {
        this(orderId, event, null, null, null);
    }

    public PushOrderEvent(Long orderId, BaseEvent event, String operator, String operatorName, String approvalResult) {
        this.setEventType(event);
        this.setOrderId(orderId);
        this.approvalResult = approvalResult;
        this.operatorId = operator;
        this.operatorName = operatorName;
    }

    public PushOrderEvent(Long orderId, BaseEvent event, String operatorName) {
        this.setEventType(event);
        this.setOrderId(orderId);
        this.operatorId = operatorName;
        this.operatorName = operatorName;
    }

}
