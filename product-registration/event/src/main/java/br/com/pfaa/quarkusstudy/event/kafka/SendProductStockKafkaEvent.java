package br.com.pfaa.quarkusstudy.event.kafka;

import br.com.pfaa.quarkusstudy.event.model.avro.NewProductNotification;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SendProductStockKafkaEvent {

    @Inject
    @Channel("create-product-stock")
    Emitter<NewProductNotification> kafkaEmitter;

    public void sendProductCreationEvent(NewProductNotification notification) {
        kafkaEmitter.send(notification);
    }
}
