package co.deepmindz.adminmainservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.dto.MessageApiRequestDto;
import co.deepmindz.adminmainservice.models.MessageApis;
import co.deepmindz.adminmainservice.repository.MessageApiRepo;
import co.deepmindz.adminmainservice.services.MessageApiService;

@Service
public class MessageApiImpl implements MessageApiService {
	
	@Autowired
	private MessageApiRepo messageApiRepo;

	@Override
	public MessageApis saveMessageApi(MessageApiRequestDto dto) {
		MessageApis messageApis = new MessageApis();
		messageApis.setApi(dto.getApi());
		messageApis.setBody(dto.getApiJson().toString());
	  return  messageApiRepo.save(messageApis);

	}

}
