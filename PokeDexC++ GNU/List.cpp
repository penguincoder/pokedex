#ifndef LIST_CPP
#define LIST_CPP

List::List()
{
   maxsize = 50;
   strings = new string[maxsize];
   cursize = 0;
}

List::List ( int initialsize )
{
   strings = new string[initialsize];
   maxsize = initialsize;
   cursize = 0;
}

void List::add ( string newentry, int pos, int rflag )
{
   if ( pos == -1 )
      pos = cursize;
   if ( pos < maxsize && pos >= 0 )
   {
		if ( !rflag )
			for ( int i = pos; i < cursize; i++ )
				strings[i + 1] = strings[i];
		strings[pos] = newentry;
      cursize++;
      if ( cursize == maxsize )
         resize();
   }
}

void List::add ( char* newentry, int pos, int rflag )
{
   string s(newentry);
   List::add ( s, pos, rflag );
}

void List::resize ( int newsize )
{
   if ( newsize == -1 )
      newsize = maxsize;
   if ( newsize > maxsize )
   {
      strings = new string[newsize - cursize];
      maxsize = newsize;
   }
}

void List::remove ( string entry )
{
for ( int i = 0; i < cursize; i++ )
   if ( strings[i] == entry )
   {
      for ( int j = i; j < cursize - 1; j++ )
         strings[j] = strings[j + 1];
      cursize--;
      break;
   }
}

void List::remove ( int pos )
{
   if ( pos >= 0 && pos < cursize )
   {
      for ( int i = pos; i < cursize - 1; i++ )
         strings[i] = strings[i + 1];
      cursize--;
   }
}

string List::get ( int pos )
{
   if ( pos >= 0 && pos < cursize )
      return strings[pos];
   return 0;
}

int List::size() const
{
   return cursize;
}

#endif /* end LIST_CPP */
