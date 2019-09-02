/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.config;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class DBManager {
    private static DBManager instance;
    private EntityManagerFactory emf;
    private DBManager() {}
    
    public synchronized static DBManager getManager() {
        if (instance == null)
            instance = new DBManager();
        return instance;
    }
    public EntityManagerFactory createEntityManagerFactory() {
        if (emf == null)
       emf = Persistence.createEntityManagerFactory("Przychodnielekarskie_war_1.0-SNAPSHOTPU");
        return emf;
    }
    public EntityManager createEntityManager() {
        return this.createEntityManagerFactory().createEntityManager();
    }

    public void EntityManagerFactory() {
        if (emf != null)
            emf.close();
    }

}