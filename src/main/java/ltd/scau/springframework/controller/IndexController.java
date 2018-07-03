package ltd.scau.springframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Weijie Wu
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "{}";
    }
}
