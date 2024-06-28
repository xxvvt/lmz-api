package com.bing.admin.lmz.server;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@Service("CallBatServer")
public class CallBatServer {


        public void runbat() {   //启动网易云音乐
            String cmd = "cmd /c start E:\\文件\\bat\\start.bat";//网易音乐安装目录E:/wyyyy/CloudMusic/
            try {
                Process ps = Runtime.getRuntime().exec(cmd);
                ps.waitFor();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("开启网易云音乐");
        }

        public void shutbat() {   //关闭网易云音乐
            String cmd = "cmd /c start E:\\文件\\bat\\kill.bat";//网易音乐安装目录E:/wyyyy/CloudMusic/
            try {
                Process ps = Runtime.getRuntime().exec(cmd);
                ps.waitFor();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("关闭网易云音乐");
        }

    }

