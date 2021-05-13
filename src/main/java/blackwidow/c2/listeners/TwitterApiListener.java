package blackwidow.c2.listeners;

import blackwidow.c2.domain.bl.AsyncArtifactListenerBl;
import blackwidow.c2.exceptions.TwitterListenerException;
import blackwidow.c2.exceptions.TwitterServiceException;
import blackwidow.c2.service.dto.IncomingArtifactDto;
import blackwidow.c2.services.TwitterService;
import blackwidow.c2.steganography.LsbDecoder;
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

    @Scheduled(fixedDelayString = "${TWITTER_LISTENER_EXECUTION_FIXED_DELAY}",
        initialDelayString = "${TWITTER_LISTENER_EXECUTION_INITIAL_DELAY}")
    @Override
    public void listen() throws TwitterServiceException {
        Collection<Status> tweets = twitter.getTweets(targetUsername);
        for (Status tweet : tweets) {
            try {
                IncomingArtifactDto artifact = extractArtifact(tweet, decoder, mapper);
                bl.processMessage(Long.toString(tweet.getId()), artifact);
            } catch (TwitterListenerException e) {
                e.printStackTrace();
            }
        }
    }

    public BufferedImage extractMedia(Status tweet) throws TwitterListenerException {
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
