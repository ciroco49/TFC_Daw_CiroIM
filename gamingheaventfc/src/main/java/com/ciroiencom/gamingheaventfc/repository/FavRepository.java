package com.ciroiencom.gamingheaventfc.repository;

import com.ciroiencom.gamingheaventfc.model.Fav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FavRepository extends JpaRepository<Fav, Long>, JpaSpecificationExecutor<Fav> {

}