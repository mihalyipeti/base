package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private Thread setspeed;
	private boolean alarmState;

	public TrainControllerImpl(){
		setspeed = new Thread(){
			public void run(){
				setspeed.run();
				try{
					followSpeed();
					setspeed.sleep(2000);

					

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
	}

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;

		if(speedLimit > 500 || speedLimit < 0){
			setAlarmState(true);
		} else {
			setAlarmState(false);
		}

		enforceSpeedLimit();
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

	public void emergencyBreak(int speed) {
		if(speed > speedLimit)
			referenceSpeed = 0;
	}

	@Override
	public boolean getAlarmState() {
		return alarmState;
	}

	@Override
	public void setAlarmState(boolean alarmState) {
		this.alarmState = alarmState;
	}

}
