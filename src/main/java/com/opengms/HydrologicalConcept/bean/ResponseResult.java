package com.opengms.HydrologicalConcept.bean;

/**
 * Description: HydrologicalConcept
 * <p>
 * Created by LYC on 2019/12/19 20:35
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "请求结果响应体")
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 5872345331016245767L;

    @ApiModelProperty(value = "响应状态回执码")
    private Integer code;

    @ApiModelProperty(value = "数据体")
    private T data;

    @ApiModelProperty(value = "响应回执消息")
    private String msg;

    @ApiModelProperty(value = "响应时间戳")
    private final long timestamps = System.currentTimeMillis();

    public synchronized static <T> ResponseResult<T> result(ResponseCode code) {
        return result(code,null);
    }

    public synchronized static <T> ResponseResult<T> result(ResponseCode code, T data) {
        ResponseResult<T> res = new ResponseResult<>();
        res.setCode(code.code);
        res.setMsg(code.msg);
        res.setData(data);
        return res;
    }
}
