package zjt.learn.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import zjt.learn.statemachine.simpleorder.SimpleOrderStatus;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/6/14 17:31
 * @ClassName: OrderTypeEnum
 */
@AllArgsConstructor
public enum OrderTypeEnum {
    SIMPLE_ORDER(1,"普通订单"),
    DEPOSIT_ORDER(2,"定金订单"),
    ;

    @Getter
    private Integer code;
    @Getter
    private String desc;

    public static OrderTypeEnum fromCode(Integer code){
        for(OrderTypeEnum e: OrderTypeEnum.values()){
            if(code.intValue()==e.getCode().intValue()){
                return e;
            }
        }
        return null;
    }

    public Integer code() {
        return this.getCode();
    }

    public String desc() {
        return this.getDesc();
    }

}
