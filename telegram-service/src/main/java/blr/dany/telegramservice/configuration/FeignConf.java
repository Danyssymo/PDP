package blr.dany.telegramservice.configuration;

import feign.Client;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConf {

    @Bean
    public Client feignClient(){
        return new feign.httpclient.ApacheHttpClient(HttpClients.createDefault());
    }

}



