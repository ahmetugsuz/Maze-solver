import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;

public class Tuppel {

    Rute[] koordinater;

    int x;
    int y;
    Tuppel(int x, int y){
        
        this.x = x;
        this.y = y;
        
    }
    public String toString(){
        return ("rad: "+ x +", kolonne: "+ y);
    }

    

    
}
