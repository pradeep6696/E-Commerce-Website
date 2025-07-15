package com.ecom.productCatalog.config;

import com.ecom.productCatalog.model.Category;
import com.ecom.productCatalog.model.Product;
import com.ecom.productCatalog.repository.CategoryRepository;
import com.ecom.productCatalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {
        // Skip if products already exist
        if (productRepository.count() > 0) {
            System.out.println("✅ Products already exist. Skipping seeding.");
            return;
        }

        // Create and save categories
        Category electronics = new Category();
        electronics.setName("Electronics");

        Category clothing = new Category();
        clothing.setName("Clothing");

        Category books = new Category();
        books.setName("Books");

        List<Category> savedCategories = categoryRepository.saveAll(Arrays.asList(electronics, clothing, books));
        Category savedElectronics = savedCategories.get(0);
        Category savedClothing = savedCategories.get(1);
        Category savedBooks = savedCategories.get(2);

        // Create and save products
        Product phone = new Product("Smartphone", "Modern Android smartphone", new BigDecimal("30000"), "https://placehold.co/600x400/png", 10, savedElectronics);
        Product laptop = new Product("Laptop", "Work + gaming laptop", new BigDecimal("99000"), "https://placehold.co/600x400/png", 5, savedElectronics);

        Product tshirt = new Product("T-Shirt", "100% cotton", new BigDecimal("1999"), "https://placehold.co/600x400/png", 20, savedClothing);
        Product jeans = new Product("Jeans", "Comfort fit", new BigDecimal("3999"), "https://placehold.co/600x400/png", 15, savedClothing);

        Product novel = new Product("Novel", "Bestselling fiction novel", new BigDecimal("499"), "https://placehold.co/600x400/png", 25, savedBooks);
        Product guide = new Product("Study Guide", "Exam preparation guide", new BigDecimal("899"), "https://placehold.co/600x400/png", 30, savedBooks);

        productRepository.saveAll(List.of(phone, laptop, tshirt, jeans, novel, guide));

        System.out.println("✅ Sample products and categories seeded successfully.");
    }
}
