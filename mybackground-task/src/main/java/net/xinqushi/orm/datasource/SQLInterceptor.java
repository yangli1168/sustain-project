package net.xinqushi.orm.datasource;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class SQLInterceptor implements Interceptor {

	private static Logger logger = LoggerFactory.getLogger("ConnectionPoolLog");

	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
		BoundSql boundSql = statementHandler.getBoundSql();
		BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler,
		        "delegate");
		MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate,
		        "mappedStatement");
		logger.info("---Begin----------------------------------------------" + mappedStatement.getId());
		logger.info(boundSql.getSql());
		if (mappedStatement.getParameterMap().getParameterMappings() != null && mappedStatement.getParameterMap().getParameterMappings().size() > 0) {
			for (ParameterMapping mapping : mappedStatement.getParameterMap().getParameterMappings()) {
				logger.info(mapping.toString());
			}
		}
		logger.info("---End----------------------------------------------");
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}
}
