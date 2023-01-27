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
	
	// /views/upload/form.jsp로 접속하게 만드는 메서드
	@GetMapping(value="/fileupload/form") //접속할 때 쓰는 주소
	public String form() {
		return "upload/form"; //파일위치
	}
	
	
	//파일 업로드 메서드
	@PostMapping("/fileupload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
		
		/*
		 * HttpServletRequest request
		ServletContext app = request.getServletContext();
		
		log.info("real path : " + app.getRealPath("/"));
		이러고 파일을 보내보면
		real path : 
		C:\JavaAWS\spring-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringMVC\
		라고 뜨는데 파일을여기에 저장을 해야 한다고 볼 수 있것지
		
		프로필 사진이 올라온다고 할 때, 아니면 간단한 파일 첨부
		webapp 의 resources (웹페이지를 구성하는 resources) 가 아닌 다른 위치를 지정해야겠지
		그 위치는 자유롭게 지정할 수 있는데 
		
		제일 괜찮은 건 realpath를 사용하는 거지만 
		realpath말고 다른 방법을 다뤄보겠음
		*/
		
		// "/target/files"로 돼 있어서 안 됐음. 상대경로로 해야 된대(...?)
 		//Path rootLocation = Paths.get("/target/files/");
		Path rootLocation = Paths.get("target/files/" + Math.abs(new Random().nextLong()));
		log.info("rootLocation : " + rootLocation);
		log.info("abs rootLocation : " + rootLocation.toAbsolutePath());
		
		if(file.isEmpty()) {
			log.error("비어있는 파일을 저장할 수 없습니다.");
			return "redirect:/message";
		}
		
		
		log.info("Paths.get(file.getOriginalFilename()) : " + Paths.get(file.getOriginalFilename()));
		
		//circle.png
		log.info("file.getOriginalFilename() : " + file.getOriginalFilename());
		
		// Path.resolve() : 해당 Path로부터 넘겨준 상대 경로를 적용한 인스턴스를 반환
		//Path destinationFile = rootLocation.resolve("/message/123");
		//경로 뒤에 /message/123을 붙인 걸 반환
		//Path destinationFile = rootLocation.resolve("../message/123");
		//log.info("file location : " + destinationFile);
		//file name : \target\files\..\message\123
		
		
		//패스.normalize() : 해당 패스의 ./ , ../ 같은 상대 경로들을 최소화한 결과를 반환 (./ ../ 를 실행한 결과를 반환한다는 것)
		//파일 이름이 겹치지 않도록 앞에 최대한 겹치지 않는 숫자를 넣어주는 것이 좋다.
		// UUID 클래스를 사용할 수도 있다. 진짜 애지간하면 안 겹침. Long보다 훨씬 낮은 확률로 겹침. 
		UUID uuid = UUID.randomUUID(); //고유식별자
		Path destinationFile = rootLocation.resolve(
									Paths.get(uuid + "_" +  
									file.getOriginalFilename()).normalize());
		
		log.info("file name : " + destinationFile);
		//file name : \target\message\123
		
		//파일 쓰기
		try(InputStream in = file.getInputStream()) {
			//createDirectories() : nio의 mkdirs()인 셈. 해당 경로에 필요한 모든 상위 디렉토리들을 생성
			Files.createDirectories(destinationFile);
			// 파일을 그대로 복사.  , 뒤에 건 중복 시 옵션
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
		return "redirect:/fileupload/"; //아나 이렇게 하니까 됨. 맨 뒤에 /추가해줘야 어쩌고 되나봄
	}
	
	
	
	
	//파일 리스트 띄우기
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
		
		//컨트롤러에서 어트리뷰트를 실을 때 model 을 활용할 수 있다. request 대신 model. request써도 되긴 함.
		model.addAttribute("files", files);
		
		return "upload/list";
	}
	
	
	//링크 눌러서 파일 다운받기
		//엥 난 왜 링크 클릭하면 /fileupload 빼고 나옴;;;;
	@GetMapping("/fileupload/target/files/{parent}/{filename:.+}") //정규표현식으로 .+는 모든것, 한 개 이상을 의미 
	//@GetMapping("/target/files/{parent}/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(
			@PathVariable String parent, @PathVariable String filename) throws Exception {
		
		log.info("parent folder : " + parent);
		log.info("file name : " + filename);
		
		
		Path file = Paths.get("target/files/").resolve(parent).resolve(filename);
		
		try {
			Resource resource = new UrlResource(file.toUri());
			//파일을 다운받으라고 할 때는 헤더의 content-disposition 항목을 attachment로 바꿔야 한다.
			//ok()는 정상응답이다 라는 뜻. 
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"hello.jpg\"")
					.body(resource);	
		} catch (MalformedURLException e) {
//			e.printStackTrace();
//			throw new Exception("파일 다운로드 중 문제 발생");
			return (ResponseEntity<Resource>) ResponseEntity.notFound();
		}
		
		

	}
	
	
	
	
}
