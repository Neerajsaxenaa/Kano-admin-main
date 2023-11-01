package co.deepmindz.adminmainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.deepmindz.adminmainservice.models.Themes;

@Repository
public interface ThemeRepository extends JpaRepository<Themes, String> {

}