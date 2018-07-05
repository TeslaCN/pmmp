package ltd.scau.dao.impl;

import ltd.scau.dao.UserDao;
import ltd.scau.mybatis.mapper.RoleMapper;
import ltd.scau.mybatis.mapper.UserMapper;
import ltd.scau.mybatis.mapper.UserRoleMapper;
import ltd.scau.mybatis.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author Weijie Wu
 */
@Component
public class UserDaoMybatis implements UserDao {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

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
    public User persistAndGrantUserRole(User user) {
        persist(user);
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andRolenameEqualTo(Role.ROLE_USER);
        Role role = roleMapper.selectByExample(roleExample).get(0);
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(role.getId());
        userRoleMapper.insert(userRole);
        return user;
    }

    @Override
    public User update(User user) {
        userMapper.updateByPrimaryKey(user);
        return user;
    }

    @Override
    public User updateSelective(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return user;
    }

    @Override
    public List<User> findAllByKey(String key, Date start, Date end) {
        UserExample example = new UserExample();

        if (start != null && end != null && start.before(end)) {
            example.createCriteria().andNicknameLike(key);
        } else {
            example.createCriteria().andNicknameLike(key).andBirthBetween(start, end);
        }
        example.or(example.createCriteria().andEducationLike(key));
        example.or(example.createCriteria().andDescriptionLike(key));
        example.or(example.createCriteria().andAddressLike(key));
        example.or(example.createCriteria().andEmailLike(key));
        example.or(example.createCriteria().andRealnameLike(key));

        return userMapper.selectByExample(example);
    }

    @Override
    public List<User> findAllByKey(String key) {
        return findAllByKey(key, null, null);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectByExample(null);
    }
}
