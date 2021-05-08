package blackwidow.c2.services;

import blackwidow.c2.exceptions.TwitterServiceException;
import org.springframework.beans.factory.annotation.Value;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.inject.Named;
import java.util.Collection;

@Named
public class TwitterApiService implements TwitterService {
    @Value("${TWITTER_CONSUMER_KEY}")
    private String twitterConsumerKey;
    @Value("${TWITTER_CONSUMER_SECRET}")
    private String twitterConsumerSecret;
    @Value("${TWITTER_ACCESS_TOKEN}")
    private String twitterAccessToken;
    @Value("${TWITTER_ACCESS_TOKEN_SECRET}")
    private String twitterAccessTokenSecret;

    @Override
    public Collection<Status> getTweets(String username) throws TwitterServiceException {
        try {
            return getTwitterInstance().getUserTimeline(username);
        } catch (TwitterException e) {
            throw new TwitterServiceException("Could not retrieve " + username + " timeline", e);
        }
    }

    private Twitter getTwitterInstance() {
        ConfigurationBuilder twitterConfigBuilder = new ConfigurationBuilder();
        twitterConfigBuilder.setOAuthConsumerKey(twitterConsumerKey);
        twitterConfigBuilder.setOAuthConsumerSecret(twitterConsumerSecret);
        twitterConfigBuilder.setOAuthAccessToken(twitterAccessToken);
        twitterConfigBuilder.setOAuthAccessTokenSecret(twitterAccessTokenSecret);
        return new TwitterFactory(twitterConfigBuilder.build()).getInstance();
    }
}
