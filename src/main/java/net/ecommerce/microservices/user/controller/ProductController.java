package net.ecommerce.microservices.user.controller;

import lombok.RequiredArgsConstructor;
import net.ecommerce.microservices.user.dto.ProductRequestDto;
import net.ecommerce.microservices.user.dto.ProductResponseDto;
import net.ecommerce.microservices.user.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return new ResponseEntity<>(productService.createProduct(productRequestDto), HttpStatus.CREATED);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
        return new ResponseEntity<>(productService.updateProduct(id, productRequestDto), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> retrieveProduct() {
        List<ProductResponseDto> responseDto = productService.retrieveProduct();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @GetMapping("/active")
    public ResponseEntity<List<ProductResponseDto>> retrieveActiveProduct() {
        List<ProductResponseDto> responseDto = productService.retrieveActiveProduct();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<String>deleteProduct(@PathVariable  Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product Deleted",HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>>searchProduct(@RequestParam String keyword){
        List<ProductResponseDto> saved=productService.searchProduct(keyword);
        return new ResponseEntity<>(saved,HttpStatus.OK);
    }
}
