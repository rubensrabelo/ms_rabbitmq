package io.github.rubensrabelo.ms.product.application;

import io.github.rubensrabelo.ms.product.application.dto.ProductCreateDTO;
import io.github.rubensrabelo.ms.product.application.dto.ProductResponseDTO;
import io.github.rubensrabelo.ms.product.application.dto.ProductUpdateDTO;
import io.github.rubensrabelo.ms.product.application.exceptions.DatabaseException;
import io.github.rubensrabelo.ms.product.application.exceptions.ResourceNotFoundException;
import io.github.rubensrabelo.ms.product.domain.Product;
import io.github.rubensrabelo.ms.product.infra.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(
            ProductRepository productRepository,
            ModelMapper modelMapper
    ) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public Page<ProductResponseDTO> findAll(Pageable pageable) {
        Page<Product> entities = productRepository.findAll(pageable);
        return entities.map(product -> modelMapper.map(product, ProductResponseDTO.class));
    }

    public ProductResponseDTO findById(Long id) {
        Product entity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found."));
        return modelMapper.map(entity, ProductResponseDTO.class);
    }

    public ProductResponseDTO create(ProductCreateDTO dtoCreate) {
        Product entity = modelMapper.map(dtoCreate, Product.class);
        entity = productRepository.save(entity);
        return modelMapper.map(entity, ProductResponseDTO.class);
    }

    public ProductResponseDTO update(Long id, ProductUpdateDTO dtoUpdate) {
        Product entity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found."));
        updateData(entity, dtoUpdate);
        entity = productRepository.save(entity);
        return modelMapper.map(entity, ProductResponseDTO.class);
    }

    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Product not found.");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(Product entity, ProductUpdateDTO dtoUpdate) {
        entity.setName(dtoUpdate.getName());
        entity.setUnitPrice(dtoUpdate.getUnitPrice());
    }
}
