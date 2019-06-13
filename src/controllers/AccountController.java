/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.GeneralDAO;
import icontrollers.IAccountController;
import idaos.IGeneralDAO;
import java.util.List;
import models.Account;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import tools.BCrypt;
import tools.HibernateUtil;

/**
 *
 * @author HP
 */
public class AccountController implements IAccountController {

    private IGeneralDAO<Account> igdao;
    private Session session;   
    private Transaction transaction;
    SessionFactory factory = HibernateUtil.getSessionFactory();

    public AccountController(SessionFactory factory) {
        igdao = new GeneralDAO(factory, Account.class);
    }



    @Override
    public String register(String id, String username, String password) {
        String result = "";
        String pass = BCrypt.hashpw(password, BCrypt.gensalt());
        Account account = new Account(Long.parseLong(id), username, pass);
        if (igdao.saveOrDelete(account, false)) {
            result = "Success";
        } else {
            result = "Failed";
        }
        return result;
    }

    @Override
    public String login(String username, String password) {
        String result = "";
        
        session = this.factory.openSession();
        transaction = session.beginTransaction();
        
        Query query = session.createQuery("SELECT password FROM Account WHERE username ='"+username+"'");
        String hashed = (String) query.uniqueResult();
        
        boolean cekpassword = BCrypt.checkpw(password, hashed);
        if (!Validasi(username, false) || !Validasi(username, true) ) {
            if (cekpassword) {
                result = "Login Successfull";
            } else {
                result = "Login Unsuccessfull, Password Wrong";
            }
        } else {
            result = "Login Unsuccessfull, Username Wrong";
        }
        return result;
    }

    @Override
    public boolean Validasi(Object keyword, boolean isId) {
        boolean result = false;
        session = this.factory.openSession();
        transaction = session.beginTransaction();
        String hql = "SELECT COUNT(*) FROM Account WHERE ";
        if (isId) {
            hql += "id = " + keyword;
        } else {
            hql += "username = '" + keyword + "'";
        }
        try {
            Query query = session.createQuery(hql);
            Long count = (Long) query.uniqueResult();
//            System.out.println(count);
            if (count != 1) {
                result = true;
            }
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

}
