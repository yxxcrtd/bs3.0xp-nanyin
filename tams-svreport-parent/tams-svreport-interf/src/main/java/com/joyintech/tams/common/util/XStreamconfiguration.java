/**
 * 
 */
package com.joyintech.tams.common.util;

import com.joyintech.framework.base.BaseEntity;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

/**
 *
 * XStream工具工厂
 *
 * @author zcw
 *
 */
@Configuration
public class XStreamconfiguration {

	/**
	 * 下发综合信披接口使用
	 *
	 * @return
	 */
	@Bean("xstream")
	public XStream xstream() {
		XStream xstream = new  XStream(new DomDriver("UTF-8", new NameCoder(){
			@Override
			public String encodeNode(String str) {
				if(str.equalsIgnoreCase("LIST")){
					return "body";
				}else{
					return str.equalsIgnoreCase("info")?"info": FieldNameUtil.camelCase2Underscore(str);
				}
			}

			@Override
			public String encodeAttribute(String str) {
				if(str.equalsIgnoreCase("LIST")){
					return "body";
				}else{
					return str.equalsIgnoreCase("info")?"info": FieldNameUtil.camelCase2Underscore(str);
				}            }

			@Override
			public String decodeNode(String s) {
				return null;
			}

			@Override
			public String decodeAttribute(String s) {
				return null;
			}
		}));
		// 忽略父类不需要的字段
		xstream.omitField(BaseEntity.class,"createUser");
		xstream.omitField(BaseEntity.class,"createTime");
		xstream.omitField(BaseEntity.class,"updateUser");
		xstream.omitField(BaseEntity.class,"updateTime");
		xstream.omitField(BaseEntity.class,"status");
		// 日期格式化(设置时区)
		xstream.registerConverter(new DateConverter("yyyy-MM-dd", null, TimeZone.getTimeZone("GMT+8")));
		// 不指向引用
		xstream.setMode(XStream.NO_REFERENCES);

		return xstream;
	}
}
