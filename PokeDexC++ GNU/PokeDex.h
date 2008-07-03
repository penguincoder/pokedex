#ifndef POKEDEX_H
#define POKEDEX_H

#include <iostream>

class PokeDex
{
public:
   static void alert ( string alertmsg )
   {
      StrToke t ( alertmsg, "|" );
      if ( t.hasTokens() )
      {
         while ( t.hasTokens() )
            cout << t.nextToken() << endl;
      }
      else
      {
         cout << alertmsg << endl;
      }
      cout << endl;
   }
   
   static string ask ( string question )
   {
      cout << question << ": ";
      string result;
      cin >> result;
      cout << endl;
      return result;
   }
   
   static string askPokemon()
   {
      string field, query;
      string fields[] = { "number", "name", "type", "location", "evolution", "tmhm", "attack", "breed", "moves" };
      cout << "Searching for Pokemon..." << endl;
      for ( int i = 0; i < 9; i++ )
         cout << fields[i] << "  ";
      cout << endl << "Search field: ";
      cin >> field;
      bool flag = false;
      for ( int i = 0; i < 9; i++ )
         if ( fields[i] == field )
            flag = true;
      if ( !flag )
      {
         cout << "Invalid Field!" << endl << "Search field: ";
         cin >> field;
         flag = false;
         for ( int i = 0; i < 9; i++ )
            if ( fields[i] == field )
               flag = true;
         if ( !flag )
         {
            field = "name";
            cout << "Field defaulted to: " << field << endl;
         }
      }
      cout << "Search query: ";
      cin >> query;
      cout << endl;
      return field + '|' + query;
   }
   
   static string askSkill()
   {
      string field, query;
      string fields[] = { "name", "type", "descr" };
      cout << "Searching for skills..." << endl;
      for ( int i = 0; i < 3; i++ )
         cout << fields[i] << "   ";
      cout << endl << "Search field: ";
      cin >> field;
      bool flag = false;
      for ( int i = 0; i < 3; i++ )
         if ( fields[i] == field )
            flag = true;
      if ( !flag )
      {
         cout << "Invalid Field!" << endl << "Search field: ";
         cin >> field;
         flag = false;
         for ( int i = 0; i < 3; i++ )
            if ( fields[i] == field )
               flag = true;
         if ( !flag )
         {
            field = "name";
            cout << "Field defaulted to: " << field << endl;
         }
      }
      cout << "Search query: ";
      cin >> query;
      cout << endl;
      return field + '|' + query;
   }
   
   static void addResult ( Pokemon pkmn, bool longdescr )
   {
      int rowsize = 5;
      cout << "# " << pkmn.getNumber() << " " << pkmn.getName() << endl;
      cout << " Type: " << pkmn.getType() << endl;
      cout << " Found at: " << pkmn.getLocation() << endl;
      cout << " Attacks learned:" << endl;
      List atk = pkmn.getAttack();
      for ( int i = 0; i < atk.size(); i++ )
         cout << "  " << atk.get ( i ) << endl;
      cout << " TM / HM Compatibility:" << endl;
      List th = pkmn.getTMHM();
      for ( int i = 0; i <= (int) th.size() / rowsize; i++ )
      {
         cout << "  ";
         for ( int j = 0; j < rowsize; j++ )
            if ( j + (i * rowsize) < th.size() )
               cout << th.get ( j + (i * rowsize) ) << ", ";
         cout << endl;
      }
      cout << " Evolution(s):" << endl;
      List evolution = pkmn.getEvolution();
      for ( int i = 0; i < evolution.size(); i++ )
         cout << "  " << evolution.get ( i ) << endl;
      if ( longdescr )
      {
         cout << " Breeding compatability:" << endl;
         List breed = pkmn.getBreed();
         for ( int i = 0; i <= (int) breed.size() / rowsize; i++ )
         {
            cout << "  ";
            for ( int j = 0; j < rowsize; j++ )
               if ( (j + (i * rowsize)) < breed.size() )
                  cout << breed.get ( j + (i * rowsize) ) << ", ";
            cout << endl;
         }
         cout << " Breeding moves:" << endl;
         List moves = pkmn.getMoves();
         for ( int i = 0; i < moves.size(); i++ )
            cout << "  " << moves.get ( i ) << endl;
      }
      cout << "|-<\"^-> End Pokemon <-^\">-|" << endl << endl;
   }
   
   static void addResult ( Skill skill )
   {
      cout << "Name: " << skill.getName() << endl;
      cout << "Type: " << skill.getType() << endl;
      cout << "Description: " << skill.getDescr() << endl;
      cout << "Power: " << skill.getPower() << '\t' << "PP: " << skill.getPP() << endl;
      cout << endl;
   }
   
};

#endif /* END POKEDEX_H */
