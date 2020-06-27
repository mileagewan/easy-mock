package top.onchange.parent;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import top.onchange.modal.WsService;
import top.onchange.modal.WsServiceItem;
import top.onchange.modal.WsServiceRequestBody;
import top.onchange.modal.WsServiceRespone;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.ResourceUtils.*;

@Data
@RestController
@RequestMapping("/common")
public class CommonService {
    private static final Logger logger = LoggerFactory.getLogger(CommonService.class);

    //    private static String xmlPath = "H:\\Users\\wlc\\Desktop\\GitHub仓库\\easy-mock\\src\\main\\resources\\serviceConfig\\wsService.xml";
    private static File xmlPath;

    static {
        try {
            xmlPath = getFile(CLASSPATH_URL_PREFIX + "serviceConfig" + File.separator + "wsService.xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<WsServiceItem> listService = new ArrayList<>();

    private static Map<String, WsServiceItem> mapService = new HashMap<>();


    static {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(WsService.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            InputStreamReader isr = new InputStreamReader(new FileInputStream(xmlPath), "utf-8");
            WsService wsService = (WsService) unmarshaller.unmarshal(isr);
            listService = wsService.wsServiceItem;
            for (WsServiceItem wsServiceItem : listService) {
                mapService.put(wsServiceItem.serviceKey, wsServiceItem);
            }
            logger.info(String.format("class is load %s", listService));
        } catch (JAXBException | FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/service")
    public WsServiceRespone  query(@RequestBody WsServiceRequestBody wsServiceRequestBody) {


        WsServiceRespone wsServiceRespone = new WsServiceRespone();
        return wsServiceRespone;
    }
}
