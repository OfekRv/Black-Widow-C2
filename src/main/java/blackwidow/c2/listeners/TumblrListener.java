package blackwidow.c2.listeners;

import blackwidow.c2.exceptions.TwitterListenerException;
import blackwidow.c2.exceptions.TwitterServiceException;
import com.tumblr.jumblr.types.Post;

public interface TumblrListener extends MediaListener<Post> {
    void listen() throws TwitterServiceException, TwitterListenerException;
}
