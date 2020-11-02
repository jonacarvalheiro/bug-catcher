# bug-catcher

## Create jar with all dependencies

mvn clean compile assembly:single

## Install the .jar on the m2 repository

mvn install:install-file -Dfile=bug-catcher-1.0.0.jar -DgroupId=qae -DartifactId=bug-catcher -Dversion=1.0.0 -Dpackaging=jar
