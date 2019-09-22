package common;

import mapper.CommonMapper;
import models.CommonModel;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


public class CaseSqlSentences {
    //查询语句，ID为case的id
    public static CommonModel selectOne(int id){
        CommonModel commonModel;
        SqlSession session = getSqlSession();


        try {
            CommonMapper mapper = session.getMapper(CommonMapper.class);
            commonModel = mapper.getCommonModelId(id);

        } finally {
            session.close();
        }
        return commonModel;
    }

  //查询语句查询所有，返回测试ID，list
    public static List<Integer> selectAllReturnId(){
        List<Integer> list = new ArrayList<Integer>();
        SqlSession session = getSqlSession();


        try {
            CommonMapper mapper = session.getMapper(CommonMapper.class);
            list = mapper.getAll();

        } finally {
            session.close();
        }
        return list;

    }

    //获取Session
    public static SqlSession getSqlSession() {
        //获取配置的资源文件
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("databaseConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //得到SqlSessionFactory,使用这个类加载器加载xml文件
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        //得到sqlsession对象，这个对象能执行配置文件中的sql语句
        SqlSession session = factory.openSession();
        return  session;
    }

}
