package co.deepmindz.adminmainservice.utils;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class CustomDataTypes {

	@AllArgsConstructor
	@Data
	@NoArgsConstructor
	@ToString
	static public class themes {
		public String key;
		public String description;
		public String color;
		public String url;
	}

	static public class keyValueObj {
		public String key;
		public List<valueObj> value;

		public keyValueObj(String key, List<valueObj> value) {
			this.key = key;
			this.value = value;
		}
	}

	static public class valueObj {
		public String id;
		public String lang;
		public String value;

		public valueObj(String id, String lang, String value) {
			this.id = id;
			this.lang = lang;
			this.value = value;
		}
	}

	static public class keyValuePair {
		public String key;
		public String value;

		public keyValuePair(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}

	static public class memberLimitObj {
		public String ISS_STATE;
		public String ISS_ZONAL;
		public String ISS_LGA;

		public memberLimitObj(String ISS_STATE, String ISS_ZONAL, String ISS_LGA) {
			this.ISS_STATE = ISS_STATE;
			this.ISS_ZONAL = ISS_ZONAL;
			this.ISS_LGA = ISS_LGA;
		}
	}

}
