import java.io.*;

public class PokeSkillData	{
	public static void main ( String[] args )	{
		String name = "", type = "", descr = "";
		int pp = 0, power = 0;
		try	{
			BufferedReader inskill = new BufferedReader ( new FileReader ( "pokeskill.txt" ) );
			ObjectOutputStream objectout = new ObjectOutputStream ( new FileOutputStream ( "crystal-skill.pkmn" ) );
			String line = inskill.readLine();
			while ( line != null )	{
				name = line.substring ( 0, 13 ).trim();
				type = line.substring ( 14, 22 ).trim();
				pp = 0;
				try	{
					pp = Integer.parseInt ( line.substring ( 23, 25 ).trim() );
				}
				catch ( NumberFormatException exception ){}
				power = 0;
				try	{
					power = Integer.parseInt ( line.substring ( 26, 28 ).trim() );
				}
				catch ( NumberFormatException exception )	{}					
				descr = line.substring ( 33 );
				PokeSkill skill = new PokeSkill ( name, type, descr, pp, power );
				//System.out.println ( skill );
				objectout.writeObject ( skill );
				System.out.println ( "name: " + name );
				System.out.println ( "type: " + type );
				System.out.println ( "descr: " + descr );
				System.out.println ( "pp: " + pp );
				System.out.println ( "power: " + power );
				System.out.println();
				line = inskill.readLine();
			}
			objectout.close();
		}
		catch ( IOException e )	{
			System.out.println ( "Error: " + e );
			System.out.println ( "name: " + name );
			System.out.println ( "type: " + type );
			System.out.println ( "descr: " + descr );
			System.out.println ( "pp: " + pp );
			System.out.println ( "power: " + power );
			System.out.println();
		}
	}
}