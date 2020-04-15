package com.rss.framework;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * xuquan 2018-04-01
 * 基于通用MyBatis Mapper插件的Service接口的实现
 */
public abstract class AbstractService<T> implements Service<T> {

    @Autowired
    protected iMapper<T> mapper;

    private Class<T> modelClass;    // 当前泛型真实类型的Class

    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    /////////////////////////////////////////////////////////////////
    //等号的CRUD
    ////////////////////////////////////////////////////////////////

    //插入一条数据,只插入不为null的字段,不会影响有默认值的字段
    //支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
    //优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
    public void insert(T model) {
        mapper.insertSelective(model);
    }

    public void insert(List<T> models) {
        mapper.insertList(models);
    }

    //插入一条数据
    //支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
    //优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
    public  void insertSelective(T model){mapper.insertSelective(model);}

    //通过主键进行删除,这里最多只会删除一条数据
    //单个字段做主键时,可以直接写主键的值
    //联合主键时,key可以是实体类,也可以是Map
    public void deleteById(String id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void deleteByIds(String ids) {mapper.deleteByIds(ids);}

    //根据实体类中字段不为null的条件进行删除,条件全部使用=号and条件
    public int delete(T key){return mapper.delete(key);}

    //根据主键进行更新
    //只会更新不是null的数据
    public int update(T model) {
        return mapper.updateByPrimaryKeySelective(model);
    }

    //根据主键进行更新,这里最多只会更新一条数据
    //参数为实体类
    public int updateByPrimaryKey(T model){return mapper.updateByPrimaryKey(model);}

    //根据主键进行查询,必须保证结果唯一
    //单个字段做主键时,可以直接写主键的值
    //联合主键时,key可以是实体类,也可以是Map
    public T findById(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T findBy(String fieldName, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<T> findByModel(T model){return mapper.select(model);}

    public List<T> findByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    public List<T> findByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }

    public List<T> findAll() {
        return mapper.selectAll();
    }

    public T selectOne(T model){return mapper.selectOne(model);}

    //////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////
    ////条件的CRUD
    //////////////////////////////////////////////////////////////////

    //根据实体类不为null的字段查询总数,条件全部使用=号and条件
    public int selectCount(T model){return mapper.selectCount(model);}

    //根据Condition条件进行查询总数
    public int selectCountByCondition(Object condition){
        return mapper.selectCountByCondition(condition);
    }

    //根据Condition条件更新实体record包含的全部属性，null值会被更新
    public int updateByCondition(@Param("record") T record, @Param("example") Object condition){
        return mapper.updateByCondition(record,condition);
    }

    //根据Condition条件更新实体record包含的不是null的属性值
    public int updateByConditionSelective(@Param("record") T record, @Param("example") Object condition){
        return mapper.updateByConditionSelective(record,condition);
    }

    //根据Condition条件删除数据
    public int deleteByCondition(Object condition){
        return mapper.deleteByCondition(condition);
    }

    ///////////////////////////////////////////////////////////////////////
}
