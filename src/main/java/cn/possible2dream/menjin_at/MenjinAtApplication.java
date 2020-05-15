package cn.possible2dream.menjin_at;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("cn.possible2dream.menjin_at.web.servlet")
@MapperScan("cn.possible2dream.menjin_at.dao")
public class MenjinAtApplication {

    public static void main(String[] args) {
        SpringApplication.run(MenjinAtApplication.class, args);
    }

}
