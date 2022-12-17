package name.wilu.zch.cdc.donations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DonationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DonationsApplication.class, args);
    }

    @RestController
    static class HealthApi {
        //
        @GetMapping("/health") ResponseEntity<String> health() {
            return ResponseEntity.ok("ok");
        }

    }
}
