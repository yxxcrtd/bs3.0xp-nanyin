package com.joyintech.tams.mobileApproval.util;

import java.util.Map;

import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class HttpUitls {
	public static <T>T sendPostRequest(String url, MultiValueMap<String, String> params,Class<T> responseType){
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用ResultVO类格式化
        ResponseEntity<T> response = client.exchange(url, method, requestEntity, responseType);

        return response.getBody();
    }
	
	public static <T>T sendGetRequest(String url, Map<String, String> params,Class<T> responseType){
        RestTemplate restTemplate = new RestTemplate();
        T t = restTemplate.getForObject(url
                , responseType);
        
        return t;
    }
}
