package co.deepmindz.adminmainservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.deepmindz.adminmainservice.models.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {

	Optional<Admin> findByUserName(String userName);

	@Query(nativeQuery = true, value = "select * from admin where linked_zone = :linkedzoneid")
	public Admin getAdminUserByLinkedZoneId(@Param("linkedzoneid") String linkedzoneid);

}