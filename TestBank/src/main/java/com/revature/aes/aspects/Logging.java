/****************************************************************
 * Project Name: Associate Evaluation System - Test Bank
 * 
 * Description: A simple rest application that persists test
 * 		information into a database. Use to evaluate associates
 * 		performance both during and before employment with Revature 
 * 		LLC.
 * 
 * Authors: Matthew Beauregard, Conner Anderson, Travis Deshotels,
 * 		Edward Crader, Jon-Erik Williams 
 ****************************************************************/
package com.revature.aes.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Logging
{
	private static Logger log = Logger.getRootLogger();
	@Pointcut("execution(* com.revature.aes..*(..))")
	public void anyMethod(){
		// This method is a hook to be used on any method (usefull for logging.)
	}
	
	@Around("anyMethod()")
	public Object log(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		String methodClass = signature.getDeclaringTypeName().toString();
		String method = signature.getName().toString();
<<<<<<< HEAD:CoreInterface/src/main/java/com/revature/aes/logging/Logging.java
		Object result = null;
		
		log.info(dashes);
=======
>>>>>>> bank-dev:TestBank/src/main/java/com/revature/aes/aspects/Logging.java
		
		log.info(methodClass + " ==> " + method);
		Object[] args = pjp.getArgs();
		for(int i = 0; i < args.length; i++){
			log.info("Argumetnt #"+i+": "+args[i]);
		}
		
<<<<<<< HEAD:CoreInterface/src/main/java/com/revature/aes/logging/Logging.java
		log.info("Executing...");
		
		try{
			result = pjp.proceed();
		} catch(Exception e){
			log.error("\t" + e.getClass() + " " + e.getMessage());
			log.info(e);
			
			for(StackTraceElement st : e.getStackTrace()){
			     log.error("\t\t" + st.getMethodName());
			}
		} catch(Throwable e){
			log.error(e);
		}
		
		log.info(methodClass + " ==> " + method + " - Exit\nReturning: " + result);

		log.info(dashes);
		return result;
=======
		log.trace("Executing...");
	
		return pjp.proceed();
>>>>>>> bank-dev:TestBank/src/main/java/com/revature/aes/aspects/Logging.java
	}
	
	@AfterThrowing(pointcut="anyMethod()", throwing="e")
	public void stackTraceLogging(Exception e){
		for(StackTraceElement st : e.getStackTrace()){
			log.error(st.getMethodName() + " at line " + st.getLineNumber());
		}
	}
}
