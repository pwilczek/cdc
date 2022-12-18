package name.wilu.zch.cdc.donations.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/event")
@Slf4j @RequiredArgsConstructor
public class EventsController {
    //
    final EventService service;

    @PostMapping
    public ResponseEntity<Event> addEvent(@RequestBody String name) {
        return ResponseEntity.ok(service.add(name));
    }

}
