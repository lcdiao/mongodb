package cn.lcdiao.springboot_mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created by diao on 2019/3/8
 */
public interface UserRepository  extends MongoRepository<User,Long> {

    public Optional<User> findByUserName(String userName);
}
