package top.onchange.modal;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

@Data
@RestController
@RequestMapping("/common")
public class CommonService {
    private Logger logger = LoggerFactory.getLogger(CommonService.class);

    private static String xmlPath = "H:\\Users\\wlc\\Desktop\\GitHub仓库\\easy-mock\\src\\main\\resources\\serviceConfig\\wsService.xml";

    private static List<WsServiceItem> listService;

    static {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(WsService.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            InputStreamReader isr=new InputStreamReader(new FileInputStream(xmlPath),"utf-8");
            WsService wsService = (WsService) unmarshaller.unmarshal(isr);
            System.out.println(wsService);
            listService = wsService.wsServiceItem;
        } catch (JAXBException | FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/service")
    public List<WsServiceItem> say() {
        return listService;
    }
}
