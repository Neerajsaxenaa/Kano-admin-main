package co.deepmindz.adminmainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.deepmindz.adminmainservice.models.ExternalResource;

public   interface ExternalResourceRepo extends JpaRepository<ExternalResource, Long> {

    @Query("SELECT resource FROM ExternalResource WHERE resource = :resource")
    String getResourceByResource(@Param("resource") String resource);


    @Query("SELECT resource FROM ExternalResource WHERE type = :type")
    String getResourceByType(@Param("type") String type);
}