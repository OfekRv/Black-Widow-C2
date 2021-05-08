package blackwidow.c2.listeners;

import blackwidow.c2.exceptions.TwitterServiceException;

public interface TwitterListener {
    void listen() throws TwitterServiceException;
}
