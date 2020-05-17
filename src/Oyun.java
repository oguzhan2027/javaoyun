
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;



class Ates{
    
    private int x;
    private int y;

    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    
}



public class Oyun extends JPanel implements KeyListener,ActionListener {
    
   
   
    Timer timer = new Timer(5,this);
    
    private int gecen_sure = 0;
    private int harcanan_ates = 0;
    
    private BufferedImage image;
    
    private ArrayList<Ates> atesler = new ArrayList<Ates>();
   
   
      Random r=new Random(); //random sınıfı
    int a=r.nextInt(550);
    int b=r.nextInt(550);
    
    private int atesdirY = 1;
    private int topX  =a;
    private int topY =b;
    private int topDirY = 2;
    private int topdirX = 2;
    private int askerX = 250;
    private int askerY = 250;
    private int dirAskerX = 20;
    private int dirAskerY = 20;
    
    
    public boolean kontrolEt(){
       
        for(Ates ates : atesler){
            if(new Rectangle(ates.getX(),ates.getY(),10,20).intersects(new Rectangle(topX,topY,20,20))){
                return true;
            }
        }
        return false;
    }
    
    public Oyun() {
        try {
            image = ImageIO.read(new FileImageInputStream(new File("people.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        setBackground(Color.BLACK );
        
        timer.start();
        
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        gecen_sure += 5;
        g.setColor(Color.red);
        g.fillOval(topX, topY, 20, 20);
        
        
        g.drawImage(image,askerX,askerY,image.getWidth(),image.getHeight(),this);
        
        for(Ates ates : atesler){
            if(ates.getY() < 0){
                atesler.remove(ates);
            }
        }
        g.setColor(Color.BLUE);
        for(Ates ates : atesler){
            g.fillRect(ates.getX(), ates.getY(), 10, 20);
        }
        
        if(kontrolEt()){
            timer.stop();
            String message = "Kazandınız..\n"+
                             "Harcanan ateş : "+harcanan_ates+
                             "\nGeçen Süre : "+gecen_sure/1000.0+"saniye";
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
        
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    

    @Override
    public void keyTyped(KeyEvent e) {
        
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if(c == KeyEvent.VK_LEFT){
            if(askerX <= 0){
                askerX = 0;
            }
            else{
                askerX -= dirAskerX;
            }
        }
        else if(c == KeyEvent.VK_RIGHT){
            if(askerX >= 520){
                askerX = 520;
            }
            else{
                askerX += dirAskerX;
            }   
        }
        else if(c == KeyEvent.VK_DOWN){
            if(askerY >= 490){
                askerY = 490;
            }
            else{
                askerY += dirAskerY;
            }
        }
        else if(c == KeyEvent.VK_UP){
            if(askerY <= 0){
                askerY = 0;
            }
            else{
                askerY -= dirAskerY;
            }
        }
        else if (c == KeyEvent.VK_SPACE ){
            atesler.add(new Ates(askerX+27,askerY-15));
            harcanan_ates++;
            
                    
          }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        
        for(Ates ates : atesler){
            ates.setY(ates.getY() - atesdirY);
        }
        
        topX += topdirX;
        topY += topDirY;
        
        if(topX >= 560 ){
            topdirX = -topdirX;
        }
        if(topX <= 0){
            topdirX = -topdirX;
        }
        if(topY >=540)
            topDirY = -topDirY;
        if(topY <=0)
            topDirY = -topDirY;
       /* if(topX >=300)
            topdirX = -topdirX;
        
        */
        
        repaint();
    }
    
 
}
