MyHttpServer 🌐
A simple, multithreaded HTTP Server built from scratch in Java. This project is an exploration into the fundamentals of network programming and the HTTP protocol.

🚀 Project Goal
The primary goal of this project is to create a lightweight, high-performance HTTP server that can handle multiple client connections concurrently and serve static web content based on a JSON configuration file.

📊 Current Status: In Progress
This project is currently under development. The core server loop and multithreaded connection handling are implemented. It can successfully parse a simple configuration file and serve static HTML files.

Implemented Features
TCP Server: Listens for incoming connections on a configurable port.

Multithreading: Spawns a new worker thread for each incoming client connection to handle requests concurrently.

Configuration Manager: Loads server settings (port, webroot) from an http.json file.

Static File Serving: Can serve basic HTML and text files from the specified webroot directory.

Future Work & Planned Features
[ ] Full parsing of HTTP headers and methods (GET, POST, etc.).

[ ] Generating proper HTTP response codes (e.g., 200 OK, 404 Not Found, 501 Not Implemented).

[ ] Handling different content types (CSS, JavaScript, images).

[ ] Implementing logging for server events and errors.

[ ] Enhancing security and robustness.

🛠️ How to Run
Clone the Repository

Bash

git clone https://github.com/Anujit268/MyHttpServer.git
cd MyHttpServer
Configure the Server
Create a src/main/resources directory if it doesn't exist. Inside it, create a file named http.json with your desired settings:

JSON

{
  "port": 8080,
  "webroot": "public" 
}
port: The port the server will listen on.

webroot: The folder (relative to the project root) where your static files (e.g., index.html) are located. Make sure you create this folder!

Compile and Run
This project uses Maven. You can compile and run it directly from your IDE (like IntelliJ) or by using the command line.

Open the project in your IDE and run the main method in the HttpServer class. The server will start, and you will see a confirmation message in the console.

Test in Browser
Place an index.html file inside your specified webroot folder. Then, open your web browser and navigate to:

http://localhost:8080
📂 Project Structure
.
└── src
    └── main
        ├── java
        │   └── com
        │       └── anujit
        │           ├── server
        │           │   ├── HttpServer.java          # Main class, starts the server.
        │           │   └── HttpServerWorkerThread.java # Handles individual client connections.
        │           └── config
        │               ├── Configuration.java       # POJO for storing config data.
        │               └── ConfigurationManager.java  # Loads and parses the JSON config.
        └── resources
            └── http.json                     # Configuration file.
💻 Technologies Used
Java: Core programming language.

Maven: For project dependency management.

Jackson: For parsing the JSON configuration file.