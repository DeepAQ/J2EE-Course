package cn.imaq.order;

import cn.imaq.tompuss.TomPuss;

import java.io.File;

public class TomPussMain {
    public static void main(String[] args) throws Exception {
        TomPuss tomPuss = new TomPuss();
        tomPuss.setPort(8080);
        tomPuss.setResourceRoot(new File(TomPussMain.class.getProtectionDomain().getCodeSource().getLocation().toURI()));
        tomPuss.start();
    }
}
