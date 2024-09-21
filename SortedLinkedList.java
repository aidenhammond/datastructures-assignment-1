import java.lang.RuntimeException;

/*
 * \brief SortedLinkedList class.
 */
public class SortedLinkedList {
	/*
	 * \brief Head of linked list.
	 */
	private NodeType head;

	/*
	 * \brief Returns the head of the linked list.
	 * \returns Head of linked list.
	 */
	public NodeType getHead() {
		return head;
	}

	/*
	 * \brief Initializes a sorted linked list object.
	 */
	public SortedLinkedList() {
		head = null;
	}
	
	/*
	 * \brief Returns the length of the linked list.
	 * \details You need to do a loop over the list and get the number of nodes in the list.
	 */
	public int getLength() {
		if (head == null) {
			return 0;
		}
		int length = 1;
		NodeType node = head;
		while (node.next != null) {
			length++;
			node = node.next;
		}
		return length;
	}

	/*
	 * \brief Item should be inserted to the linked list maintaining the ascending sorted order.
	 * \details General case: 
	 * 		Insert at the middle or end. 
	 * 	    Special case:
	 * 	    	Insert the first element
	 * 	    	Insert in an empty list
	 * 	    	Print "Sorry. You cannot insert the duplicate item." when the user tries to insert a duplicate item.
	 * \param item Item which should be inserted to the linked list maintaining the ascending sorted order.
	 */
	private void _insertItem(ItemType item) throws RuntimeException {
		NodeType node = new NodeType(item);

		// Handles if the head is null / the linked list is empty / special case 
		if (head == null) {
			head = node;
			return;
		}

		// Handles if we need to insert at the beginning / special case
		if (item.compareTo(head.info) < 0) {
			node.next = head;
			head = node;
			return;
		}

		// Handles inserting it into the middle / general case
		NodeType current = head;
		NodeType previous = null;
		
		while (current != null && item.compareTo(current.info) > 0) {
			previous = current;
			current = current.next;
		}

		// Handle duplicate
		if (current != null && item.compareTo(current.info) == 0) {
			throw new RuntimeException("Item already exists");
		}

		// Insert the item into the list
		node.next = current;
		if (previous != null) {
			previous.next = node;
		}
	}
	/*
	 * \brief Public wrapper for _insertItem.
	 */
	public void insertItem(ItemType item) {
		try {
			_insertItem(item);
		}
		catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * \brief Deletes an item from the list.
	 * \details The node in the list that contains an item equal to the item parameter should
	 * 	    be removed. You should handle all cases of deleting an element.
	 * 	    General Case:
	 * 	    	Deleting the last element or an element in the middle.
	 * 	    Special Case:
	 * 	    	Deleting the first element
	 * 	    	Deleting the only element
	 * 	    	Attempting to delete a non-existing item should print an appropriate message.
	 * 	    	Attempting to delete from an empty list should print an appropriate message.
	 * \param item Item to be removed from the list.
	 */
	private void _deleteItem(ItemType item) throws RuntimeException {
		// Handles an empty list // special case
		if (head == null) {
			//System.out.println("Sorry. You cannot delete an item from an empty list.");
			//return;
			throw new RuntimeException("You cannot delete an item from an empty list");
		}
		// Handles deleting the only element // special case
		if (head.next == null && head.info.compareTo(item) == 0) {
			head = null;
			return;
		}
		// Handles deleting the first element // special case
		if (head.info.compareTo(item) == 0) {
			head = head.next;
			return;
		}
		// Handles finding the last element or an element in the middle to delete
		NodeType current = head;
		NodeType previous = null;
		while (current != null && current.info.compareTo(item) != 0) {
			previous = current;
			current = current.next;
		}
		// Handles attempting to delete a non-existing item from the list
		if (current == null) {
			throw new RuntimeException("The item is not present in the list");
			//System.out.println("Sorry. You cannot delete a non-existing item from the list.");
			//return;
		}
		// maybe dont need if previous != null because previous at this point shouldnt be null
		previous.next = current.next;
	}
	
	public void deleteItem(ItemType info) {
		try{
			_deleteItem(info);
		}
		catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * \brief Search the linked list that contains an item equal to the parameter item and returns its index. 
	 * \details Print a message, as shown in the sample output below, if the item is present in the list.
	 * \throws RuntimeException if the list is empty of the item is not present in the list.
	 * \param item Item to search for in the linked list.
	 * \returns Index of item to search for.
	 */
	public int searchItem(ItemType item) {
		// Handles an empty list as shown in assignment
		if (head == null) {
			//throw new RuntimeException("The list is empty");
			System.out.println("The list is empty");
			return -1;
		}
		// Handles non-empty list.
		int index = 1;
		NodeType current = head;
		NodeType previous = null;
		while (current != null && current.info.compareTo(item) != 0) {
			previous = current;
			current = current.next;
			index++;
		}
		// Handles if item is not found
		if (current == null) {
			//throw new RuntimeException("Item is not present in the list");
			System.out.println("Item is not present in the list");
			return -1;
		}
		// If item found, return index at which it was found.
		return index;
	}
	

	/*
	 * \brief This function should merge two lists and not include any duplicate iterms in the list.
	 * \details If there are duplicates in the two lists, the merge function should keep only one of the duplicate instances in the resulting list.
	 * 	    Example:
	 * 	    	List 1: 9 13 45 36 47 89
	 * 	    	List 2: 3 45 89 96
	 * 	    	Merged List: 3 9 13 36 45 47 89 96
	 * 	    See the sample output in the assignment for more examples
	 * 	    In the README file there is the pseudocode (steps) for this merge operation. The complexity (Big-O) of this operation is given there.
	 * \param list Linked list which will be merged into this list.
	 */
	public void mergeList(SortedLinkedList list) {
		NodeType node = list.getHead();
		// Trying to merge an empty list.. do nothing :)
		if (node == null) {
			return;
		}
		while (node != null) {
			try {
				_insertItem(node.info);
			}
			catch (RuntimeException e) {
				break;
			}
			node = node.next;
		}
	}

	/*
	 * \brief Deletes alternate nodes from the list.
	 * \details It should skip the first node, delete the second, skip the third, delete the fourth, and so on. You are not allowed to create a
	 * 	    new list in this function. Just modify the original list by dleeting the nodes in it.
	 * 	    Example:
	 * 	    	List before alternate delete: 3 7 14 26 74 78
	 * 	    	List after alternate delete 3 14 74
	 * 	    See the sample outputs in the assignment for more examples.
	 * 	    The complexity of this function should not be more than O(n).
	 */
	public void deleteAlternateNodes() throws RuntimeException {
		// Handle case of empty list
		if (head == null) {
			throw new RuntimeException("The list is empty");
		}
		if (head.next == null) {
			head = null;
			return;
		}
		NodeType start = head; 	

		// While the next node is not null, delete the next node, then set your current node to the next node after the deleted node.
		while (start != null && start.next != null) {
			start.next = start.next.next;
			start = start.next;
		}
	}

	/*
	 * \brief This function uses another list, finds the common elements between the input list and the original lists,
	 * 	  then prints the results.
	 * \details Example:
	 * 		List 1: 2 4 14 16 35 47 54 83
	 * 		List 2: 1 3 4 15 35 54 74 91
	 * 		Intersection: 4 35 54
	 * 	    See sample outputs in the assignment for more examples.
	 * 	    Like the merge function in the README file, the pseudocode (steps) for the 'Intersection' function are given.
	 * 	    	Using the pseudocode, the complexity (Big-O) of the 'Intersection' function is also given.
	 * \param list List which this list is intersected with.
	 */
	public void intersection(SortedLinkedList list) {
		String intersections = "";
		// Check if list is empty
		if (head == null || list.getHead() == null) {
			System.out.println("Intersection of lists: ");
			return;
		}
		NodeType this_node = head;

		while (this_node != null) {
			NodeType node = list.getHead();
			while(node != null && this_node.info.getValue() >= node.info.getValue()) {
				if (this_node.info.compareTo(node.info) == 0) {
					intersections += node.info.getValue() + " ";
				}
				node = node.next;
			}
			this_node = this_node.next;
		}
		System.out.println("Intersection of lists: " + intersections);
	}

	/*
	 * \brief Returns the string version of the linked list.
	 * \returns The string version of the linked list.
	 */
	public String toString() {
		NodeType node = head;
		String string = new String("");
		while (node != null) {
			if (node.next == null) {
				string += (node.info.getValue());
			}
			else {
				string += node.info.getValue() + " ";
			}
			node = node.next;
		}
		return string;
	}
}
