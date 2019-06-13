/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import idaos.IGeneralDAO;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class GeneralDAO<T> implements IGeneralDAO<T> {

    private SessionFactory factory;
    private Session session;
    private Transaction transaction;
    T t;

    public GeneralDAO(SessionFactory factory, Class<T> type) {
        this.factory = factory;
        try {
            this.t = type.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<T> getData(Object keyword) {
        List<T> objectList = new ArrayList<>();
        session = this.factory.openSession();
        transaction = session.beginTransaction();
        String hql = "FROM " + t.getClass().getSimpleName();
        if (!keyword.equals("")) {
            hql += " WHERE ";
            for (Field field : t.getClass().getDeclaredFields()) {
                if (!field.getName().contains("UID") && !field.getName().contains("List")) {
                    hql += field.getName() + " LIKE '%" + keyword + "%' OR ";
                }
            }
            hql = hql.substring(0, hql.lastIndexOf("OR"));
        }
        try {
            Query query = session.createQuery(hql + " ORDER BY 1");
//            query.setParameter("table", table.getClass().getSimpleName());
//            if (!keyword.equals("")) {
//                query.setParameter("keyword", "%" + keyword + "%");
//            }
            objectList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return objectList;
    }

    public boolean saveOrDelete(T object, boolean isDelete) {
        boolean result = false;
        session = this.factory.openSession();
        transaction = session.beginTransaction();
        try {
            if (isDelete) {
                session.delete(object);
            } else {
                session.saveOrUpdate(object);
            }
            transaction.commit();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return result;
    }

    public T getById(Object id) {
        T location = null;
        session = this.factory.openSession();
        transaction = session.beginTransaction();
        try {
            String hql = "FROM " + t.getClass().getSimpleName() + " WHERE id = :a ORDER BY 1";
            Query query = session.createQuery(hql);
            query.setParameter("a", id);
            location = (T) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return location;
    }

    
}
