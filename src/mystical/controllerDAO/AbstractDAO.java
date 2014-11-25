/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystical.controllerDAO;

import java.util.List;
import java.util.Map;
import mystical.util.Conexao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

/**
 *
 * @author Pedro
 */
public abstract class AbstractDAO {

    protected Session session;
    protected Transaction tx;
    Boolean retorno = false;
    protected SQLQuery queryGenerica;

    protected boolean save(Object obj) {
        session = Conexao.getSessionFactory().openSession();
        tx = session.beginTransaction();
        try {
            session.save(obj);
            tx.commit();
            retorno = true;
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            session.close();
        }
        return retorno;
    }

    protected boolean update(Object obj) {
        session = Conexao.getSessionFactory().openSession();
        tx = session.beginTransaction();
        try {
            session.update(obj);
            tx.commit();
            retorno = true;
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            session.close();
        }
        return retorno;
    }

    protected boolean delete(Object obj) {
        session = Conexao.getSessionFactory().openSession();
        tx = session.beginTransaction();
        try {
            session.delete(obj);
            tx.commit();
            retorno = true;
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            session.close();
        }
        return retorno;
    }

    protected Object find(Class clazz, Long id) {
        session = Conexao.getSessionFactory().openSession();
        tx = session.beginTransaction();
        Object obj = null;
        try {
            obj = session.load(clazz, id);
            tx.commit();
            retorno = true;
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            session.close();
        }
        return obj;
    }

    protected List findAll(Class clazz) {
        session = Conexao.getSessionFactory().openSession();
        tx = session.beginTransaction();
        List objects = null;
        try {
            Query query = session.createQuery("from " + clazz.getName());
            System.out.print(query.getQueryString());
            objects = query.list();
            tx.commit();
            retorno = true;
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            session.close();
        }
        return objects;
    }

    /*--------------------------------
     protected List findAllById(Class clazz, int id){
     session = Conexao.getSessionFactory().openSession();
     tx = session.beginTransaction();

     List objects = null;
     try {

     //Query sqlQuery = EntityManager.createNativeQuery("Select * from Books where author = ?", Partida.class);
     //objects = sqlQuery.setParameter(1, "Charles Dickens").getResultList();
     System.out.println("estamos ak antes");
     //System.out.println(clazz.getField("rodada"));
     //String hql = "FROM "+clazz.getName()+" as WHERE rodada_id_rodada = 6";
     Query query = session.createSQLQuery("select * from PARTIDA where PARTIDA.rodada_id_rodada = 6").addEntity(Partida.class);
            
     System.out.println("estamos ak no meio");
     objects = query.list();
           
           
     tx.commit();

     System.out.println("estamos ak depois");
     retorno = true;
     } catch (HibernateException e) {
     tx.rollback();
     } finally {

     session.close();

     }
     return objects;
     }
        
     */
    protected List findAllById(int id, String q) {
        session = Conexao.getSessionFactory().openSession();
        tx = session.beginTransaction();
        List objects = null;
        try {
            Query query = session.createQuery(q);
            objects = query.list();
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
        } finally {

            session.close();

        }
        return objects;
    }

    protected List findAllPartidasByCampeonato(String sql) {
        session = Conexao.getSessionFactory().openSession();
        tx = session.beginTransaction();
        List objects = null;
        try {
            System.out.println("estamos na abstract antes da query");

            SQLQuery query = session.createSQLQuery(sql);
            objects = query.list();
            System.out.println("estamos na abstract depois da query");
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
        } finally {

            session.close();

        }
        return objects;
    }

    protected List<Map<String, Object>> consultaGenerica(String consulta) {

        session = Conexao.getSessionFactory().openSession();
        tx = session.beginTransaction();
        List <Map<String, Object>> objects = null;
        try {
            System.out.println("estamos na abstract antes da query");

            SQLQuery query = session.createSQLQuery(consulta);
            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            objects =(List<Map<String, Object>>) query.list();
            System.out.println("estamos na abstract depois da query");
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
        } finally {

            session.close();

        }
        return objects;
    }
}
