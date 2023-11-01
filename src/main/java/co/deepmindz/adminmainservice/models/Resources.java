package co.deepmindz.adminmainservice.models;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "resources")
public class Resources {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@UuidGenerator
	private String resourceId;
	private String type;
	private String name;
	private String url;

	public Resources() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Resources(String resourceId, String type, String name, String url) {
		super();
		this.resourceId = resourceId;
		this.type = type;
		this.name = name;
		this.url = url;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
