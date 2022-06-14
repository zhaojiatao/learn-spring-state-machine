package zjt.learn.statemachine.simpleorder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import zjt.learn.statemachine.base.BaseState;

/**
 * 功能：
 * 订单状态枚举类
 * @Author: zhaojiatao
 * @Date: 2021/11/7 13:02
 * @ClassName: OrderStatus
 * @Version: 1.0.0
 */
@AllArgsConstructor
public enum SimpleOrderStatus implements BaseState {
    WAIT_PAYMENT(1,"待支付"),
    WAIT_DELIVER(2,"待发货"),
    WAIT_RECEIVE(3,"待收货"),
    FINISH(4,"订单完成"),
    CLOSED(-1,"订单关闭"),
    ;

    @Getter
    private Integer code;
    @Getter
    private String desc;

    public static SimpleOrderStatus getOrderStatusFromCode(Integer code){
        for(SimpleOrderStatus orderStatus: SimpleOrderStatus.values()){
            if(code.intValue()==orderStatus.getCode().intValue()){
                return orderStatus;
            }
        }
        return null;
    }

    @Override
    public Integer code() {
        return this.getCode();
    }

    @Override
    public String desc() {
        return this.getDesc();
    }
}
