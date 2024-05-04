package org.cataloguemicroservice.web;

import lombok.AllArgsConstructor;
import org.cataloguemicroservice.app.ProductApp;
import org.cataloguemicroservice.dtos.ProductDTO;
import org.cataloguemicroservice.dtos.ProductDetailsDTO;
import org.cataloguemicroservice.services.IProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductApp productApp;
    private final IProductService productService;


    @GetMapping("/")
    private List<ProductDTO> getAllCategories() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productSlug}")
    public ProductDetailsDTO getProductBySlug(@PathVariable("productSlug") String productSlug) {
        return productApp.getProductBySlug(productSlug);
    }
}
