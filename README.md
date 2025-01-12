# wordtrainer
## author: Felix Aust
## date: 16.10.2024

This is a wordtrainer.

## Model
The model consists of a Statistics class, that is responsible for saving the correct and wrong guesses, 
a WordPair class that contains a single validated URL and the corresponding word to the picture and the WordTrainer class which uses the previously mentioned classes.
The wordtrainer has an array of wordpairs and is able to be saved using the persistence methods.

## Persistence
Using ObjectStreams the object (wordtrainer) gets saved and read to and from a .ser file. 