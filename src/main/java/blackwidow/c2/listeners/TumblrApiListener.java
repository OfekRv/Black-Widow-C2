package blackwidow.c2.listeners;

import blackwidow.c2.domain.bl.AsyncArtifactListenerBl;
import blackwidow.c2.exceptions.TwitterListenerException;
import blackwidow.c2.exceptions.TwitterServiceException;
import blackwidow.c2.service.dto.IncomingArtifactDto;
import blackwidow.c2.services.TwitterService;
import blackwidow.c2.steganography.LsbDecoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.PhotoPost;
import com.tumblr.jumblr.types.Post;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class TumblrApiListener implements TumblrListener {
    private static final int FIRST_MEDIA = 0;
    private static final int ORIGINAL_SIZE = 0;
    public static final String DATE_PATTERN = "yyyy-mm-dd hh:mm:ss GMT";

    @Inject
    private AsyncArtifactListenerBl bl;
    @Inject
    private TwitterService twitter;
    @Inject
    private LsbDecoder decoder;
    @Inject
    private ObjectMapper mapper;

    @Value("${TUMBLER_BLOG}")
    private String targetBlog;
    @Value("${TUMBLER_MAX_POSTS_PULL}")
    private int maxPostsPull;
    @Value("${TUMBLER_CONSUMER_KEY}")
    private String consumerKey;
    @Value("${TUMBLER_CONSUMER_SECRET}")
    private String consumerSecret;
    @Value("${TUMBLER_TOKEN}")
    private String token;
    @Value("${TUMBLER_TOKEN_SECRET}")
    private String tokenSecret;

    @Scheduled(fixedDelayString = "${TUMBLER_LISTENER_EXECUTION_FIXED_DELAY}",
        initialDelayString = "${TUMBLER_LISTENER_EXECUTION_INITIAL_DELAY}")
    @Override
    public void listen() throws TwitterServiceException, TwitterListenerException {

        List<Post> posts = generateClient().blogPosts(targetBlog, generateCallParams());

        for (Post post : posts) {
            try {
                IncomingArtifactDto artifact = extractArtifact(post, decoder, mapper);
                ZonedDateTime time = ZonedDateTime.ofInstant
                    (Instant.ofEpochSecond(post.getTimestamp()), ZoneId.systemDefault());
                bl.processMessage(Long.toString(post.getId()), time, artifact);
            } catch (TwitterListenerException e) {
                e.printStackTrace();
            }
        }
    }

    public BufferedImage extractMedia(Post post) throws TwitterListenerException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpget = new HttpGet(extractMediaUrl(post));
            try (CloseableHttpResponse response = httpclient.execute(httpget);
                 InputStream stream = response.getEntity().getContent()) {
                return ImageIO.read(stream);
            }
        } catch (IOException e) {
            throw new TwitterListenerException("An error occurred during url read", e);
        }
    }

    private String extractMediaUrl(Post post) {
        return ((PhotoPost) post).getPhotos().get(FIRST_MEDIA).getSizes().get(ORIGINAL_SIZE).getUrl();
    }

    private Map<String, Object> generateCallParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("type", "photo");
        params.put("limit", maxPostsPull);
        return params;
    }

    private JumblrClient generateClient() {
        JumblrClient client = new JumblrClient(consumerKey, consumerSecret);
        client.setToken(token, tokenSecret);
        return client;
    }
}
