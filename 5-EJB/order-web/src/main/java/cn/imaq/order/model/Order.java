package cn.imaq.order.model;

import java.io.Serializable;

public class Order implements Serializable {
    private Integer id;

    private Integer userId;

    private String time;

    private String name;

    private Integer amount;

    private Double price;

    private Boolean outOfStock;

    public Order(Integer id, Integer userId, String time, String name, Integer amount, Double price, Boolean outOfStock) {
        this.id = id;
        this.userId = userId;
        this.time = time;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.outOfStock = outOfStock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean isOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(Boolean outOfStock) {
        this.outOfStock = outOfStock;
    }
}
