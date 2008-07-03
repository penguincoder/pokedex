#ifndef SKILL_CPP
#define SKILL_CPP

Skill::Skill()
{
   name = "";type = "";descr = "";pwr = 0; pp = 0;
}

Skill::Skill ( string n, string t, string d, int pw, int p )
{
   name = n;
   type = t;
   descr = d;
   pwr = pw;
   pp = p;
}

Skill::Skill ( const Skill &newskill )
{
   name = newskill.getName();
   type = newskill.getType();
   descr = newskill.getDescr();
   pwr = newskill.getPower();
   pp = newskill.getPP();
}

string Skill::getName() const
{
   return name;
}

string Skill::getType() const
{
   return type;
}

string Skill::getDescr() const
{
   return descr;
}

int Skill::getPower() const
{
   return pwr;
}

int Skill::getPP() const
{
   return pp;
}

#endif /* END SKILL_CPP */
