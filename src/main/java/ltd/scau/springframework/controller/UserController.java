package ltd.scau.springframework.controller;

import ltd.scau.dao.UserDao;
import ltd.scau.dto.ResultDto;
import ltd.scau.mybatis.mapper.RoleMapper;
import ltd.scau.mybatis.mapper.UserMapper;
import ltd.scau.mybatis.mapper.UserRoleMapper;
import ltd.scau.mybatis.po.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Weijie Wu
 */
@RestController
public class UserController {

    private static final Log logger = LogFactory.getLog(UserController.class);

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResultDto signUp(User user, String verify) {
        ResultDto dto = new ResultDto();
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        user.setPassword(encoder.encodePassword(user.getPassword(), null));
        userDao.persist(user);
        dto.setMessage("success");
        logger.info("PERSIST" + user);
        return dto;
    }

    @InitBinder
    public void InitBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
    }



}
