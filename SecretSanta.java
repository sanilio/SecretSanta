/* 
 * Author: Sanil Sampat
 * Date:   02 / 18 / 2013
 * 
 * Notes:
 *	
 *	This solution is based on 
 *	random number generation.
 *	We choose a random name
 *	from the participants,
 *	and append it to the
 *	assignments array if
 *	the array does not
 *	contain it already, and
 *	the random name is not
 *	itself. There is a condition
 *	that can lead to an infinite
 *	loop that is dealt with
 *	by resetting the assignments.
 *
 *
 *	A more optimal solution
 *	that can handle restricted
 *	pairings would require a 
 *	graph, where each edge
 *	reflects an allowed pair
 *	and disallowed pairs
 *      may not have adjacent 
 *      vertices.
 */

import java.util.Random;

public class SecretSanta {
	
	public String [] generateAssignments(String [] participants) {
		
		int len               = participants.length;
		Random rand           = new Random();
		String [] assignments = new String [len];
		

		int i = 0;
		
		while(i < len) {

			String randomName = participants[rand.nextInt(len)];

			// make sure the name isn't itself nor assigned
			if ( !(randomName.equals(participants[i])) && !(contains(assignments, randomName)) ) {
				assignments[i] = randomName;
				i++;
			}
			
			// if cornered into infinite loop, reset assignments and index
			if(randomName.equals(participants[len-1]) && i == len-1) {
				clear(assignments);
				i = 0;
			}
			
		}
		return assignments;
	}
	
	public boolean contains(String[] arr, String item) {
		
		if (arr != null && item != null) {
		
			for(int i=0; i<arr.length; i++) {

				if(arr[i] == null) {
					return false;
				}				
				if (arr[i].equals(item)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void clear(String[] arr) {
		for(int i=0; i<arr.length; i++) { arr[i] = ""; }
	}
	
	
	public static void main(String args[]) {
		
		final String [] participants = new String[] {"Kyle", "Kenny", "Eric", "Stan", "Stewie", "Brian", "Peter", "Chef"};
		
		SecretSanta ss = new SecretSanta();
		
		for(int j=1; j<26; j++) {
			
			final String [] assignments = ss.generateAssignments(participants);
			
			for (String s: participants) {
				System.out.print(s + " ");
			}
			
			System.out.print("\n");
			
			for (String s: assignments) {
				System.out.print(s + " ");
			}
			
			System.out.println("\nPass: " + j + "\n");
			
			for(int i=0; i<participants.length; i++) {
				if (participants[i].equals(assignments[i])) {
					System.out.println("\n\nError! Self-assignment!\n\n");
					System.exit(0);
				}
			}
		}
	}
}
