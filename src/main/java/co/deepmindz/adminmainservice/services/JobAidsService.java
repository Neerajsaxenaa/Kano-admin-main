package co.deepmindz.adminmainservice.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import co.deepmindz.adminmainservice.dto.JobAidsRequestDto;
import co.deepmindz.adminmainservice.dto.JobAidsResponse;

@Service
public interface JobAidsService {
	
	

		public ResponseEntity<Resource> downloadFile(String filename);

		public JobAidsResponse createJob(JobAidsRequestDto dto) throws IllegalStateException, IOException;

		public List<JobAidsResponse> getAllJobAidsByRoleId(String role_id);
		
//	public JobAidsResponse updateJobAidsByRoleId(String role_id,JobAidsRequestDto jobAidsRequestDto,JobAidsResponse jobAidsResponse);

		public List<JobAidsResponse> getAllJobs();

		public List<JobAidsResponse> updateJobAidsByRoleId(List<JobAidsResponse> jobAidsByRoleId, JobAidsRequestDto saveFile);

//		JobAidsResponse updateJobAidsByRoleId(String role_id, JobAidsRequestDto saveFile);
		public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename);

}




