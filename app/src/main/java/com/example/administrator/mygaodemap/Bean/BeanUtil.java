package com.example.administrator.mygaodemap.Bean;

/**
 * Created by John on 2017/4/19.
 */

public class BeanUtil {

    private static Ticket ticket;

    public static Ticket getTicket() {
        if (ticket==null){
            ticket=new Ticket();
        }
        return ticket;
    }

    public static void setTicket(Ticket ticket) {
        BeanUtil.ticket = ticket;
    }
}
