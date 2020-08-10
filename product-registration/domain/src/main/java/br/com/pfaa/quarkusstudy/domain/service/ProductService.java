package br.com.pfaa.quarkusstudy.domain.service;

import br.com.pfaa.quarkusstudy.domain.entity.Product;
import br.com.pfaa.quarkusstudy.domain.exception.EntityNotFoundException;
import br.com.pfaa.quarkusstudy.domain.port.IProductRespositoryPort;
import br.com.pfaa.quarkusstudy.domain.port.ISendProductCreationEventPort;
import br.com.pfaa.quarkusstudy.domain.usecase.IProductUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductService implements IProductUseCase {

    private final IProductRespositoryPort repositoryPort;
    private final ISendProductCreationEventPort sendProductCreationEventPort;

    @Override
    public Product findById(final Long productId) {
        return findByIdOrElseThrow(productId);
    }

    @Override
    public List<Product> findAll() {
        return repositoryPort.findAll();
    }

    @Override
    public Product create(final Product product) {
        Product createdProduct = repositoryPort.create(product);
        sendProductCreationEventPort.sendCreationEvent(createdProduct);
        return createdProduct;
    }

    @Override
    public void update(final Product product) {
        findByIdOrElseThrow(product.getId());
        repositoryPort.update(product);
    }

    @Override
    public void delete(final Long productId) {
        Boolean isDeleted = repositoryPort.delete(productId);
        if (!isDeleted) {
            throw new EntityNotFoundException(productId,
                    Product.class.getSimpleName());
        }
    }

    private Product findByIdOrElseThrow(final Long productId) {
        return repositoryPort.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(productId,
                        Product.class.getSimpleName()));
    }
}
