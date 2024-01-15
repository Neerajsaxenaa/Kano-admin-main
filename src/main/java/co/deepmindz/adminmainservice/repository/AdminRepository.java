package co.deepmindz.adminmainservice.repository;

<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> branch 'main' of https://github.com/SS-Whitelabel/ss-admin-main-service.git
import java.util.Optional;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.deepmindz.adminmainservice.models.Admin;
import jakarta.transaction.Transactional;
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.deepmindz.adminmainservice.models.Admin;
>>>>>>> branch 'main' of https://github.com/SS-Whitelabel/ss-admin-main-service.git

@Repository
<<<<<<< HEAD
public interface AdminRepository extends CrudRepository<Admin, Long> {
	Optional<Admin> findByUserName(String userName);

	@Transactional
	@Query(nativeQuery = true, value = "select * from admin a where a.user_id IN :cordinatorIds")
	List<Admin> getMobileByCordinatorIds(@Param("cordinatorIds") List<String> cordinatorIds);
=======
public interface AdminRepository extends JpaRepository<Admin, Long> {

	Optional<Admin> findByUserName(String userName);

	@Query(nativeQuery = true, value = "select * from admin where linked_zone = :linkedzoneid")
	public Admin getAdminUserByLinkedZoneId(@Param("linkedzoneid") String linkedzoneid);
>>>>>>> branch 'main' of https://github.com/SS-Whitelabel/ss-admin-main-service.git

}