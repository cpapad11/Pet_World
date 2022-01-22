import bean.Customer;
import bean.OrderPlaced;
import bean.Pet;
import bean.Product;
import service.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PetWorld {

    public static void main(String[] args) {
        Service service = new Service();
        boolean exit = false;
        Scanner kbd = new Scanner(System.in);
        System.out.println("\n                       Pet World\n");
        System.out.println("Welcome to the main portal!\n");
        System.out.println("Inserting data from CSV to database.");

        //Import data from csv
//        boolean insC = service.insertCSVtoCustomer();
//        if (insC) {
//            System.out.println("Data successfully inserted into table Customer.");
//        }
//
//        boolean insP = service.insertCSVtoPet();
//        if (insP) {
//            System.out.println("Data successfully inserted into table Pet.");
//        }
//
//        boolean insOrder = service.insertCSVtoOrder_placed();
//        if (insOrder) {
//            System.out.println("Data successfully inserted into table Order.");
//        }
//
//        boolean insProduct = service.insertCSVtoProduct();
//        if (insProduct) {
//            System.out.println("Data successfully inserted into table Product.");
//        }
//
//        boolean insSup = service.insertCsvSupplier();
//        if (insSup) {
//            System.out.println("Data successfully inserted into table Supplier.");
//        }
//
//        boolean insWishlist = service.insertCSVtoWishlist();
//        if (insWishlist) {
//            System.out.println("Data successfully inserted into table Wishlist.");
//        }

        while (!exit) {
            System.out.println("Please select one of the following queries.\n");
            System.out.println("1. This query returns the name and age of a pet when the age of the pet is less " +
                    "than 13 years old. Then the\n output is grouped by the pet’s age with similar age value " +
                    "records and order in ascending order by name. \n");
            System.out.println("2. This query returns the price of an order when the order\n price is less than " +
                    "$50. Then the output is grouped by price and displays how many similar records " +
                    "were found.\n");
            System.out.println("4. This query returns products who have been supplied from a specific supplier.\n");
            System.out.println("5. Customers with more than one pet.\n");
            System.out.println("6. Display the name of the costumers whose pet is age = 1\n");
            System.out.println("7. Display customers with no pets.\n");
            System.out.println("8. Display the most expensive order.\n");
            System.out.println("9. to exit\n");
            System.out.print("Choice: ");
            int ans = kbd.nextInt();

            if (ans == 1) {
                int output = choice();
                if (output == 1) {
                    ArrayList<Pet> pets = service.getNAPetless13();
                    String txt = "";
                    for (Pet i : pets) {
                        txt += i.getAge() + " " + i.getName1() + "\n";
                    }
                    writeToTXT(txt, 1);
                } else {
                    ArrayList<Pet> pets = service.getNAPetless13();
                    for (Pet i : pets) {
                        System.out.println(i.getAge() + " " + i.getName1());
                    }
                    System.out.println();
                }
            } else if (ans == 2) {
                int output = choice();
                if (output == 1) {
                    ArrayList<ArrayList<Integer>> test = service.numOfOrders_under50();
                    String txt = "";
                    for (int i =0; i<test.size(); i++) {
                        for(int j=0; j<test.get(i).size(); j++) {
                            if (j % 2 == 0) {
                                txt += "Number of orders with this price: ";
                            } else {
                                txt += "Price: ";
                            }
                            txt += test.get(i).get(j) + " ";
                        }
                        txt += "\n";
                    }
                    writeToTXT(txt, 2);
                } else {
                    ArrayList<ArrayList<Integer>> test = service.numOfOrders_under50();
                    for (int i =0; i<test.size(); i++) {
                        for(int j=0; j<test.get(i).size(); j++) {
                            if (j % 2 == 0) {
                                System.out.print("Number of orders with this price: ");
                            } else {
                                System.out.print("Price: ");
                            }
                            System.out.print(test.get(i).get(j) + " ");
                        }
                        System.out.println();
                    }
                    System.out.println();
                }
            } else if (ans == 3) {
                int output = choice();
                if (output == 1) {
                    Customer a = service.custWithMostOrders();
                    String txt = a.getName();
                    writeToTXT(txt,3);
                } else {
                    Customer a = service.custWithMostOrders();
                    System.out.println(a.getName());
                    System.out.println();
                }
            } else if (ans == 4) {
                int output = choice();
                if (output == 1) {
                    ArrayList<Product> product = service.getProdFromSup();
                    String txt = "";
                    for (Product i : product) {
                        txt += i.toString() + "\n";
                    }
                    writeToTXT(txt, 4);
                } else {
                    ArrayList<Product> product = service.getProdFromSup();
                    for (Product i : product) {
                        System.out.println(i.toString());
                    }
                    System.out.println();
                }
            } else if (ans == 5) {
                int output = choice();
                ArrayList<Customer> out = service.moreThanOnePet();
                if (output == 1) {
                    String txt = "";
                    for (Customer i : out) {
                        txt += i.getName() + "\n";
                    }
                    writeToTXT(txt,5);
                } else {
                    for (Customer i : out) {
                        System.out.println(i.getName());
                    }
                    System.out.println();
                }
            } else if (ans == 6) {
                int output = choice();
                if (output == 1) {
                    ArrayList<Customer> customer = service.CustomersNameWhenAge1();
                    String txt = "";
                    for (Customer i : customer) {
                        txt += i.getName() + "\n";
                    }
                    writeToTXT(txt, 6);
                } else {
                    ArrayList<Customer> customer = service.CustomersNameWhenAge1();
                    for (Customer i : customer) {
                        System.out.println(i.getName());
                    }
                    System.out.println();
                }
            } else if (ans == 7) {
                int output = choice();
                if (output == 1) {
                    ArrayList<Customer> customer = service.CustomersWithNoPets();
                    String txt = "";
                    for (Customer i : customer) {
                        txt += i.getcID() + "\n";
                    }
                    writeToTXT(txt, 7);
                } else {
                    ArrayList<Customer> customer = service.CustomersWithNoPets();
                    for(Customer i : customer) {
                        System.out.println(i.getcID());
                    }
                    System.out.println();
                }
            } else if (ans == 8) {
                int output = choice();
                if (output == 1) {
                    OrderPlaced ab = service.maxPriceOrder();
                    writeToTXT(ab.toString(), 8);
                    System.out.println();
                } else {
                    OrderPlaced ab = service.maxPriceOrder();
                    System.out.println(ab.toString() + "\n");
                }
            } else if (ans == 9) {
                exit = true;
            }

            if (ans != 9) {
                System.out.print("Would you like to to select another query? \nType 'y' for yes 'n' for no: ");
                String ans3 = kbd.next();
                if (ans3.equals("n")) { exit = true; }
            }
        }
    }

    public static int choice() {
        Scanner kbd = new Scanner(System.in);
        System.out.println("\nHow do you want your output to be displayed?");
        System.out.println("1. In a text file");
        System.out.println("2. In a command prompt");
        System.out.print("Choice: ");
        int ans2 = kbd.nextInt();
        System.out.println();

        return ans2;
    }

    public static void writeToTXT (String out, int num) {
        String txtName = "reportQuery" + num + ".txt";
        try {
            File myStorageFile = new File(txtName);
            FileWriter writerObject = new FileWriter(myStorageFile);
            writerObject.write("The output of your query " + num + " is the following:\n\n");
            writerObject.write(out);

            writerObject.flush();
            writerObject.close();
            System.out.println("Text file was created successfully!\n");
        } catch(IOException Ex) {
            System.out.println("File is missing");
        }
    }
}