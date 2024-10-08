# coding challenge

## Write a web API for quotes

Create a web application using **Spring Boot** that allows the creation and fetching of quotes.

### Acceptence Criteria

- A quote consists of an author (the person who said it) and the quote itself. 
- I can create a quote using a REST Api 
- I can get paginated list of all quotes via the REST Api
- I can get a paginated filtered list of quote containing all quotes from a specific author
- The API is secured using Basic Authentication
- There are two users: One who is only allowed to read quotes and another one who can also create them.
- The username of the user who created the quote needs to be stored alongside that quote. 

### Other Requirements

- Created quotes should be persisted to a relational database, but don't have to survive a restart of the server. 
- Please provide sample requests, ideally in the [IntelliJ Http Format](https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html#composing-http-requests), or similar.
- REST API with JSON.
- Create a readme with instructions to start the server locally.
- Make your solution available on GitHub in a **private** repository.
- Invite [@lmller](https://github.com/lmller) and [@mschwarzcircus](https://github.com/mschwarzcircus) to the repository and let us know via email that you completed the challenge.

### Questions and Help

If you have any questions, feel free to reach out to us. 
In case you want some test data, there's a [quotes.txt](/quotes.txt) with some quotes. 

### Before you submit

- Make sure everything works :-)
- Think: _"Does the code meet the quality requirements of my usual work? Would I create a Pull Request like this?"_ 
