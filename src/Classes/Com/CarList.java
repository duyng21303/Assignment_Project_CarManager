/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes.Com;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author NguyenDuy
 */
public class CarList extends Car{

    private ArrayList<Car> arr;
    private Input ip;
    private ArrayList<Brand> brandList;
    private Menu menu;

    public CarList(BrandList bList) {
        arr = new ArrayList<>();
        ip = new Input();
        brandList = bList.getArr(); //create BrandList array in CarList
        menu = new Menu();
    }

//    public boolean loadFromFile(String fCar) {
//        try {
//            //check file exists
//            File f = new File(fCar);
//            if (!f.exists()) {
//                return false;
//            }
//            //read file
//            FileInputStream fis = new FileInputStream(f);
//            InputStreamReader isr = new InputStreamReader(fis);
//            BufferedReader bf = new BufferedReader(isr);
//            if (f.length() == 0) {
//                System.out.println("File is empty");
//                addCar();
//            }
//            String details;
//            int count = 0;
//            while ((details = bf.readLine()) != null) {
//                //get each element separated by ","
//                StringTokenizer stk = new StringTokenizer(details, ",");
//                if (stk.countTokens() < 5) {
//                    System.out.println("Have wrong format in file, it will be effect data");
//                    return false;
//                }
//                //from the elements assigned to the field
//                String carID = stk.nextToken().trim();
//                String brandID = stk.nextToken().trim();
//                String color = stk.nextToken().trim();
//                String frameID = stk.nextToken().trim();
//                String engineID = stk.nextToken().trim();
//                Brand brands = ip.searchBrandID(brandList, brandID);
//                //Check null brand
//                if (brands != null) {
//                    arr.add(new Car(carID, brands, color, frameID, engineID));
//                } else {
//                    count++;
//                    arr.add(new Car(carID, new Brand("Empty", "Empty", "Empty", 0), color, frameID, engineID));
//                }
//            }
//            //Error null brands 
//            if (count > 0){
//                System.out.println("Have " + count + " null brand when load file!");
//            }
//            //close file
//            bf.close();
//            fis.close();
//            isr.close();
//        } catch (FileNotFoundException e) {
//            // log error or throw exception
//            System.err.println("File not found: " + fCar);
//            return false;
//        } catch (IOException e) {
//            // log error or throw exception
//            System.err.println("Error reading from file: " + fCar);
//            return false;
//        } catch (NumberFormatException e) {
//            // log error or throw exception
//            System.err.println("Error parsing double value from input: " + e.getMessage());
//            return false;
//        }
//        return true;
//    }
    public boolean loadFromFile(String fCar) {
        try {
            //check file exists
            File f = new File(fCar);
            if (!f.exists()) {
                return false;
            }
            //read file
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream oi = new ObjectInputStream(fis);
            if (f.length() == 0) {
                System.err.println("File is empty");
                addCar();
            }
            boolean check = true;
            while (check) {
                try {
                    Car c = (Car) oi.readObject();
                    arr.add(c);
                } catch (EOFException e) {
                    break;
                }
            }
            fis.close();
            oi.close();
        } catch (FileNotFoundException e) {
            // log error or throw exception
            System.err.println("File not found: " + fCar);
            return false;
        } catch (IOException| ClassNotFoundException e) {
            // log error or throw exception
            System.err.println("Error reading from file: " + fCar + e);
            return false;
        } catch (NumberFormatException e) {
            // log error or throw exception
            System.err.println("Error parsing double value from input: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean saveToFile(String fCar) {
        try {
            File f = new File(fCar);
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream o = new ObjectOutputStream(fos);
            for (Car car : arr) {
                o.writeObject(car);
            }
            fos.close();
            o.close();
            System.out.println("Save successfull!");
            return true; // Indicates a successful save
        } catch (IOException e) {
            System.err.println("IO error save file: " + e.getMessage());
            return false; // Indicates that the saving process failed
        }
    }

    public int searchID(String carID) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getCarID().equals(carID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchFrameID(String fID) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getFrameID().equals(fID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngineID(String eID) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getEngineID().equals(eID)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Add car to array
     */
    public void addCar() {
        String carID;
        Brand brand;
        String color;
        String frameID;
        String engineID;
        boolean choice = true;
        while (choice) {
            System.out.println("Enter id car: ");
            carID = ip.inputCarID(arr);
            System.out.println("Select the brand you want to add to car");
            brand = (Brand) menu.ref_getChoice(brandList, 1, brandList.size());
            color = ip.inputStringNotBlank("Enter color: ");
            frameID = ip.inputFrameID(arr, "Enter frame id (must be fomat 'F00000': ");
            engineID = ip.inputEngineID(arr, "Enter engine id (must be fomat 'E00000': ");
            arr.add(new Car(carID, brand, color, frameID, engineID));
            choice = ip.inputYN("Continue?(Y/N)");
        }
    }

    /**
     * Print list by based brand name
     */
    public void printBasedBrandName() {
        int count = 0;
        String aPartOfBrandName = ip.inputStringNotBlank("Enter based brand name: ").toUpperCase().trim();
        for (Car car : arr) {
            if (car.brand.getBrandName().trim().toUpperCase().contains(aPartOfBrandName)) {
                if (count == 0) {
                    System.out.println("Found!: ");
                    graphic();
                    graphicDetail();
                    graphic();
                }
                System.out.println(car.toString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No car is detected!");
        } else {
            graphic();
        }
    }

    /*
     * Remove car by car id
     *
     * @return
     */
    public boolean removeCar() {
        String id = ip.inputStringNotBlank("Enter id of car you want remove: ");
        if (ip.searchCarID(arr, id) != null) {
            arr.remove(ip.searchCarID(arr, id));
            System.out.println("Remove successfully!");
            return true;
        } else {
            System.out.println("Not found!");
            return false;
        }
    }

    /**
     * Update car by id
     *
     * @return
     */
    public boolean updateCar() {
        String id = ip.inputStringNotBlank("Enter id of car you want to update: ").toUpperCase();
        if (ip.searchCarID(arr, id) != null) {
            System.out.println("Found id! Here is id: ");
            graphic();
            graphicDetail();
            graphic();
            System.out.println(ip.searchCarID(arr, id).toString());
            graphic();
            System.out.println("Select the brand you want to update to car");
            Brand brands = (Brand) menu.ref_getChoice(brandList, 1, brandList.size());
            String color = ip.inputStringNotBlank("Enter color you want to update: ");
            String frameID = ip.inputFrameID(arr, "Enter frame id you want to update (must be format 'F00000'): ");
            String engineID = ip.inputEngineID(arr, "Enter engine id you want to update (must be format 'E00000'): ");
            arr.set(searchID(id), new Car(id, brands, color, frameID, engineID));
            System.out.println("Update successfully!");
            return true;
        } else {
            System.out.println("Not found!");
            return false;
        }
    }

    public void graphicDetail() {
        System.out.printf("#%9s#%30s#%8s#%8s#%9s#\n", "CAR ID", "CAR BRAND NAME", "COLOR", "FRAME ID", "ENGINE ID");
    }

    public void graphic() {
        for (int i = 0; i < 70; i++) {
            System.out.printf("#");
        }
        System.out.println("");
    }

    /**
     * Print list of car
     */
    public void listCars() {
        Collections.sort(arr);
        graphic();
        graphicDetail();
        graphic();
        for (Car car : arr) {
            System.out.println(car);
        }
        graphic();
    }
}
