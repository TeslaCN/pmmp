package ltd.scau.springframework.controller;

import ltd.scau.dao.UserDao;
import ltd.scau.dto.SearchResultDto;
import ltd.scau.dto.UserListDto;
import ltd.scau.mybatis.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Weijie Wu
 */
@RestController
public class SearchController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/basicsearch")
    public UserListDto basicSearch(@Valid SearchResultDto dto) {
        List<User> users = userDao.findAllByKey(dto.getKey());
        dto.setUsers(users);
        return dto;
    }
}
