package com.bfxy.rabbit.producer.task;

import com.bfxy.rabbit.producer.broker.RabbitBroker;
import com.bfxy.rabbit.producer.constant.BrokerMessageStatus;
import com.bfxy.rabbit.producer.entity.BrokerMessage;
import com.bfxy.rabbit.producer.service.IMessageStoreService;
import com.bfxy.rabbit.task.annotation.ElasticJobConfig;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bfxy.rabbit.producer.constant.BrokerMessageStatus.SENDING;

/**
 * 定时任务重复投递错误消息
 * @Author Mengdexin
 * @date 2022 -05 -02 -20:34
 */
@ElasticJobConfig(
        name = "com.bfxy.rabbit.producer.task.RetryMessage",
        description = "消息重复投递",
        cron = "0/5 * * * * ?",
        shardingTotalCount = 1,
        streamingProcess = false,
        overwrite = true,
        eventTraceRdbDataSource = "rabbitProducerDataSource"
)
@Component
@Slf4j
public class RetryMessage implements DataflowJob<BrokerMessage> {

    @Autowired
    private IMessageStoreService messageStoreService;
    @Autowired
    private RabbitBroker rabbitBroker;

    private static final Integer MAX_TRY_COUNT = 3;

    @Override
    public List<BrokerMessage> fetchData(ShardingContext shardingContext) {
        List<BrokerMessage> brokerMessages = messageStoreService.queryFailMessage(SENDING.getCode());
        log.info("--------------@@@@@@@@@@ 抓取数据集合...--------------数量{}", brokerMessages.size());
        return brokerMessages;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<BrokerMessage> data) {
        data.forEach(v -> {
            if (v.getTryCount() > MAX_TRY_COUNT) {
                messageStoreService.updateFileStatus(v.getMessageId(), BrokerMessageStatus.SEND_FAIL.getCode());
                log.info("更新失败数据->{}", v.getMessageId());
            } else {
                messageStoreService.updateTryCount(v.getMessageId());
                rabbitBroker.reliantSend(v.getMessage());
                log.info("重新发送数据-{}", v.getMessageId());
            }
        });
    }

}
