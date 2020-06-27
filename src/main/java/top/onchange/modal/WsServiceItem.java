package top.onchange.modal;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "wsServiceItem")
@Data
@Getter
@Setter
public class WsServiceItem implements Serializable {

    @XmlAttribute(name = "serviceKey")
    public String serviceKey;

    @XmlAttribute(name = "serviceClass")
    public String serviceClass;

    @XmlAttribute(name = "method")
    public String method;
}
