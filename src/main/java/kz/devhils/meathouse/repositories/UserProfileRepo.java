package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {
}
