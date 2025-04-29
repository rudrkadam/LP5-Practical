# LP-V (Distributed Systems)
All problems statements (based on the 7 lab assignments) of LP-V, along with their implementations.

---
## 1. RMI (11)
**Implement multi-threaded client/server Process communication using RMI**
1. Addition of Numbers
2. Multiplication of Numbers
3. Division of Numbers
4. Subtraction of Numbers
5. 2's Power of Number
6. Celsius to Fahrenheit Conversion
7. Miles to Kilometer Conversion
8. Printing Name appending to Hello
9. Compare two Strings lexicographically
10. Count Vowels in Word
11. Factorial of Number

### Implementation
1. All **remote methods are declared** in `ServerInterface.java` interface.
2. All these **remote methods are implemented** in `ServerImplementation.java` class.
3. **Implement the problem statement** in the `Client.java` class.
4. **No need to make any changes** to the `Server.java` class.
5. Compile all the classes using:
	```bash
	javac *.java
	```
6. Run Server in one terminal window using:
	```bash
	java Server
	```
7. Run Client in another terminal window using:
	```bash
	java Client
	```

---
## 2. MPI (4)
**Design a distributed application using MPI for where root process is distributed to the worker processes**
1. Intermediate Sums of Array Elements
2. Intermediate Multiplication of Array Elements
3. Average of Array Elements
4. Reciprocals of Array Elements

### Implementation
1. Download and extract [MPJ Express](https://sourceforge.net/projects/mpjexpress/files/releases/) as a folder `mpj` in the same directory as your source code.
2. Run the following commands:
	```bash
	export MPJ_HOME=./mpj
	```
	```bash
	javac -cp $MPJ_HOME/lib/mpj.jar <Java File>
	```
	```bash
	$MPJ_HOME/bin/mpjrun.sh -np <number> <Class File>
	```

---
## 3. CORBA (2)
**Develop a distributed application using CORBA to demonstrate object brokering**
1. String Reversing
2. Changing case of String to Uppercase

---
## 4. Clock Synchronization (3)
**Implement clock synchronization with Berkeley algorithm with time daemon server**

### Implementation
1. Run `python server.py` / `java Server` in one terminal window.
2. Run `python client.py` / `java Client` in another terminal window.

---
## 5. Token Ring based Mutex (3)
**Implement token ring based mutual exclusion algorithm**

### Implementation
1. Token Ring Simulation done in `TokenRingAlgo.java` class.
2. Simulate entering / exiting critical section in `RingMutex.java`:
	1. Compile `RingMutex.java` to bytecode.
	2. In one terminal window, do:
		```bash
		java RingMutex false localhost 5000 5001
		```
	3. In another terminal window, do:
		```bash
		java RingMutex true localhost 5001 5000
		```

---
## 6. Leader Election (6)
**Perform Leader Election using**
1. Bully Algorithm (3)
2. Token Ring Algorithm (3)

---
## 7. Web Service (5)
**Create and consume a web service**
1. Simple Calculator
2. Simple Interest Calculator
3. Take as input, and display User's Name
4. Miles to Kilometers Conversion (2)

---
## Additional Resources
1. [HarshvardhanP01 GitHub](https://github.com/HarshvardhanP01/Distributed-Systems)
2. [Megha Dandapat GitHub](https://github.com/meghadandapat/BE-IT-DS)
3. [Ranjeet Kumbhar GitHub](https://github.com/RanjeetKumbhar01/BE_IT_DS_ASSIGNMENTS_SPPU)
4. [pranav15061 GitHub](https://github.com/pranav15061)
5. [Web Service in NetBeans](https://youtu.be/ASd1S-_HLWw)
6. [Web Service in NetBeans](https://youtu.be/0z-HvSfr-M4)