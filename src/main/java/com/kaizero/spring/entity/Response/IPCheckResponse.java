package com.kaizero.spring.entity.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IPCheckResponse extends ResponseData {
    private Integer port;
    private String ip;
}
