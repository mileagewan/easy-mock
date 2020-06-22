package top.onchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.onchange.interfaces.Heath;

@RestController
@RequestMapping("/")
public class HeathConstrutor {

    @Autowired
    private Heath heath;

    @RequestMapping("/heath")
    public String home() {
        return heath.home();
    }
}
