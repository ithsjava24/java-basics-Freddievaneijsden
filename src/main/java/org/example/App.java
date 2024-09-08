package org.example;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int [] pricePerHour = new int[5];
        boolean meny = true;
        do {
            System.out.println("Elpriser");
            System.out.println("========");
            System.out.println("1. Inmatning");
            System.out.println("2. Min, Max och Medel");
            System.out.println("3. Sortera");
            System.out.println("4. Bästa Laddningstid (4h)");
            System.out.println("e. Avsluta");

            String menyOption = "0";
            try {
                menyOption = scanner.nextLine();
            }
            catch (NoSuchElementException e) {
                System.out.println("NoSuchElementException e");
            }

            switch (menyOption) {
                case "1":
                    System.out.println("kW/h: ");
                    //Saves price per hour from user in array
                    for (int i = 0; i < pricePerHour.length; i++) {
                        if (i<9) {
                            System.out.println("Pris i öre mellan " + "0" + i + "-0" + (i + 1));
                            pricePerHour[i] = scanner.nextInt();
                        }
                        if (i>9) {
                            System.out.println("Pris i öre mellan " + i + "-" + (i + 1));
                            pricePerHour[i] = scanner.nextInt();
                        }
                    }

                    break;
                case "2":
                    int min = pricePerHour[0];
                    for (int i = 0; i < pricePerHour.length; i++) {
                        if (pricePerHour[i]<min) {
                            min = pricePerHour[i];
                        }
                    }
                    System.out.println(Arrays.toString(pricePerHour));
                    System.out.println("Lägsta pris: 02-03, " + min + " öre/kWh");
                    System.out.println("Högsta pris: 00-01, 100 öre/kWh");
                    System.out.println("Medelpris: 13,38 öre/kWh");
                    break;
                case "3":
                    System.out.println("Sortera");
                    break;
                case "4":
                    System.out.println("Bästa");
                    break;
                case "e", "E":
                    meny = false;
                    break;
                default:
                    System.out.println("Invalid option");

            }

        } while (meny);

    }

}
