package co.deepmindz.adminmainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import co.deepmindz.adminmainservice.models.ConfigurationManagement;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationManagement, String> {

	@Query("select c from ConfigurationManagement c where c.configuration = :sub_service")
	ConfigurationManagement findByService(@RequestParam String sub_service);

}

