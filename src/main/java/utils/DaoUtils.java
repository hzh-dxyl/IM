package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public final class DaoUtils {
    /**
     * 1.创建和初始化SqlSessionFactory
     * 2.能够创建在一定范围内（线程）有效的SqlSession
     * 3.能够关闭在一定范围内（）有效的SqlSession
     * 4.能够进行事务控制
     * 5.一些额外的支持Dao层便捷开发的方法
     * * 场景1：重定向
     * * me -> A(伊滨)      1request->response   1线程
     * *    -> B(西工)      2request->response   2线程
     * *    -> C(涧西)      3request->response   3线程
     * *    -> A(伊滨)      4request->response   4线程
     * * 场景2：转发         1request->response   1线程
     * * me -> A(伊滨)
     * *             -电话-> B(西工)
     * *             -电话-> C(涧西)
     * *     <-
     * * 多线程环境下访问SqlSession是同一个么、需要是同一个么？  不是同一个
     * * 单线程环境下访问SqlSession是同一个么、需要是同一个么？  是同一个
     */
    private static final String CONFIG_FILE = "dao/mybatis-config.xml";
    private static SqlSessionFactory factory;
    private static final ThreadLocal<SqlSessionFactory> threadFactory = new ThreadLocal<>();
    private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();

    /*static{
        initFactory(CONFIG_FILE);
    }*/
    private DaoUtils() {
    }

    public static void initFactory(String configName) {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream(configName);
            factory = builder.build(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static SqlSession open() {
        SqlSessionFactory factory = threadFactory.get();
        if (factory == null) {
            InputStream in = null;
            try {
                in = Resources.getResourceAsStream(CONFIG_FILE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            factory = new SqlSessionFactoryBuilder().build(in);
            threadFactory.set(factory);
        }
        SqlSession session = threadLocal.get();
        if (session == null) {
            session = factory.openSession(true);
            threadLocal.set(session);//线程绑定session，保证每次调用的是同一个线程
        }
        return session;
    }

    public static void close() {
        SqlSession session = threadLocal.get();
        if (session != null) {
            session.close();
            threadLocal.remove();
        }
        if (threadFactory.get() != null)
            threadFactory.remove();
    }

    public static void commit() {
        SqlSession session = threadLocal.get();
        if (session != null) session.commit();
    }

    public static <T> T getMapper(Class<T> tClass) {
        return open().getMapper(tClass);
    }

}
