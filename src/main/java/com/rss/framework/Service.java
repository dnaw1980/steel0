package com.rss.framework;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * xuquan 2018-04-01
 * Service 层 基础接口，其他Service 接口 请继承该接口
 */
public interface Service<T> {
    //xuquan
    //2019-07-22 Integer --> String,因为使用了32位无符号整数，字段类型定义为String
    //等号的CRUD
    void insert(T model);//持久化
    void insert(List<T> models);//批量持久化
    void insertSelective(T model); //插入一条数据,只插入不为null的字段,不会影响有默认值的字段
    void deleteById(String id);//通过主鍵刪除
    void deleteByIds(String ids);//批量刪除 eg：ids -> “1,2,3,4”
    int delete(T key); //根据实体类中字段不为null的条件进行删除,条件全部使用=号and条件
    int update(T model);//根据主键进行更新
    int updateByPrimaryKey(T model); //根据主键进行更新,这里最多只会更新一条数据
    T findById(String id);//通过ID查找
    T findBy(String fieldName, Object value) throws TooManyResultsException; //通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
    List<T> findByIds(String ids);//通过多个ID查找//eg：ids -> “1,2,3,4”
    List<T> findByModel(T model); //根据实体类不为null的字段进行查询,条件全部使用=号and条件
    int selectCount(T model); //根据实体类不为null的字段查询总数,条件全部使用=号and条件
    List<T> findAll();//获取所有
    T selectOne(T model); //根据实体类不为null的字段进行查询，只能有一个返回值，有多个结果抛出异常

    //条件的CRUD:
    List<T> findByCondition(Condition condition);//根据条件查找
    int selectCountByCondition(Object condition); //根据Condition条件进行查询总数
    int updateByCondition(@Param("record") T record, @Param("example") Object condition); //根据Condition条件更新实体record包含的全部属性，null值会被更新
    int updateByConditionSelective(@Param("record") T record, @Param("example") Object condition); //根据Condition条件更新实体record包含的不是null的属性值
    int deleteByCondition(Object condition); //根据Condition条件删除数据
}
