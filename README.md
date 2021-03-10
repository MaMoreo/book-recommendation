# book-recommendation
A simple book recommendation service which is usable via a REST API.

## Circle CI Status: 
[![CircleCI](https://circleci.com/gh/MaMoreo/book-recommendation.svg?style=svg)](https://circleci.com/gh/MaMoreo/book-recommendation) 
![GitHub](https://img.shields.io/github/last-commit/MaMoreo/book-recommendation)

**Task**

Provide a simple book recommendation service which is usable via a REST API. It needs to be possible to define a new user, who will then be provided with 20 book recommendations. For a recommendation of a user feedback can be provided. The feedback can either be "liked the book", "disliked the book" or "not interested".

**Requirements:**

    Users are identified by their username.
    The list of recommendations should contain exactly 20 entries if possible.
    The code should be tested as appropriated.

A suitable dataset for the books is findable alongside this challenge. 

**Process**

    Write down a brief proposal (not more than 1-2 hours) on how you do plan to solve the task. The proposal should clarify the API design, the frameworks/libraries to be used and how the recommendations are generated. If you need to cut stuff a proper solution should have but you are not doing it because it feels out of scope - state that. Show us what the thing should do and where you cut to keep in scope.
    Send the proposal to us before you start the implementation. We will provide you with feedback so that you can be sure the solution is suitable and your interpretation doesn't get too complicated. We assume you should be able to solve the task in one or two longer afternoons/evenings (6-8 hours maximum). If your plans will take a lot longer to be achieved, consider simplifying. But if you really want to invest more, go for it. (smile)
    Provide us with a git repository (eg. git init, do your stuff and then send the zip / private hosted repo) which shows us your development process and contains clear steps to quickly execute your app. 

**Resources** - books.csv

The provided book list is a part of the dataset from https://github.com/uchidalab/book-dataset/ and contains 1099 books. Here is the shortcut: https://pastebin.com/rV6HThLv
