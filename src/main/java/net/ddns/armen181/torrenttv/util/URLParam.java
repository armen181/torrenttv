package net.ddns.armen181.torrenttv.util;

import lombok.Data;

@Data
public class URLParam {
    private String name;
    private String value;

    public URLParam(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
