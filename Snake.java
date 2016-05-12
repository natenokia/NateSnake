import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.lang.*;
import java.io.*;

public class Snake {
    
    public Snake(Graphics g, int x, int y,int w, int h, String str) {
        snakePaint(g,x,y,w,h,str);
    }
    
    public void snakePaint(Graphics g, int x, int y, int w, int h, String str) {
        
        
        g.fillRect(x,y,w,h);
        
        /*int alpha = 127; // 50% transparent
        Color invisible = new Color(255, 255, 255, alpha);*/
        
        if(str.equals("green")) {
            g.setColor(Color.GREEN);
        } else if(str.equals("red")) {
            g.setColor(Color.RED);
        } else if(str.equals("gold")) {
            g.setColor(Color.BLACK);
        } else if(str.equals("white")) {
            g.setColor(Color.BLUE);
        } else if(str.equals("invisible")) {
            g.setColor(new Color(255,255,255,127));
        }
        
        g.fillRect((x+(w/4)),(y+(h/4)),(w - (w/2)),(h - (h/2)));
    }
    
    
}