package com.samllfatcat.dao;

import com.samllfatcat.pojo.Book;
import java.util.List;

/**
 * @author zsz
 * @Description
 * @date 2022/6/12
 */
public interface BookMapper {
    /**
     * 查询所有的方法
     */
    public List<Book> selectAll();
    /**
     * 通过id查询数据
     */
    public Book selectById(int id);


    /**
     * 动态Sql查询---sql if
     */
    public List<Book> selectByCondition(Book book);

    /**
     * 动态Sql查询---sql foreach
     */
    public List<Book> selectByIds(List<Integer> ids);

    /**
     * 自定义类型处理器
     */
    public void save(Book book);


}
