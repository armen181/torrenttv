package net.ddns.armen181.torrenttv.service;

import net.ddns.armen181.torrenttv.util.TTVType;

public interface TTVAPI {
    String login(String login,String Password);
    String translationList(String sessionId, TTVType type);
}
