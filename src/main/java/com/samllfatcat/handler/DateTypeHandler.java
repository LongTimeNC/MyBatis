package com.samllfatcat.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author zsz
 * @Description
 * @date 2022/6/12
 */
public class DateTypeHandler extends BaseTypeHandler<Date> {
    //将java类型转化成 数据库需要的类型
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        long time = date.getTime();
        preparedStatement.setLong(i,time);
    }
    //将数据库中的类型 转换成java中的类型
    //String参数 要转换的字段名称
    //resultSet 查询出得结果集
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
        //获得结果集中需要的数据(long)转化成(Date)并返回
        long aLong = resultSet.getLong(s);
        Date date = new Date(aLong);
        return date;
    }
    //将数据库中的类型 转换成java中的类型
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        //获得结果集中需要的数据(long)转化成(Date)并返回
        long aLong = resultSet.getLong(i);
        Date date = new Date(aLong);
        return date;
    }
    //将数据库中的类型 转换成java中的类型
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        long aLong = callableStatement.getLong(i);
        Date date = new Date(aLong);
        return date;
    }
}
