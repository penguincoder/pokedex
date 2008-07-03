#include <iostream>
#include <string>
using namespace std;

#include "List.h"
#include "StrToke.h"
#include "Pokemon.h"
#include "Skill.h"
#include "PokeDex.h"

int main ( int argc, char* argv[] )
{
   List atk(4);
   atk.add ( "kick" );
   atk.add ( "punch" );
   atk.add ( "scream" );
   atk.add ( "bite" );
   List breed(2);
   breed.add ( "suicine" );
   breed.add ( "bob" );
   List mv(1);
   mv.add ( "None" );
   List tmhm(4);
   tmhm.add ( "kiss" );
   tmhm.add ( "flash" );
   tmhm.add ( "moon" );
   tmhm.add ( "blow" );
   List evo(1);
   evo.add ( "Shyrac" );
   Pokemon p( 133, "Eevee", "Normal", "Bob's House", atk, breed, mv, tmhm, evo );
   //p.printLongDescription();
   string what = PokeDex::ask ( "What" );
   PokeDex::alert ( "You said " + what );
   PokeDex::askPokemon();
   PokeDex::askSkill();
   PokeDex::addResult ( p, true );
}
