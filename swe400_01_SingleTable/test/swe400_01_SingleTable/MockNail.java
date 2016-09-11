package swe400_01_SingleTable;

import java.sql.SQLException;

public class MockNail extends Nail
{

	public MockNail(int id) throws ClassNotFoundException, SQLException
	{
		super(id);
	}
	public double getLength()
	{
		return super.getLength();
	}

}
