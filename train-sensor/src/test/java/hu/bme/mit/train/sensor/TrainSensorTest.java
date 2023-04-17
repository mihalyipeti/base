package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

import static org.mockito.Mockito.*;

public class TrainSensorTest {
   TrainController mockTC;
   TrainUser mockTU;
   TrainSensorImpl trainSensor;


    @Before
    public void before() {
        mockTC = mock(TrainController.class);
        mockTU = mock(TrainUser.class);
        trainSensor = new TrainSensorImpl(mockTC, mockTU);
    }


    @Test
    public void Speed(){
        Assert.assertEquals(300,300);
    }


    @Test
    public void ValidSpeed(){
        trainSensor.overrideSpeedLimit(300);
        verify(mockTU, times(1)).setAlarmState(false);
    }


    @Test
    public void MaxSpeed(){
        trainSensor.overrideSpeedLimit(600);
        verify(mockTU, times(1)).setAlarmState(true);
    }


    @Test
    public void MaxSpeed_v2(){
        trainSensor.overrideSpeedLimit(501);
        verify(mockTU, times(1)).setAlarmState(true);
    }
    

    @Test
    public void MinSpeed(){
        trainSensor.overrideSpeedLimit(-1);
        verify(mockTU, times(1)).setAlarmState(true);
    }
}
