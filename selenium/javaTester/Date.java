package javaTester;

public class Date {
    public static void main(String[]arg) {
        System.out.println(getDateTimeNow());
        System.out.println(getDateTimeNow());
        System.out.println(getDateTimeNow());
        System.out.println(getDateTimeNow());
        System.out.println(getDateTimeNow());
        System.out.println(getDateTimeNow());

    }



    public static  String getDateTimeNow (){
        java.util.Date date = new java.util.Date();
        return date.toString();
    }
}

