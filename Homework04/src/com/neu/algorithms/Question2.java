package com.neu.algorithms;

import java.util.Date;

public class Question2 {

public static void main(String[] args) {
		
		User user1 = new User("Jake", 1, new Date("05/05/1986"));
		User user2 = new User("Jake", 1, new Date("05/05/1986"));
		User user3 = new User("Dane", 2, new Date("12/26/1998"));
		
		//Check if the objects are equal
		System.out.println(user1.equals(user2));
		System.out.println(user1.equals(user3));
		
		//Generate the hashcodes of all 3 users
		System.out.println("User 1 hashcode : " + user1.hashCode());
		System.out.println("User 2 hashcode : " + user2.hashCode());
		System.out.println("User 3 hashcode : " + user3.hashCode());
		
		//Compare the objects lexicographically
		System.out.println(user1.compareTo(user2));
		System.out.println(user1.compareTo(user3));
		
	}
}

class User implements Comparable<User>{

	private String name;
	private int id;
	private Date birth;
	
	public User(String name, int id, Date birth)
	{
		this.name = name;
		this.id = id;
		this.birth = birth;
	}
	
	/**
	 * Checks if 2 objects of the User class are equal
	 */
	@Override
	public boolean equals(Object other)
	{
		//If the object is compared to itself return true
		if(this == other)
			return true;
		
		//If the object to compare is null or if it is of a different type
		//as the User class return false
		if(other == null || 
				(this.getClass() != other.getClass()))
			return false;
		
		User guest = (User) other;
		
		//Check if all the properties in the object are equal and 
		//return true if they are or else return false
		return (this.id == guest.id) &&
					(this.name != null && this.name.equals(guest.name)) &&
					(this.birth != null && this.birth.equals(guest.birth));
	}
	
	/**
	 * Generates the hashcode for the User object
	 */
	@Override
	public int hashCode()
	{
		int result = 0;
		result = 31 * result + id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (birth != null ? birth.hashCode() : 0);
		return result;
	}
	
	//Compares the User object's id lexicographically to another User object and returns
	//if this > o, it returns positive number  
	//if this < o, it returns negative number  
	//if this == o, it returns 0 
	@Override
	public int compareTo(User o) {
		
		return this.id - o.id;
	}

}
