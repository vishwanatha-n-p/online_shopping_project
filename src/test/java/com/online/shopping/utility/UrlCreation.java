package com.online.shopping.utility;

import org.springframework.stereotype.Component;

@Component
public class UrlCreation {

    public String formUrlWithPort(String uri, int port) {
        return "http://localhost:" + port + uri;
    }

}
