package com.gz.common;

import com.gz.common.model._MappingKit;
import com.gz.controller.FileToolController;
import com.gz.controller.IndexController;
import com.gz.controller.ProductController;
import com.gz.controller.UserController;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;
import javax.sql.DataSource;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import handler.ResourceHandler;

import java.io.File;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * API引导式配置
 */
@Configuration
public class AppConfig {
	
	/**
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 * 
	 * 使用本方法启动过第一次以后，会在开发工具的 debug、run config 中自动生成
	 * 一条启动配置，可对该自动生成的配置再添加额外的配置项，例如 VM argument 可配置为：
	 * -XX:PermSize=64M -XX:MaxPermSize=256M
	 */
	public static void main(String[] args) {
		SpringApplication.run(AppConfig.class, args);
	}
	
	/**
	 * 配置常量
	 */
	@Autowired
	private Environment env;
	
	@Bean
	public Constants configConstant() {
	// Load necessary configurations
	Constants constants = new Constants();
	constants.setDevMode(Boolean.parseBoolean(env.getProperty("devMode")));
	constants.setBaseUploadPath(env.getProperty("base.upload.path"));
	constants.setBaseDownloadPath(env.getProperty("base.download.path"));
		return constants;
	}

	/**
	 * 配置路由
	 */
	@Bean
	public RouterFunction<ServerResponse> indexRoute(IndexController indexController) {
		return RouterFunctions.route(GET("/"), indexController::handle);
	}
	
	@Bean
	public RouterFunction<ServerResponse> fileRoute(FileToolController fileToolController) {
		return RouterFunctions.route(GET("/file"), fileToolController::handle);
	}
	
	@Bean
	public RouterFunction<ServerResponse> productRoute(ProductController productController) {
		return RouterFunctions.route(GET("/product"), productController::handle);
	}
	
	@Bean
	public RouterFunction<ServerResponse> userRoute(UserController userController) {
		return RouterFunctions.route(GET("/user"), userController::handle);

		me.add("/", IndexController.class);
		me.add("/file", FileToolController.class);
		me.add("/product", ProductController.class);
		me.add("/user", UserController.class);
	}

	public void configEngine(Engine me) {

	}

	/**
	 * 配置插件
	 */
	@Bean
	public DataSource dataSource() {
	// Configure druid database connection pool plugin
	return DataSourceBuilder.create()
		.url(env.getProperty("spring.datasource.url"))
		.username(env.getProperty("spring.datasource.username"))
		.password(env.getProperty("spring.datasource.password"))
		.driverClassName(env.getProperty("spring.datasource.driver-class-name"))
		.build();
	}
	
	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}
	
	/**
	 * 配置全局拦截器
	 */
	@Bean
	public InterceptorRegistry configInterceptor() {
		InterceptorRegistry registry = new InterceptorRegistry();
	// Add necessary interceptors
	InterceptorRegistry registry = new InterceptorRegistry();
	registry.addInterceptor(new SomeInterceptor());
	return registry;
	}
	
	@Bean
	public ResourceHandlerRegistry configHandler() {
		ResourceHandlerRegistry registry = new ResourceHandlerRegistry();
		registry.addResourceHandler("/**").addResourceLocations("/");
		return registry;
	}
}
