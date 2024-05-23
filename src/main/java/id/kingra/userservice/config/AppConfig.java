package id.kingra.userservice.config;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@EnableFeignClients("id.kingra.userservice.feignclient")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class AppConfig {

}
