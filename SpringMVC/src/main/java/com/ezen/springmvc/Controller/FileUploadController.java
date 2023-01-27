package com.ezen.springmvc.Controller;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class FileUploadController {
	
	// /views/upload/form.jsp�� �����ϰ� ����� �޼���
	@GetMapping(value="/fileupload/form") //������ �� ���� �ּ�
	public String form() {
		return "upload/form"; //������ġ
	}
	
	
	//���� ���ε� �޼���
	@PostMapping("/fileupload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
		
		/*
		 * HttpServletRequest request
		ServletContext app = request.getServletContext();
		
		log.info("real path : " + app.getRealPath("/"));
		�̷��� ������ ��������
		real path : 
		C:\JavaAWS\spring-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringMVC\
		��� �ߴµ� ���������⿡ ������ �ؾ� �Ѵٰ� �� �� �ְ���
		
		������ ������ �ö�´ٰ� �� ��, �ƴϸ� ������ ���� ÷��
		webapp �� resources (���������� �����ϴ� resources) �� �ƴ� �ٸ� ��ġ�� �����ؾ߰���
		�� ��ġ�� �����Ӱ� ������ �� �ִµ� 
		
		���� ������ �� realpath�� ����ϴ� ������ 
		realpath���� �ٸ� ����� �ٷﺸ����
		*/
		
		// "/target/files"�� �� �־ �� ����. ����η� �ؾ� �ȴ�(...?)
 		//Path rootLocation = Paths.get("/target/files/");
		Path rootLocation = Paths.get("target/files/" + Math.abs(new Random().nextLong()));
		log.info("rootLocation : " + rootLocation);
		log.info("abs rootLocation : " + rootLocation.toAbsolutePath());
		
		if(file.isEmpty()) {
			log.error("����ִ� ������ ������ �� �����ϴ�.");
			return "redirect:/message";
		}
		
		
		log.info("Paths.get(file.getOriginalFilename()) : " + Paths.get(file.getOriginalFilename()));
		
		//circle.png
		log.info("file.getOriginalFilename() : " + file.getOriginalFilename());
		
		// Path.resolve() : �ش� Path�κ��� �Ѱ��� ��� ��θ� ������ �ν��Ͻ��� ��ȯ
		//Path destinationFile = rootLocation.resolve("/message/123");
		//��� �ڿ� /message/123�� ���� �� ��ȯ
		//Path destinationFile = rootLocation.resolve("../message/123");
		//log.info("file location : " + destinationFile);
		//file name : \target\files\..\message\123
		
		
		//�н�.normalize() : �ش� �н��� ./ , ../ ���� ��� ��ε��� �ּ�ȭ�� ����� ��ȯ (./ ../ �� ������ ����� ��ȯ�Ѵٴ� ��)
		//���� �̸��� ��ġ�� �ʵ��� �տ� �ִ��� ��ġ�� �ʴ� ���ڸ� �־��ִ� ���� ����.
		// UUID Ŭ������ ����� ���� �ִ�. ��¥ �������ϸ� �� ��ħ. Long���� �ξ� ���� Ȯ���� ��ħ. 
		UUID uuid = UUID.randomUUID(); //�����ĺ���
		Path destinationFile = rootLocation.resolve(
									Paths.get(uuid + "_" +  
									file.getOriginalFilename()).normalize());
		
		log.info("file name : " + destinationFile);
		//file name : \target\message\123
		
		//���� ����
		try(InputStream in = file.getInputStream()) {
			//createDirectories() : nio�� mkdirs()�� ��. �ش� ��ο� �ʿ��� ��� ���� ���丮���� ����
			Files.createDirectories(destinationFile);
			// ������ �״�� ����.  , �ڿ� �� �ߺ� �� �ɼ�
			Files.copy(in, destinationFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		
//		Path destinationFile = rootLocation.resolve(
//					Paths.get(file.getOriginalFilename()));
//		log.info("destinationFile : " + destinationFile);		
		
		
		
		log.info("file : " + file);
		log.info(file.getOriginalFilename());
		log.info("file size : " + file.getSize() + "Byte(s)");
		
		//return "redirect:/"; 
		return "redirect:/fileupload/"; //�Ƴ� �̷��� �ϴϱ� ��. �� �ڿ� /�߰������ ��¼�� �ǳ���
	}
	
	
	
	
	//���� ����Ʈ ����
	@GetMapping("/fileupload/")
	public String listUploadedFiles(Model model) {
		
		
		List<File> files = new ArrayList<>();
		
		File rootLocation = new File("target/files/");
		for (File f : rootLocation.listFiles()) {
			
			if(f.isDirectory()) {
				for(File f2 : f.listFiles()) {
					files.add(f2);
				}
			} else {
				files.add(f);
			}
		}
		
		//��Ʈ�ѷ����� ��Ʈ����Ʈ�� ���� �� model �� Ȱ���� �� �ִ�. request ��� model. request�ᵵ �Ǳ� ��.
		model.addAttribute("files", files);
		
		return "upload/list";
	}
	
	
	//��ũ ������ ���� �ٿ�ޱ�
		//�� �� �� ��ũ Ŭ���ϸ� /fileupload ���� ����;;;;
	@GetMapping("/fileupload/target/files/{parent}/{filename:.+}") //����ǥ�������� .+�� ����, �� �� �̻��� �ǹ� 
	//@GetMapping("/target/files/{parent}/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(
			@PathVariable String parent, @PathVariable String filename) throws Exception {
		
		log.info("parent folder : " + parent);
		log.info("file name : " + filename);
		
		
		Path file = Paths.get("target/files/").resolve(parent).resolve(filename);
		
		try {
			Resource resource = new UrlResource(file.toUri());
			//������ �ٿ������� �� ���� ����� content-disposition �׸��� attachment�� �ٲ�� �Ѵ�.
			//ok()�� ���������̴� ��� ��. 
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"hello.jpg\"")
					.body(resource);	
		} catch (MalformedURLException e) {
//			e.printStackTrace();
//			throw new Exception("���� �ٿ�ε� �� ���� �߻�");
			return (ResponseEntity<Resource>) ResponseEntity.notFound();
		}
		
		

	}
	
	
	
	
}
