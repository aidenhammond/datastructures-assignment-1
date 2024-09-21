Aiden Hammond (811-564-610) amh37629@uga.edu
Esther Martinez (811-692-495) em63667@uga.edu

# Division of Work
	Aiden - SortedLinkedList, NodeType, ItemType
	Esther - LinkedListDriver

# Outside Classes
These are the classes that we used:
- Buffered reader: https://www.geeksforgeeks.org/java-io-bufferedreader-class-java/ 
- String tokenizer: https://www.geeksforgeeks.org/stringtokenizer-class-in-java/
- Scanner: https://www.w3schools.com/java/java_user_input.asp

# How to compile
Either use the `make` command to compile, and `make test` to run with input.txt and `./run <file>` to run with an input file.

This is the code used to compile:

	javac LinkedListDriver.java SortedLinkedList.java ItemType.java NodeType.java

This is the code used to run:

    java LinkedListDriver input.txt

Replace input.txt with any file that you need.
These are machine specs that this code can on:
java 17.0.6 2023-01-17 LTS
Java(TM) SE Runtime Environment (build 17.0.6+9-LTS-190)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.6+9-LTS-190, mixed mode, sharing)
Darwin Aidens-MBP.attlocal.net 23.5.0 Darwin Kernel Version 23.5.0: Wed May  1 20:12:58 PDT 2024; root:xnu-10063.121.3~5/RELEASE_ARM64_T6000 arm64

# Pseudocode
## mergeList
First, lets get some terminology:
list1 = the first list, the list that we're calling mergeList on
list2 = the list we're merging into the first list
   1. Get a copy of the first list        - O(1)
   1. In the copied list, get the head    - O(1)
   2. If the head is null, return         - O(1)
   3. Iterate over list2, for each item   - O(n)
        a. Run insertItem                 - O(n)
### Big-O Complexity
Since you have to iterate over list2 to get each item, then you have to iterate over the first list to figure out where to place each item, which means you have to do an iteration in an iteration, making it O(n^2)

## Intersection
First, lets get some terminology:
list1 = the first list, the list that we're calling intersection on
list2 = the list we're intersecting into the first list
    1. Check if the list is empty or not
    2. Create string
    3. Iterate over list1
        a. Iterate over list2
            - Check if item in list2 is equal to item in list1
            - if it is, add it to a string.
            - get next item in list2
        b. get next item in list1
    4. print string.
### Big-O Complexity
Since you have to iterate over both lists, with one iteration within another, it makes it O(n^2)

