/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes.Com;

import java.util.ArrayList;

/**
 *
 * @author NguyenDuy
 */
public class Menu {
    Input ip = new Input();
    /**
     * Print options with x to y
     * @param options
     * @param x
     * @param y
     * @return 
     */
    public int int_getChoice(ArrayList options, int x, int y){
        int respose;
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("%2d.", i+1);
            System.out.println(options.get(i));
        }
            respose = ip.inputInteger("Please choose an option " + x + "-" + y);
        return respose;
    }
    /**
     * Return object options with x to y
     * @param options
     * @param x
     * @param y
     * @return 
     */
    public Object ref_getChoice(ArrayList options, int x, int y){
        int respose;
        do{
            respose = int_getChoice(options, x, y);
        }while(respose < x || respose > y);
        return options.get(respose - 1);
    }
}
