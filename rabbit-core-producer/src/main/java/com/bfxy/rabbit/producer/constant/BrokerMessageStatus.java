package com.bfxy.rabbit.producer.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 	$BrokerMessageStatus
 * 	消息的发送状态
 * @author Alienware
 *
 */
@AllArgsConstructor
@Getter
public enum BrokerMessageStatus {
	/**
	 * 消息的状态
	 */
	SENDING("0", "待确认"),
	SEND_OK("1", "成功"),
	SEND_FAIL("2", "失败"),
	SEND_FAIL_A_MOMENT("3", "失败多次");
	
	private String code;
	private String name;

}
