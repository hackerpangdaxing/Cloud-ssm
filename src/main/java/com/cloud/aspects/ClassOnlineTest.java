package com.cloud.aspects;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloud.model.ExaminationPaper;
import com.cloud.testmybatis.JacksonUtil;

@Aspect                //表示当前POJO类为切面
@Component("aspectDemo")
public class ClassOnlineTest {
	
	private static Logger logger = Logger.getLogger(ClassOnlineTest.class);
	
	@Before("execution(* *..service.*.getExcamById(..))")		 
	public void myBefore(JoinPoint jp){
		       // 拦截的实体类
				Object target = jp.getTarget();
				// 拦截的方法名称
				String methodName = jp.getSignature().getName();
				// 拦截的方法参数
				Object[] args = jp.getArgs();
				for(int i=0; i<args.length; i++){
					if (null != args[i]) {
						logger.info("当前访问的试卷ID为："+args[i]);
					}
					else {
						logger.info("抱歉，当前访问的试卷不存在，请重试！");
					}
				}
	}
	
	
	//定义切入点
	@Pointcut("execution(* *..IOnlineTestService.getExcamById(..))")	
	private  void doExcamPointcut(){
	}
	
	//最终通知方法    织入方法
	@After("doExcamPointcut()")	
	public  void myAfter(){
		System.out.println("校验答题是否正确");
	}
	
	//定义异常通知方法
	@AfterThrowing("execution(* *..IOnlineTestService.getExcamById(..))")	
	public  void myAfterThrowing(){
		System.out.println("执行异常通知方法myAfterThrowing()");
	}
	
	@AfterThrowing(value="execution(* *..IOnlineTestService.getExcamById(..))",throwing="ex")	
	public  void myAfterThrowing(Exception ex){
		System.out.println("执行异常通知方法myAfterThrowing()  ex"+ex.getMessage());
	}
	
	//定义后置通知方法
	@AfterReturning(value="execution(* *..service.*.getExcamById(..))",returning="result")		 
	public void AfterReturning(Object result){
		/*ExaminationPaper  e = (ExaminationPaper) result;
		String resultToJson = JacksonUtil.toJSon(e);  */

		System.out.println("将结果记录下来 =  "+result);
		
	}



}