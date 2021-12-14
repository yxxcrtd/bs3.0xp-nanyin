package com.joyintech.tams.exchange.service.trade.util.http;

import org.apache.http.NameValuePair;
import org.apache.http.entity.ContentType;

public enum HttpContentType {

	json {
		@Override
		public ContentType getContentType(String charset,NameValuePair[] params) {
			ContentType type = ContentType.APPLICATION_JSON.withCharset(charset);
			if (null==params) {
				return type;
			}
			return type.withParameters(params);
		}
	},
	form {
		@Override
		public ContentType getContentType(String charset,NameValuePair[] params) {
			ContentType type = ContentType.APPLICATION_FORM_URLENCODED.withCharset(charset);
			if (null==params) {
				return type;
			}
			return type.withParameters(params);
		}
	},
	string {
		@Override
		public ContentType getContentType(String charset, NameValuePair[] params) {
			ContentType type = ContentType.TEXT_PLAIN.withCharset(charset);
			if (null==params) {
				return type;
			}
			return type.withParameters(params);
		}
	},
	file {
		@Override
		public ContentType getContentType(String charset,NameValuePair[] params) {
			ContentType type = ContentType.APPLICATION_OCTET_STREAM.withCharset(charset);
			if (null==params) {
				return type;
			}
			return type.withParameters(params);
		}
	};
	
	public abstract ContentType getContentType(String charset,NameValuePair[] params);
}
