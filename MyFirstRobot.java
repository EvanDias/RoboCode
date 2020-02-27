package man;
import robocode.*;
import java.awt.Color;
import java.util.*;


 public class MyFirstRobot extends Robot {
	 
	 public class globalVar {
		 public static int a=0;
		 public static double b=0;
	 }
	 
	 
	 
     public void run() {
    	 
    	 // Color of the robot (pink)
    	 setBodyColor(Color.PINK);
    	 double z, x=0;
    	 
    	 //while(globalVar.a == 0) { turnRadarLeft(20); }
    	 
         while(true) {        
        	 
        	 this.turnRadarLeft(360);
        	 
        	        	       	 
        	 //if(closeToWall() == true) {
        	//	 antiWall();
        	 //}
        	
        	 
  
        	 
        	 
        	 
        	 
         }
     }

     
     
     
     
     
     public void onScannedRobot(ScannedRobotEvent e) {

    	 //this.ahead(50);
    	 
    	 
    	 // Facing my robot heading 90º to the enemy
    	 double enemyBearing = e.getBearing();
    	 
    	 if(enemyBearing > 0) { // positive getBearing()
    		 if(enemyBearing > 90)
    			 this.turnRight(enemyBearing - 90); 
    		 else if(enemyBearing < 90) 
    			 this.turnLeft(90 - enemyBearing);
    		 
    	 } else { // negative getBearing
    		if(enemyBearing < -90)
    			this.turnLeft(enemyBearing + 90); 
    		else if(enemyBearing > -90) 
    			this.turnRight(-90 - enemyBearing);
    	 }
    	 

    	 
    	 
    	 //System.out.println("Energy: " + enemyEnergy + "   MyEnergy: " + this.getEnergy());
    	 
     }
     
     
     
     
     
 	/************************************************************************
 	 * onHitByBullet: What to do when you're hit by a bullet
 	 */
 	public void onHitByBullet(HitByBulletEvent e) {
 		// Replace the next line with any behavior you would like
 		//back(10);
 	}
 	
 	
 	
 	/************************************************************************
 	 * onHitWall: What to do when you hit a wall
 	 */
 	public void onHitWall(HitWallEvent e) {
 		antiWall();
 		
 	}	
 	
 	
 	
 	
 	
 	/************************************************************************
 	 *	Returns true/false if the robot is close (or not) to the wall
 	 */
 	public boolean closeToWall() {
 		if(getX() <= 100 || getX() >= (getBattleFieldWidth() - 100) || getY() <= 100 || getY() >= (getBattleFieldHeight() - 100))
 			return true;
 		else
 			return false;
 	}
 	
 	
 	/************************************************************************
 	 *	Avoid the nearest wall (100 pixels distance)
 	 */
 	
 	public void antiWall() {
 		
 		
 		// DOWN WALL
 		if(getY() <= 100)  { 
 			if(getX() <= 150) {
 				this.turnLeft(newAngle(45));
 				this.ahead(40);
 			} else if(getX() >= 650) {
 				this.turnLeft(newAngle(315));
 				this.ahead(40);
 			} else {
 				this.turnLeft(newAngle(0));
 				this.ahead(40);
 			}		
 		}
 		
 		// TOP WALL
 		if(getY() >= getBattleFieldHeight() - 100)  { 
 			if(getX() <= 150) {
 				this.turnLeft(newAngle(135));
 				this.ahead(40);
 			} else if(getX() >= 650) {
 				this.turnLeft(newAngle(225));
 				this.ahead(40);
 			} else {
 				this.turnLeft(newAngle(180));
 				this.ahead(40);
 			}		
 		}
 		
 		// LEFT WALL
 		if(getX() <= 100) {
 			if(getY() <= 200) {
 				this.turnLeft(newAngle(45));
 				this.ahead(40);
 			} else if(getY() >= 600) {
 				this.turnLeft(newAngle(135));
 				this.ahead(40);
 			} else {
 				this.turnLeft(newAngle(90));
 				this.ahead(40);
 			}
 		}
 		
 		// RIGHT WALL
 		if(getX() >= (getBattleFieldWidth() - 100)) {
 			if(getY() <= 200) {
 				this.turnLeft(newAngle(315));
 				this.ahead(40);
 			} else if(getY() >= 600) {
 				this.turnLeft(newAngle(225));
 				this.ahead(40);
 			} else {
 				this.turnLeft(newAngle(270));
 				this.ahead(40);
 			}
 		}

 		
 		
 	}
 
 	/************************************************************************
 	 *	Rotation of body/weapon/radar of the robot to the desire angle (LEFT ROTATION)
 	 */ 	
 	public double newAngle(double newAngle) {
 		double aux = getHeading() - newAngle;
 		
 		return aux;
 	}
 	
 	 
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	/*
 	public double fixRadarInEnemy(double enemyAngle, double aux) {
 		
 		System.out.println("enemyAngle: " + enemyAngle + "  aux: " + aux);

	 		
 		
	 		if(enemyAngle > aux) { 
	 			if(enemyAngle < 0)
	 				this.turnRadarRight(aux*(-1) - enemyAngle*(-1));
	 			else
	 				this.turnRadarRight(aux - enemyAngle);
	 				
	 		} else { 
	 			if(enemyAngle < 0)
	 				this.turnRadarLeft(enemyAngle*(-1) - aux*(-1));
	 			else
	 				this.turnRadarLeft(enemyAngle - aux);
	 		}
	 		
	 		
 		return enemyAngle;
 	}
 	*/
 	
 	
 	/*
 	public void changeGunAngle(double enemyAngle) {
 		double aux, currentGun = getRadarHeading(); 
 		
 		if(180 <= currentGun && currentGun <= 360) {
 			aux = (360 - currentGun) + getHeading() + enemyAngle;
 			turnRadarRight(aux);
 		} else {
 			aux = (90 - currentGun) + getHeading() + enemyAngle; 
 			turnRadarLeft(aux);
 		}
 	}	*/
 	
 	

 	}
 	
 	
     
 
 

 
 
 