package com.boyz.interceptor;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerInterceptor {
	private static final Logger logger = Logger
			.getLogger(ControllerInterceptor.class);

	/*
	 * 如果拦截器拦截Listener类，则上传会非常慢哦。
	 */
	@Pointcut("execution(* com.boyz.controller.*Controller.*(..))")
	public void everything() {
	}

	@Around("everything()")
	private Object everythingAround(ProceedingJoinPoint point) throws Throwable {
		logger.info("---\t---\teverything around begin\t---\t---");
		String paramsStr = "\n";
		Object[] args = point.getArgs();
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				paramsStr += args[i].getClass() + " " + args[i] + "\n";
			}
		}
		logger.info(paramsStr);
		Object r = point.proceed(args);
		logger.info("return=" + r);
		logger.info("---\t---\teverything around end with target "
				+ point.getTarget() + "\t---\t---");
		return r;
	}

	@Before("everything()")
	private void everythingBefore(JoinPoint point) {
		logger.info("---\t---\teverything before begin\t---\t---");
		logger.info("---\t---\t" + point.getSignature().getDeclaringTypeName()
				+ "." + point.getSignature().getName() + "\t---\t---");
	}

	@After("everything()")
	private void everythingAfter(JoinPoint point) {
		logger.info("---\t---\teverything after begin\t---\t---");
	}
}
