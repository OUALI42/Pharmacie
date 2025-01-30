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

        Scanner scanner = new Scanner(System.in);
        System.out.println("        \uD83D\uDC0D⚕️"+ ANSI_YELLOW+ " WELCOME TO THE PHARMACY "+ ANSI_RESET+"⚕️\uD83D\uDC0D");
        System.out.println("        You are " + currentUser.getName() + " (" + currentUser.user.getClass().getName() + ")" );
        System.out.println();
        System.out.println("        1 . Order a product");
        if (Objects.equals(currentUser.getName(), "Visitor")) {
            System.out.println("        2 . Connexion");
        }
        if (currentUser.user.getClass() == Admins.class){
            System.out.println("        3 . Delete a user");
        }
        if (currentUser.user.getClass() == Admins.class){
            System.out.println("        4 . Add a user");
        }
        String input = scanner.nextLine();
        if(input.equals("1")){ order.Order();
            MenuPrincipal(order,Pharma,Pharmacists,clients,admins,currentUser);}
        else if(input.equals("2")){
            MenuConnexion(order,Pharma,Pharmacists,clients,admins,currentUser);
        }
        else if (currentUser.user.getClass() == Admins.class && input.equals("3")){
            DeleteMenu(order,Pharma,Pharmacists,clients,admins,currentUser);
        }
        else if (currentUser.user.getClass() == Admins.class && input.equals("4")){
            AddMenu(order,Pharma,Pharmacists,clients,admins,currentUser);
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


    public static void DeleteMenu(Order order, Pharmacies Pharma, Pharmacist Pharmacists, Clients clients, Admins admins, CurrentUser currentUser){
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
    public static void AddMenu(Order order, Pharmacies Pharma, Pharmacist Pharmacists, Clients clients, Admins admins, CurrentUser currentUser){
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
}
