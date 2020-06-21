package top.onchange.implement;

import org.springframework.stereotype.Component;
import top.onchange.interfaces.Heath;

@Component
public class Heathimpl implements Heath {
    @Override
    public String home() {
        return "Success";
    }
}
