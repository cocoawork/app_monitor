package top.cocoawork.monitor.fetcher.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import top.cocoawork.monitor.service.api.dto.CountryDto;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, CountryDto> redisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<String, CountryDto> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        return template;
    }

}
