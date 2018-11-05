package net.ddns.armen181.torrenttv.service;

import com.google.gson.Gson;
import net.ddns.armen181.torrenttv.DTO.TTVAuth;
import net.ddns.armen181.torrenttv.util.TTVType;
import net.ddns.armen181.torrenttv.util.URLParam;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class TTVAPIImpl implements TTVAPI {
    private final String USER_AGENT = "Mozilla/5.0";
    Gson gson = new Gson();
    TTVAuth ttvAuth = null;


    @Override
    public String login(String login, String Password) {
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
            return ttvAuth.getSession();

    }

    @Override
    public String translationList(String sessionId, TTVType type) {
        String targetURL = "http://api.torrent-tv.ru/v3/translation_list.php";
        List<URLParam> urlParameters = new ArrayList<>();
        urlParameters.add(new URLParam("session", sessionId));
        urlParameters.add(new URLParam("type", type.toString()));
        urlParameters.add(new URLParam("typeresult", "json"));

        try {
            return sendGet(targetURL, urlParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    private String sendGet(String url, List<URLParam> Parameters) throws Exception {


        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder urlBuilder = new URIBuilder(url);
        Parameters.forEach(x -> {
            urlBuilder.setParameter(x.getName(), x.getValue());
        });
        HttpGet request = new HttpGet(urlBuilder.build());

        // add request header
        request.addHeader("User-Agent", USER_AGENT);

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
        request.setHeader("User-Agent", USER_AGENT);

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
