package co.deepmindz.adminmainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.deepmindz.adminmainservice.models.MessageApis;

public interface MessageApiRepo extends JpaRepository<MessageApis, String> {

}
