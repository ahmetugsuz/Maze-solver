import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;

public class HvitRute extends Rute{

    
    char tegning;
    char[][] nyRute;
    
    HvitRute(int x, int y, Labyrint labyrint){
        super(x, y, labyrint);
        
    }
    
    @Override
    char tilTegn() {
        
        return '.';
        
    }
    @Override
    void leggtilAlleNaboerHvite(Rute Nord, Rute Ost, Rute Syd, Rute Vest) {

        super.leggtilAlleNaboerHvite(Nord, Ost, Syd, Vest);
    }
    
    @Override
    void gaa(ArrayList<Tuppel> sti, Rute besokte ) {
        Tuppel tuppel = new Tuppel(x, y);
        ArrayList<Tuppel> nySti = new ArrayList<>(sti);
        nySti.add(tuppel);
        
       
        if (sti.size() != 0){

        if (ruteNord != besokte){
            ruteNord.gaa(nySti, this);
        }
        if (ruteSyd != besokte){
            ruteSyd.gaa(nySti, this);
        }

        if (ruteOst != besokte){
            ruteOst.gaa(nySti, this);
        }
        if (ruteVest != besokte){
            ruteVest.gaa(nySti, this);
        }

    }else{
        ruteNord.gaa(nySti, this);
        ruteSyd.gaa(nySti, this);
        ruteOst.gaa(nySti, this);
        ruteVest.gaa(nySti, this);

    }
    }
        
        /*
        if (erGyldig()){
            //hvis vi har gyldig
            /*if (erAapning()){
                return true; // kommet til slutten, slutt kordinatet
            }

            System.out.println("vi tar kordinatene: " + radPos + ", " + kolonnePos);
            //nord:
            boolean sjekk = gyldigRute(ruteNord);// er det en nord vi kan gaa opp til
            if(sjekk){
                sjekk = ruteNord.gaa(); //hvis ikke return verdien er true gaar vi videre, hvis den er true, fortsetter vi med aa kjore paa nytt til vi kommer til slutt kordinatet
            }

            //syd:
            if(!sjekk){
                //hvis det ikke var en nord, saa beveger vi oss til aa se om ruteSyd finnes, hvis ja kjorer vi metoden paa nytt
                if(gyldigRute(ruteSyd)){
                    sjekk = ruteSyd.gaa();
                }
            }

            if(!sjekk){
                if(gyldigRute(ruteOst)){
                    sjekk = ruteOst.gaa();
                } //hvis ost finnes og ikke de andre
            }

            if(!sjekk){
                if(gyldigRute(ruteVest)){
                    sjekk = ruteVest.gaa();
                }
            }
            //hvis ingen av rutene er true da gaar vi ned til at det ikke er en utvei her.

            //ost:

            ruteNord.gaa();

            return sjekk;
        }
        return false;
        */

    
     
    


    @Override
    boolean erGyldig() {
        
        return ruteNord != null || ruteOst != null || ruteSyd != null || ruteVest != null;
    }

    boolean gyldigRute(Rute x){
        return x != null;
    }

    

   


}
