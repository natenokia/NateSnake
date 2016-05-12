import java.util.*;
import java.awt.*;
import javax.swing.*;

public class ColorSwitch {
    public ColorSwitch(Graphics g, char stage, int rr, int gg, int bb, boolean check) {
        
        g.setColor(new Color(rr,gg,bb));
        
        if(stage == '1') {
                if(rr < 43) {
                    rr++;
                    check = false;
                } else {
                    check = true;
                }
                if(gg < 50) {
                    gg++;
                    check = false;
                } else {
                    check = true;
                }
                if(bb < 148) {
                    bb++;
                    check = false;
                } else {
                    check = true;
                }
                if(check == true) {
                    stage = '0';
                    check = false;
                }
            } else if(stage == '0') {
                if(rr > 0) {
                    rr--;
                    check = false;
                } else {
                    check = true;
                }
                if(gg > 0) {
                    gg--;
                    check = false;
                } else {
                    check = true;
                }
                if(bb > 0) {
                    bb--;
                    check = false;
                } else {
                    check = true;
                }
                if(check) {
                    stage = '1';
                    check = false;
                }
            }
    }
}