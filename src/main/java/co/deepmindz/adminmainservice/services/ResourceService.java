package co.deepmindz.adminmainservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.deepmindz.adminmainservice.models.Resources;
import co.deepmindz.adminmainservice.repository.ResourceRepo;
import co.deepmindz.adminmainservice.resources.CustomHttpResponse;
import co.deepmindz.adminmainservice.utils.ResourceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
	

}
