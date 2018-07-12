package ltd.scau.springframework.controller;

import ltd.scau.dao.UserDao;
import ltd.scau.dto.RedirectResultDto;
import ltd.scau.dto.ResultDto;
import ltd.scau.dto.UserInfoDto;
import ltd.scau.mybatis.po.Image;
import ltd.scau.mybatis.po.User;
import ltd.scau.util.Constant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

            dto.setCode(1);
            dto.setMessage(Arrays.toString(errors.getAllErrors().toArray()));

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

            if (user == null || !securityContext.getAuthentication().getName().equals(user.getAccount())) {
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
        me.setPassword(null);
        dto.setUser(me);
        dto.setMessage(Constant.SUCCESS);

        return dto;
    }

    @RequestMapping(value = "/me", method = RequestMethod.POST)
    public ResultDto updateUser(@SessionAttribute(Constant.SESSION_USER) User oldValue, @Valid User newValue, Errors errors) {
        ResultDto dto = new ResultDto();

        if (errors.hasErrors()) {

            dto.setCode(1);
            dto.setMessage(Arrays.toString(errors.getAllErrors().toArray()));

        } else {

            Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            if (newValue.getPassword() != null
                    && !newValue.getPassword().trim().equals("")
                    && !newValue.getPassword().equals(oldValue.getPassword())
                    && !encoder.isPasswordValid(oldValue.getPassword(), newValue.getPassword(), null)) {
                newValue.setPassword(encoder.encodePassword(newValue.getPassword(), null));
            } else {
                newValue.setPassword(null);
            }

            newValue.setId(oldValue.getId());
            newValue.setAccount(null);
            userDao.updateSelective(newValue);
        }
        return dto;
    }

    @RequestMapping("/isexist")
    public ResultDto isAccountExist(String account) {
        ResultDto dto = new ResultDto();
        User user = userDao.findByAccount(account);
        if (user != null) {
            dto.setCode(1);
            dto.setMessage("账户已被注册");
        } else {
            dto.setCode(0);
            dto.setMessage(Constant.SUCCESS);
        }
        return dto;
    }

    @RequestMapping(value = "/head", method = RequestMethod.POST)
    public ResultDto upload(@SessionAttribute(Constant.SESSION_USER) User me, MultipartFile file) {
        ResultDto dto = new ResultDto();

        String suffix = "";
        Pattern fileTypeMatcher = Pattern.compile(".*?(\\.\\w+$)");

        String fileName = file.getOriginalFilename();
        Matcher m = fileTypeMatcher.matcher(fileName);
        if (m.find()) {
            suffix = m.group(1);
        }

        String saveName = String.format("%s_%s%s", me.getId(), System.currentTimeMillis(), suffix);

        try {
            file.transferTo(new File(saveName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info(String.format("Upload: %s, size = %d, save as: %s", file.getOriginalFilename(), file.getSize(), saveName));

        me.setProfileUrl(saveName);
        userDao.updateSelective(me);

        dto.setCode(0);
        dto.setMessage(Constant.SUCCESS);

        return dto;
    }

    @RequestMapping("/user/{id}")
    public UserInfoDto getUserById(@PathVariable Long id) {
        UserInfoDto dto = new UserInfoDto();

        User user = userDao.findById(id);
        if (user != null) {
            user.setPassword(null);
            dto.setCode(0);
            dto.setUser(user);
            dto.setMessage(Constant.SUCCESS);
        } else {
            dto.setCode(1);
            dto.setMessage("用户不存在");
        }

        return dto;
    }
}
