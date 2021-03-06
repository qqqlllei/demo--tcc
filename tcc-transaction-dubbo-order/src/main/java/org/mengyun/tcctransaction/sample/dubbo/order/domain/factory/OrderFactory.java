
package org.mengyun.tcctransaction.sample.dubbo.order.domain.factory;

import org.apache.commons.lang3.tuple.Pair;
import org.mengyun.tcctransaction.sample.dubbo.order.domain.entity.Order;
import org.mengyun.tcctransaction.sample.dubbo.order.domain.entity.OrderLine;
import org.mengyun.tcctransaction.sample.dubbo.order.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by changming.xie on 4/1/16.
 */
@Component
public class OrderFactory {

    @Autowired
    ProductRepository productRepository;


	/**
	 * 构建订单信息
	 * @param payerUserId
	 * @param payeeUserId
	 * @param productQuantities
	 * @return
	 */
    public Order buildOrder(long payerUserId, long payeeUserId, List<Pair<Long, Integer>> productQuantities) {

        Order order = new Order(payerUserId, payeeUserId);

        for (Pair<Long, Integer> pair : productQuantities) {
            long productId = pair.getLeft();
            order.addOrderLine(new OrderLine(productId, pair.getRight(),productRepository.findById(productId).getPrice()));
        }

        return order;
    }
}
