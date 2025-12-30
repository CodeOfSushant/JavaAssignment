package sushant.assignment.java;
public class Product {
    protected String productName;
    protected double productPrice, tax, finalPrice, grossPrice;
    protected int prodQuantity;
    public enum Type {
        RAW, MANUFACTURED, IMPORTED;
        public enum SubType {
            RAW, MANUFACTURED, NA
        }
    }
    private Type type;
    private Type.SubType subType;
    // Constructor to initiate values to object.
    public Product(String productName, Type type, Type.SubType subType, double productPrice, double tax, double finalPrice, int prodQuantity, double grossPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.prodQuantity = prodQuantity;
        this.tax = tax;
        this.finalPrice = finalPrice;
        this.grossPrice = grossPrice;
        this.type = type;
        setSubType(subType);

    }
    public void setSubType(Type.SubType subType) {
            this.subType = subType;
    }
    public Type.SubType getSubType(){
        return subType;
    }
    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getProdQuantity() {
        return prodQuantity;
    }

    public Type getType(){
       return type;
    }
    public double getTax(){
        return tax;
    }
    public double getFinalPrice(){
        return finalPrice;
    }
    public double getGrossPrice(){
        return grossPrice;
    }
}


