package com.tob.part1;

import com.tob.part1.connectionMaker.ConnectionMaker;
import com.tob.part1.connectionMaker.NConnectionMaker;
import com.tob.part1.dao.DaoFactory;
import com.tob.part1.dao.DaoFactorySpring;
import com.tob.part1.dao.GoodDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserTest {
    public static void main(String args[]) {
        ConnectionMaker connectionMaker = new NConnectionMaker();

        //use constructor
        GoodDAO goodDAO = new GoodDAO(connectionMaker);

        // use object factory
        GoodDAO goodDAO1 = new DaoFactory().goodDAO();

        // use obeject factory in spring
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactorySpring.class);
        GoodDAO goodDAO2 = ac.getBean("goodDAO", GoodDAO.class); // getBean() : Dependency lookup

        // single tone registry
        GoodDAO goodDAO4 = new DaoFactory().goodDAO();
        System.out.println(goodDAO1.equals(goodDAO4)); //false

        GoodDAO goodDAO3 = ac.getBean("goodDAO", GoodDAO.class);
        System.out.println(goodDAO2.equals(goodDAO3)); //true

        // config bean by .xml
//        ApplicationContext acXML = new GenericXmlApplicationContext("com/tob/part1/applicationContext.xml");
        ApplicationContext acXML = new GenericXmlApplicationContext("applicationContext.xml");
        GoodDAO goodDAOXML = acXML.getBean("goodDAO", GoodDAO.class); //
        System.out.println(goodDAOXML.toString());


    }
}
