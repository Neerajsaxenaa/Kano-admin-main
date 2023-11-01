package co.deepmindz.adminmainservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.stereotype.Service;

import co.deepmindz.adminmainservice.models.Resources;
import co.deepmindz.adminmainservice.repository.ResourceRepo;

import java.util.List;

@Service
public class ResourceService {

	@Autowired
	private ResourceRepo resourceRepo;
	public Resources save(Resources resources) {
		return resourceRepo.save(resources);
		
		
	}

	public List<Resources> getResources() {
		return resourceRepo.getStaticAppResources();
	}

}
