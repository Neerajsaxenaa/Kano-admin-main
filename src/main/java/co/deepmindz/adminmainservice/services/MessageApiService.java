package co.deepmindz.adminmainservice.services;

import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.MessageApiRequestDto;
import co.deepmindz.adminmainservice.models.MessageApis;

@Service
public interface MessageApiService {

	MessageApis saveMessageApi(MessageApiRequestDto dto);

}
