package de.hsh;


import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.List;

public class GameScreen extends Screen implements Runnable {
	
	private static final long serialVersionUID = 1L;
	private List<Battlefield> battlefields;
	private float time;
	private de.hsh.Objects.Player player;
	private boolean running;

	
	public GameScreen(List<Battlefield> pBattlefields){
		
		battlefields = pBattlefields;
		
		player = new de.hsh.Objects.Player();
		
		this.addKeyListener(new TAdapter());
		System.out.println("Keylistener"+this.getKeyListeners());
		new Thread(this).start();
		running = true;
		
	}
	
	private void update(float pDeltaTime){
		
		
		System.out.println("Update: pDeltaTime: "+pDeltaTime);
		
		time += pDeltaTime;
		
		Point2D pos = player.getPosition();
		Point2D direction = player.getDirection();
		pos.setLocation(pos.getX() + direction.getX()*pDeltaTime, pos.getY() + direction.getY()*pDeltaTime);
		player.setPosition(pos);
		updateUI();
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		player.draw(g);
	}

	private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
        	System.out.println("Taste gedrückt "+e);
    		
    		if(e.getKeyCode() == KeyEvent.VK_UP) {
    			player.setDirection(new Point2D.Double(0,-1));
    		}
    		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
    			player.setDirection(new Point2D.Double(0,1));
    		}
    		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
    			player.setDirection(new Point2D.Double(1,0));
    		}
    		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
    			player.setDirection(new Point2D.Double(-1,0));
    		}
    		
        }
    }

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		float delta = 0;
		
		while(running){
			long now = System.nanoTime();
			delta +=  (now-lastTime)/nsPerTick;
			lastTime = now;
				
			
			
			if(delta >= 1) {
				update(delta);
				delta = 0;
			}
			
			}
		}
	}

