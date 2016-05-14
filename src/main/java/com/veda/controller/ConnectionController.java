package com.veda.controller;

import com.veda.Connection;
import com.veda.util.ConnectionPool;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Properties;
import java.util.UUID;

@Controller
public class ConnectionController {
    @RequestMapping(value = "/connection", method = RequestMethod.GET)
    @ResponseBody
    public UUID connection(@RequestParam(defaultValue = "New connection") String name,
                           @RequestParam Map<String, String> params) {

        try {
            Properties properties = new Properties();
            params.remove("name");
            properties.putAll(params);

            Configuration configuration = new Configuration().configure();
            configuration.setProperties(properties);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            Connection connection = new Connection(name, properties, sessionFactory);
            ConnectionPool.getInstance().add(connection);
            return connection.getUuid();
        } catch (Throwable ex) {
            return null;
        }
    }

}
