package ru.ifmo.uml.ui;

import javafx.scene.image.Image;
import ru.ifmo.uml.dal.implementations.ProductImpl;
import ru.ifmo.uml.dal.interfaces.ProductDao;
import ru.ifmo.uml.entity.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    private ProductDao productDAO;
    private Map<Integer,Product> products;

    public ProductRepository() {
        this.productDAO = new ProductImpl();
        this.products = new HashMap<>();
    }
    public void load(){
        products.clear();
        List<ru.ifmo.uml.dal.dto.Product> productDTOs = productDAO.getAll();
        for (ru.ifmo.uml.dal.dto.Product product : productDTOs){
            int article = product.getArticle();
            if (products.containsKey(article)){
                Product oldProduct = products.get(article);
                oldProduct.getColorIds().add(product.getSizeId());
                oldProduct.getSizeIds().add(product.getSizeId());
                oldProduct.getProductId().add(product.getProductId());
                oldProduct.getCount().add(product.getCount());
                if (product.getImageUrl() != null)
                    oldProduct.getImages().add(new Image("/image/" +product.getImageUrl()));
                else oldProduct.getImages().add(null);
            }
            else {
                Product newProduct = new Product();
                newProduct.setArticle(product.getArticle());
                newProduct.setName(product.getName());
                newProduct.setPrice(product.getPrice());
                newProduct.setSpecification(product.getSpecification());
                newProduct.setType(product.getType());
                newProduct.setColorIds(new ArrayList<>());
                newProduct.getColorIds().add(product.getSizeId());
                newProduct.setSizeIds(new ArrayList<>());
                newProduct.getSizeIds().add(product.getSizeId());
                newProduct.setProductId(new ArrayList<>());
                newProduct.getProductId().add(product.getProductId());
                newProduct.setCount(new ArrayList<>());
                newProduct.getCount().add(product.getCount());
                newProduct.setImages(new ArrayList<>());
                if (product.getImageUrl() != null)
                    newProduct.getImages().add(new Image("/image/" +product.getImageUrl()));
                else newProduct.getImages().add(null);
                products.put(product.getArticle(),newProduct);
            }
        }

    }
    public List<Product> getProducts(){
        if (products == null) load();
        return new ArrayList<>(products.values());
    }

}
