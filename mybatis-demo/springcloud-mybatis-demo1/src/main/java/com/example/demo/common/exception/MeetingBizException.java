package com.example.demo.common.exception;


import com.example.demo.common.enums.MeetingBizStatus;

/**
 * @Description: 系统业务异常约束
 * @author czj
 * @date: 下午4:04:27
 */
public class MeetingBizException extends RuntimeException{

    /**
	 * 序列号
	 */
	private static final long serialVersionUID = -8679246727786373921L;

	private String code = MeetingBizStatus.LIMIT.value();

    private String codeMsg = MeetingBizStatus.LIMIT.getReasonPhrase();

    public String getCode() {
        return code;
    }

    public String getCodeMsg() {
        return codeMsg;
    }

    public MeetingBizException(String message) {
        super(message);
    }

}
