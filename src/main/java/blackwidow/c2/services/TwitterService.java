package blackwidow.c2.services;

import blackwidow.c2.exceptions.TwitterServiceException;
import twitter4j.Status;

import java.util.Collection;

public interface TwitterService {
    Collection<Status> getTweets(String username) throws TwitterServiceException;
}
