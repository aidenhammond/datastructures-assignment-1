import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;
import java.util.Scanner;

/*
 * Custom exception to handle invalid errors
 */
class InvalidCommand extends RuntimeException {
    public InvalidCommand(String message) {
        super(message);
    }
}


/*
 * \brief LinkedListDriver class.
 */
public class LinkedListDriver {

	/*
	 * \brief List we want to persist throughout the run.
	 */
	public static SortedLinkedList list = new SortedLinkedList();

	/*
	 * \brief Prompts a user to enter a list
	 */
	public static SortedLinkedList promptUserForList() {
		Scanner scanner = new Scanner(System.in);
		SortedLinkedList newList = new SortedLinkedList();

		// ask user for length of list
		System.out.print("Enter the length of the new list: ");
		int length = scanner.nextInt();

		// ask user for list
		System.out.print("Enter the numbers: ");
		for (int i = 0; i < length; i++) {
			int number = scanner.nextInt();
			newList.insertItem(new ItemType(number));
		}

		// print this and that list
		System.out.println("list 1: " + list.toString());
		System.out.println("list 2: " + newList.toString());

		// return that list
		return newList;
	}

	public static void stringArrayToList(String[] values) {
	    // Insert each integer into the linked list
	    for (String value : values) {
		int intValue = Integer.parseInt(value);
		list.insertItem(new ItemType(intValue));
	    }
	}

	public static void readFile(String filePath) {
		try {
			// Read in file
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			// Read in line in file
			String line = br.readLine();
			// Split line up by spaces
			String[] values = line.split(" ");
			// Convert string array to list
			stringArrayToList(values);
			// close the file
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Please make sure that the file that you passed in exists.");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("Could not properly read the file that you passed in.");
			System.exit(1);
		}
	} // readFile
	
	/*
	 * \brief Print Commands
	 */
	public static void printCommands() {
		System.out.println("Commands:");
		System.out.println("(i) - Insert value");
		System.out.println("(d) - Delete value");
		System.out.println("(s) - Search value");
		System.out.println("(a) - Delete alternate nodes");
		System.out.println("(m) - Merge lists");
		System.out.println("(t) - Find intersection");
		System.out.println("(p) - Print list");
		System.out.println("(l) - Print length");
		System.out.println("(q) - Quit program");
		System.out.println();
	}

	/*
	 * \brief Starts the interface
	 */
	public static void startCLI() {
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		printCommands();
		System.out.print("Enter command: ");
		while (running) {
			try {
				// handles next commands
				handleAction(scanner.nextLine().toLowerCase());
				System.out.print("Enter command: ");
			}
			// handles invalid commands
			catch (InvalidCommand e) {
				System.out.print("Invalid command try again: ");
			}
		}
	}

	/**
	 * \brief Handles a given command 
	 */
	public static void handleAction(String action) throws InvalidCommand {
		Scanner scanner;
		switch (action) {
			case "p": // handle print
				System.out.println("The list is: " + list.toString());
				break;
			case "q": // handle quit
				System.out.println("Exiting the program...");
				System.exit(0);
				break;
			case "l": // get length
				System.out.println("The length of the list is " + list.getLength());
				break;
			case "i": // insert
				System.out.print("Enter a number to insert: ");
				scanner = new Scanner(System.in);
				int insertValue = scanner.nextInt();
				System.out.println("Original list: " + list.toString());
				list.insertItem(new ItemType(insertValue));
				System.out.println("New list: " + list.toString());
				break;
			case "d": // delete
				System.out.print("Enter a number to delete: ");
				scanner = new Scanner(System.in);
				int deleteValue = scanner.nextInt();
				System.out.println("Original list: " + list.toString());
				boolean isNull = list.getHead() == null;
				list.deleteItem(new ItemType(deleteValue));
				if (!isNull) {
					System.out.println("New list: " + list.toString());
				}
				break;
			case "s": // search
				System.out.print("Enter a number to search: ");
				scanner = new Scanner(System.in);
				int searchValue = scanner.nextInt();
				System.out.println("Original list: " + list.toString());
				int index = list.searchItem(new ItemType(searchValue));
				if (index != -1) {
					System.out.println("The item is present at index " + index);
				}
				break;
			case "a": // delete alternate node
				System.out.println("Original list: " + list.toString());
				try {
					list.deleteAlternateNodes();
					System.out.println("New list: " + list.toString());

				} catch (RuntimeException e) {
					System.out.println(e.getMessage());
					System.out.println("Modified list: " + list.toString());
				}
				break;
			case "m": // merge 
				SortedLinkedList mergeList = promptUserForList();
				System.out.println("Merged list: " + list.toString());//list.mergeList(mergeList).toString());
				break;
			case "t": // Intersect
				SortedLinkedList intersectList = promptUserForList();
				list.intersection(intersectList);
				break;
			default: 
				throw new InvalidCommand("");
		}
		System.out.println();
	}




	public static void main(String[] args) {
		try {
			readFile(args[0]);
		}
		catch (Exception e) {
			System.out.println("Please pass in a file as an argument");
			System.exit(1);
		}
		startCLI();

	}

}
