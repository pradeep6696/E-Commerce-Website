package com.ecom.productCatalog.repository;

import com.ecom.productCatalog.model.wishlist;
import com.ecom.productCatalog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface wishlistRepo extends JpaRepository<wishlist, Long>{
    Optional<wishlist> findByUser(User user);
}
