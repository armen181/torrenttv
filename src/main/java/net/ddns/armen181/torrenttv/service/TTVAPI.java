package net.ddns.armen181.torrenttv.service;

import net.ddns.armen181.torrenttv.DTO.ScreenShotDTO;
import net.ddns.armen181.torrenttv.DTO.TTVAuth;
import net.ddns.armen181.torrenttv.DTO.TTVChannel;
import net.ddns.armen181.torrenttv.DTO.TranslationList;
import net.ddns.armen181.torrenttv.util.TTVType;

public interface TTVAPI {
    TTVAuth login(String login, String Password);
    TranslationList translationList(String sessionId, TTVType type);
    TTVChannel translationStreamById(String sessionId, int channelId);
    ScreenShotDTO translationScreen (String sessionId, int channelId);
    String getSessionId();
}
