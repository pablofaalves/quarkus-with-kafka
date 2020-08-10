package br.com.pfaa.quarkusstudy.respository.adapter;

import br.com.pfaa.quarkusstudy.domain.entity.Product;
import br.com.pfaa.quarkusstudy.domain.port.ISendProductCreationEventPort;
import br.com.pfaa.quarkusstudy.event.kafka.SendProductStockKafkaEvent;
import br.com.pfaa.quarkusstudy.event.model.avro.NewProductNotification;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@RequiredArgsConstructor
@ApplicationScoped
public class ProductStockAdapter implements ISendProductCreationEventPort {

    @Inject
    SendProductStockKafkaEvent event;

    @Override
    public void sendCreationEvent(Product product) {
        event.sendProductCreationEvent(NewProductNotification.newBuilder()
                .setProductId(product.getId())
                .build());
    }
}
