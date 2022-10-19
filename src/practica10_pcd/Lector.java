/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica10_pcd;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Lector extends Thread {
    private ReentrantReadWriteLock lock;

    public Lector(ReentrantReadWriteLock lock) {
        this.lock = lock;
    }
    
    @Override
    public void run(){
        Random rand = new Random();
        lock.readLock().lock();
        try {
            System.out.println("Hilo [" + this.getId() + "] Lee...");
            Thread.sleep(2000);
            if(rand.nextInt(100) < 25){
                lock.readLock().unlock();
                lock.writeLock().lock();
                try {
                    System.out.println("Hilo [" + this.getId() + "] Se convierte en Escritor");
                    Thread.sleep(4000);
                    System.out.println("Hilo [" + this.getId() + "] Termina de Escribir");
                    lock.readLock().lock();
                } finally {
                    lock.writeLock().unlock();
                }
            } else {
                System.out.println("Hilo [" + this.getId() + "] Sigue siendo Lector");
                Thread.sleep(4000);
            }
            System.out.println("Hilo [" + this.getId() + "] Termina de Leer");
        } catch (Exception e) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            lock.readLock().unlock();
        }
    }
}
