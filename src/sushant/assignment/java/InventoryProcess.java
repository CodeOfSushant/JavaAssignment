package sushant.assignment.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryProcess {
    static String name;
    static double price;
    static Product.Type type = null;
    static Product.Type.SubType subType = null;
    static int prodQuantity;
    static double tax, finalPrice, grossPrice;
    static List<Product> productList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // call to add product addition and validation
        addProduct();
        // Displaying all the products details to the screen in tabular form.
        System.out.println("\n----------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-12s %-15s %-15s %-10s %-10s %-15S %-15S %-10S%n", "NAME", "TYPE", "SUBTYPE", "PRICE", "SALESTAX", "FINALPRICE", "QUANTITY", "GrossPrice");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        for (Product i : productList) {
            System.out.printf("%-12s %-15s %-15s %-10s %-10s %-15S %-15S %-10S%n", i.getProductName(), i.getType(), i.getSubType(), i.getProductPrice(), i.getTax(), i.getFinalPrice(), i.getProdQuantity(), i.getGrossPrice());
        }
    }

    public static void addProduct() {
        System.out.println("!!!!!!!!!!! Welcome To Product Process!!!!!!!!!!!!");
        boolean isValid = true;
        while (isValid) {
            System.out.println("Enter (name, price, Type, Quantity):");
            String input = scanner.nextLine();

            // Split input and check for sufficient fields
            String[] details = input.split(",\\s*");
            if (details.length < 4) {
                System.out.println("Error: Please provide all 4 fields (name, price, type,  salesTax ).");
                return;
            }
            name = details[0].trim();
            // 1. Individually validate Price
            try {
                price = Double.parseDouble(details[1].trim());
                if (price < 0) {
                    System.out.println("Error: Price cannot be negative.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Price must be a valid number (e.g., 10.50).");
                continue;
            }
            // validate item type
            String typeInput = details[2].trim().toUpperCase().trim();
            try {
                if (!typeInput.equals("RAW") && !typeInput.equals("MANUFACTURED") && !typeInput.equals("IMPORTED")) {
                    System.out.println(" Enter valid Product Type: ");
                    continue;
                }
                type = Product.Type.valueOf(typeInput);
            } catch (Exception e) {
                System.out.println(e);
            }
            // validate item sub-type
            if (type == Product.Type.IMPORTED) {
                System.out.println(" This is Imported Item: Please Enter SubType: (Raw or Manufactured) ");
                while (subType == null) {
                    String subInput = scanner.nextLine().toUpperCase().trim();
                    if (!subInput.equalsIgnoreCase("raw") && !subInput.equalsIgnoreCase("manufactured")) {
                        System.out.println(" Enter valid sub type: ");
                        continue;
                    }
                    try {
                        subType = Product.Type.SubType.valueOf(subInput);
                    } catch (Exception e) {
                        System.out.println(" Invalid SubTyp: Please Enter Again ");
                    }
                }
            }
            // 2. Individually validate Quantity
            try {
                prodQuantity = Integer.parseInt(details[3].trim());
                if (prodQuantity <= 0) {
                    System.out.println("Enter a valid Quantity.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println(" Quantity must be a valid  number .");
                continue;
            }
            // 3. Output results only if all validations passed
            if (isValid) {
                System.out.println("\n--- Product Details ---");
                if (!(subType == null)) {
                    System.out.printf("Name: %s | Price: %.2f | Type: %s | SubType: %s | Quantity: %d%n",
                            name, price, type, subType, prodQuantity);
                } else {
                    subType = Product.Type.SubType.NA;
                    System.out.printf("Name: %s | Price: %.2f | Type: %s | SubType: %s | Quantity: %d%n",
                            name, price, type, subType, prodQuantity);
                }
            }
            // Sales tax
            tax = TaxUtility.calSalesTax(price, type, subType);
            // Price per item after sales tax
            finalPrice = price + tax;
            // final price * quantity
            grossPrice = finalPrice * prodQuantity;
            // initialize values to the newProduct
            Product newProduct = new Product(name, type, subType, price, tax, finalPrice, prodQuantity, grossPrice);
            // add newProduct to the list.
            productList.add(newProduct);
            subType = null;
            // ask for another add-up
            System.out.println("\n Do you want to add more product?y/n");
            String choice = scanner.next().trim().toLowerCase();
            scanner.nextLine();
            if (!choice.equals("y")) {
                isValid = false;
            }
        }
    }
}
