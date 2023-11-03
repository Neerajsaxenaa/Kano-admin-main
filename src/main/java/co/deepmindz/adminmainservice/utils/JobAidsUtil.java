package co.deepmindz.adminmainservice.utils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.deepmindz.adminmainservice.dto.JobAidsRequestDto;
import co.deepmindz.adminmainservice.dto.JobAidsResponse;
import co.deepmindz.adminmainservice.models.JobAids;
import co.deepmindz.adminmainservice.repository.JobAidsRepository;

@Service
public class JobAidsUtil {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private JobAidsRepository jobAidsRepository;

	private enum content_type {
		File, Text,Image
	};
	private String FILE_PATH_ROOT = "src/main/resources/static/";



	public JobAids mapRequestDtoToEntity(JobAidsRequestDto dto) throws IllegalStateException, IOException {

		JobAids jobAids = new JobAids();

		jobAids.setJobName(dto.getJobName());
		jobAids.setProfile_img(dto.getProfile_img());
		jobAids.setContent_type(dto.getContent_type());
		jobAids.setContent(dto.getContent());
		jobAids.setRole_id(dto.getRole_id());

		return jobAids;
	}

		

	public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) {
        byte[] image = new byte[0];
        try {
            image = FileUtils.readFileToByteArray(new File(FILE_PATH_ROOT+filename));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

	

	public ResponseEntity<Resource> downloadFile(String filename) {
		String imageDirectory = "src/main/resources/static/"; // Relative path within your main service
		String imageDirectory1 = "src/main/resources/pdf/";
		String filePath = imageDirectory + filename;
		String filePath1 = imageDirectory1 + filename;

		// Create a File representing the destination file
		File file = new File(filePath);
		File file1 = new File(filePath1);

		// Load the file as a resource
		Resource resource = new FileSystemResource(file);
		Resource resource1 = new FileSystemResource(file1);

		if (resource.exists()) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(filename).build());
			return ResponseEntity.ok().headers(headers).body(resource);
		} else if (resource1.exists()){
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(filename).build());
			return ResponseEntity.ok().headers(headers).body(resource1);
			}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	public List<JobAidsResponse> mapRequestDtoToEntityById(List<JobAids> jobAids) {
		List<JobAidsResponse> jobAidsResponse = new ArrayList<>();
		for (JobAids jobAids2 : jobAids) {
			jobAidsResponse.add(new JobAidsResponse(jobAids2.getJobid(), jobAids2.getJobName(), jobAids2.getRole_id(),
					jobAids2.getRead_duration(), jobAids2.getProfile_img(), jobAids2.getContent_type(),
					jobAids2.getContent()));
			}
		return jobAidsResponse;
	}

	public List<JobAidsResponse> updateJobAidsByRoleId(List<JobAidsResponse> jobAidsByRoleId,
			JobAidsRequestDto jobAidsRequestDto) {
		JobAids jobAids = new JobAids();
		jobAids.setJobid(jobAidsByRoleId.get(0).getRole_id());
		jobAids.setRole_id(jobAidsByRoleId.get(2).getRole_id());
		jobAids.setJobName(jobAidsRequestDto.getJobName());
		jobAids.setContent(jobAidsRequestDto.getContent());
		jobAids.setContent_type(jobAidsRequestDto.getContent_type());
		jobAids.setProfile_img(jobAidsRequestDto.getProfile_img());
		return mapRequestDtoToEntityById((List<JobAids>) jobAidsRepository.save(jobAids));

	}

	public List<JobAidsResponse> mapEntityToResponseDto(List<JobAids> jobAids) {
		List<JobAidsResponse> listOfDto = new ArrayList<>();
		for (JobAids jobAids1 : jobAids) {
			listOfDto.add(
					new JobAidsResponse(jobAids1.getJobid(), jobAids1.getJobName(), jobAids1.getRole_id(), jobAids1.getRead_duration(),
							jobAids1.getProfile_img(), jobAids1.getContent_type(), jobAids1.getContent()));

		}
		return listOfDto;

	}
	public JobAidsRequestDto saveFiles(MultipartFile identifier_img, MultipartFile content_file, String data)
			throws IllegalStateException, IOException {
		JobAidsRequestDto jobAidsRequest = null;
		String downloadUrl = null;
		String imageDirectory = "src/main/resources/static/";
		String currentDirectory = System.getProperty("user.dir");
		String fileName = StringUtils.cleanPath(identifier_img.getOriginalFilename());
		String fullImageDirectory = currentDirectory + "/" + imageDirectory;
		File directory = new File(fullImageDirectory);

		// Create the directory if it doesn't exist
		if (!directory.exists()) {
			if (directory.mkdirs()) {
				System.out.println("Directory created successfully.");
			} else {
				System.out.println("Failed to create directory.");
			}
		}
		File destinationFile = new File(fullImageDirectory, fileName);
		identifier_img.transferTo(destinationFile);
		String url = "/admin-main/job-aids/public-media/image/" + fileName; // Adjust the URL path as needed
		downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path(url).toUriString();
		jobAidsRequest = objectMapper.readValue(data, JobAidsRequestDto.class);
		jobAidsRequest.setProfile_img(downloadUrl);

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> datamap = objectMapper.readValue(data, new TypeReference<Map<String, String>>() {
		});
		String contentType = datamap.get("content_type");
		if (contentType.equalsIgnoreCase(content_type.File.name())) {
			String pdfURI = null;
			String imageDirectory1 = "src/main/resources/pdf/";
			String currentDirectory1 = System.getProperty("user.dir");
			String fileName1 = StringUtils.cleanPath(content_file.getOriginalFilename());
			String fullImageDirectory1 = currentDirectory1 + "/" + imageDirectory1;
			File directory1 = new File(fullImageDirectory1);

			// Create the directory if it doesn't exist
			if (!directory1.exists()) {
				if (directory1.mkdirs()) {
					System.out.println("Directory created successfully.");
				} else {
					System.out.println("Failed to create directory.");
				}}
			
			File destinationFile1 = new File(fullImageDirectory1, fileName1);
			content_file.transferTo(destinationFile1);
			String url1 = "/admin-main/job-aids/public-media/download/" + fileName1; // Adjust the URL path as needed
			pdfURI = ServletUriComponentsBuilder.fromCurrentContextPath().path(url1).toUriString();
//			System.out.println("pdf uri"+pdfURI);
			jobAidsRequest = objectMapper.readValue(data, JobAidsRequestDto.class);
			jobAidsRequest.setContent(pdfURI);
			jobAidsRequest.setProfile_img(downloadUrl);
		}else if (contentType.equalsIgnoreCase(content_type.Image.name())) {
			String pdfURI = null;
			String imageDirectory2 = "src/main/resources/static/";
			String currentDirectory2 = System.getProperty("user.dir");
			String fileName2 = StringUtils.cleanPath(content_file.getOriginalFilename());
			String fullImageDirectory2 = currentDirectory2 + "/" + imageDirectory2;
			File directory2 = new File(fullImageDirectory2);

			// Create the directory if it doesn't exist
			if (!directory2.exists()) {
				if (directory2.mkdirs()) {
					System.out.println("Directory created successfully.");
				} else {
					System.out.println("Failed to create directory.");
				}}
			
			File destinationFile1 = new File(fullImageDirectory2, fileName2);
			content_file.transferTo(destinationFile1);
			String url2 = "/admin-main/job-aids/public-media/image/" + fileName2; // Adjust the URL path as needed
			pdfURI = ServletUriComponentsBuilder.fromCurrentContextPath().path(url2).toUriString();
		//	System.out.println("pdf url" + pdfURI);
			jobAidsRequest = objectMapper.readValue(data, JobAidsRequestDto.class);
			jobAidsRequest.setContent(pdfURI);
			jobAidsRequest.setProfile_img(downloadUrl);
			
		}
		else if (contentType.equalsIgnoreCase(content_type.Text.name())) {
			jobAidsRequest.setContent(datamap.get("content"));
		}return jobAidsRequest;
		}
	
	
	
	
	
	
	



	
	



	
	
	
}
