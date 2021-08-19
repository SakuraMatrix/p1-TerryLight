package com.github.TerryLight.LimonStand.domain;


import java.util.Objects;

public class Order {
    private int id;
    private String drinkName;
    private String customerName;
    private String cupSize;
    private double price;

    public Order() {
    }

    public Order(int id, String drinkName, String customerName, String cupSize, double price) {
        this.id = id;
        this.drinkName = drinkName;
        this.customerName = customerName;
        this.cupSize = cupSize;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer = " + customerName + '\'' +
                ", drink = " + drinkName +
                ", cup size = " + cupSize +
                ", price = $" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Double.compare(order.price, price) == 0 && Objects.equals(drinkName, order.drinkName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, drinkName, cupSize, price);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDrinkName() {return drinkName;}
    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCupSize() { return cupSize;}
    public void setCupSize(String cupSize) {this.cupSize = cupSize;}

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}