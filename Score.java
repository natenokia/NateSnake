import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Score {
    public Score(Graphics g, int x, int y, int size) {
        ScorePaint(g,x,y,size);
    }
    public void ScorePaint(Graphics g, int x, int y, int size) {
        //g.setColor(Color.BLUE);
        g.fillOval(x,y,size,size);
    }
}