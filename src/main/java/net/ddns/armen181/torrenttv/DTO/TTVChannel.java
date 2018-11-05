package net.ddns.armen181.torrenttv.DTO;

import lombok.Data;

@Data
public class TTVChannel {
    private int success;
    //private String error;
   // private String type;
    private String source;

    public TTVChannel(int success, String source) {
        this.success = success;
       // this.error = error;
        //this.type = type;
        this.source = source;
    }
}
