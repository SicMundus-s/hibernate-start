package org.example;


import org.example.entity.Company;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;
import org.hibernate.proxy.ProxyConfiguration;
import org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor;

/**
 * Наглядное представление как выглядит proxy объект в hibernate
 */
public class CompanyProxy extends Company
        implements HibernateProxy, ProxyConfiguration {

    private ByteBuddyInterceptor byteBuddyInterceptor;

    @Override
    public Object writeReplace() {
        return null;
    }

    @Override
    public LazyInitializer getHibernateLazyInitializer() {
        return byteBuddyInterceptor;
    }

    @Override
    public void $$_hibernate_set_interceptor(Interceptor interceptor) {

    }
}
