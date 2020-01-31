package com.kkb.temperature.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author iisheng
 * @date 2019/11/26 20:30:24
 */
@Configuration
public class LocalDateTimeSerializerConfig {

    private String pattern = "yyyy-MM-dd HH:mm:ss";

    @Bean
    @Primary
    public ObjectMapper serializingObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

    public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            if (value != null) {
                long timestamp = value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                gen.writeNumber(timestamp);
            }
        }
    }

    public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext deserializationContext)
                throws IOException {
            long timestamp = p.getValueAsLong();
            if (timestamp > 0) {
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
            } else {
                return null;
            }
        }
    }
}
