# Code Interview for Lab  

## Group 3  

### Lab 4 Design and Implementation  

Tasks: Learn to serialize java class, so that we can save and load it, and import and export it later on.  

Mindset  
Task1: Save file: Class -> Save into the object -> Save into the File;Hence, we should create a fos for File output stream and out for ObjectOutputStream and store
the class into the ObjectOutputStream by function "writeObject(this)".

Task2: Read file: read the file input, then object input, later we can use .readObject function to read the class stored in the file. "Remember to close the file"

Task3 & 4: We still adopt the mindset above. Note that, we should preprocess the fileName by replacing the " " with "_".  
When write the file, we should use ".write()" and "flush()", "close()" in sequence.


## Task 2 for Code Interview
How to create a TableNode?  

Mindset:  
Loading: We first read the String from the file, and split the file by "\n". Then, we have a list called listLine. The first element of listLine is the table structure. We can store the table structure into the this.title for Node.
For the rest element, we can create a new class called TableNode, with this.content as a 2-dimensional java arraylist. We can split each element by "," and store the 
table data into the two dimensional element, with each element in first dimension for each row, and each element in the second dimension stand for the column of each
value corresponding to the chosen row.

Saving: We can join the string within each row in the table data with ", ", then we join each row with "\n" in this.title and this.content in TableNode class.
Eventually we can store the String into a newly created file like we did in Lab4.

We can also add some formatting check if find necessary.


