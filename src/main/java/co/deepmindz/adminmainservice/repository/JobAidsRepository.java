package co.deepmindz.adminmainservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.deepmindz.adminmainservice.models.JobAids;
import jakarta.transaction.Transactional;

@Repository
public interface JobAidsRepository extends JpaRepository<JobAids, String> {

	@Transactional
	@Query(value = "select * from  job_aids where role_id =:role_id", nativeQuery = true)
	public List<JobAids> getAllJobAidsByRoleId(String role_id);

}
