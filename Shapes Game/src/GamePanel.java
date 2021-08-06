import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener, MouseListener{
	
	//all private main variables
	private final static int WIDTH = 800;
	private final static int HEIGHT = 600;
	private final static int DELAY = 100;

	private ArrayList<ShapeObject> allShapes = new ArrayList<ShapeObject>();
	private ArrayList<ShapeObject> onScreenShapes = new ArrayList<ShapeObject>();
	private ArrayList<ShapeObject> lostShapes = new ArrayList<ShapeObject>();
	
	private boolean running = false;
	private String shapeName = "triangle";
	private int score = 0;
	private String scoreText = "0";
	private int lives = 3;
	private Timer timer;
	private Random random;

	//constructor for the game panel
	public GamePanel() {
		
		this.setBounds(0, 0, WIDTH, HEIGHT);
		this.setFocusable(true);
		this.setBackground(Color.BLACK);
		
		start();
		
	}
	
	//initializes the game and starts the timer
	public void start() {
		running = true;
		this.timer = new Timer(DELAY, this);
		timer.start();
		random = new Random();
		addMouseListener(this);
		
		allShapes.add(new ShapeObject("square", 4));
		allShapes.add(new ShapeObject("pentagon", 5));
		allShapes.add(new ShapeObject("hexagon", 6));
		allShapes.add(new ShapeObject("heptagon", 7));
		allShapes.add(new ShapeObject("octagon", 8));
		allShapes.add(new ShapeObject("nonagon", 9));
		
		onScreenShapes.add(new ShapeObject("triangle", 3));
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.draw(g);
	}
	
	//draws shapes and text
	public void draw(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, 3*WIDTH/4, HEIGHT);
		
		
		for(ShapeObject shape : onScreenShapes) {
			g.fillPolygon(shape.getShape());
		}
		
		g.setFont(new Font("Tahoma", Font.BOLD, 14));
		g.drawString(shapeName, 4*WIDTH/5, HEIGHT/8);
		g.drawString("Score:", 4*WIDTH/5, HEIGHT/4);
		g.drawString(scoreText, 7*WIDTH/8, HEIGHT/4);
		g.drawString("Lives:  " + (3 - lostShapes.size()), 4*WIDTH/5, HEIGHT/2);
		
		if(lostShapes.size() >= lives) {
			this.gameOver(g);
			timer.stop();
		}
		
	}
	
	//ends the game and draws Game Over
	public void gameOver(Graphics g) {
		g.setFont(new Font("Tahoma", Font.BOLD, 100));
		g.drawString("GAME OVER", WIDTH/8, HEIGHT/4);
	}
	
	//increases score by 100
	public void increaseScore() {
		score += 100;
		scoreText = String.valueOf(score);
	}
	
	//changes the shape name to the lowest shape
	public void updateShapeName() {
		shapeName = onScreenShapes.get(0).getObjectName();
	}
	
	//updates the shapes on screen to fall down
	public void fall() {
		for(ShapeObject shape : onScreenShapes) {
			shape.setOffset(shape.getXOffset(), shape.getYOffset() + 3);
		}
	}


	//main loop 
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			updateShapeName();
			fall();
			if(onScreenShapes.size() < 5) {
				int randNum = random.nextInt(allShapes.size());
				onScreenShapes.add(allShapes.get(randNum));
				allShapes.remove(randNum);
				onScreenShapes.get(onScreenShapes.size()-1).setOffset(random.nextInt(500) + 50, -100);
			}
			
			if(onScreenShapes.get(0).getYOffset() > 650) {
				lostShapes.add(onScreenShapes.get(0));
				allShapes.add(onScreenShapes.get(0));
				onScreenShapes.remove(0);
			}
		}
		repaint();
		
	}

	//checks mouse clicks
	@Override
	public void mouseClicked(MouseEvent click) {
		if(onScreenShapes.size() > 0) {
			if(onScreenShapes.get(0).getShape().contains(click.getX(),click.getY())) {
				allShapes.add(onScreenShapes.get(0));
				onScreenShapes.remove(0);
				increaseScore();
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
}
