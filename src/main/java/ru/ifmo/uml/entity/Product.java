package ru.ifmo.uml.entity;


import javafx.scene.image.Image;

import java.util.List;

public class Product {

    private List<Integer> productId;
    private String name;
    private String type;
    private List<Integer> sizeIds;
    private List<Integer> colorIds;
    private double price;
    private List<Integer> count;
    private String specification;
    private int article;
    private List<Image> images;

    public Product() {
    }

    public List<Integer> getProductId() {
        return productId;
    }

    public void setProductId(List<Integer> productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getSizeIds() {
        return sizeIds;
    }

    public void setSizeIds(List<Integer> sizeIds) {
        this.sizeIds = sizeIds;
    }

    public List<Integer> getColorIds() {
        return colorIds;
    }

    public void setColorIds(List<Integer> colorIds) {
        this.colorIds = colorIds;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Integer> getCount() {
        return count;
    }

    public void setCount(List<Integer> count) {
        this.count = count;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
