package com.timesinternet.suggestor.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeProfiler {

	private static final Logger logger = Logger.getLogger(TimeProfiler.class);

	@Pointcut("execution(* com.timesinternet.suggestor.controller.SuggestionsController.*(..))")
	/*@Pointcut("execution(* com.timesinternet.suggestor.*.(..))")*/
	public void profileExecutionTime() {
	}
	
	@Around("profileExecutionTime()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		logger.error("Going to call the method.");
		Object output = pjp.proceed();
		logger.error("Method execution completed.");
		long elapsedTime = System.currentTimeMillis() - start;
		logger.error("Method execution time: " + elapsedTime + " milliseconds.");
		return output;
	}

}
