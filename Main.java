/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.partonepoe;

/**
 *
 * @author Student
 */
 import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JAVACLASS myLogin = new JAVACLASS();

        System.out.println("Please enter your first name:");
        myLogin.firstNameStored = scanner.nextLine();

        System.out.println("Please enter your last name:");
        myLogin.lastNameStored = scanner.nextLine();

        System.out.println("Enter username:");
        String user = scanner.nextLine();

        System.out.println("Enter password:");
        String pass = scanner.nextLine();

        System.out.println("Enter cell number with +27:");
        String cell = scanner.nextLine();

        // register
        String result = myLogin.registerUser(user, pass, cell);
        System.out.println(result);

        // if registered then login
        if (result.contains("successfully")) {
            System.out.println("Now login:");
            System.out.println("Username:");
            String loginUser = scanner.nextLine();
            System.out.println("Password:");
            String loginPass = scanner.nextLine();

            String loginResult = myLogin.returnLoginStatus(loginUser, loginPass);
            System.out.println(loginResult);
        }
        
        scanner.close();
    }
}  