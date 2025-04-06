package com.spotify.login;

import dev.failsafe.internal.util.Assert;

public class SpotifyLogin {

    public static void main(String[] args) {
        boolean message = LoginUtil.logIn("sidharthdas8794@gmail.com", "Sidharth@1234");
        Assert.isTrue(message, "Login failed");
    }
}
