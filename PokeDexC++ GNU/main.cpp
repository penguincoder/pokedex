#include <fstream>
#include <string>       // because i'm lazy and i don't know enough about char *'s
#include <cstdlib>      // used for atoi, will replace later possibly with custom atoi
using namespace std;

#include "List.h"
#include "StrToke.h"    // uses List
#include "Pokemon.h"    // uses List
#include "Skill.h"      // uses List
#include "PokeDex.h"    // uses StrToke

int main ( int argc, char* argv )
{
   ifstream inpkmn ( "crystal.pkmn", ios::in );
	if ( inpkmn.fail() )
	{
		PokeDex::alert ( "Could not load the database: crystal.pkmn" );
		return 1;
	}
	Pokemon pokemon[251];
	for ( int i = 0; i < 251; i++ )
	{
      char tmp[256];
      inpkmn.getline ( tmp, 50 );
		string name = tmp;
      inpkmn.getline ( tmp, 50 );
		int number = atoi ( tmp );
      inpkmn.getline ( tmp, 50 );
		string type = tmp;
      inpkmn.getline ( tmp, 50 );
		int max = atoi ( tmp );
		List evo(max);
		for ( int j = 0; j < max; j++ )
      {
         inpkmn.getline ( tmp, 50 );
			evo.add ( tmp );
      }
      inpkmn.getline ( tmp, 50 );
		max = atoi ( tmp );
		List attack(max);
		for ( int j = 0; j < max; j++ )
      {
			inpkmn.getline ( tmp, 50 );
         attack.add ( tmp );
      }
      inpkmn.getline ( tmp, 50 );
		max = atoi ( tmp );
      List tmhm(max);
		for ( int j = 0; j < max; j++ )
      {
         inpkmn.getline ( tmp, 50 );
			tmhm.add ( tmp );
      }
      inpkmn.getline ( tmp, 50 );
		max = atoi ( tmp );
		List breed(max);
		for ( int j = 0; j < max; j++ )
      {
         inpkmn.getline ( tmp, 50 );
			breed.add ( tmp );
      }
      inpkmn.getline ( tmp, 50 );
		max = atoi ( tmp );
		List moves(max);
		for ( int j = 0; j < max; j++ )
      {
         inpkmn.getline ( tmp, 50 );
			moves.add ( tmp );
      }
      inpkmn.getline ( tmp, 100 );
		string location = tmp;
		if ( inpkmn.fail() || inpkmn.bad() || inpkmn.eof() )
		{
			PokeDex::alert ( "Fatal database error at Pokemon #" + i );
			return 1;
		}
		pokemon[i] = Pokemon ( number, name, type, location, attack, breed, moves, tmhm, evo );
	}
   Skill skill[251];
   for ( int i = 0; i < 251; i++ )
   {
      char tmp[256];
      inpkmn.getline ( tmp, 50 );
      string name = tmp;
      inpkmn.getline ( tmp, 50 );
      string type = tmp;
      inpkmn.getline ( tmp, 100 );
      string descr = tmp;
      inpkmn.getline ( tmp, 10 );
      int pp = atoi ( tmp );
      inpkmn.getline ( tmp, 10 );
      int power = atoi ( tmp );
      if ( inpkmn.fail() || inpkmn.bad() || inpkmn.eof() )
		{
			PokeDex::alert ( "Fatal database error at Skill #" + i );
			return 1;
		}
      skill[i] = Skill ( name, type, descr, power, pp );
   }
   inpkmn.close();
   string command = "";
   bool longdescr = false;
   while ( command != "q" )
   {
      command = PokeDex::ask ( "PokeDex" );
      if ( command == "h" || command == "?" )
      {
         string tmp = "PokeDexC++ 1.0|Search [P]okemon|Search [S]kills|Toggle [L]ong Descriptions (";
         tmp += (longdescr)?"On":"Off";
         tmp += ")|Display [?] [H]elp|[Q]uit PokeDex";
         PokeDex::alert (  tmp  );
      }
      else if ( command == "l" )
      {
         longdescr = !longdescr;
      }
      else if ( command == "p" )
      {
         StrToke t ( PokeDex::askPokemon(), "|" );
         string field = t.nextToken();
         string query = t.nextToken();
         for ( int i = 0; i < 251; i++ )
         {
            if ( field == "name" )
            {
               string search = pokemon[i].getName();
               if ( search.find ( query ) != -1 )
                  PokeDex::addResult ( pokemon[i], longdescr );
            }
            else if ( field == "number" )
            {
               int x = atoi ( query.c_str() );
               if ( x == i + 1 )
                  PokeDex::addResult ( pokemon[i], longdescr );
            }
            else if ( field == "type" )
            {
               string search = pokemon[i].getType();
               if ( search.find ( query ) != -1 )
                  PokeDex::addResult ( pokemon[i], longdescr );
            }
            else if ( field == "location" )
            {
               string search = pokemon[i].getLocation();
               if ( search.find ( query ) != -1 )
                  PokeDex::addResult ( pokemon[i], longdescr );
            }
            else if ( field == "attack" )
            {
               List attack = pokemon[i].getAttack();
               for ( int j = 0; j < attack.size(); j++ )
               {
                  string search = attack.get ( j );
                  if ( search.find ( query ) != -1 )
                  {
                     PokeDex::addResult ( pokemon[i], longdescr );
                     break;
                  }
               }
            }
            else if ( field == "breed" )
            {
               List breed = pokemon[i].getBreed();
               for ( int j = 0; j < breed.size(); j++ )
               {
                  string search = breed.get ( j );
                  if ( search.find ( query ) != -1 )
                  {
                     PokeDex::addResult ( pokemon[i], longdescr );
                     break;
                  }
               }
            }
            else if ( field == "tmhm" )
            {
               List tmhm = pokemon[i].getTMHM();
               for ( int j = 0; j < tmhm.size(); j++ )
               {
                  string search = tmhm.get ( j );
                  if ( search.find ( query ) != -1 )
                  {
                     PokeDex::addResult ( pokemon[i], longdescr );
                     break;
                  }
               }
            }
            else if ( field == "moves" )
            {
               List moves = pokemon[i].getMoves();
               for ( int j = 0; j < moves.size(); j++ )
               {
                  string search = moves.get ( j );
                  if ( search.find ( query ) != -1 )
                  {
                     PokeDex::addResult ( pokemon[i], longdescr );
                     break;
                  }
               }
            }
            else if ( field == "evolution" )
            {
               List evo = pokemon[i].getEvolution();
               for ( int j = 0; j < evo.size(); j++ )
               {
                  string search = evo.get ( j );
                  if ( search.find ( query ) != -1 )
                  {
                     PokeDex::addResult ( pokemon[i], longdescr );
                     break;
                  }
               }
            }
            else if ( field == "tmhm" )
            {
               List tmhm = pokemon[i].getTMHM();
               for ( int j = 0; j < tmhm.size(); j++ )
               {
                  string search = tmhm.get ( j );
                  if ( search.find ( query ) != -1 )
                  {
                     PokeDex::addResult ( pokemon[i], longdescr );
                     break;
                  }
               }
            }
         }
      }
      else if ( command == "s" )
      {
         StrToke t ( PokeDex::askSkill(), "|" );
         string field = t.nextToken();
         string query = t.nextToken();
         for ( int i = 0; i < 251; i++ )
         {
            if ( field == "name" )
            {
               string search = skill[i].getName();
               if ( search.find ( query ) != -1 )
                  PokeDex::addResult ( skill[i] );
            }
            else if ( field == "type" )
            {
               string search = skill[i].getType();
               if ( search.find ( query ) != -1 )
                  PokeDex::addResult ( skill[i] );
            }
            else if ( field == "descr" )
            {
               string search = skill[i].getDescr();
               if ( search.find ( query ) != -1 )
                  PokeDex::addResult ( skill[i] );
            }
         }
      }
   }
}
