package com.cache.springbootcache.druidDateSourceConfig;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class rabbitMqConfig {
    @Bean
    public MessageConverter messageConverter(){            //此配置将消息转换为json格式,发到转换器绑定的队列
        return new Jackson2JsonMessageConverter();
    }  //将发送到消息队列中消息转化为json格式，而不是JDK默认的序列化格式
}
