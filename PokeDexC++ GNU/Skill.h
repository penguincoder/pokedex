#ifndef SKILL_H
#define SKILL_H

class Skill
{
   string name, type, descr;
   int pwr, pp;
public:
   Skill();
   Skill ( string n, string t, string d, int pwr, int p );
   Skill ( const Skill &newskill );
   string getName() const;
   string getType() const;
   string getDescr() const;
   int getPower() const;
   int getPP() const;
};

#include "Skill.cpp"

#endif /* END SKILL_H */
