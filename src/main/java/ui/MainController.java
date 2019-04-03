package ui;

import dal.implementations.BookingImpl;
import dal.implementations.ProductImpl;
import dal.implementations.SizeImpl;
import entity.Booking;
import entity.Product;
import entity.Size;

import java.util.List;


public class MainController {
    public static void main(String[] args) {
        //тест
        //size+
        //booking+
        //product+
        //product booking

        SizeImpl sizeImpl = new SizeImpl();
        Size size = new Size();
        size.setSizeName("xxl");

        BookingImpl bookingImpl = new BookingImpl();
        Booking booking = new Booking();

        ProductImpl productImpl = new ProductImpl();
        Product product = new Product();
        product.setColorId(4);
        product.setCount(5);
        product.setSizeId(1);
        product.setName("CarSticker");
        product.setPrice(500);
        product.setSpecification("чтобы все знали, что вы их этих");
        product.setType("accessory");


        //get by ID
        //Size seze1 = sizeImpl.getById(2);
        //System.out.println(seze1.toString());
        //System.out.println("\n");
        //Booking booking1 = bookingImpl.getById(1);
        //System.out.println(booking1.toString());
        //Product product1 = productImpl.getById(2);
        //System.out.println(product1.toString());

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
        List<Product> productList = productImpl.getAll();
        for (Product p : productList){
            System.out.println(p);
        }

    }


}

