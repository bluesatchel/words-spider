package com.freecoder.enlearn.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Frank
 * @date 2022/4/28 13:30
 */
@Component
public class HttpHelper {
    //type=1是英式发音,type=2是美式发音
    static String url = "https://dict.youdao.com/dictvoice?audio=";

    RestTemplate restTemplate = new RestTemplate();
    //获取英式发音
    public byte[] getMp3UK(String word) {
        return restTemplate.getForObject(url + word+"&type=1", byte[].class);
    }
    //获取美式发音
    public byte[] getMp3US(String word) {
        return restTemplate.getForObject(url + word+"&type=2", byte[].class);
    }


}
