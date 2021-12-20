
package com.stw.breweryclient.web.config;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.IOReactorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *          ScienceTechWorks
 * @author Ramon.Talavera@gmail.com 
 */
//@Component
public class NIORestTemplateCustomizer implements RestTemplateCustomizer {

    private final Integer connectTimeout; //3000
    private final Integer ioThreadCount; //4
    private final Integer soTimeout; //3000
    private final Integer defaultMaxPerRoute; //100
    private final Integer maxTotal; //1000

    public NIORestTemplateCustomizer(
            @Value("${stw.apache.nonblocking.nio.httpclient.connectTimeout}") Integer connectTimeout, //3000 
            @Value("${stw.apache.nonblocking.nio.httpclient.ioThreadCount}") Integer ioThreadCount, //4
            @Value("${stw.apache.nonblocking.nio.httpclient.soTimeout}") Integer soTimeout, //3000
            @Value("${stw.apache.nonblocking.nio.httpclient.defaultMaxPerRoute}") Integer defaultMaxPerRoute, //100 
            @Value("${stw.apache.nonblocking.nio.httpclient.maxTotal}") Integer maxTotal //1000
    ) {
        this.connectTimeout = connectTimeout;
        this.ioThreadCount = ioThreadCount;
        this.soTimeout = soTimeout;
        this.defaultMaxPerRoute = defaultMaxPerRoute;
        this.maxTotal = maxTotal;
    }
    
    
    public ClientHttpRequestFactory clientHttpRequestFactory() throws IOReactorException {
        final DefaultConnectingIOReactor ioreactor = new DefaultConnectingIOReactor(IOReactorConfig.custom().
                setConnectTimeout(connectTimeout).
                setIoThreadCount(ioThreadCount).
                setSoTimeout(soTimeout).
                build());

        final PoolingNHttpClientConnectionManager connectionManager = new PoolingNHttpClientConnectionManager(ioreactor);
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        connectionManager.setMaxTotal(maxTotal);

        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom()
                .setConnectionManager(connectionManager)
                .build();

        return new HttpComponentsAsyncClientHttpRequestFactory(httpAsyncClient);

    }

    @Override
    public void customize(RestTemplate restTemplate) {
        try {
            restTemplate.setRequestFactory(clientHttpRequestFactory());
        } catch (IOReactorException e) {
            e.printStackTrace();
        }
    }
}