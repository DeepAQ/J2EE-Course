package cn.imaq.order;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.JarResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class TomcatMain {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.getHost().setAppBase(".");
        tomcat.enableNaming();

        Context ctx = tomcat.addWebapp("", ".");
        ctx.setConfigFile(TomcatMain.class.getClassLoader().getResource("META-INF/context.xml"));

        // workaround for Tomcat 9.0
        WebResourceRoot res = new StandardRoot(ctx);
        File classPath = new File(TomcatMain.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        if (classPath.isDirectory()) {
            res.addPreResources(new DirResourceSet(res, "/WEB-INF/classes", classPath.getAbsolutePath(), "/"));
            res.addPreResources(new DirResourceSet(res, "/WEB-INF", classPath.getAbsolutePath(), "/WEB-INF"));
            res.addPreResources(new DirResourceSet(res, "/jsp", classPath.getAbsolutePath(), "/jsp"));
        } else {
            res.addPreResources(new JarResourceSet(res, "/WEB-INF/classes", classPath.getAbsolutePath(), "/"));
            res.addPreResources(new JarResourceSet(res, "/WEB-INF", classPath.getAbsolutePath(), "/WEB-INF"));
            res.addPreResources(new JarResourceSet(res, "/jsp", classPath.getAbsolutePath(), "/jsp"));
        }
        ctx.setResources(res);
        tomcat.getConnector();

        tomcat.start();
        tomcat.getServer().await();
    }
}
