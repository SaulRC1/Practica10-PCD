/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica10_pcd;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Practica10_PCD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        
        ArrayList<Lector> lectores = new ArrayList<>();
        ArrayList<Escritor> escritores = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            lectores.add(new Lector(lock));
            escritores.add(new Escritor(lock));
        }
        
        for (int i = 0; i < 10; i++) {
            lectores.get(i).start();
            escritores.get(i).start();
        }
        
        for (int i = 0; i < 10; i++) {
            try {
                lectores.get(i).join();
                escritores.get(i).join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Practica10_PCD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
