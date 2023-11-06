#!/bin/sh
cd Game &&
#1. Make target directory:
rm -rf target &&
rm -rf lib &&
mkdir target &&
mkdir lib &&

#2
touch lib/jcommander-1.78.jar &&
curl -o lib/jcommander-1.78.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.78/jcommander-1.78.jar &&
touch lib/JCDP-2.0.3.1.jar &&
curl -o lib/JCDP-2.0.3.1.jar https://repo1.maven.org/maven2/com/diogonunes/JCDP/2.0.3.1/JCDP-2.0.3.1.jar &&

#3
javac -d target -sourcepath src/java -cp lib/JCDP-2.0.3.1.jar:lib/jcommander-1.78.jar:. src/main/java/game/*.java &&

#4
jar -xf lib/jcommander-1.78.jar &&
jar -xf lib/JCDP-2.0.3.1.jar &&
rm -rf META-INF &&
mv com target &&

#5
jar cf target/game.jar -C target/ . &&

#6
java -cp target/game.jar Main --enemiesCount=10 --wallsCount=10 --size=30 --profile=production 
