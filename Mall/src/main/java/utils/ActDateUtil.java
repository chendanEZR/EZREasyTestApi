package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ActDateUtil {
//获取当前时间
    public static String getCurrentTime(){
        Date date = new Date();
        SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String t =fDate.format(date);
        return t;
    }
//获取一个小时后的时间
    public static String getTimeAfterOneHour(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY,1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(calendar.getTime());
        return time;

    }

    public static String getTimeAfterOneMin(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE,1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(calendar.getTime());
        return time;

    }

    public static String getTimeBeforeOneMin(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE,1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(calendar.getTime());
        return time;

    }
//获取当前时间的时间戳
    public static String getCurrentTimeStamp(){
        Date date = new Date();
        //System.out.println(date);
        SimpleDateFormat fDate = new SimpleDateFormat("yyyyMMddHHmmss");
        String time =fDate.format(date);
        return  time;
    }

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat fDate = new SimpleDateFormat("yyyyMMddHHmmss");
        String t =fDate.format(date);
        System.out.println(t);
    }
}
