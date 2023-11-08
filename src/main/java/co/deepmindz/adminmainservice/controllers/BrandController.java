package co.deepmindz.adminmainservice.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import co.deepmindz.adminmainservice.resources.CustomHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.deepmindz.adminmainservice.models.Resources;
import co.deepmindz.adminmainservice.services.ResourceService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/admin-main/brand")
public class BrandController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/app-static/upload")
    public ResponseEntity<Object> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("type") String type)
            throws IOException {
        if (type.contains("splash") || type.contains("logo")) {
            String imageDirectory = "src/main/resources/static/";
            String currentDirectory = System.getProperty("user.dir");
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fullImageDirectory = currentDirectory + "/" + imageDirectory;
            File directory = new File(fullImageDirectory);
            // Create the directory if it doesn't exist
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println("Directory created successfully.");
                } else {
                    return ResponseEntity.badRequest().body("Failed to create directory.");
                }
            }
            File destinationFile = new File(fullImageDirectory, fileName);
            file.transferTo(destinationFile);
            String url = "/admin-main/brand/entity/download/" + fileName; // Adjust the URL path as needed
            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(url)
                    .toUriString();
            Resources resources2 = new Resources();
            resources2.setType(type);
            resources2.setName(fileName);
            resources2.setUrl(downloadUrl);
            resourceService.save(resources2);


            Map<String, Object> response = new HashMap<>();
            response.put("downloadUrl", downloadUrl);

            return CustomHttpResponse.responseBuilder(type + " uploaded successfully", HttpStatus.CREATED, response);
        } else {
            return ResponseEntity.badRequest().body("Invalid file type.");
        }
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
                return ResponseEntity.ok()
                        .headers(headers)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
