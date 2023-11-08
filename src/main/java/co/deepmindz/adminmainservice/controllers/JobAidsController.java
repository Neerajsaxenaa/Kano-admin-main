package co.deepmindz.adminmainservice.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.deepmindz.adminmainservice.dto.JobAidsResponse;
import co.deepmindz.adminmainservice.exception.ResourceNotFoundException;
import co.deepmindz.adminmainservice.repository.JobAidsRepository;
import co.deepmindz.adminmainservice.resources.CustomHttpResponse;
import co.deepmindz.adminmainservice.services.JobAidsService;
import co.deepmindz.adminmainservice.utils.JobAidsUtil;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin-main/job-aids")
public class JobAidsController {
	@Autowired
	JobAidsService jobAidsService;

	@Autowired
	JobAidsRepository jobAidsRepository;

	@Autowired
	JobAidsUtil jobAidsUtil;



	@GetMapping("/public-media/download/{filename:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
		try {
			return jobAidsService.downloadFile(filename);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/getall-job-aids/{role_id}")
	public ResponseEntity<Object> getAllJobAidsByRoleId(@Valid @PathVariable("role_id") String role_id)
			throws ResourceNotFoundException {
		List<JobAidsResponse> jobAidsById = jobAidsService.getAllJobAidsByRoleId(role_id);
		if (jobAidsById != null) {
			return CustomHttpResponse.responseBuilder("jobAids with roleId :" + role_id, HttpStatus.OK, jobAidsById);

		} else {
			return CustomHttpResponse.responseBuilder("jobAids with roleId " + role_id + "not found..!",
					HttpStatus.OK, jobAidsById);

		}
	}

//	@PostMapping("/update-job-aids/{role_id}")
//	public ResponseEntity<Object> updateJobAidsByRoleId(@Valid @PathVariable String role_id,
//			@RequestParam(required = true, value = "identifier_img") MultipartFile identifier_img,
//
//			@RequestParam(required = false, value = "content_file") MultipartFile content_file,
//			@RequestParam(required = true, value = "data") String data) throws IllegalStateException, IOException {
//
//		List<JobAidsResponse> jobAidsByRoleId = jobAidsService.getAllJobAidsByRoleId(role_id);
//		if (jobAidsByRoleId != null) {
//		List<JobAidsResponse> updateJobAidsByRoleId = jobAidsService.updateJobAidsByRoleId(jobAidsByRoleId,
//					jobAidsUtil.saveFile(identifier_img, content_file, data));
//			if (updateJobAidsByRoleId != null) {
//				return CustomHttpResponse.responseBuilder("JobAids sucessfully Updated", HttpStatus.OK,
//						updateJobAidsByRoleId);
//			} else {
//				return CustomHttpResponse.responseBuilder("Same JobAids Already present please update JobAids",
//						HttpStatus.ALREADY_REPORTED, updateJobAidsByRoleId);
//			}
//		} else {
//			return CustomHttpResponse.responseBuilder("JobAids Not found", HttpStatus.NOT_FOUND, null);
//		}
//	}

	@GetMapping("/getall-job-aids")
	public ResponseEntity<Object> getAllJobAids() throws ResourceNotFoundException {
		List<JobAidsResponse> getJob = jobAidsService.getAllJobs();
		if (getJob == null || getJob.size() == 0)
			return CustomHttpResponse.responseBuilder("JobAids Not Found", HttpStatus.OK, getJob);

		return CustomHttpResponse.responseBuilder("All available JobAids", HttpStatus.OK, getJob);
	}

	@GetMapping("/public-media/image/{filename}")
	public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) {
		try {
			return jobAidsService.getImage(filename);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping(value = "/add-job-aids", consumes = { org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
			org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Object> createJobAidsForImageView(
			@RequestParam(required = true, value = "identifier_img") MultipartFile identifier_img,
			@RequestParam(required = false, value = "content_file") MultipartFile content_file,
			@RequestParam(required = true, value = "data") String data) throws IOException {

		JobAidsResponse createJobAidsResponse = jobAidsService
				.createJob(jobAidsUtil.saveFiles(identifier_img, content_file, data));
		if (createJobAidsResponse != null) {
			return CustomHttpResponse.responseBuilder("Job_Aids has been created", HttpStatus.OK,
					createJobAidsResponse);
		} else {
			return CustomHttpResponse.responseBuilder("Job_Aids already exists Failed to create Job_Aids",
					HttpStatus.ALREADY_REPORTED, null);
		}

	}

}
