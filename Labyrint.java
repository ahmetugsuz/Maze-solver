import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import java.util.Scanner;

class Labyrint {
    
    Scanner fil = null;
    int x; // rad
    int y; // kolonne
    Rute[][] labyrintRute;
    Rute nord, Ost, Syd, Vest;
    ArrayList<ArrayList<Tuppel>> utvei;
    JPanel brett = new JPanel();
    JLabel tekst = new JLabel("");
    Labyrint(File filnavn) throws FileNotFoundException {
        try {
            fil = new Scanner(filnavn);
        } catch (Exception e) {
            System.out.println("kan ikke lese filen " + filnavn);
            System.exit(1);
        }

        boolean forste = false;
        int rad = 0;

        while (fil.hasNextLine()) {
            String fillinje = fil.nextLine();
            if (!forste) {
                String[] biter = fillinje.split(" ");
                x = Integer.parseInt(biter[0]);
                y = Integer.parseInt(biter[1]);
                brett.setLayout(new GridLayout(x, y));
                System.out.println("Det er totalt: " + x + " rader");
                System.out.println("Det er totalt: " + y + " kolonner");
                labyrintRute = new Rute[x][y];
                forste = true;
                continue;
            } else {
                

                for (int kolonne = 0; kolonne < fillinje.length(); kolonne++) { // kolonne

                    JButton hverRute = new JButton("");
                    hverRute.setPreferredSize(new Dimension(50, 50));
                    hverRute.setHorizontalAlignment(JLabel.CENTER);
                    hverRute.setVerticalAlignment(JLabel.CENTER);
                    hverRute.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    hverRute.setOpaque(true);
                    hverRute.setFont(new Font("Monospaced", Font.BOLD, 50));
                    
    
                    class HverRute implements ActionListener{
                        int x;
                        int y;
                        HverRute(int x, int y){
                            this.x = x;
                            this.y =y;
                        }
                        @Override
                        public void actionPerformed (ActionEvent e){
                            
                            tekst.setText("rad: " + x + " kolonne " + y);
                        }
                    }
                    hverRute.addActionListener(new HverRute(x, y));
                    brett.add(hverRute);
                    // System.out.println("rad: " + rad + " kolonne: " + i);
                    if (fillinje.charAt(kolonne) == '#') {
                        hverRute.setBackground(Color.BLACK);
                        SortRute sorte = new SortRute(rad, kolonne, this);
                        labyrintRute[rad][kolonne] = sorte;

                    } else if (fillinje.charAt(kolonne) == '.') {
                        if (rad == 0 || kolonne == 0 || rad == x-1 || kolonne == y-1) {
                            // System.out.println("aapning");
                            Aapning Aapning = new Aapning(rad, kolonne, this);
                            labyrintRute[rad][kolonne] = Aapning;
                            hverRute.setBackground(Color.WHITE);
                        } else {

                            HvitRute hvite = new HvitRute(rad, kolonne, this);
                            labyrintRute[rad][kolonne] = hvite;
                            hverRute.setBackground(Color.WHITE);
                        }

                    }


                }

            }

            rad++;

        }

        for (Rute[] lab : labyrintRute) {
            for (Rute object : lab) {
                nord = null;
                Ost = null;
                Syd = null;
                Vest = null;
                // if ((labyrintRute[object.x][object.x]).tilTegn() == '.'){
                if (object.x - 1 >= 0) {
                    nord = labyrintRute[object.x - 1][object.y];
                }
                if (object.x + 1 < x) {
                    Syd = labyrintRute[object.x + 1][object.y];
                }

                if (object.y - 1 >= 0) {
                    Vest = labyrintRute[object.x][object.y - 1];
                }

                if (object.y + 1 < y) {
                    Ost = labyrintRute[object.x][object.y + 1];
                }

                object.leggtilAlleNaboerHvite(nord, Ost, Syd, Vest);

            }
        }

        // System.out.println("Rader: " + rad);

    }

    void lesFil() throws FileNotFoundException{
        JFileChooser velger = new JFileChooser();
        int resultat = velger.showOpenDialog(null);
        if (resultat != JFileChooser.APPROVE_OPTION) 
            System.exit(1);
        File f = velger.getSelectedFile();
        Scanner leser = null;
        try {
            leser = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
        
        
        
    }

   

    void initGUI() {

        brett.setLayout(new GridLayout(x, y));
        for (int i = 0; i < x; i++){
            for (int j = 0; j< y; j++){
                JButton hverRute = new JButton("");
                

                hverRute.setPreferredSize(new Dimension(50, 50));
                hverRute.setHorizontalAlignment(JLabel.CENTER);
                hverRute.setVerticalAlignment(JLabel.CENTER);
                hverRute.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                
                hverRute.setOpaque(true);
                hverRute.setFont(new Font("Monospaced", Font.BOLD, 50));
                
                if (labyrintRute[i][j] instanceof HvitRute){
                    hverRute.setBackground(Color.WHITE);
                }
                else if (labyrintRute[i][j] instanceof Aapning){
                    hverRute.setBackground(Color.WHITE);
                }
                else if (labyrintRute[i][j] instanceof SortRute){
                    hverRute.setBackground(Color.BLACK);
                }

                class HverRute implements ActionListener{
                    int x;
                    int y;
                    HverRute(int x, int y){
                        this.x = x;
                        this.y =y;
                    }
                    @Override
                    public void actionPerformed (ActionEvent e){
                        
                        tekst.setText("rad: " + x + " kolonne " + y);
                    }
                }
                hverRute.addActionListener(new HverRute(i, j));
                brett.add(tekst);
                brett.add(hverRute);
                
                
            }
        }


    }

    public String toString() {
        String s = "";
        for (int i = 0; i < labyrintRute.length; i++) { // i < antall rader?

            for (int j = 0; j < labyrintRute[i].length; j++) { // j mindre enn antall kolonner?
                s += labyrintRute[i][j].tilTegn();
                

            }
            s += "\n";
        }
        return s;
    }

    ArrayList<ArrayList<Tuppel>> finnUtveiFra(int x, int y ){
        
        utvei = new ArrayList<ArrayList<Tuppel>>();
        
        labyrintRute[x][y].finnUtvei();
        
        return utvei;
    }

    int hentRad(){
        return x;
    }

    int hentKolonne(){
        return y;
    }

    

    Rute[][] hentRute() {
        return labyrintRute;
    }

    boolean erAapning() {
        return true;
    }
    
}