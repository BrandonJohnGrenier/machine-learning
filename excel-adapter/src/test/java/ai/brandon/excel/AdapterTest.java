package ai.brandon.excel;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public abstract class AdapterTest {

	public String path(String filename) {
		try {
			Resource resource = new ClassPathResource("excel/" + filename);
			return resource.getURL().getPath();
		}
		catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
