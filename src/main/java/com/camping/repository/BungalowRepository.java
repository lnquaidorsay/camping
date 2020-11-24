package com.camping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.camping.entities.Bungalow;

@Repository
public interface BungalowRepository extends JpaRepository<Bungalow, Integer> {
	List<Bungalow> findByNameContaining(String name);

	@Query("SELECT b FROM Bungalow b WHERE b.id = ?1")
	Bungalow chercherBungalow();

//	@Query("update Bungalow b set u.photoName = :phone where u.id = :id")
//	Long updateImage(String imageName, Long id);

	@Modifying(clearAutomatically = true)
	@Query("update Bungalow b set b.photoName = :image where b.id = :id")
	int updateImage(@Param(value = "id") int id, @Param(value = "image") String image);

//	bungalowImage  :: 5.jpeg
//	Error while updating profileImage from DAO :: C:\Users\nlelo\AppData\Local\Temp\tomcat-docbase.8181.5569096893170875118\resources\images\5.jpeg
//	Hibernate: insert into bungalow (category_id, current_price, description, name, photo_name) values (?, ?, ?, ?, ?)
//	rootLocation  ==  C:\Users\nlelo\AppData\Local\Temp\tomcat-docbase.8181.5569096893170875118\resources\images
//	Hibernate: select bungalow0_.id as id1_0_0_, bungalow0_.category_id as category6_0_0_, bungalow0_.current_price as current_2_0_0_, bungalow0_.description as descript3_0_0_, bungalow0_.name as name4_0_0_, bungalow0_.photo_name as photo_na5_0_0_, category1_.id as id1_1_1_, category1_.name as name2_1_1_ from bungalow bungalow0_ left outer join category category1_ on bungalow0_.category_id=category1_.id where bungalow0_.id=?
//	bungalowImage  :: 6.jpeg
//	Error while updating profileImage from DAO :: C:\Users\nlelo\AppData\Local\Temp\tomcat-docbase.8181.5569096893170875118\resources\images\6.jpeg

}
