package co.deepmindz.adminmainservice.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.deepmindz.adminmainservice.dto.BrandImagesResponseDto;
import co.deepmindz.adminmainservice.models.Resources;
import co.deepmindz.adminmainservice.repository.ResourceRepo;
import co.deepmindz.adminmainservice.utils.ResourceUtil;

@Service
public class ResourceService {

	@Autowired
	private ResourceRepo resourceRepo;

	@Autowired
	private ResourceUtil resourceUtil;

	public Resources save(Resources resources) {
		return resourceRepo.save(resources);
	}

	public List<Resources> getResources() {
		return resourceRepo.getStaticAppResources();
	}

	public ResponseEntity<Object> fileUploadFuction(MultipartFile file, String type) throws IOException {
		return resourceUtil.fileUploadFuction(file, type);

	}

	public Resources findByType(String type) {
		Resources findByType = resourceRepo.findByType(type);
		return findByType;
	}

	public BrandImagesResponseDto findAllImages() {
		List<Resources> findAll = resourceRepo.findAll();
		if (findAll==null || findAll.isEmpty()) {
			return null;
		}
		BrandImagesResponseDto brandImagesResponseDto = resourceUtil.mapEntityToResponseDto(findAll);
		return brandImagesResponseDto;
		
	}
	

}
