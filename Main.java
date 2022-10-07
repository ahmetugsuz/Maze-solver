import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

import javax.annotation.processing.FilerException;
import javax.swing.*;

public class Main extends JFrame implements ActionListener {
    public static void main(String[] args) throws FileNotFoundException {
        JFrame vindu = new JFrame("Labyrint");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel tekst = new JLabel("");
        JLabel teks = new JLabel("");
        Filleser filleser = new Filleser();
        File f = filleser.les();
        
        Labyrint labyrint = new Labyrint(f);

        Main mn = new Main();
        
        boolean clicked = false;
        JPanel panel = new JPanel(new GridLayout());
        vindu.add(panel);
        JPanel brett = new JPanel();
        int rad = labyrint.hentRad();
        int kolonne = labyrint.hentKolonne();
        brett.setLayout(new GridLayout(rad, kolonne));
        Rute[][] labyrinRute = labyrint.hentRute();
        JButton[][] hverRute = new JButton[rad][kolonne];
        for (int i = 0; i < rad; i++) {
            for (int j = 0; j < kolonne; j++) {
                hverRute[i][j] = new JButton();

                hverRute[i][j].setPreferredSize(new Dimension(50, 50));
                hverRute[i][j].setHorizontalAlignment(JLabel.CENTER);
                hverRute[i][j].setVerticalAlignment(JLabel.CENTER);
                hverRute[i][j].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                if (labyrinRute[i][j] instanceof HvitRute) {
                    hverRute[i][j].setBackground(Color.WHITE);
                } else if (labyrinRute[i][j] instanceof Aapning) {
                    hverRute[i][j].setBackground(Color.WHITE);
                } else if (labyrinRute[i][j] instanceof SortRute) {
                    hverRute[i][j].setBackground(Color.BLACK);
                }

                hverRute[i][j].setOpaque(true);
                hverRute[i][j].setFont(new Font("Monospaced", Font.BOLD, 50));
                brett.add(hverRute[i][j]);


                class HverRute implements ActionListener {
                    int x;
                    int y;
                    boolean clicked;
                    HverRute(int x, int y, boolean clicked) {
                        this.x = x;
                        this.y = y;
                        this.clicked = clicked;
                    }

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        tekst.setText("rad: " + x + " kolonne " + y);
                        if (labyrinRute[x][y] instanceof HvitRute) {
                            
                            mn.klikketRute(x, y, labyrint, hverRute[x][y], teks);
                            
                            mn.utveier(x, y, hverRute, labyrint, rad, kolonne);
                            
                            
                            
                            //
                        }

                    }
                    
                }
                

                hverRute[i][j].addActionListener(new HverRute(i, j, clicked));

            }
        }

        panel.add(teks);
        panel.add(tekst);
        panel.add(brett);
        vindu.pack();
        vindu.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

    void klikketRute(int x, int y, Labyrint labyrint, JButton hverRute, JLabel tekst) {
        ArrayList<ArrayList<Tuppel>> utveier = labyrint.finnUtveiFra(x, y);
        for (ArrayList<Tuppel> lis : utveier) {

            for (Tuppel t : lis) {
                tekst.setText("" + t.toString());

            }
            System.out.println();

        }

    }

    void utveier(int x, int y, JButton[][] hverRute, Labyrint labyrint, int rad, int kolonne) {
        int startKol = x;
        int startRad = y;
        boolean clicked = false;
        System.out.println("Utveier:");
        reset(hverRute, rad, kolonne); //fjerner forrige trykk
        ArrayList<ArrayList<Tuppel>> utveier = labyrint.finnUtveiFra(startKol, startRad);
        for (ArrayList<Tuppel> lis : utveier) {
            for (Tuppel t : lis)
                System.out.println(t);

            System.out.println();
        }
        System.out.println();

        for (ArrayList<Tuppel> lis : utveier) {
            for (Tuppel t : lis) {
                
                
                hverRute[t.x][t.y].setBackground(Color.RED);


            }
        }
        
        

    }

    void reset(JButton[][] hverRute, int rad, int kolonne){
        for (int i = 0; i < rad; i++) {
            for (int j = 0; j < kolonne; j++) {
                if (hverRute[i][j].getBackground() == Color.RED) {
                    hverRute[i][j].setBackground(Color.WHITE);
                }
            }

        }

        
    }

}
