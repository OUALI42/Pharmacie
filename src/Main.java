import com.google.gson.Gson;

import java.io.*;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
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

        Gson Admingson = new Gson();
        try (Reader readerAdmin = new FileReader("admins.json")) {
            admin = Admingson.fromJson(readerAdmin, Admins.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Gson Pharmajson = new Gson();
        try (Reader readerPharma = new FileReader("admins.json")) {
            pharmacist = gson.fromJson(readerPharma, Pharmacist.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Gson Clientjson = new Gson();
        try (Reader readerClient = new FileReader("admins.json")) {
            client = gson.fromJson(readerClient, Clients.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Gson PharmacistesGson = new Gson();

//            List<String> PharmaNames = new ArrayList<String>();
//            List<String> PharmaPwd = new ArrayList<String>();
//            PharmaNames.add("Strauss");
//            PharmaPwd.add("azerty123");
//            PharmaNames.add("Liam");
//            PharmaPwd.add("ULTRAKILL");

//            Pharmacist Pharmacists = new Pharmacist(PharmaNames, PharmaPwd);
//            Pharmacists.Login();
//            PharmacistesGson.toJson(reader, Pharmacist.class);



            CurrentUser Visitor = new CurrentUser("Visitor", new Clients(new ArrayList<>(),new ArrayList<>()));

            MenuPrincipal(order,Pharma,pharmacist,client,admin,Visitor);

            // Converts Java object to File


    }
    public static void MenuPrincipal(Order order, Pharmacies Pharma, Pharmacist Pharmacists, Clients clients, Admins admins, CurrentUser currentUser) {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GREEN = "\u001B[32m";

        Scanner scanner = new Scanner(System.in);
        System.out.println("        \uD83D\uDC0D⚕️"+ ANSI_GREEN+ " WELCOME TO THE PHARMACY "+ ANSI_RESET+"⚕️\uD83D\uDC0D");
        System.out.println("        You are " + currentUser.getName() + " (" + currentUser.user.getClass().getName() + ")" );
        System.out.println();
        System.out.println("        1 . Order a product");
        System.out.println("        2 . Connexion");
        if (currentUser.user.getClass() == Admins.class){
            System.out.println("        3 . Delete a user");
        }
        String input = scanner.nextLine();
        if(input.equals("1")){ order.Order();
            MenuPrincipal(order,Pharma,Pharmacists,clients,admins,currentUser);}
        else if(input.equals("2")){
            MenuConnexion(order,Pharma,Pharmacists,clients,admins,currentUser);
        }
        else if (currentUser.user.getClass() == Admins.class && input.equals("3")){
            currentUser.user.DeleteUser();
        }
    }
    public static void MenuConnexion(Order order, Pharmacies Pharma, Pharmacist Pharmacists, Clients clients, Admins admins, CurrentUser currentUser){
        Scanner namesc = new Scanner(System.in);
        Scanner passwordsc = new Scanner(System.in);

        System.out.println("Username :");
        String name = namesc.nextLine();
        System.out.println("Password :");
        String password = passwordsc.nextLine();

        System.out.println(name);
        System.out.println(password);

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
}
