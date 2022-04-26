package com.bfxy.rabbit.common.serializer;

public interface SerializerFactory {
	/**
	 * 创建序列化对象
	 * @return
	 */
	Serializer create();

}
