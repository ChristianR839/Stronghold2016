package org.usfirst.frc.team839.robot.util;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

import java.util.Iterator;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionCalib implements ITableListener{
	private double xCoord;
	private double yCoord;
	private double distance;
	private NetworkTable nt;
	//private ITableListener table;
	/**
	 * 
	 */
	public VisionCalib() {
		
		//NetworkTable.setIPAddress("");
//		NetworkTable nt = NetworkTable.getTable("targeting");
//		nt.addTableListener(this);

		nt = NetworkTable.getTable("SmartDashboard");
		nt.addTableListener(this);
	}

	private void update(){
		//nt.beginTransaction();
		xCoord = nt.getNumber("COG_X", 1);
		yCoord = nt.getNumber("COG_Y", 0);
		distance = nt.getNumber("TL_TARGET_DISTANCE", 0);
		//nt.endTransaction();
	}
	
	public void valueChanged(ITable itable, String key, Object obj, boolean isNew)
	{
		update();
	}
	public void setNums()
	{
		nt.putNumber("COG_X", 0.0);
		nt.putNumber("COG_Y", 0.0);
		nt.putNumber("TL_TARGET_DISTANCE", 0.0);
	}
	public double getDistance() {return distance;}
	public double getX() {return xCoord;}
	public double getY() {return yCoord;}
	public NetworkTable getNt() {
		return nt;
	}
	public void setNt(String table) {
		this.nt = NetworkTable.getTable(table);
	}
	@Override
	public String toString()
	{
		update();
		
String key = null;
		for (Iterator<String> iterator = nt.getKeys().iterator(); iterator.hasNext();)
		{
			 System.out.println(key = iterator.next() + " ===> "+nt.getString(key,""));
			
		}
		String s="";
		s+="xCoord = "+xCoord;
		s+="\n"+"yCoord = "+yCoord;
		s+="\n"+"Distance = "+distance;
		return s;
	}
}