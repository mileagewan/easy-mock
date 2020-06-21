package top.onchange.service;

import org.springframework.beans.factory.annotation.Autowired;
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
    @ResponseBody
    public String home() {
        return heath.home();
    }
}
