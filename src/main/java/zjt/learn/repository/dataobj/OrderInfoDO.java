package zjt.learn.repository.dataobj;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/6/14 13:17
 * @ClassName: OrderInfoDO
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "order_info",autoResultMap = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoDO implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单状态
     */
    private Integer state;


    /**
     * 订单类型
     */
    private Integer orderType;
}
