package com.veda.controller;

import com.veda.util.ConnectionPool;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Controller
public class QueryController {
    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    @ResponseBody
    public List executeQuery(@RequestParam UUID connection,
                             @RequestParam String sql) {

        SessionFactory factory = ConnectionPool.getInstance().get(connection).getSessionFactory();
        Session session = factory.openSession();

        Transaction transaction = null;
        List result = Collections.emptyList();

        try {
            transaction = session.beginTransaction();

            result = session.createSQLQuery(sql).list();

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.flush();
        }

        return result;
    }
}
