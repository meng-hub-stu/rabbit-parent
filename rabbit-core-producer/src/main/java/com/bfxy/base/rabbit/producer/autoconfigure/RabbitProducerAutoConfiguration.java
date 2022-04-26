package com.bfxy.base.rabbit.producer.autoconfigure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Mengdexin
 * @date 2022 -04 -18 -22:13
 */
@Configuration
@ComponentScan({"com.bfxy.base.rabbit.producer.*"})
public class RabbitProducerAutoConfiguration {
}
