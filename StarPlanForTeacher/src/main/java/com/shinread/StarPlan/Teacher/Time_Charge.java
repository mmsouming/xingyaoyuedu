package com.shinread.StarPlan.Teacher;

import com.fancyfamily.primarylibrary.commentlibrary.framework.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lizehong on 2016/5/11.
 */
public class Time_Charge {
public void timechange(String time){
    Long t3=null ;
    Long t3s=null;
    try {


        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;

        date=sdf.parse(time);//将String to Date类型

        t3=new Long(date.getTime());

     return;

    } catch (ParseException e) {
        e.printStackTrace();
    }
}

}
