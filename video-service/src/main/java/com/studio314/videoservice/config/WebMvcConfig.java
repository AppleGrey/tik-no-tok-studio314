package com.studio314.videoservice.config;

import com.studio314.videoservice.service.VideoService;
import com.studio314.videoservice.service.impl.VideoServiceImpl;
import com.studio314.videoservice.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 钱波
 * @ClassName WebMvcConfig
 * @description: 进行视频文件的路径映射
 * @date 2024年05月30日
 * @version: 1.0
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    VideoServiceImpl videoService;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将匹配上/files/**虚拟路径的URL映射到文件上传到服务器的目录，获取静态资源
        registry.addResourceHandler("/files/**")
            .addResourceLocations("file:" + Constant.UPLOAD_DIR.getValue() + "/");
    }
}
