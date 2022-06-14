package zjt.learn.statemachine.depositorder;

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
public enum DepositOrderStatus implements BaseState {
    WAIT_PAYMENT_DEPOSIT(1,"待支付"),
    WAIT_DELIVER_DEPOSIT(2,"待发货"),
    WAIT_RECEIVE_DEPOSIT(3,"待收货"),
    FINISH_DEPOSIT(4,"订单结束"),
    ;

    @Getter
    private Integer code;
    @Getter
    private String desc;

    public static DepositOrderStatus getOrderStatusFromCode(Integer code){
        for(DepositOrderStatus orderStatus: DepositOrderStatus.values()){
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
