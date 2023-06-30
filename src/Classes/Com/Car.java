/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes.Com;

/**
 *
 * @author NguyenDuy
 */
public class Car implements Comparable<Car> {

    private String carID;
    protected Brand brand;
    private String color;
    private String frameID;
    private String engineID;

    public Car() {
    }

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    @Override
    public String toString() {
            return String.format("#%9s#%30s#%8s#%8s#%9s#", carID, brand.getBrandName(), color, frameID, engineID);
    }
    
    @Override
    public int compareTo(Car o) {
        if (this.brand != null || o.brand != null) {
            int d = this.brand.getBrandName().compareToIgnoreCase(o.brand.getBrandName());
            if (d != 0) {
                return d;
            } else {
                return this.getCarID().compareToIgnoreCase(o.getCarID());
            }
        }
        return 0;
    }

}
