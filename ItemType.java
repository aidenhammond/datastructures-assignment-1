/*
 * \brief ItemType class
 */
public class ItemType {
	/*
	 * \brief Private value held inside the ItemType object.
	 */
	private int value;

	/*
	 * \brief Compares teh value of item with the current objects value.
	 * \param item Item which you are comparing the current object against.
	 * \return Returns -1 if the value of the current object is less than the value
	 * 	   in item, 0 if equal, and 1 if greater.
	 */
	public int compareTo(ItemType item) throws RuntimeException {
		if (value < item.getValue()) {
			return -1;
		}
		else if (item.getValue() == value) {
			return 0;
		}
		else if (value > item.getValue()) {
			return 1;
		}
		throw new RuntimeException("Value passed into compareTo is invalid");
	}

	/*
	 * \brief Returns the value instance variable.
	 * \return The value instance variable.
	 */ 
	public int getValue() {
		return value;
	}

	/*
	 * \brief Initializes the data member by variable num.
	 * \param num Variable which is stored in the data member that this function initializes.
	 */ 
	public void initialize(int num) {
		value = num;
	}

	/*
	 * \brief Constructs an ItemType object.
	 * \param num Variable stored in the constructed ItemType object.
	 */
	public ItemType(int num) {
		initialize(num);
	}

}

