package common.Busi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
        String str="subPages/activity/full-reduction/activity-list/activity-list?actId=321738";
        String str2="123456";
        String reg="actId=\\d+";
        Pattern P = Pattern.compile(reg);
        Matcher m =P.matcher(str);
        str = m.replaceAll(str2);
        System.out.println(str);
    }
}
