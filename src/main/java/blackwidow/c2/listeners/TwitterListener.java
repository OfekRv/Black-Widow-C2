package blackwidow.c2.listeners;

import blackwidow.c2.exceptions.TwitterServiceException;
import twitter4j.Status;

public interface TwitterListener extends MediaListener<Status> {
    void listen() throws TwitterServiceException;
}
