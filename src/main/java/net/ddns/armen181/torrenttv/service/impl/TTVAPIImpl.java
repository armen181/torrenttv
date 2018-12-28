package net.ddns.armen181.torrenttv.service.impl;

import com.google.gson.Gson;
import net.ddns.armen181.torrenttv.DTO.ScreenShotDto;
import net.ddns.armen181.torrenttv.DTO.TTVAuthDto;
import net.ddns.armen181.torrenttv.DTO.TTVChannelDto;
import net.ddns.armen181.torrenttv.DTO.TranslationListDto;
import net.ddns.armen181.torrenttv.service.TTVAPI;
import net.ddns.armen181.torrenttv.util.TtvType;
import net.ddns.armen181.torrenttv.util.UrlParam;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class TTVAPIImpl implements TTVAPI {
    @Autowired
    private Environment env;

    private final String USER_AGENT = "Mozilla/5.0";
    Gson gson = new Gson();
    TTVAuthDto ttvAuthDto = null;


    @Override
    public TTVAuthDto login(String login, String Password) {
        if(ttvAuthDto == null|| ttvAuthDto.getSession().equals("")) {
            String targetURL = "http://1ttvapi.top/v3/auth.php";
            List<UrlParam> urlParameters = new ArrayList<>();
            urlParameters.add(new UrlParam("username", login));
            urlParameters.add(new UrlParam("password", Password));
            urlParameters.add(new UrlParam("application", "xbmc"));
            urlParameters.add(new UrlParam("typeresult", "json"));
            urlParameters.add(new UrlParam("guid", "6F9600DA-8B86-D591-B42D-00CF4FC154FF"));

            try {
                //ttvAuthDto = gson.fromJson(sendGet(targetURL, urlParameters),TTVAuthDto.class);
                //ttvAuthDto.setSession();
                ttvAuthDto = new TTVAuthDto(0,"",0,0,0,"X9eELT4Berg8cabuPExIYryY");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
            return ttvAuthDto;

    }

    @Override
    public TranslationListDto translationList(String sessionId, TtvType type) {

        String targetURL = "http://api.torrent-tv.ru/v3/translation_list.php";
        List<UrlParam> urlParameters = new ArrayList<>();
        urlParameters.add(new UrlParam("session", sessionId));
        urlParameters.add(new UrlParam("type", type.toString()));
        urlParameters.add(new UrlParam("typeresult", "json"));

        try {
            return gson.fromJson(sendGet(targetURL, urlParameters), TranslationListDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new TranslationListDto(0,null,null);
    }

    @Override
    public TTVChannelDto translationStreamById(String sessionId, int channelId) {
        String targetURL = "http://api.torrent-tv.ru/v3/translation_stream.php";
        List<UrlParam> urlParameters = new ArrayList<>();
        urlParameters.add(new UrlParam("session", sessionId));
        urlParameters.add(new UrlParam("channel_id", String.valueOf(channelId)));
        urlParameters.add(new UrlParam("typeresult", "json"));

        try {
            TTVChannelDto ttvChannelDto = gson.fromJson(sendGet(targetURL, urlParameters), TTVChannelDto.class);
           // URIBuilder urlBuilder = new URIBuilder("");
           // urlBuilder.setParameter("id", ttvChannelDto.getSource());
           // ttvChannelDto.setSource(env.getProperty("server.url")+urlBuilder.toString().substring(4)+"/video.mp4");
           return ttvChannelDto;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new TTVChannelDto(0,"");
    }

    @Override
    public ScreenShotDto translationScreen(String sessionId, int channelId) {
        String targetURL = "http://api.torrent-tv.ru/v3/translation_screen.php";

        List<UrlParam> urlParameters = new ArrayList<>();
        urlParameters.add(new UrlParam("session", sessionId));
        urlParameters.add(new UrlParam("channel_id", String.valueOf(channelId)));
        urlParameters.add(new UrlParam("typeresult", "json"));
        urlParameters.add(new UrlParam("count", "5"));

        try {
            return gson.fromJson(sendGet(targetURL, urlParameters), ScreenShotDto.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ScreenShotDto(0,null);
    }

    @Override
    public String getSessionId() {
        return ttvAuthDto.getSession();
    }


    // HTTP GET request
    private String sendGet(String url, List<UrlParam> Parameters) throws Exception {


        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder urlBuilder = new URIBuilder(url);
        Parameters.forEach(x -> urlBuilder.setParameter(x.getName(), x.getValue()));
        HttpGet request = new HttpGet(urlBuilder.build());

        // add request header
        request.addHeader("UserCustom-Agent", USER_AGENT);

        HttpResponse response = client.execute(request);

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());
        return result.toString();

    }

    // HTTP POST request
    private void sendPost(String url, List<UrlParam> Parameters) throws Exception {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder urlBuilder = new URIBuilder(url);
        Parameters.forEach(x -> {
            urlBuilder.setParameter(x.getName(), x.getValue());
        });
        HttpPost request = new HttpPost(urlBuilder.build());

        // add header
        request.setHeader("UserCustom-Agent", USER_AGENT);

        HttpResponse response = client.execute(request);
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());

    }


}
