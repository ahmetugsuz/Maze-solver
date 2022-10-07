import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFileChooser;

import java.io.*;


public class Filleser {
    File fil;
    Scanner lesFil;
    int response;
    JFileChooser filvelger = new JFileChooser(".");


    File les(){
        filvelger.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        response= filvelger.showOpenDialog(null);
        if (response== JFileChooser.APPROVE_OPTION){
            fil = filvelger.getSelectedFile();

        }
        return fil;

    }
}
