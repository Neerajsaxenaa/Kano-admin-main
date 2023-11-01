package co.deepmindz.adminmainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.deepmindz.adminmainservice.models.Resources;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceRepo extends JpaRepository<Resources, String> {
    @Query("SELECT r FROM Resources r WHERE r.type IN ('splash', 'logo')")
    List<Resources> getStaticAppResources();
}
