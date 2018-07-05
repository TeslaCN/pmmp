package ltd.scau.dao;

import ltd.scau.mybatis.po.User;

import java.util.Date;
import java.util.List;

/**
 * @author Weijie Wu
 */
public interface UserDao {

    User findById(Long id);

    User findByAccount(String account);

    User persist(User user);

    User persistAndGrantUserRole(User user);

    User update(User user);

    User updateSelective(User user);

    List<User> findAll();

    List<User> findAllByKey(String key);

    List<User> findAllByKey(String key, Date start, Date end);
}
