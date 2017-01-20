package com.dexcoder.test.persistence;

import java.util.Date;

import org.junit.Test;

import com.dexcoder.dal.BoundSql;
import com.dexcoder.dal.build.Criteria;
import com.dexcoder.dal.handler.DefaultMappingHandler;
import com.dexcoder.test.model.User;

/**
 * Created by liyd on 2015-12-8.
 */
public class CriteriaTest {

    @Test
    public void insertSql() {
        User user = new User();
        user.setEmail("selfly@dexcoder.com");
        user.setUserAge(18);
        user.setUserType("1");
        user.setGmtBeginTime(new Date());

        System.out.println("---------------含entity-----------------------");
        Criteria<User> criteria = Criteria.insert(User.class).mappingHandler(new DefaultMappingHandler())
            .into("userId", 10000L).into("loginName", "selfly").into("password", "123456");
        BoundSql boundSql = criteria.build(user, true);
        System.out.println(boundSql.getSql());
        for (Object obj : boundSql.getParameters()) {
            System.out.println(obj);
        }
        System.out.println("---------------不含entity-----------------------");
        criteria = Criteria.insert(User.class).mappingHandler(new DefaultMappingHandler()).into("userId", 10000L)
            .into("loginName", "selfly").into("password", "123456");
        boundSql = criteria.build(null, true);
        System.out.println(boundSql.getSql());
        for (Object obj : boundSql.getParameters()) {
            System.out.println(obj);
        }
    }

    @Test
    public void updateSql() {
        User user = new User();
        user.setEmail("selfly@dexcoder.com");
        user.setUserAge(18);
        user.setUserType("1");

        System.out.println("---------------含entity-----------------------");
        Criteria<User> criteria = Criteria.update(User.class).set("loginName", "selfly").set("password", "123456")
            .where("userId", "not in", new Object[] { 10000L, 100001L, 10000L });
        BoundSql boundSql = criteria.mappingHandler(new DefaultMappingHandler()).build(user, true);
        System.out.println(boundSql.getSql());
        for (Object obj : boundSql.getParameters()) {
            System.out.println(obj);
        }

        System.out.println("---------------不含entity-----------------------");
        criteria = Criteria.update(User.class).tableAlias("t").set("loginName", "selfly").set("password", "123456")
            .where("userId", "not in", new Object[] { 10000L, 100001L, 10000L });
        boundSql = criteria.mappingHandler(new DefaultMappingHandler()).build(null, true);
        System.out.println(boundSql.getSql());
        for (Object obj : boundSql.getParameters()) {
            System.out.println(obj);
        }

        System.out.println("---------------含native处理-----------------------");
        criteria = Criteria.update(User.class).tableAlias("t").set("{t.loginName}", "selfly").set("[userType]", "2")
            .set("[userAge]", "[userAge]+1").where("userId", "not in", new Object[] { 10000L, 100001L, 10000L });
        boundSql = criteria.mappingHandler(new DefaultMappingHandler()).build(null, true);
        System.out.println(boundSql.getSql());
        for (Object obj : boundSql.getParameters()) {
            System.out.println(obj);
        }

        System.out.println("---------------含entity null属性-----------------------");
        criteria = Criteria.update(User.class).set("loginName", "selfly").where("userId", new Object[] { 10000L });
        boundSql = criteria.mappingHandler(new DefaultMappingHandler()).build(user, false);
        System.out.println(boundSql.getSql());
        for (Object obj : boundSql.getParameters()) {
            System.out.println(obj);
        }
    }

    @Test
    public void deleteSql() {
        User user = new User();
        user.setEmail("selfly@dexcoder.com");
        user.setUserAge(18);

        System.out.println("---------------含entity-----------------------");
        Criteria<User> criteria = Criteria.delete(User.class).tableAlias("t")
            .where("userId", "not in", new Object[] { 10000L, 100001L, 10000L }).begin()
            .and("userType", new Object[] { 1111 }).or("password", new Object[] { "123456" }).end();
        BoundSql boundSql = criteria.mappingHandler(new DefaultMappingHandler()).build(user, true);
        System.out.println(boundSql.getSql());
        for (Object obj : boundSql.getParameters()) {
            System.out.println(obj);
        }

        System.out.println("---------------不含entity-----------------------");
        criteria = Criteria.delete(User.class).where("userId", "not in", new Object[] { 10000L, 100001L, 10000L })
            .begin().and("userType", new Object[] { 1111 }).or("password", new Object[] { "123456" }).end();
        boundSql = criteria.mappingHandler(new DefaultMappingHandler()).build(null, true);
        System.out.println(boundSql.getSql());
        for (Object obj : boundSql.getParameters()) {
            System.out.println(obj);
        }

        System.out.println("---------------含native处理-----------------------");
        criteria = Criteria.delete(User.class).where("userId", new Object[] { 10000L })
            .and("{userType}", new Object[] { "1" }).or("[userAge]", new Object[] { "max([userAge])" });
        boundSql = criteria.mappingHandler(new DefaultMappingHandler()).build(null, true);
        System.out.println(boundSql.getSql());
        for (Object obj : boundSql.getParameters()) {
            System.out.println(obj);
        }
    }

    @Test
    public void selectSql() {

        User user = new User();
        user.setEmail("selfly@dexcoder.com");
        user.setUserAge(18);

        System.out.println("---------------含entity-----------------------");
        BoundSql boundSql = Criteria.select(User.class).mappingHandler(new DefaultMappingHandler()).tableAlias("t")
            .where("userId", new Object[] { 111, 333 }).build(user, true);
        System.out.println(boundSql.getSql());
        for (Object obj : boundSql.getParameters()) {
            System.out.println(obj);
        }

        System.out.println("---------------不含entity-----------------------");
        boundSql = Criteria.select(User.class).mappingHandler(new DefaultMappingHandler()).include("loginName")
            .where("userId", new Object[] { 111, 333 }).build(null, true);
        System.out.println(boundSql.getSql());
        for (Object obj : boundSql.getParameters()) {
            System.out.println(obj);
        }

        System.out.println("---------------含function处理-----------------------");
        boundSql = Criteria.select(User.class).mappingHandler(new DefaultMappingHandler()).tableAlias("t")
            .addSelectFunc("count(*)").where("userId", new Object[] { 111, 333 }).build(null, true);
        System.out.println(boundSql.getSql());
        for (Object obj : boundSql.getParameters()) {
            System.out.println(obj);
        }

        System.out.println("---------------含native处理-----------------------");
        boundSql = Criteria.select(User.class).mappingHandler(new DefaultMappingHandler()).addSelectFunc("count(*)")
            .where("userId", new Object[] { 111, 333 }).and("[userAge]", ">", new Object[] { 16 }).build(null, true);
        System.out.println(boundSql.getSql());
        for (Object obj : boundSql.getParameters()) {
            System.out.println(obj);
        }
    }

    @Test
    public void nativeUpdateSql() {

        User user = new User();
        user.setEmail("selfly@dexcoder.com");
        user.setUserType("1");

        Criteria<User> criteria = Criteria.update(User.class).tableAlias("t").set("[userAge]", "[userAge] + 1")
            .set("password", "123456").where("userId", "not in", new Object[] { 10000L, 100001L, 10000L });
        BoundSql boundSql = criteria.mappingHandler(new DefaultMappingHandler()).build(user, true);
        System.out.println(boundSql.getSql());
        for (Object obj : boundSql.getParameters()) {
            System.out.println(obj);
        }
    }

    @Test
    public void nativeSelectSql() {

        User user = new User();
        user.setEmail("selfly@dexcoder.com");
        user.setUserType("1");

        Criteria<User> criteria = Criteria.select(User.class).tableAlias("t").include("userId")
            .where("[userId]", new Object[] { "[userAge]" });
        BoundSql boundSql = criteria.mappingHandler(new DefaultMappingHandler()).build(user, true);
        System.out.println(boundSql.getSql());
        for (Object obj : boundSql.getParameters()) {
            System.out.println(obj);
        }
    }

    @Test
    public void nativeDeleteSql() {

        User user = new User();
        user.setEmail("selfly@dexcoder.com");
        user.setUserType("1");

        Criteria<User> criteria = Criteria.delete(User.class).where("[userId]", new Object[] { "[userAge]" });
        BoundSql boundSql = criteria.mappingHandler(new DefaultMappingHandler()).build(user, true);
        System.out.println(boundSql.getSql());
        for (Object obj : boundSql.getParameters()) {
            System.out.println(obj);
        }
    }

    @Test
    public void countSelectSql() {

        User user = new User();
        user.setEmail("selfly@dexcoder.com");
        user.setUserType("1");

        Criteria<User> criteria = Criteria.select(User.class).tableAlias("t").addSelectFunc("count(*)")
            .where("[userId]", new Object[] { "[userAge]" });
        BoundSql boundSql = criteria.mappingHandler(new DefaultMappingHandler()).build(null, true);
        System.out.println(boundSql.getSql());
        for (Object obj : boundSql.getParameters()) {
            System.out.println(obj);
        }
    }

}
