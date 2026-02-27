package com.deepblue.test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("msg_sms_send_record")
public class SmsSendRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录ID  主键自增
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 产品线
     */
    private Integer siteId;

    /**
     * 关联短信类型表
     */
    private Long typeId;

    /**
     * 产品类型 编码
     */
    private String typeCode;

    /**
     * 供应商code
     */
    private String providerCode;

    /**
     * 实际发送供应商code
     */
    private String actualProvider;

    /**
     * 所属任务id
     */
    private String taskId;

    /**
     * 所属批次id
     */
    private String batchId;

    /**
     * 是否实时发送
     1-实时，2-定时
     */
    private Integer isRealTime;

    /**
     * 用户loginName
     */
    private String loginName;

    /**
     * 用户customerId
     */
    private String customerId;

    /**
     * 将要展示的手机号。
     * 使用对应加密字段
     */
    @TableField(exist = false)
    private String phone;

    /**
     * 加密后的手机号，可逆
     */
    @JsonIgnore
    private String phoneEncry;

    /**
     * 加密手机号，方便进行查询匹配，不能返回到前端
     */
    @JsonIgnore
    private String phoneMd5;

    /**
     * 短信内容
     */
    private String content;

    /**
     * 短信条数
     */
    private Integer amount;

    /**
     * 消息状态， 1. 等待中  2. 推送中  3. 推送完成  4. 失败 5.中止
     */
    private Integer state;

    /**
     * 计划定时发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date explanTime;

    /**
     * 实际发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    /**
     * 消息id ，正常返回值/ 回调修改
     */
    private String msgId;

    /**
     * 任务备注
     */
    private String remark;

    /**
     * 重试次数
     */
    private Integer sendRetry;

    /**
     * 回调状态，运营商返回
     */
    private Integer resultStatus;

    /**
     * 额外参数
     */
    private String extraParam;

    /**
     * 发送短信来源
     * 1 office 2 api  3 kafka
     */
    private String fromSource;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 数据更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     *  数据来源.  只在数据流转中存在
     */
    @TableField(exist = false)
    private String source;

    /**
     * 运营商代码
     */
    private String isp = "0";
}