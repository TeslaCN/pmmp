package ltd.scau.springframework.controller;

import com.github.pagehelper.PageHelper;
import ltd.scau.dao.UserDao;
import ltd.scau.dto.AdvancedSearchResultDto;
import ltd.scau.dto.SearchResultDto;
import ltd.scau.dto.UserListDto;
import ltd.scau.mybatis.po.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author Weijie Wu
 */
@RestController
public class SearchController {

    private static final Log logger = LogFactory.getLog(SearchController.class);

    @Autowired
    private UserDao userDao;

    @RequestMapping("/basicsearch")
    public SearchResultDto basicSearch(@Valid SearchResultDto dto, Errors errors) {
        if (errors.hasErrors()) {

            dto.setCode(1);

            ObjectError[] errorArray = (ObjectError[]) errors.getAllErrors().toArray();
            dto.setMessage(Arrays.toString(errorArray));
        }
        else {

            dto.setCode(0);

            PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
            List<User> users = userDao.findAllByKey("%" + dto.getKey() + "%");

            dto.setUsers(users);
        }
        return dto;
    }

    @RequestMapping("/advancedsearch")
    public AdvancedSearchResultDto basicSearch(@Valid AdvancedSearchResultDto dto, Errors errors) {
        if (errors.hasErrors()) {

            dto.setCode(1);

            ObjectError[] errorArray = (ObjectError[]) errors.getAllErrors().toArray();
            dto.setMessage(Arrays.toString(errorArray));
        } else {

            dto.setCode(0);

            PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
            List<User> users = userDao.findAllByKey("%" + dto.getKey() + "%", dto.getStart(), dto.getEnd());

            dto.setUsers(users);
        }
        return dto;
    }

}
