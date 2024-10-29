package javaspring.laptopshop.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javaspring.laptopshop.domain.Product;
import javaspring.laptopshop.service.UploadService;
import javaspring.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProductController {
    private final UserService userService;
    private final UploadService uploadService;

    public ProductController(UserService userService, UploadService uploadService) {
        this.userService = userService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product")
    public String getMethodName(Model model) {
        List<Product> products = this.userService.getAllProduct();
        model.addAttribute("products", products);

        return "admin/product/show";
    }

    @GetMapping("/admin/product/create-product")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create-product";
    }

    @PostMapping("/admin/product/create-product")
    public String createProductPage(Model model, @ModelAttribute("newProduct") Product product,
            @RequestParam("productFile") MultipartFile file) throws IOException {

        String img = this.uploadService.handleUploadFile(file, "product");
        product.setImage(img);
        this.userService.handleSaveProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/update-product/{id}")
    public String getUpdateProductPage(Model model, @PathVariable long id) {
        Product product = this.userService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/update-product";
    }

    @PostMapping("/admin/product/update-product")
    public String updateProductPage(Model model, @ModelAttribute("product") Product product) {
        Product updateProduct = this.userService.getProductById(product.getId());
        if (updateProduct != null) {
            updateProduct.setName(product.getName());
            updateProduct.setDetailDesc(product.getDetailDesc());
            updateProduct.setFactory(product.getFactory());
            updateProduct.setTarget(product.getTarget());
            updateProduct.setQuantity(product.getQuantity());
            updateProduct.setShortDesc(product.getShortDesc());
            updateProduct.setFactory(product.getFactory());
            updateProduct.setPrice(product.getPrice());
        }

        this.userService.handleSaveProduct(updateProduct);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/product-detail/{id}")
    public String getMethodName(Model model, @PathVariable long id) {
        Product productItem = this.userService.getProductById(id);
        model.addAttribute("product", productItem);

        return "admin/product/product-detail";
    }

    @GetMapping("/admin/product/delete-product/{id}")
    public String getDeleteProductPage(Model model, @PathVariable long id) {
        Product product = this.userService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/delete-product";
    }

    @PostMapping("/admin/product/delete-product")
    public String postMethodName(Model model, @ModelAttribute("product") Product product) {
        this.userService.deleteProductBId(product.getId());
        return "redirect:/admin/product";
    }

}
