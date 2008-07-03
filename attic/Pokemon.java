import java.io.*;

public class Pokemon implements Serializable	{
	private int number;
	private String name, type;
	private String[] attack, breedingmoves, breedcompat, tmhm, evolution;
	private String location;
	
	public Pokemon ( int number )	{
		this.number = number;
		name = null;
		type = null;
		evolution = null;
		attack = null;
		breedingmoves = null;
		breedcompat = null;
		location = null;
	}
	
	public Pokemon ( int number, String name, String type, String[] evolution, String[] attack, String[] tmhm, String[] breedingmoves, String[] breedcompat, String location )	{
		this.number = number;
		this.name = name;
		this.type = type;
		this.evolution = evolution;
		this.attack = attack;
		this.tmhm = tmhm;
		this.breedingmoves = breedingmoves;
		this.breedcompat = breedcompat;
		this.location = location;
	}

	public int getNumber()	{
		return number;
	}
	
	public void setNameType ( String name, String type )	{
		this.name = name;
		this.type = type;
	}
	
	public void setType ( String type )	{
		this.type = type;
	}
	
	public String getName()	{
		return name;
	}
	
	public String getType()	{
		return type;
	}
	
	public void setEvolution ( String[] evolution )	{
		this.evolution = evolution;
	}
	
	public String[] getEvolution()	{
		return evolution;
	}
	
	public void setAttack ( String[] attacks )	{
		this.attack = attacks;
	}
	
	public void setTMHM ( String[] tmhm )	{
		this.tmhm = tmhm;
	}
	
	public void setBreedingMoves ( String[] breedingmoves )	{
		this.breedingmoves = breedingmoves;
	}

	public void setBreedingCompatibility ( String[] breedcompat )	{
		this.breedcompat = breedcompat;
	}
	
	public String[] getAttack()	{
		return attack;
	}
	
	public String[] getTMHM()	{
		return tmhm;
	}
	
	public String[] getBreedingMoves()	{
		return breedingmoves;
	}
	
	public String[] getBreedingList()	{
		return breedcompat;
	}

	public void foundAt ( String l )	{
		location = l;
	}
	
	public String foundAt()	{
		return location;
	}
	
	public String longDescription()	{
		String result;
		result = "#" + number + "\t" + name + "\n" + "Type: " + type + "\n" + "Evolves: ";
		for ( int i = 0; i < evolution.length; i++ )	{
			result += evolution[i];
			if ( i != evolution.length - 1 )
				result += ", ";
		}
		result += "\n\n" + "Attacks: ";
		for ( int i = 0; i < attack.length; i++)	{
			result += attack[i];
			if ( i != attack.length - 1 )
				result += ", ";
		}
		result += "\n\n" + "TM/HM Compatability: ";
		for ( int i = 0; i < tmhm.length; i++)	{
			result += tmhm[i];
			if ( i != tmhm.length - 1 )
				result += ", ";
		}
		result += "\n\n" + "Breeding Compatibility: ";
		for ( int i = 0; i < breedcompat.length; i++)	{
			result += breedcompat[i];
			if ( i != breedcompat.length - 1 )
				result += ", ";
		}
		result += "\n\n" + "Breeding Moves: ";
		for ( int i = 0; i < breedingmoves.length; i++)	{
			result += breedingmoves[i];
			if ( i != breedingmoves.length - 1 )
				result += ", ";
		}
		result += "\n\n" + "Found: " + location + "\n";
		return result;
	}
	
	public String toString()	{
		return number + " " + name;
	}
	
	public void writeObject ( OutputStream out )	{
		try	{
			PrintWriter outstream = new PrintWriter ( out );
			outstream.println ( name );
			outstream.println ( number );
			outstream.println ( type );
			outstream.println ( evolution.length );
			for ( int i = 0; i < evolution.length; i++ )
				outstream.println ( evolution[i] );
			outstream.println ( attack.length );
			for ( int i = 0; i < attack.length; i++ )
				outstream.println ( attack[i] );
			outstream.println ( tmhm.length );
			for ( int i = 0; i < tmhm.length; i++ )
				outstream.println ( tmhm[i] );
			outstream.println ( breedcompat.length );
			for ( int i = 0; i < breedcompat.length; i++ )
				outstream.println ( breedcompat[i] );
			outstream.println ( breedingmoves.length );
			for ( int i = 0; i < breedingmoves.length; i++ )
				outstream.println ( breedingmoves );
			outstream.println ( location );
			outstream.flush();
		}
		catch ( Exception exception )	{
			System.out.println ( "Error: " + exception );
			System.exit ( 0 );
		}
	}
	
	public void readObject ( InputStream in )	{
		try	{
			BufferedReader instream = new BufferedReader ( new InputStreamReader ( in ) );
			name = instream.readLine();
			number = Integer.parseInt ( instream.readLine() );
			type = instream.readLine();
			evolution = new String[Integer.parseInt ( instream.readLine() )];
			for ( int i = 0; i < evolution.length; i++ )
				evolution[i] = instream.readLine();
			attack = new String[Integer.parseInt ( instream.readLine() )];
			for ( int i = 0; i < attack.length; i++ )
				attack[i] = instream.readLine();
			tmhm = new String[Integer.parseInt ( instream.readLine() )];
			for ( int i = 0; i < tmhm.length; i++ )
				tmhm[i] = instream.readLine();
			breedcompat = new String[Integer.parseInt ( instream.readLine() )];
			for ( int i = 0; i < breedcompat.length; i++ )
				breedcompat[i] = instream.readLine();
			breedingmoves = new String[Integer.parseInt ( instream.readLine() )];
			for ( int i = 0; i < breedingmoves.length; i++ )
				breedingmoves[i] = instream.readLine();
			location = instream.readLine();
		}
		catch ( Exception exception )	{
			System.out.println ( "Error: " + exception );
			System.exit ( 0 );
		}
	}
}