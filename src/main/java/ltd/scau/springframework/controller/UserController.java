package ltd.scau.springframework.controller;

import ltd.scau.dao.UserDao;
import ltd.scau.dto.RedirectResultDto;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        userDao.persistAndGrantUserRole(user);
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


    @RequestMapping("/check")
    public ResultDto check(@SessionAttribute(value = "SPRING_SECURITY_CONTEXT", required = false) SecurityContext securityContext,
                           @SessionAttribute(value = "redirect", required = false) String redirect, HttpServletRequest request) {
        ResultDto dto = null;
        if (securityContext == null) {
            dto = new ResultDto();
            logger.debug("CHECK:" + securityContext);
            dto.setCode(1);
            dto.setMessage("Not sign in.");
        } else if (redirect != null && !redirect.trim().equals("")) {
            RedirectResultDto rrdto = new RedirectResultDto();
            rrdto.setCode(0);
            if (redirect.contains("signin.html")) {
                rrdto.setRedirect(request.getContextPath() + "/");
            } else {
                rrdto.setRedirect(redirect);
            }
            rrdto.setMessage("Redirect to url before sign in.");
            request.getSession().setAttribute("redirect", null);
            logger.debug(rrdto);
            return rrdto;
        } else {
            dto = new ResultDto();
            dto.setMessage("Signed in.");
        }
        return dto;
    }

}
