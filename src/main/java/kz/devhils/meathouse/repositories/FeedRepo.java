package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.entities.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepo extends JpaRepository<Feed, Long> {
}
