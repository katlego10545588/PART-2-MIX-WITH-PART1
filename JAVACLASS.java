/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.partonepoe;


public class JAVACLASS {
    
    //details
    public String firstNameStored;
    public String lastNameStored;
    private String registeredUsername;
    private String registeredPassword;

    // check if username has underscore and is short
    public boolean checkUserName(String username) {
        if (username.contains("_") && username.length() <= 5) {
            return true;
        } else {
            return false;
        }
    }

    // check password - needs 8 chars, capital, number and special char
    public boolean checkPasswordComplexity(String password) {
        // must be 8 or more
        if (password.length() < 8) {
            return false;
        }
        
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;
        
        // loop through password
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            
            // capital letter check
            if (c >= 'A' && c <= 'Z') {
                hasCapital = true;
            }
            // number check
            if (c >= '0' && c <= '9') {
                hasNumber = true;
            }
            // special char check
            if (c == '!' || c == '@' || c == '#' || c == '$' || c == '%' || c == '&' || c == '*') {
                hasSpecialChar = true;
            }
        }
        
        if (hasCapital == true && hasNumber == true && hasSpecialChar == true) {
            return true;
        } else {
            return false;
        }
    }

    // check cell number must start with +27 and be 12 long
    public boolean checkCellPhoneNumber(String cellNumber) {
        if (cellNumber.startsWith("+27") && cellNumber.length() == 12) {
            return true;
        } else {
            return false;
        }
    }

    // register method that Main will call
    public String registerUser(String username, String password, String cellNumber) {
        
        if (checkUserName(username) == false) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters long.";
        }
        
        if (checkPasswordComplexity(password) == false) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }
        
        if (checkCellPhoneNumber(cellNumber) == false) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        registeredUsername = username;
        registeredPassword = password;
        
        return "The two above conditions have been met and the user has been registered successfully.";
    }

    // login check
    public boolean loginUser(String username, String password) {
        if (username.equals(registeredUsername) && password.equals(registeredPassword)) {
            return true;
        } else {
            return false;
        }
    }

    // welcome message
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password) == true) {
            return "Welcome " + firstNameStored + " " + lastNameStored + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}

    
