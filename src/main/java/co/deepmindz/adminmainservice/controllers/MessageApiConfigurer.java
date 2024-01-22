//package co.deepmindz.adminmainservice.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import co.deepmindz.adminmainservice.dto.MessageApiRequestDto;
//import co.deepmindz.adminmainservice.models.MessageApis;
//import co.deepmindz.adminmainservice.resources.CustomHttpResponse;
//import co.deepmindz.adminmainservice.services.MessageApiService;
//
//@RestController
//@RequestMapping("/admin-main/message")
//public class MessageApiConfigurer {
//
//	@Autowired
//	private MessageApiService messageApiService;
//
//	
//	@PostMapping("/save-message-api")
//	public ResponseEntity<Object> saveMessageApi(@RequestBody MessageApiRequestDto dto) {
//		MessageApis messageApis = messageApiService.saveMessageApi(dto);
//		return CustomHttpResponse.responseBuilder(" Meesage Api saved ", HttpStatus.OK, messageApis);
//	}
//
//}
