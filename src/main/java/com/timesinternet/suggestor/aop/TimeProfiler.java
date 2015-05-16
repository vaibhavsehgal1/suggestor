package com.timesinternet.suggestor.aop;

import java.util.concurrent.TimeUnit;

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

	@Pointcut("within(@com.timesinternet.suggestor.aop.LogAction *)")
	public void beanAnnotatedWithLogAction() {
	}

	@Pointcut("execution(public * *(..))")
	public void publicMethod() {
	}

	@Pointcut("publicMethod() && beanAnnotatedWithLogAction()")
	public void publicMethodInsideAClassAnnotatedWithLogAction() {
	}

	@Around("publicMethodInsideAClassAnnotatedWithLogAction()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.nanoTime();

		Object output = pjp.proceed();

		long elapsedTimeInNanoSeconds = System.nanoTime() - start;
		long elapsedTimeInMilliseconds = TimeUnit.MILLISECONDS.convert(
				elapsedTimeInNanoSeconds, TimeUnit.NANOSECONDS);

		logger.debug("Method execution time of : " + pjp.getSignature()
				+ +elapsedTimeInMilliseconds + " milliseconds.");

		return output;
	}

}
