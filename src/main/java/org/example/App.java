package org.example;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {

    public static Scanner scanner;

    public App() {
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.of("SV", "SE"));
        new App();

        int[] pricePerHour = new int[24];
        boolean meny = true;
        do {
            System.out.print("Elpriser\n");
            System.out.print("========\n");
            System.out.print("1. Inmatning\n");
            System.out.print("2. Min, Max och Medel\n");
            System.out.print("3. Sortera\n");
            System.out.print("4. Bästa Laddningstid (4h)\n");
            System.out.print("5. Visualisering\n");
            System.out.print("e. Avsluta\n");

            String menyOption = "0";
            try {
                menyOption = scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.print("NoSuchElementException e\n");
            }

            switch (menyOption) {
                case "1":
                    input(pricePerHour);
                    break;
                case "2":
                    minMaxMean(pricePerHour);
                    break;
                case "3":
                    sorted(pricePerHour);
                    break;
                case "4":
                    bestHours(pricePerHour);
                    break;
                case "5":
                    visualisering(pricePerHour);
                    break;
                case "e", "E":
                    meny = false;
                    break;
                default:
                    System.out.print("Invalid option\n");
            }
        } while (meny);
    }

    static void bestHours(int[] pricePerHour) {
        int[] position = new int[pricePerHour.length];
        for (int i = 0; i < pricePerHour.length; i++) {
            position[i] = i;

        }
        for (int i = 0; i < pricePerHour.length; i++) {
            for (int j = 1; j < pricePerHour.length - i; j++) {
                if (pricePerHour[j - 1] < pricePerHour[j]) {

                    int temp = pricePerHour[j];
                    pricePerHour[j] = pricePerHour[j - 1];
                    pricePerHour[j - 1] = temp;

                    int tempPosition = position[j];
                    position[j] = position[j - 1];
                    position[j - 1] = tempPosition;
                }
            }
        }

        float lowestPrice = Integer.MAX_VALUE;
        int lowestPosition = 0;
        for (int i = 0; i < pricePerHour.length - 3; i++) {
            int sum = pricePerHour[i] + pricePerHour[i + 1] + pricePerHour[i + 2] + pricePerHour[i + 3];
            if (lowestPrice > sum) {
                lowestPrice = sum;
                lowestPosition = i;
            }
        }
        float averagePrice = lowestPrice / 4;
        System.out.printf("""
                Påbörja laddning klockan %02d
                Medelpris 4h: %.1f öre/kWh
                """, lowestPosition - 1, averagePrice);
    }

    static void input(int[] pricePerHour) {
        for (int i = 0; i < pricePerHour.length; i++) {
            pricePerHour[i] = scanner.nextInt();
            scanner.nextLine();
        }
    }

    static void minMaxMean(int[] pricePerHour) {
        int min = pricePerHour[0];
        int lowestPricePosition = 0;
        for (int i = 0; i < pricePerHour.length; i++) {
            if (pricePerHour[i] < min) {
                min = pricePerHour[i];
                lowestPricePosition = i;
            }
        }
        int max = pricePerHour[0];
        int maxPricePosition = 0;
        for (int i = 0; i < pricePerHour.length; i++) {
            if (pricePerHour[i] > max) {
                max = pricePerHour[i];
                maxPricePosition = i;
            }
        }

        float sum = 0;
        for (int j : pricePerHour) {
            sum += j;
        }
        float averagePrice = sum / pricePerHour.length;

        System.out.printf("Lägsta pris: %02d-%02d, %d öre/kWh\n", lowestPricePosition, lowestPricePosition + 1, min);
        System.out.printf("Högsta pris: %02d-%02d, %d öre/kWh\n", maxPricePosition, maxPricePosition + 1, max);
        System.out.printf("Medelpris: %.2f öre/kWh\n", averagePrice);
    }

    static void sorted(int[] pricePerHour) {
        int[] position = new int[pricePerHour.length];
        for (int i = 0; i < pricePerHour.length; i++) {
            position[i] = i;

        }
        for (int i = 0; i < pricePerHour.length; i++) {
            for (int j = 1; j < pricePerHour.length - i; j++) {
                if (pricePerHour[j - 1] < pricePerHour[j]) {

                    int temp = pricePerHour[j];
                    pricePerHour[j] = pricePerHour[j - 1];
                    pricePerHour[j - 1] = temp;

                    int tempPosition = position[j];
                    position[j] = position[j - 1];
                    position[j - 1] = tempPosition;
                }
            }
        }
        for (int i = 0; i < pricePerHour.length - 1; i++) {
            System.out.printf("%02d-%02d %d öre\n", position[i], position[i] + 1, pricePerHour[i]);
        }
    }

    static void visualisering(int[] pricePerHour) {
        int[] position = new int[pricePerHour.length];
        for (int i = 0; i < pricePerHour.length; i++) {
            position[i] = i;
        }

        for (int i = 0; i < pricePerHour.length; i++) {
            for (int j = 1; j < pricePerHour.length - i; j++) {
                if (pricePerHour[j - 1] < pricePerHour[j]) {

                    int temp = pricePerHour[j];
                    pricePerHour[j] = pricePerHour[j - 1];
                    pricePerHour[j - 1] = temp;

                    int tempPosition = position[j];
                    position[j] = position[j - 1];
                    position[j - 1] = tempPosition;
                }
            }
        }

        int max = pricePerHour[0];
        int min = pricePerHour[0];
        for (int k : pricePerHour) {
            if (k < min) {
                min = k;
            }
            if (k > max) {
                max = k;
            }
        }

        int rows = 6;
        double priceRange = max - min;
        double difference = priceRange/(rows - 1f);
        for (int i = rows; i > 0; i--) {
            int printX;
            if (i == 1) printX = min;
            else printX = (int) (max - (rows - i) * difference);
            if (i == rows) {
                System.out.printf("%3d|", max);
            }
            else if (i == 1) {
                System.out.printf("%3d|", min);
            }
            else {
                    System.out.print("   |");
                }
            for (int j = 0; j < 24; j++) {
                int currentPrice = pricePerHour[j];
                    if (currentPrice >= printX) {
                        System.out.print("  x");
                    } else {
                        System.out.print("   ");
                    }

            }
            System.out.print("\n");
        }
        System.out.print("   |------------------------------------------------------------------------\n");
        System.out.print("   | 00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 20 21 22 23\n");
    }
}