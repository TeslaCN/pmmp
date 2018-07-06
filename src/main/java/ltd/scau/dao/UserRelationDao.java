package ltd.scau.dao;

import ltd.scau.mybatis.po.User;
import ltd.scau.mybatis.po.UserRelation;

import java.util.List;

/**
 * @author Weijie Wu
 */
public interface UserRelationDao {

    List<User> findAllFriends(Long id);

    List<User> findAllRequests(Long id);

    List<User> findAllRefusedMe(Long id);

    UserRelation findByUsersId(Long aId, Long bId, boolean fixedPosition);

    UserRelation persist(UserRelation userRelation);

    int update(UserRelation userRelation);

    int remove(UserRelation userRelation);

    int remove(Long aId, Long bId);
}
