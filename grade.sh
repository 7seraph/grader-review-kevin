CPATH='.;lib/hamcrest-core-1.3.jar;lib/junit-4.13.2.jar'

rm -rf student-submission
rm -rf grading-area

mkdir grading-area


git clone $1 student-submission > git-output.txt
echo 'Finished cloning'


# Draw a picture/take notes on the directory structure that's set up after
# getting to this point

# Then, add here code to compile and run, and do any post-processing of the
# tests

# check if file exists
if [ -f "student-submission/ListExamples.java" ]
then 
    echo "File Found"
else 
    echo "File not found"
    exit 1
fi

#copy over the files to directory

cp -r lib grading-area/
cp student-submission/ListExamples.java grading-area/
cp TestListExamples.java grading-area/


# compile
cd grading-area
javac -cp $CPATH *.java

# checks if error occured while compiling
if [ $? -ne 0 ]
then
    echo "Compilation failed"
    exit 1
else
    echo "Compilation success"
fi

#run the file
java -cp $CPATH  org.junit.runner.JUnitCore TestListExamples > test-results.txt

#gets results of JUnit Test
TESTRESULTS=$(cat test-results.txt | tail -n 2 | head -n 1)
echo $TESTRESULTS


TOTALTESTS=$(echo $TESTRESULTS | awk -F'[, ]' '{print $3}')

FAILED=$(echo $TESTRESULTS | awk -F'[, ]' '{print $6}')


PASSED=$(($TOTALTESTS - $FAILED))
GRADE=($PASSED/$TOTALTESTS)
#PASSED=$(($FAILED-$TOTALTESTS)) / $TOTALTESTS
echo 'Your Grade is :' $GRADE
