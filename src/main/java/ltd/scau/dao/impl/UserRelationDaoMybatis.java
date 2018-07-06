package ltd.scau.dao.impl;

import ltd.scau.dao.UserRelationDao;
import ltd.scau.mybatis.mapper.UserRelationMapper;
import ltd.scau.mybatis.po.User;
import ltd.scau.mybatis.po.UserRelation;
import ltd.scau.mybatis.po.UserRelationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Weijie Wu
 */
@Component
public class UserRelationDaoMybatis implements UserRelationDao {

    @Autowired
    private UserRelationMapper userRelationMapper;

    @Override
    public UserRelation persist(UserRelation userRelation) {
        userRelationMapper.insert(userRelation);
        return userRelation;
    }

    @Override
    public int remove(UserRelation userRelation) {
        return remove(userRelation.getUserAId(), userRelation.getUserBId());
    }

    @Override
    public int remove(Long aId, Long bId) {
        UserRelationExample example = new UserRelationExample();
        example.createCriteria().andUserAIdEqualTo(aId).andUserBIdEqualTo(bId);
        example.or().andUserAIdEqualTo(bId).andUserBIdEqualTo(aId);
        return userRelationMapper.deleteByExample(example);
    }

    @Override
    public List<User> findAllRefused(Long id) {
        // TODO
        return null;
    }

    @Override
    public UserRelation findByUsersId(Long aId, Long bId, boolean fixedPosition) {
        UserRelationExample example = new UserRelationExample();

        example.createCriteria().andUserAIdEqualTo(aId).andUserBIdEqualTo(bId);
        if (!fixedPosition) {
            example.or().andUserAIdEqualTo(bId).andUserBIdEqualTo(aId);
        }

        List<UserRelation> relations = userRelationMapper.selectByExample(example);
        if (relations.size() > 0) {
            return relations.get(0);
        }

        return null;
    }

    @Override
    public int update(UserRelation userRelation) {
        UserRelationExample example = new UserRelationExample();
        example.createCriteria().andUserAIdEqualTo(userRelation.getUserAId()).andUserBIdEqualTo(userRelation.getUserBId());
        return userRelationMapper.updateByExample(userRelation, example);
    }

    @Override
    public List<User> findAllFriends(Long id) {
        // TODO
        return null;
    }

    @Override
    public List<User> findAllRequests(Long id) {
        // TODO
        return null;
    }
}
