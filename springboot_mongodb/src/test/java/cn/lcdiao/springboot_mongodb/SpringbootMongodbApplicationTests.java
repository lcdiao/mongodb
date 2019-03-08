package cn.lcdiao.springboot_mongodb;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMongodbApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private UserRepository repository;

//   @Before
//    public void setUp(){
//        repository.deleteAll();
//    }

    @Test
    public void test(){

        repository.deleteAll();

        //创建三个User，并验证User总数
        repository.save(new User(1L,"john",30));
        repository.save(new User(2L,"ken",22));
        repository.save(new User(3L,"tom",33));
        Assert.assertEquals(3,repository.findAll().size());

        //删除一个User，再验证User总数
        Optional<User> u = repository.findById(1L);
        repository.delete(u.get());
        Assert.assertEquals(2,repository.findAll().size());

        Optional<User> o = repository.findByUserName("tom");
        repository.delete(o.get());
        Assert.assertEquals(1,repository.findAll().size());
    }
}
