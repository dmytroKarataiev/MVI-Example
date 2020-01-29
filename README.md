## Experiments with the MVI Architecture

This repo is a complete refactoring of the excellent MVI example by AdamMc331 where I tried to see if we can merge and maintain multiple ViewModels that emit a single State object to a view
https://github.com/AdamMc331/MVWTF

## UML Class diagram 
Based on my limited understanding of UML the architecture looks something like that:
[mvi uml diagram.png](resources/mvi uml diagram.png)

## Why MVI?
When you have a very complicated screen that consumes 4+ ViewModels it gets extremely hard to manager screen state. 
You need to account for: n (number of viewmodels) * at least 4 states (error, progress, empty, loaded). 
MVI architecture takes some inspiration from the Reducer pattern that combines all the different data sources and emits a single consistent state.