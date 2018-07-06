package ltd.scau.springframework.controller;

import ltd.scau.dao.UserDao;
import ltd.scau.dao.UserRelationDao;
import ltd.scau.dto.ResultDto;
import ltd.scau.dto.UserListDto;
import ltd.scau.mybatis.po.User;
import ltd.scau.mybatis.po.UserRelation;
import ltd.scau.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Weijie Wu
 */
@RestController
public class RelationController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRelationDao userRelationDao;

    @RequestMapping(value = "/friend/{id}", method = RequestMethod.PUT)
    public ResultDto newFriend(@PathVariable Long id, @SessionAttribute(Constant.SESSION_USER) User me) {
        ResultDto dto = new ResultDto();

        UserRelation relation = userRelationDao.findByUsersId(me.getId(), id, false);

        if (relation == null) {
            relation = new UserRelation();

            relation.setUserAId(me.getId());
            relation.setUserBId(id);
            relation.setRelation(UserRelation.WAIT_FOR_ACCEPT);

            userRelationDao.persist(relation);

        } else if (relation.getRelation() == null || UserRelation.NONE.equals(relation.getRelation())) {

            relation.setRelation(UserRelation.WAIT_FOR_ACCEPT);
            userRelationDao.update(relation);

        } else if (UserRelation.WAIT_FOR_ACCEPT.equals(relation.getRelation())) {

        } else if (UserRelation.FRIEND.equals(relation.getRelation())) {

            dto.setCode(1);
            dto.setMessage("Already been friend.");

            return dto;
        }

        dto.setMessage("Wait for accept.");
        dto.setCode(0);

        return dto;
    }

    @RequestMapping(value = "/accept/{id}")
    public ResultDto accept(@PathVariable Long id, @SessionAttribute(Constant.SESSION_USER) User me) {
        ResultDto dto = new ResultDto();

        UserRelation relation = userRelationDao.findByUsersId(id, me.getId(), true);

        if (UserRelation.WAIT_FOR_ACCEPT.equals(relation.getRelation())) {

            relation.setRelation(UserRelation.FRIEND);
            userRelationDao.update(relation);

            dto.setCode(0);
            dto.setMessage(Constant.SUCCESS);

        } else {
            dto.setCode(1);
            dto.setMessage("Invalid Operation");
        }

        return dto;
    }

    @RequestMapping(value = "/refuse/{id}")
    public ResultDto refuse(@PathVariable Long id, @SessionAttribute(Constant.SESSION_USER) User me) {
        ResultDto dto = new ResultDto();

        UserRelation relation = userRelationDao.findByUsersId(id, me.getId(), true);

        if (UserRelation.WAIT_FOR_ACCEPT.equals(relation.getRelation())) {

            relation.setRelation(UserRelation.NONE);
            userRelationDao.update(relation);

            dto.setCode(0);
            dto.setMessage(Constant.SUCCESS);

        } else {
            dto.setCode(1);
            dto.setMessage("Invalid Operation");
        }

        return dto;
    }

    @RequestMapping(value = "/friend/{id}", method = RequestMethod.DELETE)
    public ResultDto removeFrient(@PathVariable Long id, @SessionAttribute(Constant.SESSION_USER) User me) {
        ResultDto dto = new ResultDto();

        int affected = userRelationDao.remove(me.getId(), id);

        if (affected > 0) {
            dto.setCode(0);
            dto.setMessage(Constant.SUCCESS);
        } else {
            dto.setCode(1);
            dto.setMessage("Affected relations: 0");
        }

        return dto;
    }

    @RequestMapping("/requests")
    public UserListDto getRequests(@SessionAttribute(Constant.SESSION_USER) User me) {
        UserListDto dto = new UserListDto();

        List<User> users = userRelationDao.findAllRequests(me.getId());

        dto.setUsers(users);

        return dto;
    }
}
