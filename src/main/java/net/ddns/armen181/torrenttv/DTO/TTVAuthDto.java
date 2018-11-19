package net.ddns.armen181.torrenttv.DTO;

import lombok.Data;

@Data
public class TTVAuthDto {
    private int success;
    private String error;
    private int balance;
    private int tsproxy_date;
    private int tsproxy_access;
    private String session;

    public TTVAuthDto(int success, String error, int balance, int tsproxy_date, int tsproxy_access, String session) {
        this.success = success;
        this.error = error;
        this.balance = balance;
        this.tsproxy_date = tsproxy_date;
        this.tsproxy_access = tsproxy_access;
        this.session = session;
    }
}
