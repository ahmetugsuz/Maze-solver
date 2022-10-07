import java.util.ArrayList;

import javax.swing.JButton;



public class Aapning extends HvitRute {

    Aapning(int x, int y, Labyrint labyrint) {
        super(x, y, labyrint);
        
    }
    
    @Override
    void gaa(ArrayList<Tuppel> sti, Rute besokte) {
        Tuppel tuppel = new Tuppel(x, y);
        ArrayList<Tuppel> nySti = new ArrayList<>(sti);
        nySti.add(tuppel);
        labyrint.utvei.add(nySti);
    }
}
