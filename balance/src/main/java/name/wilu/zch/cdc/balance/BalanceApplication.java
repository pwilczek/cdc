package name.wilu.zch.cdc.balance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BalanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BalanceApplication.class, args);
    }

    @RestController
    static class HealthApi {
        //
        @GetMapping("/health") ResponseEntity<String> health() {
            return ResponseEntity.ok("ok");
        }
    }
}
