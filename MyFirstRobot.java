package man;
import robocode.*;
import java.awt.Color;
import java.util.*;


 public class MyFirstRobot extends Robot {
	 
	 public class globalVar {
		 public static double energy=0;
		 public static double pastBearing=361;
	 }
	 
	 
	 
     public void run() {
    	 
    	 // Color of the robot (pink)
    	 setBodyColor(Color.PINK);
    	 
    	 
    	 while(true) {        
        	 
    		 
    		 
    		 this.turnRadarLeft(360);
        	 
    		 
        	 
        	         	 
        	 
        	        	       	 
//	    	 if(closeToWall() == true) {
//	    		 antiWall();
//	    	 }
	    	

        	 
        	 
         }
     }

     
     

     
     
     public void onScannedRobot(ScannedRobotEvent e) {
   
    	    	 
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
    	 

    	 // Block the radar on the enemy
    	 this.turnRadarRight(this.getHeading() - this.getRadarHeading() + e.getBearing());
    	 
    	 // Block the gun on the enemy
    	 this.turnGunRight(this.getHeading() - this.getGunHeading() + e.getBearing());
    	 
    	
    	 
    	 // Fire power formula
    	 double bearing = e.getBearing();
    	 double enemyVelocity = e.getVelocity();
    	
    	 
    	 	if(enemyVelocity == 0) { // enemy is immobile
    	 		fire(1);
     		} else if(bearing != globalVar.pastBearing) { // enemy is moving
     			
	    		 if(bearing > globalVar.pastBearing) { // enemy robot moved to right
	    			 this.turnGunRight(20);
	    			 this.fire(2);
	    		 } else if(bearing < globalVar.pastBearing) { // enemy robot moved to left
	    			 this.turnGunLeft(20);
	    			 this.fire(2); 	 
    		 } 
    	 } 
    	 
    	 //this.fire(Math.min(400 / e.getDistance(), 3));
    	 
    	 globalVar.pastBearing = bearing;
    	 
    	
    	 // Dodge bot
    	 double currentEnergy = e.getEnergy();
    	 long random =  Math.round( Math.random() ); // 0 or 1
    	 
    	 if(currentEnergy < globalVar.energy) { // Enemy lost energy
    		 if(random == 1) this.ahead(60);
    		 else if(random == 0) this.ahead(-60);
    	 }
    
    	 globalVar.energy = currentEnergy;

    	 
    

    	 
    	 
    	 
     }
     
     public void onHitRobot(HitRobotEvent INI) {
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
 		//antiWall();
 		
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
 			newAngle(0);		
 		}
 		
 		// TOP WALL
 		if(getY() >= getBattleFieldHeight() - 100)  { 
 			newAngle(180);
 		}
 		
 		// LEFT WALL
 		if(getX() <= 100) {
 			newAngle(90);
 		}
 		
 		// RIGHT WALL
 		if(getX() >= (getBattleFieldWidth() - 100)) {
 			newAngle(270);
 		}

 		
 		
 	}
 
 	/************************************************************************
 	 *	Rotation of body/weapon/radar of the robot to the desire angle (LEFT ROTATION)
 	 */ 	
 	public void newAngle(double newAngle) {
 		
 		double aux = this.getHeading() - newAngle;
 		
 		if(this.getHeading() >=0) {
 			aux = newAngle - this.getHeading();
 			this.turnRight(aux);
 		} else {
 			aux = newAngle - this.getHeading();
 			this.turnRight(aux);
 		}
 	}
 	
 	 
 	




 	
 	
 	
 	
 	
 
 	
 	
 	
 	
 	
 
 	
 	

 	}
 	
 	
     
 
 

 
 
 