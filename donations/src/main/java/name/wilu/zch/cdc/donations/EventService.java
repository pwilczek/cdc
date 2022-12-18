package name.wilu.zch.cdc.donations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional @RequiredArgsConstructor @Slf4j
class EventService {
    //
    final EventRepository eventRepo;
    final DonationRepository donationRepo;

    Event addEvent(String name) {
        Event event = new Event();
        event.setName(name);
        Event saved = eventRepo.save(event);
        log.info("New event added {}", saved);
        return saved;
    }

    Donation donate(Long id, Long amount) {
        Donation donation = new Donation();
        donation.setAmount(amount);
        Event event = new Event();
        event.setId(id);
        donation.setEvent(event);
        Donation saved = donationRepo.save(donation);
        log.info("New donation added {}", saved);
        return saved;
    }
}

@Repository
interface EventRepository extends JpaRepository<Event, Long> {
}

@Repository
interface DonationRepository extends JpaRepository<Donation, Long> {
}