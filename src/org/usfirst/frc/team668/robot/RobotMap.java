package org.usfirst.frc.team668.robot;

public class RobotMap {
	
	// Talon CAN IDs
	public static final int FLY_WHEEL_CAN_ID = 26;
	public static final int INTAKE_CAN_ID = 27;
	
	public static final int FRONT_LEFT_CAN_ID = 24;
	public static final int FRONT_RIGHT_CAN_ID = 21;
	public static final int REAR_LEFT_CAN_ID = 23;
	public static final int REAR_RIGHT_CAN_ID = 22;
	
	public static final int SHOOTER_ANGLE_CAN_ID = 25;
	public static final int SHOOTER_ANGLE_TWO_CAN_ID = 28;
	
	public static final int OPTIC_SENSOR_DIGITAL_INPUT_PORT = 0;
	
	public static final int PCM_CAN_ID = 20;
	
	public static final int WHEEL_ID = 0;
	public static final int THROTTLE_ID = 1;
	public static final int OPERATOR_ID = 2;
	
	public static final int INTAKE_PISTON_EXPAND_CHANNEL = 0;
	public static final int INTAKE_PISTON_RETRACT_CHANNNEL = 1;
	public static final int PISTON_SHIFT_EXPAND_CHANNEL = 2;
	public static final int PISTON_SHIFT_RETRACT_CHANNEL = 3;
	
	
	public static final int MINIMIZE_BUTTON = 3; // throttle
	public static final int INTAKE_BUTTON = 2;
	public static final int REVERSE_BUTTON = 3;
	public static final int FIRE_BUTTON = 4;
	public static final int LOWER_INTAKE_BUTTON = 5;
	public static final int RISE_INTAKE_BUTTON = 6;
	public static final int STOP_FLYWHEEL_BUTTON = 7;
	public static final int CLOSE_ANGLE_BUTTON = 8;
	public static final int FAR_ANGLE_BUTTON = 9;
	public static final int COLLAPSE_BUTTON = 10;
	public static final int LOWER_BUTTON = 11;
	public static final int MANUAL_BUTTON = 12;
	public static final int MANUAL_HOOD_BUTTON = 12;
	public static final int RETURN_BUTTON = 1;
	public static final int LOW_GEAR_BUTTON = 7; //throttle
	public static final int HIGH_GEAR_BUTTON = 8; //throttle
	
	public static final boolean OPTIC_SENSOR_VALUE = Robot.opticSensor.get();
	
	public static final int INIT_STATE = 1;
	public static final int WAIT_FOR_BUTTON_STATE = 2;
	public static final int LOWER_INTAKE_STATE = 3;
	public static final int INIT_FIRE_STATE = 4;
	public static final int FAR_FIRE_STATE = 5;
	public static final int CLOSE_FIRE_STATE = 6;
	public static final int CLOSE_ANGLE_STATE = 7;
	public static final int FAR_ANGLE_STATE = 8;
	public static final int COLLAPSE_STATE = 9;
	public static final int MANUAL_OVERRIDE_STATE = 10;
	public static final int SET_FAR_PID_STATE = 11;
	public static final int SET_CLOSE_PID_STATE = 13;
	public static final int BALL_CLEAR_STATE = 12;
	public static final int DEFAULT_STATE = INIT_STATE;
	public static int currentState = DEFAULT_STATE;
	
	
	public static final int MANUAL_CLOSE_ANGLE_STATE = 0;
	public static final int MANUAL_FAR_ANGLE_STATE = 1;
	public static final int MANUAL_CONTROLL_HOOD_ANGLE_STATE = 2;
	public static final int MANUAL_FIRE_STATE = 3;
	public static final int MANUAL_WAIT_FOR_BUTTON_STATE = 4; 
	public static final int MANUAL_COLLAPSE_ANGLE_STATE = 5;
	public static final int MANUAL_BALL_CLEAR_STATE = 6;
	public static final int MANUAL_DEFAULT_STATE = MANUAL_WAIT_FOR_BUTTON_STATE;
	public static int manualState = DEFAULT_STATE;
	
	public static final int POT_ANALOG_INPUT_PORT = 0;
	public static final int LIMIT_SWITCH_DIGITAL_INPUT = 2;
	public static final int LIMIT_SWITCH_TWO_DIGITAL_INPUT = 3;
	
	
	
	//CONSTANTS
	public static final double CONSTANT_SPEED = 0;
	public static final double HOOD_SPEED = 1;
	public static final double ACCEPTABLE_HOOD_RANGE = 0;
	public static final double FAR_ANGLE_VALUE = 0;
	public static final double CLOSE_ANGLE_VALUE = 0;
	public static final double COLLAPSE_ANGLE_VALUE = 0;
	public static final double MAX_HOOD_POSITION = 0;
	public static final double MIN_HOOD_POSITION = 0;
	public static final double FAR_FIRE_SPEED_RANGE = 0;
	public static final double FIRE_INTAKE_SPEED = .8;
	public static final double BALL_WAIT_TIME = 200;
	public static final double MANUAL_CLOSE_FIRE_SPEED = .7;
	public static final double MANUAL_FAR_FIRE_SPEED = .9;
	
	public static final double FIRE_DISTANCE_1 = 0;
	public static final double FIRE_DISTANCE_2 = 0;
	public static final double FIRE_DISTANCE_3 = 0;
			
}

