package utils;

import sun.java2d.pipe.SpanIterator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 小许同学
 * @date 2022/1/11 10:23
 **/
public class StringUtils {
    /**
    * @author  小许同学
     * @param param
    * @return {@line {@link boolean}}
    * @date 2022/1/11 10:24
    * 字符串有长度
    */
    public static boolean hasLength(String param , int length ){
        if (param !=null && param.trim().length()>length)return true;
        return false;
    }
    public static boolean hasLength(String param ){
        if (param !=null && param.trim().length()>0) return true;
        return false;
    }
    /**
    * @author  小许同学
     * @param param  邮箱号
    * @return {@line {@link boolean}}
    * @date 2022/1/12 9:36
    * 判断字符串是否为邮箱号
    */
    public static boolean isEmail(String param){
        String regex = "[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\\.[a-zA-Z0-9]+)+";
        return  param.matches(regex);
    }
    private static final String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };

    //生成n位的随机码
    private static String generateShortUuid(int length) {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < length; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
    * @author  小许同学
     * @param msg  标识 例如 user
     * @param length 随机数的位数
    * @return {@line {@link String}} 唯一id
    * @date 2022/1/13 16:55
    * user20210101xxxxxxxxx   标识  日期 随机码
    */
    public static String createOnlyId(String msg,int length){
        SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMdd");
        String onlyId = msg + dfm.format(new Date()) + generateShortUuid(length);
        return onlyId;
    }
    public static  boolean isId(String flag,String idStr){
        if (!hasLength(idStr))return false; //空字符串 直接失败
        int length = flag.length();
        String userflag = idStr.substring(0, length); //开头的标识
        if(userflag.equals(flag)){
            //用户标识标识成功
            String dateString = idStr.substring(length, length + 8);
            String yyyy = dateString.substring(0, 4);
            String MM = dateString.substring(4, 6);
            String dd = dateString.substring(6, 8);
           //System.out.println(yyyy+"-"+MM+"-"+dd);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date parse = format.parse(yyyy+"-"+MM+"-"+dd);
                //这边是日期校验成功

                String substring = idStr.substring(length + 9);
                return hasLength(substring);  //这边是日期之后还有数据即可算是正确
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    //shop20220119cdap



     static AtomicInteger atomicInteger = new AtomicInteger(1000);
     static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String CreateOrderId(String ShopId){

        StringBuffer sBuffer = new StringBuffer();
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        int intFlag = Integer.parseInt(format.substring(format.lastIndexOf("-")+1))*1000 ;
       //System.out.println(intFlag);

        if(intFlag > atomicInteger.get()){
            atomicInteger.set(intFlag);
        }else {
            //日期比
            int i =  intFlag/ 1000;
            int j = atomicInteger.get()/1000;
            if (i < j){ //后者日期大于 前者
                atomicInteger.set(intFlag);
            }

        }
       // System.out.println(intFlag);
        sBuffer.append(ShopId.substring(8)+"-");  //日期+4位店铺标识  0119cdap
        sBuffer.append(date.getTime()+"-");
        sBuffer.append(generateShortUuid(2)+"-");
        sBuffer.append(atomicInteger.getAndIncrement());

        return sBuffer.toString();
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100 ; i++) {

            System.out.println( CreateOrderId("shop20220119cdap"));
        }

    }

}
