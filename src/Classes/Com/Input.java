/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes.Com;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author NguyenDuy
 */
public class Input {

    Scanner sc = new Scanner(System.in);

    /**
     * Input valid integer
     *
     * @param msg
     * @return s
     */
    public int inputInteger(String msg)//Input true integer
    {
        System.out.println(msg);
        int integer;
        boolean cont = true;
        while (cont) {
            try {
                integer = Integer.parseInt(sc.nextLine());
                return integer;
            } catch (NumberFormatException e) {
                System.out.println("This must be number");
                cont = true;
            }
        }
        return 0;
    }
    /**
     * Input valid double
     * @param msg
     * @return 
     */
    public double inputDouble(String msg)//Input true double
    {
        System.out.println(msg);
        double iDouble;
        boolean cont = true;
        while (cont) {
            try {
                iDouble = Double.parseDouble(sc.nextLine());
                if (!(iDouble > 0)) {
                    System.out.println("Can't be less than 0");
                } else {
                    return iDouble;
                }
            } catch (NumberFormatException e) {
                System.out.println("This must be number");
                cont = true;
            }
        }
        return 0;
    }
    /**
     * Search and return brand id
     * @param arr
     * @param id
     * @return 
     */
    public Brand searchBrandID(ArrayList<Brand> arr, String id) {
        for (Brand brand : arr) {
            if (brand.getBrandID().trim().equalsIgnoreCase(id)) {
                return brand;
            }
        }
        return null;
    }
    /**
     * Search and return car
     * @param arr
     * @param id
     * @return 
     */
    public Car searchCarID(ArrayList<Car> arr, String id) {
        for (Car car : arr) {
            if (car.getCarID().trim().equalsIgnoreCase(id)) {
                return car;
            }
        }
        return null;
    }
    
    
    
    /**
     * Search and return car by frame id
     * @param arr
     * @param id
     * @return 
     */
    public Car searchFrameID(ArrayList<Car> arr, String id) {
        for (Car car : arr) {
            if (car.getFrameID().equalsIgnoreCase(id)) {
                return car;
            }
        }
        return null;
    }
    /**
     * Search and return car by engine id
     * @param arr
     * @param id
     * @return 
     */
    public Car searchEngineID(ArrayList<Car> arr, String id) {
        for (Car car : arr) {
            if (car.getEngineID().equalsIgnoreCase(id)) {
                return car;
            }
        }
        return null;
    }
    /**
     * Input valid brand id (Duplicated and empty)
     * @param arr
     * @return 
     */
    public String inputBrandID(ArrayList<Brand> arr) {
        String id;
        do {

            System.out.println("Enter brand id: ");
            id = sc.nextLine();
            if (searchBrandID(arr, id) != null) {
                System.out.println("Duplicated code.Try with another one");
            } else if (id.trim().isEmpty()) {
                System.out.println("ID can't not empty!");
            } else {
                return id.toUpperCase();
            }
        } while (true);
    }
    /**
     * Input string is not blank
     * @param msg
     * @return 
     */
    public String inputStringNotBlank(String msg) {
        String input = "";
        while (input.trim().isEmpty()) {
            System.out.println(msg);
            input = sc.nextLine();
        }

        return input;
    }
    /**
     * Input yes or no (Y/N)
     * @param msg
     * @return 
     */
    public boolean inputYN(String msg) {
        String choice;
        while (true) {
            System.out.println(msg);
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                return true;
            } else if (choice.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("Must be Y or N");
                continue;
            }
        }
    }
    /**
     * Input valid car id (Duplicated and empty)
     * @param arr
     * @return 
     */
    public String inputCarID(ArrayList<Car> arr) {
        String id;
        do {

            System.out.println("Enter car id: ");
            id = sc.nextLine();
            if (searchCarID(arr, id) != null) {
                System.out.println("Duplicated ID.Try with another one");
            } else if (id.trim().isEmpty()) {
                System.out.println("ID can't not empty!");
            } else {
                return id.toUpperCase();
            }
        } while (true);
    }
    /**
     * Input valid car frame id (Duplicated, empty, format)
     * @param arr
     * @return 
     */
    public String inputFrameID(ArrayList<Car> arr, String msg) {
        String id;
        do {
            id = inputPattern(msg, "^[fF]\\d{5}$");
            if (searchFrameID(arr, id) != null) {
                System.out.println("Duplicated ID.Try with another one");
            } else if (id.trim().isEmpty()) {
                System.out.println("ID can't not empty!");
            } else {
                return id.toUpperCase();
            }
        } while (true);
    }
    /**
     * Input valid car engine id (Duplicated, empty, format)
     * @param arr
     * @return 
     */
    public String inputEngineID(ArrayList<Car> arr, String msg) {
        String id;
        do {
            id = inputPattern(msg, "^[eE]\\d{5}$");
            if (searchEngineID(arr, id) != null) {
                System.out.println("Duplicated ID.Try with another one");
            } else if (id.trim().isEmpty()) {
                System.out.println("ID can't not empty!");
            } else {
                return id.toUpperCase();
            }
        } while (true);
    }
    /**
     * Check true pattern string
     * @param msg
     * @param patternString
     * @return 
     */
    public String inputPattern(String msg, String patternString) {
        String dataString;
        do {
            System.out.println(msg);
            dataString = sc.nextLine().trim();
        } while (!dataString.matches(patternString));
        return dataString;
    }
}

