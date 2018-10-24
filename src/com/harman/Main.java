package com.harman;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        prepareGame();

        String[][] oceanMap = createEmptyMap();
        showCurrentMap(oceanMap);

        String[][] playerMap = deployPlayerShips();
        showCurrentMap(oceanMap);
        oceanMap = updateOceanMap (oceanMap, playerMap);
        showCurrentMap(oceanMap);

        String[][] computerMap = deployComputerShips(playerMap);
        showCurrentMap(computerMap);

        playerMap = playersTurn(computerMap, playerMap);
        oceanMap = updateOceanMap (oceanMap, playerMap);
        showCurrentMap(oceanMap);












//
//        for (int i=1; i<11; i++) {
//            for (int j=2; j<12; j++) {
//                oceanMap[i][j] = computerMap[i-1][j-2];
//            }
//        }
//        showCurrentMap(oceanMap);


    }



    public static void prepareGame() {
        System.out.println("**** Welcome to Battle Ships game ******");
        System.out.println();
        System.out.println("Right now, the sea is empty.");
        System.out.println();

    }


    public static String[][] createEmptyMap() {
        String[][] oceanMap = new String[12][14];
        for (int i = 0; i < 12; i=i+11) {
            int numberInt = 0;
            for (int j = 2; j < 12; j++) {
                String numberString = Integer.toString(numberInt);
                oceanMap[i][j] = numberString;
                numberInt++;
            }
        }
        for (int j=0; j<14; j=j+13) {
            int numberInt = 0;
            for (int i=1; i<11; i++) {
                String numberString = Integer.toString(numberInt);
                oceanMap[i][j] = numberString;
                numberInt++;
            }
        }
        for (int j=1; j<13; j=j+11) {
            for (int i=1; i<11; i++) {
                oceanMap[i][j] = "|";
            }

        }
        return oceanMap;
    }

    public static void showCurrentMap(String [][] oceanMap) {
        for (int i=0; i<oceanMap.length; i++) {
            for (int j=0; j<oceanMap[0].length; j++) {
                if (oceanMap[i][j]==null) {
                    System.out.print(" ");
                }
                else
                    System.out.print(oceanMap[i][j]);
            }
            System.out.println();
        }
    }


    public static String[][] deployPlayerShips() {
        Scanner input = new Scanner(System.in);
        String[][] playerShipsMap = new String[10][10];
        int count = 1;
        while (count <6) {

            System.out.print("Enter X coordinate for your " + count + " ship (0-9): ");
            int y = input.nextInt();
            while (y<0 || y>9) {
                System.out.println("Coordinate is out of range 0-9. Enter X coordinate for your " + count + " ship again: ");
                y = input.nextInt();
            }
            System.out.print("Enter Y coordinate for your " + count + " ship (0-9): ");
            int x = input.nextInt();
            while (x<0 || x>9) {
                System.out.println("Coordinate is out of range 0-9. Enter Y coordinate for your " + count + " ship again: ");
                x = input.nextInt();
            }
            if (playerShipsMap[x][y] == null) {
                playerShipsMap[x][y] = "@";
                count ++;
            }
            else System.out.println("This location that is already taken by another ship. Enter new coordinates.");
        }
        return playerShipsMap;
    }

    public static String[][] deployComputerShips(String [][] playerShipMap) {
        String[][] computerShipsMap = new String[10][10];

        Random rand = new Random();
        int count = 1;
        System.out.println("Computer is deploying ships: ");
        while (count < 6) {
            int x = rand.nextInt(9);
            int y = rand.nextInt(9);

            if (playerShipMap[x][y] == null) {
                computerShipsMap[x][y] = "O";
                System.out.println(count + ". ship DEPLOYED");
                count++;
            }
        }
        return computerShipsMap;
    }


    public static String[][] updateOceanMap (String[][] oceanMap, String[][] playerMap) {
        for (int i=1; i<11; i++) {
            for (int j=2; j<12; j++) {
                oceanMap[i][j] = playerMap[i-1][j-2];
            }
        }
        return oceanMap;
    }

    public static String[][] playersTurn (String [][] computerMap, String [][] playerMap) {
        System.out.println("YOUR TURN");

        Scanner input = new Scanner(System.in);
        System.out.println("Enter X coordinate for your shot: ");
        int y = input.nextInt();
        while (y<0 || y>9) {
            System.out.println("Coordinate is out of range 0-9. Enter X coordinate again: ");
            y = input.nextInt();
        }

        System.out.println("Enter Y coordinate for your shot: ");
        int x = input.nextInt();
        while (x<0 || x>9) {
            System.out.println("Coordinate is out of range 0-9. Enter X coordinate again: ");
            x = input.nextInt();
        }

        if (playerMap[x][y]=="@") {
            System.out.println("Oh no, you sunk your own ship :(");
            playerMap[x][y] = "!";
        }

        else if (computerMap[x][y]=="O") {
            System.out.println("Boom! You sunk the ship!");
            playerMap[x][y] = "!";
        }

        else if (computerMap[x][y]==null) {
            System.out.println("Sorry, you missed");
            playerMap[x][y] = "-";
        }

        return playerMap;
    }
}
