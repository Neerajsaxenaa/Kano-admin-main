package co.deepmindz.adminmainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.deepmindz.adminmainservice.models.ConfigurationManagement;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationManagement, String> {

}

