package net.ddns.armen181.torrenttv.util;

import lombok.Data;

@Data
public class UrlParam {
    private String name;
    private String value;

    public UrlParam(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
