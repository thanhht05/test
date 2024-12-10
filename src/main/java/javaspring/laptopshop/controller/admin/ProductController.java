package javaspring.laptopshop.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javaspring.laptopshop.domain.Product;
import javaspring.laptopshop.service.ProductService;
import javaspring.laptopshop.service.UploadService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product")
    public String getMethodName(Model model, @RequestParam("page") Optional<String> pageOptional) {
        int page = 1;

        // handle exception

        try {
            if (pageOptional.isPresent()) {
                page = Integer.parseInt(pageOptional.get());
            } else {
                // page still =1
            }

        } catch (Exception e) {
            // handle exception
        }

        Pageable pageable = PageRequest.of(page - 1, 2);
        Page<Product> products = this.productService.getAllProduct(pageable);

        List<Product> listProducts = products.getContent();
        model.addAttribute("products", listProducts);
        model.addAttribute("currentPage", page);

        model.addAttribute("totalPages", products.getTotalPages());
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create-product")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create-product";
    }

    @PostMapping("/admin/product/create-product")
    public String createProductPage(Model model, @ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProductBindingResult,
            @RequestParam("productFile") MultipartFile file) throws IOException {

        if (newProductBindingResult.hasErrors()) {
            return "/admin/product/create-product";
        }
        String img = this.uploadService.handleUploadFile(file, "product");
        product.setImage(img);
        this.productService.handleSaveProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/update-product/{id}")
    public String getUpdateProductPage(Model model, @PathVariable long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/update-product";
    }

    @PostMapping("/admin/product/update-product")
    public String updateProductPage(Model model, @ModelAttribute("product") Product product,
            @RequestParam("productFile") MultipartFile file) throws IOException {
        Product updateProduct = this.productService.getProductById(product.getId());
        if (updateProduct != null) {
            updateProduct.setName(product.getName());
            updateProduct.setDetailDesc(product.getDetailDesc());
            updateProduct.setFactory(product.getFactory());
            updateProduct.setTarget(product.getTarget());
            updateProduct.setQuantity(product.getQuantity());
            updateProduct.setShortDesc(product.getShortDesc());
            updateProduct.setFactory(product.getFactory());
            updateProduct.setPrice(product.getPrice());
            if (!file.isEmpty()) {

                String img = this.uploadService.handleUploadFile(file, "product");
                updateProduct.setImage(img);
            }

        }

        this.productService.handleSaveProduct(updateProduct);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/product-detail/{id}")
    public String getMethodName(Model model, @PathVariable long id) {
        Product productItem = this.productService.getProductById(id);
        model.addAttribute("product", productItem);

        return "admin/product/product-detail";
    }

    @GetMapping("/admin/product/delete-product/{id}")
    public String getDeleteProductPage(Model model, @PathVariable long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/delete-product";
    }

    @PostMapping("/admin/product/delete-product")
    public String postMethodName(Model model, @ModelAttribute("product") Product product) {
        this.productService.deleteProductBId(product.getId());
        return "redirect:/admin/product";
    }

}
