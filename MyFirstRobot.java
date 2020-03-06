package man;
import robocode.*;
import java.awt.*;
import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;



public class MyFirstRobot extends AdvancedRobot {
	boolean movingForward;
	double exactLocation;
	
	public void run() {
		setBodyColor(Color.blue);
		setGunColor(Color.orange);
		setRadarColor(Color.red);
		setScanColor(Color.red);
		setBulletColor(Color.white);
		setAdjustRadarForRobotTurn(true);
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true); 
		
		setTurnRadarRightRadians(Double.POSITIVE_INFINITY); 
		
				
		while (true) {
			
			scan();
			setAhead(100);
			
			setTurnRight(200);
			
			// call antiWall() to avoid the wall
			if(closeToWall() == true) antiWall();
			
		}
	}
	
	public void onScannedRobot(ScannedRobotEvent e) {
		// Calcula a localização exata do robot
		double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());

		// If it's close enough, fire!
		if (Math.abs(bearingFromGun) <= 3) {
			turnGunRight(bearingFromGun);
			// We check gun heat here, because calling fire()
			// uses a turn, which could cause us to lose track
			// of the other robot.
			if (getGunHeat() == 0) {
				fire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));
			}
		} // otherwise just set the gun to turn.
		// Note:  This will have no effect until we call scan()
		else {
			turnGunRight(bearingFromGun);
		}
		// Generates another scan event if we see a robot.
		// We only need to call this if the gun (and therefore radar)
		// are not turning.  Otherwise, scan is called automatically.
		if (bearingFromGun == 0) {
			scan();
		}
	}

	public void onHitByBullet(HitByBulletEvent e) {
		//turnRight(20);
	   // ahead(100);

	}
	

	
	public void reverseDirection() {
		if (movingForward) {
			turnRight(20);
			back(200);
			movingForward = false;
		} else {
			turnRight(25);
			ahead(200);
			movingForward = true;
		}
	}
	
	public void onHitWall(HitWallEvent e) {
		//reverseDirection();
	}
	
	public void onBulletHitBullet(BulletHitBulletEvent e) {
		reverseDirection();
	}
	
	public void onHitRobot(HitRobotEvent e) {
			if(e.getBearing() >= -180 && e.getBearing() <= 0) {
				exactLocation = e.getBearing() + (getHeading() - getGunHeading());
				setTurnGunRight(normalRelativeAngleDegrees(-exactLocation));
				setBack(100);
				setFire(3);
			}
			else if(e.getBearing() > 0 && e.getBearing() <= 180){
				exactLocation = e.getBearing() + (getHeading() - getGunHeading());
				setTurnGunRight(normalRelativeAngleDegrees(exactLocation));
				setBack(100);
				setFire(3);
			}
			
	}
	
	
	
	
	
	
 	/************************************************************************
 	*	Returns true/false if the robot is close (or not) to the wall 
 	*	Distance of 80 pixels
 	*/
 	public boolean closeToWall() {
 		double height = this.getBattleFieldHeight();
		double width = this.getBattleFieldWidth();
 		if(this.getX() <= 80 || this.getX() >= (width - 80) || this.getY() <= 80 || this.getY() >= (height - 80))
 			return true;
 		else
 			return false;
 	}
	
	
 	/************************************************************************
 	*	É calculado o centro do BattleField
 	*	Vetor formado entre o centro e o robot para descobrir qual o angulo (relativamente ao eixo dos x)
 	*	Fazendo uns simples calculos consegue-se apontar a HEAD para o centro afastando se assim da parede
 	*/	
	public void antiWall() {
		stop();
		// Battlefield size
		double height = this.getBattleFieldHeight();
		double width = this.getBattleFieldWidth();
		
		// Central point of the battlefield
		double center_x = (height / 2), center_y = (width / 2);
		
		// Vector 
		double vetor_x = (center_x - this.getX());
		double vetor_y = (center_y - this.getY());
		
		// Angulo formado relativamente ao eixo dos x
		double theta = (180.0 / Math.PI * Math.atan2(vetor_y, vetor_x));
		
		theta *= (-1);
			
		if(this.getHeading() > theta) { // Se o angulo da head foi maior que o angulo relativo ao centro
			this.turnLeft(this.getHeading() - (theta + 90));
		} else {
			if(this.getHeading() < 90 && this.getHeading() >= 0) 
				this.turnRight((90 - this.getHeading()) + theta);
			else
				this.turnRight(theta - this.getHeading());
			}

		this.ahead(81);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}