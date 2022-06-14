package zjt.learn.statemachine.base.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import zjt.learn.statemachine.base.BaseEvent;

@Data
@EqualsAndHashCode(callSuper = true)
public class HumanOperationOrderEvent<E extends BaseEvent> extends BasicOrderEvent<E> {
    /**
     * 操作人
     */
    protected String operatorId;
    /**
     * 操作人名
     */
    protected String operatorName;
    /**
     * 审批意见
     */
    protected String approvalResult;

}