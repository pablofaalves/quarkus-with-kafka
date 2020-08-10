package br.com.pfaa.quarkusstudy.application.config;

import br.com.pfaa.quarkusstudy.domain.port.ISendProductCreationEventPort;
import br.com.pfaa.quarkusstudy.domain.service.ProductService;
import br.com.pfaa.quarkusstudy.domain.port.IProductRespositoryPort;
import br.com.pfaa.quarkusstudy.domain.usecase.IProductUseCase;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.transaction.Transactional;


@Dependent
public class QuarkusConfig {

    @Produces
    @ApplicationScoped
    public ModelMapper modelMapper() {
        return new ModelMapperConfig().getModelMapper();
    }

    @Produces
    @ApplicationScoped
    @Transactional
    public IProductUseCase productDriverPort(IProductRespositoryPort respositoryDrivenPort,
                                             ISendProductCreationEventPort sendProductCreationEventPort) {
        return new ProductService(respositoryDrivenPort, sendProductCreationEventPort);
    }
}
