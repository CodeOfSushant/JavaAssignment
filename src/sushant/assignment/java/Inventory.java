package sushant.assignment.java;

public class Inventory {
    protected String productName;

    protected double productPrice;
    protected double salesTax;
    protected double finalProductPrice;
    protected double netTotalPrice;

    // Constructor to initiate values to object.
    public Inventory(String productName, double productPrice, double salesTax, double finalProductPrice, double netTotalPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.salesTax = salesTax;
        this.finalProductPrice = finalProductPrice;
        this.netTotalPrice = netTotalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public double getFinalProductPrice() {
        return finalProductPrice;
    }

    public double getNetTotalPrice() {
        return netTotalPrice;
    }

    @Override
    public String toString() {
        return "Inventory{\nproductName=" + productName + ",\n price=" + productPrice + ",\nsalesTax=" + salesTax + ",\nproductFinalPrice=" + finalProductPrice + ",\nnetTotalPrice=" + netTotalPrice;
    }
}


