package webCalendarSpring;


import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RepositoryInterface extends JpaRepository<Event, Long> {
    List<Event> findByDate(LocalDate date);

    Optional<Event> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);

    List<Event> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
