package zjt.learn.statemachine.base.event;




import zjt.learn.statemachine.base.BaseEvent;

import java.util.UUID;

public class BasicOrderEvent<E extends BaseEvent> {
    final String eventId = UUID.randomUUID().toString();
    private Long orderId;
    private E eventType;

    /**
     * @return the orderId
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the eventType
     */
    public E getEventType() {
        return eventType;
    }

    /**
     * @param eventType the eventType to set
     */
    public void setEventType(E eventType) {
        this.eventType = eventType;
    }

    /**
     * @return the eventId
     */
    public String getEventId() {
        return eventId;
    }

}