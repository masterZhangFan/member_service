package cn.gaozheng.sales.split;

import cn.gaozheng.sales.anotation.table.TableSplit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import java.sql.Connection;
import java.util.Properties;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class TableSplitInterceptor implements Interceptor {
    private Log log = LogFactory.getLog(getClass());
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_OBJECT_REFLECTOR_FACTORY=new DefaultReflectorFactory();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,DEFAULT_OBJECT_REFLECTOR_FACTORY);

        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
        // Configuration configuration = (Configuration) metaStatementHandler
        // .getValue("delegate.configuration");
        Object parameterObject = metaStatementHandler.getValue("delegate.boundSql.parameterObject");
        doSplitTable(metaStatementHandler);
        // 传递给下一个拦截器处理
        return invocation.proceed();

    }

    @Override
    public Object plugin(Object target) {
        // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private void doSplitTable(MetaObject metaStatementHandler) throws ClassNotFoundException{
        String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        if (originalSql != null && !originalSql.equals("")) {
            log.info("分表前的SQL："+originalSql);
            MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
            String id = mappedStatement.getId();
            String className = id.substring(0, id.lastIndexOf("."));
            Class<?> classObj = Class.forName(className);
            // 根据配置自动生成分表SQL
            TableSplit tableSplit = classObj.getAnnotation(TableSplit.class);
            if (tableSplit != null && tableSplit.split()) {
                StrategyManager strategyManager = ContextHelper.getStrategyManager();
                Strategy strategy=strategyManager.getStrategy(tableSplit.strategy());//获取分表策略来处理分表
                String convertedSql=originalSql.replaceAll(tableSplit.value(), strategy.convert(tableSplit.value()));
                metaStatementHandler.setValue("delegate.boundSql.sql",convertedSql);
                log.info("分表后的SQL："+convertedSql);
            }
        }
    }
}
