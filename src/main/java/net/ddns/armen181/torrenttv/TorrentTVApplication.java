package net.ddns.armen181.torrenttv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class TorrentTVApplication {

    public static void main(String[] args) {
        SpringApplication.run(TorrentTVApplication.class, args);
    }
}
