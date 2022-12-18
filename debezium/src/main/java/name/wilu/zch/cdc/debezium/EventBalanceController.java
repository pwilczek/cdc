package name.wilu.zch.cdc.debezium;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/balance")
@Slf4j @RequiredArgsConstructor
class EventBalanceController {
    //
    final EventService service;

    @GetMapping
    ResponseEntity<?> events() {
        return ResponseEntity.ok(service.events());
    }
}