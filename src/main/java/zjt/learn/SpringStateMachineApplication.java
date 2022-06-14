package zjt.learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("zjt.learn.repository.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class SpringStateMachineApplication  extends SpringBootServletInitializer {
        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(SpringStateMachineApplication.class);
        }
        public static void main(String[] args) {
            SpringApplication.run(SpringStateMachineApplication.class, args);
        }
}