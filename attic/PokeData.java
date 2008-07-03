import java.io.*;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;

public class PokeData	{
	public static void main ( String[] args )	{
		try	{
			BufferedReader inpoke = new BufferedReader ( new FileReader ( "pokemon.txt" ) );
			BufferedReader index = new BufferedReader ( new FileReader ( "pokedex.txt" ) );
			ObjectOutputStream objectout = new ObjectOutputStream ( new FileOutputStream ( "crystal.pkmn" ) );
			StringTokenizer token = new StringTokenizer("");
			String line = inpoke.readLine();
			int number;
			String name = "", type1 = "", type2 = "", location = "";
			String[] attack, breedcompat, breedingmoves, tmhm, evolution;
			int attacks = 0, breedcompats = 0, breedingmovess = 0, tmhms = 0, evolutions = 0;
			while ( line != null )	{
				attack = new String[15];
				breedcompat = new String[150];
				breedingmoves = new String[50];
				tmhm = new String[100];
				evolution = new String[5];
				number = Integer.parseInt ( line.substring ( 1, 5 ).trim() );
				name = line.substring ( 8, 20 ).trim();
				String types = line.substring ( 21, 36 ).trim();
				token = new StringTokenizer ( types, "/" );
				type1 = token.nextToken();
				if ( token.hasMoreTokens() )
					type2 = token.nextToken();
				else
					type2 = "None";
				String finalevo = line.substring ( 44 ).trim();
				token = new StringTokenizer ( finalevo, " " );
				finalevo = token.nextToken();
				evolution[evolutions] = finalevo;
				if ( !finalevo.equals ( "None" ) )
					evolution[evolutions] += " - " + line.substring ( 44 + finalevo.length() + 1 );
				evolutions++;
				line = inpoke.readLine();
				while ( line != null && line.startsWith ( " " ) )	{
					token = new StringTokenizer ( line.substring ( 44 ).trim(), " " );
					String tempevo = token.nextToken();
					evolution[evolutions] = tempevo + " - " + line.substring ( 44 + tempevo.length() + 1 ).trim();
					evolutions++;
					line = inpoke.readLine();
				}
				String otherline = index.readLine();
				while ( !otherline.startsWith("-") )	{
					if ( otherline.startsWith ( "Attacks: " ) )	{
						while ( !otherline.equals ( "" ) )	{
							if ( otherline.endsWith ( "-" ) )
								attack[attacks] = otherline.substring ( 9, 22 ).trim();
							else if ( otherline.endsWith ( "None" ) )
								attack[attacks] = "None";
							else
								attack[attacks] = otherline.substring ( 9, 22 ).trim() + " at " + otherline.substring ( 23 ).trim();
							attacks++;
							otherline = index.readLine();
						}
						otherline = index.readLine();
						while ( !otherline.equals("") )	{
							token = new StringTokenizer ( otherline.substring ( 13 ).trim(), "," );
							do	{
								String tmmove = token.nextToken().trim();
								if ( tmmove.endsWith ( ")" ) )
									//tmmove = tmmove.substring ( tmmove.indexOf ( "(" ) + 1, tmmove.indexOf ( ")" ) ) + " " + tmmove.substring ( 0, tmmove.indexOf ( "(" ) - 1 );
									tmmove = tmmove.substring ( 0, tmmove.indexOf ( "(" ) ).trim();
								tmhm[tmhms] = tmmove;
								tmhms++;
							} while ( token.hasMoreTokens() );
							otherline = index.readLine();
						}
						otherline = index.readLine();
						while ( !otherline.equals ( "" ) )	{
							token = new StringTokenizer ( otherline.substring ( 15 ).trim(), "," );
							do	{
								breedingmoves[breedingmovess] = token.nextToken().trim();
								breedingmovess++;
							} while ( token.hasMoreTokens() );
							otherline = index.readLine();
						}
						otherline = index.readLine();
						while ( !otherline.equals ( "" ) )	{
							token = new StringTokenizer ( otherline.substring ( 24 ).trim(), "," );
							do	{
								breedcompat[breedcompats] = token.nextToken().trim();
								breedcompats++;
							} while ( token.hasMoreTokens() );
							otherline = index.readLine();
						}
						while ( !otherline.startsWith ( "Found at:" ) )
							otherline = index.readLine();
						location = otherline.substring ( 9 );
					}
					otherline = index.readLine();
				}
				String[] finalattack, finalcompat, finalmoves, finaltmhm, finalevolution;
				finalattack = new String[attacks];
				for ( int i = 0; i < attacks; i++ )
					finalattack[i] = attack[i];
				finalcompat = new String[breedcompats];
				for ( int i = 0; i < breedcompats; i++ )
					finalcompat[i] = breedcompat[i];
				finalmoves = new String[breedingmovess];
				for ( int i = 0; i < breedingmovess; i++ )
					finalmoves[i] = breedingmoves[i];
				finaltmhm = new String[tmhms];
				for ( int i = 0; i < tmhms; i++ )
					finaltmhm[i] = tmhm[i];
				finalevolution = new String[evolutions];
				for ( int i = 0; i < evolutions; i++ )
					finalevolution[i] = evolution[i];
				String finaltype = type1;
				if ( !type2.toLowerCase().equals ( "none" ) )
					finaltype += " / " + type2;
				objectout.writeObject ( new Pokemon ( number, name, finaltype, finalevolution, finalattack, finaltmhm, finalmoves, finalcompat, location ) );
				System.out.println ( number + " " + name );
				attacks = 0;
				tmhms = 0;
				breedingmovess = 0;
				breedcompats = 0;
				evolutions = 0;
			}
			objectout.close();
		}
		catch ( IOException e )	{
			System.out.println (e);
			System.exit ( 0 );
		}
	}
}