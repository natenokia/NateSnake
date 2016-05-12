import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.io.*;

public class NateSnake extends JPanel implements KeyListener{
    
    boolean check = false;
    int dia = 20;
    char c;
    String color;
    boolean end = false;
    char stage = '1';
    int rr = 43;
    int gg = 50;
    int bb = 148;
    int d = 0;
    int x = 380;
    int y = 340;
    int randx = (int)(Math.random() * 700);
    int randy = (int)(Math.random() * 600);
    int randxx = randx + 10;
    int randyy = randy + 10;
    int points = 0;
    int speed = 3;
    int num = 3;
    boolean move = true;
    int chan = 0;
    double timeTrack = 0;
    double timer = 0.0;
    
    
    //Power up variables
    
    boolean special = false;
    String power = "inactive";
    String powerUp = "";
    int checkSwoll = 0;
    double powerUpTimer = 0;
    int offset = 0;
    boolean surgeScoring = false;
    boolean invisible = false;
    
    
    
    public void keyReleased(KeyEvent e) {
        c = e.getKeyChar();
        if(c == 'w') {
            //System.out.println("Up");
        } 
        if(c == 'a') {
            //System.out.println("Left");
        }
        if(c == 's') {
            //System.out.println("Down");
            
        }
        if(c=='d') {
            //System.out.println("Right");
        }
    }
    public void keyTyped(KeyEvent e) {
        c = e.getKeyChar();     
    }

    public void keyPressed(KeyEvent e) {
        c = e.getKeyChar();
    }

    public NateSnake() {
        repaint();
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NateSnake.this.repaint();
            }
        };

        Timer t = new Timer(12, action);
        t.setRepeats(true);
        t.setInitialDelay(0);
        t.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        
        //local variables
        
        int primex = 100;
        
        //Overwriting of old picture
        super.paintComponent(g);
        
        if(move) {
            timer += 0.01;
        }
        
        if(move) {
            
            int time = (int)(timer);
            
            if(time % 3 == 0) {
                chan = 1;
            } else {
                chan = 0;
            }
            
            g.setColor(new Color(rr,gg,bb));
        
                if(stage == '0') {
                    if(rr < 43) {
                        rr += chan;
                        check = false;
                    } else if(rr > 43) {
                        rr -= chan;
                        check = false;
                    } else {
                        check = true;
                    }
                    if(gg < 50) {
                        gg += chan;
                        check = false;
                    } else {
                        check = true;
                    }
                    if(bb < 148) {
                        bb += chan;
                        check = false;
                    } else {
                        check = true;
                    }
                    if(check == true) {
                        stage = '1';
                        check = false;
                    }
                } else if(stage == '1') {
                    if(rr < 81) {
                        rr += chan;
                        check = false;
                    } else {
                        check = true;
                    }
                    if(gg < 140) {
                        gg += chan;
                        check = false;
                    } else {
                        check = true;
                    }
                    if(bb < 82) {
                        bb += chan;
                        check = false;
                    } else {
                        check = true;
                    }
                    if(check) {
                        stage = '2';
                        check = false;
                    }
                } else if(stage == '2') {
                    if(rr < 204) {
                        rr += chan;
                        check = false;
                    } else {
                        check = true;
                    }
                    if(gg > 41) {
                        gg -= chan;
                        check = false;
                    } else {
                        check = true;
                    }
                    if(bb > 60) {
                        bb -= chan;
                        check = false;
                    } else {
                        check = true;
                    }
                    if(check) {
                        stage = '0';
                        check = false;
                    }
                }
        }
        
        Background canvas = new Background(g,0,0,800,800);
        
        
        //SNAKE MANIPULATION AND CHANGE
        
        if(invisible) {
            color = "invisible";
            speed = 1;
        } else {
            if(points >= 600) {
                color = "gold";
                speed = 6;
            } else if(points >= 300) {
                color = "red";
                speed = 5;
            } else if(points >= 100) {
                color = "green";
                speed = 4;
            } else {
                color = "white";
                speed = 3;
            }
        }
        
        
            if(move) {
                if(y > 0 && y < 700 && x < 780 && x > 0) {
                    switch(c) {
                        case 'w': y -= speed + offset; break;
                        case 'a': x -= speed + offset; break;
                        case 's': y += speed + offset; break;
                        case 'd': x += speed + offset; break;
                    }
                /*} else if(!(y > 0)) {
                    y = 1;
                } else if(!(y < 600)) {
                    y = 699;
                } else if(!(x > 0)) {
                    x = 1;
                } else if(!(x < 780)) {
                    x = 779;
                }*/
                } else {
                    end = true;
                }
            }
            
            //FOOD DECISIONS _SPECIAL OR NOT_
            
        
        if(special) {
            g.setColor(new Color(142,212,114));
            Feed food = new Feed(g, randx, randy, 10);
        } else {
            g.setColor(Color.BLUE);
            Feed food = new Feed(g, randx, randy, 10);
        }    
        
        
        //SNAKE POWERUPS AND CHANGES
        
        
        
        if(power.equals("inactive")) {
            if(dia > 19) {
                dia -= 2;
                x++;
                y++;
            } 
            if(dia < 21) {
                dia += 2;
                x --;
                y--;
            }
            surgeScoring = false;
            offset = 0;
            g.setColor(Color.WHITE);
            Snake toby = new Snake(g, x, y, dia, dia, color);
        } else if(power.equals("active")) {
            if(powerUp.equals("Swoll")) {
                
                surgeScoring = true;
                
                if(dia < 40 && checkSwoll == 0) {
                    dia++;
                    if(dia%2 == 0) {
                        x--;
                        y--;
                    }
                } else {
                    checkSwoll = 1;
                    if(dia > 19) {
                        dia--;
                        if(dia%2 == 0) {
                            x++;
                            y++;
                        }   
                    } else {
                        checkSwoll = 0;
                    }
                }
                g.setColor(Color.WHITE);
                Snake toby = new Snake(g, x, y, dia, dia, color);
                powerUpTimer = powerUpTimer + 0.01;
                
            } else if(powerUp.equals("Micro")) {
                
                if(dia > 10) {
                    dia--;
                    if(dia % 2 == 0) {
                        x++;
                        y++;
                    }
                }
                g.setColor(Color.WHITE);
                Snake toby = new Snake(g, x, y, dia, dia, color);
                powerUpTimer = powerUpTimer + 0.01;
                
            } else if(powerUp.equals("Envelop")) {
                
                surgeScoring = true;
                
                offset = 2;
                g.setColor(Color.BLACK);
                if(dia < 60) {
                    dia++;
                    if(dia % 2 == 0) {
                        x--;
                        y--;
                    }
                }
                Snake toby = new Snake(g, x, y, dia, dia, color);
                powerUpTimer = powerUpTimer + 0.01;
                
            } else if(powerUp.equals("Speed")) {
                
                offset = 3;
                g.setColor(Color.WHITE);
                Snake toby = new Snake(g, x, y, dia, dia, color);
                powerUpTimer = powerUpTimer + 0.01;
                
            } else if(powerUp.equals("Smallspeed")) {
                
                if(dia > 15) {
                    dia--;
                    if(dia % 2 == 0) {
                        x++;
                        y++;
                    }
                }
                
                offset = 4;
                
                g.setColor(Color.WHITE);
                Snake toby = new Snake(g, x, y, dia, dia, color);
                powerUpTimer = powerUpTimer + 0.01;
                
            } else if(powerUp.equals("Invisibility")) {
                
                surgeScoring = true;
                offset = -2;
                g.setColor(new Color(rr,gg,bb));
                dia = 200;
                Snake toby = new Snake(g, x, y, dia, dia, color);
                powerUpTimer = powerUpTimer + 0.01;
                
            } else {
                Snake toby = new Snake(g, x, y, 20, 20, color);
            }
        }
        
        if(powerUpTimer > 8) {
            special = false;
            power = "inactive";
            powerUpTimer = 0;
        }
        if(power == "active") {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Comic Sans MS", Font.PLAIN, 20)); 
            g.drawString(powerUp, 600, 720);
        }
                
                
        //COLLISION DETECTION AND REACTION
        
        int diax = x + dia;
        int diay = y + dia;
        
        
        if((randx < diax && randx > x) && (randyy > y && randyy < diay)) {
            randx = (int)(Math.random() * 700);
            randy = (int)(Math.random() * 600);
            randxx = randx + 10;
            randyy = randy + 10;
            if(special) {
                power = "active";
                System.out.println("Power is Active");
                powerUp = Feed.powerUp();
                System.out.println(Feed.powerUp());
            }
            if(surgeScoring) {
                points += 25;
            } else {
                points += 15;
            }
            special = Feed.SpecialFeed();
        }
        
        if((randxx < diax && randxx > x) && (randyy > y && randyy < diay)) {
            randx = (int)(Math.random() * 700);
            randy = (int)(Math.random() * 600);
            randxx = randx + 10;
            randyy = randy + 10;
            if(special) {
                power = "active";
                System.out.println("Power is Active");
                powerUp = Feed.powerUp();
                System.out.println(Feed.powerUp());
            }
            if(surgeScoring) {
                points += 25;
            } else {
                points += 15;
            }
            special = Feed.SpecialFeed();
        }
        
        
        
        //POINT SYSTEM AND ALLOCATION
        
        /*if(points >= 30 && c != 'p') {
            System.out.println("You WIN!, Game Over");
            end = true;
            move = false;
            x = 380;
            y = 340;
            //points = 0;
            c = 'p';
        }*/
        
        if(!end) {
            timeTrack += 0.01;
        }
        
        //int pointDisplay;
        
        if(end) {
            g.setFont(new Font("Comic Sans MS", Font.PLAIN, 20)); 
            g.setColor(Color.WHITE);
            g.drawString("Game Over, press C to play again!", 300, 400);
            //timer = Double.parseDouble(new DecimalFormat("##.##").format(timer));
            g.drawString("Your time was " + Math.round(timeTrack*100.0)/100.0 + " seconds", 300, 450);
            
            g.drawString("You collected " + points + " points!", 300, 500);
            g.drawString("Your proficiency index is " + (Math.round((points/timeTrack)*100.0)/100.0), 300, 550);
            
            if(c == 'c') {
                points = 0;
                timeTrack = 0;
                System.out.println("Replay");
                x = 380;
                y = 300;
                end = false;
                move = true;
                int randx = (int)(Math.random() * 700);
                int randy = (int)(Math.random() * 600);
            }
        }
        

        
            g.setColor(Color.WHITE);
            g.setFont(new Font("Comic Sans MS", Font.PLAIN, 20)); 
            g.drawString(points + " points", 100, 720);
        
        
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame();
        frame.setSize(800, 800);
        final NateSnake panel = new NateSnake();
        panel.setOpaque(true);
        frame.getContentPane().add(panel);
        frame.addKeyListener(panel);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}