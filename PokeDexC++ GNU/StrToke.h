#ifndef STRTOKE_H
#define STRTOKE_H

class StrToke
{
   List tokens;
   int tokes;
public:
   StrToke ( string s, string d );
   bool hasTokens();
   string nextToken();
};

#include "StrToke.cpp"

#endif /* END STRTOKE_H */
