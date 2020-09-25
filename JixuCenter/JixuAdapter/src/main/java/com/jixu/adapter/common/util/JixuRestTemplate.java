package com.jixu.adapter.common.util;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;


@Service
public class JixuRestTemplate extends RestTemplate {
//
//    @Autowired(required = false)
//    private List<ClientHttpRequestHandler> handlers;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> Page<T> postForPage(String url, Object request, Class<T> beanClass, Object... uriVariables)
			throws RestClientException {
		if (StringUtil.isNotEmpty(url)) {
			url = url + (url.endsWith("/") ? "" : "/") + "{" + GlobalConstant.PARAM_PAGE + "}/{" + GlobalConstant.PARAM_LENGTH + "}";
		}
		String pageNumber = StringUtil.getOrElse(RequestUtil.getParam(GlobalConstant.PARAM_PAGE), "1");
		String pageSize = StringUtil.getOrElse(RequestUtil.getParam(GlobalConstant.PARAM_LENGTH), "20");
		Page<T> page = null;
		if(request instanceof Map) {
			pageNumber = StringUtil.getOrElse(((Map)request).get(GlobalConstant.PARAM_PAGE), pageNumber);
			pageSize = StringUtil.getOrElse(((Map)request).get(GlobalConstant.PARAM_LENGTH), pageSize);
			page = postForObject(url, request, Page.class, pageNumber, pageSize, uriVariables);
		}
		else {
			page = postForObject(url, request, Page.class, uriVariables);
		}
		page.setRoot(convertList(page.getRoot(), beanClass));
		return page;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> Page<T> postForPage(String url, Object request, Class<T> beanClass, Map<String, Object> uriVariables)
			throws RestClientException {
		if (StringUtil.isNotEmpty(url)) {
			url = url + (url.endsWith("/") ? "" : "/") + "{" + GlobalConstant.PARAM_PAGE + "}/{" + GlobalConstant.PARAM_LENGTH + "}";
			String pageNumber = StringUtil.getOrElse(RequestUtil.getParam(GlobalConstant.PARAM_PAGE), "1");
			String pageSize = StringUtil.getOrElse(RequestUtil.getParam(GlobalConstant.PARAM_LENGTH), "20");
			if(request instanceof Map) {
				pageNumber = StringUtil.getOrElse(String.valueOf(((Map)request).get(GlobalConstant.PARAM_PAGE)), pageNumber);
				pageSize = StringUtil.getOrElse(String.valueOf(((Map)request).get(GlobalConstant.PARAM_LENGTH)), pageSize);
			}
			if (!uriVariables.containsKey(GlobalConstant.PARAM_PAGE)) {
				uriVariables.put(GlobalConstant.PARAM_PAGE, pageNumber);
			}
			if (!uriVariables.containsKey(GlobalConstant.PARAM_LENGTH)) {
				uriVariables.put(GlobalConstant.PARAM_LENGTH, pageSize);
			}
		}
		Page<T> page = postForObject(url, request, Page.class, uriVariables);
		page.setRoot(convertList(page.getRoot(), beanClass));
		return page;
	}

	@SuppressWarnings({ "unchecked" })
	public <T> List<T> getForList(String url, Class<T> beanClass, Object... uriVariables) throws RestClientException {
		List<T> list = getForObject(trimUrl(url), List.class, uriVariables);
		return convertList(list, beanClass);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getForList(String url, Class<T> beanClass, Map<String, ?> uriVariables)
			throws RestClientException {
		List<T> list = getForObject(trimUrl(url), List.class, uriVariables);
		return convertList(list, beanClass);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getForList(URI url, Class<T> beanClass) throws RestClientException {
		List<T> list = getForObject(url, List.class);
		return convertList(list, beanClass);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> postForList(String url, Object request, Class<T> beanClass, Object... uriVariables)
			throws RestClientException {
		List<T> list = postForObject(url, request, List.class, uriVariables);
		return convertList(list, beanClass);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> postForList(String url, Object request, Class<T> beanClass, Map<String, ?> uriVariables)
			throws RestClientException {
		List<T> list = postForObject(url, request, List.class, uriVariables);
		return convertList(list, beanClass);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> postForList(URI url, Object request, Class<T> beanClass) throws RestClientException {
		List<T> list = postForObject(url, request, List.class);
		return convertList(list, beanClass);
	}

//	@Override
//    protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback,
//            ResponseExtractor<T> responseExtractor) throws RestClientException {
//
//        Assert.notNull(url, "'url' must not be null");
//        Assert.notNull(method, "'method' must not be null");
//        ClientHttpResponse response = null;
//        try {
//            ClientHttpRequest request = createRequest(url, method);
//            if (requestCallback != null) {
//                requestCallback.doWithRequest(request);
//            }
//            if(null != handlers) {
//                handlers.forEach(handler -> handler.preRequest(request));
//            }
//            response = request.execute();
//            handleResponse(url, method, response);
//            if(null != handlers) {
//                for(ClientHttpRequestHandler handler : handlers) {
//                    handler.afterResponse(request, response);
//                }
//            }
//            if (responseExtractor != null) {
//                return responseExtractor.extractData(response);
//            }
//            else {
//                return null;
//            }
//        }
//        catch (IOException ex) {
//            String resource = url.toString();
//            String query = url.getRawQuery();
//            resource = (query != null ? resource.substring(0, resource.indexOf('?')) : resource);
//            throw new ResourceAccessException("I/O error on " + method.name() +
//                    " request for \"" + resource + "\": " + ex.getMessage(), ex);
//        }
//        finally {
//            if (response != null) {
//                response.close();
//            }
//        }
//    }

    @SuppressWarnings({ "unchecked" })
    private <T> List<T> convertList(List<?> list, Class<T> beanClass) {
        if (list != null && list.size() > 0) {
            List<T> resultList = new ArrayList<T>(list.size());
            list.forEach(item -> {
                try {
                    if (item instanceof Map) {
                        resultList.add((T) JsonUtils.map2entity((Map<String, Object>) item, beanClass));
                    }
                } catch (Exception e) {
                    logger.error(e);
                }
            });
            return resultList;
        }
        return (List<T>) list;
    }
    
    private String trimUrl(String url) {
        if(StringUtil.isNotEmpty(url)) {
            if(url.contains("?") && (url.contains("=null") || url.contains("= null"))) {
                url = StringUtil.replaceAll(url,"=null", "");
                url = StringUtil.replaceAll(url,"= null", "");
            }
        }
        return url;
    }
}
