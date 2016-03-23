package org.usfirst.frc.team668.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Autonomous {

	public static boolean fired = false;
	public static void driveUnderBarAuton(Robot r){
		/*
		boolean doneRight = DriveController.rightMove(RobotMap.DRIVE_UNDER_BAR_RIGHT_DISTANCE);
		boolean doneLeft = DriveController.leftMove(RobotMap.DRIVE_UNDER_BAR_LEFT_DISTANCE);
		
		while(r.isAutonomous() && r.isEnabled() && (!doneRight || !doneLeft)){
			doneRight = DriveController.rightMove(RobotMap.DRIVE_UNDER_BAR_RIGHT_DISTANCE);
			doneLeft = DriveController.leftMove(RobotMap.DRIVE_UNDER_BAR_LEFT_DISTANCE);
		}
		
		DriveController.stop();
		
		return;
		*/
		
		switch (RobotMap.autonStateForward){
		
		case RobotMap.DRIVE_FORWARD_STATE:
			boolean doneRight = DriveController.rightMove(RobotMap.DRIVE_UNDER_BAR_RIGHT_DISTANCE);
			boolean doneLeft = DriveController.leftMove(RobotMap.DRIVE_UNDER_BAR_LEFT_DISTANCE);
			if (doneRight || doneLeft){
				RobotMap.autonStateForward = RobotMap.STOP_STATE;
			}
			break;
			
		case RobotMap.STOP_STATE:
			DriveController.stop();
		}
	}
	
	public static void driveAndShootCameraAuton(Robot r){
	/*	
		RobotMap.hoodState = RobotMap.HOOD_ZERO_STATE;
		
		boolean doneRight = DriveController.rightMove(RobotMap.DRIVE_AND_SHOOT_RIGHT_DISTANCE);
		boolean doneLeft = DriveController.leftMove(RobotMap.DRIVE_AND_SHOOT_LEFT_DISTANCE);
		
		while(r.isAutonomous() && r.isEnabled() && (!doneRight || !doneLeft)){
			doneRight = DriveController.rightMove(RobotMap.DRIVE_AND_SHOOT_RIGHT_DISTANCE);
			doneLeft = DriveController.leftMove(RobotMap.DRIVE_AND_SHOOT_LEFT_DISTANCE);
		}
		
		//Robot.robotDrive.drive(0.0, 0.0);
		
		DriveController.stop();
		
		int ref = RobotMap.FAR_FIRE_SPEED;
		Shooter.setPID(ref);
		
		while(r.isAutonomous() && r.isEnabled() && Robot.distance == 0.0){
			DriveController.turnInPlace(RobotMap.DRIVE_AND_SHOOT_TURN_SPEED);
		}
		
		//Robot.robotDrive.drive(0.0, 0.0);
		DriveController.stop();
		
		while(r.isAutonomous() && r.isEnabled() && (Robot.azimuth > RobotMap.AZIMUTH_RANGE)
				|| (Robot.azimuth < 360 - RobotMap.AZIMUTH_RANGE) && (Robot.azimuth !=400) ){
			DriveController.aimPI();
		}
		
		
		
		RobotMap.hoodState = RobotMap.HOOD_GET_STATE;
		while ( Math.abs(Shooter.angle - Robot.pot.getValue()) <= RobotMap.ACCEPTABLE_HOOD_RANGE){
			RobotMap.hoodState = RobotMap.HOOD_GET_STATE;
		}
		
		while (r.isEnabled() && r.isAutonomous() && !fired){
			if (Math.abs(ref - Robot.canTalonFlyWheel.getSpeed()) <= RobotMap.FAR_FIRE_SPEED_RANGE){ //waits for the speed of the motor to be correct 
				Intake.spin(RobotMap.FIRE_INTAKE_SPEED);
				if (Robot.opticSensor.get()){ //the sensor returns true once the ball has been fired. 
					long time = System.currentTimeMillis(); //wait for the ball to be completely out of the firing chamber
					if (System.currentTimeMillis() - time >= RobotMap.BALL_WAIT_TIME){
						Robot.canTalonFlyWheel.disable(); //this might not allow the flywheel to start back up
						Intake.stop(); //stops the intake 
						fired = true; //kills the firing sequence
					}
				}
			}
		}
		
		return;
		*/
		
		switch (RobotMap.autonStateShoot){
		case RobotMap.DRIVE_FORWARD_SHOOT_STATE:
			System.out.println("DF");
			boolean doneRight = DriveController.rightMove(RobotMap.DRIVE_AND_CLOSE_DISTANCE);
			boolean doneLeft = DriveController.leftMove(RobotMap.DRIVE_AND_CLOSE_DISTANCE);
			if (doneRight || doneLeft){
				DriveController.stop();
				Robot.intakePiston.set(DoubleSolenoid.Value.kReverse);
				RobotMap.autonStateShoot = RobotMap.DRIVE_FORWARD_COMPLETE_STATE;
			}
			break;
		
		case RobotMap.DRIVE_FORWARD_COMPLETE_STATE:
		System.out.println("DC");
			boolean finishedRight = DriveController.rightMove(RobotMap.DRIVE_AND_SHOOT_DISTANCE);
			boolean finishedLeft = DriveController.leftMove(RobotMap.DRIVE_AND_SHOOT_DISTANCE);
			if (finishedRight || finishedLeft){
				DriveController.stop();
				Robot.intakePiston.set(DoubleSolenoid.Value.kForward);
				RobotMap.autonStateShoot = RobotMap.TURN_STATE;
			}
			
			break;
			
		case RobotMap.TURN_STATE:
			
			System.out.print("TURN");
			System.out.print(" Azimuth: "  + Robot.azimuth);
			System.out.println(" Distance: " + Robot.distance);
			DriveController.turnInPlace(RobotMap.DRIVE_AND_SHOOT_TURN_SPEED);
			
			if (Robot.distance != 0.0 && (Robot.azimuth < 10 || Robot.azimuth > 350) && Robot.azimuth != 400){
				DriveController.stop();
				RobotMap.autonStateShoot = RobotMap.AIM_STATE;
			}
			break;
			
		case RobotMap.AIM_STATE:
			DriveController.aimPI();
			System.out.println("AIM");
			if ((Robot.azimuth < RobotMap.AZIMUTH_RANGE)
				|| (Robot.azimuth > (360 - RobotMap.AZIMUTH_RANGE)) && (Robot.azimuth !=400)){
				DriveController.stop();
				RobotMap.autonStateShoot = 9;
			}
			break;
			
		case 9:
			RobotMap.hoodState = RobotMap.HOOD_GET_STATE;
			if (Math.abs(Shooter.angle - Robot.pot.getValue()) < RobotMap.ACCEPTABLE_HOOD_RANGE){
				RobotMap.autonStateShoot = RobotMap.SPIN_STATE;
			}
			
		case RobotMap.SPIN_STATE:
			int ref = RobotMap.FAR_FIRE_SPEED;
			Shooter.setPID(ref);
			RobotMap.autonStateShoot = RobotMap.FIRE_SHOOT_STATE;
			break;
			
		case RobotMap.FIRE_SHOOT_STATE:
			System.out.println("RPM: " + Robot.canTalonFlyWheel.getSpeed());
			if (Math.abs(Robot.canTalonFlyWheel.getSpeed() - RobotMap.FAR_FIRE_SPEED) < RobotMap.FAR_FIRE_SPEED_RANGE){
				Shooter.fire(-RobotMap.FIRE_INTAKE_SPEED);
				long time = System.currentTimeMillis(); //wait for the ball to be completely out of the firing chamber
				if (System.currentTimeMillis() - time >= RobotMap.BALL_WAIT_TIME){
					Intake.stop();
					RobotMap.autonStateShoot = RobotMap.DONE_STATE;
				}
			}
			break;
			
		case RobotMap.DONE_STATE:
			DriveController.stop();
		}
	}
	
	public static void driveAndShootPIDAuton(Robot r){
	/*	
		boolean doneRight = DriveController.rightMove(RobotMap.DRIVE_AND_SHOOT_RIGHT_DISTANCE);
		boolean doneLeft = DriveController.leftMove(RobotMap.DRIVE_AND_SHOOT_LEFT_DISTANCE);
		
		while(r.isAutonomous() && r.isEnabled() && (!doneRight || !doneLeft)){
			doneRight = DriveController.rightMove(RobotMap.DRIVE_AND_SHOOT_RIGHT_DISTANCE);
			doneLeft = DriveController.leftMove(RobotMap.DRIVE_AND_SHOOT_LEFT_DISTANCE);
		}
		
		DriveController.stop();
		
		boolean turnRight = DriveController.rightMove(-RobotMap.TURN_RIGHT_AUTON_DISTANCE);
		boolean turnLeft = DriveController.leftMove(RobotMap.TURN_LEFT_AUTON_DISTANCE);
		
		while (r.isEnabled() && r.isAutonomous() && (!turnRight || !turnLeft)){
			turnRight = DriveController.rightMove(RobotMap.TURN_RIGHT_AUTON_DISTANCE);
			turnLeft = DriveController.leftMove(RobotMap.TURN_LEFT_AUTON_DISTANCE);
		}
		

		int ref = RobotMap.FAR_FIRE_SPEED;
		Shooter.setPID(ref);
		
		RobotMap.hoodState = RobotMap.HOOD_GET_STATE;
		while ( Math.abs(Shooter.angle - Robot.pot.getValue()) <= RobotMap.ACCEPTABLE_HOOD_RANGE){
			RobotMap.hoodState = RobotMap.HOOD_GET_STATE;
		}
		
		
		while (r.isEnabled() && r.isAutonomous() && !fired){
			if (Math.abs(ref - Robot.canTalonFlyWheel.getSpeed()) <= RobotMap.FAR_FIRE_SPEED_RANGE){ //waits for the speed of the motor to be correct 
				Intake.spin(RobotMap.FIRE_INTAKE_SPEED);
				if (Robot.opticSensor.get()){
					long time = System.currentTimeMillis();
					if ( System.currentTimeMillis() - time >= RobotMap.BALL_WAIT_TIME){
						//Robot.canTalonFlyWheel.disable();
						Intake.stop();
						fired = true;
					}
				}
			}
		}
		
		return;
		
		*/
	}
	
	public static void driveToDefenseAuton(Robot r){
		boolean doneRight = DriveController.rightMove(RobotMap.DRIVE_TO_DEFENSE_RIGHT_DISTANCE);
		boolean doneLeft = DriveController.leftMove(RobotMap.DRIVE_TO_DEFENSE_LEFT_DISTANCE);
		
		while(r.isAutonomous() && r.isEnabled() && (!doneRight || !doneLeft)){
			doneRight = DriveController.rightMove(RobotMap.DRIVE_TO_DEFENSE_RIGHT_DISTANCE);
			doneLeft = DriveController.leftMove(RobotMap.DRIVE_TO_DEFENSE_LEFT_DISTANCE);
			System.out.println("Forward");
		}
		
		DriveController.stop();
		
		return;
	}
	public static void stopAuton(){
		
		return;
		
	}
	
	public static void leeroyJenkins(Robot r){
		
		long time = System.currentTimeMillis();
		
		while (r.isEnabled() && r.isAutonomous() && (Math.abs(Robot.canTalonFrontRight.getEncPosition()) < Math.abs(RobotMap.LEEROY_JENKINS_DISTANCE))
				&& (Math.abs(Robot.canTalonFrontLeft.getEncPosition()) < Math.abs(RobotMap.LEEROY_JENKINS_DISTANCE))){
			DriveController.drive(RobotMap.LEEROY_JENKINS_SPEED);
		}
		
		DriveController.stop();
		return;
		
	}
	
	public static void chivelDeFriseAuton(Robot r){
		
		boolean doneRight = DriveController.rightPID(RobotMap.DRIVE_TO_DEFENSE_RIGHT_DISTANCE);
		boolean doneLeft = DriveController.leftPID(RobotMap.DRIVE_TO_DEFENSE_LEFT_DISTANCE);
		
		while(r.isAutonomous() && r.isEnabled() && (!doneRight || !doneLeft)){
			doneRight = DriveController.rightPID(RobotMap.DRIVE_TO_DEFENSE_RIGHT_DISTANCE);
			doneLeft = DriveController.leftPID(RobotMap.DRIVE_TO_DEFENSE_LEFT_DISTANCE);
		}
		
		DriveController.stop();

	}
	
	public static void spyBotShotAutonomous(Robot r){
		
		boolean completeRight = DriveController.rightMove(0);
		boolean completeLeft = DriveController.leftMove(0);
		
		while(r.isAutonomous() && r.isEnabled() && (!completeRight || !completeLeft)){
			completeRight = DriveController.rightPID(0);
			completeLeft = DriveController.leftPID(0);
		}
		
		while (r.isAutonomous() && r.isEnabled() && (Robot.azimuth > RobotMap.AZIMUTH_RANGE)
				|| (Robot.azimuth < 360 - RobotMap.AZIMUTH_RANGE) && (Robot.azimuth !=400) ){
			DriveController.aimPI();
		}
		
		DriveController.stop();
		
		int ref = RobotMap.FAR_FIRE_SPEED;
		Shooter.setPID(ref);
		
		RobotMap.hoodState = RobotMap.HOOD_GET_STATE;
		while ( Math.abs(Shooter.angle - Robot.pot.getValue()) <= RobotMap.ACCEPTABLE_HOOD_RANGE){
			RobotMap.hoodState = RobotMap.HOOD_GET_STATE;
		}
		
		while (r.isEnabled() && r.isAutonomous() && !fired){
			if (Math.abs(ref - Robot.canTalonFlyWheel.getSpeed()) <= RobotMap.FAR_FIRE_SPEED_RANGE){ //waits for the speed of the motor to be correct 
				Intake.spin(RobotMap.FIRE_INTAKE_SPEED);
				if (Robot.opticSensor.get()){
					long time = System.currentTimeMillis();
					if ( System.currentTimeMillis() - time >= RobotMap.BALL_WAIT_TIME){
						Robot.canTalonFlyWheel.disable(); //this might not allow the flywheel to start back up try adding .enable()
						Intake.stop();
						fired = true;
					}
				}
			}
		}
		
		return;
		
	}
	
	
	
}
												