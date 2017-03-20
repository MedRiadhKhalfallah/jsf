/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory = builSessionFactory();
    private static ServiceRegistry serviceRegistry;
    private static Session session = null;
    
    private static SessionFactory builSessionFactory()
    {
    	try{
    		Configuration configuration = new Configuration();
    		configuration.configure("config/hibernate.cfg.xml");
    		
    	    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
    	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    	    return sessionFactory;
    	}catch(Throwable ex){
    		System.err.println(ex);
    		throw new ExceptionInInitializerError(ex);
    		
    	}
    }
 
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static Session openSession() {
        return sessionFactory.openSession();
    }
    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    public static void close() {
        if(sessionFactory !=null)
        {
        	sessionFactory.close();
        }
        sessionFactory=null;
    }
    
    
    
}