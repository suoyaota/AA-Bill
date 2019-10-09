package uestc.wyb.aa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.mybatis.spring.annotation.MapperScan;

@MapperScan("uestc.wyb.aa.mapper")
@SpringBootApplication
public class AaApplication {

    public static void main(String[] args) { SpringApplication.run(AaApplication.class, args);
    }

}
