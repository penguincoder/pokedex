#ifndef POKEMON_H
#define POKEMON_H

class Pokemon
{
   string name, type, location;
   List attack, breed, moves, tmhm, evolution;
   int number;
public:
   Pokemon();
   Pokemon ( int number, string name, string type, string location, List attack, List breed, List moves, List tmhm, List evolution );
   Pokemon ( const Pokemon& pkmn );
   
   string getName() const;
   string getType() const;
   string getLocation() const;
   int getNumber() const;
   List getAttack() const;
   List getBreed() const;
   List getMoves() const;
   List getTMHM() const;
   List getEvolution() const;
};

#include "Pokemon.cpp"
#endif /* end POKEMON_H */
