package com.ck;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@EnableEurekaServer
//@EnableDiscoveryClient
public class Application {

	@Autowired
	private Environment env;
	
	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
//		Properties systemProps = System.getProperties();
//	    systemProps.put("javax.net.ssl.trustStore","C:\\Chetan\\chetank\\cacerts");
//	    systemProps.put("javax.net.ssl.trustStorePassword","changeit");
//	    systemProps.put("server.ssl.trust-store","C:\\Chetan\\chetank\\cacerts");
//	    systemProps.put("server.ssl.trust-store-password","changeit");
//	    systemProps.put("javax.net.ssl.keyStore","C:\\Chetan\\chetank\\cacerts");
//        System.setProperty("javax.net.ssl.trustStore", env.getProperty("server.ssl.trust-store")); 
//        System.setProperty("javax.net.ssl.trustStorePassword",env.getProperty("server.ssl.trust-store-password"));
//	    System.setProperties(systemProps);
	}
	
	@Bean(name="restTemplateWithSSL")
	public RestTemplate getRestTemplate() throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, KeyManagementException {
		
		String trustStorePath = "coinbase_truststore";
		Resource trustStoreResource = new ClassPathResource(trustStorePath);
		
//		File trustStore = new File("C:\\Users\\ckhadye\\eclipse-workspace\\currencypricetracker\\src\\main\\resources\\coinbase_truststore");
//		File trustStore = new File("C:\\Chetan\\chetank\\coinbase.truststore");
		File trustStore = trustStoreResource.getFile();
		
		SSLContextBuilder sslContextBuilder = SSLContexts.custom();
		SSLContext sslContext = sslContextBuilder.loadTrustMaterial(trustStore, "changeit".toCharArray()).build();
		
		
		CredentialsProvider credProvider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials
		 = new UsernamePasswordCredentials("user1", "user1Pass");
		credProvider.setCredentials(AuthScope.ANY, credentials);
		
		 final SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(
		            sslContext,
		            new String[] { "TLSv1.2" },
		            null,
		            SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		 final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
		            .register("http", PlainConnectionSocketFactory.getSocketFactory())
		            .register("https", sslSocketFactory)
		            .build();
		        final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
		        CloseableHttpClient closeableHttpClient = HttpClients.custom()
		            .setSSLSocketFactory(sslSocketFactory)
		            .setConnectionManager(connectionManager)
//		            Use it if you have proxy to be authenticated
//		            .setDefaultCredentialsProvider(credProvider)
		            .build();
		        
		 
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory(closeableHttpClient));
	}
	
	@Bean(name="restTemplateWithoutSSL")
	public RestTemplate getRestTemplateWithoutSSL() throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, KeyManagementException {
		        CloseableHttpClient httpClient
		        = HttpClients.custom()
		          .setSSLHostnameVerifier(new NoopHostnameVerifier())
		          .build();
		      HttpComponentsClientHttpRequestFactory requestFactory 
		        = new HttpComponentsClientHttpRequestFactory();
		      requestFactory.setHttpClient(httpClient);
		      
		      return new RestTemplate(requestFactory);
	}
}
