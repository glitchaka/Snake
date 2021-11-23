import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java .awt.Toolkit;
import java.awt.event.*;
import java.awt.Point;
import java.awt.Color;



public class Snake extends JFrame{
    int width = 640;
    int height = 480;
    Point snake;
    int widthPoint =10;
    int heigthPoint =10;
    ImagenSnake imagenSnake;
    int direccion = KeyEvent.VK_LEFT;
    long frecuencia = 20;


    public Snake(){

        setSize(width, height);
        setTitle("Snake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-width/2, dim.height/2-height/2);

        Teclas teclas = new Teclas();
        this.addKeyListener(teclas);
        snake = new Point(width/2, height/2);
        ImagenSnake imagenSnake = new ImagenSnake();
        this.getContentPane().add(imagenSnake);
        Momento momento = new Momento();
        Thread trid = new Thread(momento);
        trid.start();
    }

    public static void main(String[] args) throws Exception {
       Snake s = new Snake();
    }

    public void actualizar(){
        imagenSnake.repaint();

    }

    public class ImagenSnake extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);

            g.setColor(new Color(0,0,255));
            g.fillRect(snake.x, snake.y, widthPoint, heigthPoint);


        }
        
    }

    public class Teclas extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                System.exit(0);
            }else if(e.getKeyCode() == KeyEvent.VK_UP){
                if(direccion != KeyEvent.VK_DOWN){
                    direccion = KeyEvent.VK_UP;
                    System.out.println("Arriba es leido");
                }
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            if(direccion != KeyEvent.VK_UP){
                direccion = KeyEvent.VK_DOWN;
                System.out.println("Abajo es leido");
            }
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(direccion != KeyEvent.VK_RIGHT){
                direccion = KeyEvent.VK_LEFT;
                System.out.println("Izquierda es leido");
            }
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(direccion != KeyEvent.VK_LEFT){
                direccion = KeyEvent.VK_RIGHT;
                System.out.println("Derecha es leido");
            }
        }
    }
}
public class Momento extends Thread{
    long last = 0;
        public void run(){
            System.out.println("entro a run");
        while(true){
            if((java.lang.System.currentTimeMillis() - last) > frecuencia){
                System.out.println("entro a if");

                if(direccion == KeyEvent.VK_UP){
                    snake.y = snake.y - heigthPoint;
                    if(snake.y > height){
                        snake.y = 0;
                    }
                }else if(direccion == KeyEvent.VK_DOWN){
                        snake.y = snake.y + heigthPoint;
                    if (snake.y < 0){
                        snake.y = height - heigthPoint;
                    }
                   
                actualizar();
                last = java.lang.System.currentTimeMillis();
                
            }
    
}
        
}
}
}
}
