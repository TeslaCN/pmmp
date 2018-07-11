package ltd.scau.springframework.controller;

import com.github.pagehelper.PageHelper;
import ltd.scau.dao.UserDao;
import ltd.scau.dto.UserListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Weijie Wu
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/unrealuser")
    public UserListDto getUnrealUser(UserListDto dto) {

        PageHelper.startPage(dto.getPageNo(), dto.getPageSize());

        return dto;
    }
}
