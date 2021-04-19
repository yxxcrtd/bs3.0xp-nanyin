@echo on
@set currentpath=%cd%
@set basepath=%cd%
cd %~dp0
@echo  "BasePath   : %basepath%"
@echo  "BatFilePath: %currentpath%"


@echo  "==============Mavne Install==============="
call mvn clean install -f %currentpath%\运营一体化.xml --settings %1

@echo  "==============Copy Jar==============="
copy %basepath%\tams-system\yunying\app\target\tams-yunying.jar %currentpath%\jar\tams-yunying.jar
copy %basepath%\tams-system\yunying\batch\target\tams-yunying-batch.jar %currentpath%\jar\tams-yunying-batch.jar

