#ifndef STRTOKE_CPP
#define STRTOKE_CPP

StrToke::StrToke ( string s, string d )
{
   string str = s;
   tokes = 0;
   while ( str.find ( d ) != -1 )
   {
      int index = str.find ( d );
      string newstr = str.substr ( 0, index );
      tokens.add ( newstr );
      str = str.substr ( index + 1, str.length() - index );
      tokes++;
   }
   tokens.add ( str );
   tokes++;
}

bool StrToke::hasTokens()
{
   return (tokes > 0);
}

string StrToke::nextToken()
{
   if ( tokes > 0 )
   {
      tokes--;
      return tokens.get ( tokens.size() - tokes - 1 );
   }
   return "Out of tokens";
}

#endif /* END STRTOKE_CPP */
