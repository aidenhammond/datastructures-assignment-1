public class NodeType {
	public ItemType info;
	public NodeType next;

	/*
	 * \brief Constructs a NodeType object given a value / item.
	 * \param item Item stored in constructed NodeType.
	 */
	public NodeType(ItemType item) {
		info = item;
		next = null;
	}
}
