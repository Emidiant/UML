package ru.ifmo.uml.ui.controllers;

import ru.ifmo.uml.dal.dto.ProductOrder;
import ru.ifmo.uml.dal.implementations.OrderImpl;
import ru.ifmo.uml.dal.implementations.ProductOrderImpl;
import ru.ifmo.uml.dal.interfaces.OrderDao;
import ru.ifmo.uml.dal.interfaces.ProductOrderDao;
import ru.ifmo.uml.entity.Order;
import ru.ifmo.uml.entity.ProductWithAmount;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private ProductRepository productRepository;
    private OrderDao orderDao;
    private ProductOrderDao productOrderDao;


    public OrderManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.orderDao = new OrderImpl();
        this.productOrderDao = new ProductOrderImpl();
    }
    public Order getById(int id){
        ru.ifmo.uml.dal.dto.Order orderDto = orderDao.getById(id);
        if (orderDto != null) {
            List<ProductWithAmount> list = new ArrayList<>();
            for (ProductOrder productOrder : productOrderDao.getByOrderId(orderDto)) {
                list.add(new ProductWithAmount(productRepository.getProductByProductId(productOrder.getProductId()), productOrder.getProductId(), productOrder.getCount()));
            }
            Order order = new Order(orderDto.getOrderId(), orderDto.getCustomerId(), orderDto.getDeliveryType(), orderDto.getStatus(), list);
            return order;
        }
        else return null;
    }
}
