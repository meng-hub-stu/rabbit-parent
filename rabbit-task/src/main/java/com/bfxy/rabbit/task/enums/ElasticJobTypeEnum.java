package com.bfxy.rabbit.task.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ElasticJobTypeEnum {
	/**
	 * 任务的类型
	 */
	SIMPLE("SimpleJob", "简单类型job"),
	DATAFLOW("DataflowJob", "流式类型job"),
	SCRIPT("ScriptJob", "脚本类型job");
	
	private String type;
	
	private String desc;

}
