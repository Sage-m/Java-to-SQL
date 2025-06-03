# Java-to-SQL
This is a basic implementation of Java to SQL for a database of movies

I will be implementing this with a local instance of mySQL, connected to a Java database management system using JDBC.
This requires a mySQL server instance, you can use the dump file I have included to set up your program, simply run
```
source dump.sql
```
from within mysql. You must modify the hostname to the name of the device that host your mysql database for this to work on your machine.

The final iteration of this project will allow users to rate each movie with a relationship table, and then average the results to show the ratings. Possibly with official ratings as well.
Made to be a watchlist. A out of scope modification of this would be applying it to a web UI.
