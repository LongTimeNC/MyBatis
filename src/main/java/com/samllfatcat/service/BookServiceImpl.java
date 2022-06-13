package com.samllfatcat.service;

import com.samllfatcat.dao.BookMapper;
import com.samllfatcat.pojo.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zsz
 * @Description
 * @date 2022/6/12
 */
public class BookServiceImpl{

    //查询所有数据
    @Test
    public void findAll() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        List<Book> books = mapper.selectAll();
        System.out.println(books);
        sqlSession.close();
    }
    @Test
    //通过id查询
    public void findById() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        Book book = mapper.selectById(3);
        System.out.println(book);
        sqlSession.close();
    }

    @Test
    //动态Sql查询 sql-if
    public void findByCondition() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        //模拟条件查询
        Book condition = new Book();
        condition.setId(3);
        condition.setBook_name("月亮与六便士");
        List<Book> books = mapper.selectByCondition(condition);
        System.out.println(books);
        sqlSession.close();
    }

    @Test
    //动态Sql查询 sql-foreach
    public void findByIds() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        //模拟条件查询
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ids.add(2);
        ids.add(3);
        ids.add(8);
        ids.add(9);
        List<Book> books = mapper.selectByIds(ids);
        System.out.println(books);
        sqlSession.close();
    }


    @Test
    //测试自定义处理器
    public void save() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        //模拟数据
        Book book = new Book();
        book.setBook_name("火影忍者");
        book.setAuthor("朱圣哲");
        book.setPrice(BigDecimal.valueOf(13));
        //通过自定义处理器 将Date类型转换成long类型的毫秒值
        book.setTime(new Date());
        mapper.save(book);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    //测试自定义处理器
    public void save2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        Book book = mapper.selectById(12);
        System.out.println("Book中的Time："+book.getTime());
        sqlSession.close();
    }





}
