/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica10_pcd;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Escritor extends Thread {

    private ReentrantReadWriteLock lock;

    public Escritor(ReentrantReadWriteLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.writeLock().lock();
        try {
            System.out.println("Hilo [" + this.getId() + "] Escribe...");
            Thread.sleep(2000);
            System.out.println("Hilo [" + this.getId() + "] Termina de Escribir");
        } catch (InterruptedException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
