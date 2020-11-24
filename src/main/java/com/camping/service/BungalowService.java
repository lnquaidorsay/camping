package com.camping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.camping.entities.Bungalow;

public interface BungalowService {

	public Bungalow ajoutBungalow(Bungalow bungalow);

	public Bungalow modifierBungalow(Bungalow bungalowDetail);

	public Bungalow recupererBungalowDetail(int id);

	public Bungalow recupererBungalow(Bungalow bungalow);

	public List<Bungalow> listBungalow();

	public void SupprimerBungalow(Bungalow bungalow);

	public int enregistrerImage(MultipartFile file, int bungalowId, HttpSession session);

}
