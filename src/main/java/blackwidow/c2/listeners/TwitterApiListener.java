package blackwidow.c2.listeners;

import blackwidow.c2.domain.bl.AsyncArtifactListenerBl;
import blackwidow.c2.exceptions.TwitterListenerException;
import blackwidow.c2.exceptions.TwitterServiceException;
import blackwidow.c2.service.dto.IncomingArtifactDto;
import blackwidow.c2.services.TwitterService;
import blackwidow.c2.steganography.LsbDecoder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import twitter4j.Status;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;

@Slf4j
@Named
public class TwitterApiListener implements TwitterListener {
    private static final int FIRST_MEDIA = 0;

    @Inject
    private AsyncArtifactListenerBl bl;
    @Inject
    private TwitterService twitter;
    @Inject
    private LsbDecoder decoder;
    @Inject
    private ObjectMapper mapper;

    @Value("${TWITTER_USERNAME}")
    private String targetUsername;

    @Scheduled(fixedDelayString = "${LISTENER_EXECUTION_FIXED_DELAY}",
        initialDelayString = "${LISTENER_EXECUTION_INITIAL_DELAY}")
    @Override
    public void listen() throws TwitterServiceException {
        Collection<Status> tweets = twitter.getTweets(targetUsername);
        for (Status tweet : tweets) {
            try {
                String message = decoder.decode(extractMedia(tweet));
                bl.processMessage(Long.toString(tweet.getId()), parseMessageToArtifact(message));
            } catch (TwitterListenerException e) {
               // log.error("Could not process message " + tweet.getId(), e);
            }
        }
    }

    private IncomingArtifactDto parseMessageToArtifact(String message) throws TwitterListenerException {
        IncomingArtifactDto artifact;
        try {
            artifact = mapper.readValue(message, IncomingArtifactDto.class);
        } catch (JsonProcessingException e) {
            throw new TwitterListenerException("Could not parse message", e);
        }

        return artifact;
    }

    private BufferedImage extractMedia(Status tweet) throws TwitterListenerException {
        try {
            return ImageIO.read(new URL(extractMediaUrl(tweet)));
        } catch (IOException e) {
            throw new TwitterListenerException("An error occurred during url read", e);
        }
    }

    private String extractMediaUrl(Status tweet) {
        return tweet.getMediaEntities()[FIRST_MEDIA].getMediaURL();
    }
}
