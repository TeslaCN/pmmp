package ltd.scau.springframework.controller;

import ltd.scau.dto.ResultDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Weijie Wu
 */
@RestController
public class UserController {

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResultDto signIn() {
        ResultDto dto = new ResultDto();
        dto.setMessage("success");
        return dto;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResultDto signUp() {
        ResultDto dto = new ResultDto();

        return dto;
    }
}
