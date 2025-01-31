import com.google.gson.Gson;

import java.io.*;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Gson gson = new Gson();
        Pharmacies Pharma;
        Order order;
        Clients client;
        Admins admin;
        Pharmacist pharmacist;
        try (Reader reader = new FileReader("stocks_pharma.json")) {
            Pharma = gson.fromJson(reader, Pharmacies.class);
            order = new Order(Pharma.pharmacie);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Reader readerAdmin = new FileReader("admins.json")) {
            admin = gson.fromJson(readerAdmin, Admins.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Reader readerPharma = new FileReader("pharmacists.json")) {
            pharmacist = gson.fromJson(readerPharma, Pharmacist.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Reader readerClient = new FileReader("clients.json")) {
            client = gson.fromJson(readerClient, Clients.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CurrentUser Visitor = new CurrentUser("Visitor", new Clients(new ArrayList<>(),new ArrayList<>()));

        MenuPrincipal(order,Pharma,pharmacist,client,admin,Visitor);
    }
    public static void MenuPrincipal(Order order, Pharmacies Pharma, Pharmacist Pharmacists, Clients clients, Admins admins, CurrentUser currentUser) {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_YELLOW = "\u001B[33m";
        while (true) {
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
            if (currentUser.user.getClass() == Admins.class || currentUser.user.getClass() == Pharmacist.class) {
                System.out.println("        4\uFE0F⃣ . Look at the stock");
            }
            System.out.println();
            System.out.println("        0\uFE0F⃣ . Quit");
            String input = scanner.nextLine();
            if (input.equals("1") && currentUser.user.getClass() == Clients.class) {
                System.out.println("yamero");
                order.Order();
                MenuPrincipal(order, Pharma, Pharmacists, clients, admins, currentUser);
            } else if (input.equals("2") && currentUser.user.getClass() == Clients.class) {
                MenuConnexion(order, Pharma, Pharmacists, clients, admins, currentUser);
            } else if (input.equals("1") && (currentUser.user.getClass() == Admins.class || currentUser.user.getClass() == Pharmacist.class)) {
                MenuAddProduct(order, Pharma, Pharmacists, clients, admins, currentUser);
                MenuPrincipal(order, Pharma, Pharmacists, clients, admins, currentUser);
            } else if (currentUser.user.getClass() == Admins.class && input.equals("2")) {
                DeleteUserMenu(order, Pharma, Pharmacists, clients, admins, currentUser);
                MenuPrincipal(order, Pharma, Pharmacists, clients, admins, currentUser);
            } else if (currentUser.user.getClass() == Admins.class && input.equals("3")) {
                AddUserMenu(order, Pharma, Pharmacists, clients, admins, currentUser);
                MenuPrincipal(order, Pharma, Pharmacists, clients, admins, currentUser);
            } else if (input.equals("4") && (currentUser.user.getClass() == Admins.class || currentUser.user.getClass() == Pharmacist.class)) {
                Pharma.pharmacie.ShowProducts();
                MenuPrincipal(order, Pharma, Pharmacists, clients, admins, currentUser);
            } else if (input.equals("0")) {
                return;
            } else {
                System.out.println("Incorrect input. Try again.");
            }
        }
    }

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


    public static void MenuAddProduct(Order order, Pharmacies Pharma, Pharmacist Pharmacists, Clients clients, Admins admins, CurrentUser currentUser){
        Pharma.pharmacie.addProduct();

        Gson Gson = new Gson();
        try (Writer writer = new FileWriter("stocks_pharma.json")) {
            Gson.toJson(Pharma, writer);
        } catch (IOException e) {throw new RuntimeException(e);}

        MenuPrincipal(order,Pharma,Pharmacists,clients,admins,currentUser);
    }

}
