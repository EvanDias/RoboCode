package slete;
import java.awt.*;
import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;

public class slete extends AdvancedRobot {

	boolean movingForward;
	double exactLocation;


	public void run() {

		//Robot personalization
		setBodyColor(Color.blue);
		setGunColor(Color.orange);
		setRadarColor(Color.red);
		setScanColor(Color.red);
		setBulletColor(Color.white);

		//Turn the three robot components (Gun, Radar, Body) to turn independent from each other
		setAdjustRadarForRobotTurn(true);
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);


		// Turn the radar infinitely
		setTurnRadarRightRadians(Double.POSITIVE_INFINITY);

		while (true) {

			this.setAhead(200);

			this.setTurnRight(90);

			scan();
		}
	}



	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {

		// Find the exact location of the enemy robot
		double absoluteBearing = this.getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - this.getGunHeading());

		// Turn the gun to the enemy
		this.turnGunRight(bearingFromGun);

		/*
		 * if (enemy distance >= 400) THEN fire_power = (between 0 and 1)
		 * else if (enemy distance <= 400) THEN -> fire_power = (between 1 and 3)
		*/
		this.setFire(Math.min(400 / e.getDistance(), 3));
	}



	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {

		this.setAhead(-100);

		execute();
	}



	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {

		reverseDirection();
	}



	/**
	 * onHitWall: What to do when you hit a robot
	 */
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



	/*
 	*	 reverseDirection: Reverses 180º degrees the direction the robot is heading
 	*/
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



	/*
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



 	/*
 	*	antiWall:
 	*	The center of the battlefield is calculated
 	*	A vector is formed between the battlefield center and the robot´s position to discover the angle (relative to the X axis)
 	*	With some mathematical formulas we can get the robot to head to the center of the battlefield therefore staying away from the walls
 	*/
	public void antiWall() {

		// Battlefield size
		double height = this.getBattleFieldHeight();
		double width = this.getBattleFieldWidth();

		// Central point of the battlefield
		double center_x = (height / 2), center_y = (width / 2);

		// Vector
		double vetor_x = (center_x - this.getX());
		double vetor_y = (center_y - this.getY());

		// Angle relative to the x axis
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

		//this.ahead(81);
	}

}