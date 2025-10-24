package net.ecommerce.microservices.user.service;

import lombok.RequiredArgsConstructor;
import net.ecommerce.microservices.user.dto.ProductRequestDto;
import net.ecommerce.microservices.user.dto.ProductResponseDto;
import net.ecommerce.microservices.user.entity.Product;
import net.ecommerce.microservices.user.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepo;

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        updateProductFromRequest(product,productRequestDto);
        Product saved = productRepo.save(product);

        return mapToProductResponse(saved);

    }

    public ProductResponseDto updateProduct(Long id,ProductRequestDto productRequestDto){

       return productRepo.findById(id).map(existing->{
           updateProductFromRequest(existing,productRequestDto);
         Product savedProduct =productRepo.save(existing);
        return mapToProductResponse(savedProduct);
       }).orElseThrow(()->new RuntimeException("product not found :"    + id));

    }
    public List<ProductResponseDto> retrieveProduct() {
        return  productRepo.findAll().stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }


    public List<ProductResponseDto> retrieveActiveProduct() {
        return  productRepo.findByActiveTrue().stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    public void deleteProduct(Long id){
        Product product= productRepo.findById(id).orElseThrow(()->new RuntimeException("product not found: "+id));
        product.setActive(false);
        productRepo.save(product);
    }


    private ProductResponseDto mapToProductResponse(Product savedProduct) {
        ProductResponseDto response = new ProductResponseDto();
        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setActive(savedProduct.getActive());
        response.setCategory(savedProduct.getCategory());
        response.setDescription(savedProduct.getDescription());
        response.setPrice(savedProduct.getPrice());
        response.setImageUrl(savedProduct.getImageUrl());
        response.setStockQuantity(savedProduct.getStockQuantity());
        return response;

    }

    private void updateProductFromRequest(Product product, ProductRequestDto productRequest) {
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setImageUrl(productRequest.getImageUrl());
        product.setStockQuantity(productRequest.getStockQuantity());
    }


    public List<ProductResponseDto> searchProduct(String keyword) {
        return productRepo.searchProduct(keyword).stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }
}
