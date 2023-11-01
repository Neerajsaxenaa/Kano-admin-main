package co.deepmindz.adminmainservice.services.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;

import co.deepmindz.adminmainservice.dto.JobAidsRequestDto;
import co.deepmindz.adminmainservice.dto.JobAidsResponse;
import co.deepmindz.adminmainservice.models.JobAids;
import co.deepmindz.adminmainservice.repository.JobAidsRepository;
import co.deepmindz.adminmainservice.services.JobAidsService;
import co.deepmindz.adminmainservice.utils.JobAidsUtil;

@Service
public class JobAidsServiceImpl implements JobAidsService {

	@Autowired
	JobAidsRepository jobAidsRepository;

	@Autowired
	JobAidsUtil jobAidsUtil;

	@Override
	public JobAidsResponse createJobs(JobAidsRequestDto dto) throws IllegalStateException, IOException {
		JobAids jobAids = jobAidsUtil.mapRequestDtoToEntity(dto);
		JobAids createJobAids = jobAidsRepository.save(jobAids);
		return new JobAidsResponse(createJobAids.getJobid(), createJobAids.getJobName(), createJobAids.getRole_id(),
				createJobAids.getRead_duration(), createJobAids.getProfile_img(), createJobAids.getContent_type(),
				createJobAids.getContent());
	}
	

	@Override
	public ResponseEntity<Resource> downloadFile(String filename) {
		return jobAidsUtil.downloadFile(filename);

	}
	
	

	@Override
	public List<JobAidsResponse> getAllJobAidsByRoleId(String role_id) {
		List<JobAids> alljobAids = jobAidsRepository.getAllJobAidsByRoleId(role_id);
		if (alljobAids.isEmpty()) {
			return null;
		}
		return jobAidsUtil.mapRequestDtoToEntityById(alljobAids);

	}

//	@Override
//	public JobAidsResponse updateJobAidsByRoleId(String role_id, JobAidsRequestDto jobAidsRequestDto,
//			JobAidsResponse jobAidsResponse) {
//		Optional<JobAids> findByRoleId = jobAidsRepository.getAllJobAidsByRoleId(role_id);
//		System.out.println("findByRoleId : "+findByRoleId);
//		if(findByRoleId.isPresent()) {
//			JobAidsResponse updateJobAidsByRoleId = jobAidsUtil.updateJobAidsByRoleId(role_id, jobAidsRequestDto);
//			return updateJobAidsByRoleId;
//		}
//		else {
//			return null;
//		}
//	}

	@Override
	public List<JobAidsResponse> getAllJobs() {
		List<JobAids> findAllJobAids = jobAidsRepository.findAll();
		if (findAllJobAids.isEmpty())
			return null;
		return jobAidsUtil.mapEntityToResponseDto(findAllJobAids);

	}

	@Override
	public List<JobAidsResponse> updateJobAidsByRoleId(List<JobAidsResponse> jobAidsByRoleId, JobAidsRequestDto saveFile){
		Optional<JobAids> findById = jobAidsRepository.findById(jobAidsByRoleId.get(0).getJobid());
		if (findById != null) {
			List<JobAidsResponse> updateJobAidsByRoleId = (List<JobAidsResponse>) jobAidsUtil.updateJobAidsByRoleId(jobAidsByRoleId, saveFile);
			return updateJobAidsByRoleId;
		} else {
			return null;
		}
	}

	public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) {
		return jobAidsUtil.getImage(filename);
		
	}
	@Override
	public JobAidsResponse createJob(JobAidsRequestDto dto) throws IllegalStateException, IOException {
		JobAids jobAids = jobAidsUtil.mapRequestDtoToEntity(dto);
		JobAids createJobAids = jobAidsRepository.save(jobAids);
		return new JobAidsResponse(createJobAids.getJobid(), createJobAids.getJobName(), createJobAids.getRole_id(),
				createJobAids.getRead_duration(), createJobAids.getProfile_img(), createJobAids.getContent_type(),
				createJobAids.getContent());
	}
	

	


	
	

	
	

}
