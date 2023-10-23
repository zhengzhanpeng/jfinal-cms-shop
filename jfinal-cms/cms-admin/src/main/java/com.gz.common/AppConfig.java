package com.gz.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import javax.sql.DataSource;
import com.jfinal.template.Engine;
import handler.ResourceHandler;

import java.io.File;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * API引导式配置
 */
@SpringBootApplication
public class AppConfig {
	
	/**
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 * 
	 * 使用本方法启动过第一次以后，会在开发工具的 debug、run config 中自动生成
	 * 一条启动配置，可对该自动生成的配置再添加额外的配置项，例如 VM argument 可配置为：
	 * -XX:PermSize=64M -XX:MaxPermSize=256M
	 */
	public static void main(String[] args) {
		/**
		 * 特别注意：Eclipse 之下建议的启动方式
		 */
		public static void main(String[] args) {
				SpringApplication.run(AppConfig.class, args);
			}
	
	/**
	 * 配置常量
	 */
	@Configuration
	@PropertySource("classpath:a_little_config.txt")
	public class AppConfig {
	
	   @Value("${devMode}")
	   private boolean devMode;
	
	   @Value("${FILE_UPLOAD_PATH}")
	   private String fileUploadPath;
	
	   @Bean
	   public AppConfig appConfig() {
	       AppConfig appConfig = new AppConfig();
	       appConfig.devMode = this.devMode;
	       appConfig.fileUploadPath = this.fileUploadPath;
	       return appConfig;
	   }
	}

	/**
	 * 配置路由
	 */
	// Removed configRoute method as routing is handled through annotations in Spring Boot

	public void configEngine(Engine me) {

	}

	/**
	 * 配置插件
	 */
	@Bean
	public DataSource dataSource() {
	    return DataSourceBuilder.create()
	            .url("jdbcUrl")
	            .username("user")
	            .password("password")
	            .build();
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		
	}
	
	/**
	 * 配置处理器
	 */
	// No replacement needed as resources are handled through static resource configurations in Spring Boot
}
