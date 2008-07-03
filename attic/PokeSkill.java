import java.io.*;

public class PokeSkill implements Serializable	{
	private String name, type, descr;
	private int pp, power;

	public PokeSkill ( String name, String type, String descr, int pp, int power )	{
		this.name = name;
		this.type = type;
		this.descr = descr;
		this.pp = pp;
		this.power = power;
	}

	public void setName ( String name )	{
		this.name = name;
	}

	public void setType ( String type )	{
		this.type = type;
	}

	public void setDescription ( String descr )	{
		this.descr = descr;
	}

	public void setPP ( int pp )	{
		this.pp = pp;
	}

	public void setPower ( int power )	{
		this.power = power;
	}

	public String getName()	{
		return name;
	}

	public String getType()	{
		return type;
	}

	public String getDescription()	{
		return descr;
	}

	public int getPP()	{
		return pp;
	}

	public int getPower()	{
		return power;
	}
	
	public String toString()	{
		return name + " - " + descr;
	}
	
	public String longDescription()	{
		return "Name: " + name + "\nType: " + type + "\nPP: " + pp + "\nPower: " + power + "\nDescription: " + descr;
	}
	
}