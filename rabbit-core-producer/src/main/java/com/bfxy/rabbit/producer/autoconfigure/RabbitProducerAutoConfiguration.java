package com.bfxy.rabbit.producer.autoconfigure;

import com.bfxy.rabbit.task.annotation.EnableElasticJob;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Mengdexin
 * @date 2022 -04 -18 -22:13
 */
@EnableElasticJob
@Configuration
@ComponentScan({"com.bfxy.rabbit.producer.*"})
public class RabbitProducerAutoConfiguration {
}
