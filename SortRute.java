import java.util.ArrayList;

import javax.swing.JButton;

public class SortRute extends Rute {

    char tegning;
    SortRute(int x, int y, Labyrint labyrint){
        super(x, y, labyrint);
        

    }
    
    @Override
    char tilTegn() {
        
        return '#';
        
    }




    @Override
    boolean erGyldig() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    void gaa(ArrayList<Tuppel> tuppel, Rute besokte) {
        // TODO Auto-generated method stub
        
    }
    
}
