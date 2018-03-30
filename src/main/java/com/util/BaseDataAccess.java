package com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;
/**
 * jpa原生sql基类(使用方式：继承此类)
 */
public class BaseDataAccess {
    private static Logger logger = LoggerFactory.getLogger(BaseDataAccess.class);

    // 获得Factory
    @PersistenceUnit
    private EntityManagerFactory emf;

    /**
     * 查询工具方法(利用此方法通过sql直接操作数据库)
     * @param sql 原生sql写法
     * @return Object[]
     */
    public List transactionNo(String sql){
        // 获取Manager
        EntityManager em=emf.createEntityManager();
        // 获取操作对象
        Query query=em.createNativeQuery(sql);
        // 访问数据库并接收数据
        List list = null;
        try {
            list = query.getResultList();
        } catch (Exception e) {
            logger.info("【访问数据库异常】："+e.getMessage());
        }
        // 关闭
        em.close();
        return list;
    }

    /**
     * 增删改工具方法(利用此方法通过sql直接操作数据库)
     * @param sql 原生sql写法
     */
    public void transactionYes(String sql){
        // 获取Manager
        EntityManager em=emf.createEntityManager();
        // 获取事物
        EntityTransaction transaction = em.getTransaction();
        // 开启事物
        transaction.begin();
        // 获取操作对象
        Query query=em.createNativeQuery(sql);
        // 访问数据库
        try {
            query.executeUpdate();
        } catch (Exception e) {
            logger.info("【访问数据库异常】："+e.getMessage());
        }
        // 提交事物
        transaction.commit();
        // 关闭
        em.close();
    }
}
