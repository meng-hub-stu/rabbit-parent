package com.bfxy.rabbit.producer.mapper;

import com.bfxy.rabbit.producer.entity.BrokerMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @Author Mengdexin
 * @date 2022 -04 -26 -22:13
 */
@Mapper
public interface BrokerMessageMapper {
    /**
     * 查询投送消息内容
     * @param messageId 消息的id
     * @return 返回结果
     */
    BrokerMessage selectByPrimaryKey(@Param("messageId") String messageId);

    /**
     * 插入消息内容
     * @param brokerMessage 消息内容
     * @return 返回结果
     */
    int insert(@Param("record") BrokerMessage brokerMessage);

    /**
     * 修改状态
     * @param messageId 消息id
     * @param code 成功码
     * @param date 更新时间
     * @return 返回结果
     */
    int updateMessageStatus(@Param("messageId") String messageId, @Param("code") String code, @Param("date") Date date);

}
