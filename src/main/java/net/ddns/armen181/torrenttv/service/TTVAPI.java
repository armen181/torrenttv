package net.ddns.armen181.torrenttv.service;

import net.ddns.armen181.torrenttv.DTO.ScreenShotDto;
import net.ddns.armen181.torrenttv.DTO.TTVAuthDto;
import net.ddns.armen181.torrenttv.DTO.TTVChannelDto;
import net.ddns.armen181.torrenttv.DTO.TranslationListDto;
import net.ddns.armen181.torrenttv.util.TtvType;

public interface TTVAPI {
    TTVAuthDto login(String login, String Password);
    TranslationListDto translationList(String sessionId, TtvType type);
    TTVChannelDto translationStreamById(String sessionId, int channelId);
    ScreenShotDto translationScreen (String sessionId, int channelId);
    String getSessionId();
}
