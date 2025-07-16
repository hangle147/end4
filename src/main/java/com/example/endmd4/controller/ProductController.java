package com.example.endmd4.controller;

import com.example.endmd4.Service.ProductService;
import com.example.endmd4.model.Product;
import com.example.endmd4.repository.ProductTypeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeRepository productTypeRepository;


    @GetMapping
    public String listProducts(Model model,
                               @RequestParam(defaultValue = "") String name,
                               @RequestParam(defaultValue = "0") double price,
                               @RequestParam(required = false) Long typeId,
                               @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Product> productPage = productService.search(name, price, typeId, pageable);

        model.addAttribute("products", productPage);
        model.addAttribute("types", productTypeRepository.findAll());
        model.addAttribute("name", name);
        model.addAttribute("price", price);
        model.addAttribute("typeId", typeId);

        return "product/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("types", productTypeRepository.findAll());
        return "product/create";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        model.addAttribute("types", productTypeRepository.findAll());
        return "product/create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", productTypeRepository.findAll());
            return "product/create";
        }
        product.setStatus("Chờ duyệt");
        productService.save(product);
        return "redirect:/products";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("ids") List<Long> ids) {
        for (Long id : ids) {
            Product product = productService.findById(id);
            if (product != null) {
                productService.deleteById(product);
            }
        }
        return "redirect:/products";
    }
}