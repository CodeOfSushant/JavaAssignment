package sushant.assignment.java;

//import sushant.assignment.java.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryProcess {

    private static double salesTax, finalProductPrice, netTotalPrice;

    // function to calculate sales tax for Raw and Manufactured.
    static double calSalesTax(int choice, double productPrice) {
        double tax = 0.00;
        if (choice == 1)
            tax = (productPrice * 12.5 / 100);

        if (choice == 2)
            tax = (productPrice * 12.5 / 100) + ((productPrice + (productPrice * 12.5 / 100)) * 2 / 100);

        return tax;
    }

    // function to calculate sales tax for Imported items
    static double calSalesTax(double salesTax, double productPrice, int importDuty) {
        double surCharge;
        double finalCost;
        finalCost = (salesTax + productPrice * importDuty / 100);
        if (finalCost <= 100)
            surCharge = 5;
        else if (finalCost > 100 && finalCost <= 200) {
            surCharge = 10;
        } else {
            surCharge = finalCost * 5 / 100;
        }
        return finalCost + surCharge;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // create Arraylist for Inventory class to store product details.
        List<Inventory> inventoryList = new ArrayList<>();
        boolean input = true;

        System.out.println("!!!!!!!!!!! Welcome To Inventory Process!!!!!!!!!!!!");

        while (input) {
            System.out.println("\n Enter Product Details");

            // Ask for product name
            System.out.println(" Enter the Product Name");
            String productName = scanner.nextLine();

            // Ask for product Type..Raw,Manufactured,Imported
            boolean valid = false;
            int itemType = 0;

            // validation for choosing correct options
            while (!valid) {
                System.out.println(" Enter the Type Of Product");
                System.out.println("\n 1: Raw\n 2: Manufactured\n 3: Imported");
                try {
                    itemType = Integer.parseInt(scanner.nextLine());
                    if (itemType >= 1 && itemType <= 3) {
                        valid = true;
                    } else {
                        System.out.println(" Wrong Choice ! choose from the above option");
                    }

                } catch (NumberFormatException e) {
                    System.out.println(" Wrong Choice ! choose from the above option");
                }
            }

            // Ask for product quantity
            // check validation
            valid = false;
            int productQuantity = 0;

            while (!valid) {
                System.out.println(" Enter Quantity of product");
                try {
                    productQuantity = Integer.parseInt(scanner.nextLine());
                    if (productQuantity > 0)
                        valid = true;
                    else
                        System.out.println(" Enter valid quantity");
                } catch (NumberFormatException e) {
                    System.out.println(" Invalid Entry ! Enter valid Quantity");
                }
            }

            // Ask for product price
            // Check for validation
            valid = false;
            double productPrice = 0.00;

            while (!valid) {
                System.out.println(" Enter The Price of the Product");
                try {
                    productPrice = Double.parseDouble(scanner.nextLine());
                    if (productPrice > 0)
                        valid = true;
                    else
                        System.out.println(" Enter valid Price");
                } catch (NumberFormatException e) {
                    System.out.println(" Enter valid price");
                }
            }
            // sales tax per item for Raw and Manufactured items.
            if (itemType != 3) {
                salesTax = calSalesTax(itemType, productPrice);
            }

            // sales tax per item for Imported items.
            if (itemType == 3) {
                valid = false;
                int impType = 0;
                // Check for validation
                while (!valid) {
                    System.out.println(" Enter The Type of Imported Product");
                    System.out.println("\n 1: Raw\n 2: Manufactured");
                    try {
                        impType = Integer.parseInt(scanner.nextLine());
                        if (impType >= 1 && impType <= 2)
                            valid = true;
                        else
                            System.out.println(" Enter valid options from above");
                    } catch (NumberFormatException e) {
                        System.out.println(" Enter valid Options from Above");
                    }
                }
                salesTax = calSalesTax(impType, productPrice);
                double impSalesTax = calSalesTax(salesTax, productPrice, 10);
                salesTax = impSalesTax;
            }

            // Final price per item.
            finalProductPrice = productPrice + salesTax;
            netTotalPrice = (finalProductPrice * productQuantity);

            // create object for Inventory class
            Inventory newinventory = new Inventory(productName, productPrice, salesTax, finalProductPrice, netTotalPrice);

            // Add product details to an arraylist.
            inventoryList.add(newinventory);
            System.out.println(" Product added:" + newinventory.getProductName());

            System.out.println(" Do you want to add more product? (y/n)");
            String choice = scanner.next().trim().toLowerCase();
            scanner.nextLine();
            if (!choice.equals("y")) {
                input = false;
            }

        }


        // Displaying details of the added Product.
        System.out.println("Displaying Product details");
        System.out.println(inventoryList);

    }

}
