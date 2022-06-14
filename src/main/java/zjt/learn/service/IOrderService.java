package zjt.learn.service;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/6/14 16:59
 * @ClassName: IOrderService
 */
public interface IOrderService {
    /**
     * 下单
     * @param orderNo
     * @param orderType
     * @return
     */
    Long makeOrder(String orderNo,Integer orderType);

    /**
     * 支付
     * @param orderId
     */
    void payOrder(Long orderId);

    /**
     * 发货
     * @param orderId
     */
    void delivery(Long orderId);

    /**
     * 收货
     * @param orderId
     */
    void received(Long orderId);

    /**
     * 关单
     * @param orderId
     */
    void closeOrder(Long orderId);
}
