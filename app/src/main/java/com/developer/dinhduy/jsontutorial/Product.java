package com.developer.dinhduy.jsontutorial;

public class Product {
    private String Name;
    private String Price;
    private String Path;

    public Product(String name, String price, String path) {
        Name = name;
        Price = price;
        Path = path;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }
}
