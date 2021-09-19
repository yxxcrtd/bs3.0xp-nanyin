package com.jixu.api.util;

import com.jixu.common.apiconst.ApiPathConst;
import com.jixu.config.ServiceApplication;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @auth 何阳阳
 * @date 2021/7/9
 */
public final class RestUrlUtil {
    public static String apiUrl(String path) {
        return UriComponentsBuilder.fromHttpUrl(ServiceApplication.ADAPTER_SERVICE_NAME)
                .pathSegment(ApiPathConst.API_PATH, path).build().toString();
    }
}
