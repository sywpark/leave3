/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idaos;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public interface IGeneralDAO<T> {

    public T getById(Object id);

    public List<T> getData(Object keyword);

    public boolean saveOrDelete(T object, boolean isDelete);
    
    public boolean register(T object);
}
