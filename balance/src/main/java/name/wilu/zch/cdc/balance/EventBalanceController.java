package name.wilu.zch.cdc.balance;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequestMapping("/balance")
@Slf4j @RequiredArgsConstructor
class EventBalanceController {
    //
    final EventService service;

    @GetMapping
    ResponseEntity<List<EventBalance>> events() {
        return ResponseEntity.ok(service.events());
    }
}