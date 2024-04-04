package org.cataloguemicroservice.web;

import lombok.AllArgsConstructor;
import org.cataloguemicroservice.app.CategoryApp;
import org.cataloguemicroservice.app.ProductApp;
import org.cataloguemicroservice.dtos.ProductDetailsDTO;
import org.cataloguemicroservice.services.IProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final CategoryApp categoryApp;
    private final ProductApp productApp;
    private final IProductService iProductService;

    @GetMapping("/{productSlug}")
    public ProductDetailsDTO getProductBySlug(@PathVariable("productSlug") String productSlug) {
        return productApp.getProductBySlug(productSlug);
    }
}
