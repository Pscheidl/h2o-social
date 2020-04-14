package ai.h2o.social.stackexchange;

import org.jboss.resteasy.plugins.interceptors.AcceptEncodingGZIPFilter;
import org.jboss.resteasy.plugins.interceptors.GZIPDecodingInterceptor;
import org.jboss.resteasy.plugins.interceptors.GZIPEncodingInterceptor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@ApplicationScoped
public class StackExchangeWebTargetProducer {
    public static final String STACK_EXCHANGE_URI_BASE = "https://api.stackexchange.com/2.2/";
    private final Client client;

    public StackExchangeWebTargetProducer() {
        client = ClientBuilder.newClient()
                .register(AcceptEncodingGZIPFilter.class)
                .register(GZIPDecodingInterceptor.class)
                .register(GZIPEncodingInterceptor.class);
    }

    @Produces
    @StackExchange
    @Dependent
    public WebTarget produceStackExchangeWebTarget() {
        return client.target(STACK_EXCHANGE_URI_BASE);
    }
}
