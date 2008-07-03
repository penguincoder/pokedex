import java.io.*;
import java.util.*;

public class PokeDex {
	
	private static Pokemon[] pokemon;
	private static PokeSkill[] pokeskill;
	private final static String version = "2.1";
	private final static InputStream systemin = System.in;
	private static boolean longdescr = true;
	private final static String[] types = new String[]	{ "pokemon", "skill" };
	private final static String[][] fields = new String[][]	{ { "name", "number", "type", "evolution", "attack", "tmhm", "location", "breedmatch", "breedmove" }, { "name", "type", "description" } };
	
	public static void main ( String[] args )	{
		pokemon = new Pokemon[251];
		pokeskill = new PokeSkill[251];

		try	{
			ObjectInputStream instream = new ObjectInputStream ( new FileInputStream ( "crystal.pkmn" ) );
			ObjectInputStream inskill = new ObjectInputStream ( new FileInputStream ( "crystal-skill.pkmn" ) );
			System.out.println ( "   Loading Pokemon" );
			System.out.println ( "0%                  100%");
			for ( int i = 0; i < 251; i++ )	{
				pokemon[i] = (Pokemon) instream.readObject();
				pokeskill[i] = (PokeSkill) inskill.readObject();
				if ( (i / 12) == (i / 12.0) )
					System.out.print ( "." );
			}
			System.out.println();
			instream.close();
			inskill.close();
		}
		catch ( FileNotFoundException exception )	{
			System.out.println ( "Could not find the databases!" );
			System.exit ( 0 );
		}
		catch ( ClassNotFoundException exception )	{
			System.out.println ( "Class error: " + exception );
			System.exit ( 0 );
		}
		catch ( IOException exception )	{
			System.out.println ( "Database I/O error!" + exception );
			System.exit ( 0 );
		}
		
		String currentcommand = "";
		do	{
			System.out.print ( "PokeDex> " );
			try	{
				BufferedReader instream = new BufferedReader ( new InputStreamReader ( systemin ) );
				currentcommand = instream.readLine().toLowerCase().trim();
				if ( validate ( currentcommand, types ) )
					searchField ( currentcommand );
				else if ( currentcommand.equals ( "help" ) || currentcommand.equals ( "?" ) || currentcommand.equals ( "info" ) )
					dispHelp();
				else if ( currentcommand.equals ( "long" ) )	{
					longdescr = !longdescr;
					if ( longdescr )
						System.out.println ( "Long descriptions turned on.\n" );
					else
						System.out.println ( "Long descriptions turned off.\n" );
				}
				else if ( !currentcommand.equals ( "quit" ) )
					System.out.println ( "Invalid command (type help for usage)\n" );
			}
			catch ( IOException exception )	{
				System.out.println ( "IO Stream error! " + exception );
				currentcommand = "quit";
			}
		} while ( !currentcommand.equals ( "quit" ) );
	}
	
	private static boolean validate ( String test, String[] fields )	{
		for ( int i = 0; i < fields.length; i++ )
			if ( test.equals ( fields[i] ) )
				return true;
		return false;
	}
	
	private static void dispHelp()	{
		System.out.println ( "PokeDex " + version );
		System.out.println ( "Released by Coleman under the GPL" );
		System.out.println();
		System.out.println ( "Commands:" );
		System.out.println();
		System.out.println ( " help, ?, info				Display Help Message" );
		System.out.println ( " pokemon, p				Search for a Pokemon" );
		System.out.println ( " skill, s				Search for a Skill" );
		System.out.println ( " long					Toggle long descriptions" );
		System.out.println();
		System.out.println ( "  Valid Pokemon search fields:" );
		String temp = "";
		for ( int i = 0; i < fields[0].length; i++ )	{
			if ( i != fields[0].length - 1 )
				temp += fields[0][i] + ", ";
			else
				temp += fields[0][i];
		}
		System.out.println ( "   " + temp );
		//System.out.println ( "   name, number, type, attack, tmhm, location," );
		//System.out.println ( "   breedmatch, breedmove, evolution");
		System.out.println();
		System.out.println ( "  Valid Skill search fields:" );
		//System.out.println ( "   name, type, description" );
		temp = "";
		for ( int i = 0; i < fields[1].length; i++ )	{
			if ( i != fields[1].length - 1 )
				temp += fields[1][i] + ", ";
			else
				temp += fields[1][i];
		}
		System.out.println ( "   " + temp );
		System.out.println();
		System.out.println ( " All queries are case-insensitive." );
		System.out.println();
		System.out.println();
	}
	
	private static void searchField ( String type )	{
		String field = "";
		System.out.print ( "Enter search field: " );
		
		try	{
			BufferedReader instream = new BufferedReader ( new InputStreamReader ( systemin ) );
			field = instream.readLine().trim().toLowerCase();
		}
		catch ( IOException exception )	{
			System.out.println ( "IO Stream error!" + exception );
			field = "";
		}
		
		int typenum = 0;
		for ( int i = 0; i < types.length; i++ )
			if ( type.equals ( types[i] )	) {
				typenum = i;
				break;
			}
			
		if ( !field.equals ( "" ) && validate ( field, fields[typenum] ) )
			searchQuery ( field, typenum );
	}
	
	private static void searchQuery ( String field, int typenum )	{
		String query = "";
		System.out.print ( "Enter search query: " );
		
		try	{
			BufferedReader instream = new BufferedReader ( new InputStreamReader ( systemin ) );
			query = instream.readLine().trim().toLowerCase();
		}
		catch ( IOException exception )	{
			System.out.println ( "IO Stream error!" + exception );
			query = "";
		}
		
		if ( !query.equals ( "" ) )	{
			System.out.println ( "\n==================" );
			System.out.println ( "   Begin search   " );
			System.out.println ( "==================\n" );
			for ( int current = 0; current < 251; current++ )	{
				if ( containsQuery ( query, field, typenum, current ) )	{
					if ( longdescr && typenum == 0 )
						System.out.println ( pokemon[current].longDescription() );
					else if ( longdescr && typenum == 1 )
						System.out.println ( pokeskill[current].longDescription() );
					else if ( !longdescr && typenum == 0 )
						System.out.println ( pokemon[current] );
					else if ( !longdescr && typenum == 1 )
						System.out.println ( pokeskill[current] );
				}
			}
			
			System.out.println ( "\n==================" );
			System.out.println ( "    End Search" );
			System.out.println ( "==================\n" );
		}
	}
	
	private static boolean containsQuery ( String query, String field, int typenum, int current )	{
		boolean result = false;
		String searchresult = "";
		if ( typenum == 0 )	{
			if ( field.equals ( "name" ) )
				searchresult = pokemon[current].getName();
			else if ( field.equals ( "number" ) )
				searchresult = (new Integer ( pokemon[current].getNumber() )).toString();
			else if ( field.equals ( "location" ) )
				searchresult = pokemon[current].foundAt();
			else if ( field.equals ( "type" ) )
				searchresult = pokemon[current].getType();
			if ( field.equals ( "attack" ) )	{
				String[] temp = pokemon[current].getAttack();
				for ( int i = 0; i < temp.length; i++ )
					searchresult += temp[i] + " ";
			}
			if ( field.equals ( "tmhm" ) )	{
				String[] temp = pokemon[current].getTMHM();
				for ( int i = 0; i < temp.length; i++ )
					searchresult += temp[i] + " ";
			}
			if ( field.equals ( "breedmatch" ) )	{
				String[] temp = pokemon[current].getBreedingList();
				for ( int i = 0; i < temp.length; i++ )
					searchresult += temp[i] + " ";
			}
			if ( field.equals ( "breedmove" ) )	{
				String[] temp = pokemon[current].getBreedingMoves();
				for ( int i = 0; i < temp.length; i++ )
					searchresult += temp[i] + " ";
			}
			if ( field.equals ( "evolution" ) )	{
				String[] temp = pokemon[current].getEvolution();
				for ( int i = 0; i < temp.length; i++ )
					searchresult += temp[i] + " ";
			}			
		}
		else if ( typenum == 1 )	{
			if ( field.equals ( "name" ) )
				searchresult = pokeskill[current].getName();
			else if ( field.equals ( "description" ) )
				searchresult = pokeskill[current].getDescription();
			else if ( field.equals ( "type" ) )
				searchresult = pokeskill[current].getType();
		}
		if ( searchresult.toLowerCase().indexOf ( query ) > -1 )
			result = true;
		return result;
	}

}