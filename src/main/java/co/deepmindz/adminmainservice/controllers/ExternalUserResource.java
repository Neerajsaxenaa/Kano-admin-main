package co.deepmindz.adminmainservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.deepmindz.adminmainservice.dto.ExternalReourceResponseDTO;
import co.deepmindz.adminmainservice.dto.ExternalResourceDTO;
import co.deepmindz.adminmainservice.dto.UserBaseAvailabilityDTO;
import co.deepmindz.adminmainservice.resources.CustomHttpResponse;
import co.deepmindz.adminmainservice.services.ExternalResourceService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin-main/external-resource")
public class ExternalUserResource {
    @Autowired
    ExternalResourceService externalResourceService;

    @PostMapping("/add-resource")
    public ResponseEntity<Object> addResource(@Valid @RequestBody ExternalResourceDTO externalResourceDTO) {
        String externalResourceExist = externalResourceService.isResourceExist(externalResourceDTO.getResource());
        if (externalResourceExist == null || externalResourceExist.isEmpty()) {
            externalResourceService.createResource(externalResourceDTO);
            return CustomHttpResponse.responseBuilder("New Resource Added: ", HttpStatus.CREATED, "newLanguage");
        } else {
            return CustomHttpResponse.responseBuilder("Resource already exists", HttpStatus.BAD_REQUEST, "error");
        }
    }
//
//<<<<<<<HEAD
//
//    @PostMapping("/create-user-base-availability")
//    public ResponseEntity<Object> createUserBaseAvailability(
//            @Valid @RequestBody UserBaseAvailabilityDTO userBaseAvailabilityDTO) {
//        boolean found = externalResourceService.isAvailbilityFound();
//        if (!found) {
//            externalResourceService.createUserBaseAvailability(userBaseAvailabilityDTO);
//            return CustomHttpResponse.responseBuilder("External User base has been updated", HttpStatus.CREATED,
//                    "success");
//        } else if (!userBaseAvailabilityDTO.isExternal_availability() && !found) {
//            externalResourceService.createUserBaseAvailability(userBaseAvailabilityDTO);
//            return CustomHttpResponse.responseBuilder("External User base has been updated", HttpStatus.CREATED,
//                    "success");
//        } else {
//            return CustomHttpResponse.responseBuilder("Request already served!", HttpStatus.BAD_REQUEST, "error");
//        }
//    }
//=======

    @PostMapping("/create-user-base-availability")
    public ResponseEntity<Object> createUserBaseAvailability(@Valid @RequestBody UserBaseAvailabilityDTO userBaseAvailabilityDTO) {
        boolean found = externalResourceService.isAvailbilityFound();
        System.out.println(found + "found");


        if (!found) {
            UserBaseAvailabilityDTO resource = externalResourceService.createUserBaseAvailability(userBaseAvailabilityDTO);
            System.out.println(resource + "ubuntu@deepmindz2023");
            if (resource.getMessage() != null && !resource.getMessage().isEmpty()) {
                return CustomHttpResponse.responseBuilder("Please Add Resource", HttpStatus.CONFLICT, "failed");
            }
            return CustomHttpResponse.responseBuilder("External User base has been updated", HttpStatus.CREATED, "success");
        } else {
            return CustomHttpResponse.responseBuilder("Request already served!", HttpStatus.BAD_REQUEST, "error");

        }

//        if (!found) {
//            UserBaseAvailabilityDTO resource = externalResourceService.createUserBaseAvailability(userBaseAvailabilityDTO);
//            System.out.println(resource);
//            if (resource.getMessage_code() == null || resource.getMessage_code().isEmpty()) {
//                return CustomHttpResponse.responseBuilder("Please Add Resource", HttpStatus.CONFLICT, "failed");
//            }
//            return CustomHttpResponse.responseBuilder("External User base has been updated", HttpStatus.CREATED, "success");
//        } else if (!found && !userBaseAvailabilityDTO.isExternal_availability()) {
//            UserBaseAvailabilityDTO resource = externalResourceService.createUserBaseAvailability(userBaseAvailabilityDTO);
//            System.out.println(resource);
//            if (resource.getMessage_code() == null || resource.getMessage_code().isEmpty()) {
//                return CustomHttpResponse.responseBuilder("Please Add Resource", HttpStatus.CONFLICT, "failed");
//            }
//            return CustomHttpResponse.responseBuilder("External User base has been updated", HttpStatus.CREATED, "success");
//        } else {
//            return CustomHttpResponse.responseBuilder("Request already served!", HttpStatus.BAD_REQUEST, "error");
//        }
//        return null;
    }
//>>>>>>>

//    aa0f2fe(Resource)

    @GetMapping("/check-user-base-resource")
    public ResponseEntity<Object> checkUserBaseAvailability() {
        boolean found = externalResourceService.isAvailbilityFound();
        return CustomHttpResponse.responseBuilder("Resource Found", HttpStatus.OK, found);
    }

    @GetMapping("/get-all-external-resource")
    public ResponseEntity<Object> getAllExternalResources() {
        List<ExternalReourceResponseDTO> responseDTOs = externalResourceService.getAllExternalResource();
        if (responseDTOs == null)
            return CustomHttpResponse.responseBuilder("External Resource Not found", HttpStatus.OK, "");
        else {
            return CustomHttpResponse.responseBuilder("All External Resource Configuration", HttpStatus.OK,
                    responseDTOs);
        }
    }

}
