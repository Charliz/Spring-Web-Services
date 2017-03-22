package com.ncproject.controller;

/**
 * Created by secret on 21.03.2017.
 */
import javax.naming.*;
import java.util.Hashtable;

public class RemoteEJBClient {

    /**
     *
     * @param tt - экземпляр класса требуемого бина
     * @param <T> - Т класс требуемого бина
     * @return - EJB из webstore
     * @throws NamingException
     */

    public static <T> T getBeanInterface(T tt) throws NamingException {

        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);

        final String appName = "";
        final String moduleName = "webstore";
        final String distinctName = "";
        final String beanName = tt.getClass().getSimpleName();

        final String viewClassName = tt.getClass().getInterfaces()[0].getName() + "?stateful";
        System.out.println("Looking EJB via JNDI ");
        String s = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName;
        System.out.println(s);

        T ejbHelloWorld = (T) context.lookup(s);

        return ejbHelloWorld;

    }
}