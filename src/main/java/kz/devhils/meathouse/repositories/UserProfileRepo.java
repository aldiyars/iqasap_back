package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {
}
