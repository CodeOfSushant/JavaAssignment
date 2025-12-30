package sushant.assignment.java;

public class TaxUtility {
    static final double importDuty = 10;
    static double surCharge;
    static double tax;

    //Tax calculation
    static double calSalesTax(double productPrice, Product.Type type, Product.Type.SubType subType) {
        if (type == Product.Type.RAW) {
            tax = (productPrice * 12.5 / 100);
        }
        if (type == Product.Type.MANUFACTURED) {
            tax = (productPrice * 12.5 / 100) + ((productPrice + (productPrice * 12.5 / 100)) * 2 / 100);
        }
        if (subType == Product.Type.SubType.RAW || subType == Product.Type.SubType.MANUFACTURED) {
            if (subType == Product.Type.SubType.RAW) {
                tax = (productPrice * 12.5 / 100);
            } else {
                tax = (productPrice * 12.5 / 100) + ((productPrice + (productPrice * 12.5 / 100)) * 2 / 100);
            }
            tax = tax + (productPrice * importDuty / 100);
            if (tax <= 100)
                surCharge = 5;
            else if (tax > 100 && tax <= 200) {
                surCharge = 10;
            } else {
                surCharge = tax * 5 / 100;
            }
            tax = tax + importDuty + surCharge;
        }
        return tax;
    }
}
