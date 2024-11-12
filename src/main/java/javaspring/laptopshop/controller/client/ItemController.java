package javaspring.laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javaspring.laptopshop.domain.Product;
import javaspring.laptopshop.service.ProductService;

@Controller
public class ItemController {
    private final ProductService productService;

    ItemController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String getMethodName(Model model, @PathVariable long id) {
        Product productItem = this.productService.getProductById(id);
        model.addAttribute("productItem", productItem);
        return "/client/product/product-detail";
    }

}
