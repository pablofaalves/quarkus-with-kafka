package br.com.pfaa.quarkusstudy.domain.port;

import br.com.pfaa.quarkusstudy.domain.entity.Product;

public interface ISendProductCreationEventPort {
    void sendCreationEvent(Product product);
}
