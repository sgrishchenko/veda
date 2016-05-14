package com.veda.controller;

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

@Controller
public class MainController {
    @RequestMapping(value = "/test-connection", method = RequestMethod.GET)
    @ResponseBody
    public boolean test(@RequestParam Map<String, String> params) {
        try {
            Properties properties = new Properties();
            properties.putAll(params);

            Configuration configuration = new Configuration().configure();
            configuration.setProperties(properties);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            configuration.buildSessionFactory(serviceRegistry).close();
            return true;
        } catch (Throwable ex) {
            return false;
        }
    }

}
