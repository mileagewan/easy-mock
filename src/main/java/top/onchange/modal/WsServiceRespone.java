package top.onchange.modal;

import lombok.Data;

import java.io.Serializable;

@Data
public class WsServiceRespone implements Serializable {
    private String serviceKey;

    private String returnData;

    private Boolean success;

    private String errorMsg;
}
