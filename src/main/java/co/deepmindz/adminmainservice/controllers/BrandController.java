package co.deepmindz.adminmainservice.controllers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.deepmindz.adminmainservice.models.Resources;
import co.deepmindz.adminmainservice.repository.ResourceRepo;
import co.deepmindz.adminmainservice.resources.CustomHttpResponse;
import co.deepmindz.adminmainservice.services.ResourceService;
import co.deepmindz.adminmainservice.utils.Templates;
/*  author - Neeraj Kumarr
 * 
 * 
 */
@RestController
@RequestMapping("/admin-main/brand")
public class BrandController {
	@Autowired
	private ResourceService resourceService;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ResourceRepo resourceRepo;

	private String FILE_PATH_ROOT = "src/main/resources/static/";

	
	@PostMapping("/app-static/upload")
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("type") String type) throws IOException {
		resourceService.fileUploadFuction(file, type);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String url = "/admin-main/brand/entity/download/" + fileName; // Adjust the URL path as needed
		String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path(url).toUriString();
		Resources resources2 = new Resources();
		resources2.setType(type);
		resources2.setName(fileName);
		resources2.setUrl(downloadUrl);
		Resources findByType = resourceService.findByType(type);
		Map<String, Object> response = new HashMap<>();

		if (findByType == null) {
			resourceService.save(resources2);
			response.put("downloadUrl", downloadUrl);
			return CustomHttpResponse.responseBuilder(type + " uploaded successfully", HttpStatus.CREATED, response);
		}
		String resourceId = findByType.getResourceId();
		resources2.setResourceId(resourceId);
		resourceService.save(resources2);
		response.put("downloadUrl", downloadUrl);

		return CustomHttpResponse.responseBuilder(type + " uploaded successfully", HttpStatus.CREATED, response);
	}

	@GetMapping("/entity/download/{filename:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
		try {
			// Construct the path to the file based on your storage directory
			String imageDirectory = "src/main/resources/static/"; // Relative path within your main service
			String filePath = imageDirectory + filename;

			// Create a File representing the destination file
			File file = new File(filePath);

			// Load the file as a resource
			Resource resource = new FileSystemResource(file);

			if (resource.exists()) {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.setContentDisposition(ContentDisposition.builder("attachment").filename(filename).build());
				return ResponseEntity.ok().headers(headers).body(resource);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/entity/view-image/{filename:.+}")
	public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) {
		byte[] image = new byte[0];
		try {

			image = FileUtils.readFileToByteArray(new File(FILE_PATH_ROOT + filename));
//			System.out.println("file path :" + FILE_PATH_ROOT);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);

	}

	@PostMapping("/app-static/upload-image-forDesktop")
	public ResponseEntity<Object> viewUploadImage(@RequestParam("file") MultipartFile file,
			@RequestParam("type") String type) throws IOException {
		ResponseEntity<Object> fileUploadFuction = resourceService.fileUploadFuction(file, type);
		if (fileUploadFuction.getStatusCode().isSameCodeAs(HttpStatus.BAD_REQUEST)) {
			return CustomHttpResponse.responseBuilder( "Invalid file type : " + type, HttpStatus.NOT_ACCEPTABLE, type);
		}
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		String url = "/admin-main/brand/entity/view-image/" + fileName; // Adjust the URL path as needed
		String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path(url).toUriString();
		Resources resources2 = new Resources();
		if (type.equals(Templates.LOGO_TYPES.login_screen.name()))
			resources2.setType(Templates.LOGO_TYPES.login_screen.name());
		else
			resources2.setType(Templates.LOGO_TYPES.splash_screen.name());
		resources2.setType(type);
		resources2.setName(fileName);
		resources2.setUrl(downloadUrl);
		Resources findByType = resourceService.findByType(type);
		Map<String, Object> response = new HashMap<>();

		if (findByType == null) {
			resourceService.save(resources2);
			response.put("downloadUrl", downloadUrl);
			return CustomHttpResponse.responseBuilder(type + " uploaded successfully", HttpStatus.CREATED, response);
		}
		String resourceId = findByType.getResourceId();
		resources2.setResourceId(resourceId);
		resourceService.save(resources2);
		response.put("downloadUrl", downloadUrl);

		return CustomHttpResponse.responseBuilder(type + " uploaded successfully", HttpStatus.CREATED, response);
	}
	@GetMapping("/get-all-images")
	public ResponseEntity<Object> getAllImages() {
		List<Resources> findAllImages = resourceRepo.findAll();
		if (findAllImages.isEmpty()) {
			return CustomHttpResponse.responseBuilder("Images are not found", HttpStatus.OK, findAllImages);
		}
		return CustomHttpResponse.responseBuilder("All avilable images", HttpStatus.OK, findAllImages);
	}

}
