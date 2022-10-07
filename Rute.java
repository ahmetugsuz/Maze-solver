import java.util.ArrayList;

import javax.swing.JButton;



abstract class Rute {

    public int x; //rad
    public int y; //kolonne
    public int radPos; //rad
    public int kolonnePos; //kolonne
    Rute ruteNord;
    Rute ruteVest;
    Rute ruteSyd;
    Rute ruteOst;
    Labyrint labyrint;
    Rute[] naboer;
    ArrayList<Tuppel> kordinater = new ArrayList<Tuppel>();

    Rute(int x, int y, Labyrint labyrint){
        this.x= x;
        this.y = y;
        this.labyrint = labyrint;
        
        radPos = x;
        kolonnePos = y;
        //new Labyrint(x, y);

    }

    

    void settNord(Rute x){
        ruteNord = x;
    }
    Rute hentNord(){
        return ruteNord;
    }
    
    void settVest(Rute x){
        ruteVest = x;
    }
    Rute hentVest(){
        return ruteVest;
    }

    void settOst(Rute x){
        ruteOst = x;
    }
    Rute hentOst(){
        return ruteOst;
    }

    void settSyd(Rute x){
        ruteSyd = x;
    }
    Rute hentSyd(){
        return ruteSyd;
    }


    void leggtilAlleNaboerHvite(Rute Nord, Rute Ost, Rute Syd, Rute Vest){
        ruteNord = Nord;
        ruteOst = Ost;
        ruteSyd = Syd;
        ruteVest = Vest;
        naboer = new Rute[]{ruteNord, ruteOst, ruteSyd, ruteVest};
    }
    void hentNaboer(){
        System.out.println("ost ");
    }
    

    boolean erAapning(){
        return true;
    }



    abstract char tilTegn();

    abstract void gaa(ArrayList<Tuppel> tuppel, Rute besokte);

    void finnUtvei(){
        gaa(kordinater, null);
    }

    abstract boolean erGyldig();

}
