package com.example.demo.aspect;

import com.example.demo.common.enums.MeetingBizStatus;
import com.example.demo.common.exception.MeetingBizException;
import com.example.demo.vo.ResponseVo;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import java.util.Arrays;

/**
 * @Description: 为接口规范,方便开发 
 * 统一接口返回格式：
 * @author czj
 * @date: 下午3:57:35
 */
@Component
@Aspect
@Order(10)
public class DataPackDecorator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataPackDecorator.class);
    
    /**
     * @Description: 封装返回ResponseVo,DataPack和扫描的包路径其一
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.example.demo.annotation.DataPack) || within(com.example.demo.controller.*) ")
    //@Around("@annotation(com.csair.bs.locationresource.annotation.DataPack)")
	public Object decorate(ProceedingJoinPoint pjp) throws Throwable {
		// 定义返回结果封装类
    	ResponseVo<Object> er = new ResponseVo<>();
    	String code = MeetingBizStatus.SUCCESS.value();
    	String msg = MeetingBizStatus.SUCCESS.getReasonPhrase();
    	Exception ex = null;
		try {
			Object o = pjp.proceed();
			er.setData(o);
		}catch ( MeetingBizException e) {
			ex =e;
			code = e.getCode();
			msg = StringUtils.isEmpty(e.getMessage())?e.getCodeMsg():e.getMessage();
		}catch ( IllegalArgumentException e) {
			ex =e;
			code = MeetingBizStatus.PARAMETER_ERROR.value();
			msg = e.getMessage();
		}catch ( BindException e) {
			ex =e;
			code = MeetingBizStatus.PARAMETER_ERROR.value();
			FieldError fieldError = e.getBindingResult().getFieldError();
	        msg = fieldError.getDefaultMessage();
		}catch (Exception e) {
			ex =e;
            er.setCode(MeetingBizStatus.ERROR.value());
			er.setMsg(e.getMessage());
		}
		if(ex!=null) {
			LOGGER.error("API 失败!", ex);
			LOGGER.error("类:{}" , pjp.getSignature().getDeclaringTypeName());
	        LOGGER.error("方法:{}" , pjp.getSignature().getName());
	        LOGGER.error("参数:{}" , Arrays.toString(pjp.getArgs()));
		}
		er.setCode(code);
		er.setMsg(msg);
		return er;
	}
    
}
