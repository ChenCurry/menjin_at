package cn.possible2dream.menjin_at;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.possible2dream.menjin_at.mapper")
public class MenjinAtApplication {

    public static void main(String[] args) {
        SpringApplication.run(MenjinAtApplication.class, args);
    }

}
