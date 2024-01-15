package co.deepmindz.adminmainservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.deepmindz.adminmainservice.models.Admin;
import jakarta.transaction.Transactional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
	Optional<Admin> findByUserName(String userName);

	@Transactional
	@Query(nativeQuery = true, value = "select * from admin a where a.user_id IN :cordinatorIds")
	List<Admin> getMobileByCordinatorIds(@Param("cordinatorIds") List<String> cordinatorIds);

}