package ltd.scau.springframework.controller;

import ltd.scau.dao.UserDao;
import ltd.scau.dto.RedirectResultDto;
import ltd.scau.dto.ResultDto;
import ltd.scau.dto.UserInfoDto;
import ltd.scau.mybatis.po.User;
import ltd.scau.util.Constant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Map;

/**
 * @author Weijie Wu
 */
@RestController
@SessionAttributes(value = {Constant.SESSION_USER, Constant.SESSION_REDIRECT, Constant.VERIFICATION_CODE}, types = {User.class, String.class})
public class UserController {

    private static final Log logger = LogFactory.getLog(UserController.class);

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResultDto signUp(@Valid User user, String verify, Errors errors,
                            @SessionAttribute(Constant.VERIFICATION_CODE) String verificationCode,
                            Map<String, Object> map) {
        ResultDto dto = new ResultDto();
        map.put(Constant.VERIFICATION_CODE, null);

        if (verificationCode == null || verify == null || verificationCode.trim().equals("") || verify.trim().equals("") || !verificationCode.equalsIgnoreCase(verify)) {

            dto.setCode(1);
            dto.setMessage("验证码不一致");

            return dto;
        }

        if (errors.hasErrors()) {

            ObjectError[] errorArray = (ObjectError[]) errors.getAllErrors().toArray();

            dto.setCode(1);
            dto.setMessage(Arrays.toString(errorArray));

        } else {

            Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            user.setPassword(encoder.encodePassword(user.getPassword(), null));

            userDao.persistAndGrantUserRole(user);
            dto.setMessage(Constant.SUCCESS);

            logger.info("PERSIST: " + user);
        }
        return dto;
    }

    @RequestMapping("/error")
    public ResultDto error(@RequestAttribute(value = "SPRING_SECURITY_LAST_EXCEPTION", required = false) AuthenticationException exception) {
        ResultDto dto = new ResultDto();
        if (exception == null) {
            dto.setCode(0);
            dto.setMessage("none");
        } else {
            dto.setCode(1);
            dto.setMessage(exception.getLocalizedMessage());
        }
        return dto;
    }

    @RequestMapping("/check")
    public ResultDto check(@SessionAttribute(value = "SPRING_SECURITY_CONTEXT", required = false) SecurityContext securityContext,
                           @SessionAttribute(value = Constant.SESSION_REDIRECT, required = false) String redirect,
                           @SessionAttribute(value = Constant.SESSION_USER, required = false) User user,
                           Map<String, Object> map) {

        logger.info("CHECK: " + user + "\nREDIRECT: " + redirect + "\nSECURITY_CONTEXT" + securityContext);

        if (securityContext == null) {

            ResultDto dto = new ResultDto();
            dto.setCode(1);
            dto.setMessage("Not sign in.");

            return dto;

        } else if (redirect != null && !redirect.trim().equals("")) {

            if (user == null) {
                user = userDao.findByAccount(securityContext.getAuthentication().getName());
                map.put(Constant.SESSION_USER, user);
            }

            RedirectResultDto rrdto = new RedirectResultDto();
            rrdto.setCode(0);

            if (!redirect.trim().equals("") && !redirect.contains("signin.html")) {
                rrdto.setRedirect(redirect);
            }

            rrdto.setMessage("Redirect to url before sign in.");
            map.put(Constant.SESSION_REDIRECT, null);

            return rrdto;

        } else {

            ResultDto dto = new ResultDto();

            if (user == null) {
                user = userDao.findByAccount(securityContext.getAuthentication().getName());
                map.put(Constant.SESSION_USER, user);
            }

            dto.setMessage("Signed in.");

            return dto;
        }
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public UserInfoDto me(@SessionAttribute("SPRING_SECURITY_CONTEXT") SecurityContext securityContext) {

        User me = userDao.findByAccount(securityContext.getAuthentication().getName());

        UserInfoDto dto = new UserInfoDto();
        dto.setUser(me);
        dto.setMessage(Constant.SUCCESS);

        return dto;
    }

    @RequestMapping(value = "/me", method = RequestMethod.POST)
    public ResultDto updateUser(@SessionAttribute(Constant.SESSION_USER) User oldValue, @Valid User newValue, Errors errors) {
        ResultDto dto = new ResultDto();

        if (errors.hasErrors()) {

            ObjectError[] errorArray = (ObjectError[]) errors.getAllErrors().toArray();

            dto.setCode(1);
            dto.setMessage(Arrays.toString(errorArray));

        } else {

            Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            if (newValue.getPassword() != null) {
                newValue.setPassword(encoder.encodePassword(newValue.getPassword(), null));
            }

            newValue.setId(oldValue.getId());
            userDao.update(newValue);
        }
        return dto;
    }


}
