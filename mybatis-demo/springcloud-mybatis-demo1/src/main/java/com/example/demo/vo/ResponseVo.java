package com.example.demo.vo;


import com.example.demo.common.enums.MeetingBizStatus;

import java.io.Serializable;

/**
 * @Description: 操作返回结果泛型
 * @author czj
 * @date: 下午3:35:33
 */
public class ResponseVo<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1134626523416648612L;

	private String code;//系统数据编号

    private String msg;//提示信息

    private T data;//具体开发返回数据

    public ResponseVo(){}

    public ResponseVo(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResponseVo(String code, String msg, T t){
        this.code = code;
        this.msg = msg;
        this.data = t;
    }

    public ResponseVo(T t){
       this.code = MeetingBizStatus.SUCCESS.value();
       this.msg = MeetingBizStatus.SUCCESS.getReasonPhrase();
       this.data = t;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
}
