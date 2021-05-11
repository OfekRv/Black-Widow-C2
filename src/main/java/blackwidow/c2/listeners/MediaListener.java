package blackwidow.c2.listeners;

import blackwidow.c2.exceptions.BlackWidowException;
import blackwidow.c2.exceptions.TwitterListenerException;
import blackwidow.c2.service.dto.IncomingArtifactDto;
import blackwidow.c2.steganography.Decoder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.image.BufferedImage;

public interface MediaListener<E> {
    void listen() throws BlackWidowException;

    BufferedImage extractMedia(E entity) throws TwitterListenerException;

    default IncomingArtifactDto extractArtifact(E entity, Decoder<BufferedImage, String> decoder, ObjectMapper mapper) throws TwitterListenerException {
        String message = decoder.decode(extractMedia(entity));
        return parseMessageToArtifact(message, mapper);
    }

    default IncomingArtifactDto parseMessageToArtifact(String message, ObjectMapper mapper) throws TwitterListenerException {
        IncomingArtifactDto artifact;
        try {
            artifact = mapper.readValue(message, IncomingArtifactDto.class);
        } catch (JsonProcessingException e) {
            throw new TwitterListenerException("Could not parse message", e);
        }

        return artifact;
    }
}
