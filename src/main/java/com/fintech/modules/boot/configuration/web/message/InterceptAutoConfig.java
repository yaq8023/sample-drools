package com.fintech.modules.boot.configuration.web.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Description
 * <br> 自定义拦截器自动配置
 *
 * @author chendongdong
 * @date 2019/12/11
 **/
@Configuration
@ConditionalOnWebApplication
public class InterceptAutoConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        //FastJson转换器
        converters.add(0, fastJsonConverter());
    }


    /**
     * Description
     * <br>  FastJson转换器
     *
     * @date 2019/12/11
     * @author chendongdong
     **/
    private HttpMessageConverter fastJsonConverter() {
        //1、定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        //2、添加fastJson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //2-1 处理日期格式转换问题
        fastJsonConfig.setDateFormat(JSON.DEFFAULT_DATE_FORMAT);
        SerializerFeature[] serializerFeatures = new SerializerFeature[]{
                SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue
        };
        fastJsonConfig.setSerializerFeatures(serializerFeatures);
        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
        //2-2 处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonConverter.setFastJsonConfig(fastJsonConfig);
        return fastJsonConverter;
    }


}
