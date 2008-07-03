#ifndef LIST_H
#define LIST_H

class List
{
   string* strings;
   int cursize;
   int maxsize;
   void resize ( int newsize = -1 );
public:
   List();
   List ( int initialsize );
   //~List();
   void add ( string newentry, int pos = -1, int rflag = 0 );
   void add ( char* newentry, int pos = -1, int rflag = 0 );
   void remove ( string entry );
   void remove ( int pos );
   int size() const;
   string get ( int pos );
};

#include "List.cpp"

#endif /* end LIST_H */
