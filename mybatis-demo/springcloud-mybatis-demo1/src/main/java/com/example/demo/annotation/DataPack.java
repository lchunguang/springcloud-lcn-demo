package com.example.demo.annotation;

import java.lang.annotation.*;

/**
 * @Description: 与切面DataPackDecorator一起使用,标准化接口数据传输
 * @author czj
 * @date: 下午3:56:05
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataPack {

}
