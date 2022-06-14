package zjt.learn.statemachine.base;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/11/7 20:47
 * @ClassName: BaseState
 * @Version: 1.0.0
 */
public interface BaseState {
    /**
     * 状态code
     *
     * @return
     */
    Integer code();

    /**
     * 状态描述
     *
     * @return
     */
    String desc();

    /**
     * 获取枚举name
     * @return
     */
    String name();
}
