package co.deepmindz.adminmainservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.deepmindz.adminmainservice.models.Admin;
import jakarta.transaction.Transactional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

	@Transactional
	@Query(nativeQuery = true, value = "select * from admin a where a.user_id IN :cordinatorIds")
	List<Admin> getMobileByCordinatorIds(@Param("cordinatorIds") List<String> cordinatorIds);

	Optional<Admin> findByUserName(String userName);

	@Query(nativeQuery = true, value = "select * from admin where linked_zone = :linkedzoneid")
	public Admin getAdminUserByLinkedZoneId(@Param("linkedzoneid") String linkedzoneid);

}