package net.xinqushi.orm.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.xinqushi.common.exceptions.CommonException;

public class DataSourceSwapper {

	private static Logger logger = LoggerFactory.getLogger(DataSourceSwapper.class);

	public Object swapDataSource(ProceedingJoinPoint jp) throws CommonException {
		MethodSignature methodSignature = (MethodSignature) jp.getSignature();
		Method targetMethod = methodSignature.getMethod();
		DataSource dataSource = targetMethod.getAnnotation(DataSource.class);
		if (dataSource == null) {
			SuperDataSource.setCurrentLookupKey(DataSource.WRITE);
		} else {
			SuperDataSource.setCurrentLookupKey(dataSource.name());
		}

		Object[] args = jp.getArgs();
		try {
			return jp.proceed(args);
		} catch (CommonException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Fail to invoke service", e);
			throw new CommonException("内部错误");
		} catch (Throwable e) {
			logger.error("Fail to invoke service", e);
			throw new CommonException("内部错误");
		}
	}

}
