package com.group21.tour_reservation.utils;

public class StringUtils {


    public static Integer getIdFromSlug (String slug) {
        try {
            String [] parts = slug.split("-");
            Integer id  = Integer.parseInt(parts[parts.length-1]);
            return id;
        } catch (NumberFormatException e)  {
            return -1;
        }
    }

}
