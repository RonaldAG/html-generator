package services;

import java.io.IOException;

public interface ApiClient {
    String getBody() throws IOException, InterruptedException;

}
