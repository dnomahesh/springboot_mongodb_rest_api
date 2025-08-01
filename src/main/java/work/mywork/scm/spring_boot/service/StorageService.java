package work.mywork.scm.spring_boot.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Stream;

public interface StorageService {

	void init();

	String store(MultipartFile file);

	Stream<String> loadAll();

	String load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();

}
