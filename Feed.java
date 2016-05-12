import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Feed {
    public Feed(Graphics g, int x, int y, int size) {
        FeedPaint(g,x,y,size);
    }
    public void FeedPaint(Graphics g, int x, int y, int size) {
        //g.setColor(Color.BLUE);
        g.fillOval(x,y,size,size);
    }
    public static boolean SpecialFeed() {
        int specialrand = (int)(Math.random() * 101);
        if(specialrand <= 30) {
            return true;
        } else {
            return false;
        }
    }
    public static String powerUp() {
        int powerrandom = (int)(Math.random() * 22);
        if(powerrandom >= 18) {
            return "Swoll";
        } else if(powerrandom >= 13) {
            return "Micro";
        } else if(powerrandom >= 12) {
            return "Envelop";
        } else if(powerrandom >= 7) {
            return "Speed";
        } else if(powerrandom >= 2) {
            return "Smallspeed";
        } else {
            return "Invisibility";
        }
        
    }
}