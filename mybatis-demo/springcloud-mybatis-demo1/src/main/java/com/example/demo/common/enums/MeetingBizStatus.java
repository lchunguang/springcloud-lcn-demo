package com.example.demo.common.enums;

/**
 * @Description: 业务状态
 * @author czj
 * @date: 下午3:35:15
 */
public enum MeetingBizStatus {
    SUCCESS("0000","请求成功"),
    ERROR("0001","服务异常"),//未知异常
    PARAMETER_ERROR("0002","参数异常"),
    UNAUTHORIZED("0003","您没有操作权限"),
    LIMIT("0004","业务约束"),
    NOMAPPING("0005","接口不存在");
	
    private final String value;

    private final String reasonPhrase;

    private MeetingBizStatus(String value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public String value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

}
