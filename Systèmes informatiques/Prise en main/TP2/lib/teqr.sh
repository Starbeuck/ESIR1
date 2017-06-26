#!/bin/bash



#recuperation des chemins : absolu, projet, source, classe et librairies
# donne le chemin absolu
cheminabs=`$(realpath $1)`

fSource=$(echo $cheminabs | sed -e 's/src.*/src/')
biblio=$(echo)

javac -sourcepath $fSource
      -cp $
