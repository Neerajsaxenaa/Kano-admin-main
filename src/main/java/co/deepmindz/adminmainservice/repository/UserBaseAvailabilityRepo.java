package co.deepmindz.adminmainservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.deepmindz.adminmainservice.models.UserBaseAvailability;

public interface UserBaseAvailabilityRepo extends CrudRepository<UserBaseAvailability, Long> {

    @Query("SELECT u FROM UserBaseAvailability u")
    List<UserBaseAvailability> getAvailability();
}
