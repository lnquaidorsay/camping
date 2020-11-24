package com.camping.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.camping.entities.Bungalow;
import com.camping.repository.BungalowRepository;
import com.camping.service.BungalowService;

@Service
public class BungalowServiceImpl implements BungalowService {
	@Autowired
	private BungalowRepository bungalowRepository;

	@Override
	public Bungalow ajoutBungalow(Bungalow bungalow) {
		return bungalowRepository.save(bungalow);

	}

	@Override
	public Bungalow modifierBungalow(Bungalow bungalowDetail) {
		Bungalow bungalow = recupererBungalowDetail(bungalowDetail.getId());

		bungalow.setName(bungalowDetail.getName());
		bungalow.setDescription(bungalow.getDescription());
		bungalow.setCurrentPrice(bungalowDetail.getCurrentPrice());

		Bungalow updateBungalow = ajoutBungalow(bungalow);

		return updateBungalow;

	}

	@Override
	public List<Bungalow> listBungalow() {
		return bungalowRepository.findAll();
	}

	@Override
	public Bungalow recupererBungalowDetail(int id) {
		return bungalowRepository.findById(id).get();
	}

	@Override
	public Bungalow recupererBungalow(Bungalow bungalow) {
		return bungalowRepository.findById(bungalow.getId()).get();
	}

	@Override
	public void SupprimerBungalow(Bungalow bungalow) {
		bungalowRepository.deleteById(bungalow.getId());
	}

	@Override
	@Transactional
	public int enregistrerImage(MultipartFile file, int bungalowId, HttpSession session) {

		Path rootLocation = Paths.get(session.getServletContext().getRealPath("/resources/images"));
		System.out.println("rootLocation  ==  " + rootLocation);

		Path resourceDirectory = Paths.get("src", "test", "resources");

		Path resourceDirectory2 = Paths.get("src", "main", "resources", "images");
		String absolutePath1 = resourceDirectory.toFile().getAbsolutePath();
		String absolutePath2 = resourceDirectory2.toFile().getAbsolutePath();

		System.out.println(" absolutePath1 : !! => " + absolutePath1);

		System.out.println(" absolutePath2 : !! => " + absolutePath2);

		Bungalow bungalowDetail = this.recupererBungalowDetail(bungalowId);

		String nameExtension[] = file.getContentType().split("/");

		System.out.println("nameExtension length  :: " + nameExtension.length);

		// System.out.println("bungalowImage :: " + bungalowImage);

		String bungalowImage = bungalowId + "." + nameExtension[1];

		System.out.println("bungalowImage  :: " + bungalowImage);

		if (bungalowDetail.getId() > 0) {
			if (bungalowDetail.getPhotoName() == null || bungalowDetail.getPhotoName() == " ") {
				try {
					Files.copy(file.getInputStream(), resourceDirectory2.resolve(bungalowImage));
					int result = bungalowRepository.updateImage(bungalowId, bungalowImage);
					if (result > 0)
						return result;
					else
						return -5;
				} catch (Exception exception) {
					System.out.println("Error while updating profileImage from DAO :: " + exception.getMessage());
					return -5;
				}
			} else {
				try {
					// Files.delete(rootLocation.resolve(profileImage));

					Files.delete(rootLocation.resolve(bungalowDetail.getPhotoName()));

					Files.copy(file.getInputStream(), rootLocation.resolve(bungalowImage));
					// int result = userDAO.updateProfileImage(profileImage, userID);
					int result = bungalowRepository.updateImage(bungalowId, bungalowImage);
					if (result > 0)
						return result;
					else
						return -5;
				} catch (Exception exception) {
					System.out.println(
							"Error while uploading image when image is already Exists :: " + exception.getMessage());
					return -5;
				}
			}
		} else {
			return 0;
		}
		// return 0;
	}

}
