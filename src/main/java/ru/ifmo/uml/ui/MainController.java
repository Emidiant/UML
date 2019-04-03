package ru.ifmo.uml.ui;

import ru.ifmo.uml.dal.implementations.OrderImpl;
import ru.ifmo.uml.dal.implementations.ProductImpl;
import ru.ifmo.uml.dal.implementations.ProductOrderImpl;
import ru.ifmo.uml.dal.implementations.SizeImpl;
import ru.ifmo.uml.entity.Order;
import ru.ifmo.uml.entity.Product;
import ru.ifmo.uml.entity.ProductOrder;
import ru.ifmo.uml.entity.Size;


public class MainController {
    public static void main(String[] args) {
        //тест
        //size+
        //booking+
        //product+
        //product order

        ProductOrderImpl productOrderImpl = new ProductOrderImpl();
        ProductOrder productOrder = new ProductOrder();

        //productOrder.setOrderId(1);
        //productOrder.setCount(2);
        //productOrder.setProductId(2);
        //Pair<Integer, Integer> pair = new Pair<>(2, 1);
        //System.out.println(productOrderImpl.getById(pair));

        //productOrderImpl.update(productOrder);

        SizeImpl sizeImpl = new SizeImpl();
        Size size = new Size();
        size.setSizeName("xxl");

        ProductImpl productImpl = new ProductImpl();
        Product product = new Product();

        Order order = new Order();
        OrderImpl orderImpl = new OrderImpl();


        //get by ID
        Size seze1 = sizeImpl.getById(2);
        System.out.println(seze1.toString());
        System.out.println("\n");
        Product product1 = productImpl.getById(2);
        System.out.println(product1.toString());
        System.out.println(orderImpl.getById(1));

        //ADD
        //sizeImpl.add(size);
        //bookingImpl.add(booking);
        //productImpl.add(product);

        //UPDATE
        //size.setSizeName("XXL");
        //size.setSizeId(33);
        //sizeImpl.update(size);
        //bookingImpl.update(booking);
        //product.setProductId(34);
        //productImpl.update(product);

        //DELETE
        //sizeImpl.delete(size);
        //bookingImpl.delete(booking);
        //productImpl.delete(product);

        //GET ALL
        /*List<Color> colorList = colorImpl.getAll();
           for (Color c : colorList){
               System.out.println(c);
           }*/
        /*List<Size> sizeList = sizeImpl.getAll();
        for (Size s : sizeList){
            System.out.println(s);
        }*/
        /*List<Booking> bookingList = bookingImpl.getAll();
        for (Booking o : bookingList){
            System.out.println(o);
        }*/
        /*List<Product> productList = productImpl.getAll();
        for (Product p : productList){
            System.out.println(p);
        }*/
        /*List<ProductOrder> productOrderList = productOrderImpl.getAll();
        for (ProductOrder p : productOrderList){
            System.out.println(p);
        }*/
    }


}

