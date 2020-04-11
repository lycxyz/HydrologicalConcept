package com.opengms.HydrologicalConcept;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HydrologicalConceptApplication {

	public static void main(String[] args) {
		SpringApplication.run(HydrologicalConceptApplication.class, args);
	}

	/**
	 * 注册netty-socketio服务端
	 * @author liangxifeng 2018-07-07
	 * @return
	 */
	@Bean
	public SocketIOServer socketIOServer() {
		com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();

		String os = System.getProperty("os.name");
		if(os.toLowerCase().startsWith("win")){   //在本地window环境测试时用localhost
			System.out.println("this is  windows");
			config.setHostname("localhost");
		} else {
			config.setHostname("223.2.41.168");
		}
		config.setPort(8090);

		final SocketIOServer server = new SocketIOServer(config);
		return server;
	}

	/**
	 * tomcat启动时候，扫码socket服务器并注册
	 * @param socketServer
	 * @return
	 */
	@Bean
	public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
		return new SpringAnnotationScanner(socketServer);
	}

}
