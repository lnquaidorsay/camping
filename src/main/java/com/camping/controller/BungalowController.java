package com.camping.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.camping.entities.Bungalow;
import com.camping.service.BungalowService;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
//@CrossOrigin("http://localhost:4200")
public class BungalowController {
	@Autowired
	private BungalowService bungalowService;

	@GetMapping("/bungalows")
	public List<Bungalow> listeBungalows() {
		return bungalowService.listBungalow();
	}

	// @PostMapping("/bungalow")
	// @CrossOrigin("http://localhost:4200")
	@PostMapping(value = "/bungalow", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public Bungalow ajouterBungalow(@RequestBody Bungalow bungalow) {
		return bungalowService.ajoutBungalow(bungalow);
	}

	@GetMapping("/bungalow/{id}")
	public Bungalow recupereBungalowParSonId(@PathVariable("id") int id) {
		return bungalowService.recupererBungalowDetail(id);
	}

	@PostMapping("/uploadImage/{bungalowId}")
	public int handleFileUpload(@PathVariable int bungalowId, @RequestParam("file") MultipartFile file,
			HttpSession session) {
		return bungalowService.enregistrerImage(file, bungalowId, session);
	}

	// https://www.javatpoint.com/angular-spring-file-upload-example : front

}
