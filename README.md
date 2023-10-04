# Timer using UDP Protocol
This project is a clock application that uses the UDP protocol to display the current time. The application consists of a client and a server.

# Features
The server runs continuously and listens for incoming requests from clients.
The client sends a request to start or stop receiving time from server.
The client displays the received time on user interface.

# Prerequisites
Before running this application, make sure you have the following prerequisites:

Java Development Kit (JDK) installed on your system.
Basic knowledge of Java programming and networking concepts.

# Installation
Clone this repository to your local machine.
Open the project in your favorite Java IDE.
Build the project to compile the source code.

# Usage
Start the server by running the TimeServer.main() method.
Start the client by running the TimeClient.main() method.
The client will display the current time received from the server.

# Configuration
The server and client use the UDP protocol to communicate. By default, the server listens on port 5000. If you want to change the port number, you can modify the serverThread.java file and update the ds variable initialization.

# Troubleshooting
If the server is not responding, make sure it is running and listening on the correct port.
If the client is not receiving the time, make sure it is connected to the correct server address and port.

# Acknowledgements
Java Documentation for the official Java documentation.
UDP Protocol for information about the UDP protocol.
# Contributing
Contributions are welcome! If you find any issues or have suggestions for improvement, please open an issue or create a pull request.
