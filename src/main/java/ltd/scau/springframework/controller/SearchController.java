package ltd.scau.springframework.controller;

import ltd.scau.dto.UserListDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Weijie Wu
 */
@RestController
public class SearchController {

    @RequestMapping("/basicsearch")
    public UserListDto basicSearch(UserListDto dto) {

        return dto;
    }
}
