package top.onchange.modal;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "wsServiceItem")
@Data
public class WsServiceItem implements Serializable {

    @XmlAttribute(name = "serviceKey")
    public String serviceKey;

    @XmlAttribute(name = "serviceClass")
    public String serviceClass;

    @XmlAttribute(name = "method")
    public String method;
}
