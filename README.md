# coding challenge

## Option 1: Write a web API that returns quotes in JSON format.
```
GET /quotes
GET /quotes/random
POST /quotes
```
The result of the GET requests should look like this.
```json
{
  "name": "Dean Pelton",
  "quote": "From now on, April 1st is now March 32nd."
}
```


## Option 2: Fetch and merge two JSON files and display their content
Display the contents of the following "APIs" in a web app: 
- https://raw.githubusercontent.com/lmller/codingchallenge/main/people.json
- https://raw.githubusercontent.com/lmller/codingchallenge/main/quotes.json

Use the following format: `<Name>: "<Quote>"`

Each entry has an ID, you can determine wich quote belongs to whom by checking that ID.

--- 

You can use your favourite tools.

It's ok if you don't make it in time. We want to get a general idea on _how_ you work and approach problems.
