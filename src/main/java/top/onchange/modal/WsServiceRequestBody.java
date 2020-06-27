package top.onchange.modal;

import lombok.Getter;
import lombok.Setter;

/**
 * 公共方法中requestBody
 *
 * @author WLC
 * @since 2020-6-26
 *
 */
@Getter
@Setter
public class WsServiceRequestBody {
    /**
     * 方法key
     */
    private String serviceKey;

    /**
     * 公共参数
     */
    private String params;

}
