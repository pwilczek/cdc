package name.wilu.zch.cdc.donations;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/event")
@Slf4j @RequiredArgsConstructor
class EventsController {
    //
    final EventService service;

    @PostMapping
    ResponseEntity<Event> addEvent(@RequestBody String name) {
        return ResponseEntity.ok(service.addEvent(name));
    }

    @PostMapping("/donate")
    ResponseEntity<Donation> donate(@RequestBody DonationRequest donation) {
        return ResponseEntity.ok(service.donate(donation.id, donation.amount));
    }
}

@Data
class DonationRequest {
    Long id, amount;
}
