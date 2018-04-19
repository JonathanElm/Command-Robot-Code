package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controller {
	public double c = 0.1;
	public double deadzone = 0.2;
	public double max = 0.8;
	public double k = (max - c) / Math.log(2 - deadzone);
	
	public double cT = 0.08;
	public double deadzoneT = 0.08;
	public double maxT = 0.4;
	public double kT = (maxT - cT) / Math.log(2 - deadzoneT);
	
	public enum Map {
		// MAP(extendLadder, retractLadder, coil1, coil2, unsafeOpenClaw, unsafeCloseClaw, turnLeft, turnRight)
		SIDEWINDER_CONTROLLER(3, 4, 7, 5, 1, 5), 
		BAD_CONTROLLER(6, 7, 8, 9, 12, 1, 4, 5);
		
		private int[] buttons;
		
		private Map(int... buttons) {
			this.buttons = buttons;
		}
		
		public int extendLadder() {
			return buttons[0];
		}
		
		public int retractLadder() {
			return buttons[1];
		}
		
		public int coil1() {
			return buttons[2];
		}
		
		public int coil2() {
			return buttons[3];
		}
		
		public int unsafeOpenClaw() {
			return buttons[4];
		}
		
		public int unsafeCloseClaw() {
			return buttons[5];
		}
		
		public int turnLeft() {
			return buttons[6];
		}
		
		public int turnRight() {
			return buttons[7];
		}
		
		public boolean turnButtons() {
			return buttons.length > 6;
		}
	}

	public Joystick joystick;
	public Map map;
	
	public Controller(Joystick joystick) {
		this.joystick = joystick;
		using(Map.SIDEWINDER_CONTROLLER);
	}
	
	public double getHorizontalMovement() {
		double x = joystick.getX();
		return Math.abs(x) >= deadzone ? k * Math.signum(x) * (Math.log(Math.abs(x) + 1 - deadzone) + c) : 0;
	}

	public double getLateralMovement() {
		double y = -joystick.getY();
		return Math.abs(y) >= deadzone ? k * Math.signum(y) * (Math.log(Math.abs(y) + 1 - deadzone) + c) : 0;
	}
	
	public double getRotation() {
		if(map.turnButtons()) {
			return joystick.getRawButton(map.turnLeft()) ? -0.4 : joystick.getRawButton(map.turnRight()) ? 0.4 : 0;
		} else {
			double t = joystick.getZ();
			return Math.abs(t) >= deadzoneT ? kT * Math.signum(t) * (Math.log(Math.abs(t) + 1 - deadzoneT) + cT) : 0;
		}
	}
	
	public boolean extendLadder() {
		return joystick.getRawButton(map.extendLadder());
	}
	
	public boolean retractLadder() {
		return joystick.getRawButton(map.retractLadder());
	}
	
	public boolean coil() {
		return joystick.getRawButton(map.coil1()) && joystick.getRawButton(map.coil2());
	}
	
	public boolean unsafeOpenClaw() {
		return joystick.getRawButton(map.unsafeOpenClaw());
	}
	
	public boolean unsafeCloseClaw() {
		return joystick.getRawButton(map.unsafeCloseClaw());
	}
	
	public void using(Map map) {
		this.map = map;
	}
}