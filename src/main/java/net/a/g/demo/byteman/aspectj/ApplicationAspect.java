package net.a.g.demo.byteman.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ApplicationAspect {

	public ApplicationAspect() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.err.println("Shutdown Hook is running !");
			}
		});
	}

	@Around("@annotation(Deprecated) && execution(String *(..))")
	public Object aroundalDeprecatedAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		Object returnObject = null;
		try {
			System.out.println("Before @Deprecated methode is called.");
			// returnObject = joinPoint.proceed();

			returnObject = "### AspectJ Call ###";
		} catch (Throwable throwable) {
			throw throwable;
		} finally {
			System.out.println("After @Deprecated methode is called.");
		}
		return returnObject;
	}

}