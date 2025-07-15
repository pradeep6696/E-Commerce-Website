package com.ecom.productCatalog.service;
import com.ecom.productCatalog.model.Product;
import com.ecom.productCatalog.model.User;
import com.ecom.productCatalog.model.wishlist;
import com.ecom.productCatalog.repository.ProductRepository;
import com.ecom.productCatalog.repository.UserRepository;
import com.ecom.productCatalog.repository.wishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class wishlistService {
    @Autowired
    private wishlistRepo wishlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public wishlist addToWishlist(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        wishlist wishlist = wishlistRepository.findByUser(user).orElseGet(() -> {
            wishlist newWishlist = new wishlist();
            newWishlist.setUser(user);
            return newWishlist;
        });

        List<Product> products = wishlist.getProducts();
        if (!products.contains(product)) {
            products.add(product);
        }

        wishlist.setProducts(products);
        return wishlistRepository.save(wishlist);
    }

    public wishlist getWishlist(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return wishlistRepository.findByUser(user).orElseThrow();
    }
}
