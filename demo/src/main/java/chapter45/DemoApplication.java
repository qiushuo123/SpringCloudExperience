package chapter45;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    @RequestMapping("/")
    public String greeting(){
        return "hello world";
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("链接数据库已启动");
    }
}

