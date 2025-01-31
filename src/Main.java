import com.google.gson.Gson;

import java.io.*;
import java.lang.String;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        //Variables initialisation for json and classes
        Gson gson = new Gson();
        Pharmacies Pharma;
        Order order;
        Clients client;
        Admins admin;
        Pharmacist pharmacist;

        //Get data for Pharma from json file stocks_pharma.json
        try (Reader reader = new FileReader("stocks_pharma.json")) {
            Pharma = gson.fromJson(reader, Pharmacies.class);
            order = new Order(Pharma.pharmacie);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Get data for admin from json file admins.json
        try (Reader readerAdmin = new FileReader("admins.json")) {
            admin = gson.fromJson(readerAdmin, Admins.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Get data for pharmacist from json file pharmacists.json
        try (Reader readerPharma = new FileReader("pharmacists.json")) {
            pharmacist = gson.fromJson(readerPharma, Pharmacist.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Get data for client from json file clients.json
        try (Reader readerClient = new FileReader("clients.json")) {
            client = gson.fromJson(readerClient, Clients.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Creation of new Client Visitor for disconnected User
        CurrentUser Visitor = new CurrentUser("Visitor", new Clients(new ArrayList<>(),new ArrayList<>()));

        //Main menu initialization
        MenuPrincipal(order,Pharma,pharmacist,client,admin,Visitor);
    }

    /**
     * The main menu
     * <p>
     * This method shows a user interface with multiple options
     * By entering a number, you can access other menus
     * The options change based on your autorisations (Client, Pharmacist, Admin)
     * You can exit the menu or disconnect
     */
    public static void MenuPrincipal(Order order, Pharmacies Pharma, Pharmacist Pharmacists, Clients clients, Admins admins, CurrentUser currentUser) {
        //Variables for colored text
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_YELLOW = "\u001B[33m";
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println();
        System.out.println(ANSI_YELLOW + "           ██-----██-███████-██-------██████--██████--███----███-███████-----████████--██████-------██████--██----██-██████------██████--██---██--█████--██████--███----███--█████---██████-██----██\n" +
                "           ██-----██-██------██------██------██----██-████--████-██-------------██----██----██-----██----██-██----██-██---██-----██---██-██---██-██---██-██---██-████--████-██---██-██-------██--██\n" +
                "           ██--█--██-█████---██------██------██----██-██-████-██-█████----------██----██----██-----██----██-██----██-██████------██████--███████-███████-██████--██-████-██-███████-██--------████\n" +
                "           ██-███-██-██------██------██------██----██-██--██--██-██-------------██----██----██-----██----██-██----██-██---██-----██------██---██-██---██-██---██-██--██--██-██---██-██---------██\n" +
                "           -███-███--███████-███████--██████--██████--██------██-███████--------██-----██████-------██████---██████--██---██-----██------██---██-██---██-██---██-██------██-██---██--██████----██" + ANSI_RESET);
        System.out.println();
        System.out.println("        You are " + currentUser.getName() + " (" + currentUser.user.getClass().getName() + ")");
        System.out.println();

        //Print of all menu options based on hierarchy
        if (currentUser.user.getClass() == Clients.class) {
            System.out.println("        1\uFE0F⃣ . Order a product");
            System.out.println("        2\uFE0F⃣ . Connexion");
        }
        if (currentUser.user.getClass() == Admins.class || currentUser.user.getClass() == Pharmacist.class) {
            System.out.println("        1\uFE0F⃣ . Add / Remove a product");
        }
        if (currentUser.user.getClass() == Admins.class) {
            System.out.println("        2\uFE0F⃣ . Delete a user");
            System.out.println("        3\uFE0F⃣ . Add a user");
        }
        if (currentUser.user.getClass() == Pharmacist.class) {
            System.out.println("        2\uFE0F⃣ . Look at the stock");
            System.out.println("        3\uFE0F⃣ . Disconnection");
        }
        if (currentUser.user.getClass() == Admins.class) {
            System.out.println("        4\uFE0F⃣ . Look at the stock");
            System.out.println("        5\uFE0F⃣ . Disconnection");
        }
        System.out.println();
        System.out.println("        0\uFE0F⃣ . Quit");

        //Get user input
        String input = scanner.nextLine();
        //Order a product for clients only
        if (input.equals("1") && currentUser.user.getClass() == Clients.class) {
            order.Order();
            MenuPrincipal(order, Pharma, Pharmacists, clients, admins, currentUser);
        }

        //Connexion for Clients only
        else if (input.equals("2") && currentUser.user.getClass() == Clients.class) {
            MenuConnexion(order, Pharma, Pharmacists, clients, admins, currentUser);
        }

        //Add product for Admins and Pharmacists
        else if (input.equals("1") && (currentUser.user.getClass() == Admins.class || currentUser.user.getClass() == Pharmacist.class)) {
            MenuAddProduct(order, Pharma, Pharmacists, clients, admins, currentUser);
            MenuPrincipal(order, Pharma, Pharmacists, clients, admins, currentUser);
        }

        //Delete User for Admins only
        else if (currentUser.user.getClass() == Admins.class && input.equals("2")) {
            DeleteUserMenu(order, Pharma, Pharmacists, clients, admins, currentUser);
            MenuPrincipal(order, Pharma, Pharmacists, clients, admins, currentUser);
        }

        //Add User for Admins only
        else if (currentUser.user.getClass() == Admins.class && input.equals("3")) {
            AddUserMenu(order, Pharma, Pharmacists, clients, admins, currentUser);
            MenuPrincipal(order, Pharma, Pharmacists, clients, admins, currentUser);

        }

        //Show detailed Products for Admins and Pharmacists
        else if (input.equals("2") && currentUser.user.getClass() == Pharmacist.class) {
            Pharma.pharmacie.ShowProducts();
            MenuPrincipal(order, Pharma, Pharmacists, clients, admins, currentUser);
        } else if (input.equals("4") && (currentUser.user.getClass() == Admins.class)) {
            Pharma.pharmacie.ShowProducts();
            MenuPrincipal(order, Pharma, Pharmacists, clients, admins, currentUser);
        }

        //Disconnection for Admins and Pharmacists
        else if (input.equals("3") && currentUser.user.getClass() == Pharmacist.class) {
            MenuPrincipal(order, Pharma, Pharmacists, clients, admins, new CurrentUser("Visitor", new Clients(new ArrayList<>(),new ArrayList<>())));
        } else if (input.equals("5") && (currentUser.user.getClass() == Admins.class)) {
            MenuPrincipal(order, Pharma, Pharmacists, clients, admins, new CurrentUser("Visitor", new Clients(new ArrayList<>(),new ArrayList<>())));
        }

        //Quit the menu
        else if (input.equals("0")) {
            System.out.println("Goodbye !");
        }

        //If any other input, loop the menu
        else {
            System.out.println("Incorrect input. Try again.");
            MenuPrincipal(order, Pharma, Pharmacists, clients, admins, currentUser);
        }
    }

    /**
     * The connexion menu
     * <p>
     * This method asks the user for his username and password.
     * It then calls the Users.Login() method for Admins, Pharmacists and Clients
     * If the user did not enter valid information, he is sent back to the main menu with his previous information
     */
    public static void MenuConnexion(Order order, Pharmacies Pharma, Pharmacist Pharmacists, Clients clients, Admins admins, CurrentUser currentUser){
        Scanner namesc = new Scanner(System.in);
        Scanner passwordsc = new Scanner(System.in);

        System.out.println("Username :");
        String name = namesc.nextLine();
        System.out.println("Password :");
        String password = passwordsc.nextLine();

        boolean Connected = true;
        CurrentUser NewUser = admins.Login(name, password);
        if (NewUser == null){
            NewUser = Pharmacists.Login(name, password);
            if (NewUser == null){
                NewUser = clients.Login(name, password);
                if (NewUser == null){
                    System.out.println("Username or password wrong, please try again");
                    System.out.println(" ");
                    Connected = false;
                }
            }
        }
        if (Connected){
            MenuPrincipal(order,Pharma,Pharmacists,clients,admins,NewUser);
        }
        else{
            MenuPrincipal(order,Pharma,Pharmacists,clients,admins,currentUser);
        }
    }

    /**
     * The menu to delete a user
     * <p>
     * This method asks the Admin for the name of a user to remove.
     * It then calls the Users.DeleteUser() method for Admins, Pharmacists and Clients
     * Finally, it updates the json files for all Users
     */
    public static void DeleteUserMenu(Order order, Pharmacies Pharma, Pharmacist Pharmacists, Clients clients, Admins admins, CurrentUser currentUser){
        Scanner Deletesc = new Scanner(System.in);
        System.out.println("Username of user to delete :");
        String name = Deletesc.nextLine();
        clients.DeleteUser(name);
        Pharmacists.DeleteUser(name);
        admins.DeleteUser(name);


        Gson Gson = new Gson();
        try (Writer writer = new FileWriter("pharmacists.json")) {
            Gson.toJson(Pharmacists, writer);
        } catch (IOException e) {throw new RuntimeException(e);}

        try (Writer writer = new FileWriter("admins.json")) {
            Gson.toJson(admins, writer);
        } catch (IOException e) {throw new RuntimeException(e);}

        try (Writer writer = new FileWriter("clients.json")) {
            Gson.toJson(clients, writer);
        } catch (IOException e) {throw new RuntimeException(e);}

        MenuPrincipal(order,Pharma,Pharmacists,clients,admins,currentUser);
    }

    /**
     * The menu to add a user
     * <p>
     * This method asks the Admin for the name, password and authorizations of a user to add.
     * It then calls the Users.AddUser() method for the asked level of authorizations
     * Finally, it updates the json files for all Users
     */
    public static void AddUserMenu(Order order, Pharmacies Pharma, Pharmacist Pharmacists, Clients clients, Admins admins, CurrentUser currentUser){
        Scanner namesc = new Scanner(System.in);
        Scanner passwordsc = new Scanner(System.in);
        Scanner hierarchysc = new Scanner(System.in);

        System.out.println("New user Username :");
        String name = namesc.nextLine();
        System.out.println("New user Password :");
        String password = passwordsc.nextLine();
        System.out.println("User permissions ( 1 :Client / 2 :Pharmacist / 3 :Admins ) :");
        String hierarchy = hierarchysc.nextLine();

        switch (hierarchy) {
            case "1" -> clients.AddUser(name, password);
            case "2" -> Pharmacists.AddUser(name, password);
            case "3" -> admins.AddUser(name, password);
            default -> System.out.println("Saisie incorrecte");
        }

        Gson Gson = new Gson();
        try (Writer writer = new FileWriter("pharmacists.json")) {
            Gson.toJson(Pharmacists, writer);
        } catch (IOException e) {throw new RuntimeException(e);}

        try (Writer writer = new FileWriter("admins.json")) {
            Gson.toJson(admins, writer);
        } catch (IOException e) {throw new RuntimeException(e);}

        try (Writer writer = new FileWriter("clients.json")) {
            Gson.toJson(clients, writer);
        } catch (IOException e) {throw new RuntimeException(e);}

        MenuPrincipal(order,Pharma,Pharmacists,clients,admins,currentUser);
    }

    /**
     * The menu to Add or Delete a product
     * <p>
     * This method calls the Pharmacie.addProduct()
     * Then, it updates the json files for all Users
     */
    public static void MenuAddProduct(Order order, Pharmacies Pharma, Pharmacist Pharmacists, Clients clients, Admins admins, CurrentUser currentUser){
        Pharma.pharmacie.addProduct();

        Gson Gson = new Gson();
        try (Writer writer = new FileWriter("stocks_pharma.json")) {
            Gson.toJson(Pharma, writer);
        } catch (IOException e) {throw new RuntimeException(e);}

        MenuPrincipal(order,Pharma,Pharmacists,clients,admins,currentUser);
    }
}
