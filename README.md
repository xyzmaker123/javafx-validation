# JavaFX validation library (proof of concept)

## To run project:
```
  ./gradlew run
```




## INFO
This main goal of this project is to find best approach for making validation in JavaFX. 
For now focuses only on TextField, but aim is to cover other controls as well.

Considered approaches:
1) create TextField wrapper which handles validation logic internally
2) create TextField wrapper which accepts error messages, but validation logic is made outside