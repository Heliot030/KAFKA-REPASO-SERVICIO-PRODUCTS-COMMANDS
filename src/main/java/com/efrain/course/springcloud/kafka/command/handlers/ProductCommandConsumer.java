package com.efrain.course.springcloud.kafka.command.handlers;

import com.efrain.course.springcloud.kafka.command.models.Command;
import com.efrain.course.springcloud.kafka.command.models.dto.ProductDto;
import com.efrain.course.springcloud.kafka.command.models.dto.Replay;
import com.efrain.course.springcloud.kafka.command.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Consumer;
import java.util.function.Function;


@Configuration
public class ProductCommandConsumer {

    private static final Logger log = LoggerFactory.getLogger(ProductCommandConsumer.class);

    private final ProductService service;

    public ProductCommandConsumer(ProductService productService) {
        this.service = productService;
    }

    @Bean
    public Function<Message<Command<ProductDto>>, Message<Replay<?>>> handleConsumer(){
        return  message-> {
             Command<ProductDto> command =  message.getPayload();
            String type = command.type() == null ? "" : command.type().toUpperCase();
            Replay<?> replay;
            switch (type) {
                case "CREATE" -> {
                    if (command.body() == null) {
                        log.warn("cretate Empty body");
                        replay = new Replay<>("ERROR", " Creado Cuerpo vacio" ,
                                null);
                    }
                    ProductDto productDto = command.body();
                    ProductDto productoSave = service.create(productDto);
                    log.info("Creando Producto : name={}, price={}",
                            productDto.name(), productDto.price());
                    replay =  new Replay<>("EXITO", " Creado CORRECTAMENTE" ,
                            productoSave);
                }
//                case "UPDATE" ->
//                    log.info("Actualizando Producto : name={}, price={}", null, null);
//                case "DELETE" ->
//                    log.info("Creando Producto");
                default -> {
                    log.warn("Comando desconocido type={}", type);
                    replay = new Replay<>("ERROR", "Respuesta Desconcida",null);
                }

            }
            String correlationId = message.getHeaders().get("CORRELACION-ID", String.class);
            log.info("CORRELACION-ID={}", correlationId);

            MessageBuilder<Replay<?>> out = MessageBuilder.withPayload(replay);
            if (correlationId != null){
                out.setHeader("CORRELACION-ID",correlationId);
            }
            return out.build();
        };
    }
}
