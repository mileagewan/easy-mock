package top.onchange.parent;

import com.google.gson.Gson;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import top.onchange.modal.WsService;
import top.onchange.modal.WsServiceItem;
import top.onchange.modal.WsServiceRequestBody;
import top.onchange.modal.WsServiceResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    private static ClassPathResource  xmlPath;

    static {
        //            xmlPath = getFile(CLASSPATH_URL_PREFIX + "serviceConfig" + File.separator + "wsService.xml");
        xmlPath = new ClassPathResource("serviceConfig" + File.separator + "wsService.xml");

    }

    private static List<WsServiceItem> listService = new ArrayList<>();

    private static Map<String, WsServiceItem> mapService = new HashMap<>();


    static {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(WsService.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            InputStreamReader isr = new InputStreamReader(xmlPath.getInputStream(), "utf-8");
            WsService wsService = (WsService) unmarshaller.unmarshal(isr);
            listService = wsService.wsServiceItem;
            for (WsServiceItem wsServiceItem : listService) {
                mapService.put(wsServiceItem.serviceKey, wsServiceItem);
            }
            logger.info(String.format("class is load %s", listService));
            logger.info(String.format("class is load %s", listService));
        } catch (JAXBException | FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/service")
    public WsServiceResponse query(@RequestBody WsServiceRequestBody wsServiceRequestBody)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Gson gson = new Gson();
        String serviceKey = wsServiceRequestBody.serviceKey;
        WsServiceResponse wsServiceResponse = new WsServiceResponse();
        logger.info(gson.toJson(wsServiceRequestBody));
        if (!mapService.containsKey(serviceKey)) {
            wsServiceResponse.setServiceKey(serviceKey);
            wsServiceResponse.setReturnData("");
            wsServiceResponse.setSuccess(false);
            wsServiceResponse.setErrorMsg(String.format("the %s serviceKey was not found, please try again with other key", serviceKey));
        }

        WsServiceItem wsServiceItem = mapService.get(serviceKey);
        Class queryClass = Class.forName(wsServiceItem.serviceClass);
        Object queryObject = queryClass.newInstance();
        Method method = queryClass.getMethod(wsServiceItem.method, String.class);
        String response = (String) method.invoke(queryObject, wsServiceRequestBody.params);
        wsServiceResponse.setReturnData(response);
        wsServiceResponse.setSuccess(true);
        wsServiceResponse.setErrorMsg(null);
        logger.info(gson.toJson(wsServiceResponse));
        return wsServiceResponse;
    }
}
