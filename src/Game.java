import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.event.*; 
import java.util.Queue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Game  extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener{

	
	private BufferedImage back; 
	private int key, x, y, bx;
	public char screen;
	public ArrayList<Protagonist> playerOptions;
	public Protagonist player;
	private String message1, message2, message3, message4, message5, message6;
    public int progress, target, select, actSelect, counter, en1Type, en2Type, en3Type, sz1, sz2, sz3, trn, trn1, trn2, trn3, trn4, enemyTurn, highScore, i1, ii1, i2, ii2;
    public boolean shwTargt, shwActns, immunity, counting, shwMainActns, frstBtle, inBtle, playerTurn, attacking, transition, illegalRun;
    public ImageIcon e1, e2, e3;
	public Slot s1, s2, s3;
    public Enemies spookFace, chocoBlorp, flamito, purpleShrop, freezeFace, strawBlorp, flyingEelect, lilGlacestorm, mrWall, rogue, en1, en2, en3;
    public static final ImageIcon SF = new ImageIcon("SpookFace(C).gif");
    public static final ImageIcon CB = new ImageIcon("ChocoBlorp(C).gif");
    public static final ImageIcon F = new ImageIcon("Flamito(C).gif");
    public static final ImageIcon PS = new ImageIcon("PurpleShrop(C).png");
    public static final ImageIcon FF = new ImageIcon("FreezeFace(C).gif");
    public static final ImageIcon SB = new ImageIcon("StrawBlorp(C).gif");
    public static final ImageIcon FE = new ImageIcon("FlyingEelect(C).gif");
    public static final ImageIcon LG = new ImageIcon("LilGlacestorm(C).gif");
    public static final ImageIcon MW = new ImageIcon("MrWall(C).gif");
    public static final ImageIcon R = new ImageIcon("Rogue(C).gif");
    public Queue <Enemies> enemies1;
    public Queue <Enemies> enemies2;
    public Queue <Enemies> enemies3;
    private Soundplayer Track2;
    private File saveFile;

	
	public Game() {
        saveFile = new File("caveFile.txt");

		new Thread(this).start();	
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		key =-1;
		x=0;
		y=0;
		playerOptions = new ArrayList<Protagonist>();
		playerOptions = setPlayers();
		screen = 'C';
		player = new Protagonist();
		bx = 0;
        progress = 0;
        target = 2;
        select = 1;
        actSelect = 1;
        counter = 0;
        en1Type = 0;
        en2Type = 0;
        en3Type = 0;
        shwTargt = false;
        shwActns = false;
        immunity = false;
        counting = false;
        shwMainActns = false;
        frstBtle = true;
        inBtle = false;
        message1 = "";
        message2 = "";
        message3 = "";
        message4 = "";
        message5 = "";
        message6 = "";
        e1 = new ImageIcon("");
        e2 = new ImageIcon("");
        e3 = new ImageIcon("");
        sz1 = 1;
        sz2 = 2;
        sz3 = 3;
        s1 = new Slot(e1, 1, sz1);
        s2 = new Slot(e2, 2, sz2);
        s3 = new Slot(e3, 3, sz3);
        //spookFace = new Enemies("Spook Face", 9, 7, 2, 0, 3, 1, 1, 1, 1, 0, 1, 1, 1, SF);
        //chocoBlorp = new Enemies("Choco Blorp", 10, 7, 3, 1, 2, 0, 0, 0, 1, 0, 2, 1, 2, CB);
        //flamito = new Enemies("Flamito", 14, 9, 4, 1, 3, 3, 0, 1, 2, 0, 2, 3, 3, F);
        spookFace = new Enemies();
        chocoBlorp = new Enemies();
        flamito = new Enemies();
        purpleShrop = new Enemies();
        freezeFace = new Enemies();
        strawBlorp = new Enemies();
        flyingEelect = new Enemies();
        lilGlacestorm = new Enemies();
        mrWall = new Enemies();
        rogue = new Enemies();
        en1 = new Enemies(-1, 0, 0, 0, "", new SpecialMoves(), new SpecialMoves(), new ImageIcon(""), 0, 0);
        en2 = new Enemies(-1, 0, 0, 0, "", new SpecialMoves(), new SpecialMoves(), new ImageIcon(""), 0, 0);
        en3 = new Enemies(-1, 0, 0, 0, "", new SpecialMoves(), new SpecialMoves(), new ImageIcon(""), 0, 0);
        trn = 0;
        trn1 = 0;
        trn2 = 0;
        trn3 = 0;
        trn4 = 0;
        playerTurn = false;
        enemyTurn = 0;
        attacking = false;
        transition = false;
		enemies1 = setEnemies();
        enemies2 = setEnemies();
        enemies3 = setEnemies();
        Track2 = new Soundplayer();
        Track2.playmusic("Battle.wav");
        i1 = 0;
        ii1 = 0;
        i2 = 0;
        ii2 = 0;
        illegalRun = false;
	}

    public void createFile() {
        try {
            if (saveFile.createNewFile()) {
                System.out.println("File created: " + saveFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        }
        catch(IOException e) {
            System.out.println("Error Creating File");
            e.printStackTrace();

        }
    }

    public void readFile() {
        try {
            Scanner reader = new Scanner(saveFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (data.equals("win")) {
                    System.out.println("You win!");
                }
                if (data.equals("High score:")) {
                    String s = reader.nextLine();
                    highScore = Integer.parseInt(s);
                }
                System.out.println(data);
            }
            reader.close();
        }
        catch(IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
    }

    public void writeToFile() {
        try {
            FileWriter writer = new FileWriter(saveFile);
            //writer.write("win\n");
            if (!(illegalRun)) {
                writer.write("High score:\n" + highScore);
            }
            writer.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch(IOException e) {
            System.out.println("Error writing to file");
            e.printStackTrace();
        }
    }

    public Queue<Enemies> setEnemies() {
        Queue <Enemies> temp = new LinkedList<>();
        temp.add(new SpookFace(SF));
        temp.add(new SpookFace(SF));
        temp.add(new ChocoBlorp(CB));
        temp.add(new Flamito(F));
        return temp;
    }

    public Queue<Enemies> setEnemiesR() {
        Queue <Enemies> temp = new LinkedList<>();
        int i = RNG();
        if (i <= 40) {
            temp.add(new SpookFace(SF));
        }
        else if (i <= 70) {
            temp.add(new ChocoBlorp(CB));
        }
        else {
            temp.add(new Flamito(F));
        }
        return temp;
    }

	public ArrayList<Protagonist> setPlayers() {
		ArrayList<Protagonist> temp = new ArrayList<Protagonist>();
		temp.add(new Magician());
		temp.add(new Hunter());
		temp.add(new Explorer());
        temp.add(new HUGE());

		return temp;
	}

	
	
	public void run()
	   {
	   	try
	   	{
	   		while(true)
	   		{
	   		   Thread.currentThread().sleep(5);
	            repaint();
	         }
	      }
	   		catch(Exception e)
	      {
	      }
	  	}
	

	public void screen(Graphics g2d) {
		switch(screen) {
			case 'C':

			bx = bx - 10;
			if (bx < -1799) {
				bx = 0;
			}
			g2d.drawImage(new ImageIcon("SparklyWall.png").getImage(), bx, 0, 1800, 1600, this);
			g2d.drawImage(new ImageIcon("SparklyWall.png").getImage(), bx, 800, 1800, 1600, this);
			g2d.drawImage(new ImageIcon("SparklyWall.png").getImage(), bx + 1800, 0, 1800, 1600, this);
			g2d.drawImage(new ImageIcon("SparklyWall.png").getImage(), bx + 1800, 800, 1800, 1600, this);
			drawStats(g2d, playerOptions.get(0), 15, 35);
			drawStats(g2d, playerOptions.get(1), 15, 305);
			drawStats(g2d, playerOptions.get(2), 795, 35);
            g2d.drawString("High Score: " + highScore, 30, 525);


			break;

			case 'P':

			bx = bx - 10;
			if (bx < -1799) {
				bx = 0;
			}
			g2d.drawImage(new ImageIcon("SparklyWall.png").getImage(), bx, 0, 1800, 1600, this);
			g2d.drawImage(new ImageIcon("SparklyWall.png").getImage(), bx, 800, 1800, 1600, this);
			g2d.drawImage(new ImageIcon("SparklyWall.png").getImage(), bx + 1800, 0, 1800, 1600, this);
			g2d.drawImage(new ImageIcon("SparklyWall.png").getImage(), bx + 1800, 800, 1800, 1600, this);
			g2d.drawString("Selected: " + player.getName(), 30, 30);
			g2d.drawImage(player.getIcon().getImage(), 500, 50, 400, 400, this);
			g2d.drawString(("Special 1: " + player.getAction1().getName()).substring(0, i1), 100, 200);
			g2d.drawString(("Special 2: " + player.getAction2().getName()).substring(0, i2), 100, 300);
            ii1++;
            ii2++;
            if (ii1 % 10 == 0) {
                i1++;
            }
            if (ii2 % 10 == 0) {
                i2++;
            }
            if (i1 > ("Special 1: " + player.getAction1().getName()).length()) {
                i1 = ("Special 1: " + player.getAction1().getName()).length();
            }
            if (i2 > ("Special 2: " + player.getAction2().getName()).length()) {
                i2 = ("Special 2: " + player.getAction2().getName()).length();
            }
            //System.out.println("" + ii1);

			break;

			case 'G':

            g2d.drawImage(new ImageIcon("Cave(C).png").getImage(), 0, 0, 1300, 500, this);
            g2d.setColor(Color.BLACK);
			g2d.fillRect(20, 325, 1250, 170);
			g2d.setColor(Color.WHITE);
			g2d.drawRect(20, 325, 1250, 170);
            if (!inBtle) {
                startBattle(g2d);
            }

			if (enemyTurn == 0) {
                shwTargt = true;
                shwMainActns = true;
            } else if (!(enemyTurn == 0)) {
                enemyAttack(enemyTurn);
            }

            if (shwTargt && attacking) {
                if (target == 1) {
                    if (!(en1.getHp() == 0)) {
                        g2d.drawImage((new ImageIcon("Target(C).png")).getImage(), 220, 35, 260, 260, this);
                    } else {
                        target = 2;
                    }
                } else if ((target == 2)) {
                    if (!(en2.getHp() == 0)) {
                        g2d.drawImage((new ImageIcon("Target(C).png")).getImage(), 555, 35, 260, 260, this);
                    } else if (!(en1.getHp() == 0)) {
                        target = 1;
                    } else {
						target = 3;
					}
                } else if (target == 3) {
                    if (!(en3.getHp() == 0)) {
                        g2d.drawImage((new ImageIcon("Target(C).png")).getImage(), 887, 35, 260, 260, this);
                    } else {
                        target = 2;
                    }
                }

                message5 = "HP: " + player.getHp();
                message6 = "AP: " + player.getAp();
            }

            if ((shwMainActns) && (!shwActns) && (attacking)) {
                resetStrings();
                message2 = "    Attack";
                message4 = "    Special Actions";

            	if (select == 1) {
                    g2d.drawImage((new ImageIcon("Selecter(C).png")).getImage(), 24, 380, 20, 20, this);
                    //System.out.println("Selecter drawn");
                } else if (select == 2) {
                    g2d.drawImage((new ImageIcon("Selecter(C).png")).getImage(), 474, 380, 20, 20, this);
                    //System.out.println("Selecter drawn");
                } else {
                    select = 1;
                }
                message5 = "HP: " + player.getHp();
                message6 = "AP: " + player.getAp();
            }

            if (shwActns && attacking) {
                resetStrings();
                
                if (player.getAction1() instanceof FireBurst) {
                    message2 = "    Fire Burst";
                }
                else if (player.getAction1() instanceof Buff) {
                    message2 = "    Buff";
                }
                else if (player.getAction1() instanceof Resist) {
                    message2 = "    Resist";
                }
                if (player.getAction2() instanceof ColdSnap) {
                    message4 = "    Cold Snap";
                }
                else if (player.getAction2() instanceof Skewer) {
                    message4 = "    Skewer";
                }
                else if (player.getAction2() instanceof Replenish) {
                    message4 = "    Replenish";
                }
                if (actSelect == 1) {
                    g2d.drawImage((new ImageIcon("Selecter(C).png")).getImage(), 24, 380, 20, 20, this);
                    //System.out.println("Selecter drawn");
                } else if (actSelect == 2) {
                    g2d.drawImage((new ImageIcon("Selecter(C).png")).getImage(), 474, 380, 20, 20, this);
                    //System.out.println("Selecter drawn");
                } else {
                    actSelect = 1;
                }
                message5 = "HP: " + player.getHp();
                message6 = "AP: " + player.getAp();
            }
            if (!(en1 == null)) {
                if ((en1.getHp() == 0) && (!frstBtle)) {
                    resetEnemy(en1);
                    if (!(enemies1.isEmpty())) {
                        enemies1.remove();
                    }
                }
            }
            if (!(en2 == null)) {
                if (en2.getHp() == 0) {
                    resetEnemy(en2);
                    if (!(enemies2.isEmpty())) {
                        enemies2.remove();
                    }
                    frstBtle = false;
                }
            }
            if (!(en3 == null)) {
                if ((en3.getHp() == 0) && (!frstBtle)) {
                    resetEnemy(en3);
                    if (!(enemies3.isEmpty())) {
                        enemies3.remove();
                    }
                }
            }
            if ((!(en1 == null)) && (!(en2 == null)) && (!(en3 == null))) {
                if (((en1.getHp() <= 0) && (en2.getHp() <= 0)) && (en3.getHp() <= 0)) {
                    inBtle = false;
                }
            }
            else if ((en1 == null) && (en2 == null) && (en3 == null)) {
                inBtle = false;
            }

            if (!(en1 == null)) {
                if (!(en1.getHp() <= 0)) {
                    drawEnemy1(g2d);
                }
            }
            if (!(en2 == null)) {
                if (!(en2.getHp() <= 0)) {
                    drawEnemy2(g2d);
                }
            }
            if (!(en3 == null)) {
                if (!(en3.getHp() <= 0)) {
                    drawEnemy3(g2d);
                }
            }

            if (player.getHp() <= 0) {
                screen = 'E';
            }

            if (illegalRun) {
                g2d.drawString("Run disqualified", 50, 50);
            }

            if (progress/2 > highScore) {
                highScore = progress/2;
            }

            drawStrings(g2d);

			break;

            case 'E':

            g2d.setFont( new Font("Times New Roman", Font.PLAIN, 60));
            g2d.drawString("GAME OVER", 300, 150);
            g2d.drawString("Score: " + progress, 300, 350);

            break;
		}
	}
	
	
	public void paint(Graphics g){
		
		Graphics2D twoDgraph = (Graphics2D) g; 
		if(back == null)
			back=(BufferedImage)( (createImage(getWidth(), getHeight()))); 
		

		Graphics g2d = back.createGraphics();
	
		g2d.clearRect(0,0,getSize().width, getSize().height);
	
		g2d.setFont( new Font("Broadway", Font.PLAIN, 20));
		g2d.setColor(Color.WHITE);
		
		//g2d.drawString("Hello!" , x, y);
		
		screen(g2d);
	
		twoDgraph.drawImage(back, null, 0, 0);

	}

	public void drawStats(Graphics g2d, Protagonist c, int x, int y) {
		g2d.drawImage(c.getIcon().getImage(), x, y, 150, 150, this);
		g2d.drawString("" + c.getName(), x, y - 10);
		g2d.drawString("Health: " + c.getHp(), x + 200, y + 10);
		g2d.drawString("Action Points: " + c.getAp(), x + 200, y + 40);
		g2d.drawString("Strength: " + c.getStrngth(), x + 200, y + 70);
		g2d.drawString("Armor Points: " + c.getArmrp(), x + 200, y + 100);
		if (c.getType() == 1) {
			g2d.drawString("Press A", x + 200, y + 130);
		}
		else if (c.getType() == 2) {
			g2d.drawString("Press B", x + 200, y + 130);
		}
		else if (c.getType() == 3) {
			g2d.drawString("Press C", x + 200, y + 130);
		}
		if (c.getAction1() instanceof Attack) {
			g2d.setColor(Color.ORANGE);
		}
        else if (c.getAction1() instanceof BoostMove) {
            g2d.setColor(Color.CYAN);
        }
		g2d.drawString("Special 1: " + c.getAction1().getName(), x + 400, y + 10);
        if (c.getAction2() instanceof Attack) {
			g2d.setColor(Color.ORANGE);
		}
        else if (c.getAction2() instanceof BoostMove) {
            g2d.setColor(Color.CYAN);
        }
		g2d.drawString("Special 2: " + c.getAction2().getName(), x + 400, y + 40);
        g2d.setColor(Color.WHITE);
	}

	public void drawStrings(Graphics g2d) {
        g2d.drawString(message1, 25, 350);
        g2d.drawString(message2, 25, 400);
        g2d.drawString(message3, 475, 350);
        g2d.drawString(message4, 475, 400);
        g2d.drawString(message5, 25, 450);
        g2d.drawString(message6, 475, 450);
    }

    public void resetStrings() {
        message1 = "";
        message2 = "";
        message3 = "";
        message4 = "";
        message5 = "";
        message6 = "";
    }

    public int RNG() {
        return (int) ((Math.random() * 100) + 1);
    }

    public void startBattle(Graphics g2d) {
        if (frstBtle) {

            /*if (!(enemies2.size() == 3)) {
                en2 = enemies2.peek();
            }
            else {
                addEnemy(enemies2);
                en2 = enemies2.peek();
            }*/

            addEnemy(enemies2);
            en2 = enemies2.peek();

            inBtle = true;
        }
        else {

            /*if (!(enemies1.size() == 3)) {
                en1 = enemies1.peek();
            }
            else {
                addEnemy(enemies1);
                en1 = enemies1.peek();
            }
            if (!(enemies2.size() == 3)) {
                en2 = enemies2.peek();
            }
            else {
                addEnemy(enemies2);
                en2 = enemies2.peek();
            }
            if (!(enemies3.size() == 3)) {
                en3 = enemies3.peek();
            }
            else {
                addEnemy(enemies3);
                en3 = enemies3.peek();
            }*/

            addEnemy(enemies1);
            en1 = enemies1.peek();
            addEnemy(enemies2);
            en2 = enemies2.peek();
            addEnemy(enemies3);
            en3 = enemies3.peek();

            inBtle = true;
        }
    }

    public void addEnemy(Queue<Enemies> q) {
        int i = RNG();
        if (i <= 40) {
            q.add(new SpookFace(SF));
        }
        else if (i <= 70) {
            q.add(new ChocoBlorp(CB));
        }
        else {
            q.add(new Flamito(F));
        }
    }

    public void resetImage(ImageIcon e) {
        e = new ImageIcon("");
    }

    public void resetSlot(Slot slot, ImageIcon e) {
        slot = new Slot(e, 1, 1);
    }

    public void resetEnemy(Enemies en) {
        en = new Enemies(-1, 0, 0, 0, "", new SpecialMoves(), new SpecialMoves(), new ImageIcon(), 0, 0);
    }

    public void setEnemy() {
        if (en1Type == 0) {
            resetImage(e1);
            resetSlot(s1, e1);
            resetEnemy(en1);
        } else if (en1Type == 1) {
            //e1 = SF;
            //en1 = spookFace;
            //en1.getSize();
        } else if (en1Type == 2) {
            //e1 = CB;
            //en1 = chocoBlorp;
            //en1.getSize();
        } else if (en1Type == 3) {
            //e1 = F;
            //en1 = flamito;
            //en1.getSize();
        }
    }

    public void drawEnemy1(Graphics g2d) {
        if (en1.getSize() == 1) {
            g2d.drawImage(en1.getIcon().getImage(), 225, 140, 150, 150, this);
        } else if (en1.getSize() == 2) {
            g2d.drawImage(en1.getIcon().getImage(), 225, 90, 200, 200, this);
        } else if (en1.getSize() == 3) {
            g2d.drawImage(en1.getIcon().getImage(), 225, 40, 250, 250, this);
        }
    }

    public void drawEnemy2(Graphics g2d) {
        if (en2.getSize() == 1) {
            g2d.drawImage(en2.getIcon().getImage(), 560, 140, 150, 150, this);
            //System.out.println("enemy drawn");
        } else if (en2.getSize() == 2) {
            g2d.drawImage(en2.getIcon().getImage(), 560, 90, 200, 200, this);
            //System.out.println("enemy drawn");
        } else if (en2.getSize() == 3) {
            g2d.drawImage(en2.getIcon().getImage(), 560, 40, 250, 250, this);
            //System.out.println("enemy drawn");
        }
    }

    public void drawEnemy3(Graphics g2d) {
        if (en3.getSize() == 1) {
            g2d.drawImage(en3.getIcon().getImage(), 892, 140, 150, 150, this);
        } else if (en3.getSize() == 2) {
            g2d.drawImage(en3.getIcon().getImage(), 892, 90, 200, 200, this);
        } else if (en3.getSize() == 3) {
            g2d.drawImage(en3.getIcon().getImage(), 892, 40, 250, 250, this);
        }
    }

    public void resetTurns() {
        trn = 0;
        trn1 = 0;
        trn2 = 0;
        trn3 = 0;
        trn4 = 0;
    }

    public void enemyAttack(int et) {
        if (attacking) {
            if (et == 1) {
                if (!(en1.getHp() < 0)) {
                    en1Attack();
                }
                else {
                    enemyTurn = 2;
                    enemyAttack(2);
                }
            } else if (et == 2) {
                if (!(en2.getHp() < 0)) {
                    en2Attack();
                }
                else {
                    enemyTurn = 3;
                    enemyAttack(3);
                }
            } else if (et == 3) {
                if (!(en3.getHp() < 0)) {
                    en3Attack();
                }
                else {
                    enemyTurn = 0;
                }
            }
            attacking = false;
        }
    }

    public void en1Attack() {
        int r = RNG();
        if (r >= 35) {
            handleAttack(en1, enemies1, 1);
        } else {
            handleAttack(en1, enemies1, 2);
        }
    }

    public void en2Attack() {
        int r = RNG();
        if (r >= 35) {
            handleAttack(en2, enemies2, 1);
        } else {
            handleAttack(en2, enemies2, 2);
        }
    }

    public void en3Attack() {
        int r = RNG();
        if (r >= 35) {
            handleAttack(en3, enemies3, 1);
        } else {
            handleAttack(en3, enemies3, 2);
        }
    }

    public void handleAttack(Enemies enmy, Queue<Enemies> q, int a) {
        int d = 0;
        int b = 0;
        if ((enmy == q.peek()) && (!(enmy.getHp() <= 0))) {
            if (a == 1) {
                if (enmy.getAction1().getName() == "slam") {
                    d = (enmy.getStrngth() + enmy.getAction1().getDamage() - player.getArmrp());
                    if (d < 0) {
                        d = 0;
                    }
                    player.setHp(player.getHp() - d);
                    message1 = ("The " + enmy.getName() + " slammed into you and dealt " + d + " damage.");
                }
                else if (enmy.getAction1().getName() == "raise guard") {
                    b = enmy.getAction1().getBoost();
                    enmy.setArmrp(enmy.getArmrp() + b);
                    message1 = ("The " + enmy.getName() + " raised its guard and gained " + b + " armor points.");
                }
                else if (enmy.getAction1().getName() == "feed") {
                    d = (enmy.getStrngth() + enmy.getAction1().getDamage() - player.getArmrp());
                    player.setHp(player.getHp() - d);
                    message1 = ("The " + enmy.getName() + " force-fed you a piece of itself!");
                    message2 = ("You recieved " + d + " damage from extreme spiciness.");
                }
            }
            else if (a == 2) {
                if (enmy.getAp() > 0) {
                    enmy.setAp(enmy.getAp() - 1);
                    if (enmy.getAction2().getName() == "slam") {
                        d = (enmy.getStrngth() + enmy.getAction2().getDamage() - player.getArmrp());
                        if (d < 0) {
                            d = 0;
                        }
                        player.setHp(player.getHp() - d);
                        message1 = ("The " + enmy.getName() + " slammed into you and dealt " + d + " damage.");
                    }
                    else if (enmy.getAction2().getName() == "raise guard") {
                        b = enmy.getAction2().getBoost();
                        enmy.setArmrp(enmy.getArmrp() + b);
                        message1 = ("The " + enmy.getName() + " raised its guard and gained " + b + " armor points.");
                    }
                    else if (enmy.getAction2().getName() == "feed") {
                        d = (enmy.getStrngth() + enmy.getAction2().getDamage() - player.getArmrp());
                        if (d < 0) {
                            d = 0;
                        }
                        player.setHp(player.getHp() - d);
                        message1 = ("The " + enmy.getName() + " force-fed you a piece of itself!");
                        message2 = ("You received " + d + " damage from extreme spiciness.");
                    }
                }
                else {
                    if (enmy.getAction2().getName() == "feed") {
                        message1 = ("The " + enmy.getName() + " is too small to feed you some of itself.");
                    }
                    else {
                        message1 = ("The " + enmy.getName() + " doesn't have enough AP to perform its special move.");
                    }
                }
            }
        }

        transition = true;
    }

    public void playerAttack() {
        int d = 0;
        String e1Name = en1.getName();
        System.out.println(e1Name);
        String e2Name = en2.getName();
        System.out.println(e2Name);
        String e3Name = en3.getName();
        System.out.println(e3Name);

        if (target == 1) {
            d = (player.getStrngth() - en1.getArmrp());
            if (d < 1) {
                d = 1;
            }
            en1.setHp(en1.getHp() - d);
            resetStrings();
            message1 = "You dealt " + d + " damage to the " + en1.getName();
        } else if (target == 2) {
            d = (player.getStrngth() - en2.getArmrp());
            if (d < 1) {
                d = 1;
            }
            en2.setHp(en2.getHp() - d);
            resetStrings();
            message1 = "You dealt " + d + " damage to the " + en2.getName();
        } else if (target == 3) {
            d = (player.getStrngth() - en3.getArmrp());
            if (d < 1) {
                d = 1;
            }
            en3.setHp(en3.getHp() - d);
            resetStrings();
            message1 = "You dealt " + d + " damage to the " + en3.getName();
        }
        attacking = false;
        transition = true;

        if (en1.getHp() <= 0) {
            en1.setHp(0);
            if (target == 1) {
                if (e1Name == "Spook Face") {
                    message5 = ("The Spook Face was defeated.");
                }
                else if (e1Name == "Chocolate Blorp") {
                    message5 = ("The Chocolate Blorp was defeated.");
                }
                else if (e1Name == "Flamito") {
                    message5 = ("The Flamito was defeated.");
                }
            }
            progress = progress + 1;
        }
        if (en2.getHp() <= 0) {
            en2.setHp(0);
            if (target == 2) {
                if (e2Name == "Spook Face") {
                    message5 = ("The Spook Face was defeated.");
                }
                else if (e2Name == "Chocolate Blorp") {
                    message5 = ("The Chocolate Blorp was defeated.");
                }
                else if (e2Name == "Flamito") {
                    message5 = ("The Flamito was defeated.");
                }
            }
            progress = progress + 1;
        }
        if (en3.getHp() <= 0) {
            en3.setHp(0);
            if (target == 3) {
                if (e3Name == "Spook Face") {
                    message5 = ("The Spook Face was defeated.");
                }
                else if (e3Name == "Chocolate Blorp") {
                    message5 = ("The Chocolate Blorp was defeated.");
                }
                else if (e3Name == "Flamito") {
                    message5 = ("The Flamito was defeated.");
                }
            }
            progress = progress + 1;
        }

        if (en1.getHp() == 0 && en2.getHp() == 0 && en3.getHp() == 0) {
            enemyTurn = 4;
        }

        /*System.out.println(e1Name);
        System.out.println(e2Name);
        System.out.println(e3Name);*/

    }

    public void specialAttack(Enemies e, SpecialMoves m) {
        attacking = false;
        transition = true;
        int d = 0;
        int b = 0;
        String e1Name = en1.getName();
        System.out.println(e1Name);
        String e2Name = en2.getName();
        System.out.println(e2Name);
        String e3Name = en3.getName();
        System.out.println(e3Name);
        /*if (target == 1) {
            d = (player.getStrngth() - en1.getArmrp());
            en1.setHp(en1.getHp() - d);
            resetStrings();
            message1 = "You dealt " + d + " damage to the " + en1.getName();
        } else if (target == 2) {
            d = (player.getStrngth() - en2.getArmrp());
            en2.setHp(en2.getHp() - d);
            resetStrings();
            message1 = "You dealt " + d + " damage to the " + en2.getName();
        } else if (target == 3) {
            d = (player.getStrngth() - en3.getArmrp());
            en3.setHp(en3.getHp() - d);
            resetStrings();
            message1 = "You dealt " + d + " damage to the " + en3.getName();
        }*/
        resetStrings();
        if (player.getAp() > 0) {
            player.setAp(player.getAp() - 1);
            if (m instanceof BoostMove) {
                b = m.getBoost();
                if (m instanceof Buff) {
                    player.setStrngth(player.getStrngth() + b);
                    message2 = "You gained 3 strength!";
                }
                else if (m instanceof Resist) {
                    player.setArmrp(player.getArmrp() + b);
                    message2 = "You gained 3 Armor Points!";
                }
                else if (m instanceof Replenish) {
                    player.setHp(player.getHp() + b);
                    message2 = "You gained 7 Health Points!";
                }
            }
            else if (m instanceof Attack) {
                if (m instanceof FireBurst) {
                    if (target == 1) {
                        d = (m.getDamage() - en1.getArmrp());
                        if (en1.getType() == 1) {
                            d = (d + 5);
                        }
                        en1.setHp(en1.getHp() - d);
                        resetStrings();
                        message2 = "You dealt " + d + " damage to the " + en1.getName() + " using fire magic!";
                    } else if (target == 2) {
                        d = (m.getDamage() - en2.getArmrp());
                        if (en2.getType() == 1) {
                            d = (d + 5);
                        }
                        en2.setHp(en2.getHp() - d);
                        resetStrings();
                        message2 = "You dealt " + d + " damage to the " + en2.getName() + " using fire magic!";
                    } else if (target == 3) {
                        d = (m.getDamage() - en3.getArmrp());
                        if (en3.getType() == 1) {
                            d = (d + 5);
                        }
                        en3.setHp(en3.getHp() - d);
                        resetStrings();
                        message2 = "You dealt " + d + " damage to the " + en3.getName() + " using fire magic!";
                    }
                }
                else if (m instanceof ColdSnap) {
                    if (target == 1) {
                        d = (m.getDamage() - en1.getArmrp());
                        if (en1.getType() == 2) {
                            d = (d + 5);
                        }
                        en1.setHp(en1.getHp() - d);
                        resetStrings();
                        message2 = "You dealt " + d + " damage to the " + en1.getName() + " using ice magic!";
                    } else if (target == 2) {
                        d = (m.getDamage() - en2.getArmrp());
                        if (en2.getType() == 2) {
                            d = (d + 5);
                        }
                        en2.setHp(en2.getHp() - d);
                        resetStrings();
                        message2 = "You dealt " + d + " damage to the " + en2.getName() + " using ice magic!";
                    } else if (target == 3) {
                        d = (m.getDamage() - en3.getArmrp());
                        if (en3.getType() == 2) {
                            d = (d + 5);
                        }
                        en3.setHp(en3.getHp() - d);
                        resetStrings();
                        message2 = "You dealt " + d + " damage to the " + en3.getName() + " using ice magic!";
                    }
                }
                else if (m instanceof Skewer) {
                    if (target == 1) {
                        d = (m.getDamage() - en1.getArmrp());
                        en1.setHp(en1.getHp() - d);
                        resetStrings();
                        message1 = "You dealt " + d + " damage to the " + en1.getName() + " using your spear!";
                    } else if (target == 2) {
                        d = (m.getDamage() - en2.getArmrp());
                        en2.setHp(en2.getHp() - d);
                        resetStrings();
                        message1 = "You dealt " + d + " damage to the " + en2.getName() + " using your spear!";
                    } else if (target == 3) {
                        d = (m.getDamage() - en3.getArmrp());
                        en3.setHp(en3.getHp() - d);
                        resetStrings();
                        message1 = "You dealt " + d + " damage to the " + en3.getName() + " using your spear!";
                    }
                }

                if ((en1.getHp() <= 0) && (target == 1)) {
                    en1.setHp(0);
                    if (target == 1) {
                        if (e1Name == "Spook Face") {
                            message5 = ("The Spook Face was defeated.");
                        }
                        else if (e1Name == "Chocolate Blorp") {
                            message5 = ("The Chocolate Blorp was defeated.");
                        }
                        else if (e1Name == "Flamito") {
                            message5 = ("The Flamito was defeated.");
                        }
                    }
                    progress = progress + 1;
                }
                if ((en2.getHp() <= 0) && (target == 2)) {
                    en2.setHp(0);
                    if (target == 2) {
                        if (e2Name == "Spook Face") {
                            message5 = ("The Spook Face was defeated.");
                        }
                        else if (e2Name == "Chocolate Blorp") {
                            message5 = ("The Chocolate Blorp was defeated.");
                        }
                        else if (e2Name == "Flamito") {
                            message5 = ("The Flamito was defeated.");
                        }
                    }
                    progress = progress + 1;
                }
                if ((en3.getHp() <= 0) && (target == 3)) {
                    en3.setHp(0);
                    if (target == 3) {
                        if (e3Name == "Spook Face") {
                            message5 = ("The Spook Face was defeated.");
                        }
                        else if (e3Name == "Chocolate Blorp") {
                            message5 = ("The Chocolate Blorp was defeated.");
                        }
                        else if (e3Name == "Flamito") {
                            message5 = ("The Flamito was defeated.");
                        }
                    }
                    progress = progress + 1;

                }

                if (en1.getHp() == 0 && en2.getHp() == 0 && en3.getHp() == 0) {
                    enemyTurn = 4;
                    player.setHp(player.getHp() + 5);
                }

                /*System.out.println(e1Name);
                System.out.println(e2Name);
                System.out.println(e3Name);*/
                //System.out.println("en2 Hp: " + en2.getHp());

            }
        }
        else {
            message2 = ("You don't have any AP!");
        }
    }



	//DO NOT DELETE
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}




    //DO NOT DELETE
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		key= e.getKeyCode();
		System.out.println(key);
		
		if (screen == 'C') {
			if (key == 65) {
				player = playerOptions.get(0);
				screen = 'P';
			}
			else if (key == 66) {
				player = playerOptions.get(1);
				screen = 'P';
			}
			else if (key == 67) {
				player = playerOptions.get(2);
				screen = 'P';
			}
            else if (key == 68) {
				player = playerOptions.get(3);
				screen = 'P';
                illegalRun = true;
			}
		}

		if ((key == 32) && (screen == 'P')) {
            screen = 'G';
            resetStrings();
            //playerTurn = true;
            enemyTurn = 0;
            attacking = true;
            shwTargt = true;
        }

        if ((key == 78) && (screen == 'G') && (transition)) {
            System.out.println("enemyTurn: " + enemyTurn);
            enemyTurn = enemyTurn + 1;
            if (enemyTurn > 3) {
                enemyTurn = 0;
            }
            System.out.println("enemyTurn: " + enemyTurn);
            resetStrings();
            transition = false;
            attacking = true;
        }

        if ((key == 78) && (screen == 'G') && (enemyTurn == 0)) {
            enemyTurn = 0;
            transition = false;
            attacking = true;
            shwTargt = true;
            shwMainActns = true;
            shwActns = false;
        }

        if ((key == 39) && (shwTargt) && (screen == 'G') && (attacking) && (!frstBtle)) {
            if (target == 3) {
                target = 1;
            } else {
                target = target + 1;
            }
        }

        if ((key == 37) && (shwTargt) && (screen == 'G') && (attacking) && !frstBtle) {
            if (target == 1) {
                target = 3;
            } else {
                target = target - 1;
            }
        }

        if ((key == 65) && (shwMainActns) && (!shwActns) && (screen == 'G') && (attacking)) {
            if (select == 1) {
                select = 2;
            } else {
                select = select - 1;
            }
        }

        if ((key == 68) && (shwMainActns) && (!shwActns) && (screen == 'G') && (attacking)) {
            if (select == 2) {
                select = 1;
            } else {
                select = select + 1;
            }
        }

        if ((key == 32) && (shwMainActns) && (!shwActns) && (screen == 'G') && (attacking)) {
            if (select == 1) {
                playerAttack();
            } else if (select == 2) {
                shwActns = true;
            }
        }

        if ((key == 65) && (shwActns) && (screen == 'G') && (attacking)) {
            if (actSelect == 1) {
                actSelect = 2;
            } else {
                actSelect = actSelect - 1;
            }
        }

        if ((key == 68) && (shwActns) && (screen == 'G') && (attacking)) {
            if (actSelect == 2) {
                actSelect = 1;
            } else {
                actSelect = actSelect + 1;
            }
        }

        if ((key == 82) && (shwActns) && (screen == 'G') && (attacking)) {
            shwActns = false;
            shwMainActns = true;
        }

        if ((key == 66) && (shwActns) && (screen == 'G') && (attacking)) {
            if (actSelect == 1) {
                if (target == 1) {
                    specialAttack(en1, player.getAction1());
                }
                else if (target == 2) {
                    specialAttack(en2, player.getAction1());
                }
                else if (target == 3) {
                    specialAttack(en3, player.getAction1());
                }
            }
            else if (actSelect == 2) {
                if (target == 1) {
                    specialAttack(en1, player.getAction2());
                }
                else if (target == 2) {
                    specialAttack(en2, player.getAction2());
                }
                else if (target == 3) {
                    specialAttack(en3, player.getAction2());
                }
            }
            shwActns = false;
        }
		
	
	}


	//DO NOT DELETE
	@Override
	public void keyReleased(KeyEvent e) {
		
		
		
		
	}



	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//x=arg0.getX();
		//y=arg0.getY();
	}



	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("entered");
	}



	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("exited");
	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		System.out.println("you clicked at"+ arg0.getY());
		//x=arg0.getX();
		//y=arg0.getY();
		
	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	

	
}