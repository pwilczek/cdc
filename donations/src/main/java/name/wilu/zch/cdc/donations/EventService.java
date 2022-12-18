package name.wilu.zch.cdc.donations;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional @RequiredArgsConstructor
class EventService {
    //
    final EventRepository repo;

    public Event add(String name) {
        Event event = new Event();
        event.setName(name);
        return repo.save(event);
    }
}

@Repository
interface EventRepository extends JpaRepository<Event, Long> {
}

