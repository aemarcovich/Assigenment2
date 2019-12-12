# Assigenment2
The code works as intended. The program searches for the shortest path between the two actors given.
All the actors are added into a hashtable where they could than be acccessed. The code checks for
case sensitivty and makes sure that it shouldn't be an issue whether you searched for a actor in lower or upper case.
IMPORTANT->I used eclipse to get j-son to work, in doing so I had to use the absoulte path to read the csv. The absolute path may be 
different depending where you store the csv file. If needed change the file read to just the csv file name.
Analysis:
Since my bfs iterates through the hashset of actors the wortcase runntime would be simular to a bfs alogrithum. For it would be n+m with 
n being the number of keys in the hash table and m being the number of items in the hash set. 
O(n+m) would be it's runtime with the search for the key being O(1),as it simple checks if such as key exists in the hash table.
My assbemble function uses a nested for loop so it should have a runtime of O(n^2).
Again the code works. If there is an erro reading the file it's a simple change of the name. If the project is in the same directory
should work with just the file name.
