package com.bfxy.rabbit.producer.entity;

import java.io.Serializable;
import java.util.Date;

import com.bfxy.rabbit.api.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 	$BrokerMessage 消息记录表实体映射
 * @author Alienware
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrokerMessage implements Serializable {
	
	private static final long serialVersionUID = 7447792462810110841L;

	private String messageId;

    private Message message;

    private Integer tryCount = 0;

    private String status;

    private Date nextRetry;

    private Date createTime;

    private Date updateTime;

}