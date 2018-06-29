package com.gz.common;

import com.jfinal.core.JFinal;

/**
 * Created by gongzhen on 2018/6/6.
 */
public class Constant {
    public final static String HOST_PATH = "http://192.168.1.150";
    public static String FILE_PATH=HOST_PATH+"/";
    public final static String PROJECT_PATH = JFinal.me().getContextPath()+"/";
}
