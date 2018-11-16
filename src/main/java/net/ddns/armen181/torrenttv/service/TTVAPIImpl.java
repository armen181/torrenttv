package net.ddns.armen181.torrenttv.service;

import com.google.gson.Gson;
import com.sun.jndi.toolkit.url.Uri;
import net.ddns.armen181.torrenttv.DTO.ScreenShotDTO;
import net.ddns.armen181.torrenttv.DTO.TTVAuth;
import net.ddns.armen181.torrenttv.DTO.TTVChannel;
import net.ddns.armen181.torrenttv.DTO.TranslationList;
import net.ddns.armen181.torrenttv.util.TTVType;
import net.ddns.armen181.torrenttv.util.URLParam;
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
    TTVAuth ttvAuth = null;


    @Override
    public TTVAuth login(String login, String Password) {
        if(ttvAuth == null|| ttvAuth.getSession().equals("")) {
            String targetURL = "http://1ttvapi.top/v3/auth.php";
            List<URLParam> urlParameters = new ArrayList<>();
            urlParameters.add(new URLParam("username", login));
            urlParameters.add(new URLParam("password", Password));
            urlParameters.add(new URLParam("application", "xbmc"));
            urlParameters.add(new URLParam("typeresult", "json"));
            urlParameters.add(new URLParam("guid", "6F9600DA-8B86-D591-B42D-00CF4FC154FF"));

            try {
                //ttvAuth = gson.fromJson(sendGet(targetURL, urlParameters),TTVAuth.class);
                //ttvAuth.setSession();
                ttvAuth = new TTVAuth(0,"",0,0,0,"X9eELT4Berg8cabuPExIYryY");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
            return ttvAuth;

    }

    @Override
    public TranslationList translationList(String sessionId, TTVType type) {

        String targetURL = "http://api.torrent-tv.ru/v3/translation_list.php";
        List<URLParam> urlParameters = new ArrayList<>();
        urlParameters.add(new URLParam("session", sessionId));
        urlParameters.add(new URLParam("type", type.toString()));
        urlParameters.add(new URLParam("typeresult", "json"));

        try {
            return gson.fromJson(sendGet(targetURL, urlParameters),TranslationList.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new TranslationList(0,null,null);
    }

    @Override
    public TTVChannel translationStreamById(String sessionId, int channelId) {
        String targetURL = "http://api.torrent-tv.ru/v3/translation_stream.php";
        List<URLParam> urlParameters = new ArrayList<>();
        urlParameters.add(new URLParam("session", sessionId));
        urlParameters.add(new URLParam("channel_id", String.valueOf(channelId)));
        urlParameters.add(new URLParam("typeresult", "json"));

        try {
            TTVChannel ttvChannel = gson.fromJson(sendGet(targetURL, urlParameters),TTVChannel.class);
            URIBuilder urlBuilder = new URIBuilder("");
            urlBuilder.setParameter("id", ttvChannel.getSource());
            ttvChannel.setSource(env.getProperty("server.url")+urlBuilder.toString().substring(4)+"/video.mp4");
            return ttvChannel;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new TTVChannel(0,"");
    }

    @Override
    public ScreenShotDTO translationScreen(String sessionId, int channelId) {
        String targetURL = "http://api.torrent-tv.ru/v3/translation_screen.php";

        List<URLParam> urlParameters = new ArrayList<>();
        urlParameters.add(new URLParam("session", sessionId));
        urlParameters.add(new URLParam("channel_id", String.valueOf(channelId)));
        urlParameters.add(new URLParam("typeresult", "json"));
        urlParameters.add(new URLParam("count", "5"));

        try {
            return gson.fromJson(sendGet(targetURL, urlParameters),ScreenShotDTO.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ScreenShotDTO(0,null);
    }

    @Override
    public String getSessionId() {
        return ttvAuth.getSession();
    }


    // HTTP GET request
    private String sendGet(String url, List<URLParam> Parameters) throws Exception {


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
    private void sendPost(String url, List<URLParam> Parameters) throws Exception {
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
