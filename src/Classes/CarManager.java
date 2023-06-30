/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Classes.Com.BrandList;
import Classes.Com.CarList;
import Classes.Com.Menu;
import java.util.ArrayList;

/**
 *
 * @author NguyenDuy
 */
public class CarManager {

    public static void main(String[] args) {
        ArrayList<String> ops = new ArrayList<>();
        BrandList bl = new BrandList();
        bl.loadFromFile("brands.txt");
        CarList cl = new CarList(bl);
        cl.loadFromFile("cars.txt");
        Menu menu = new Menu();
        int choice;
        ops.add("List all brands");
        ops.add("Add a new brand");
        ops.add("Search a brand based on its ID");
        ops.add("Update a brand");
        ops.add("Save brands to the file, named brands.txt");
        ops.add("List all cars in ascending order of brand names");
        ops.add("List cars based on a part of an input brand name");
        ops.add("Add a car");
        ops.add("Remove a car based on its ID");
        ops.add("Update a car based on its ID");
        ops.add("Save cars to file, named cars.txt");
        ops.add("Orther: Exit");
        do {
            choice = menu.int_getChoice(ops, 1, 11);
            switch (choice) {
                case 1:
                    bl.listBrands();
                    break;
                case 2:
                    bl.addBrand();
                    break;
                case 3:
                    bl.searchBrandByID();
                    break;
                case 4:
                    bl.updateBrand();
                    break;
                case 5:
                    bl.saveToFile("brands.txt");
                    break;
                case 6:
                    cl.listCars();
                    break;
                case 7:
                    cl.printBasedBrandName();
                    break;
                case 8:
                    cl.addCar();
                    break;
                case 9:
                    cl.removeCar();
                    break;
                case 10:
                    cl.updateCar();
                    break;
                case 11:
                    cl.saveToFile("cars.txt");
                    break;
                default:
                    System.out.println("Bye!");
            }
        } while (!(choice < 1 || choice > 11));
    }
}
