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
import java.util.StringTokenizer;

/**
 *
 * @author NguyenDuy
 */
public class BrandList extends Brand{

    private ArrayList<Brand> arr;
    private Input ip;

    public BrandList() {
        arr = new ArrayList<>();
        ip = new Input();
    }

    public ArrayList<Brand> getArr() {
        return arr;
    }

//    public boolean loadFromFile(String fBrand) {
//        try {
//            //check file exists
//            File f = new File("Xbrands.txt");
//            if (!f.exists()) {
//                return false;
//            }
//            //read file
//            FileInputStream fis = new FileInputStream(f);
//            InputStreamReader isr = new InputStreamReader(fis);
//            BufferedReader bf = new BufferedReader(isr);
//            if (f.length() == 0) {
//                System.out.println("File is empty");
//                addBrand();
//            }
//            String details;
//            while ((details = bf.readLine()) != null) {
//                //get each element separated by ","
//                StringTokenizer stk = new StringTokenizer(details, "[,:]");
//                if (stk.countTokens() < 4) {
//                    System.out.println("Have wrong format in file, it will be effect data");
//                    return false;
//                }
//                //from the elements assigned to the field
//                String brandID = stk.nextToken().trim();
//                String brandName = stk.nextToken().trim();
//                String soundBrand = stk.nextToken().trim();
//                double price = Double.parseDouble(stk.nextToken());
//                arr.add(new Brand(brandID, brandName, soundBrand, price));
//            }
//            //close file
//            bf.close();
//            fis.close();
//            isr.close();
//        } catch (FileNotFoundException e) {
//            // log error or throw exception
//            System.err.println("File not found: " + fBrand);
//            return false;
//        } catch (IOException e) {
//            // log error or throw exception
//            System.err.println("Error reading from file: " + fBrand);
//            return false;
//        } catch (NumberFormatException e) {
//            // log error or throw exception
//            System.err.println("Error parsing double value from input: " + e.getMessage());
//            return false;
//        }
//        return  true;
//    }
    public boolean loadFromFile(String fBrand) {
        try {
            //check file exists
            File f = new File(fBrand);
            if (!f.exists()) {
                return false;
            }
            //read file
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream oi = new ObjectInputStream(fis);
            if (f.length() == 0) {
                System.err.println("File is empty");
                addBrand();
            }
            boolean check = true;
            while (check) {
                try {
                    Brand b = (Brand) oi.readObject();
                    arr.add(b);
                } catch (EOFException e) {
                    break;
                }
            }
            fis.close();
            oi.close();
        } catch (FileNotFoundException e) {
            // log error or throw exception
            System.err.println("File not found: " + fBrand);
            return false;
        } catch (IOException | ClassNotFoundException e) {
            // log error or throw exception
            System.err.println("Error reading from file: " + fBrand + e);
            return false;
        } catch (NumberFormatException e) {
            // log error or throw exception
            System.err.println("Error parsing double value from input: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean saveToFile(String fBrand) {
        try {
            File f = new File(fBrand);
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream o = new ObjectOutputStream(fos);
            for (Brand brand : arr) {
                o.writeObject(brand);
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

    public int searchID(String bID) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getBrandID().equals(bID)) {
                return i;
            }
        }
        return -1;
    }

    public void graphicDetail() {
        System.out.printf("#%10s#%30s#%20s#%8s#\n", "BRAND ID", "BRAND NAME", "SOUND BRAND", "PRICE");
    }

    public void graphic() {//Graphic with line
        for (int i = 0; i < 73; i++) {
            System.out.printf("#");
        }
        System.out.println("");
    }

    public Brand getUserChoice() {
        Menu mnu = new Menu();
        return (Brand) mnu.ref_getChoice(arr, 1, arr.size());
    }

    public void addBrand() {
        String id;
        String brandName;
        String soundBrand;
        double price;
        boolean choice = true;
        while (choice) {
            System.out.println("Enter id brand: ");
            id = ip.inputBrandID(arr);
            brandName = ip.inputStringNotBlank("Enter brand name: ");
            soundBrand = ip.inputStringNotBlank("Enter sound brand: ");
            price = ip.inputDouble("Enter price: ");
            arr.add(new Brand(id, brandName, soundBrand, price));
            choice = ip.inputYN("Continue?(Y/N)");
        }
    }

    /**
     * Update brand by id
     */
    public void updateBrand() {
        String id = ip.inputStringNotBlank("Enter id to update: ").toUpperCase();

        if (ip.searchBrandID(arr, id) != null) {
            System.out.println("Found id! Here is brand: ");
            graphic();
            graphicDetail();
            graphic();
            System.out.println(ip.searchBrandID(arr, id).toString());
            graphic();
            String brandName = ip.inputStringNotBlank("Update brand name: ");
            String soundBrand = ip.inputStringNotBlank("Update sound brand: ");
            double price = ip.inputDouble("Update price: ");
            arr.set(searchID(id), new Brand(id, brandName, soundBrand, price));
            System.out.println("Update successfully!");
        } else {
            System.out.println("Not found!");
        }
    }

    /**
     * Search brand by id
     */
    public void searchBrandByID() {
        String id = ip.inputStringNotBlank("Enter id brand you want to search: ");
        if (ip.searchBrandID(arr, id) != null) {
            System.out.println("Found id! Here is brand: ");
            graphic();
            graphicDetail();
            graphic();
            System.out.println(ip.searchBrandID(arr, id).toString());
            graphic();
        } else {
            System.out.println("Can't found brand!");
        }
    }

    /**
     * Print list of brands
     */
    public void listBrands() {
        graphic();
        graphicDetail();
        graphic();
        for (Brand brand : arr) {
            System.out.println(brand.toString());
        }
        graphic();
    }
}
