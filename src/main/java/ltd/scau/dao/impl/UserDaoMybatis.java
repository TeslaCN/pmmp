package ltd.scau.dao.impl;

import ltd.scau.dao.UserDao;
import ltd.scau.mybatis.mapper.UserMapper;
import ltd.scau.mybatis.po.User;
import ltd.scau.mybatis.po.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Weijie Wu
 */
@Component
public class UserDaoMybatis implements UserDao {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User findByAccount(String account) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountEqualTo(account);
        List<User> users = userMapper.selectByExample(example);
        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public User persist(User user) {
        int record = userMapper.insertSelective(user);
        return user;
    }

    @Override
    public User update(User user) {
        // TODO implement
        return null;
    }
}
