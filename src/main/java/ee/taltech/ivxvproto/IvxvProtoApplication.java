package ee.taltech.ivxvproto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IvxvProtoApplication {

    public static void main(String[] args) {
        SpringApplication.run(IvxvProtoApplication.class, args);
    }

}
