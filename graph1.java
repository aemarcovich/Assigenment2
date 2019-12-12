import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import java.util.Iterator;
/**
 *A hashtable with hash sets is created.
 *The keys are strings and values are hash set.
 **/
public class graph1
{
	HashMap<String,HashSet<String>> adjlist;
	public graph1()
	{
		adjlist=new HashMap<>();
	}
	public void add(String name1, String name2)
	{
		if(name1.equals(name2))
		{
			return;
		}
		if(!adjlist.containsKey(name1))
		{
			adjlist.put(name1,new HashSet<>());
		}
		adjlist.get(name1).add(name2);
		if(!adjlist.containsKey(name2))
		{
			adjlist.put(name2,new HashSet<>());
		}
		adjlist.get(name2).add(name1);
	}
	/**
	 * Simple check function nothing to see here. Move along, move along 
	 */
	public boolean checkIn(String name)//use compareToignorecase
	{
		if(adjlist.containsKey(name))
			return true;
		else
			return false;
	}
	/**
	 * Function searches for the shortest paths.
	 * Uses hash maps of strings to log the previous layer of actors.
	 * unvisited logs the actor names yet to be traverse.
	 * Function returns arraylist formed from getpath function.
	 * */
	public ArrayList<String> bfs(String actor1, String actor2)
	{
		HashMap<String, String> prevActors = new HashMap<>();
		ArrayList<String> unvisited = new ArrayList<String>();

		//Now, we start adding actor1 node to the unvisted
		//we also add the actor in the hashmap.
		unvisited.add(actor1);
		prevActors.put(actor1, null);
		//since it's the first the parent should be null.
		while (unvisited.size() > 0)
		{
			String currentActor = unvisited.get(0);
			HashSet<String> knownActors = adjlist.get(currentActor);
			//KnownActors is a hashset
			//loop through all the known actors of the current actor
			//use iterator.
			Iterator<String> it = knownActors.iterator();
		    while(it.hasNext())
			{
				String nextActor = it.next();
				//If checks if the actor was visited, then we continue with the next known actor
				if (prevActors.containsKey(nextActor))
				{
					continue;
				}
				//If not, we add it to the List of unvisited
				else
				{
					unvisited.add(nextActor);
					//add to prev actors as well
					prevActors.put(nextActor, currentActor);
					if (nextActor.equals(actor2))
					{
						return getPath(prevActors, actor2);
					}
				}
			}
			unvisited.remove(0);
		}
		return null;
	}
	/**
	 * Function return an Arraylist with the the actors name.
	 * The order goes from source name to the search actor
	 * It uses the the HashMap prev_actor to iterate backward through logged names.
	 * Stops when the parent is null 
	 **/
	private ArrayList<String> getPath(HashMap<String, String> prev, String actor2)
	{
		ArrayList<String> path = new ArrayList<String>();
		String cur_actor = actor2;
		while (cur_actor != null)
		{
			//since the hashmap is backwards from what we want, as we add we shift every other name
			path.add(0, cur_actor); 
			String new_parent = prev.get(cur_actor); 
			//Get the prevActor of cur_actor
			cur_actor = new_parent; 
		}
		return path;
	}
}