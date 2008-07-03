#ifndef POKEMON_CPP
#define POKEMON_CPP

Pokemon::Pokemon()
{
	number = 0;
	name = "";
	type = "";
	location = "";
}

Pokemon::Pokemon ( int num, string nm, string tp, string loc, List atk, List brd, List mvs, List th, List evo )
{
   number = num;
   name = nm;
   type = tp;
   location = loc;
   attack = atk;
   breed = brd;
   moves = mvs;
   tmhm = th;
   evolution = evo;
}

Pokemon::Pokemon ( const Pokemon &pkmn )
{
   number = pkmn.getNumber();
   name = pkmn.getName();
   type = pkmn.getType();
   location = pkmn.getLocation();
   attack = pkmn.getAttack();
   breed = pkmn.getBreed();
   moves = pkmn.getMoves();
   tmhm = pkmn.getTMHM();
   evolution = pkmn.getEvolution();
}

int Pokemon::getNumber() const
{
   return number;
}
string Pokemon::getName() const
{
   return name;
}

string Pokemon::getType() const
{
   return type;
}

string Pokemon::getLocation() const
{
   return location;
}

List Pokemon::getAttack() const
{
   return attack;
}

List Pokemon::getBreed() const
{
   return breed;
}

List Pokemon::getMoves() const
{
   return moves;
}

List Pokemon::getTMHM() const
{
   return tmhm;
}

List Pokemon::getEvolution() const
{
   return evolution;
}

#endif /* end POKEMON_CPP */
