package name.wilu.zch.cdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CdcApplication {

    public static void main(String[] args) {
        SpringApplication.run(CdcApplication.class, args);
    }

    @RestController
    static class HealthApi {
        //
        @GetMapping("/health") ResponseEntity<String> health() {
            return ResponseEntity.ok("ok");
        }

    }

}
