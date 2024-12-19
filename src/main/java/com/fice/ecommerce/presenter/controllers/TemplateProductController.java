package com.fice.ecommerce.presenter.controllers;

import com.fice.ecommerce.application.ProductService;
import com.fice.ecommerce.application.context.product.ProductContext;
import com.fice.ecommerce.domain.Product;
import com.fice.ecommerce.presenter.dto.product.ProductRequestDto;
import com.fice.ecommerce.presenter.dto.product.ProductResponseDto;
import com.fice.ecommerce.presenter.mapper.DtoProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*") // Allow CORS for all origins
public class TemplateProductController {

    private final ProductService productService;
    private final DtoProductMapper dtoProductMapper;

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getProducts();
        List<ProductResponseDto> productDtos = dtoProductMapper.toDto(products);
        model.addAttribute("products", productDtos);
        return "products";
    }

    @GetMapping("/new")
    public String newProductForm(Model model) {
        model.addAttribute("productRequestDto", new ProductRequestDto(null, null, null, null, List.of()));
        return "products";
    }

    @PostMapping("/new")
    public String createProduct(@ModelAttribute @Valid ProductRequestDto productRequestDto,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productRequestDto", productRequestDto);
            return "products";
        }
        ProductContext productContext = dtoProductMapper.toContext(productRequestDto);
        productService.createProduct(productContext);
        return "redirect:/products";
    }

    @GetMapping("/edit/{code}")
    public String editProductForm(@PathVariable String code, Model model) {
        Product product = productService.getProductByCode(code);
        ProductResponseDto productDto = dtoProductMapper.toDto(product);
        model.addAttribute("productToEdit", productDto);
        return "products";
    }

    @PostMapping("/edit/{code}")
    public String updateProduct(@PathVariable String code,
                                @ModelAttribute @Valid ProductRequestDto productRequestDto,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productToEdit", productRequestDto);
            return "products";
        }
        ProductContext productContext = dtoProductMapper.toContext(productRequestDto);
        productService.updateProduct(code, productContext);
        return "redirect:/products";
    }

    @PostMapping("/delete/{code}")
    public String deleteProduct(@PathVariable String code) {
        productService.deleteProduct(code);
        return "redirect:/products";
    }
}
